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

	public List<Object[]> ofertaPopular(PersistenceManager pm) {
		String sql = "";
		sql += "SELECT id, visitas, costo_contrato_dia, fecha_publicacion_reserva ";
		sql += "FROM oferta ";
		sql += "ORDER BY visitas DESC ";
		sql += "FETCH FIRST 20 ROWS ONLY ";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();

	}

	public List<Object[]> ofertasDisponibles(PersistenceManager pm){
		String sql = "SELECT ofertas_2.id, ofertas_2.fecha_inicial_reserva, ofertas_2.fecha_finalizacion_reserva "
           + "FROM "
           + "    (SELECT ofertas.id, ofertas.fecha_publicacion_reserva, "
           + "           NVL(reservas.fecha_inicial,ofertas.fecha_publicacion_reserva) AS fecha_inicial_reserva, "
           + "           NVL(reservas.fecha_inicial,ofertas.fecha_publicacion_reserva)+NVL(reservas.duracion_dias,0) AS fecha_finalizacion_reserva "
           + "    FROM "
           + "        ((SELECT id_oferta, fecha_inicial, duracion_dias "
           + "        FROM reserva) reservas "
           + "        FULL OUTER JOIN "
           + "            (SELECT id, fecha_publicacion_reserva, costo_contrato_dia, precio_especial_dia "
           + "            FROM oferta) ofertas "
           + "            ON reservas.id_oferta = ofertas.id)) ofertas_2 "
           + "WHERE (ofertas_2.fecha_inicial_reserva >= to_date('01-01-2021','DD-MM-YYYY')) "
           + "AND (ofertas_2.fecha_finalizacion_reserva <= to_date('01-01-2022','DD-MM-YYYY'))";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}

	public List<Object[]> clientesFrecuentes(String tipo,PersistenceManager pm, String identificador){
		
		String sql = "SELECT reservas.id_cliente, veces_reservado, cantidad_dias_reservados "
				+ "FROM "
				+ "(SELECT id, "
				+ "DIRECCION_HOSTAL_HABITACION_HOSTAL, NUMERO_HABITACION_HABITACION_HOSTAL, "
				+ "DIRECCION_HOTEL_HABITACION_HOTEL, NUMERO_HABITACION_HABITACION_HOTEL, "
				+ "DIRECCION_VIVIENDA_UNIVERSITARIA, NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA, "
				+ "DIRECCION_VIVIENDA_HABITACION, NUMERO_APARTAMENTO_VIVIENDA_HABITACION, "
				+ "DIRECCION_APARTAMENTO, NUMERO_APARTAMENTO, DIRECCION_VIVIENDA_EXPRESS "
				+ "FROM oferta "
				+ "WHERE " + tipo +" = \'"+identificador+"\' ) ofertas "
				+ "RIGHT OUTER JOIN "
				+ "(SELECT tabla2.id_cliente, tabla2.id_oferta, veces_reservado, cantidad_dias_reservados "
				+ "FROM "
				+ "(SELECT id_cliente, id_oferta, COUNT(*) AS veces_reservado "
				+ "FROM reserva "
				+ "GROUP BY id_cliente, id_oferta) tabla1 "
				+ "FULL OUTER JOIN "
				+ "(SELECT id_cliente, id_oferta, SUM(duracion_dias) AS cantidad_dias_reservados "
				+ "FROM reserva "
				+ "GROUP BY id_cliente, id_oferta) tabla2 "
				+ "ON tabla1.id_cliente = tabla2.id_cliente AND tabla1.id_oferta = tabla2.id_oferta "
				+ "WHERE veces_reservado >= 3 OR cantidad_dias_reservados >= 15) reservas "
				+ "ON ofertas.id = reservas.id_oferta";
			Query q = pm.newQuery(SQL, sql);
			return q.executeList();
	}

	public List<Object[]> mostrarIndiceDeOcupacion (PersistenceManager pm)
	{
	    String sql = "SELECT ofertas.id, ROUND((NVL(reservas.dias_ocupada,0))/ofertas.date_difference,2) AS indice_ocupacion" +
		 	" FROM" +
	 		" (SELECT id, ROUND(CURRENT_DATE-fecha_publicacion_reserva,0) AS date_difference" +
	 		" FROM oferta) ofertas" +
	 		" LEFT OUTER JOIN" +
		 	" (SELECT id_oferta, SUM(duracion_dias) AS dias_ocupada" +
		 	" FROM reserva" +
		 	" GROUP BY id_oferta) reservas" +
		 	" ON reservas.id_oferta = ofertas.id" +
			" WHERE ofertas.date_difference <> 0";
		
	    Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}

	public List<Object[]> mostrarOfertasAlojamientoPocaDemanda (PersistenceManager pm)
	{
	    String sql = "SELECT ofertas.id, NVL(cantidad_dias_reservados,0) AS cantidad_dias_reservados,"+
		" DIRECCION_HOSTAL_HABITACION_HOSTAL, NUMERO_HABITACION_HABITACION_HOSTAL, "+
		" DIRECCION_HOTEL_HABITACION_HOTEL, NUMERO_HABITACION_HABITACION_HOTEL,"+
		" DIRECCION_VIVIENDA_UNIVERSITARIA, NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA,"+
		" DIRECCION_VIVIENDA_HABITACION, NUMERO_APARTAMENTO_VIVIENDA_HABITACION,"+
		" DIRECCION_APARTAMENTO, NUMERO_APARTAMENTO, DIRECCION_VIVIENDA_EXPRESS"+
 		" FROM"+
	 	" (SELECT id,"+
		" NVL(DIRECCION_HOSTAL_HABITACION_HOSTAL,'No Aplica') AS DIRECCION_HOSTAL_HABITACION_HOSTAL, NVL(NUMERO_HABITACION_HABITACION_HOSTAL,'No Aplica') AS NUMERO_HABITACION_HABITACION_HOSTAL, "+
		" NVL(DIRECCION_HOTEL_HABITACION_HOTEL,'No Aplica') AS DIRECCION_HOTEL_HABITACION_HOTEL, NVL(NUMERO_HABITACION_HABITACION_HOTEL,'No Aplica') AS NUMERO_HABITACION_HABITACION_HOTEL," +
		" NVL(DIRECCION_VIVIENDA_UNIVERSITARIA,'No Aplica') AS DIRECCION_VIVIENDA_UNIVERSITARIA, NVL(NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA,'No Aplica') AS NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA, "+
		" NVL(DIRECCION_VIVIENDA_HABITACION,'No Aplica') AS DIRECCION_VIVIENDA_HABITACION, NVL(NUMERO_APARTAMENTO_VIVIENDA_HABITACION,'No Aplica') AS NUMERO_APARTAMENTO_VIVIENDA_HABITACION,"+
		" NVL(DIRECCION_APARTAMENTO,'No Aplica') AS DIRECCION_APARTAMENTO, NVL(NUMERO_APARTAMENTO,'No Aplica') AS NUMERO_APARTAMENTO, "+
		" NVL(DIRECCION_VIVIENDA_EXPRESS,'No Aplica') AS DIRECCION_VIVIENDA_EXPRESS"+
	 	" FROM oferta) ofertas"+
	 	" FULL OUTER JOIN"+
		" (SELECT id_oferta, SUM(duracion_dias) AS cantidad_dias_reservados"+
		" FROM reserva"+
		" GROUP BY id_oferta) reservas"+
		" ON ofertas.id = reservas.id_oferta"+
		" WHERE cantidad_dias_reservados <= 30 OR cantidad_dias_reservados IS NULL";
		
	    Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}

	
}
