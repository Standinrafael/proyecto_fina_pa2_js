package ec.edu.uce.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.ClienteVip;
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.ReservaVip;
import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.modelo.VehiculoSencillo;
import ec.edu.uce.modelo.VehiculoVip;
import ec.edu.uce.service.IClienteService;
import ec.edu.uce.service.IGestorClienteService;
import ec.edu.uce.service.IGestorEmpleadoService;
import ec.edu.uce.service.IGestorReporteService;
import ec.edu.uce.service.IReservaService;
import ec.edu.uce.service.IVehiculoService;

@Controller
@RequestMapping("/sistemas/")
public class ReporteController {

	@Autowired
	private IGestorReporteService reporteService;

	@Autowired
	private IGestorClienteService clienteService;

	@Autowired
	private IGestorEmpleadoService empleadoService;

	@Autowired
	private IClienteService clieService;

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IReservaService reservaService;

	
	// Variables utilizados en el  metodo de reserva
	
	private Cliente clienteCopia = new Cliente();

	private Vehiculo vehiculoCopia = new Vehiculo();

	private int dias;

	private LocalDateTime fechaUno;

	private LocalDateTime fechaDos;

	// --------------MENU
	// PRINCIPAL--------------------------------------------------------------------

	@GetMapping("menu")
	public String menu(Model model) {

		return "menu";
	}

	// ----------------MENU DE OPCIONES
	// ------------------------------------------------------------

	// -----MENU DE CLIENTES

	@GetMapping("menu/clientes")
	public String menuClientes(Model model) {

		return "menuClientes";
	}

	// -----MENU DE EMPLEADOS

	@GetMapping("menu/empleados")
	public String menuEmpleados(Model model) {

		return "menuEmpleados";
	}

	// ----MENU DE REPORTES

	@GetMapping("menu/reportes")
	public String menuReportes(Model model) {

		return "menuReportes";
	}

	// -----------------OPCIONALIDADES DE CADA
	// SECCION-------------------------------------------

	// ----------CLIENTES----------------------

	// BUSCAR VEHICULOS DISPONIBLES

	@GetMapping("buscar/vehiculo/disponible")
	public String seleccionarMarcaModelo(Model modelo) {

		return "vehiculoDisponible";
	}

	@GetMapping("encontrar/vehiculo/disponible")
	public String obtenerVehiculoDisponible(@RequestParam("marca") String marcaVehiculo,
			@RequestParam("modelo") String modeloVehiculo, Model modelo) {

		List<VehiculoSencillo> listaVehiculos = this.clienteService.buscarVehiculoDisponbleSencillo(marcaVehiculo,
				modeloVehiculo);

		// Comprobacion se hace en el html con th:if

		modelo.addAttribute("vehiculos", listaVehiculos);

		return "vehiculosDisponiblesResultado";

	}

	// RESERVAR VEHICULO

	@GetMapping("datos/reserva")
	public String recibirDatosReserva(Model modelo) {

		return "datosReserva";
	}

	@GetMapping("comprobar/datos")
	public String ComprobarDatos(@RequestParam("placa") String placaVehiculo,
			@RequestParam("cedula") String cedulaCliente,
			@RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime primeraFecha,
			@RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime segundaFecha,
			Model modelo) {
		dias = segundaFecha.getDayOfMonth() - primeraFecha.getDayOfMonth();
		fechaUno = primeraFecha;
		fechaDos = segundaFecha;

		// Verificacion de que existe el cliente

		Cliente cliente1 = this.clieService.buscarClientePorCedula(cedulaCliente);
		if (cliente1 != null) {
			clienteCopia = cliente1;

			// Verificacion de que existe el vehiculo

			Vehiculo vehiculo1 = this.vehiculoService.buscarVehiculoPorPlaca(placaVehiculo);
			if (vehiculo1 != null) {
				vehiculoCopia = vehiculo1;

				// Verificaciones de los diferentes tipos de solapamientos de fechas

				Reserva reserva1 = this.reservaService.buscarReservaVehiculoFechas(vehiculo1.getId(), primeraFecha);

				if (reserva1 == null) {

					Reserva reserva2 = this.reservaService.buscarReservaVehiculoFechas(vehiculo1.getId(), segundaFecha);

					if (reserva2 == null) {

						Reserva reserva3 = this.reservaService.buscarReservaVehiculosCasoEspecialFechaInicio(
								vehiculo1.getId(), primeraFecha, segundaFecha);

						if (reserva3 == null) {

							Reserva reserva4 = this.reservaService.buscarReservaVehiculosCasoEspecialFechaFin(
									vehiculo1.getId(), primeraFecha, segundaFecha);

							if (reserva4 == null) {
								return "tarjeta";
							} else {
								LocalDateTime fechaProxima = reserva4.getFechaFin().plusDays(1);

								String fecha = this.clienteService.retornarFecha(fechaProxima);
								modelo.addAttribute("fecha", fecha);
								return "fechaInvalida";

							}

						} else {

							// Metodo para enviar la fecha disponible

							LocalDateTime fechaProxima = reserva3.getFechaFin().plusDays(1);

							String fecha = this.clienteService.retornarFecha(fechaProxima);
							modelo.addAttribute("fecha", fecha);
							return "fechaInvalida";
						}

					} else {

						// Metodo para enviar la fecha disponible

						LocalDateTime fechaProxima = reserva2.getFechaFin().plusDays(1);

						String fecha = this.clienteService.retornarFecha(fechaProxima);
						modelo.addAttribute("fecha", fecha);
						return "fechaInvalida";
					}
				} else {
					// Metodo para enviar la fecha disponible

					LocalDateTime fechaProxima = reserva1.getFechaFin().plusDays(1);

					String fecha = this.clienteService.retornarFecha(fechaProxima);
					modelo.addAttribute("fecha", fecha);
					return "fechaInvalida";
				}

			} else {
				return "valorVacio";
			}

		} else {
			return "valorVacio";
		}

	}

