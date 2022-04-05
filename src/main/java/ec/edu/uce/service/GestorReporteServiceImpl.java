package ec.edu.uce.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.uce.modelo.ClienteVip;
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.ReservaVip;
import ec.edu.uce.modelo.VehiculoVip;

@Service
public class GestorReporteServiceImpl implements IGestorReporteService {

	final static Logger LOGGER = Logger.getLogger(GestorReporteServiceImpl.class);

	@Autowired
	private IReservaService reservaService;

	// Medodo que me permite mostrar las reservas con ayuda de una clase vip
	// Se uso parallel Stream que representa los hilos.
	@Override
	public List<ReservaVip> reporteDeReservas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		// TODO Auto-generated method stub

		List<Reserva> listaTodos = this.reservaService.ReporteReservas();
		// List<ReservaSencilla> listaSencilla= new ArrayList<>();

		Stream<ReservaVip> listaFiltrada = listaTodos.parallelStream()
				.filter(reserva -> (reserva.getCobro().getFechaCobro().isAfter(fechaInicio)
						|| reserva.getCobro().getFechaCobro().isEqual(fechaInicio))
						&& (reserva.getCobro().getFechaCobro().isBefore(fechaFin)
								|| reserva.getCobro().getFechaCobro().isEqual(fechaFin)))
				.map(reserva -> {
					ReservaVip reservaSencilla = new ReservaVip();
					reservaSencilla.setId(reserva.getId());
					reservaSencilla.setFechaInicio(reserva.getFechaInicio());
					reservaSencilla.setFechaFin(reserva.getFechaFin());
					reservaSencilla.setEstado(reserva.getEstado());
					reservaSencilla.setPlaca(reserva.getVehiculo().getPlaca());
					reservaSencilla.setModelo(reserva.getVehiculo().getModelo());
					reservaSencilla.setMarca(reserva.getVehiculo().getMarca());
					reservaSencilla.setCedula(reserva.getCliente().getCedula());
					reservaSencilla.setNombre(reserva.getCliente().getNombre());
					reservaSencilla.setApellido(reserva.getCliente().getApellido());

					// listaSencilla.add(reservaSencilla);
					return reservaSencilla;
				});

		// listaFiltrada.forEach( lista->System.out.println(lista));

		List<ReservaVip> listaConvertida = listaFiltrada.collect(Collectors.toList());

		for (ReservaVip reserva : listaConvertida) {
			LOGGER.info(reserva);
		}

		return listaConvertida;
	}

//Meotod que me permite tener el registro de los clientes
	// Se uso parallelStream par auso de hilos
	@Override
	public List<ClienteVip> reporteClientesVip() {
		// TODO Auto-generated method stub

		// Obtener la lista de todas las reservas
		List<Reserva> listaTodos = this.reservaService.ReporteReservas();

		// Insetar datos en cliente vip
		Stream<ClienteVip> listaFiltrada = listaTodos.parallelStream().map(cliente -> {
			ClienteVip cliente1 = new ClienteVip();
			cliente1.setId(cliente.getCliente().getId());
			cliente1.setCedula(cliente.getCliente().getCedula());
			cliente1.setNombre(cliente.getCliente().getNombre());
			cliente1.setApellido(cliente.getCliente().getApellido());
			cliente1.setValorIva(cliente.getCobro().getValorIva());
			cliente1.setValorTotal(cliente.getCobro().getValorPagar());

			return cliente1;

		});

		// Transformar el stream en lista
		List<ClienteVip> listaConvertida = listaFiltrada.collect(Collectors.toList());

		// Agrupamiento y ordenamiento de los objetos Cliente Vip
		List<ClienteVip> listaReducida = listaConvertida.parallelStream()
				.collect(Collectors.groupingBy(ClienteVip::getId)).entrySet().parallelStream()
				.map(cliente -> cliente.getValue().parallelStream()
						.reduce((cliente1, cliente2) -> new ClienteVip(cliente1.getId(), cliente1.getCedula(),
								cliente1.getNombre(), cliente1.getApellido(),
								cliente1.getValorIva().add(cliente2.getValorIva()),
								cliente1.getValorTotal().add(cliente2.getValorTotal()))))
				.map(cliente -> cliente.get()).sorted(Comparator.comparing(ClienteVip::getValorTotal).reversed())
				.collect(Collectors.toList());

		for (ClienteVip cliente : listaReducida) {
			LOGGER.info(cliente);
		}
		return listaReducida;
	}

	// Metodo que permite obtener el reporte de Vehcilos Vip
	@Override
	public List<VehiculoVip> reporteVehiculosVip(Month mes, Year anio) {
		// TODO Auto-generated method stub

		// Se obtiene la lista reservas porque de ahi puedo sacar los datos del vehiculo
		// y la reserva conjuntamente

		List<Reserva> listaTodos = this.reservaService.ReporteReservas();

		//Obtengo los valores en entero para comparar
		int anioComparar = anio.getValue();
		int mesComparar = mes.getValue();

		// Comparacion de fechas y filtrado para insertar en vehiculo vip
		Stream<VehiculoVip> listaUno = listaTodos.parallelStream()
				.filter(reserva -> (reserva.getCobro().getFechaCobro().getMonthValue() == mesComparar)
						&& (reserva.getCobro().getFechaCobro().getYear() == anioComparar))
				.map(vehiculo -> {
					VehiculoVip vehiculo1 = new VehiculoVip();
					vehiculo1.setId(vehiculo.getVehiculo().getId());
					vehiculo1.setPlaca(vehiculo.getVehiculo().getPlaca());
					vehiculo1.setMarca(vehiculo.getVehiculo().getMarca());
					vehiculo1.setModelo(vehiculo.getVehiculo().getModelo());
					vehiculo1.setValorSubtotal(vehiculo.getCobro().getValorSubtotal());
					vehiculo1.setValorTotal(vehiculo.getCobro().getValorPagar());

					return vehiculo1;

				});

		// Transformar el stream en lista
		List<VehiculoVip> listaConvertida = listaUno.collect(Collectors.toList());

		// Agrupamiento y ordenamiento de los objetos Vehiculo vip

		List<VehiculoVip> listaReducida = listaConvertida.parallelStream()
				.collect(Collectors.groupingBy(VehiculoVip::getId)).entrySet().parallelStream()
				.map(vehiculo -> vehiculo.getValue().parallelStream()
						.reduce((vehiculo1, vehiculo2) -> new VehiculoVip(vehiculo1.getId(), vehiculo1.getPlaca(),
								vehiculo1.getMarca(), vehiculo1.getModelo(),
								vehiculo1.getValorSubtotal().add(vehiculo2.getValorSubtotal()),
								vehiculo1.getValorTotal().add(vehiculo2.getValorTotal()))))
				.map(vehiculo -> vehiculo.get()).sorted(Comparator.comparing(VehiculoVip::getValorTotal).reversed())
				.collect(Collectors.toList());

		for (VehiculoVip vehi : listaReducida) {

			LOGGER.info(vehi);
		}
		return listaReducida;
	}

}
