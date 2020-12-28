package ec.ups.edu.sistemafinanciero.vista;


import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import ec.ups.edu.sistemafinanciero.exceptions.GeneralException;
import ec.ups.edu.sistemafinanciero.gestion.GestionUsuarioON;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;
import ec.ups.edu.sistemafinanciero.utils.MessagesUtil;
import ec.ups.edu.sistemafinanciero.utils.SessionUtil;

@Named
@RequestScoped
public class LoginBean {
	
	private static final long serialVersionUID = 1L;

	@Inject
	HttpServletRequest request;

	@Inject
	private GestionUsuarioON gestionUsuarioON;
	
	private String usernameString;
	private String passwordString;
	
	public String loginUser() {
		
		Usuario userUsuario;
		
		try {
			userUsuario = gestionUsuarioON.validarUsuarioAdmin(usernameString, passwordString);
			System.out.println("Usuario Bean: "+userUsuario.toString());
			return "ClienteView";
		} catch (GeneralException e) {
			MessagesUtil.agregarMensajeError("El Correo y Password es incorrecto");
		}
<<<<<<< HEAD
		return "";
=======
		return null;
>>>>>>> ff4d3d5c77f21bce6150bc934261c3c979c0b352
	}
	
	public String logoutUser() {
		SessionUtil.getSession().invalidate();
		return "pretty:index";
	}
	

	public String getUsernameString() {
		return usernameString;
	}


	public void setUsernameString(String usernameString) {
		this.usernameString = usernameString;
	}


	public String getPasswordString() {
		return passwordString;
	}


	public void setPasswordString(String passwordString) {
		this.passwordString = passwordString;
	}


	
	
	

}
