package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Apartamento;


class SQLApartamento
{
	
	private final static String SQL = PersistenciaAlohAndes.SQL;


	private PersistenciaAlohAndes pa;

	public SQLApartamento (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}

	public long adicionarApartamento (PersistenceManager pm, String nombre, String numero, int amoblado, int numeroHabitaciones,String direccion, int incluyeServicios, float calificacion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaApartamento () + "(NOMBRE, NUMERO, AMOBLADO, NUMERO_HABITACIONES, DIRECCION, INCLUYE_SERVICIOS, CALIFICACION ) values (?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(nombre, numero, amoblado, numeroHabitaciones, direccion, incluyeServicios, calificacion);
        return (long) q.executeUnique();
	}


	public long eliminarApartamentoPorNombreYNumero (PersistenceManager pm, String nombre, String numero)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaApartamento () + " WHERE (NOMBRE = ? AND NUMERO = ?)");
        q.setParameters(nombre, numero);
        return (long) q.executeUnique();            
	}

	public long eliminarApartmentoPorDireccionYNumero (PersistenceManager pm, String direccion, String numero)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaApartamento () + " WHERE (DIRECCION = ? AND NUMERO = ?)");
        q.setParameters(direccion, numero);
        return (long) q.executeUnique();            
	}

	public Apartamento darApartamentoPorDireccionYNumero (PersistenceManager pm, String direccion, String numero) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaApartamento()+ " WHERE (DIRECCION = ? AND NUMERO = ?)");
		q.setResultClass(Apartamento.class);
		q.setParameters(direccion, numero);
		return (Apartamento) q.executeUnique();
	}

	public List<Apartamento> darApartamentoPorNombreYNumero (PersistenceManager pm, String nombre, String numero) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaApartamento()+ " WHERE (NOMBRE = ? AND NUMERO = ?)");
		q.setResultClass(Apartamento.class);
		q.setParameters(nombre, numero);
		return (List<Apartamento>) q.executeUnique();
	}

	
	public List<Apartamento> darApartamentosPorDireccion (PersistenceManager pm, String direccion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaApartamento () + " WHERE DIRECCION = ?");
		q.setResultClass(Apartamento.class);
		q.setParameters(direccion);
		return (List<Apartamento>) q.executeList();
	}


	public List<Apartamento> darApartamento (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaApartamento ());
		q.setResultClass(Apartamento.class);
		return (List<Apartamento>) q.executeList();
	}

}
