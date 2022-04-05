package ec.edu.uce.service;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.Vehiculo;

@Service
public class GestorEmpleadoServiceImpl implements IGestorEmpleadoService {

	final static Logger LOGGER = Logger.getLogger(GestorEmpleadoServiceImpl.class);

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IReservaService reservaService;

	@Autowired
	private IVehiculoService vehiculoService;

	// Metodo que me devuelve un cliente por la cedula

	@Override
	public Cliente retornarClientePorCedula(String cedula) {
		// TODO Auto-generated method stub
		Cliente cliente = this.clienteService.buscarClientePorCedula(cedula);

		if (cliente != null) {
			LOGGER.info(cliente);
		}

		return cliente;
	}

	// Metodo que me devuelve un vehiculo por la cla
	@Override
	public Vehiculo retornarVehiculoPorPlaca(String placa) {
		// TODO Auto-generated method stub
		Vehiculo vehiculo = this.vehiculoService.buscarVehiculoPorPlaca(placa);

		if (vehiculo != null) {
			LOGGER.info(vehiculo);
		} else {
			LOGGER.info(vehiculo);
		}

		return vehiculo;
	}

	@Override
	// Metodo que me permite Reigstra un cliente con REGISTRO EMPLEADO

	public void registrarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		Cliente cliente1 = cliente;
		this.clienteService.insertarCliente(cliente1);

		cliente1.setRegistro("Empleado");
		this.clienteService.actualizarCliente(cliente1);
	}

	// Metodo que me permite ingresar un vehiculo
	// Transactional por que se realiza la insercion y la actualizacion
	@Override
	@Transactional
	public void ingresarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		Vehiculo vehiculo1 = vehiculo;

		this.vehiculoService.insertarVehiculo(vehiculo1);

		vehiculo.setEstado("Disponible");
		this.vehiculoService.actualizarVehiculo(vehiculo1);
	}

	// Metodo que me permite retirar un vehiculo
	// Transactiona por que me actualizara dos objetos diferentes

	@Override
	@Transactional
	public boolean retirarVehiculoReservado(Integer idReserva) {
		// TODO Auto-generated method stub

		boolean comprobar = false;
		try {
			Reserva reserva1 = this.reservaService.buscarReservaPorId(idReserva);
			LOGGER.info(reserva1);
			if (reserva1.getEstado().equals("Ejecutada")) {
				LOGGER.info("La reserva ya ha sido ejecutada");

			} else {

				Vehiculo vehiculo1 = reserva1.getVehiculo();
				if (vehiculo1.getEstado() != "No Disponible") {
					vehiculo1.setEstado("No Disponible");
					reserva1.setEstado("Ejecutada");

					this.vehiculoService.actualizarVehiculo(vehiculo1);
					this.reservaService.actualizarReserva(reserva1);
					LOGGER.info("Retiro de vehiculo realizado con exito");
					comprobar = true;
				} else {
					LOGGER.info("Vehiculo ya se encuentra en estado no disponible");
				}
			}

		} catch (NullPointerException e) {
			LOGGER.info("La reserva no existe, escoja la opcion de retirar sin reserva");
		}

		return comprobar;
	}

}
