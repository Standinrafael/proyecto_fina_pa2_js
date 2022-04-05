package ec.edu.uce.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Cobro;
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.modelo.VehiculoSencillo;

@Service
public class GestorClienteServiceImpl implements IGestorClienteService {

	final static Logger LOGGER = Logger.getLogger(GestorClienteServiceImpl.class);

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IReservaService reservaService;

	@Autowired
	private IVehiculoService vehiculoService;

	// Transactional por que es parte de la funcion de realizar un cobro si se
	// coloca la reserva
// 
	@Override
	@Transactional
	public Reserva calcularValores(String numeroTarjeta, Vehiculo vehiculo, Cliente cliente, int dias,
			LocalDateTime fechaInicio, LocalDateTime fechaFin) {

		Reserva reserva = new Reserva();

		String tarjetaCopia = numeroTarjeta;

		// Si no se ingresa la tarjeta, no se realiza el cobro y no eberia generarse la
		// reserva
		if (tarjetaCopia == "") {

			LOGGER.info("No ingreso el numero de la tarjeta, se cancela la operacion");

			return null;

		} else {

			// Se llama al metodo de los cobros y si se realizo el cobro entonces se
			// generara la reserva
			reserva = this.calcularPagos(numeroTarjeta, vehiculo, cliente, dias, fechaInicio, fechaFin);

			this.reservaService.insertarReserva(reserva);
		}

		return reserva;
	}

	// Transactional por que es parte de la funcion de realizar un cobro si se
	// coloca la reserva
	// En este metodo se realiza los calculos de los valores a pagar y se colocaran
	// en la tabla de cobros

	@Override
	@Transactional
	public Reserva calcularPagos(String numeroTarjeta, Vehiculo vehiculo, Cliente cliente, int dias,
			LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		Reserva reserva = new Reserva();

		BigDecimal valorSubtotal = vehiculo.getValorDia().multiply(BigDecimal.valueOf(dias));
		BigDecimal valorIva = (valorSubtotal.multiply(BigDecimal.valueOf(12))).divide(BigDecimal.valueOf(100));
		BigDecimal valorTotal = valorSubtotal.add(valorIva);
		reserva.setCliente(cliente);
		reserva.setEstado("Generada");
		reserva.setFechaFin(fechaFin);
		reserva.setFechaInicio(fechaInicio);

		reserva.setVehiculo(vehiculo);

		Cobro cobro = new Cobro();
		cobro.setFechaCobro(LocalDateTime.now());
		cobro.setTarjeta(numeroTarjeta);
		cobro.setReserva(reserva);
		cobro.setValorIva(valorIva);
		cobro.setValorSubtotal(valorSubtotal);
		cobro.setValorPagar(valorTotal);

		reserva.setCobro(cobro);

		return reserva;
	}

	// Metodo que me transforma la fecha requerida en palabras, para que el usuario
	// pueda entender

	@Override
	public String retornarFecha(LocalDateTime fechaConvertir) {

		String fechaProxima = "";

		String meses[] = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
				"Octubre", "Noviembre", "Diciembre" };

		int mes1 = fechaConvertir.getMonthValue();

		int dia1 = fechaConvertir.getDayOfMonth();

		String dia = String.valueOf(dia1);

		String mes = meses[mes1 - 1];

		String parteUno = "El vehiculo estará disponible desde: ".concat(dia);

		String parteDos = " de ".concat(mes);

		fechaProxima = parteUno.concat(parteDos);

		return fechaProxima;
	}

	// Metodo que me permite Reigstra un cliente con REGISTRO CLIENTE
	@Override
	public void registrarseComoCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		Cliente cliente1 = cliente;
		this.clienteService.insertarCliente(cliente1);

		cliente1.setRegistro("Cliente");
		this.clienteService.actualizarCliente(cliente1);
	}

	// Metodo que me devuelve la lista de vehiculos segun la marca y el modelo
	// pero solo con los valores requeridos

	@Override
	public List<VehiculoSencillo> buscarVehiculoDisponbleSencillo(String marca, String modelo) {
		// TODO Auto-generated method stub
		List<VehiculoSencillo> listaVehiculos = this.vehiculoService.buscarVehiculoSencillo(marca, modelo);
		return listaVehiculos;
	}

}
