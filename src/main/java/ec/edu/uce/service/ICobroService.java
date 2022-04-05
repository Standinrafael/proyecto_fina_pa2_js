package ec.edu.uce.service;

import ec.edu.uce.modelo.Cobro;
import ec.edu.uce.modelo.Reserva;

public interface ICobroService {

	// Metodos CRUD

	public void insertarCobro(Cobro cobro);

	public void actualizarCobro(Cobro cobro);

	Cobro buscarCobroPorId(Integer id);

	public void eliminarCobroPorId(Integer id);

	// Metodos Adicionales

	public Cobro crearCobro(String tarjeta, Reserva reserva);

}
