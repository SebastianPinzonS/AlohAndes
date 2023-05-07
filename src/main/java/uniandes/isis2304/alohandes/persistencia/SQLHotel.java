package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Hotel;

class SQLHotel 
{

	private final static String SQL = PersistenciaAlohAndes.SQL;


	private PersistenciaAlohAndes pa;

	public SQLHotel (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	

	public long adicionarHotel (PersistenceManager pm, String nombre, float calificacion, String direccion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaHotel () + "(nombre, calificacion, direccion) values (?, ?, ?)");
        q.setParameters(nombre, calificacion, direccion);
        return (long) q.executeUnique();
	}

	public long eliminarHotelPorNombre (PersistenceManager pm, String nombre)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHotel () + " WHERE NOMBRE = ?");
        q.setParameters(nombre);
        return (long) q.executeUnique();            
	}

	public long eliminarHotelPorDireccion (PersistenceManager pm, String direccion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHotel () + " WHERE DIRECCION = ?");
        q.setParameters(direccion);
        return (long) q.executeUnique();            
	}

	public Hotel darHotelPorDireccion (PersistenceManager pm, String direccion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHotel () + " WHERE DIRECCION = ?");
		q.setResultClass(Hotel.class);
		q.setParameters(direccion);
		return (Hotel) q.executeUnique();
	}

	public List<Object[]> darHotelesPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHotel () + " WHERE NOMBRE = ?");
		q.setParameters(nombre);
		return (List<Object[]>) q.executeList();
	}

	public List<Object[]> darHoteles (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHotel ());
		return (List<Object[]>) q.executeList();
	}

	

}
