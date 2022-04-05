package ec.edu.uce.service;

import java.time.LocalDateTime;
import java.util.List;

import ec.edu.uce.modelo.Cliente;
import ec.edu.uce.modelo.Reserva;
import ec.edu.uce.modelo.Vehiculo;
import ec.edu.uce.modelo.VehiculoSencillo;

public interface IGestorClienteService {

	// Metodos del Gestor Cliente

	public List<VehiculoSencillo> buscarVehiculoDisponbleSencillo(String marca, String modelo);

	public void registrarseComoCliente(Cliente cliente);

	public Reserva calcularPagos(String numeroTarjeta, Vehiculo vehiculo, Cliente cliente, int dias,
			LocalDateTime fechaInicio, LocalDateTime fechaFin);

	public Reserva calcularValores(String numeroTarjeta, Vehiculo vehiculo, Cliente cliente, int dias,
			LocalDateTime fechaInicio, LocalDateTime fechaFin);

	public String retornarFecha(LocalDateTime fechaConvertir);
}
