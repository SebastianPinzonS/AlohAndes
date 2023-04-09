package uniandes.isis2304.alohandes.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Operador;

class SQLOperador 
{
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
	public SQLOperador (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	

	public long adicionarOperador (PersistenceManager pm, String id, String nombre, String tipo, int validacionCamaraDeComercioEmpresa, int validacionSuperTurismoEmpresa, int miembroComunidadUniversitariaPersona) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOperador () + "(id, nombre, tipo, validacionCamaraDeComercioEmpresa, validacionSuperTurismoEmpresa, miembroComunidadUniversitariaPersona) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, tipo, validacionCamaraDeComercioEmpresa, validacionSuperTurismoEmpresa, miembroComunidadUniversitariaPersona);
        return (long) q.executeUnique();
	}

	public long eliminarOperadorPorNombre (PersistenceManager pm, String nombre)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOperador() + " WHERE nombre = ?");
        q.setParameters(nombre);
        return (long) q.executeUnique();            
	}

	public long eliminarOperadorPorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOperador () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();            
	}

	public Operador darOperadorPorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOperador () + " WHERE id = ?");
		q.setResultClass(Operador.class);
		q.setParameters(id);
		return (Operador) q.executeUnique();
	}

	public List<Operador> darOperadorPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOperador () + " WHERE nombre = ?");
		q.setResultClass(Operador.class);
		q.setParameters(nombre);
		return (List<Operador>) q.executeList();
	}

	public List<Operador> darOperadores (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOperador ());
		q.setResultClass(Operador.class);
		return (List<Operador>) q.executeList();
	}

}
