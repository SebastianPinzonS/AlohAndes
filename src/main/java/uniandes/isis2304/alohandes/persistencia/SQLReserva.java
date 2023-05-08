package uniandes.isis2304.alohandes.persistencia;

import java.sql.Date;
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
	

	public long adicionarReserva (PersistenceManager pm, long idReserva, String idCliente, String idOferta, int precioEspecialTomado, int reservaColectiva, Date fechaInicial, int duracionDias) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO RESERVA (id_reserva,id_oferta, id_cliente, precio_especial_tomado, reserva_colectiva, fecha_realizacion_reserva,fecha_inicial,duracion_dias) VALUES (?, ?, ?, ?, ?, CURRENT_DATE, ?, ?)");
        q.setParameters(idReserva,idOferta, idCliente, precioEspecialTomado, reservaColectiva, fechaInicial, duracionDias);
        return (long) q.executeUnique();
	}

	public long eliminarReservaPorIdOferta (PersistenceManager pm, long idOferta)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaReserva() + " WHERE id_oferta = ?");
        q.setParameters(idOferta);
        return (long) q.executeUnique();            
	}

	public long eliminarReservaPorIdCliente (PersistenceManager pm, String idCliente)
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

	public List<Reserva> darReservasPorIdCliente (PersistenceManager pm, String idCliente) 
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