	@PutMapping("reservar/vehiculo/disponible")
	public String realizarReserva(@RequestParam("tarjeta") String tarjetaCliente, Model modelo) {

		Reserva reserva = this.clienteService.calcularValores(tarjetaCliente, vehiculoCopia, clienteCopia, dias,
				fechaUno, fechaDos);

		// Comprobar si se realizo la reserva

		if (reserva != null) {
			this.reservaService.insertarReserva(reserva);
			modelo.addAttribute("reserva", reserva);
			return "reservaGenerada";
		} else {
			return "reservaFallida";
		}

	}

	// REGISTRARSE COMO CLIENTE

	@GetMapping("clientes/clienteNuevo")
	public String obtenerPaginaRegistroCliente(Cliente cliente) {

		return "clienteNuevo";
	}

	@PostMapping("insertar1")
	public String insertarClienteSeccionCliente(Cliente cliente, BindingResult result, Model modelo,
			RedirectAttributes redirectAttrs) {

		Cliente cliente1 = this.empleadoService.retornarClientePorCedula(cliente.getCedula());

		// Comprobar si el cliente se encuentra ya registrado

		if (cliente1 == null) {
			this.clienteService.registrarseComoCliente(cliente);
			redirectAttrs.addFlashAttribute("mensaje1", "Cliente insertado");
			return "redirect:menu/clientes";
		} else {
			return "valorRegistrado";
		}

	}

	// ---------EMPLEADOS----------------------

	// REGISTRARSE COMO CLIENTE

	@GetMapping("empleados/clienteNuevo")
	public String obtenerInformacionCliente(Cliente cliente) {

		return "clienteNuevoDos";
	}

	@PostMapping("insertar2")
	public String insertarClienteSeccionEmpleado(Cliente cliente, BindingResult result, Model modelo,
			RedirectAttributes redirectAttrs) {

		Cliente cliente1 = this.empleadoService.retornarClientePorCedula(cliente.getCedula());

		// Comprobar si el cliente se encuentra registrado

		if (cliente1 == null) {
			this.empleadoService.registrarCliente(cliente);
			redirectAttrs.addFlashAttribute("mensaje1", "Cliente insertado");
			return "redirect:menu/empleados";
		} else {
			return "valorRegistrado";
		}
	}

	// BUSCAR CLIENTE

	@GetMapping("buscar/cliente")
	public String ingresarCedula(Model modelo) {

		return "clienteBusca";
	}

	@GetMapping("encontrar/cliente")
	public String obtenerCliente(@RequestParam("cedula") String cedulaCliente, Model modelo) {

		Cliente clienteRetorna = this.empleadoService.retornarClientePorCedula(cedulaCliente);

		// Comprobacion se realiza en el html con un th:if

		modelo.addAttribute("clienteRetorna", clienteRetorna);

		return "clienteResultado";

	}

	// INGRESAR UN VEHICULO

	@GetMapping("empleados/vehiculoNuevo")
	public String obtenerInformacionVehiculo(Vehiculo vehiculo) {

		return "vehiculoNuevo";
	}

