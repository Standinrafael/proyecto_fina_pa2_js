package ec.edu.uce;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

@SpringBootTest
class ProyectoFinalPa2JsApplicationTests {

	@Autowired
	private IGestorClienteService gestorClienteService;

	@Autowired
	private IGestorReporteService gestorReporteService;

	@Autowired
	private IGestorEmpleadoService gestorEmpleadoService;

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IVehiculoService vehiculoService;

	@Autowired
	private IReservaService reservaService;

	// Comprobar la insercion de un cliente
	@Test
	void pruebaUnitariaUno() {

//		Vehiculo vehiculo3= new Vehiculo();
//		vehiculo3.setValorDia(new BigDecimal("30.00"));
//		vehiculo3.setAnioFabricacion("2018");
//		vehiculo3.setAvaluo(new BigDecimal("30000.00"));
//		vehiculo3.setCilindraje("1800 cc");
//		vehiculo3.setMarca("Tucson");
//		vehiculo3.setModelo("TGDI");
//		vehiculo3.setPais("Ecuador");
//		vehiculo3.setEstado("Disponible");
//		vehiculo3.setPlaca("ABX-1000");

		assertNotNull(this.vehiculoService.buscarVehiculoPorId(1));
	}

	// Comprobar la insercion de un vehiculo
	@Test
	void pruebaUnitariaDos() {
//		Cliente cliente1=new Cliente();
//		cliente1.setApellido("Perez");
//		cliente1.setCedula("1105166290");
//		cliente1.setFechaNacimiento(LocalDateTime.of(1997, Month.DECEMBER,25,10,15));
//		cliente1.setGenero("Masculino");
//		cliente1.setNombre("Juan");
//		cliente1.setRegistro("Empleado");
//		
//		this.clienteService.insertarCliente(cliente1);

		assertNotNull(this.clienteService.buscarClientePorId(1));

	}

	// Comprobar el funcionamiento de busqueda de cliente por cedula
	@Test
	void pruebaUnitariaTres() {

		Cliente cliente = this.clienteService.buscarClientePorCedula("1105166290");

		assertNotNull(cliente);
	}

	// Comprobar el funcionamiento de busqueda de vehiculo por placa
	@Test
	void pruebaUnitariaCuatro() {

		Vehiculo vehiculo = this.vehiculoService.buscarVehiculoPorPlaca("LLL-2012");
		assertNotNull(vehiculo);
	}

	// Comprobar funcionamiento de busqueda de vehiculo por marca y modelo
	@Test
	void pruebaUnitariaCinco() {
		List<Vehiculo> lista = this.vehiculoService.buscarVehiculoPorMarcaModelo("Chevrolet", "Aveo");

		int tamanio = lista.size();
		assertEquals(1, tamanio);
	}

	// Comprobar funcionamiento de busqueda de vehiculo por marca y modelo usando
	// TO.
	@Test
	void pruebaUnitariaSeis() {
		List<VehiculoSencillo> lista = this.gestorClienteService.buscarVehiculoDisponbleSencillo("Chevrolet", "Aveo");
		int tamanio = lista.size();
		assertEquals(1, tamanio);
	}

	// Comprobar funcionamiento de ingreso por seccion de empleado, con registro
	// empleado
	@Test
	void pruebaUnitariaSiete() {

		Cliente cliente = this.clienteService.buscarClientePorId(2);
		String registro = cliente.getRegistro();
		assertEquals("Empleado", registro);
	}

	@Test
	// Comprobar funcionamiento de ingreso por seccion de cliente, con registro
	// cliente
	void pruebaUnitariaOcho() {
		Cliente cliente = this.clienteService.buscarClientePorId(5);
		String registro = cliente.getRegistro();
		assertEquals("Cliente", registro);
	}

	// Comprobar funcionamiento de reporte de clientes
	@Test
	void pruebaUnitariaNueve() {
		List<ClienteVip> lista = this.gestorReporteService.reporteClientesVip();
		int tamanio = lista.size();
		assertNotEquals(0, tamanio);
	}

	// Comprobar funcionamiento de reporte de reservas
	@Test
	void pruebaUnitariaDiez() {
		List<ReservaVip> lista = this.gestorReporteService.reporteDeReservas(
				LocalDateTime.of(2022, Month.JANUARY, 2, 10, 10), LocalDateTime.of(2022, Month.JANUARY, 3, 10, 10));
		int tamanio = lista.size();
		assertEquals(0, tamanio);
	}

	// Comprobar el funcionamiento de reporte de Vehiculos
	@Test
	void pruebaUnitariaOnce() {

		List<VehiculoVip> lista = this.gestorReporteService.reporteVehiculosVip(Month.JANUARY, Year.of(2020));
		int tamanio = lista.size();
		assertEquals(0, tamanio);
	}

