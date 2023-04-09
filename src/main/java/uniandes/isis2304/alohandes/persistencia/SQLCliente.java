package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Cliente;

class SQLCliente 
{
	
	private final static String SQL = PersistenciaAlohAndes.SQL;

	private PersistenciaAlohAndes pa;

	public SQLCliente (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	

	public long adicionarCliente (PersistenceManager pm, String id, String tipoId, String nombre, String tipo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaCliente () + "(ID, TIPO_ID, NOMBRE, TIPO) values (?, ?, ?, ?)");
        q.setParameters(id, tipoId, nombre, tipo);
        return (long) q.executeUnique();
	}

	public long eliminarClientePorNombre (PersistenceManager pm, String nombre)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaCliente () + " WHERE NOMBRE = ?");
        q.setParameters(nombre);
        return (long) q.executeUnique();            
	}

	
	public long eliminarClientePorId (PersistenceManager pm, String id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaCliente () + " WHERE ID = ?");
        q.setParameters(id);
        return (long) q.executeUnique();            
	}

	public Cliente darClientePorId (PersistenceManager pm, String id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaCliente () + " WHERE ID = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(id);
		return (Cliente) q.executeUnique();
	}


	public List<Cliente> darClientesPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaCliente () + " WHERE NOMBRE = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(nombre);
		return (List<Cliente>) q.executeList();
	}

	public List<Cliente> darClientes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaCliente ());
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}

}
