package ec.edu.uce.service;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Vehiculo;

public interface IGestorEmpleadoService {

	// Metodos Gestor Empleado

	public void registrarCliente(Cliente cliente);

	public void ingresarVehiculo(Vehiculo vehiculo);

	public Cliente retornarClientePorCedula(String cedula);

	public Vehiculo retornarVehiculoPorPlaca(String placa);

	public boolean retirarVehiculoReservado(Integer idReserva);


}
