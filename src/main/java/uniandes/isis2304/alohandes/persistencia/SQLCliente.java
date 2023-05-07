package uniandes.isis2304.alohandes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Cliente;

class SQLCliente 
{
	
	private final static String SQL = PersistenciaAlohAndes.SQL;

	private PersistenciaAlohAndes pa;

	public SQLCliente (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	

	public long adicionarCliente (PersistenceManager pm, String id, String tipoId, String nombre, String tipo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pa.darTablaCliente () + "(ID, TIPO_ID, NOMBRE, TIPO) values (?, ?, ?, ?)");
        q.setParameters(id, tipoId, nombre, tipo);
        return (long) q.executeUnique();
	}

	public long eliminarClientePorNombre (PersistenceManager pm, String nombre)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaCliente () + " WHERE NOMBRE = ?");
        q.setParameters(nombre);
        return (long) q.executeUnique();            
	}

	
	public long eliminarClientePorId (PersistenceManager pm, String id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaCliente () + " WHERE ID = ?");
        q.setParameters(id);
        return (long) q.executeUnique();            
	}

	public Cliente darClientePorId (PersistenceManager pm, String id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaCliente () + " WHERE ID = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(id);
		return (Cliente) q.executeUnique();
	}


	public List<Cliente> darClientesPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaCliente () + " WHERE NOMBRE = ?");
		q.setResultClass(Cliente.class);
		q.setParameters(nombre);
		return (List<Cliente>) q.executeList();
	}

	public List<Cliente> darClientes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pa.darTablaCliente ());
		q.setResultClass(Cliente.class);
		return (List<Cliente>) q.executeList();
	}

	public List<Object[]> mostrarUsoAlohAndesClientes (PersistenceManager pm)
	{
	    String sql = "SELECT id, tipo," +
			" DIRECCION_HOSTAL_HABITACION_HOSTAL, NUMERO_HABITACION_HABITACION_HOSTAL," + 
			" DIRECCION_HOTEL_HABITACION_HOTEL, NUMERO_HABITACION_HABITACION_HOTEL, "+
			" DIRECCION_VIVIENDA_UNIVERSITARIA, NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA, "+
			" DIRECCION_VIVIENDA_HABITACION, NUMERO_APARTAMENTO_VIVIENDA_HABITACION, "+
			" DIRECCION_APARTAMENTO, NUMERO_APARTAMENTO, DIRECCION_VIVIENDA_EXPRESS"+
			" FROM"+
			" (SELECT clientes.id, clientes.tipo,"+
			" ofertas.DIRECCION_HOSTAL_HABITACION_HOSTAL, ofertas.NUMERO_HABITACION_HABITACION_HOSTAL, "+
			" ofertas.DIRECCION_HOTEL_HABITACION_HOTEL, ofertas.NUMERO_HABITACION_HABITACION_HOTEL,"+
			" ofertas.DIRECCION_VIVIENDA_UNIVERSITARIA, ofertas.NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA,"+
			" ofertas.DIRECCION_VIVIENDA_HABITACION, ofertas.NUMERO_APARTAMENTO_VIVIENDA_HABITACION,"+
			" ofertas.DIRECCION_APARTAMENTO, ofertas.NUMERO_APARTAMENTO, ofertas.DIRECCION_VIVIENDA_EXPRESS"+
			" FROM"+
			" (SELECT id, "+
			" NVL(DIRECCION_HOSTAL_HABITACION_HOSTAL,'No Aplica') AS DIRECCION_HOSTAL_HABITACION_HOSTAL, NVL(NUMERO_HABITACION_HABITACION_HOSTAL,'No Aplica') AS NUMERO_HABITACION_HABITACION_HOSTAL, "+
			" NVL(DIRECCION_HOTEL_HABITACION_HOTEL,'No Aplica') AS DIRECCION_HOTEL_HABITACION_HOTEL, NVL(NUMERO_HABITACION_HABITACION_HOTEL,'No Aplica') AS NUMERO_HABITACION_HABITACION_HOTEL, "+
			" NVL(DIRECCION_VIVIENDA_UNIVERSITARIA,'No Aplica') AS DIRECCION_VIVIENDA_UNIVERSITARIA, NVL(NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA,'No Aplica') AS NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA, "+
			" NVL(DIRECCION_VIVIENDA_HABITACION,'No Aplica') AS DIRECCION_VIVIENDA_HABITACION, NVL(NUMERO_APARTAMENTO_VIVIENDA_HABITACION,'No Aplica') AS NUMERO_APARTAMENTO_VIVIENDA_HABITACION, "+
			" NVL(DIRECCION_APARTAMENTO,'No Aplica') AS DIRECCION_APARTAMENTO, NVL(NUMERO_APARTAMENTO,'No Aplica') AS NUMERO_APARTAMENTO, "+
			" NVL(DIRECCION_VIVIENDA_EXPRESS,'No Aplica') AS DIRECCION_VIVIENDA_EXPRESS"+
			" FROM oferta) ofertas"+
			" LEFT OUTER JOIN"+
			" (SELECT clientos.id, clientos.nombre, clientos.tipo, reservas.id_oferta"+
			" FROM"+
			" (SELECT id_oferta, id_cliente"+
			" FROM reserva) reservas"+
			" FULL OUTER JOIN"+
		 		" (SELECT id, nombre, tipo"+
				" FROM cliente) clientos"+
				" ON clientos.id = reservas.id_cliente) clientes"+
			" ON clientes.id_oferta = ofertas.id"+
			" ORDER BY clientes.tipo, clientes.id DESC)"+
			" WHERE id IS NOT NULL";
		
	    Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}

	public List<Object[]> mostrarUsoAlohAndesParaUnUsuarioDado (PersistenceManager pm, String idCliente)
	{
	    String sql = "SELECT clientes.id_cliente, precio_especial_tomado,"+
		" (duracion_dias*costo_contrato_dia+costo_adicional_servicios+costo_seguro_arrendamiento) AS precio_total_pagado_normal,"+
		" (duracion_dias*precio_especial_dia+costo_adicional_servicios+costo_seguro_arrendamiento) AS precio_especial_total_pagado_normal,"+
		" duracion_dias AS dias_contratados"+
 		" FROM"+
	 	" (SELECT id_cliente, id_oferta, precio_especial_tomado, fecha_realizacion_reserva, fecha_inicial, duracion_dias"+
	 	" FROM reserva) clientes"+
	 	" LEFT OUTER JOIN"+
		" (SELECT id, costo_contrato_dia, precio_especial_dia, costo_adicional_servicios, costo_seguro_arrendamiento,"+
		" NVL(DIRECCION_HOSTAL_HABITACION_HOSTAL,'No Aplica') AS DIRECCION_HOSTAL_HABITACION_HOSTAL, NVL(NUMERO_HABITACION_HABITACION_HOSTAL,'No Aplica') AS NUMERO_HABITACION_HABITACION_HOSTAL," +
		" NVL(DIRECCION_HOTEL_HABITACION_HOTEL,'No Aplica') AS DIRECCION_HOTEL_HABITACION_HOTEL, NVL(NUMERO_HABITACION_HABITACION_HOTEL,'No Aplica') AS NUMERO_HABITACION_HABITACION_HOTEL,"+
		" NVL(DIRECCION_VIVIENDA_UNIVERSITARIA,'No Aplica') AS DIRECCION_VIVIENDA_UNIVERSITARIA, NVL(NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA,'No Aplica') AS NUMERO_APARTAMENTO_VIVIENDA_UNIVERSITARIA, "+
		" NVL(DIRECCION_VIVIENDA_HABITACION,'No Aplica') AS DIRECCION_VIVIENDA_HABITACION, NVL(NUMERO_APARTAMENTO_VIVIENDA_HABITACION,'No Aplica') AS NUMERO_APARTAMENTO_VIVIENDA_HABITACION, "+
		" NVL(DIRECCION_APARTAMENTO,'No Aplica') AS DIRECCION_APARTAMENTO, NVL(NUMERO_APARTAMENTO,'No Aplica') AS NUMERO_APARTAMENTO," +
		" NVL(DIRECCION_VIVIENDA_EXPRESS,'No Aplica') AS DIRECCION_VIVIENDA_EXPRESS"+
		" FROM oferta) reservas"+
		" ON clientes.id_oferta = reservas.id"+
 		" WHERE clientes.id_cliente = ?";
		
	    Query q = pm.newQuery(SQL, sql);
		q.setParameters(idCliente);
		return q.executeList();
	}

}
