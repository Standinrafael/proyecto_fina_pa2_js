package ec.edu.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Cliente;

@Repository
@Transactional
public class ClienteRepositoryImpl implements IClienteRepository {

	final static Logger LOGGER = Logger.getLogger(ClienteRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	// Metodos CRUD

	@Override
	public void insertarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		this.entityManager.persist(cliente);
	}

	@Override
	public void actualizarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		this.entityManager.merge(cliente);
	}

	@Override
	public Cliente buscarClientePorId(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Cliente.class, id);
	}

	@Override
	public void eliminarClientePorId(Integer id) {
		// TODO Auto-generated method stub
		Cliente clienteEliminar = this.buscarClientePorId(id);
		this.entityManager.remove(clienteEliminar);
	}

	//Metodo para buscar un Cliente por la cedula
	@Override
	public Cliente buscarClientePorCedula(String cedula) {
		// TODO Auto-generated method stub
		try {
			TypedQuery<Cliente> myQuery = this.entityManager
					.createQuery("select c from Cliente c where c.cedula=:cedula", Cliente.class);
			myQuery.setParameter("cedula", cedula);
			return myQuery.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.info("No se encontro cliente con cedula: " + cedula);
			return null;
		}

	}

	//Obtener la lista de Reportes
	
	@Override
	public List<Cliente> reporteClientes(String reserva) {
		// TODO Auto-generated method stub

		TypedQuery<Cliente> myQuery = this.entityManager
				.createQuery(" select c from Cliente c JOIN FETCH c.reservaCliente r where r.", Cliente.class);
		myQuery.setParameter("reserva", reserva);
		return myQuery.getResultList();
	}

}
