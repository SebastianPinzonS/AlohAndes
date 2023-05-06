package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.ServicioHabitacion;


class SQLServicioHabitacion
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
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
	public SQLServicioHabitacion (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	

	public long adicionarServicioHabitacion (PersistenceManager pm, String tipo, int precio) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaServicioHabitacion () + "(tipo, precio) values (?, ?)");
        q.setParameters(tipo, precio);
        return (long) q.executeUnique();
	}

	public long eliminarServicioHabitacionPorTipo (PersistenceManager pm, String tipo)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaServicioHabitacion() + " WHERE tipo = ?");
        q.setParameters(tipo);
        return (long) q.executeUnique();            
	}

	public ServicioHabitacion darServicioHabitacionPorTipo (PersistenceManager pm, String tipo) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaServicioHabitacion () + " WHERE tipo = ?");
		q.setResultClass(ServicioHabitacion.class);
		q.setParameters(tipo);
		return (ServicioHabitacion) q.executeUnique();
	}

	public List<ServicioHabitacion> darServicioHabitacionPorPrecio (PersistenceManager pm, int precio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaServicioHabitacion () + " WHERE precio = ?");
		q.setResultClass(ServicioHabitacion.class);
		q.setParameters(precio);
		return (List<ServicioHabitacion>) q.executeList();
	}

	public List<ServicioHabitacion> darTodosServicioHabitacion (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaServicioHabitacion ());
		q.setResultClass(ServicioHabitacion.class);
		return (List<ServicioHabitacion>) q.executeList();
	}
}
