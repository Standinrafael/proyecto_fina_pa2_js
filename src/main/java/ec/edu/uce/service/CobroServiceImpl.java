package ec.edu.uce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Cobro;
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.repository.ICobroRepository;

@Service
public class CobroServiceImpl implements ICobroService {

	@Autowired
	private ICobroRepository cobroRepo;

	// Metodos CRUD

	@Override
	public void insertarCobro(Cobro cobro) {
		// TODO Auto-generated method stub
		this.cobroRepo.insertarCobro(cobro);
	}

	@Override
	public void actualizarCobro(Cobro cobro) {
		// TODO Auto-generated method stub
		this.cobroRepo.actualizarCobro(cobro);
	}

	@Override
	public Cobro buscarCobroPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.cobroRepo.buscarCobroPorId(id);
	}

	@Override
	public void eliminarCobroPorId(Integer id) {
		// TODO Auto-generated method stub
		this.cobroRepo.eliminarCobroPorId(id);
	}

	// Metodo Adicional

	@Override
	public Cobro crearCobro(String tarjeta, Reserva reserva) {
		// TODO Auto-generated method stub
		return this.cobroRepo.crearCobro(tarjeta, reserva);
	}

}