	@PostMapping("insertar3")
	public String insertarVehiculo(Vehiculo vehiculo, BindingResult result, Model modelo,
			RedirectAttributes redirectAttrs) {

		Vehiculo vehiculo1 = this.empleadoService.retornarVehiculoPorPlaca(vehiculo.getPlaca());

		// Comprobar si ya existe el vehiculo en el sistema

		if (vehiculo1 == null) {
			this.empleadoService.ingresarVehiculo(vehiculo);
			redirectAttrs.addFlashAttribute("mensaje1", "Vehiculo insertado");
			return "redirect:menu/empleados";
		} else {
			return "valorRegistrado";
		}
	}

	// BUSCAR UN VEHICULO
	@GetMapping("buscar/vehiculo")
	public String ingresarPlaca(Model modelo) {

		return "vehiculoBusca";
	}

	@GetMapping("encontrar/vehiculo")
	public String obtenerVehiculo(@RequestParam("placa") String placaVehiculo, Model modelo) {

		Vehiculo vehiculoRetorna = this.empleadoService.retornarVehiculoPorPlaca(placaVehiculo);

		// Comprobacion se hace en el html con iun th:if

		modelo.addAttribute("vehiculoRetorna", vehiculoRetorna);

		return "vehiculoResultado";

	}

	// RETIRAR VEHICULO RESERVADO

	@GetMapping("buscar/reservado")
	public String buscarVehiculoReservado(Model modelo) {

		return "vehiculoReserva";
	}

	@PutMapping("retirar/vehiculo")
	public String retirarVehiculo(@RequestParam("numeroReserva") Integer idReserva, Model modelo) {

		boolean variable = this.empleadoService.retirarVehiculoReservado(idReserva);

		modelo.addAttribute("variable", variable);

		// Comprobacion se hace en el html con un th:if

		return "valorActualizado";

	}

	// RETIRAR VEHICULO SIN RESERVA

	@GetMapping("buscar/pagina/unida")
	public String opcionesUnidas(Model modelo) {

		return "paginaUnida";
	}

	// OPCION DE BUSCAR VEHICHULO SIN RESERVA
	@GetMapping("buscar/vehiculo/disponible/sin/reserva")
	public String seleccionarVehiculoSinReserva(Model modelo) {

		return "vehiculoDisponibleSinReserva";
	}

	@GetMapping("encontrar/vehiculo/disponible/sin/reserva")
	public String obtenerVehiculoDisponibleSinReserva(@RequestParam("marca") String marcaVehiculo,
			@RequestParam("modelo") String modeloVehiculo, Model modelo) {

		List<VehiculoSencillo> listaVehiculos = this.clienteService.buscarVehiculoDisponbleSencillo(marcaVehiculo,
				modeloVehiculo);

		modelo.addAttribute("vehiculos", listaVehiculos);

		// Comprobacion se hace en el html con un th:if

		return "vehiculosDisponiblesSinReservaResultado";

	}

	// OPCION DE RESERVAR VEHICULO SIN RESERVA

	@GetMapping("datos/sin/reserva")
	public String recibirDatosSinReserva(Model modelo) {

		return "datosSinReserva";
	}

