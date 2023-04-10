package uniandes.isis2304.alohandes.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.ViviendaExpress;


class SQLViviendaExpress 
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
	public SQLViviendaExpress (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	

	public long adicionarViviendaExpress (PersistenceManager pm, String nombre, String direccion, int numeroHabitaciones, String menaje, float calificacion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaViviendaExpress() + "(NOMBRE, DIRECCION, NUMERO_HABITACIONES, MENAJE, CALIFICACION) values (?, ?, ?, ?, ?)");
        q.setParameters(nombre, direccion, numeroHabitaciones, menaje, calificacion);
        return (long) q.executeUnique();
	}

	public long eliminarVivendaExpressPorDireccion (PersistenceManager pm, String direccion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaViviendaExpress () + " WHERE direccion = ?");
        q.setParameters(direccion);
        return (long) q.executeUnique();            
	}

	public long eliminarVivendaExpressPorNombre (PersistenceManager pm, String nombre)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaViviendaExpress () + " WHERE nombre = ?");
        q.setParameters(nombre);
        return (long) q.executeUnique();            
	}

	public ViviendaExpress darViviendaExpressPorDireccion (PersistenceManager pm, String direccion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaViviendaExpress () + " WHERE id = ?");
		q.setResultClass(ViviendaExpress.class);
		q.setParameters(direccion);
		return (ViviendaExpress) q.executeUnique();
	}

	public List<ViviendaExpress> darViviendaExpressPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaViviendaExpress () + " WHERE nombre = ?");
		q.setResultClass(ViviendaExpress.class);
		q.setParameters(nombre);
		return (List<ViviendaExpress>) q.executeList();
	}

	public List<ViviendaExpress> darTodasViviendaExpress (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaViviendaExpress ());
		q.setResultClass(ViviendaExpress.class);
		return (List<ViviendaExpress>) q.executeList();
	}

}
