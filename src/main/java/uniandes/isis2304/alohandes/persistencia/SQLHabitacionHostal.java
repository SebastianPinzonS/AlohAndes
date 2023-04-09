package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.HabitacionHostal;


class SQLHabitacionHostal
{

	private final static String SQL = PersistenciaAlohAndes.SQL;

	private PersistenciaAlohAndes pa;

	public SQLHabitacionHostal (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	

	public long adicionarHabitacionHostal (PersistenceManager pm, String direccionHostal, String numeroHabitacion, int tamano) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaHabitacionHostal () + "(DIRECCION_HOSTAL, NUMERO_HABITACION, TAMANO) values (?, ?, ?)");
        q.setParameters(direccionHostal, numeroHabitacion, tamano);
        return (long) q.executeUnique();
	}

	public long eliminarHabitacionHostalPorDireccionHostal (PersistenceManager pm, String direccionHostal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHabitacionHostal () + " WHERE DIRECCION_HOSTAL = ?");
        q.setParameters(direccionHostal);
        return (long) q.executeUnique();            
	}


	public long eliminarHostalPorDireccionHostalYNumeroHabitacion (PersistenceManager pm, String direccionHostal, String numeroHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHabitacionHostal () + " WHERE (DIRECCION_HOSTAL = ? AND NUMERO_HABITACION = ?)");
        q.setParameters(direccionHostal, numeroHabitacion);
        return (long) q.executeUnique();            
	}


	public HabitacionHostal darHabitacionHostalPorDireccionHostalYNumeroHabitacion (PersistenceManager pm, String direccionHostal, String numeroHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHabitacionHostal () + " WHERE (DIRECCION_HOSTAL = ? AND NUMERO_HABITACION = ?)");
		q.setResultClass(HabitacionHostal.class);
		q.setParameters(direccionHostal, numeroHabitacion);
		return (HabitacionHostal) q.executeUnique();
	}


	public List<HabitacionHostal> darHabitacionesHostalPorDireccionHostal (PersistenceManager pm, String direccionHostal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHabitacionHostal () + " WHERE DIRECCION_HOSTAL = ?");
		q.setResultClass(HabitacionHostal.class);
		q.setParameters(direccionHostal);
		return (List<HabitacionHostal>) q.executeList();
	}

	
	public List<HabitacionHostal> darTodasHabitacionHostal (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHabitacionHostal ());
		q.setResultClass(HabitacionHostal.class);
		return (List<HabitacionHostal>) q.executeList();
	}

}
