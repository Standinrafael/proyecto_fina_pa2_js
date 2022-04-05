package ec.edu.uce.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.repository.IReservaRepository;

@Service
public class ReservaServiceImpl implements IReservaService {

	@Autowired
	private IReservaRepository reservaRepo;

	// Metodos CRUD

	// Transaccion Support para que me conecte con la transaccion referida en el
	// repository
	@Override
	@Transactional(value = TxType.SUPPORTS)
	public void insertarReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		this.reservaRepo.insertarReserva(reserva);
	}

	// Transaccion Support para que me conecte con la transaccion referida en el
	// repository

	@Override
	@Transactional(value = TxType.SUPPORTS)
	public void actualizarReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		this.reservaRepo.actualizarReserva(reserva);
	}

	// Transaccion NOT_SUPPORTED por que se trata de un select
	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Reserva buscarReservaPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.reservaRepo.buscarReservaPorId(id);
	}

	@Override
	public void eliminarReservaPorId(Integer id) {
		// TODO Auto-generated method stub
		this.reservaRepo.eliminarReservaPorId(id);
	}

	// Metodos Adicionales

	@Override
	public Reserva buscarReservaVehiculoFechas(Integer idVehiculo, LocalDateTime fechaReserva) {
		// TODO Auto-generated method stub
		return this.reservaRepo.buscarReservaVehiculoFechas(idVehiculo, fechaReserva);
	}

	@Override
	public List<Reserva> ReporteReservas() {
		// TODO Auto-generated method stub
		return this.reservaRepo.ReporteReservas();
	}

	@Override
	public Reserva buscarReservaVehiculosCasoEspecialFechaInicio(Integer idVehiculo, LocalDateTime fechaInicio,
			LocalDateTime fechaFin) {
		// TODO Auto-generated method stub
		return this.reservaRepo.buscarReservaVehiculosCasoEspecialFechaInicio(idVehiculo, fechaInicio, fechaFin);
	}

	@Override
	public Reserva buscarReservaVehiculosCasoEspecialFechaFin(Integer idVehiculo, LocalDateTime fechaInicio,
			LocalDateTime fechaFin) {
		// TODO Auto-generated method stub
		return this.reservaRepo.buscarReservaVehiculosCasoEspecialFechaFin(idVehiculo, fechaInicio, fechaFin);
	}

}
