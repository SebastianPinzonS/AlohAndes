package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Hostal;

class SQLHostal 
{
	private final static String SQL = PersistenciaAlohAndes.SQL;

	private PersistenciaAlohAndes pa;

	public SQLHostal (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	

	public long adicionarHostal (PersistenceManager pm, String nombre, String direccion, String horarioApertura, String horarioCierre, float calificacion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaHostal () + "(NOMBRE, DIRECCION, HORARIO_APERTURA, HORARIO_CIERRE, CALIFICACION) values (?, ?, ?, ?, ?)");
        q.setParameters(nombre, direccion, horarioApertura, horarioCierre, calificacion);
        return (long) q.executeUnique();
	}

	public long eliminarHostalPorNombre (PersistenceManager pm, String nombre)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHostal () + " WHERE NOMBRE = ?");
        q.setParameters(nombre);
        return (long) q.executeUnique();            
	}

	public long eliminarHostalPorDireccion (PersistenceManager pm, String direccion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHostal () + " WHERE DIRECCION = ?");
        q.setParameters(direccion);
        return (long) q.executeUnique();            
	}

	public List<Object[]> darHostalPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHostal () + " WHERE NOMBRE = ?");
		q.setParameters(nombre);
		return (List<Object[]>) q.executeUnique();
	}

	
	public Object[] darHostalPorDireccion (PersistenceManager pm, String direccion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHostal () + " WHERE DIRECCION = ?");
		q.setParameters(direccion);
		return (Object[]) q.executeUnique();
	}


	public List<Object[]> darHostales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHostal ());
	
		return (List<Object[]>) q.executeList();
	}


}
