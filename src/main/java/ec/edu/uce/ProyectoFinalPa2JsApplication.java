package ec.edu.uce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.service.IClienteService;
import ec.edu.uce.service.ICobroService;
import ec.edu.uce.service.IGestorClienteService;
import ec.edu.uce.service.IGestorEmpleadoService;
import ec.edu.uce.service.IGestorReporteService;
import ec.edu.uce.service.IReservaService;
import ec.edu.uce.service.IVehiculoService;

@SpringBootApplication
public class ProyectoFinalPa2JsApplication implements CommandLineRunner {

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IReservaService reservaService;

	@Autowired
	private ICobroService cobroService;

	@Autowired
	private IGestorClienteService gestorService;

	@Autowired
	private IGestorEmpleadoService empleadoService;

	@Autowired
	private IGestorReporteService reporteService;

	private static final Logger LOG = LoggerFactory.getLogger(ProyectoFinalPa2JsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalPa2JsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

	

		// ----------------------- METODOS DE
		// REPORTES----------------------------------------

		// Reporte Vehiculos VIP
//		
//		this.reporteService.reporteVehiculosVip(Month.APRIL,Year.of(2022));

//		Reporte Clientes VIP
//		
//		this.reporteService.reporteClientesVip();

//		// Reporte de Reservas
//		List<Reserva> lista = this.reporteService.reporteDeReservas(LocalDateTime.of(2022, Month.APRIL, 2, 17, 17),
//				LocalDateTime.of(2022, Month.APRIL, 2, 17, 33));

		// -----------------METODOS DE
		// EMPLEADO----------------------------------------------

		// Retirar un Vehiculo sin reserva

		// Retirar un Vehiculo reservado

//		this.empleadoService.retirarVehiculoReservado(6);

		// Ingresar un vehiculo
//		Vehiculo vehiculo1= new Vehiculo();
//		vehiculo1.setValorDia(new BigDecimal("15.00"));
//		vehiculo1.setAnioFabricacion("2014");
//		vehiculo1.setAvaluo(new BigDecimal("15000.00"));
//		vehiculo1.setCilindraje("1400 cc");
//		vehiculo1.setMarca("Corsa");
//		vehiculo1.setModelo("S543");
//		vehiculo1.setPais("China");
//		vehiculo1.setEstado("Disponible");
//		vehiculo1.setPlaca("ABC-5587");
//		
//		this.empleadoService.ingresarVehiculo(vehiculo1);

		// Insertar cliente desde empleado
//		Cliente cliente4=new Cliente();
//		cliente4.setApellido("Lopez");
//		cliente4.setCedula("22222");
//		cliente4.setFechaNacimiento(LocalDateTime.of(1976, Month.SEPTEMBER,10,10,15));
//		cliente4.setGenero("Masculino");
//		cliente4.setNombre("Pepito");
//		this.empleadoService.registrarCliente(cliente4);

		// Retornar Cliente por Cedula
//		
//		this.empleadoService.retornarClientePorCedula("1105166290");
//		
		// Retornar Vehiculo por Placa
////		
//		this.empleadoService.retornarVehiculoPorPlaca("LLL-2012");

		// -------------METODOS EN CLIENTE--------------------------

		// Insertar cliente desde la parte de Cliente
//		Cliente cliente1=new Cliente();
//		cliente1.setApellido("Fernandez");
//		cliente1.setCedula("11111111");
//		cliente1.setFechaNacimiento(LocalDateTime.of(1986, Month.DECEMBER,25,10,15));
//		cliente1.setGenero("Femenino");
//		cliente1.setNombre("Maria");
//		
//		this.gestorService.registrarseComoCliente(cliente1);

		// Buscar por Marca y Modelo Sencillo

//		List<VehiculoSencillo> lista=this.gestorService.buscarVehiculoDisponbleSencillo("Tucson", "TGDI");
//		
//		for(VehiculoSencillo v: lista) {
//			System.out.println(v);
//		}

		// Bucar por Marca y Modelo
//
//		List<Vehiculo> lista = this.gestorService.buscarVehiculoDisponble("Chevrolet", "Aveo");
//
//		// System.out.println(lista.size());
//
//		for (Vehiculo v : lista) {
//			System.out.println(v);
//		}

		// Metodo para reservar un vehiculo
//		this.gestorService.reservarVehiculo("ABC-5587", "22222", LocalDateTime.of(2022, Month.JULY,20, 12, 30),
//				LocalDateTime.of(2022, Month.JULY, 28, 12, 30));

		// Insercion de datos por consola para hacer pruebas de funcionamiento de
		// metodos

//		Vehiculo vehiculo1= new Vehiculo();
//		vehiculo1.setValorDia(new BigDecimal("25.00"));
//		vehiculo1.setAnioFabricacion("2017");
//		vehiculo1.setAvaluo(new BigDecimal("20000.00"));
//		vehiculo1.setCilindraje("1600 cc");
//		vehiculo1.setMarca("Chevrolet");
//		vehiculo1.setModelo("Aveo");
//		vehiculo1.setPais("Ecuador");
//		vehiculo1.setEstado("Disponible");
//		vehiculo1.setPlaca("LLL-2012");
//		
//			
//		
//		Vehiculo vehiculo2= new Vehiculo();
//		vehiculo2.setValorDia(new BigDecimal("45.00"));
//		vehiculo2.setAnioFabricacion("2020");
//		vehiculo2.setAvaluo(new BigDecimal("40000.00"));
//		vehiculo2.setCilindraje("2000 cc");
//		vehiculo2.setMarca("Tucson");
//		vehiculo2.setModelo("TGDI");
//		vehiculo2.setPais("Ecuador");
//		vehiculo2.setEstado("Disponible");
//		vehiculo2.setPlaca("PPT-1412");
//		

//		
//		this.vehiculoService.insertarVehiculo(vehiculo2);
//		this.vehiculoService.insertarVehiculo(vehiculo1);
//		this.vehiculoService.insertarVehiculo(vehiculo3);
//		
//		Cliente cliente1=new Cliente();
//		cliente1.setApellido("Perez");
//		cliente1.setCedula("1105166290");
//		cliente1.setFechaNacimiento(LocalDateTime.of(1997, Month.DECEMBER,25,10,15));
//		cliente1.setGenero("Masculino");
//		cliente1.setNombre("Juan");
//		cliente1.setRegistro("Empleado");
//		
//		this.clienteService.insertarCliente(cliente1);
	}

}
