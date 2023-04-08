package uniandes.isis2304.alohandes.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.InstalacionHabitacionHotel;

class SQLInstalacionHabitacionHotel 
{
	
	private final static String SQL = PersistenciaAlohAndes.SQL;

	private PersistenciaAlohAndes pa;

	public SQLHotel (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	

	public long adicionarInstalacionHabitacionHotel (PersistenceManager pm, String tipoInstalacion, String numeroHabitacionHabitacionHotel, String direccionHotelHabitacionHotel) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaInstalacionHabitacionHotel () + "(tipoInstalacion, numeroHabitacionHabitacionHotel, direccionHotelHabitacionHotel) values (?, ?, ?)");
        q.setParameters(tipoInstalacion, numeroHabitacionHabitacionHotel, direccionHotelHabitacionHotel);
        return (long) q.executeUnique();
	}

	public long eliminarInstalacionHabitacionHotelPorTipoInstalacionYDireccionHotelHabitacionHotel (PersistenceManager pm, String tipoInstlacion, String direccionHotelHabitacionHotel )
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaInstalacionHabitacionHotel () + " WHERE TIPO_INSTALACION = ? AND DIRECCION_HOTEL_HABITACION_HOTEL = ?");
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
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaInstalacionHabitacionHotel () + " WHERE DIRECCION_HOTEL_HABITACION_HOTEL = ? AND NUMERO_HABITACION_HABITACION_HOTEL = ?");
        q.setParameters(direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
        return (long) q.executeUnique();            
	}


	public InstalacionHabitacionHotel darInstalacionHabitacionHotelPorDireccionHotelHabitacionHotel (PersistenceManager pm, String direccionHotelHabitacionHotel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaInstalacionHabitacionHotel () + " WHERE DIRECCION_HOTEL_HABITACION_HOTEL = ?");
		q.setResultClass(InstalacionHabitacionHotel.class);
		q.setParameters(direccionHotelHabitacionHotel);
		return (InstalacionHabitacionHotel) q.executeUnique();
	}

	public InstalacionHabitacionHotel darInstalacionHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel (PersistenceManager pm, String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaInstalacionHabitacionHotel () + " WHERE DIRECCION_HOTEL_HABITACION_HOTEL = ? AND NUMERO_HABITACION_HABITACION_HOTEL = ?");
		q.setResultClass(InstalacionHabitacionHotel.class);
		q.setParameters(direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
		return (InstalacionHabitacionHotel) q.executeUnique();
	}

	public List<InstalacionHabitacionHotel> darTodasInstalacionHabitacionHotel (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaInstalacionHabitacionHotel ());
		q.setResultClass(InstalacionHabitacionHotel.class);
		return (List<InstalacionHabitacionHotel>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BEBEDORES Y DE SUS VISITAS REALIZADAS de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idBebedor - El identificador del bebedor
	 * @return Una lista de arreglos de objetos, de tamaño 7. Los elementos del arreglo corresponden a los datos de 
	 * los bares visitados y los datos propios de la visita:
	 * 		(id, nombre, ciudad, presupuesto, cantsedes) de los bares y (fechavisita, horario) de las visitas
	 */
	public List<Object []> darVisitasRealizadas (PersistenceManager pm, long idBebedor)
	{
        String sql = "SELECT bar.id, bar.nombre, bar.ciudad, bar.presupuesto, bar.cantsedes, vis.fechavisita, vis.horario";
        sql += " FROM ";
        sql += pa.darTablaInstalacionHabitacionHotel () + " bdor, ";
        sql += pa.darTablaVisitan () + " vis, ";
        sql += pa.darTablaBar () + " bar ";
       	sql	+= " WHERE ";
       	sql += "bdor.id = ?";
       	sql += " AND bdor.id = vis.idbebedor";
       	sql += " AND vis.idbar = bar.id";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(idBebedor);
		return q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BEBEDORES Y DE LAS BEBIDAS QUE GUSTA de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idBebedor - El identificador del bebedor
	 * @return Una lista de arreglos de objetos, de tamaño 5. Los elementos del arreglo corresponden a los datos de 
	 * las bebidas (con el nombre del tipo de bebida) que le gustan al bebedor:
	 * 		(id, nombre, idtipobebida, gradoalcohol) de la bebida y el (nombre) del tipo de bebida
	 */
	public List<Object []> darBebidasQueLeGustan (PersistenceManager pm, long idBebedor)
	{
        String sql = "SELECT beb.id, beb.nombre, beb.idtipobebida, beb.gradoalcohol, tb.nombre";
        sql += " FROM ";
        sql += pa.darTablaInstalacionHabitacionHotel () + " bdor, ";
        sql += pa.darTablaGustan () + " g, ";
        sql += pa.darTablaBebida () + " beb, ";
        sql += pa.darTablaTipoBebida () + " tb ";
       	sql	+= " WHERE ";
       	sql += "bdor.id = ?";
       	sql += " AND bdor.id = g.idbebedor";
       	sql += " AND g.idBebida = beb.id";
       	sql += " AND beb.idtipobebida = tb.id";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(idBebedor);
		return q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BEBEDORES Y DE CUÁNTAS VISITAS HA REALIZADO de la 
	 * base de datos de Parranderos. Incluye, con 0, los bebedores que no han realizado visitas 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de arreglos de objetos, de tamaño 5. Los elementos del arreglo corresponden a los datos del bebedor,
	 * y del número de visitas realizadas:
	 * 		(id, nombre, ciudad, presupuesto) del bebedor y numVisitas
	 */
	public List<Object> darBebedoresYNumVisitasRealizadas (PersistenceManager pm)
	{
	    String sql = "SELECT id, nombre, ciudad, presupuesto, count (idbebedor) as numVisitas";
	    sql += " FROM " + pa.darTablaInstalacionHabitacionHotel ();
	    sql += " LEFT OUTER JOIN " + pa.darTablaVisitan () + " ON id = idbebedor";
	    sql	+= " GROUP BY id, nombre, ciudad, presupuesto";
	    sql	+= " ORDER BY numVisitas";
		
	    Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la cantidad de BEBEDORES de una ciudad que visitan bares de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param ciudad - La ciudad de interés
	 * @return El número de bebedores de la ciudad que son referenciados en VISITAN
	 */
	public long darCantidadBebedoresCiudadVisitanBares (PersistenceManager pm, String ciudad)
	{
        String sql1 = "SELECT UNIQUE ID";
        sql1 += " FROM " + pa.darTablaInstalacionHabitacionHotel ();
        sql1 += " INNER JOIN " + pa.darTablaVisitan () + " ON id = idbebedor";
       	sql1	+= " WHERE ciudad = ?";
       	
       	String sql = "SELECT count (*) FROM (" + sql1 + ")";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(ciudad);
		return ((BigDecimal) q.executeUnique()).longValue ();
	}

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar la ciudad de un bebedor en la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idBebedor - El identificador del bebedor
	 * @param ciudad - La nueva ciudad del bebedor
	 * @return El número de tuplas modificadas
	 */
	public long cambiarCiudadBebedor (PersistenceManager pm, long idBebedor, String ciudad) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaInstalacionHabitacionHotel () + " SET ciudad = ? WHERE id = ?");
	     q.setParameters(ciudad, idBebedor);
	     return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para:
	 * Eliminar un bebedor y las visitas a bares que haya realizado v1: 
	 * En caso que el bebedor esté referenciado por otra relación, NO SE BORRA NI EL BEBEDOR, NI SUS VISITAS
	 * Adiciona entradas al log de la aplicación
	 * @param pm - El manejador de persistencia
	 * @param idBebedor - El bebedor que se quiere eliminar
	 * @return Una pareja de números [número de bebedores eliminados, número de visitas eliminadas]
	 */
	public long [] eliminarBebedorYVisitas_v1 (PersistenceManager pm, long idBebedor) 
	{
      Query q1 = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaVisitan () + " WHERE idBebedor = ?");
      q1.setParameters(idBebedor);
      Query q2 = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaInstalacionHabitacionHotel () + " WHERE id = ?");
      q2.setParameters(idBebedor);
      
      long visitasEliminadas = (long) q1.executeUnique ();
      long bebedoresEliminads = (long) q2.executeUnique ();
      return new long[] {bebedoresEliminads, visitasEliminadas};
	}

	/* ****************************************************************
	 * 			Métodos de prueba para subtransacciones
	 *****************************************************************/
	//****************** QUITAR EN LA VERSIÓN A ENTREGAR
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BEBEDORES Y DE CUÁNTOS BARES VISITAN de la 
	 * base de datos de Parranderos. Incluye, con 0, los bebedores que no han realizado visitas 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de arreglos de objetos, de tamaño 5. Los elementos del arreglo corresponden a los datos del bebedor,
	 * y del número de bares que visita:
	 * 		(id, nombre, ciudad, presupuesto) del bebedor y numbares
	 */
	public List<Object> darBebedoresYCuantosBaresVisitan (PersistenceManager pm)
	{
		// Selecciona las parejas [idBebedor, idBar] únicas de la tabla VISITAN
		String sql0 = "SELECT DISTINCT idbebedor, idBar";
		sql0 += " FROM " + pa.darTablaVisitan ();
		
		// Agrupa las parejas anteriores por idBebedor y cuenta cuántos bares visita cada bebedor
		String sql1 = "SELECT idbebedor, count (*) as numbares";
		sql1 += " FROM " + "(" + sql0 + ")";
		sql1 += " GROUP BY idBebedor";
		
		// Hace join de BEBEDORES con el resultado anterior para asociar la información del bebedor
        String sql = "SELECT id, nombre, ciudad, presupuesto, NVL (numbares, 0)";
        sql += " FROM " + pa.darTablaInstalacionHabitacionHotel () + " LEFT OUTER JOIN (" + sql1 + ")";
        sql += " ON id = idBebedor";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BEBEDORES Y DE CUÁNTAS VISITAS HA REALIZADO de la 
	 * base de datos de Parranderos. Incluye, con 0, los bebedores que no han realizado visitas 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de arreglos de objetos, de tamaño 5. Los elementos del arreglo corresponden a los datos del bebedor,
	 * y del número de visitas realizadas:
	 * 		(id, nombre, ciudad, presupuesto) del bebedor y numVisitas
	 */
	public List<Object> darBebedoresYNumVisitasRealizadas_v2 (PersistenceManager pm)
	{		
		String sql1 = "SELECT idbebedor, count (*) as numVisitas";
		sql1 += " FROM " + pa.darTablaVisitan ();
		sql1 += " GROUP BY idBebedor";
		
        String sql = "SELECT id, nombre, ciudad, presupuesto, NVL (numVisitas, 0)";
        sql += " FROM " + pa.darTablaInstalacionHabitacionHotel () + " LEFT OUTER JOIN (" + sql1 + ")";
        sql += " ON id = idBebedor";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}


}
