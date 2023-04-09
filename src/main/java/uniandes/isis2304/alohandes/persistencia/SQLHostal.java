package uniandes.isis2304.alohandes.persistencia;

import java.math.BigDecimal;
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

	public List<Hostal> darHostalPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHostal () + " WHERE NOMBRE = ?");
		q.setResultClass(Hostal.class);
		q.setParameters(nombre);
		return (List<Hostal>) q.executeUnique();
	}

	
	public Hostal darHostalesPorDireccion (PersistenceManager pm, String direccion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHostal () + " WHERE DIRECCION = ?");
		q.setResultClass(Hostal.class);
		q.setParameters(direccion);
		return (Hostal) q.executeList();
	}


	public List<Hostal> darHostales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHostal ());
		q.setResultClass(Hostal.class);
		return (List<Hostal>) q.executeList();
	}


}
