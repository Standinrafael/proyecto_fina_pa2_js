package ec.edu.uce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.repository.IClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteRepository clienteRepo;

	// Metodos CRUD

	@Override
	public void insertarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		this.clienteRepo.insertarCliente(cliente);
	}

	@Override
	public void actualizarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		this.clienteRepo.actualizarCliente(cliente);
	}

	@Override
	public Cliente buscarClientePorId(Integer id) {
		// TODO Auto-generated method stub
		return this.clienteRepo.buscarClientePorId(id);
	}

	@Override
	public void eliminarClientePorId(Integer id) {
		// TODO Auto-generated method stub
		this.clienteRepo.eliminarClientePorId(id);
	}

	// Metodos Adicionales

	@Override
	public Cliente buscarClientePorCedula(String cedula) {
		// TODO Auto-generated method stub
		return this.clienteRepo.buscarClientePorCedula(cedula);
	}

	@Override
	public List<Cliente> reporteClientes(String reserva) {
		// TODO Auto-generated method stub
		return this.clienteRepo.reporteClientes(reserva);
	}

}
