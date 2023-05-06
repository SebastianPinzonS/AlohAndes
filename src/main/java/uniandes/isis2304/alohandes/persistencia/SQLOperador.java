package uniandes.isis2304.alohandes.persistencia;

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
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOperador () + "(id, nombre, tipo, validacion_Camara_De_Comercio_Empresa, validacion_Super_Turismo_Empresa, miembro_Comunidad_Universitaria_Persona) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(id, nombre, tipo, validacionCamaraDeComercioEmpresa, validacionSuperTurismoEmpresa, miembroComunidadUniversitariaPersona);
        return (long) q.executeUnique();
	}

	public long eliminarOperadorPorNombre (PersistenceManager pm, String nombre)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOperador() + " WHERE nombre = ?");
        q.setParameters(nombre);
        return (long) q.executeUnique();            
	}

	public long eliminarOperadorPorId (PersistenceManager pm, String id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOperador () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();            
	}

	public Operador darOperadorPorId (PersistenceManager pm, String id) 
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

	public List<Object> mostrarDineroRecibidoPorCadaOperador (PersistenceManager pm)
	{
	    String sql = "SELECT dinero.id_operador, nombres.nombre, dinero.dinero_total, dinero.dinero_ano";
		sql += "FROM";
		sql += "(SELECT total.id_operador, total.dinero_total, ano.dinero_ano";
		sql += "FROM";
		sql += "(SELECT id_operador, SUM(costo_contrato) AS dinero_total";
	    sql += " FROM " + pa.darTablaOferta ();
		sql += "WHERE id_cliente IS NOT NULL";
		sql += "GROUP BY id_operador) total";
		sql += "FULL OUTER JOIN";
		sql += "(SELECT id_operador, SUM(costo_contrato) AS dinero_ano";
		sql += "FROM " + pa.darTablaOferta ();
		sql += "WHERE (id_cliente IS NOT NULL)";
		sql += "AND (fecha_inicial >= to_date('01-01-2022','DD-MM-YYYY'))";
		sql += "GROUP BY id_operador) ano";
		sql += "ON total.id_operador = ano.id_operador) dinero";
		sql += "INNER JOIN";
		sql += "(SELECT id, nombre";
		sql += "FROM " + pa.darTablaOperador() + ") nombres";
		sql += "ON dinero.id_operador = nombres.id";
		sql += "ORDER BY dinero.id_operador";
		
	    Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}

}
