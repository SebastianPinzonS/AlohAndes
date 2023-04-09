package uniandes.isis2304.alohandes.persistencia;

import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import oracle.sql.DATE;
import uniandes.isis2304.alohandes.negocio.Oferta;



class SQLOferta 
{

	private final static String SQL = PersistenciaAlohAndes.SQL;

	private PersistenciaAlohAndes pa;

	public SQLOferta (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	

	public long adicionarOfertaHabitacionHostal (PersistenceManager pm, String id, Date fechaInicial, int duracionContratoDias, int costoContrato, int precioEspecial, String condicionPrecioEspecial, int precioEspecialTomado, int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionHostal, String numeroHabitacionHostal, int visitas) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOferta () + "(ID, FECHA_INICIAL, DURACION_CONTRATO_DIAS, COSTO_CONTRATO, PRECIO_ESPECIAL, CONDICION_PRECIO_ESPECIAL, PRECIO_ESPECIAL_TOMADO, COSTO_ADICIONAL_SERVICIO, COSTO_SEGURO_ARRENDAMIENTO, ID_OPERADOR, DIRECCION_HOSTAL, NUMERO_HABITACION_HOSTAL, VISITAS) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, fechaInicial, duracionContratoDias, costoContrato, precioEspecial ,  condicionPrecioEspecial , precioEspecialTomado, costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionHostal, numeroHabitacionHostal, visitas );
        return (long) q.executeUnique();
	}

