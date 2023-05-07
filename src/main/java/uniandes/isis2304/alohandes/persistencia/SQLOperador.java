package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


import uniandes.isis2304.alohandes.negocio.Operador;
import uniandes.isis2304.alohandes.negocio.ValorOperador;

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

	public Object[] darOperadorPorId (PersistenceManager pm, String id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOperador () + " WHERE id = ?");
		q.setParameters(id);
		return (Object[]) q.executeUnique();
	}

	public List<Object[]> darOperadorPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOperador () + " WHERE nombre = ?");
		q.setParameters(nombre);
		return (List<Object[]>) q.executeList();
	}

	public List<Object[]> darOperadores (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOperador ());
		return (List<Object[]>) q.executeList();
	}

	public List<Object[]> mostrarDineroRecibidoPorCadaOperadorAnoActual (PersistenceManager pm)
	{
	    String sql = "SELECT DISTINCT dineros.id_operador, nombrardos.nombre, dineros.dinero_ano_corrido, dineros.dinero_ano_actual " +
             "FROM " +
             "( " +
             "  SELECT ano_actual.id_operador, NVL(ano_corrido.dinero_ano_corrido,0) AS dinero_ano_corrido, NVL(ano_actual.dinero_ano_actual,0) AS dinero_ano_actual " +
             "  FROM " +
             "  ( " +
             "    SELECT oferta_corta.id_operador, SUM(ofertas_ano_corrido.duracion_dias*oferta_corta.costo_contrato_dia) AS dinero_ano_corrido " +
             "    FROM " +
             "    ( " +
             "      ( " +
             "        SELECT id_oferta, duracion_dias, fecha_inicial " +
             "        FROM reserva " +
             "        WHERE fecha_inicial >= (CURRENT_DATE - NUMTOYMINTERVAL(1, 'year')) " +
             "      ) ofertas_ano_corrido " +
             "      FULL OUTER JOIN " +
             "      ( " +
             "        SELECT id, id_operador, costo_contrato_dia " +
             "        FROM oferta " +
             "      ) oferta_corta " +
             "      ON ofertas_ano_corrido.id_oferta = oferta_corta.id " +
             "    ) " +
             "    GROUP BY oferta_corta.id_operador " +
             "  ) ano_corrido " +
             "  FULL OUTER JOIN " +
             "  ( " +
             "    SELECT oferta_corta.id_operador, SUM(ofertas_ano_actual.duracion_dias*oferta_corta.costo_contrato_dia) AS dinero_ano_actual " +
             "    FROM " +
             "    ( " +
             "      ( " +
             "        SELECT id_oferta, duracion_dias, fecha_inicial " +
             "        FROM reserva " +
             "        WHERE fecha_inicial >= to_date('01-01-2023','DD-MM-YYYY') " +
             "      ) ofertas_ano_actual " +
             "      FULL OUTER JOIN " +
             "      ( " +
             "        SELECT id, id_operador, costo_contrato_dia " +
             "        FROM oferta " +
             "      ) oferta_corta " +
             "      ON ofertas_ano_actual.id_oferta = oferta_corta.id " +
             "    ) " +
             "    GROUP BY oferta_corta.id_operador " +
             "  ) ano_actual " +
             "  ON ano_actual.id_operador = ano_corrido.id_operador " +
             ") dineros " +
             "LEFT OUTER JOIN " +
             "( " +
             "  SELECT oferta_corta.id, oferta_corta.id_operador, nombre_operador.nombre, oferta_corta.costo_contrato_dia " +
             "  FROM " +
             "  ( " +
             "    SELECT id, id_operador, costo_contrato_dia " +
             "    FROM oferta " +
             "  ) oferta_corta " +
             "  LEFT OUTER JOIN " +
             "  ( " +
             "    SELECT id, nombre " +
             "    FROM operador " +
             "  ) nombre_operador " +
             "  ON nombre_operador.id = oferta_corta.id_operador " +
             ") nombrardos " +
             "ON dineros.id_operador = nombrardos.id_operador";
		
	    Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}


}
