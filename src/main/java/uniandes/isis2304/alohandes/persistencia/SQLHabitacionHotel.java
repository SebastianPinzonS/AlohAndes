package uniandes.isis2304.alohandes.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.HabitacionHotel;
import uniandes.isis2304.alohandes.negocio.Hotel;

class SQLHabitacionHotel
{
	private final static String SQL = PersistenciaAlohAndes.SQL;

	private PersistenciaAlohAndes pa;

	public SQLHabitacionHotel (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}

	public long adicionarHabitacionHotel (PersistenceManager pm, String tipo, String numerohabitacion, float tamano, String direccionHotel) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaHabitacionHotel () + "(TIPO, NUMERO_HABITACION, TAMANO, DIRECCION_HOTEL ) values (?, ?, ?, ?)");
        q.setParameters(tipo, numerohabitacion, tamano, direccionHotel);
        return (long) q.executeUnique();
	}

	public long eliminarHabitacionHotelPorDireccionHotel (PersistenceManager pm, String direccionHotel)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHabitacionHotel () + " WHERE DIRECCION_HOTEL = ?");
        q.setParameters(direccionHotel);
        return (long) q.executeUnique();            
	}

	public long eliminarHabitacionHotelPorNumeroHabitacionYDireccionHotel (PersistenceManager pm, String numeroHabitacion, String direccionHotel)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHabitacionHotel () + " WHERE (NUMERO_HABITACION = ? AND DIRECCION_HOTEL = ?)");
        q.setParameters(numeroHabitacion, direccionHotel);
        return (long) q.executeUnique();            
	}

	public HabitacionHotel darHabitacionHotelPorNumeroHabitacionYDireccionHotel (PersistenceManager pm, String numeroHabitacion, String direccionHotel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHabitacionHotel () + " WHERE (NUMERO_HABITACION = ? AND DIRECCION_HOTEL = ?)");
		q.setResultClass(HabitacionHotel.class);
		q.setParameters(numeroHabitacion, direccionHotel);
		return (HabitacionHotel) q.executeUnique();
	}

	public List<HabitacionHotel> darHabitacionHotelesPorNombre (PersistenceManager pm, String nombreHabitacionHotel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHabitacionHotel () + " WHERE nombre = ?");
		q.setResultClass(HabitacionHotel.class);
		q.setParameters(nombreHabitacionHotel);
		return (List<HabitacionHotel>) q.executeList();
	}

	public List<HabitacionHotel> darHabitacionHoteles (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHabitacionHotel ());
		q.setResultClass(HabitacionHotel.class);
		return (List<HabitacionHotel>) q.executeList();
	}

	/** ------------------------------------------------------------------------------------------------------ */
	public List<Object []> darVisitasRealizadas (PersistenceManager pm, long idHabitacionHotel)
	{
        String sql = "SELECT bar.id, bar.nombre, bar.ciudad, bar.presupuesto, bar.cantsedes, vis.fechavisita, vis.horario";
        sql += " FROM ";
        sql += pa.darTablaHabitacionHotel () + " bdor, ";
        sql += pa.darTablaVisitan () + " vis, ";
        sql += pa.darTablaBar () + " bar ";
       	sql	+= " WHERE ";
       	sql += "bdor.id = ?";
       	sql += " AND bdor.id = vis.idhabitacionHotel";
       	sql += " AND vis.idbar = bar.id";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(idHabitacionHotel);
		return q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BEBEDORES Y DE LAS BEBIDAS QUE GUSTA de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idHabitacionHotel - El identificador del habitacionHotel
	 * @return Una lista de arreglos de objetos, de tamaño 5. Los elementos del arreglo corresponden a los datos de 
	 * las bebidas (con el nombre del tipo de bebida) que le gustan al habitacionHotel:
	 * 		(id, nombre, idtipobebida, gradoalcohol) de la bebida y el (nombre) del tipo de bebida
	 */
	public List<Object []> darBebidasQueLeGustan (PersistenceManager pm, long idHabitacionHotel)
	{
        String sql = "SELECT beb.id, beb.nombre, beb.idtipobebida, beb.gradoalcohol, tb.nombre";
        sql += " FROM ";
        sql += pa.darTablaHabitacionHotel () + " bdor, ";
        sql += pa.darTablaGustan () + " g, ";
        sql += pa.darTablaBebida () + " beb, ";
        sql += pa.darTablaTipoBebida () + " tb ";
       	sql	+= " WHERE ";
       	sql += "bdor.id = ?";
       	sql += " AND bdor.id = g.idhabitacionHotel";
       	sql += " AND g.idBebida = beb.id";
       	sql += " AND beb.idtipobebida = tb.id";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(idHabitacionHotel);
		return q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BEBEDORES Y DE CUÁNTAS VISITAS HA REALIZADO de la 
	 * base de datos de Parranderos. Incluye, con 0, los habitacionHoteles que no han realizado visitas 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de arreglos de objetos, de tamaño 5. Los elementos del arreglo corresponden a los datos del habitacionHotel,
	 * y del número de visitas realizadas:
	 * 		(id, nombre, ciudad, presupuesto) del habitacionHotel y numVisitas
	 */
	public List<Object> darHabitacionHotelesYNumVisitasRealizadas (PersistenceManager pm)
	{
	    String sql = "SELECT id, nombre, ciudad, presupuesto, count (idhabitacionHotel) as numVisitas";
	    sql += " FROM " + pa.darTablaHabitacionHotel ();
	    sql += " LEFT OUTER JOIN " + pa.darTablaVisitan () + " ON id = idhabitacionHotel";
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
	 * @return El número de habitacionHoteles de la ciudad que son referenciados en VISITAN
	 */
	public long darCantidadHabitacionHotelesCiudadVisitanBares (PersistenceManager pm, String ciudad)
	{
        String sql1 = "SELECT UNIQUE ID";
        sql1 += " FROM " + pa.darTablaHabitacionHotel ();
        sql1 += " INNER JOIN " + pa.darTablaVisitan () + " ON id = idhabitacionHotel";
       	sql1	+= " WHERE ciudad = ?";
       	
       	String sql = "SELECT count (*) FROM (" + sql1 + ")";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(ciudad);
		return ((BigDecimal) q.executeUnique()).longValue ();
	}

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar la ciudad de un habitacionHotel en la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idHabitacionHotel - El identificador del habitacionHotel
	 * @param ciudad - La nueva ciudad del habitacionHotel
	 * @return El número de tuplas modificadas
	 */
	public long cambiarCiudadHabitacionHotel (PersistenceManager pm, long idHabitacionHotel, String ciudad) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaHabitacionHotel () + " SET ciudad = ? WHERE id = ?");
	     q.setParameters(ciudad, idHabitacionHotel);
	     return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para:
	 * Eliminar un habitacionHotel y las visitas a bares que haya realizado v1: 
	 * En caso que el habitacionHotel esté referenciado por otra relación, NO SE BORRA NI EL BEBEDOR, NI SUS VISITAS
	 * Adiciona entradas al log de la aplicación
	 * @param pm - El manejador de persistencia
	 * @param idHabitacionHotel - El habitacionHotel que se quiere eliminar
	 * @return Una pareja de números [número de habitacionHoteles eliminados, número de visitas eliminadas]
	 */
	public long [] eliminarHabitacionHotelYVisitas_v1 (PersistenceManager pm, long idHabitacionHotel) 
	{
      Query q1 = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaVisitan () + " WHERE idHabitacionHotel = ?");
      q1.setParameters(idHabitacionHotel);
      Query q2 = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHabitacionHotel () + " WHERE id = ?");
      q2.setParameters(idHabitacionHotel);
      
      long visitasEliminadas = (long) q1.executeUnique ();
      long habitacionHotelesEliminads = (long) q2.executeUnique ();
      return new long[] {habitacionHotelesEliminads, visitasEliminadas};
	}

	/* ****************************************************************
	 * 			Métodos de prueba para subtransacciones
	 *****************************************************************/
	//****************** QUITAR EN LA VERSIÓN A ENTREGAR
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BEBEDORES Y DE CUÁNTOS BARES VISITAN de la 
	 * base de datos de Parranderos. Incluye, con 0, los habitacionHoteles que no han realizado visitas 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de arreglos de objetos, de tamaño 5. Los elementos del arreglo corresponden a los datos del habitacionHotel,
	 * y del número de bares que visita:
	 * 		(id, nombre, ciudad, presupuesto) del habitacionHotel y numbares
	 */
	public List<Object> darHabitacionHotelesYCuantosBaresVisitan (PersistenceManager pm)
	{
		// Selecciona las parejas [idHabitacionHotel, idBar] únicas de la tabla VISITAN
		String sql0 = "SELECT DISTINCT idhabitacionHotel, idBar";
		sql0 += " FROM " + pa.darTablaVisitan ();
		
		// Agrupa las parejas anteriores por idHabitacionHotel y cuenta cuántos bares visita cada habitacionHotel
		String sql1 = "SELECT idhabitacionHotel, count (*) as numbares";
		sql1 += " FROM " + "(" + sql0 + ")";
		sql1 += " GROUP BY idHabitacionHotel";
		
		// Hace join de BEBEDORES con el resultado anterior para asociar la información del habitacionHotel
        String sql = "SELECT id, nombre, ciudad, presupuesto, NVL (numbares, 0)";
        sql += " FROM " + pa.darTablaHabitacionHotel () + " LEFT OUTER JOIN (" + sql1 + ")";
        sql += " ON id = idHabitacionHotel";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BEBEDORES Y DE CUÁNTAS VISITAS HA REALIZADO de la 
	 * base de datos de Parranderos. Incluye, con 0, los habitacionHoteles que no han realizado visitas 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de arreglos de objetos, de tamaño 5. Los elementos del arreglo corresponden a los datos del habitacionHotel,
	 * y del número de visitas realizadas:
	 * 		(id, nombre, ciudad, presupuesto) del habitacionHotel y numVisitas
	 */
	public List<Object> darHabitacionHotelesYNumVisitasRealizadas_v2 (PersistenceManager pm)
	{		
		String sql1 = "SELECT idhabitacionHotel, count (*) as numVisitas";
		sql1 += " FROM " + pa.darTablaVisitan ();
		sql1 += " GROUP BY idHabitacionHotel";
		
        String sql = "SELECT id, nombre, ciudad, presupuesto, NVL (numVisitas, 0)";
        sql += " FROM " + pa.darTablaHabitacionHotel () + " LEFT OUTER JOIN (" + sql1 + ")";
        sql += " ON id = idHabitacionHotel";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}


}