	public long adicionarOfertaHabitacionHotel (PersistenceManager pm, String id, Date fechaInicial, int duracionContratoDias, int costoContrato, int precioEspecial, String condicionPrecioEspecial, int precioEspecialTomado, int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel, int visitas) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOferta () + "(ID, FECHA_INICIAL, DURACION_CONTRATO_DIAS, COSTO_CONTRATO, PRECIO_ESPECIAL, CONDICION_PRECIO_ESPECIAL, PRECIO_ESPECIAL_TOMADO, COSTO_ADICIONAL_SERVICIO, COSTO_SEGURO_ARRENDAMIENTO, ID_OPERADOR, DIRECCION_HOTEL_HABITACION_HOTEL, NUMERO_HABITACION_HABITACION_HOTEL, VISITAS) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?. ?)");
        q.setParameters(id, fechaInicial, duracionContratoDias, costoContrato, precioEspecial ,  condicionPrecioEspecial , precioEspecialTomado, costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel, visitas );
        return (long) q.executeUnique();
	}

	public long adicionarOfertaViviendaUniversitaria (PersistenceManager pm, String id, Date fechaInicial, int duracionContratoDias, int costoContrato, int precioEspecial, String condicionPrecioEspecial, int precioEspecialTomado, int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionViviendaUniversitaria, String numeroApartamentoViviendaUniversitaria, int visitas) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOferta () + "(ID, FECHA_INICIAL, DURACION_CONTRATO_DIAS, COSTO_CONTRATO, PRECIO_ESPECIAL, CONDICION_PRECIO_ESPECIAL, PRECIO_ESPECIAL_TOMADO, COSTO_ADICIONAL_SERVICIO, COSTO_SEGURO_ARRENDAMIENTO, ID_OPERADOR, DIRECCION_VIVIENDA_UNIVERSITARIA, NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA, VISITAS) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, fechaInicial, duracionContratoDias, costoContrato, precioEspecial ,  condicionPrecioEspecial , precioEspecialTomado, costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionViviendaUniversitaria, numeroApartamentoViviendaUniversitaria, visitas );
        return (long) q.executeUnique();
	}

	public long adicionarOfertaViviendaHabitacion (PersistenceManager pm, String id, Date fechaInicial, int duracionContratoDias, int costoContrato, int precioEspecial, String condicionPrecioEspecial, int precioEspecialTomado, int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionViviendaHabitacion, String numeroApartamentoViviendaHabitacion, int visitas) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOferta () + "(ID, FECHA_INICIAL, DURACION_CONTRATO_DIAS, COSTO_CONTRATO, PRECIO_ESPECIAL, CONDICION_PRECIO_ESPECIAL, PRECIO_ESPECIAL_TOMADO, COSTO_ADICIONAL_SERVICIO, COSTO_SEGURO_ARRENDAMIENTO, ID_OPERADOR, DIRECCION_VIVIENDA_HABITACION, NUMERO_APARTAMENTO_VIVIENDA_HABITACION, VISITAS) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, fechaInicial, duracionContratoDias, costoContrato, precioEspecial ,  condicionPrecioEspecial , precioEspecialTomado, costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionViviendaHabitacion, numeroApartamentoViviendaHabitacion, visitas );
        return (long) q.executeUnique();
	}

	public long adicionarOfertaApartamento (PersistenceManager pm, String id, Date fechaInicial, int duracionContratoDias, int costoContrato, int precioEspecial, String condicionPrecioEspecial, int precioEspecialTomado, int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionApartamento, String numeroApartamento, int visitas) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOferta () + "(ID, FECHA_INICIAL, DURACION_CONTRATO_DIAS, COSTO_CONTRATO, PRECIO_ESPECIAL, CONDICION_PRECIO_ESPECIAL, PRECIO_ESPECIAL_TOMADO, COSTO_ADICIONAL_SERVICIO, COSTO_SEGURO_ARRENDAMIENTO, ID_OPERADOR, DIRECCION_APARTAMENTO, NUMERO_APARTAMENTO, VISITAS) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, fechaInicial, duracionContratoDias, costoContrato, precioEspecial ,  condicionPrecioEspecial , precioEspecialTomado, costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionApartamento, numeroApartamento, visitas );
        return (long) q.executeUnique();
	}

	public long adicionarOfertaViviendaExpress (PersistenceManager pm, String id, Date fechaInicial, int duracionContratoDias, int costoContrato, int precioEspecial, String condicionPrecioEspecial, int precioEspecialTomado, int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionViviendaExpress, int visitas) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOferta () + "(ID, FECHA_INICIAL, DURACION_CONTRATO_DIAS, COSTO_CONTRATO, PRECIO_ESPECIAL, CONDICION_PRECIO_ESPECIAL, PRECIO_ESPECIAL_TOMADO, COSTO_ADICIONAL_SERVICIO, COSTO_SEGURO_ARRENDAMIENTO, ID_OPERADOR, DIRECCION_VIVIENDA_EXPRESS, VISITAS) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, fechaInicial, duracionContratoDias, costoContrato, precioEspecial ,  condicionPrecioEspecial , precioEspecialTomado, costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionViviendaExpress, visitas );
        return (long) q.executeUnique();
	}

	public long adicionarClienteAOferta (PersistenceManager pm, String id, String idCliente)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pa.darTablaOferta () + " SET ID_CLIENTE = ? WHERE ID = ?");
        q.setParameters(idCliente, id);
        return (long) q.executeUnique();
	}


	public long eliminarOfertaPorId (PersistenceManager pm, String id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOferta () + " WHERE ID = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	public long eliminarOfertaPorIdOperador (PersistenceManager pm, String idOperador)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOferta () + " WHERE ID_OPERADOR = ?");
        q.setParameters(idOperador);
        return (long) q.executeUnique();
	}

	public long eliminarOfertaPorHabitacionHostal (PersistenceManager pm, String direccionHostal, String numeroHabitacionHostal)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOferta () + " WHERE (DIRECCION_HOSTAL = ? AND NUMERO_HABITACION_HOSTAL = ?)");
        q.setParameters(direccionHostal, numeroHabitacionHostal);
        return (long) q.executeUnique();
	}

	public long eliminarOfertaPorHabitacionHotel (PersistenceManager pm, String direccionHotel, String numeroHabitacionHotel)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOferta () + " WHERE (DIRECCION_HOSTAL = ? AND NUMERO_HABITACION_HOSTAL = ?)");
        q.setParameters(direccionHotel, numeroHabitacionHotel);
        return (long) q.executeUnique();
	}

	public long eliminarOfertaPorViviendaUniversitaria (PersistenceManager pm, String direccionViviendaUniversitaria, String numeroApartamentoViviendaUniversitaria)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOferta () + " WHERE (DIRECCION_VIVIENDA_UNIVERSITARIA = ? AND NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA = ?)");
        q.setParameters(direccionViviendaUniversitaria, numeroApartamentoViviendaUniversitaria);
        return (long) q.executeUnique();
	}

	public long eliminarOfertaPorViviendaHabitacion (PersistenceManager pm, String direccionViviendaHabitacion, String numeroApartamentoViviendaHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOferta () + " WHERE (DIRECCION_VIVIENDA_HABITACION = ? AND NUMERO_APARTAMENTO_VIVIENDA_HABITACION = ?)");
        q.setParameters(direccionViviendaHabitacion, numeroApartamentoViviendaHabitacion);
        return (long) q.executeUnique();
	}

	public long eliminarOfertaPorApartamento (PersistenceManager pm, String direccionApartamento, String numeroApartamento)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOferta () + " WHERE (DIRECCION_APARTAMENTO = ? AND NUMERO_APARTAMENTO = ?)");
        q.setParameters(direccionApartamento, numeroApartamento);
        return (long) q.executeUnique();
	}

	public long eliminarOfertaPorViviendaExpress (PersistenceManager pm, String direccionViviendaExpress)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOferta () + " WHERE (DIRECCION_VIVIENDA_EXPRESS = ?)");
        q.setParameters(direccionViviendaExpress);
        return (long) q.executeUnique();
	}

	/** ----------------------------------------------------------------------------------------------------------------------------------------------------------- */
	public Oferta darOfertaPorId (PersistenceManager pm, String id)
	{
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta () + " WHERE ID = ?");
		q.setResultClass(Oferta.class);
        q.setParameters(id);
        return (Oferta) q.executeUnique();
	}

	public List<Oferta> darOfertaPorIdOperador (PersistenceManager pm, String idOperador)
	{
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta () + " WHERE ID_OPERADOR = ?");
		q.setResultClass(Oferta.class);
        q.setParameters(idOperador);
        return (List<Oferta>) q.executeUnique();
	}

	public List<Oferta> darOfertaPorIdCliente (PersistenceManager pm, String idCliente)
	{
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta () + " WHERE ID_CLIENTE = ?");
		q.setResultClass(Oferta.class);
        q.setParameters(idCliente);
        return (List<Oferta>) q.executeUnique();
	}

	public Oferta darOfertaPorHabitacionHostal (PersistenceManager pm, String direccionHostal, String numeroHabitacionHostal)
	{
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta () + " WHERE (DIRECCION_HOSTAL = ? AND NUMERO_HABITACION_HOSTAL = ?)");
		q.setResultClass(Oferta.class);
        q.setParameters(direccionHostal, numeroHabitacionHostal);
        return (Oferta) q.executeUnique();
	}

	public Oferta darOfertaPorHabitacionHotel (PersistenceManager pm, String direccionHotel, String numeroHabitacionHotel)
	{
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta () + " WHERE (DIRECCION_HOSTAL = ? AND NUMERO_HABITACION_HOSTAL = ?)");
		q.setResultClass(Oferta.class);
		q.setParameters(direccionHotel, numeroHabitacionHotel);
        return (Oferta) q.executeUnique();
	}

	public Oferta darOfertaPorViviendaUniversitaria (PersistenceManager pm, String direccionViviendaUniversitaria, String numeroApartamentoViviendaUniversitaria)
	{
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta () + " WHERE (DIRECCION_VIVIENDA_UNIVERSITARIA = ? AND NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA = ?)");
		q.setResultClass(Oferta.class);
		q.setParameters(direccionViviendaUniversitaria, numeroApartamentoViviendaUniversitaria);
        return (Oferta) q.executeUnique();
	}

	public Oferta darOfertaPorViviendaHabitacion (PersistenceManager pm, String direccionViviendaHabitacion, String numeroApartamentoViviendaHabitacion)
	{
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta () + " WHERE (DIRECCION_VIVIENDA_HABITACION = ? AND NUMERO_APARTAMENTO_VIVIENDA_HABITACION = ?)");
		q.setResultClass(Oferta.class);
		q.setParameters(direccionViviendaHabitacion, numeroApartamentoViviendaHabitacion);
        return (Oferta) q.executeUnique();
	}

	public Oferta darOfertaPorApartamento (PersistenceManager pm, String direccionApartamento, String numeroApartamento)
	{
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta () + " WHERE (DIRECCION_APARTAMENTO = ? AND NUMERO_APARTAMENTO = ?)");
		q.setResultClass(Oferta.class);
        q.setParameters(direccionApartamento, numeroApartamento);
        return (Oferta) q.executeUnique();
	}

	public Oferta darOfertaPorViviendaExpress (PersistenceManager pm, String direccionViviendaExpress)
	{
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta () + " WHERE (DIRECCION_VIVIENDA_EXPRESS = ?)");
		q.setResultClass(Oferta.class);
        q.setParameters(direccionViviendaExpress);
        return (Oferta) q.executeUnique();
	}

	public List<Oferta> darOfertas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta ());
		q.setResultClass(Oferta.class);
		return (List<Oferta>) q.executeList();
	}


	
}
