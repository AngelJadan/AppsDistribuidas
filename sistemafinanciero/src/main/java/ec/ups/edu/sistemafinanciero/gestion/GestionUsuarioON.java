package ec.ups.edu.sistemafinanciero.gestion;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;

import ec.ups.edu.sistemafinanciero.dao.CajeroDAO;
import ec.ups.edu.sistemafinanciero.dao.ClienteDAO;
import ec.ups.edu.sistemafinanciero.dao.UsuarioDAO;
import ec.ups.edu.sistemafinanciero.exceptions.GeneralException;
import ec.ups.edu.sistemafinanciero.modelo.Acceso;
import ec.ups.edu.sistemafinanciero.modelo.Cajero;
import ec.ups.edu.sistemafinanciero.modelo.Cliente;
import ec.ups.edu.sistemafinanciero.modelo.Usuario;
import ec.ups.edu.sistemafinanciero.util.MailUtil;
import ec.ups.edu.sistemafinanciero.util.MessagesUtil;
import ec.ups.edu.sistemafinanciero.util.RandomUtil;

@Stateless
public class GestionUsuarioON {
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private ClienteDAO clienteDAO;
	@Inject
	private CajeroDAO cajeroDAO;
	
	
	public boolean saveUsuario(Usuario isUsuario) {
		
		try {
			System.out.println("Usuario Guardado");
			usuarioDAO.guardarUsuario(isUsuario);
		} catch (SQLException e) {
			System.out.println("Error Gestion Usuario:" + e.getMessage());
			MessagesUtil.agregarMensajeError(e.getMessage());
		}
		
		return true;
	}
	
	public boolean saveCliente(Cliente isCliente) {
		try {
			System.out.println("Cliente Guardado");
			clienteDAO.guardarCliente(isCliente);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Gestion Usuario: "+e.getMessage());
		}
		return true;
	}
	
	public String generarPassword() {
		String passwordString = RandomUtil.generarPassword();
		return passwordString;
	}
	
	public Usuario buscarUsuarioTipo(String usuario, String tipo) {
		Usuario user = new Usuario();
		try {
			user = usuarioDAO.readUserType(usuario, tipo);
		} catch (Exception e) {
			new Exception("Error al consultar el usuario. "+e.getLocalizedMessage());
		}finally {
			return user;
		}
	}
	public Cajero buscarCajero(long usuarioId) throws Exception {
		Cajero cajero = new Cajero();
		 cajero = cajeroDAO.buscar(usuarioId);
		 return cajero;
	}
	public Usuario validarUsuarioAdmin(String usuario, String password) throws GeneralException {
		Usuario usuarioAdmin = usuarioDAO.obtenerUsuario(usuario);
		System.out.println("Usuario Admin: " + usuarioAdmin.toString());
		if(usuarioAdmin.getPasswordString().equals(password)) {
			System.out.println("Usuario ON2: "+usuarioAdmin.toString());
			return usuarioAdmin;
		}else {
			throw new GeneralException(201, "Password Incorrecto");
		}
	}
	
	public Usuario buscarUsuario(String usuario) {
		Usuario user = new Usuario();
		user = usuarioDAO.readUsuario(usuario);
		return user;
	}
	
	public List<Usuario> buscarUsuariosCedula(String cedula) {
		List<Usuario> userUsuario = usuarioDAO.obtenerUsuariosPorCedula(cedula);
		System.out.println("Gestion Usuario: " + userUsuario.toString());
		return userUsuario;
	}
	
	public void enviarCorreoInicial(Usuario usuarioCliente, String password) {
		String asuntoMensaje = "Acceso a la Banca Virtual";
		StringBuilder cuerpoMensaje = new StringBuilder("Estimado(a) Cliente/Usuario: <strong>")
				.append(usuarioCliente.getNombre()).append("</strong><br>")
				.append("SISTEMA FINANCIERO notifica a Ud. la siguiente informacion <br>")
				.append("========================================================== <br>")
				.append("<strong> USUARIO: </strong> ").append(usuarioCliente.getNombreUsuarioString()).append("<br>")
				.append("<strong> CONTRASEÑA: </strong> ").append(usuarioCliente.getPasswordString()).append("<br>")
				.append("========================================================== <br>");
		CompletableFuture.runAsync(() -> {
			try {
				MailUtil.enviarCorreo(usuarioCliente.getEmail(), asuntoMensaje, cuerpoMensaje.toString());
			} catch (MessagingException ex) {
				ex.printStackTrace();
			}
		});
	}
	
	public void enviarCorreo(Usuario usuario, String dispositivo, String ubicacion, boolean correcto) {
		
		String asuntoMensaje = "Intento de Acceso a la Banca Virtual";
		
		StringBuilder cuerpoMensaje = new StringBuilder("Querido Usuario: <strong>");
		cuerpoMensaje.append(usuario.getNombre()).append("</strong><br>")
						.append("SISTEMA FINANCIERO notifica a Ud. la siguiente informacion <br>")
						.append("========================================================== <br>");
		
		if (correcto) {
			cuerpoMensaje.append("<strong> INTENTO DE ACCESO SATISFACTORIO </strong><br>");
		}else {
			cuerpoMensaje.append("<strong> INTENTO DE ACCESO FALLIDO </strong><br>");
			cuerpoMensaje.append("<strong> IP ADDRES: </strong> ").append(ubicacion).append("<br>")
					.append("<strong> DISPOSITIVO: </strong> ").append(dispositivo).append( "<br>")
					.append("========================================================== <br>");
		}
		
		CompletableFuture.runAsync(() -> {
				try {
					MailUtil.enviarCorreo(usuario.getEmail(), asuntoMensaje, cuerpoMensaje.toString());
				} catch (MessagingException e) {
					e.printStackTrace();
				}
		});
	}
	
	public List<Acceso> obtenerAccesos (long idUsuario) throws GeneralException {
		return clienteDAO.obtenerAccesoClientes(idUsuario);
	}
	
	public List<Usuario> obtenerUsuarios() throws GeneralException {
		return usuarioDAO.obtenerTodosUsuariosList();
	}
}
