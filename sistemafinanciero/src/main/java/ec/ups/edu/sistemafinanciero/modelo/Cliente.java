package ec.ups.edu.sistemafinanciero.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Cliente", schema = "public")
public class Cliente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "id_cliente_seq", sequenceName = "id_cliente_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_cliente_seq")
	@Column(name = "id_cliente", updatable = false, unique = true, nullable = false)
	private Long idClienteLong;
	@Column(name = "cliente_cuenta")
	private int cuenta;
	@Column(name = "cliente_tipo_cuenta")
	private int tipoCuenta;
	@Column(name = "cliente_fecha_registro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistroDate;
	
	
	@ManyToOne
	@JoinColumn(name = "cliente_usuario_fk")
	private Usuario usuario;
	

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Cliente(int cuenta, int tipoCuenta, Date fechaRegistroDate) {
		super();
		this.cuenta = cuenta;
		this.tipoCuenta = tipoCuenta;
		this.fechaRegistroDate = fechaRegistroDate;
	}

	
	public int getCuenta() {
		return cuenta;
	}
	public Long getIdClienteLong() {
		return idClienteLong;
	}

	public void setIdClienteLong(Long idClienteLong) {
		this.idClienteLong = idClienteLong;
	}

	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}
	public int getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(int tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public Date getFechaRegistroDate() {
		return fechaRegistroDate;
	}
	public void setFechaRegistroDate(Date fechaRegistroDate) {
		this.fechaRegistroDate = fechaRegistroDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}