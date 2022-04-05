package ec.edu.uce.repository;

import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.modelo.Reserva;

public interface IReservaRepository {

	// Metodos CRUD

	public void insertarReserva(Reserva reserva);

	public void actualizarReserva(Reserva reserva);

	Reserva buscarReservaPorId(Integer id);

	public void eliminarReservaPorId(Integer id);

	// Metodos Adicionales

	Reserva buscarReservaVehiculoFechas(Integer idVehiculo, LocalDateTime fechaReserva1);

	List<Reserva> ReporteReservas();

	public Reserva buscarReservaVehiculosCasoEspecialFechaInicio(Integer idVehiculo, LocalDateTime fechaInicio,
			LocalDateTime fechaFin);

	public Reserva buscarReservaVehiculosCasoEspecialFechaFin(Integer idVehiculo, LocalDateTime fechaInicio,
			LocalDateTime fechaFin);

}
