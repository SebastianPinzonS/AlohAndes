package uniandes.isis2304.alohandes.persistencia;

import java.sql.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Oferta;



class SQLOferta 
{

	private final static String SQL = PersistenciaAlohAndes.SQL;

	private PersistenciaAlohAndes pa;

	public SQLOferta (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	

	public long adicionarOfertaHabitacionHostal (PersistenceManager pm, long id,   int costoContrato, int precioEspecial, String condicionPrecioEspecial, int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionHostal, String numeroHabitacionHostal) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOferta () + "(ID, FECHA_PUBLICACION_RESERVA,  COSTO_CONTRATO_DIA, PRECIO_ESPECIAL_DIA, CONDICION_PRECIO_ESPECIAL,  COSTO_ADICIONAL_SERVICIOS, COSTO_SEGURO_ARRENDAMIENTO, ID_OPERADOR, DIRECCION_HOSTAL_HABITACION_HOSTAL, NUMERO_HABITACION_HABITACION_HOSTAL, VISITAS) values (?, CURRENT_DATE, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id,   costoContrato, precioEspecial ,  condicionPrecioEspecial , costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionHostal, numeroHabitacionHostal, 0 );
        return (long) q.executeUnique();
	}

	public long adicionarOfertaHabitacionHotel (PersistenceManager pm, long id,   int costoContrato, int precioEspecial, String condicionPrecioEspecial, int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOferta () + "(ID, FECHA_PUBLICACION_RESERVA,  COSTO_CONTRATO_DIA, PRECIO_ESPECIAL_DIA, CONDICION_PRECIO_ESPECIAL,  COSTO_ADICIONAL_SERVICIOS, COSTO_SEGURO_ARRENDAMIENTO, ID_OPERADOR, DIRECCION_HOTEL_HABITACION_HOTEL, NUMERO_HABITACION_HABITACION_HOTEL, VISITAS) values (?, CURRENT_DATE, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id,   costoContrato, precioEspecial ,  condicionPrecioEspecial , costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel, 0 );
        return (long) q.executeUnique();
	}

	public long adicionarOfertaViviendaUniversitaria (PersistenceManager pm, long id,   int costoContrato, int precioEspecial, String condicionPrecioEspecial, int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionViviendaUniversitaria, String numeroApartamentoViviendaUniversitaria)
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOferta () + "(ID, FECHA_PUBLICACION_RESERVA,  COSTO_CONTRATO_DIA, PRECIO_ESPECIAL_DIA, CONDICION_PRECIO_ESPECIAL,  COSTO_ADICIONAL_SERVICIOS, COSTO_SEGURO_ARRENDAMIENTO, ID_OPERADOR, DIRECCION_VIVIENDA_UNIVERSITARIA, NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA, VISITAS) values ( ?, CURRENT_DATE, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id,   costoContrato, precioEspecial ,  condicionPrecioEspecial , costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionViviendaUniversitaria, numeroApartamentoViviendaUniversitaria, 0 );
        return (long) q.executeUnique();
	}

	public long adicionarOfertaViviendaHabitacion (PersistenceManager pm, long id,   int costoContrato, int precioEspecial, String condicionPrecioEspecial, int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionViviendaHabitacion, String numeroApartamentoViviendaHabitacion) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOferta () + "(ID, FECHA_PUBLICACION_RESERVA,  COSTO_CONTRATO_DIA, PRECIO_ESPECIAL_DIA, CONDICION_PRECIO_ESPECIAL,  COSTO_ADICIONAL_SERVICIOS, COSTO_SEGURO_ARRENDAMIENTO, ID_OPERADOR, DIRECCION_VIVIENDA_HABITACION, NUMERO_APARTAMENTO_VIVIENDA_HABITACION, VISITAS) values ( ?, CURRENT_DATE, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id,   costoContrato, precioEspecial ,  condicionPrecioEspecial , costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionViviendaHabitacion, numeroApartamentoViviendaHabitacion, 0 );
        return (long) q.executeUnique();
	}

	public long adicionarOfertaApartamento (PersistenceManager pm, long id,   int costoContrato, int precioEspecial, String condicionPrecioEspecial, int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionApartamento, String numeroApartamento) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOferta () + "(ID, FECHA_PUBLICACION_RESERVA,  COSTO_CONTRATO_DIA, PRECIO_ESPECIAL_DIA, CONDICION_PRECIO_ESPECIAL, COSTO_ADICIONAL_SERVICIOS, COSTO_SEGURO_ARRENDAMIENTO, ID_OPERADOR, DIRECCION_APARTAMENTO, NUMERO_APARTAMENTO, VISITAS) values ( ?, CURRENT_DATE, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id,   costoContrato, precioEspecial ,  condicionPrecioEspecial , costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionApartamento, numeroApartamento, 0 );
        return (long) q.executeUnique();
	}

	public long adicionarOfertaViviendaExpress (PersistenceManager pm, long id,   int costoContrato, int precioEspecial, String condicionPrecioEspecial, int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionViviendaExpress) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaOferta () + "(ID, FECHA_PUBLICACION_RESERVA,  COSTO_CONTRATO_DIA, PRECIO_ESPECIAL_DIA, CONDICION_PRECIO_ESPECIAL, COSTO_ADICIONAL_SERVICIOS, COSTO_SEGURO_ARRENDAMIENTO, ID_OPERADOR, DIRECCION_VIVIENDA_EXPRESS, VISITAS) values ( ?, CURRENT_DATE, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id,   costoContrato, precioEspecial ,  condicionPrecioEspecial , costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionViviendaExpress, 0 );
        return (long) q.executeUnique();
	}



	public long eliminarOfertaPorId (PersistenceManager pm, long id)
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
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOferta () + " WHERE (DIRECCION_HOSTAL_HABITACION_HOSTAL = ? AND NUMERO_HABITACION_HABITACION_HOSTAL = ?)");
        q.setParameters(direccionHostal, numeroHabitacionHostal);
        return (long) q.executeUnique();
	}

	public long eliminarOfertaPorHabitacionHotel (PersistenceManager pm, String direccionHotel, String numeroHabitacionHotel)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOferta () + " WHERE (DIRECCION_HOTEL_HABITACION_HOTEL = ? AND NUMERO_HABITACION_HABITACION_HOTEL = ?)");
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
	public Oferta darOfertaPorId (PersistenceManager pm, long id)
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

	public List<Object[]> darOfertas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaOferta ());
		return (List<Object[]>) q.executeList();
	}


	
}
