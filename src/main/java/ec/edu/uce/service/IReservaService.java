package ec.edu.uce.service;

import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.modelo.Reserva;

public interface IReservaService {

	// Metodos CRUD

	public void insertarReserva(Reserva reserva);

	public void actualizarReserva(Reserva reserva);

	Reserva buscarReservaPorId(Integer id);

	public void eliminarReservaPorId(Integer id);

	// Metodos Adicionales

	Reserva buscarReservaVehiculoFechas(Integer idVehiculo, LocalDateTime Reserva);

	List<Reserva> ReporteReservas();

	public Reserva buscarReservaVehiculosCasoEspecialFechaInicio(Integer idVehiculo, LocalDateTime fechaInicio,
			LocalDateTime fechaFin);

	public Reserva buscarReservaVehiculosCasoEspecialFechaFin(Integer idVehiculo, LocalDateTime fechaInicio,
			LocalDateTime fechaFin);

}