	@GetMapping("comprobar/datos/sin/reserva")
	public String ComprobarDatosSinReserva(@RequestParam("placa") String placaVehiculo,
			@RequestParam("cedula") String cedulaCliente,
			@RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime primeraFecha,
			@RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime segundaFecha,
			Model modelo) {

		dias = segundaFecha.getDayOfMonth() - primeraFecha.getDayOfMonth();
		fechaUno = primeraFecha;
		fechaDos = segundaFecha;

		// Comprobar si existe el sistema

		Cliente cliente1 = this.clieService.buscarClientePorCedula(cedulaCliente);
		if (cliente1 != null) {
			clienteCopia = cliente1;

			// Comprobar si existe el vehiculo

			Vehiculo vehiculo1 = this.vehiculoService.buscarVehiculoPorPlaca(placaVehiculo);
			if (vehiculo1 != null) {
				vehiculoCopia = vehiculo1;

				// Comprobar los distintos casos de solapamiento de fechas

				Reserva reserva1 = this.reservaService.buscarReservaVehiculoFechas(vehiculo1.getId(), primeraFecha);

				if (reserva1 == null) {

					Reserva reserva2 = this.reservaService.buscarReservaVehiculoFechas(vehiculo1.getId(), segundaFecha);

					if (reserva2 == null) {

						Reserva reserva3 = this.reservaService.buscarReservaVehiculosCasoEspecialFechaInicio(
								vehiculo1.getId(), primeraFecha, segundaFecha);

						if (reserva3 == null) {
							Reserva reserva4 = this.reservaService.buscarReservaVehiculosCasoEspecialFechaFin(
									vehiculo1.getId(), primeraFecha, segundaFecha);

							if (reserva4 == null) {
								return "tarjetaSinReserva";
							} else {
								LocalDateTime fechaProxima = reserva4.getFechaFin().plusDays(1);

								String fecha = this.clienteService.retornarFecha(fechaProxima);
								modelo.addAttribute("fecha", fecha);
								return "fechaInvalidaSinReserva";

							}

						} else {
							LocalDateTime fechaProxima = reserva3.getFechaFin().plusDays(1);

							String fecha = this.clienteService.retornarFecha(fechaProxima);
							modelo.addAttribute("fecha", fecha);
							return "fechaInvalidaSinReserva";
						}

					} else {

						// LLamar a metodo que imprime las fechas disponibles
						LocalDateTime fechaProxima = reserva2.getFechaFin().plusDays(1);

						String fecha = this.clienteService.retornarFecha(fechaProxima);
						modelo.addAttribute("fecha", fecha);
						return "fechaInvalidaSinReserva";
					}
				} else {
					LocalDateTime fechaProxima = reserva1.getFechaFin().plusDays(1);

					String fecha = this.clienteService.retornarFecha(fechaProxima);
					modelo.addAttribute("fecha", fecha);
					return "fechaInvalidaSinReserva";
				}

			} else {
				return "valorVacioSinReserva";
			}

		} else {
			return "valorVacioSinReserva";
		}

	}

	@PutMapping("reservar/vehiculo/disponible/sin/reserva")
	public String realizarSinReserva(@RequestParam("tarjeta") String tarjetaCliente, Model modelo) {

		Reserva reserva = this.clienteService.calcularValores(tarjetaCliente, vehiculoCopia, clienteCopia, dias,
				fechaUno, fechaDos);

		// Comprobar si se realizo la reserva

		if (reserva != null) {
			this.reservaService.insertarReserva(reserva);
			modelo.addAttribute("reserva", reserva);
			return "reservaGeneradaDos";
		} else {
			return "reservaFallidaDos";
		}

	}

	// OPCION DE RETIRAR VEHICULO SIN RESERVA

	@GetMapping("buscar/no/reservado")
	public String buscarVehiculoSinReserva(Model modelo) {

		return "vehiculoSinReserva";
	}

	@PutMapping("retirar/vehiculo/no/reservado")
	public String retirarVehiculoNoReservado(@RequestParam("numeroReserva") Integer idReserva, Model modelo) {

		boolean variable = this.empleadoService.retirarVehiculoReservado(idReserva);

		modelo.addAttribute("variable", variable);

		// Comprobacion se hace en el html con un th:if

		return "valorActualizadoSinReserva";

	}

	// -----------REPORTES------------------------

	// Reporte de Reservas

	@GetMapping("reportes/reservas")
	public String seleccionarFechasReservas(Model modelo) {

		return "fechaReportes";
	}

	@GetMapping("reservas/lista")
	public String obtenerReportesVip(
			@RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime primeraFecha,
			@RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime segundaFecha,
			Model modelo) {

		List<ReservaVip> listaReservaVip = this.reporteService.reporteDeReservas(primeraFecha, segundaFecha);

		// Comprobacion se hace en el html con un th:if

		modelo.addAttribute("reservas", listaReservaVip);
		return "listaReservas";

	}

	// Reportes de Clientes

	@GetMapping("reportes/clientes")
	public String obtenerClientesVip(Model modelo) {

		List<ClienteVip> listaClienteVip = this.reporteService.reporteClientesVip();

		// Comprobacion se hace en el html con un th:if

		modelo.addAttribute("clientes", listaClienteVip);
		return "listaClientes";

	}

	// Reportes de Vehiculos

	@GetMapping("reportes/vehiculos")
	public String seleccionarFechasVehiculos(Model modelo) {

		return "fechaVehiculos";
	}

	@GetMapping("reportes/lista/vehiculos")
	public String obtenerVehiculosVip(
			@RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate primeraFecha,
			Model modelo) {

		// Obtener el mes y el año para las comprobaciones

		Month mes = primeraFecha.getMonth();
		int anio = primeraFecha.getYear();
		List<VehiculoVip> listaVehiculoVip = this.reporteService.reporteVehiculosVip(mes, Year.of(anio));

		// Comprobacion se hace en el html con un th:if

		modelo.addAttribute("vehiculos", listaVehiculoVip);
		return "listaVehiculos";

	}

}
