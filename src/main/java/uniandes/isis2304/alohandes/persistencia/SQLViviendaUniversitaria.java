package uniandes.isis2304.alohandes.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.ViviendaUniversitaria;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BEBEDOR de AlohAndes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLViviendaUniversitaria
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
	public SQLViviendaUniversitaria (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	

	public long adiccionarViviendaUniversitaria (PersistenceManager pm, String nombre, String direccion, String numeroApartamento, int viviendaCompartida, int capacidad, String menaje, float calificacion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaViviendaUniversitaria() + "(NOMBRE, DIRECCION, NUMERO_APARTAMENTO, VIVIENDA_COMPARTIDA, CAPACIDAD, MENAJE, CALIFICACION) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(nombre, direccion, numeroApartamento, viviendaCompartida, capacidad, menaje, calificacion);
        return (long) q.executeUnique();
	}

	public long eliminarViviendaUniversitariaPorDireccionYNumero (PersistenceManager pm, String direccion, String numeroApartamento)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaViviendaUniversitaria () + " WHERE (DIRECCION = ? AND NUMERO_APARTAMENTO = ?)");
        q.setParameters(direccion,numeroApartamento);
        return (long) q.executeUnique();            
	}

	public long eliminarViviendaUniversitariaPorDireccion (PersistenceManager pm, String direccion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaViviendaUniversitaria () + " WHERE DIRECCION = ?");
        q.setParameters(direccion);
        return (long) q.executeUnique();            
	}

	public long eliminarViviendaUniversitariaPorNumeroApto (PersistenceManager pm, String numeroApartamento)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaViviendaUniversitaria () + " WHERE NUMERO_APARTAMENTO = ?");
        q.setParameters(numeroApartamento);
        return (long) q.executeUnique();            
	}

	public ViviendaUniversitaria darViviendaUniversitariaPorDireccionYNumero (PersistenceManager pm, String direccion, String numeroApartamento) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaViviendaUniversitaria () + "WHERE (DIRECCION = ? AND NUMERO_APARTAMENTO = ?)");
		q.setResultClass(ViviendaUniversitaria.class);
		q.setParameters(direccion,numeroApartamento);
		return (ViviendaUniversitaria) q.executeUnique();
	}

	public List<ViviendaUniversitaria> darViviendaUniversitariaPorDireccion (PersistenceManager pm, String direccion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaViviendaUniversitaria () + " WHERE DIRECCION = ?");
		q.setResultClass(ViviendaUniversitaria.class);
		q.setParameters(direccion);
		return (List<ViviendaUniversitaria>) q.executeList();
	}

	public List<ViviendaUniversitaria> darViviendaUniversitariaPorNumero (PersistenceManager pm, String numeroApartamento) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaViviendaUniversitaria () + " WHERE NUMERO_APARTAMENTO = ?");
		q.setResultClass(ViviendaUniversitaria.class);
		q.setParameters(numeroApartamento);
		return (List<ViviendaUniversitaria>) q.executeList();
	}

	public List<ViviendaUniversitaria> darTodasViviendasUniversitaria (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaViviendaUniversitaria ());
		q.setResultClass(ViviendaUniversitaria.class);
		return (List<ViviendaUniversitaria>) q.executeList();
	}

}
