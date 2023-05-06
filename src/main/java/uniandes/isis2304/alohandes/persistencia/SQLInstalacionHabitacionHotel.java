package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.InstalacionHabitacionHotel;

class SQLInstalacionHabitacionHotel 
{
	
	private final static String SQL = PersistenciaAlohAndes.SQL;

	private PersistenciaAlohAndes pa;

	public SQLInstalacionHabitacionHotel (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	

	public long adicionarInstalacionHabitacionHotel (PersistenceManager pm, String tipoInstalacion, String numeroHabitacionHabitacionHotel, String direccionHotelHabitacionHotel) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaInstalacionHabitacionHotel () + "(TIPO_INSTALACION, NUMERO_HABITACION_HABITACION_HOTEL, DIRECCION_HOTEL_HOTEL_HABITACION) values (?, ?, ?)");
        q.setParameters(tipoInstalacion, numeroHabitacionHabitacionHotel, direccionHotelHabitacionHotel);
        return (long) q.executeUnique();
	}

	public long eliminarInstalacionHabitacionHotelPorTipoInstalacionYDireccionHotelHabitacionHotel (PersistenceManager pm, String tipoInstlacion, String direccionHotelHabitacionHotel )
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaInstalacionHabitacionHotel () + " WHERE (TIPO_INSTALACION = ? AND DIRECCION_HOTEL_HABITACION_HOTEL = ?)");
        q.setParameters(tipoInstlacion, direccionHotelHabitacionHotel);
        return (long) q.executeUnique();            
	}

	public long eliminarInstalacionHabitacionHotelPorDireccionHotelHabitacionHotel (PersistenceManager pm, String direccionHotelHabitacionHotel)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaInstalacionHabitacionHotel () + " WHERE DIRECCION_HOTEL = ?");
        q.setParameters(direccionHotelHabitacionHotel);
        return (long) q.executeUnique();            
	}

	public long eliminarInstalacionHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel (PersistenceManager pm, String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaInstalacionHabitacionHotel () + " WHERE (DIRECCION_HOTEL_HABITACION_HOTEL = ? AND NUMERO_HABITACION_HABITACION_HOTEL = ?)");
        q.setParameters(direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
        return (long) q.executeUnique();            
	}

	public long eliminarInstalacionHabitacionHotelPorTipoInstalacionDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel (PersistenceManager pm, String tipoInstalacion, String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaInstalacionHabitacionHotel () + " WHERE (TIPO_INSTALACION = ? AND DIRECCION_HOTEL_HABITACION_HOTEL = ? AND NUMERO_HABITACION_HABITACION_HOTEL = ?)");
		q.setParameters(tipoInstalacion, direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
		return (long) q.executeUnique();
	}


	public List<InstalacionHabitacionHotel> darInstalacionHabitacionHotelPorDireccionHotelHabitacionHotel (PersistenceManager pm, String direccionHotelHabitacionHotel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaInstalacionHabitacionHotel () + " WHERE DIRECCION_HOTEL_HABITACION_HOTEL = ?");
		q.setResultClass(InstalacionHabitacionHotel.class);
		q.setParameters(direccionHotelHabitacionHotel);
		return (List<InstalacionHabitacionHotel>) q.executeUnique();
	}

	public List<InstalacionHabitacionHotel> darInstalacionHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel (PersistenceManager pm, String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaInstalacionHabitacionHotel () + " WHERE (DIRECCION_HOTEL_HABITACION_HOTEL = ? AND NUMERO_HABITACION_HABITACION_HOTEL = ?)");
		q.setResultClass(InstalacionHabitacionHotel.class);
		q.setParameters(direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
		return (List<InstalacionHabitacionHotel>) q.executeUnique();
	}

	public InstalacionHabitacionHotel darInstalacionHabitacionHotelPorTipoInstalacionDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel (PersistenceManager pm, String tipoInstalacion, String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaInstalacionHabitacionHotel () + " WHERE (TIPO_INSTALACION = ? AND DIRECCION_HOTEL_HABITACION_HOTEL = ? AND NUMERO_HABITACION_HABITACION_HOTEL = ?)");
		q.setParameters(tipoInstalacion, direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
		return (InstalacionHabitacionHotel) q.executeUnique();
	}

	public List<InstalacionHabitacionHotel> darTodasInstalacionHabitacionHotel (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaInstalacionHabitacionHotel ());
		q.setResultClass(InstalacionHabitacionHotel.class);
		return (List<InstalacionHabitacionHotel>) q.executeList();
	}


}
