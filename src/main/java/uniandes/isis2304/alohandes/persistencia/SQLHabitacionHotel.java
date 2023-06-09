package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.HabitacionHotel;

class SQLHabitacionHotel
{
	private final static String SQL = PersistenciaAlohAndes.SQL;

	private PersistenciaAlohAndes pa;

	public SQLHabitacionHotel (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}

	public long adicionarHabitacionHotel (PersistenceManager pm, String tipo, String numerohabitacion, float tamano, String direccionHotel) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaHabitacionHotel () + "(TIPO, NUMERO_HABITACION, TAMANO, DIRECCION_HOTEL ) values (?, ?, ?, ?)");
        q.setParameters(tipo, numerohabitacion, tamano, direccionHotel);
        return (long) q.executeUnique();
	}

	public long eliminarHabitacionHotelPorDireccionHotel (PersistenceManager pm, String direccionHotel)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHabitacionHotel () + " WHERE DIRECCION_HOTEL = ?");
        q.setParameters(direccionHotel);
        return (long) q.executeUnique();            
	}

	public long eliminarHabitacionHotelPorNumeroHabitacionYDireccionHotel (PersistenceManager pm, String numeroHabitacion, String direccionHotel)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHabitacionHotel () + " WHERE (NUMERO_HABITACION = ? AND DIRECCION_HOTEL = ?)");
        q.setParameters(numeroHabitacion, direccionHotel);
        return (long) q.executeUnique();            
	}

	public HabitacionHotel darHabitacionHotelPorNumeroHabitacionYDireccionHotel (PersistenceManager pm, String numeroHabitacion, String direccionHotel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHabitacionHotel () + " WHERE (NUMERO_HABITACION = ? AND DIRECCION_HOTEL = ?)");
		q.setResultClass(HabitacionHotel.class);
		q.setParameters(numeroHabitacion, direccionHotel);
		return (HabitacionHotel) q.executeUnique();
	}

	public List<Object[]> darHabitacionHotelesPorDireccionHotel (PersistenceManager pm, String direccionHabitacionHotel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHabitacionHotel () + " WHERE DIRECCION_HOTEL = ?");
		q.setParameters(direccionHabitacionHotel);
		return (List<Object[]>) q.executeList();
	}

	public List<Object[]> darHabitacionHoteles (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHabitacionHotel ());
		return (List<Object[]>) q.executeList();
	}

	/** ------------------------------------------------------------------------------------------------------ */

}
