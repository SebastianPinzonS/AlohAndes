package uniandes.isis2304.alohandes.persistencia;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Reserva;

public class SQLReserva 
{
	private final static String SQL = PersistenciaAlohAndes.SQL;
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaAlohAndes pa;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pa - El Manejador de persistencia de la aplicación
	 */
	public SQLReserva (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	

	public long adicionarReserva (PersistenceManager pm, long idOferta, String idCliente, int precioEspecialTomado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaReserva () + "(id_oferta, id_cliente, precio_especial_tomado) VALUES (?, ?, ?)");
        q.setParameters(idOferta, idCliente, precioEspecialTomado);
        return (long) q.executeUnique();
	}

	public long eliminarReservaPorIdOferta (PersistenceManager pm, long idOferta)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaReserva() + " WHERE id_oferta = ?");
        q.setParameters(idOferta);
        return (long) q.executeUnique();            
	}

	public long eliminarReservaPorIdCliente (PersistenceManager pm, long idCliente)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaReserva() + " WHERE id_cliente = ?");
        q.setParameters(idCliente);
        return (long) q.executeUnique();
	}        

	public Reserva darReservaPorIdOferta (PersistenceManager pm, long idOferta) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaReserva () + " WHERE id_oferta = ?");
		q.setResultClass(Reserva.class);
		q.setParameters(idOferta);
		return (Reserva) q.executeUnique();
	}

	public List<Reserva> darReservaPorIdCliente (PersistenceManager pm, long idCliente) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaReserva () + " WHERE id_cliente = ?");
		q.setResultClass(Reserva.class);
		q.setParameters(idCliente);
		return (List<Reserva>) q.executeList();
	}

	public List<Reserva> darReservas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaReserva ());
		q.setResultClass(Reserva.class);
		return (List<Reserva>) q.executeList();
	}

	

}

