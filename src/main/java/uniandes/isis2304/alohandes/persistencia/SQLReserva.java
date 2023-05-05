uniandes.isis2304.alohandes.persistencia;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Reserva;

public class SQLReserva 
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
	public SQLReserva (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	

	public long adicionarReserva (PersistenceManager pm, String idOferta, String idCliente, int precioEspecialTomado) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOferta () + "(id_oferta, id_cliente, precio_especial_tomado) VALUES (?, ?, ?)");
        q.setParameters(idOferta, idCliente, precioEspecialTomado);
        return (long) q.executeUnique();
	}

	public long eliminarOfertaPorIdOferta (PersistenceManager pm, String idOferta)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOferta() + " WHERE id_oferta = ?");
        q.setParameters(idOferta);
        return (long) q.executeUnique();            
	}

	public long eliminarOfertaPorIdCliente (PersistenceManager pm, String idCliente)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOferta() + " WHERE id_cliente = ?");
        q.setParameters(idCliente);
        return (long) q.executeUnique();        

	public Oferta darOfertaPorIdOferta (PersistenceManager pm, String idOferta) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta () + " WHERE id_oferta = ?");
		q.setResultClass(Oferta.class);
		q.setParameters(idOferta);
		return (Oferta) q.executeUnique();
	}

	public List<Oferta> darOfertaPorNombre (PersistenceManager pm, String idCliente) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta () + " WHERE id_cliente = ?");
		q.setResultClass(Oferta.class);
		q.setParameters(idCliente);
		return (List<Oferta>) q.executeList();
	}

	public List<Oferta> darOfertas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT ID, NOMBRE, TIPO, VALIDACION_CAMARA_DE_COMERCIO_EMPRESA, VALIDACION_SUPER_TURISMO_EMPRESA, MIEMBRO_COMUNIDAD_UNIVERSITARIA_PERSONA FROM " + pa.darTablaOferta());
		List<Oferta> resp = new LinkedList<>();
		List results = q.executeList();
		for (Object obj : results)
		{
			Object [] datos = (Object []) obj;
			String id =  (String) datos [0];
			String nombre =  (String) datos [1];
			String tipo = (String) datos[2];
			int validacionCamaraDeComercioEmpresa = ((BigDecimal) datos [3]).intValue();
			int validacionSuperTurismoEmpresa = ((BigDecimal) datos [4]).intValue();
			int miembroComunidadUniversitariaPersona = ((BigDecimal) datos [5]).intValue();
			resp.add (new Oferta(id, nombre, tipo, validacionCamaraDeComercioEmpresa, validacionSuperTurismoEmpresa, miembroComunidadUniversitariaPersona));
		}
		return (resp) ;
	}

	

}

