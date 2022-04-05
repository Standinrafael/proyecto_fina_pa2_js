package ec.edu.uce.modelo;

import java.math.BigDecimal;

public class ClienteVip {

	private Integer id;

	private String cedula;

	private String nombre;

	private String apellido;

	private BigDecimal valorIva;

	private BigDecimal valorTotal;

	public ClienteVip() {

	}

	public ClienteVip(Integer id, String cedula, String nombre, String apellido, BigDecimal valorIva,
			BigDecimal valorTotal) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.valorIva = valorIva;
		this.valorTotal = valorTotal;
	}

	// Metodos GET-SET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public BigDecimal getValorIva() {
		return valorIva;
	}

	public void setValorIva(BigDecimal valorIva) {
		this.valorIva = valorIva;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	// To String
	@Override
	public String toString() {
		return "ClienteVip [id=" + id + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", valorIva=" + valorIva + ", valorTotal=" + valorTotal + "]";
	}

}
