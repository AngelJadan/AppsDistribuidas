package ec.ups.edu.sistemafinanciero.vista;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

=======
>>>>>>> ff4d3d5c77f21bce6150bc934261c3c979c0b352
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;



@Named
@RequestScoped
public class UsuarioBean {

	@Inject
<<<<<<< HEAD
	private GestionUsuarioON usuarioON;

	private Usuario usuario;
	private Date factual;

	public UsuarioBean() {
		// TODO Auto-generated constructor stub
	}

=======
	private GestionUsuarioON gestionUsuarioON;
	
	private Usuario usuario;
	
	
>>>>>>> ff4d3d5c77f21bce6150bc934261c3c979c0b352
	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}
	
	
	public UsuarioBean() {
		super();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String doGuardar() {
<<<<<<< HEAD

		System.out.println(usuario);

		usuarioON.saveUsuario(usuario);
=======
		
		gestionUsuarioON.saveUsuario(usuario);
>>>>>>> ff4d3d5c77f21bce6150bc934261c3c979c0b352
		return null;
	}
	
	
	
	

	public GestionUsuarioON getUsuarioON() {
		return usuarioON;
	}

	public void setUsuarioON(GestionUsuarioON usuarioON) {
		this.usuarioON = usuarioON;
	}

	public Date getFactual() {
		return factual;
	}

	public void setFactual(Date factual) {
		this.factual = factual;
	}

}
