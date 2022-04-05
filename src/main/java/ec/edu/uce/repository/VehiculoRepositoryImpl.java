package ec.edu.uce.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.modelo.VehiculoSencillo;

@Repository
@Transactional
public class VehiculoRepositoryImpl implements IVehiculoRepository {

	final static Logger LOGGER = Logger.getLogger(VehiculoRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	// Metodos CRUD

	@Override
	public void insertarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.persist(vehiculo);
	}

	@Override
	@Transactional
	public void actualizarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.entityManager.merge(vehiculo);
	}

	@Override
	public Vehiculo buscarVehiculoPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Vehiculo.class, id);
	}

	@Override
	public void eliminarVehiculoPorId(Integer id) {
		// TODO Auto-generated method stub
		Vehiculo vehiculoEliminar = this.buscarVehiculoPorId(id);
		this.entityManager.remove(vehiculoEliminar);
	}

	//Metodo para buscar Vehiculo por la placa
	
	@Override
	public Vehiculo buscarVehiculoPorPlaca(String placa) {
		// TODO Auto-generated method stub
		try {
			TypedQuery<Vehiculo> myQuery = this.entityManager
					.createQuery("select v from Vehiculo v where v.placa=:placa", Vehiculo.class);
			myQuery.setParameter("placa", placa);
			return myQuery.getSingleResult();
		} catch (NoResultException e) {

			LOGGER.info("No existe resultado para la busqueda");
			return null;
		}

	}

	//Metodo para obtener lista de vehiculos segun la placa y el modelo
	@Override
	public List<Vehiculo> buscarVehiculoPorMarcaModelo(String marca, String modelo) {
		// TODO Auto-generated method stub
		TypedQuery<Vehiculo> myQuery = this.entityManager
				.createQuery(" select v from Vehiculo v where v.marca=:marca and v.modelo=:modelo", Vehiculo.class);
		myQuery.setParameter("marca", marca);
		myQuery.setParameter("modelo", modelo);
		return myQuery.getResultList();
	}

	//Metodo que me permite usa clase VehiculoSencillo (TO)
	@Override
	public List<VehiculoSencillo> buscarVehiculoSencillo(String marca, String modelo) {
		// TODO Auto-generated method stub
		TypedQuery<VehiculoSencillo> myQuery = this.entityManager.createQuery(
				" select NEW ec.edu.uce.modelo.VehiculoSencillo(v.placa,v.modelo,v.marca,v.anioFabricacion,v.estado,v.valorDia) from Vehiculo v  where v.marca=:marca and v.modelo=:modelo",
				VehiculoSencillo.class);

		myQuery.setParameter("marca", marca);
		myQuery.setParameter("modelo", modelo);
		return myQuery.getResultList();
	}

}
