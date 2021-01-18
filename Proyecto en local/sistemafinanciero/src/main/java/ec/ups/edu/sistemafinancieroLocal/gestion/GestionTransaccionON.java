package ec.ups.edu.sistemafinancieroLocal.gestion;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import ec.ups.edu.sistemafinancieroLocal.dao.ClienteDAO;
import ec.ups.edu.sistemafinancieroLocal.dao.IntebancarioDAO;
import ec.ups.edu.sistemafinancieroLocal.dao.TransaccionDAO;
import ec.ups.edu.sistemafinancieroLocal.dao.TransferenciaDAO;
import ec.ups.edu.sistemafinancieroLocal.modelo.Cliente;
import ec.ups.edu.sistemafinancieroLocal.modelo.Interbancario;
import ec.ups.edu.sistemafinancieroLocal.modelo.Transaccion;
import ec.ups.edu.sistemafinancieroLocal.modelo.Transferencia;

@Named
public class GestionTransaccionON {
	@Inject
	private TransaccionDAO transaccionDAO;
	@Inject
	private TransferenciaDAO transferenciaDAO;
	@Inject
	private ClienteDAO clienteDAO;
	@Inject
	private IntebancarioDAO interDAO;
	
	/**
	 *  
	 * @param Id del cliente a buscar.
	 * @return Listado de transacciones de operancion tipo DEPOSITO.
	 */
	public List<Transaccion> listarDeposito(long clienteId){
		List<Transaccion> list = new ArrayList<Transaccion>();
		list = transaccionDAO.list("DEPOSITO", clienteId);
		return list;
	}
	/**
	 * @param Id del cliente a buscar.
	 * @return Listado de transacciones de operacion tipo RETIRO.
	 */
	public List<Transaccion> listarRetiro(long clienteId){
		List<Transaccion> list = new ArrayList<Transaccion>();
		list = transaccionDAO.list("RETIRO",clienteId);
		return list;
	}
	/**
	 * 
	 * @param idCliente Id del cliente
	 * @return List de las transacciones del id del cliente indicado.
	 */
	public List<Transaccion> listarAllTransacciones(long idCliente){
		List<Transaccion> transacciones = new ArrayList<Transaccion>();
		try {
			transacciones = transaccionDAO.list("%", idCliente);
		} catch (Exception e) {
			new Exception("Error al consultar las transacciones. "+e.getLocalizedMessage());
		}finally {
			return transacciones;
		}
	}
	
	/**
	 * 
	 * @param clienteId Id del cliente a listar las transferencias.
	 * @return List de transferencias del cliente recibido.
	 */
	public List<Transferencia> listarTransferencia(long clienteId){
		List<Transferencia> list = new ArrayList<Transferencia>();
		try {
			list = transaccionDAO.listTransferencia(clienteId);
		} catch (Exception e) {
			new Exception("Se ha generado un error al recupera las transacciones: Error: \n"+e.getLocalizedMessage());
		}finally {
			return list;
		}
	}
	public boolean generarEstadoCuenta() {
		return true;
	}
	/**
	 * 
	 * @param transaccion Tipo de deposito, retiros.
	 * @param cliente Datos del cliente desde donde se realiza la transaccion
	 * o datos del beneficiario.
	 * @return estado true, si se realiza correctamente toda la transaccion.
	 */
	public boolean transaccion(Transaccion transaccion) {
		boolean estado = false;
		try {
			if (transaccion.getOperacion()!="TRANSFERENCIA") {
				estado = transaccionDAO.insert(transaccion);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}
	/**
	 * 
	 * @param transferencia Datos para registrar la transferencia.
	 * @return true, si se guarda correctamente.
	 */
	@SuppressWarnings("finally")
	public boolean transferir(Transferencia transferencia) {
		boolean estado = false;
		try {
			estado = transferenciaDAO.insert(transferencia);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return estado;
		}
	}
	/**
	 * 
	 * @param intebancario Datos de interbancarios, para cuentas de otros bancos e internos.
	 * @return true, si se guarda correctamente. False o throw, si no se logra guardar.
	 */
	public boolean registrarInterbancario(Interbancario intebancario) {
		boolean res=false;
		try {
			res = interDAO.insert(intebancario);
		} catch (Exception e) {
			new Exception("Se ha generado un error. "+e.getLocalizedMessage());
		}finally {
			return res;
		}
	}
	/**
	 * 
	 * @return Listado de todas la cuentas internas o externas registradas.
	 */
	@SuppressWarnings("finally")
	public List<Interbancario> listarInterbancario(){
		List<Interbancario> interbancarios = new ArrayList<Interbancario>();
		try {
			interbancarios = interDAO.list();
		} catch (Exception e) {
			new Exception("Se ha generado un error al listar las cuentas. "+e.getLocalizedMessage());
		}finally {
			return interbancarios;
		}
	}
	/**
	 * 
	 * @param clienteId identificador del cliente a buscar el saldo disponible.
	 * @return Ultimo saldo disponible en la cuenta.
	 */
	@SuppressWarnings("finally")
	public double saldoActual(long clienteId) {
		Transaccion transaccion = new Transaccion();		
		try {
			transaccion = transaccionDAO.searchSalFinal(clienteId);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			return transaccion.getSladoActual();
		}
	}
}
