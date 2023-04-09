package uniandes.isis2304.alohandes.persistencia;

import java.math.BigDecimal;
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

	public List<Hotel> darHotelesPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHotel () + " WHERE NOMBRE = ?");
		q.setResultClass(Hotel.class);
		q.setParameters(nombre);
		return (List<Hotel>) q.executeList();
	}

	public List<Hotel> darHoteles (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaHotel ());
		q.setResultClass(Hotel.class);
		return (List<Hotel>) q.executeList();
	}

	/** --------------------------------------------------------------------------------------------------------------------------  */

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BEBEDORES Y DE SUS VISITAS REALIZADAS de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idHotel - El identificador del hotel
	 * @return Una lista de arreglos de objetos, de tamaño 7. Los elementos del arreglo corresponden a los datos de 
	 * los bares visitados y los datos propios de la visita:
	 * 		(id, nombre, ciudad, presupuesto, cantsedes) de los bares y (fechavisita, horario) de las visitas
	 */
	public List<Object []> darVisitasRealizadas (PersistenceManager pm, long idHotel)
	{
        String sql = "SELECT bar.id, bar.nombre, bar.ciudad, bar.presupuesto, bar.cantsedes, vis.fechavisita, vis.horario";
        sql += " FROM ";
        sql += pa.darTablaHotel () + " bdor, ";
        sql += pa.darTablaVisitan () + " vis, ";
        sql += pa.darTablaBar () + " bar ";
       	sql	+= " WHERE ";
       	sql += "bdor.id = ?";
       	sql += " AND bdor.id = vis.idhotel";
       	sql += " AND vis.idbar = bar.id";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(idHotel);
		return q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BEBEDORES Y DE LAS BEBIDAS QUE GUSTA de la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idHotel - El identificador del hotel
	 * @return Una lista de arreglos de objetos, de tamaño 5. Los elementos del arreglo corresponden a los datos de 
	 * las bebidas (con el nombre del tipo de bebida) que le gustan al hotel:
	 * 		(id, nombre, idtipobebida, gradoalcohol) de la bebida y el (nombre) del tipo de bebida
	 */
	public List<Object []> darBebidasQueLeGustan (PersistenceManager pm, long idHotel)
	{
        String sql = "SELECT beb.id, beb.nombre, beb.idtipobebida, beb.gradoalcohol, tb.nombre";
        sql += " FROM ";
        sql += pa.darTablaHotel () + " bdor, ";
        sql += pa.darTablaGustan () + " g, ";
        sql += pa.darTablaBebida () + " beb, ";
        sql += pa.darTablaTipoBebida () + " tb ";
       	sql	+= " WHERE ";
       	sql += "bdor.id = ?";
       	sql += " AND bdor.id = g.idhotel";
       	sql += " AND g.idBebida = beb.id";
       	sql += " AND beb.idtipobebida = tb.id";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(idHotel);
		return q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BEBEDORES Y DE CUÁNTAS VISITAS HA REALIZADO de la 
	 * base de datos de Parranderos. Incluye, con 0, los hoteles que no han realizado visitas 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de arreglos de objetos, de tamaño 5. Los elementos del arreglo corresponden a los datos del hotel,
	 * y del número de visitas realizadas:
	 * 		(id, nombre, ciudad, presupuesto) del hotel y numVisitas
	 */
	public List<Object> darHotelesYNumVisitasRealizadas (PersistenceManager pm)
	{
	    String sql = "SELECT id, nombre, ciudad, presupuesto, count (idhotel) as numVisitas";
	    sql += " FROM " + pa.darTablaHotel ();
	    sql += " LEFT OUTER JOIN " + pa.darTablaVisitan () + " ON id = idhotel";
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
	 * @return El número de hoteles de la ciudad que son referenciados en VISITAN
	 */
	public long darCantidadHotelesCiudadVisitanBares (PersistenceManager pm, String ciudad)
	{
        String sql1 = "SELECT UNIQUE ID";
        sql1 += " FROM " + pa.darTablaHotel ();
        sql1 += " INNER JOIN " + pa.darTablaVisitan () + " ON id = idhotel";
       	sql1	+= " WHERE ciudad = ?";
       	
       	String sql = "SELECT count (*) FROM (" + sql1 + ")";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(ciudad);
		return ((BigDecimal) q.executeUnique()).longValue ();
	}

	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar la ciudad de un hotel en la 
	 * base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idHotel - El identificador del hotel
	 * @param ciudad - La nueva ciudad del hotel
	 * @return El número de tuplas modificadas
	 */
	public long cambiarCiudadHotel (PersistenceManager pm, long idHotel, String ciudad) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaHotel () + " SET ciudad = ? WHERE id = ?");
	     q.setParameters(ciudad, idHotel);
	     return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para:
	 * Eliminar un hotel y las visitas a bares que haya realizado v1: 
	 * En caso que el hotel esté referenciado por otra relación, NO SE BORRA NI EL BEBEDOR, NI SUS VISITAS
	 * Adiciona entradas al log de la aplicación
	 * @param pm - El manejador de persistencia
	 * @param idHotel - El hotel que se quiere eliminar
	 * @return Una pareja de números [número de hoteles eliminados, número de visitas eliminadas]
	 */
	public long [] eliminarHotelYVisitas_v1 (PersistenceManager pm, long idHotel) 
	{
      Query q1 = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaVisitan () + " WHERE idHotel = ?");
      q1.setParameters(idHotel);
      Query q2 = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHotel () + " WHERE id = ?");
      q2.setParameters(idHotel);
      
      long visitasEliminadas = (long) q1.executeUnique ();
      long hotelesEliminads = (long) q2.executeUnique ();
      return new long[] {hotelesEliminads, visitasEliminadas};
	}

	/* ****************************************************************
	 * 			Métodos de prueba para subtransacciones
	 *****************************************************************/
	//****************** QUITAR EN LA VERSIÓN A ENTREGAR
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BEBEDORES Y DE CUÁNTOS BARES VISITAN de la 
	 * base de datos de Parranderos. Incluye, con 0, los hoteles que no han realizado visitas 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de arreglos de objetos, de tamaño 5. Los elementos del arreglo corresponden a los datos del hotel,
	 * y del número de bares que visita:
	 * 		(id, nombre, ciudad, presupuesto) del hotel y numbares
	 */
	public List<Object> darHotelesYCuantosBaresVisitan (PersistenceManager pm)
	{
		// Selecciona las parejas [idHotel, idBar] únicas de la tabla VISITAN
		String sql0 = "SELECT DISTINCT idhotel, idBar";
		sql0 += " FROM " + pa.darTablaVisitan ();
		
		// Agrupa las parejas anteriores por idHotel y cuenta cuántos bares visita cada hotel
		String sql1 = "SELECT idhotel, count (*) as numbares";
		sql1 += " FROM " + "(" + sql0 + ")";
		sql1 += " GROUP BY idHotel";
		
		// Hace join de BEBEDORES con el resultado anterior para asociar la información del hotel
        String sql = "SELECT id, nombre, ciudad, presupuesto, NVL (numbares, 0)";
        sql += " FROM " + pa.darTablaHotel () + " LEFT OUTER JOIN (" + sql1 + ")";
        sql += " ON id = idHotel";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BEBEDORES Y DE CUÁNTAS VISITAS HA REALIZADO de la 
	 * base de datos de Parranderos. Incluye, con 0, los hoteles que no han realizado visitas 
	 * @param pm - El manejador de persistencia
	 * @return Una lista de arreglos de objetos, de tamaño 5. Los elementos del arreglo corresponden a los datos del hotel,
	 * y del número de visitas realizadas:
	 * 		(id, nombre, ciudad, presupuesto) del hotel y numVisitas
	 */
	public List<Object> darHotelesYNumVisitasRealizadas_v2 (PersistenceManager pm)
	{		
		String sql1 = "SELECT idhotel, count (*) as numVisitas";
		sql1 += " FROM " + pa.darTablaVisitan ();
		sql1 += " GROUP BY idHotel";
		
        String sql = "SELECT id, nombre, ciudad, presupuesto, NVL (numVisitas, 0)";
        sql += " FROM " + pa.darTablaHotel () + " LEFT OUTER JOIN (" + sql1 + ")";
        sql += " ON id = idHotel";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}


}
