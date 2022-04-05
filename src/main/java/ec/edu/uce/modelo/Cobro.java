package ec.edu.uce.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cobro")
public class Cobro {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cobro")
	@SequenceGenerator(name = "seq_cobro", sequenceName = "seq_cobro", allocationSize = 1)
	@Column(name = "cobr_id")
	private Integer id;

	@Column(name = "cobr_tarjeta")
	private String tarjeta;

	@Column(name = "cobr_fecha", columnDefinition = "TIMESTAMP")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime fechaCobro;

	@Column(name = "cobr_valor_subtotal")
	private BigDecimal valorSubtotal;

	@Column(name = "cobr_valor_iva")
	private BigDecimal valorIva;

	@Column(name = "cobr_valor_pagar")
	private BigDecimal valorPagar;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rese_id_cobro")
	private Reserva reserva;

	// Metodos GET y SET
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public LocalDateTime getFechaCobro() {
		return fechaCobro;
	}

	public void setFechaCobro(LocalDateTime fechaCobro) {
		this.fechaCobro = fechaCobro;
	}

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}

	public BigDecimal getValorIva() {
		return valorIva;
	}

	public void setValorIva(BigDecimal valorIva) {
		this.valorIva = valorIva;
	}

	public BigDecimal getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(BigDecimal valorPagar) {
		this.valorPagar = valorPagar;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	// To String
	@Override
	public String toString() {
		return "Cobro [id=" + id + ", tarjeta=" + tarjeta + ", fechaCobro=" + fechaCobro + ", valorSubtotal="
				+ valorSubtotal + ", valorIva=" + valorIva + ", valorPagar=" + valorPagar + "]";
	}

}
