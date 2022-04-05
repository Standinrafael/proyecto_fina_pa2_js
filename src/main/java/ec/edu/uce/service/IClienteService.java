package ec.edu.uce.service;

import java.util.List;

import ec.edu.uce.modelo.Cliente;

public interface IClienteService {

	// Metodos CRUD

	public void insertarCliente(Cliente cliente);

	public void actualizarCliente(Cliente cliente);

	Cliente buscarClientePorId(Integer id);

	public void eliminarClientePorId(Integer id);

	// Metodos Adicionales

	Cliente buscarClientePorCedula(String cedula);

	List<Cliente> reporteClientes(String reserva);
}
