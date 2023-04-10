package uniandes.isis2304.alohandes.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.ViviendaHabitacion;


class SQLViviendaHabitacion 
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
	public SQLViviendaHabitacion (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	

	public long adiccionarViviendaHabitacion (PersistenceManager pm, String nombre, String direccion, String numeroApartamento, float calificacion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaViviendaHabitacion () + "(NOMBRE, DIRECCION, NUMERO_APARTAMENTO, CALIFICACION) values (?, ?, ?, ?)");
        q.setParameters(nombre, direccion, numeroApartamento, calificacion);
        return (long) q.executeUnique();
	}

	public long eliminarViviendaHabitacionPorDireccion (PersistenceManager pm, String direccion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaViviendaHabitacion () + " WHERE direccion = ?");
        q.setParameters(direccion);
        return (long) q.executeUnique();            
	}

	public ViviendaHabitacion darViviendaHabitacionPorDireccion (PersistenceManager pm, String direccion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaViviendaHabitacion () + " WHERE direccion = ?");
		q.setResultClass(ViviendaHabitacion.class);
		q.setParameters(direccion);
		return (ViviendaHabitacion) q.executeUnique();
	}

	public List<ViviendaHabitacion> darTodasViviendaHabitacion (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaViviendaHabitacion ());
		q.setResultClass(ViviendaHabitacion.class);
		return (List<ViviendaHabitacion>) q.executeList();
	}

	
}
