package ec.edu.uce.repository;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Cobro;
import ec.edu.uce.modelo.Reserva;

@Repository
@Transactional
public class CobroRepositoryImpl implements ICobroRepository {

	@PersistenceContext
	private EntityManager entityManager;

	// Metodos CRUD

	@Override
	public void insertarCobro(Cobro cobro) {
		// TODO Auto-generated method stub
		this.entityManager.persist(cobro);
	}

	@Override
	public void actualizarCobro(Cobro cobro) {
		// TODO Auto-generated method stub
		this.entityManager.merge(cobro);
	}

	@Override
	public Cobro buscarCobroPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Cobro.class, id);
	}

	@Override
	public void eliminarCobroPorId(Integer id) {
		// TODO Auto-generated method stub
		Cobro cobroEliminar = this.buscarCobroPorId(id);
		this.entityManager.remove(cobroEliminar);
	}

	@Override
	public Cobro crearCobro(String tarjeta, Reserva reserva) {
		// TODO Auto-generated method stub
		Cobro cobro = new Cobro();
		cobro.setFechaCobro(LocalDateTime.now());
		cobro.setReserva(reserva);
		cobro.setTarjeta(tarjeta);

		return cobro;
	}

}
