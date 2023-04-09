package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.ServicioHabitacionHotel;

class SQLServicioHabitacionHotel
{

	private final static String SQL = PersistenciaAlohAndes.SQL;


	private PersistenciaAlohAndes pa;

	public SQLServicioHabitacionHotel (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	

	public long adicionarServicioHabitacionHotel (PersistenceManager pm, String tipoServicio, String numeroHabitacionHabitacionHotel, String direccionHotelHabitacionHotel) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaServicioHabitacionHotel () + "(TIPO_SERVICIO, NUMERO_HABITACION_HABITACION_HOTEL, DIRECCION_HOTEL_HABITACION_HOTEL) values (?, ?, ?)");
        q.setParameters(tipoServicio, numeroHabitacionHabitacionHotel, direccionHotelHabitacionHotel);
        return (long) q.executeUnique();
	}


	public long eliminarServicioHabitacionHotelPorDireccionHotelHabitacionHotel (PersistenceManager pm, String direccionHotelHabitacionHotel)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaServicioHabitacionHotel () + " WHERE DIRECCION_HOTEL_HABITACION_HOTEL = ?");
        q.setParameters(direccionHotelHabitacionHotel);
        return (long) q.executeUnique();            
	}

	public long eliminarServicioHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel (PersistenceManager pm, String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaServicioHabitacionHotel () + " WHERE (DIRECCION_HOTEL_HABITACION_HOTEL = ? AND NUMERO_HABITACION_HABITACION_HOTEL = ?)");
        q.setParameters(direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
        return (long) q.executeUnique();            
	}


	public List<ServicioHabitacionHotel> darServicioHabitacionHotelPorDireccionHotelHabitacionHotel (PersistenceManager pm, String direccionHotelHabitacionHotel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaServicioHabitacionHotel () + " WHERE DIRECCION_HOTEL_HABITACION_HOTEL = ?");
		q.setResultClass(ServicioHabitacionHotel.class);
		q.setParameters(direccionHotelHabitacionHotel);
		return (List<ServicioHabitacionHotel>) q.executeUnique();
	}

	public ServicioHabitacionHotel darServicioHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel (PersistenceManager pm, String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaServicioHabitacionHotel () + " WHERE (DIRECCION_HOTEL_HABITACION_HOTEL = ? AND NUMERO_HABITACION_HABITACION_HOTEL = ?)");
		q.setResultClass(ServicioHabitacionHotel.class);
		q.setParameters(direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
		return (ServicioHabitacionHotel) q.executeUnique();
	}

	
	public List<ServicioHabitacionHotel> darTodosServicioHabitacionHotel (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaServicioHabitacionHotel ());
		q.setResultClass(ServicioHabitacionHotel.class);
		return (List<ServicioHabitacionHotel>) q.executeList();
	}

	
}
