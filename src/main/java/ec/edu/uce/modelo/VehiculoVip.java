package ec.edu.uce.modelo;

import java.math.BigDecimal;

public class VehiculoVip {

	private Integer id;

	private String placa;

	private String marca;

	private String modelo;

	private BigDecimal valorSubtotal;

	private BigDecimal valorTotal;

	public VehiculoVip() {

	}

	public VehiculoVip(Integer id, String placa, String marca, String modelo, BigDecimal valorSubtotal,
			BigDecimal valorTotal) {
		super();
		this.id = id;
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.valorSubtotal = valorSubtotal;
		this.valorTotal = valorTotal;
	}

	// Metodos GET-SET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
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
		return "VehiculoVip [id=" + id + ", placa=" + placa + ", marca=" + marca + ", modelo=" + modelo
				+ ", valorSubtotal=" + valorSubtotal + ", valorTotal=" + valorTotal + "]";
	}

}
