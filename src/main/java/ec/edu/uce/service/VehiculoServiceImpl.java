package ec.edu.uce.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.modelo.VehiculoSencillo;
import ec.edu.uce.repository.IVehiculoRepository;

@Service
public class VehiculoServiceImpl implements IVehiculoService {

	@Autowired
	private IVehiculoRepository vehiculoRepo;

	// Metodos CRUD

	@Override
	public void insertarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.vehiculoRepo.insertarVehiculo(vehiculo);
	}

	// Transaccion Support para que me conecte con la transaccion referida en el
	// repository

	@Override
	@Transactional(value = TxType.SUPPORTS)
	public void actualizarVehiculo(Vehiculo vehiculo) {
		// TODO Auto-generated method stub
		this.vehiculoRepo.actualizarVehiculo(vehiculo);
	}

	@Override
	public Vehiculo buscarVehiculoPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.vehiculoRepo.buscarVehiculoPorId(id);
	}

	@Override
	public void eliminarVehiculoPorId(Integer id) {
		// TODO Auto-generated method stub
		this.vehiculoRepo.eliminarVehiculoPorId(id);
	}

	// Metodos Adicionales

	@Override
	public Vehiculo buscarVehiculoPorPlaca(String placa) {
		// TODO Auto-generated method stub
		return this.vehiculoRepo.buscarVehiculoPorPlaca(placa);
	}

	@Override
	public List<Vehiculo> buscarVehiculoPorMarcaModelo(String marca, String modelo) {
		// TODO Auto-generated method stub
		return this.vehiculoRepo.buscarVehiculoPorMarcaModelo(marca, modelo);
	}

	@Override
	public List<VehiculoSencillo> buscarVehiculoSencillo(String marca, String modelo) {
		// TODO Auto-generated method stub
		return this.vehiculoRepo.buscarVehiculoSencillo(marca, modelo);
	}

}
