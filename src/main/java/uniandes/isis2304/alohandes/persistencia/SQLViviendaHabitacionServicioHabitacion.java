package uniandes.isis2304.alohandes.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.ViviendaHabitacionServicioHabitacion;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBEDOR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLViviendaHabitacionServicioHabitacion
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
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
	public SQLViviendaHabitacionServicioHabitacion (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	

	public long adiccionarViviendaHabitacionServicioHabitacion (PersistenceManager pm, String direccionViviendaHabitacion, String numeroViviendaHabitacion, String tipo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaViviendaHabitacionServicioHabitacion () + "(DIRECCION_VIVIENDA_HABITACION, NUMERO_VIVIENDA_HABITACION, TIPO) values (?, ?, ?)");
        q.setParameters(direccionViviendaHabitacion, numeroViviendaHabitacion, tipo);
        return (long) q.executeUnique();
	}

	public long eliminarViviendaHabitacionServicioHabitacionPorDirecionNombreYTipo (PersistenceManager pm, String direccionViviendaHabitacion, String numeroViviendaHabitacion, String tipo)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaViviendaHabitacionServicioHabitacion () + " WHERE (DIRECCION_VIVIENDA_HABITACION = ? AND NUMERO_VIVIENDA_HABITACION = ? AND TIPO = ?)");
        q.setParameters(direccionViviendaHabitacion, numeroViviendaHabitacion, tipo);
        return (long) q.executeUnique();            
	}

	public long eliminarViviendaHabitacionServicioHabitacionPorDirecionYNumeroViviendaHabitacion (PersistenceManager pm, String direccionViviendaHabitacion, String numeroViviendaHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaViviendaHabitacionServicioHabitacion () + " WHERE (DIRECCION_VIVIENDA_HABITACION = ? AND NUMERO_VIVIENDA_HABITACION = ?)");
        q.setParameters(direccionViviendaHabitacion, numeroViviendaHabitacion);
        return (long) q.executeUnique();            
	}

	public long eliminarViviendaHabitacionServicioHabitacionPorTipo (PersistenceManager pm, String tipo)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaViviendaHabitacionServicioHabitacion () + " WHERE TIPO = ?");
        q.setParameters(tipo);
        return (long) q.executeUnique();            
	}

	public ViviendaHabitacionServicioHabitacion darViviendaHabitacionServicioPorDireccionNombreYTipo (PersistenceManager pm, String direccionViviendaHabitacion, String numeroViviendaHabitacion, String tipo)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaViviendaHabitacionServicioHabitacion () + " WHERE (DIRECCION_VIVIENDA_HABITACION = ? AND NUMERO_VIVIENDA_HABITACION = ? AND TIPO = ?)");
		q.setResultClass(ViviendaHabitacionServicioHabitacion.class);
		q.setParameters(direccionViviendaHabitacion, numeroViviendaHabitacion, tipo);
		return (ViviendaHabitacionServicioHabitacion) q.executeUnique();
	}

	public List<ViviendaHabitacionServicioHabitacion> darViviendaHabitacionServicioPorDireccion (PersistenceManager pm, String direccionViviendaHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaViviendaHabitacionServicioHabitacion () + " WHERE DIRECCION_VIVIENDA_HABITACION = ?");
		q.setResultClass(ViviendaHabitacionServicioHabitacion.class);
		q.setParameters(direccionViviendaHabitacion);
		return (List<ViviendaHabitacionServicioHabitacion>) q.executeList();
	}

	public List<ViviendaHabitacionServicioHabitacion> darViviendaHabitacionServicioPorNumero (PersistenceManager pm, String numeroViviendaHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaViviendaHabitacionServicioHabitacion () + " WHERE NUMERO_VIVIENDA_HABITACION = ?");
		q.setResultClass(ViviendaHabitacionServicioHabitacion.class);
		q.setParameters(numeroViviendaHabitacion);
		return (List<ViviendaHabitacionServicioHabitacion>) q.executeList();
	}

	public List<ViviendaHabitacionServicioHabitacion> darViviendaHabitacionServicioPorTipo (PersistenceManager pm, String tipo) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaViviendaHabitacionServicioHabitacion () + " WHERE TIPO = ?");
		q.setResultClass(ViviendaHabitacionServicioHabitacion.class);
		q.setParameters(tipo);
		return (List<ViviendaHabitacionServicioHabitacion>) q.executeList();
	}

	public List<ViviendaHabitacionServicioHabitacion> darTodasViviendaHabitacionServicio (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaViviendaHabitacionServicioHabitacion ());
		q.setResultClass(ViviendaHabitacionServicioHabitacion.class);
		return (List<ViviendaHabitacionServicioHabitacion>) q.executeList();
	}

}
