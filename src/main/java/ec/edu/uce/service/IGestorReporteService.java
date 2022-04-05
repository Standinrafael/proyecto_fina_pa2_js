package ec.edu.uce.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.List;

import ec.edu.uce.modelo.ClienteVip;
import ec.edu.uce.modelo.ReservaVip;
import ec.edu.uce.modelo.VehiculoVip;

public interface IGestorReporteService {

	// Metodos Gestor Reserva

	List<ReservaVip> reporteDeReservas(LocalDateTime fechaInicio, LocalDateTime fechaFin);

	List<ClienteVip> reporteClientesVip();

	List<VehiculoVip> reporteVehiculosVip(Month mes, Year anio);
}
