package ec.ups.edu.sistemafinanciero.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ejb.NoSuchEntityException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

<<<<<<< HEAD
import ec.ups.edu.sistemafinanciero.modelo.Interes;
=======
import org.antlr.v4.parse.ANTLRParser.throwsSpec_return;

import ec.ups.edu.sistemafinanciero.exceptions.GeneralException;
>>>>>>> 9759a4cd3e8fb5f66e177c848c2e968f30f87f80
import ec.ups.edu.sistemafinanciero.modelo.Usuario;

@Stateless
public class UsuarioDAO {

	@Inject
	private Connection connection;

	@Inject
	private EntityManager entityManager;

	public void guardarUsuario(Usuario usuario) throws SQLException {
		entityManager.persist(usuario);
	}
<<<<<<< HEAD

	public Usuario readUsuario(String usuario) {
		Usuario user = new Usuario();
		try {
			String sql = "SELECT u FROM Usuario u" + " WHERE usuario_nombre_cuenta=:usuario";
			user = (Usuario) entityManager.createQuery(sql, Usuario.class).setParameter("usuario", usuario)
					.getSingleResult();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error en la consulta del usuario");
		} finally {
			return user;
		}
	}
=======
	
	public Usuario obtenerUsuarioCorreoAdmin(String nombre) throws GeneralException {
		try {
			String sql= "SELECT * FROM usuario usu WHERE usu.usuario_nombre_cuenta=:nombre AND usu.usuario_admin=:esCliente";
			
			return entityManager
						.createQuery(sql, Usuario.class)
						.setParameter("nombre", nombre)
						.setParameter("esCliente", false)
						.getSingleResult();	
		} catch (NoSuchEntityException ex) {
			throw new GeneralException(101, "Usuario No Encontrado");
		} catch (Exception e) {
			throw new GeneralException("ERROR DAO: "+e.getMessage());
		}
	}
	
	
>>>>>>> 9759a4cd3e8fb5f66e177c848c2e968f30f87f80
}
