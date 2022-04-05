package ec.edu.uce.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Reserva;

@Repository
@Transactional
public class ReservaRepositoryImpl implements IReservaRepository {

	final static Logger LOGGER = Logger.getLogger(ReservaRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	// Metodos CRUD

	@Override
	@Transactional
	public void insertarReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		this.entityManager.persist(reserva);
	}

	@Override
	@Transactional
	public void actualizarReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		this.entityManager.merge(reserva);
	}

	@Override
	public Reserva buscarReservaPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Reserva.class, id);
	}

	@Override
	public void eliminarReservaPorId(Integer id) {
		// TODO Auto-generated method stub
		Reserva reservaEliminar = this.buscarReservaPorId(id);
		this.entityManager.remove(reservaEliminar);
	}

	// Metodo para comprobar el primer casi de solapamiento con fechas registradas

	@Override
	public Reserva buscarReservaVehiculoFechas(Integer idVehiculo, LocalDateTime fechaReserva) {
		// TODO Auto-generated method stub

		try {
			TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
					"select r from Reserva r JOIN FETCH r.vehiculo v where v.id=:id and :fechaReserva between r.fechaInicio AND r.fechaFin ",
					Reserva.class);
			myQuery.setParameter("id", idVehiculo);

			myQuery.setParameter("fechaReserva", fechaReserva);

			return myQuery.getSingleResult();

		} catch (NoResultException e) {

			LOGGER.info("No existe resultado para la busqueda");
			return null;
		}

	}

	// Metodos para comprobar el caso extra de solapamiento (fechas que abarque toda
	// el rango de una reserva ya hecha)

	@Override
	public Reserva buscarReservaVehiculosCasoEspecialFechaInicio(Integer idVehiculo, LocalDateTime fechaInicio,
			LocalDateTime fechaFin) {
		try {
			TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
					" select r from Reserva r JOIN FETCH r.vehiculo v where v.id=:id and r.fechaInicio between :fechaInicio AND :fechaFin",
					Reserva.class);
			myQuery.setParameter("id", idVehiculo);

			myQuery.setParameter("fechaInicio", fechaInicio);
			myQuery.setParameter("fechaFin", fechaFin);

			return myQuery.getSingleResult();

		} catch (NoResultException e) {
			LOGGER.info("No existe resultado para la busqueda");
			return null;
		}
	}

	@Override
	public Reserva buscarReservaVehiculosCasoEspecialFechaFin(Integer idVehiculo, LocalDateTime fechaInicio,
			LocalDateTime fechaFin) {
		try {
			TypedQuery<Reserva> myQuery = this.entityManager.createQuery(
					" select r from Reserva r JOIN FETCH r.vehiculo v where v.id=:id and r.fechaFin between :fechaInicio AND :fechaFin",
					Reserva.class);
			myQuery.setParameter("id", idVehiculo);
			myQuery.setParameter("fechaInicio", fechaInicio);
			myQuery.setParameter("fechaFin", fechaFin);

			return myQuery.getSingleResult();

		} catch (NoResultException e) {
			LOGGER.info("No existe resultado para la busqueda");
			return null;
		}
	}

	// Metodo para obtener todo el reporte de Reservas
	@Override
	public List<Reserva> ReporteReservas() {
		// TODO Auto-generated method stub
		TypedQuery<Reserva> myQuery = this.entityManager.createQuery("select r from Reserva r", Reserva.class);
		return myQuery.getResultList();
	}

}
