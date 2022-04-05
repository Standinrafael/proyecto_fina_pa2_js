package ec.edu.uce.repository;

import java.util.List;

import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.modelo.VehiculoSencillo;

public interface IVehiculoRepository {

	// Metodos CRUD

	public void insertarVehiculo(Vehiculo vehiculo);

	public void actualizarVehiculo(Vehiculo vehiculo);

	Vehiculo buscarVehiculoPorId(Integer id);

	public void eliminarVehiculoPorId(Integer id);

	// Metodos Adicionales

	Vehiculo buscarVehiculoPorPlaca(String placa);

	List<Vehiculo> buscarVehiculoPorMarcaModelo(String marca, String modelo);

	List<VehiculoSencillo> buscarVehiculoSencillo(String marca, String modelo);
}