	// Comprobar el funcionamiento de la conversion a Fecha
	@Test
	void pruebaUnitariaDoce() {

		String fecha = this.gestorClienteService.retornarFecha(LocalDateTime.of(2022, Month.APRIL, 10, 11, 11));
		assertNotNull(fecha);
	}

	// Comprobar el funcionamiento de que se inserto la reserva
	@Test
	void pruebaUnitariaTrece() {

		Reserva reserva = this.reservaService.buscarReservaPorId(1);
		assertNotNull(reserva);
	}

	// Comprobar que retorne null cuando no encuentre vehiculo
	@Test
	void pruebaUnitariaCatorce() {
		Vehiculo vehiculo1 = this.vehiculoService.buscarVehiculoPorPlaca("123");
		Cliente cliente1 = this.clienteService.buscarClientePorCedula("1105166290");
		Reserva reserva = this.gestorClienteService.calcularPagos("12345", vehiculo1, cliente1, 5,
				LocalDateTime.of(2020, Month.JUNE, 1, 10, 10), LocalDateTime.of(2020, Month.JUNE, 1, 10, 10));
		assertNull(reserva);

	}

	// Comprobar que retorne null cuando no encuentre vehiculo
	@Test
	void pruebaUnitariaQuince() {
		Vehiculo vehiculo1 = this.vehiculoService.buscarVehiculoPorPlaca("JJJ-1023");
		Cliente cliente1 = this.clienteService.buscarClientePorCedula("110");
		Reserva reserva = this.gestorClienteService.calcularPagos("12345", vehiculo1, cliente1, 5,
				LocalDateTime.of(2020, Month.JUNE, 1, 10, 10), LocalDateTime.of(2020, Month.JUNE, 1, 10, 10));
		assertNull(reserva);
	}

	// Comprobar que retorne null cuando no se haya ingresado la tarjeta
	@Test
	void pruebaUnitariaDieciseis() {
		Vehiculo vehiculo1 = this.vehiculoService.buscarVehiculoPorPlaca("JJJ-1023");
		Cliente cliente1 = this.clienteService.buscarClientePorCedula("1105166290");
		Reserva reserva = this.gestorClienteService.calcularPagos(null, vehiculo1, cliente1, 5,
				LocalDateTime.of(2020, Month.JUNE, 1, 10, 10), LocalDateTime.of(2020, Month.JUNE, 1, 10, 10));
		assertNull(reserva);
	}

	// Comprobar que se inserta Vehiculo desde la seccion de Empleado
	@Test
	void pruebaUnitariaDiecisiete() {
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
//		this.gestorEmpleadoService.ingresarVehiculo(vehiculo1);

		Vehiculo vehiculo = this.gestorEmpleadoService.retornarVehiculoPorPlaca("ABC-5587");

		assertNotNull(vehiculo);

	}

	// Comprobar que se inserta Cliente desde la seccion de Empleado
	@Test
	void pruebaUnitariaDieciocho() {
//		Cliente cliente4=new Cliente();
//		cliente4.setApellido("Lopez");
//		cliente4.setCedula("22222");
//		cliente4.setFechaNacimiento(LocalDateTime.of(1976, Month.SEPTEMBER,10,10,15));
//		cliente4.setGenero("Masculino");
//		cliente4.setNombre("Pepito");
//		this.empleadoService.registrarCliente(cliente4);
//		this.gestorEmpleadoService.registrarCliente(cliente4);

		Cliente cliente = this.gestorEmpleadoService.retornarClientePorCedula("22222");
		assertNotNull(cliente);

	}

	// Comprobar que se inserta Vehiculo desde la seccion de Cliente
	@Test
	void pruebaUnitariaDiecinueve() {
//		Cliente cliente4=new Cliente();
//		cliente4.setApellido("Perez");
//		cliente4.setCedula("55555");
//		cliente4.setFechaNacimiento(LocalDateTime.of(1976, Month.SEPTEMBER,10,10,15));
//		cliente4.setGenero("Masculino");
//		cliente4.setNombre("Pepito");
//		this.empleadoService.registrarCliente(cliente4);
//		this.gestorClienteService.registrarseComoCliente(cliente4);

		Cliente cliente = this.gestorEmpleadoService.retornarClientePorCedula("22222");
		assertNotNull(cliente);
	}

	// Comprobar que funciona el metodo de calculode valores de pago
	@Test
	void pruebaUnitariaVeinte() {
		Vehiculo vehiculo = this.gestorEmpleadoService.retornarVehiculoPorPlaca("JJJ-512");
		Cliente cliente = this.gestorEmpleadoService.retornarClientePorCedula("1105166290");
		Reserva reserva = this.gestorClienteService.calcularValores("111", vehiculo, cliente, 0,
				LocalDateTime.of(2020, Month.JUNE, 1, 10, 10), LocalDateTime.of(2020, Month.JUNE, 1, 10, 10));
		assertNull(reserva);

	}

}
