package uniandes.isis2304.alohandes.persistencia;


import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.alohandes.negocio.*;



public class PersistenciaAlohAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaAlohAndes.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaAlohAndes instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	private List <String> tablas;
	
	private SQLUtil sqlUtil;
	
	private SQLInstalacionHabitacionHotel sqlInstalacionHabitacionHotel;
	
	private SQLHabitacionHotel sqlHabitacionHotel;
	
	private SQLHotel sqlHotel;

	private SQLServicioHabitacionHotel sqlServicioHabitacionHotel;

	private SQLHostal sqlHostal;

	private SQLHabitacionHostal sqlHabitacionHostal;

    private SQLServicioHabitacion sqlServicioHabitacion;

	private SQLViviendaUniversitaria sqlViviendaUniversitaria;

	private SQLViviendaHabitacion sqlViviendaHabitacion;

	private SQLViviendaHabitacionServicioHabitacion sqlViviendaHabitacionServicioHabitacion;

	private SQLServicioHabitacion servicioHabitacion;

	private SQLApartamento sqlApartamento;

	private SQLViviendaExpress sqlViviendaExpress;

	private SQLCliente sqlCliente;

	private SQLOperador sqlOperador;

	private SQLOferta sqlOferta;

    private SQLReserva sqlReserva;

	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaAlohAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("AlohAndes");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("AlohAndes_sequence");
		tablas.add ("INSTALACION_HABITACION_HOTEL");
		tablas.add ("HABITACION_HOTEL");
		tablas.add ("HOTEL");
		tablas.add ("SERVICIO_HABITACION_HOTEL");
		tablas.add ("HOSTAL");
		tablas.add ("HABITACION_HOSTAL");
		tablas.add ("VIVIENDA_UNIVERSITARIA");
		tablas.add ("VIVIENDA_HABITACION");
		tablas.add ("VIVIENDA_HABITACION_SERVICIO_HABITACION");
		tablas.add ("SERVICIO_HABITACION");
		tablas.add ("APARTAMENTO");
		tablas.add ("VIVIENDA_EXPRESS");
		tablas.add ("CLIENTE");
		tablas.add ("OPERADOR");
		tablas.add ("OFERTA");
        tablas.add ("RESERVA");
}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaAlohAndes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaAlohAndes existente - Patrón SINGLETON
	 */
	public static PersistenciaAlohAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohAndes ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaAlohAndes existente - Patrón SINGLETON
	 */
	public static PersistenciaAlohAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohAndes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlInstalacionHabitacionHotel = new SQLInstalacionHabitacionHotel(this);
        sqlApartamento = new SQLApartamento(this);
        sqlCliente = new SQLCliente(this);
        sqlHabitacionHostal = new SQLHabitacionHostal(this);
        sqlHabitacionHotel = new SQLHabitacionHotel(this);
        sqlHostal = new SQLHostal(this);
        sqlHotel = new SQLHotel(this);
        sqlInstalacionHabitacionHotel = new SQLInstalacionHabitacionHotel(this);
        sqlOferta = new SQLOferta(this);
        sqlOperador = new SQLOperador(this);
        sqlServicioHabitacion = new SQLServicioHabitacion(this);
        sqlServicioHabitacionHotel = new SQLServicioHabitacionHotel(this);
        sqlViviendaExpress = new SQLViviendaExpress(this);
        sqlViviendaHabitacion = new SQLViviendaHabitacion(this);
        sqlViviendaHabitacionServicioHabitacion = new SQLViviendaHabitacionServicioHabitacion(this);
        sqlViviendaUniversitaria = new SQLViviendaUniversitaria(this);
		sqlUtil = new SQLUtil(this);
        sqlOferta = new SQLOferta(this);
        sqlReserva = new SQLReserva(this);
	}

    public String getTabla(int index){
        return tablas.get(index);
    }

	public String darSeqAlohAndes()
	{
		return tablas.get (0);
	}

	public String darTablaInstalacionHabitacionHotel ()
	{
		return tablas.get (1);
	}

	public String darTablaHabitacionHotel ()
	{
		return tablas.get (2);
	}

	public String darTablaHotel ()
	{
		return tablas.get (3);
	}

	public String darTablaServicioHabitacionHotel ()
	{
		return tablas.get (4);
	}

	public String darTablaHostal ()
	{
		return tablas.get (5);
	}

	public String darTablaHabitacionHostal ()
	{
		return tablas.get (6);
	}
	
	public String darTablaViviendaUniversitaria ()
	{
		return tablas.get (7);
	}

	public String darTablaViviendaHabitacion ()
	{
		return tablas.get (8);
	}

	public String darTablaViviendaHabitacionServicioHabitacion()
	{
		return tablas.get (9);
	}
	
	public String darTablaServicioHabitacion ()
	{
		return tablas.get (10);
	}

	public String darTablaApartamento ()
	{
		return tablas.get (11);
	}

	public String darTablaViviendaExpress ()
	{
		return tablas.get (12);
	}

	public String darTablaCliente ()
	{
		return tablas.get (13);
	}

	public String darTablaOperador ()
	{
		return tablas.get (14);
	}

	public String darTablaOferta ()
	{
		return tablas.get (15);
	}

    public String darTablaReserva ()
    {
        return tablas.get (16);
    }

	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }

    private long nextVal1()
    {
        long resp = sqlUtil.nextval1 (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }

	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los APARTAMENTOS
	 *****************************************************************/
	public Apartamento adicionarApartamento(String nombre, String numero, int amoblado, int numeroHabitaciones,String direccion, int incluyeServicios, float calificacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlApartamento.adicionarApartamento(pm, nombre, numero, amoblado, numeroHabitaciones, direccion, incluyeServicios, calificacion);
            tx.commit();
            
            log.trace ("Inserción Apartamento: " + direccion + ","+ numero + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Apartamento (nombre, numero, amoblado, numeroHabitaciones, direccion, incluyeServicios, calificacion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarApartamentoPorNombreYNumero (String nombre, String numero) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlApartamento.eliminarApartamentoPorNombreYNumero(pm, nombre, numero);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarApartmentoPorDireccionYNumero (String direccion, String numero) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlApartamento.eliminarApartmentoPorDireccionYNumero(pm, direccion, numero);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

  
	public Apartamento darApartamentoPorDireccionYNumero (String direccion, String numero)
	{
		return sqlApartamento.darApartamentoPorDireccionYNumero (pmf.getPersistenceManager(), direccion, numero);
	}

	public List<Apartamento> darApartamentoPorNombreYNumero (String nombre, String numero)
	{
		return sqlApartamento.darApartamentoPorNombreYNumero (pmf.getPersistenceManager(), nombre, numero);
	}

	public List<Apartamento> darApartamentosPorDireccion (String direccion)
	{
		return sqlApartamento.darApartamentosPorDireccion (pmf.getPersistenceManager(), direccion);
	}

	public List<Apartamento> darApartamento ()
	{
		return sqlApartamento.darApartamento (pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los CLIENTES
	 *****************************************************************/
	public Cliente adicionarCliente(String id, String tipoId, String nombre, String tipo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlCliente.adicionarCliente(pm, id, tipoId, nombre, tipo);
            tx.commit();
            
            log.trace ("Inserción Cliente: " + id + ","+ nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Cliente (id, tipoId, nombre, tipo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarClientePorId (String id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCliente.eliminarClientePorId(pm, id);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarClientePorNombre (String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCliente.eliminarClientePorNombre(pm, nombre);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public Cliente darClientePorId (String id)
	{
		return sqlCliente.darClientePorId (pmf.getPersistenceManager(), id);
	}

	public List<Cliente> darClientesPorNombre (String nombre)
	{
		return sqlCliente.darClientesPorNombre (pmf.getPersistenceManager(), nombre);
	}

	public List<Cliente> darClientes ()
	{
		return sqlCliente.darClientes (pmf.getPersistenceManager());
	}

    public List<String> mostrarUsoAlohAndesClientes()
    {
        List<Object[]> tuplas = null;
        List<String> respuesta = new LinkedList <String> ();
        tuplas = sqlCliente.mostrarUsoAlohAndesClientes(pmf.getPersistenceManager());
       
        for ( Object[] tupla : tuplas)
        {
            String resp = "";
            resp += "id del Cliente: " + tupla[0];
            resp += " tipo de cliente: " + tupla[1];
            resp += " direccion hostal habitacion hostal: " + tupla[2];
            resp += " numero habitacion habitacion hostal: " + tupla[3];
            resp += " direccion hotel habitacion hotel: " + tupla[4];
            resp += " numero habitacion habitacion hotel: " + tupla[5];
            resp += " direccion vivienda universitaria: " + tupla[6];
            resp += " numero apartamento vivienda universitaria: " + tupla[7];
            resp += " direccion vivienda habitacion: " + tupla[8];
            resp += " numero apartamento vivienda habitacion: " + tupla[9];
            resp += " direccion apartamento: " + tupla[10];
            resp += " numero apartamento: " + tupla[11];
            resp += " direccion vivienda express: " + tupla[12];
			respuesta.add(resp);
        }
		return respuesta;
    }

    public List<String> mostrarUsoAlohAndesParaUnUsuarioDado(String idCliente)
    {
        List<Object[]> tuplas = null;
        List<String> respuesta = new LinkedList <String> ();
        tuplas = sqlCliente.mostrarUsoAlohAndesParaUnUsuarioDado(pmf.getPersistenceManager(), idCliente);
       
        for ( Object[] tupla : tuplas)
        {
            String resp = "";
            resp += "id del Cliente: " + tupla[0];
            if( tupla[1].toString().equals("1"))
            {
                resp += " precio total pagado: " + tupla[3];
            }
            else if ( tupla[1].toString().equals("0") )
            {
                resp += " precio total pagado: " + tupla[2];
            }
            resp += " dias contratados: " + tupla[4];
			respuesta.add(resp);
        }
		return respuesta;
    }

	/* ****************************************************************
	 * 			Métodos para manejar los HABITACION HOSTEL
	 *****************************************************************/
	public HabitacionHostal adicionarHabitacionHostal(String direccionHostal, String numeroHabitacion, float tamano) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlHabitacionHostal.adicionarHabitacionHostal(pm, direccionHostal, numeroHabitacion, tamano);
            tx.commit();
            
            log.trace ("Inserción Habitacion Hostal: " + direccionHostal + ","+ numeroHabitacion + ": " + tuplasInsertadas + " tuplas insertadas");
            return new HabitacionHostal (direccionHostal, numeroHabitacion, tamano);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarHabitacionHostalPorDireccionHostal (String direccionHostal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionHostal.eliminarHabitacionHostalPorDireccionHostal(pm, direccionHostal);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarHostalPorDireccionHostalYNumeroHabitacion (String direccionHostal, String numeroHabitacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionHostal.eliminarHostalPorDireccionHostalYNumeroHabitacion(pm, direccionHostal, numeroHabitacion);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public HabitacionHostal darHabitacionHostalPorDireccionHostalYNumeroHabitacion (String direccionHostal, String numeroHabitacion)
	{
		return sqlHabitacionHostal.darHabitacionHostalPorDireccionHostalYNumeroHabitacion (pmf.getPersistenceManager(), direccionHostal, numeroHabitacion);
	}

	public List<HabitacionHostal> darHabitacionesHostalPorDireccionHostal (String direccionHostal)
	{
		return sqlHabitacionHostal.darHabitacionesHostalPorDireccionHostal (pmf.getPersistenceManager(), direccionHostal);
	}

	public List<Object[]> darTodasHabitacionHostal ()
	{
		return sqlHabitacionHostal.darTodasHabitacionHostal (pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar los HABITACION HOTEL
	 *****************************************************************/
	public HabitacionHotel adicionarHabitacionHotel(String tipo, String numerohabitacion, float tamano, String direccionHotel) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlHabitacionHotel.adicionarHabitacionHotel(pm, tipo, numerohabitacion, tamano, direccionHotel);
            tx.commit();
            
            log.trace ("Inserción Habitacion Hotel: " + direccionHotel + ","+ numerohabitacion + ": " + tuplasInsertadas + " tuplas insertadas");
            return new HabitacionHotel (tipo, numerohabitacion, tamano, direccionHotel);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarHabitacionHotelPorDireccionHotel (String direccionHotel) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionHotel.eliminarHabitacionHotelPorDireccionHotel(pm, direccionHotel);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarHabitacionHotelPorNumeroHabitacionYDireccionHotel (String numeroHabitacion, String direccionHotel) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionHotel.eliminarHabitacionHotelPorNumeroHabitacionYDireccionHotel(pm, numeroHabitacion, direccionHotel);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public HabitacionHotel darHabitacionHotelPorNumeroHabitacionYDireccionHotel ( String numeroHabitacion, String direccionHotel)
	{
		return sqlHabitacionHotel.darHabitacionHotelPorNumeroHabitacionYDireccionHotel (pmf.getPersistenceManager(), numeroHabitacion, direccionHotel);
	}

	public List<Object[]> darHabitacionHotelesPorNombre (String nombreHabitacionHotel)
	{
		return sqlHabitacionHotel.darHabitacionHotelesPorDireccionHotel(pmf.getPersistenceManager(), nombreHabitacionHotel);
	}

	public List<Object[]> darHabitacionHoteles ()
	{
		return sqlHabitacionHotel.darHabitacionHoteles (pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar los HOSTAL
	 *****************************************************************/
	public Hostal adicionarHostal(String nombre, String direccion, String horarioApertura, String horarioCierre, float calificacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlHostal.adicionarHostal(pm, nombre, direccion, horarioApertura, horarioCierre, calificacion);
            tx.commit();
            
            log.trace ("Inserción Hostal: " + nombre + ","+ direccion + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Hostal (nombre, direccion, horarioApertura, horarioCierre, calificacion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarHostalPorNombre (String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHostal.eliminarHostalPorNombre(pm, nombre);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarHostalPorDireccion (String direccion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHostal.eliminarHostalPorDireccion(pm, direccion);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public Object[] darHostalesPorDireccion ( String direccion)
	{
		return sqlHostal.darHostalPorDireccion (pmf.getPersistenceManager(), direccion);
	}

	public List<Object[]> darHostalPorNombre (String nombre)
	{
		return sqlHostal.darHostalPorNombre (pmf.getPersistenceManager(), nombre);
	}

	public List<Object[]> darHostales ()
	{
		return sqlHostal.darHostales (pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar los HOTEL
	 *****************************************************************/
	public Hotel adicionarHotel(String nombre, float calificacion, String direccion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlHotel.adicionarHotel(pm, nombre, calificacion, direccion);
            tx.commit();
            
            log.trace ("Inserción Hotel: " + nombre + ","+ direccion + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Hotel (nombre, calificacion, direccion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarHotelPorNombre (String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHotel.eliminarHotelPorNombre(pm, nombre);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	

	public long eliminarHotelPorDireccion (String direccion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHotel.eliminarHotelPorDireccion(pm, direccion);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public Hotel darHotelPorDireccion ( String direccion)
	{
		return sqlHotel.darHotelPorDireccion (pmf.getPersistenceManager(), direccion);
	}

	public List<Object[]> darHotelesPorNombre (String nombre)
	{
		return sqlHotel.darHotelesPorNombre (pmf.getPersistenceManager(), nombre);
	}

	public List<Object[]> darHoteles ()
	{
		return sqlHotel.darHoteles (pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 *     Métodos para manejar los INSTALACION HABITACION HOTEL
	 *****************************************************************/
	public InstalacionHabitacionHotel adicionarInstalacionHabitacionHotel(String tipoInstalacion, String numeroHabitacionHabitacionHotel, String direccionHotelHabitacionHotel) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlInstalacionHabitacionHotel.adicionarInstalacionHabitacionHotel(pm, tipoInstalacion, numeroHabitacionHabitacionHotel, direccionHotelHabitacionHotel);
            tx.commit();
            
            log.trace ("Inserción Instalacion Habitacion hotel: " + tipoInstalacion + ","+ numeroHabitacionHabitacionHotel + ": " + tuplasInsertadas + " tuplas insertadas");
            return new InstalacionHabitacionHotel (tipoInstalacion, numeroHabitacionHabitacionHotel, direccionHotelHabitacionHotel);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarInstalacionHabitacionHotelPorTipoInstalacionYDireccionHotelHabitacionHotel (String tipoInstlacion, String direccionHotelHabitacionHotel )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlInstalacionHabitacionHotel.eliminarInstalacionHabitacionHotelPorTipoInstalacionYDireccionHotelHabitacionHotel(pm,tipoInstlacion, direccionHotelHabitacionHotel);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarInstalacionHabitacionHotelPorDireccionHotelHabitacionHotel (String direccionHotelHabitacionHotel)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlInstalacionHabitacionHotel.eliminarInstalacionHabitacionHotelPorDireccionHotelHabitacionHotel(pm, direccionHotelHabitacionHotel);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarInstalacionHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel (String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlInstalacionHabitacionHotel.eliminarInstalacionHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel(pm, direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public long eliminarInstalacionHabitacionHotelPorTipoInstalacionDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel (String tipoInstalacion, String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel)
    {
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlInstalacionHabitacionHotel.eliminarInstalacionHabitacionHotelPorTipoInstalacionDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel(pm, tipoInstalacion, direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
            log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
    }

    public InstalacionHabitacionHotel darInstalacionHabitacionHotelPorTipoInstalacionDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel (String tipoInstalacion, String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel)
    {
        return sqlInstalacionHabitacionHotel.darInstalacionHabitacionHotelPorTipoInstalacionDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel(pmf.getPersistenceManager(), tipoInstalacion, direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
    }
	public List<InstalacionHabitacionHotel> darInstalacionHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel (String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel) 
	{
		return sqlInstalacionHabitacionHotel.darInstalacionHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel (pmf.getPersistenceManager(), direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
	}

	public List<InstalacionHabitacionHotel> darInstalacionHabitacionHotelPorDireccionHotelHabitacionHotel (String direccionHotelHabitacionHotel)
	{
		return sqlInstalacionHabitacionHotel.darInstalacionHabitacionHotelPorDireccionHotelHabitacionHotel (pmf.getPersistenceManager(), direccionHotelHabitacionHotel);
	}

	public List<InstalacionHabitacionHotel> darTodasInstalacionHabitacionHotel ()
	{
		return sqlInstalacionHabitacionHotel.darTodasInstalacionHabitacionHotel (pmf.getPersistenceManager());
	}
	/* ****************************************************************
	 * 			Métodos para manejar los OFERTA
	 *****************************************************************/
	public Oferta adicionarOfertaHabitacionHostal(  int costoContrato, int precioEspecial, String condicionPrecioEspecial, int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionHostalHabitacionHostal, String numeroHabitacionHabitacionHostal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlOferta.adicionarOfertaHabitacionHostal(pm, id,   costoContrato, precioEspecial ,  condicionPrecioEspecial ,  costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionHostalHabitacionHostal, numeroHabitacionHabitacionHostal);
            tx.commit();

            log.trace ("Inserción de Oferta: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
          
            String direccionHotelHabitacionHotel = "";
            String numeroHabitacionHabitacionHotel = "";
            String direccionViviendaUniversitaria = "";
            String numeroApartamentoViviendaUniversitaria = "";
            String direccionViviendaHabitacion = "";
            String numeroApartamentoViviendaHabitacion = "";
            String direccionApartamento = "";
            String numeroApartamento = "";
            String direccionViviendaExpress = "";
            return new Oferta (id,   costoContrato, precioEspecial ,  condicionPrecioEspecial , costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionHostalHabitacionHostal, numeroHabitacionHabitacionHostal,direccionHotelHabitacionHotel,numeroHabitacionHabitacionHotel,direccionViviendaUniversitaria,numeroApartamentoViviendaUniversitaria,direccionViviendaHabitacion,numeroApartamentoViviendaHabitacion,direccionApartamento,numeroApartamento,direccionViviendaExpress, 0);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public Oferta adicionarOfertaHabitacionHotel(  int costoContrato, int precioEspecial, String condicionPrecioEspecial, int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlOferta.adicionarOfertaHabitacionHotel(pm, id,   costoContrato, precioEspecial ,  condicionPrecioEspecial , costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel );
            tx.commit();

            log.trace ("Inserción de Oferta: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            String direccionHostalHabitacionHostal = "";
            String numeroHabitacionHabitacionHostal = "";
            String direccionViviendaUniversitaria = "";
            String numeroApartamentoViviendaUniversitaria = "";
            String direccionViviendaHabitacion = "";
            String numeroApartamentoViviendaHabitacion = "";
            String direccionApartamento = "";
            String numeroApartamento = "";
            String direccionViviendaExpress = "";
            return new Oferta (id,   costoContrato, precioEspecial ,  condicionPrecioEspecial , costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionHostalHabitacionHostal, numeroHabitacionHabitacionHostal,direccionHotelHabitacionHotel,numeroHabitacionHabitacionHotel,direccionViviendaUniversitaria,numeroApartamentoViviendaUniversitaria,direccionViviendaHabitacion,numeroApartamentoViviendaHabitacion,direccionApartamento,numeroApartamento,direccionViviendaExpress, 0 );
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public Oferta adicionarOfertaViviendaUniversitaria(  int costoContrato, int precioEspecial, String condicionPrecioEspecial, int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionViviendaUniversitaria, String numeroApartamentoViviendaUniversitaria)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlOferta.adicionarOfertaViviendaUniversitaria(pm, id,   costoContrato, precioEspecial ,  condicionPrecioEspecial , costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionViviendaUniversitaria, numeroApartamentoViviendaUniversitaria);
            tx.commit();

            log.trace ("Inserción de Oferta: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            String direccionHostalHabitacionHostal = "";
            String numeroHabitacionHabitacionHostal = "";
            String direccionHotelHabitacionHotel = "";
            String numeroHabitacionHabitacionHotel = "";
            String direccionViviendaHabitacion = "";
            String numeroApartamentoViviendaHabitacion = "";
            String direccionApartamento = "";
            String numeroApartamento = "";
            String direccionViviendaExpress = "";
            return new Oferta (id,   costoContrato, precioEspecial ,  condicionPrecioEspecial , costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionHostalHabitacionHostal, numeroHabitacionHabitacionHostal,direccionHotelHabitacionHotel,numeroHabitacionHabitacionHotel,direccionViviendaUniversitaria,numeroApartamentoViviendaUniversitaria,direccionViviendaHabitacion,numeroApartamentoViviendaHabitacion,direccionApartamento,numeroApartamento,direccionViviendaExpress, 0 );
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public Oferta adicionarOfertaViviendaHabitacion (  int costoContrato, int precioEspecial, String condicionPrecioEspecial,int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionViviendaHabitacion, String numeroApartamentoViviendaHabitacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlOferta.adicionarOfertaViviendaHabitacion(pm, id,   costoContrato, precioEspecial ,  condicionPrecioEspecial , costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionViviendaHabitacion, numeroApartamentoViviendaHabitacion );
            tx.commit();

            log.trace ("Inserción de Oferta: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            String direccionHostalHabitacionHostal = "";
            String numeroHabitacionHabitacionHostal = "";
            String direccionHotelHabitacionHotel = "";
            String numeroHabitacionHabitacionHotel = "";
            String direccionViviendaUniversitaria = "";
            String numeroApartamentoViviendaUniversitaria = "";
            String direccionApartamento = "";
            String numeroApartamento = "";
            String direccionViviendaExpress = "";
            return new Oferta (id,   costoContrato, precioEspecial ,  condicionPrecioEspecial , costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionHostalHabitacionHostal, numeroHabitacionHabitacionHostal,direccionHotelHabitacionHotel,numeroHabitacionHabitacionHotel,direccionViviendaUniversitaria,numeroApartamentoViviendaUniversitaria,direccionViviendaHabitacion,numeroApartamentoViviendaHabitacion,direccionApartamento,numeroApartamento,direccionViviendaExpress, 0 );
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

   

    public Oferta adicionarOfertaApartamento (  int costoContrato, int precioEspecial, String condicionPrecioEspecial, int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionApartamento, String numeroApartamento) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlOferta.adicionarOfertaApartamento(pm, id,   costoContrato, precioEspecial ,  condicionPrecioEspecial , costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionApartamento, numeroApartamento);
            tx.commit();

            log.trace ("Inserción de Oferta: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            String direccionHostalHabitacionHostal = "";
            String numeroHabitacionHabitacionHostal = "";
            String direccionHotelHabitacionHotel = "";
            String numeroHabitacionHabitacionHotel = "";
            String direccionViviendaUniversitaria = "";
            String numeroApartamentoViviendaUniversitaria = "";
            String direccionViviendaHabitacion = "";
            String numeroApartamentoViviendaHabitacion = "";
            String direccionViviendaExpress = "";
            return new Oferta (id,   costoContrato, precioEspecial ,  condicionPrecioEspecial , costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionHostalHabitacionHostal, numeroHabitacionHabitacionHostal,direccionHotelHabitacionHotel,numeroHabitacionHabitacionHotel,direccionViviendaUniversitaria,numeroApartamentoViviendaUniversitaria,direccionViviendaHabitacion,numeroApartamentoViviendaHabitacion,direccionApartamento,numeroApartamento,direccionViviendaExpress, 0 );
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public Oferta adicionarOfertaViviendaExpress (  int costoContrato, int precioEspecial, String condicionPrecioEspecial, int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionViviendaExpress) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval ();
            long tuplasInsertadas = sqlOferta.adicionarOfertaViviendaExpress(pm, id,   costoContrato, precioEspecial ,  condicionPrecioEspecial , costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionViviendaExpress);
            tx.commit();

            log.trace ("Inserción de Oferta: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
            String direccionHostalHabitacionHostal = "";
            String numeroHabitacionHabitacionHostal = "";
            String direccionHotelHabitacionHotel = "";
            String numeroHabitacionHabitacionHotel = "";
            String direccionViviendaUniversitaria = "";
            String numeroApartamentoViviendaUniversitaria = "";
            String direccionViviendaHabitacion = "";
            String numeroApartamentoViviendaHabitacion = "";
            String direccionApartamento = "";
            String numeroApartamento = "";
            return new Oferta (id,   costoContrato, precioEspecial ,  condicionPrecioEspecial , costoAdicionalServicios, costoSeguroArrendamiento, idOperador, direccionHostalHabitacionHostal, numeroHabitacionHabitacionHostal,direccionHotelHabitacionHotel,numeroHabitacionHabitacionHotel,direccionViviendaUniversitaria,numeroApartamentoViviendaUniversitaria,direccionViviendaHabitacion,numeroApartamentoViviendaHabitacion,direccionApartamento,numeroApartamento,direccionViviendaExpress, 0 );
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

   

    public long eliminarOfertaPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOferta.eliminarOfertaPorId(pm, id);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public long eliminarOfertaPorIdOperador (String idOperador) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOferta.eliminarOfertaPorIdOperador(pm, idOperador);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public long eliminarOfertaPorHabitacionHostal ( String direccionHostalHabitacionHostal, String numeroHabitacionHabitacionHostal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOferta.eliminarOfertaPorHabitacionHostal(pm, direccionHostalHabitacionHostal, numeroHabitacionHabitacionHostal);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public long eliminarOfertaPorHabitacionHotel ( String direccionHotel, String numeroHabitacionHotel)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOferta.eliminarOfertaPorHabitacionHotel(pm, direccionHotel, numeroHabitacionHotel);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public long eliminarOfertaPorViviendaUniversitaria (String direccionViviendaUniversitaria, String numeroApartamentoViviendaUniversitaria)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOferta.eliminarOfertaPorViviendaUniversitaria(pm, direccionViviendaUniversitaria, numeroApartamentoViviendaUniversitaria);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public long eliminarOfertaPorViviendaHabitacion (String direccionViviendaHabitacion, String numeroApartamentoViviendaHabitacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOferta.eliminarOfertaPorViviendaHabitacion(pm, direccionViviendaHabitacion, numeroApartamentoViviendaHabitacion);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public long eliminarOfertaPorApartamento (String direccionApartamento, String numeroApartamento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOferta.eliminarOfertaPorViviendaHabitacion(pm, direccionApartamento, numeroApartamento);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public long eliminarOfertaPorViviendaExpress (String direccionViviendaExpress)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOferta.eliminarOfertaPorViviendaExpress(pm, direccionViviendaExpress);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public Oferta darOfertaPorId ( long id)
	{
		return sqlOferta.darOfertaPorId (pmf.getPersistenceManager(), id);
	}

	public List<Oferta> darOfertaPorIdOperador (String idOperador)
	{
		return sqlOferta.darOfertaPorIdOperador (pmf.getPersistenceManager(), idOperador);
	}

    public Oferta darOfertaPorHabitacionHostal (String direccionHostalHabitacionHostal, String numeroHabitacionHabitacionHostal)
	{
		return sqlOferta.darOfertaPorHabitacionHostal (pmf.getPersistenceManager(), direccionHostalHabitacionHostal, numeroHabitacionHabitacionHostal);
	}

    public Oferta darOfertaPorHabitacionHotel (String direccionHotel, String numeroHabitacionHotel)
	{
		return sqlOferta.darOfertaPorHabitacionHotel (pmf.getPersistenceManager(), direccionHotel, numeroHabitacionHotel);
	}

    public Oferta darOfertaPorViviendaUniversitaria (String direccionViviendaUniversitaria, String numeroApartamentoViviendaUniversitaria)
	{
		return sqlOferta.darOfertaPorViviendaUniversitaria (pmf.getPersistenceManager(), direccionViviendaUniversitaria, numeroApartamentoViviendaUniversitaria);
	}

    public Oferta darOfertaPorViviendaHabitacion (String direccionViviendaHabitacion, String numeroApartamentoViviendaHabitacion)
	{
		return sqlOferta.darOfertaPorViviendaHabitacion (pmf.getPersistenceManager(), direccionViviendaHabitacion, numeroApartamentoViviendaHabitacion);
	}

    public Oferta darOfertaPorApartamento (String direccionApartamento, String numeroApartamento)
	{
		return sqlOferta.darOfertaPorApartamento (pmf.getPersistenceManager(), direccionApartamento, numeroApartamento);
	}

    public Oferta darOfertaPorViviendaExpress (String direccionViviendaExpress)
	{
		return sqlOferta.darOfertaPorViviendaExpress (pmf.getPersistenceManager(), direccionViviendaExpress);
	}

    public List<Object[]> darOfertas(){
        return sqlOferta.darOfertas(pmf.getPersistenceManager());
    }

    public List<String> mostrarIndiceDeOcupacion()
    {
        List<Object[]> tuplas = null;
        List<String> respuesta = new LinkedList <String> ();
        tuplas = sqlOferta.mostrarIndiceDeOcupacion(pmf.getPersistenceManager());
       
        for ( Object[] tupla : tuplas)
        {
            String resp = "";
            resp += "id de la Oferta: " + tupla[0];
            resp += " indice de ocupacion de la Oferta: " + tupla[1];
			respuesta.add(resp);
        }
		return respuesta;
    }
    
    public List<String> mostrarOfertasAlojamientoPocaDemanda()
    {
        List<Object[]> tuplas = null;
        List<String> respuesta = new LinkedList <String> ();
        tuplas = sqlOferta.mostrarOfertasAlojamientoPocaDemanda(pmf.getPersistenceManager());
       
        for ( Object[] tupla : tuplas)
        {
            String resp = "";
            resp += "id de la Oferta: " + tupla[0];
            resp += " cantidad de dias reservados: " + tupla[1];
            resp += " direccion hostal habitacion hostal: " + tupla[2];
            resp += " numero habitacion habitacion hostal: " + tupla[3];
            resp += " direccion hotel habitacion hotel: " + tupla[4];
            resp += " numero habitacion habitacion hotel: " + tupla[5];
            resp += " direccion vivienda universitaria: " + tupla[6];
            resp += " numero apartamento vivienda universitaria: " + tupla[7];
            resp += " direccion vivienda habitacion: " + tupla[8];
            resp += " numero apartamento vivienda habitacion: " + tupla[9];
            resp += " direccion apartamento: " + tupla[10];
            resp += " numero apartamento: " + tupla[11];
            resp += " direccion vivienda express: " + tupla[12];

			respuesta.add(resp);
        }
		return respuesta;
    }

    public List<Object[]> darOfertasPopulares(){
        return sqlOferta.ofertaPopular(pmf.getPersistenceManager());
    }

    public List<Object[]> darOfertasDisponibles(){
        return sqlOferta.ofertasDisponibles(pmf.getPersistenceManager());
    }

    public List<Object[]> darClientesFrecuentes(String tipo, String identificador){
        return sqlOferta.clientesFrecuentes(tipo,pmf.getPersistenceManager(), identificador);
    }

	/* ****************************************************************
	 * 			Métodos para manejar los OPERADOR
	 *****************************************************************/

	public Operador adicionarOperadorEmpresa(String id, String nombre, String tipo, Integer validacionCamaraDeComercioEmpresa, Integer validacionSuperTurismoEmpresa, Integer miembroComunidadUniversitariaPersona) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlOperador.adicionarOperador(pm, id, nombre, tipo, validacionCamaraDeComercioEmpresa, validacionSuperTurismoEmpresa, miembroComunidadUniversitariaPersona);
            tx.commit();
            
            log.trace ("Inserción Hotel: " + id + ","+ nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Operador (id, nombre, tipo, validacionCamaraDeComercioEmpresa, validacionSuperTurismoEmpresa, miembroComunidadUniversitariaPersona);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public Operador adicionarOperadorEmpresa(String id, String nombre, String tipo, Integer validacionCamaraDeComercioEmpresa, Integer validacionSuperTurismoEmpresa, Integer miembroComunidadUniversitariaPersona) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlOperador.adicionarOperador(pm, id, nombre, tipo, validacionCamaraDeComercioEmpresa, validacionSuperTurismoEmpresa, miembroComunidadUniversitariaPersona);
            tx.commit();
            
            log.trace ("Inserción Hotel: " + id + ","+ nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Operador (id, nombre, tipo, validacionCamaraDeComercioEmpresa, validacionSuperTurismoEmpresa, miembroComunidadUniversitariaPersona);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarOperadorPorNombre (String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOperador.eliminarOperadorPorNombre(pm, nombre);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarOperadorPorId (String id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOperador.eliminarOperadorPorId(pm, id);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public Object[] darOperadorPorId ( String id)
	{
		return sqlOperador.darOperadorPorId (pmf.getPersistenceManager(), id);
	}

	public List<Object[]> darOperadorPorNombre (String nombre)
	{
		return sqlOperador.darOperadorPorNombre (pmf.getPersistenceManager(), nombre);
	}

	public List<Object[]> darOperadores ()
	{
		return sqlOperador.darOperadores (pmf.getPersistenceManager());
	}

    public List<String> mostrarDineroRecibidoPorCadaOperador()
    {
        List<Object[]> tuplas = null;
        List<String> respuesta = new LinkedList <String> ();
        tuplas = sqlOperador.mostrarDineroRecibidoPorCadaOperadorAnoActual(pmf.getPersistenceManager());
       
        for ( Object[] tupla : tuplas)
        {
            String resp = "";
            resp += "id del Operador: " + tupla[0];
            resp += " nombre del Operador: " + tupla[1];
            resp += " dinero ganado año corrido: " + tupla[2];
            resp += " dinero ganado año actual: " + tupla[3];
			respuesta.add(resp);
        }
		return respuesta;
    }

    public List<String> mostrarUsoAlohAndesOperador()
    {
        List<Object[]> tuplas = null;
        List<String> respuesta = new LinkedList <String> ();
        tuplas = sqlOperador.mostrarUsoAlohAndesOperador(pmf.getPersistenceManager());
       
        for ( Object[] tupla : tuplas)
        {
            String resp = "";
            resp += "id del Operador: " + tupla[0];
            resp += " nombre Operador: " + tupla[1];
            resp += " direccion hostal habitacion hostal: " + tupla[2];
            resp += " numero habitacion habitacion hostal: " + tupla[3];
            resp += " direccion hotel habitacion hotel: " + tupla[4];
            resp += " numero habitacion habitacion hotel: " + tupla[5];
            resp += " direccion vivienda universitaria: " + tupla[6];
            resp += " numero apartamento vivienda universitaria: " + tupla[7];
            resp += " direccion vivienda habitacion: " + tupla[8];
            resp += " numero apartamento vivienda habitacion: " + tupla[9];
            resp += " direccion apartamento: " + tupla[10];
            resp += " numero apartamento: " + tupla[11];
            resp += " direccion vivienda express: " + tupla[12];
			respuesta.add(resp);
        }
		return respuesta;
    }
	/* ****************************************************************
	 * 		  Métodos para manejar los SERVICIO HABITACION
	 *****************************************************************/
    public ServicioHabitacion adicionarServicioHabitacion(String tipo, int precio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlServicioHabitacion.adicionarServicioHabitacion(pm, tipo, precio);
            tx.commit();
            
            log.trace ("Inserción Servicio Habitacion: " + tipo + ": " + tuplasInsertadas + " tuplas insertadas");
            return new ServicioHabitacion(tipo, precio);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public long eliminarServicioHabitacionPorTipo (String tipo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicioHabitacion.eliminarServicioHabitacionPorTipo(pm, tipo);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	public ServicioHabitacion darServicioHabitacionPorTipo (String tipo) 
	{
		return sqlServicioHabitacion.darServicioHabitacionPorTipo (pmf.getPersistenceManager(), tipo);
	}

	public List<ServicioHabitacion> darServicioHabitacionPorPrecio (int precio)
	{
		return sqlServicioHabitacion.darServicioHabitacionPorPrecio (pmf.getPersistenceManager(), precio);
	}

	public List<ServicioHabitacion> darTodosServicioHabitacion ()
	{
		return sqlServicioHabitacion.darTodosServicioHabitacion (pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar los SERVICIO HABITACION HOTEL
	 *****************************************************************/
    public ServicioHabitacionHotel adicionarServicioHabitacionHotel(String tipoServicio, String numeroHabitacionHabitacionHotel, String direccionHotelHabitacionHotel) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlServicioHabitacionHotel.adicionarServicioHabitacionHotel(pm, tipoServicio, numeroHabitacionHabitacionHotel, direccionHotelHabitacionHotel);
            tx.commit();
            
            log.trace ("Inserción Servicio Habitacion Hotel: " + numeroHabitacionHabitacionHotel + ","+ direccionHotelHabitacionHotel + ": " + tuplasInsertadas + " tuplas insertadas");
            return new ServicioHabitacionHotel (tipoServicio, numeroHabitacionHabitacionHotel, direccionHotelHabitacionHotel);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarServicioHabitacionHotelPorDireccionHotelHabitacionHotel (String direccionHotelHabitacionHotel) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicioHabitacionHotel.eliminarServicioHabitacionHotelPorDireccionHotelHabitacionHotel(pm, direccionHotelHabitacionHotel);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarServicioHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel (String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicioHabitacionHotel.eliminarServicioHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel(pm, direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public ServicioHabitacionHotel darServicioHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel ( String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel) 
	{
		return sqlServicioHabitacionHotel.darServicioHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel (pmf.getPersistenceManager(), direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
	}

	public List<ServicioHabitacionHotel> darServicioHabitacionHotelPorDireccionHotelHabitacionHotel (String direccionHotelHabitacionHotel)
	{
		return sqlServicioHabitacionHotel.darServicioHabitacionHotelPorDireccionHotelHabitacionHotel (pmf.getPersistenceManager(), direccionHotelHabitacionHotel);
	}

	public List<ServicioHabitacionHotel> darTodosServicioHabitacionHotel ()
	{
		return sqlServicioHabitacionHotel.darTodosServicioHabitacionHotel (pmf.getPersistenceManager());
	}
    /* ****************************************************************
	 * 		Métodos para manejar los VIVIENDA EXPRESS
	 *****************************************************************/
	public ViviendaExpress adicionarViviendaExpress(String nombre, String direccion, int numeroHabitaciones, String menaje, float calificacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlViviendaExpress.adicionarViviendaExpress(pm, nombre, direccion, numeroHabitaciones, menaje, calificacion);
            tx.commit();
            
            log.trace ("Inserción Vivienda Express: " + nombre + ","+ direccion + ": " + tuplasInsertadas + " tuplas insertadas");
            return new ViviendaExpress (nombre, direccion, numeroHabitaciones, menaje, calificacion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarVivendaExpressPorDireccion (String direccion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlViviendaExpress.eliminarVivendaExpressPorDireccion(pm, direccion);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public long eliminarVivendaExpressPorNombre (String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlViviendaExpress.eliminarVivendaExpressPorNombre(pm, nombre);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public ViviendaExpress darViviendaExpressPorDireccion ( String direccion) 
	{
		return sqlViviendaExpress.darViviendaExpressPorDireccion (pmf.getPersistenceManager(), direccion);
	}

	public List<ViviendaExpress> darViviendaExpressPorNombre (String nombre)
	{
		return sqlViviendaExpress.darViviendaExpressPorNombre (pmf.getPersistenceManager(), nombre);
	}

	public List<ViviendaExpress> darTodasViviendaExpress ()
	{
		return sqlViviendaExpress.darTodasViviendaExpress (pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar los VIVIENDA HABITACION
	 *****************************************************************/
    public ViviendaHabitacion adiccionarViviendaHabitacion(String nombre, String direccion, String numeroApartamento, float calificacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlViviendaHabitacion.adiccionarViviendaHabitacion(pm, nombre, direccion, numeroApartamento, calificacion);
            tx.commit();
            
            log.trace ("Inserción Vivienda Express: " + nombre + ","+ direccion + ": " + tuplasInsertadas + " tuplas insertadas");
            return new ViviendaHabitacion (nombre, direccion, numeroApartamento, calificacion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarViviendaHabitacionPorDireccion (String direccion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlViviendaHabitacion.eliminarViviendaHabitacionPorDireccion(pm, direccion);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public ViviendaHabitacion darViviendaHabitacionPorDireccion ( String direccion) 
	{
		return sqlViviendaHabitacion.darViviendaHabitacionPorDireccion (pmf.getPersistenceManager(), direccion);
	}

	public List<ViviendaHabitacion> darTodasViviendaHabitacion ()
	{
		return sqlViviendaHabitacion.darTodasViviendaHabitacion (pmf.getPersistenceManager());
	}

	/* ******************************************************************
	 * Métodos para manejar los VIVIENDA HABITACION SERVICIO HABITACION
	 ********************************************************************/
    public ViviendaHabitacionServicioHabitacion adiccionarViviendaHabitacionServicioHabitacion(String direccionViviendaHabitacion, String numeroViviendaHabitacion, String tipo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlViviendaHabitacionServicioHabitacion.adiccionarViviendaHabitacionServicioHabitacion(pm, direccionViviendaHabitacion, numeroViviendaHabitacion, tipo);
            tx.commit();
            
            log.trace ("Inserción Vivienda Express: " + direccionViviendaHabitacion + ","+ numeroViviendaHabitacion + ": " + tuplasInsertadas + " tuplas insertadas");
            return new ViviendaHabitacionServicioHabitacion (direccionViviendaHabitacion, numeroViviendaHabitacion, tipo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarViviendaHabitacionServicioHabitacionPorDirecionNombreYTipo (String direccionViviendaHabitacion, String numeroViviendaHabitacion, String tipo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlViviendaHabitacionServicioHabitacion.eliminarViviendaHabitacionServicioHabitacionPorDirecionNombreYTipo(pm, direccionViviendaHabitacion, numeroViviendaHabitacion, tipo);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public long eliminarViviendaHabitacionServicioHabitacionPorDirecionYNumeroViviendaHabitacion (String direccionViviendaHabitacion, String numeroViviendaHabitacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlViviendaHabitacionServicioHabitacion.eliminarViviendaHabitacionServicioHabitacionPorDirecionYNumeroViviendaHabitacion(pm, direccionViviendaHabitacion, numeroViviendaHabitacion);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public long eliminarViviendaHabitacionServicioHabitacionPorTipo (String tipo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlViviendaHabitacionServicioHabitacion.eliminarViviendaHabitacionServicioHabitacionPorTipo(pm, tipo);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public ViviendaHabitacionServicioHabitacion darViviendaHabitacionServicioPorDireccionNombreYTipo (String direccionViviendaHabitacion, String numeroViviendaHabitacion, String tipo)
	{
		return sqlViviendaHabitacionServicioHabitacion.darViviendaHabitacionServicioPorDireccionNombreYTipo (pmf.getPersistenceManager(), direccionViviendaHabitacion, numeroViviendaHabitacion, tipo);
	}

    public List<ViviendaHabitacionServicioHabitacion> darViviendaHabitacionServicioPorNumero (String numeroViviendaHabitacion)
	{
		return sqlViviendaHabitacionServicioHabitacion.darViviendaHabitacionServicioPorNumero (pmf.getPersistenceManager(), numeroViviendaHabitacion);
	}

    public List<ViviendaHabitacionServicioHabitacion> darViviendaHabitacionServicioPorDireccion (String direccionViviendaHabitacion)
	{
		return sqlViviendaHabitacionServicioHabitacion.darViviendaHabitacionServicioPorDireccion (pmf.getPersistenceManager(), direccionViviendaHabitacion);
	}

    public List<ViviendaHabitacionServicioHabitacion> darViviendaHabitacionServicioPorTipo (String tipo)
	{
		return sqlViviendaHabitacionServicioHabitacion.darViviendaHabitacionServicioPorTipo (pmf.getPersistenceManager(), tipo);
	}

	public List<ViviendaHabitacionServicioHabitacion> darTodasViviendaHabitacionServicio ()
	{
		return sqlViviendaHabitacionServicioHabitacion.darTodasViviendaHabitacionServicio (pmf.getPersistenceManager());
	}
	/* ****************************************************************
	 * 			Métodos para manejar los VIVIENDA UNIVERSITARIA
	 *****************************************************************/
	public ViviendaUniversitaria adiccionarViviendaUniversitaria(String nombre, String direccion, String numeroApartamento, int viviendaCompartida, int capacidad, String menaje, float calificacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlViviendaUniversitaria.adiccionarViviendaUniversitaria(pm, nombre, direccion, numeroApartamento, viviendaCompartida, capacidad, menaje, calificacion);
            tx.commit();
            
            log.trace ("Inserción Vivienda Express: " + direccion + ","+ numeroApartamento + ": " + tuplasInsertadas + " tuplas insertadas");
            return new ViviendaUniversitaria (nombre, direccion, numeroApartamento, viviendaCompartida, capacidad, menaje, calificacion);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarViviendaUniversitariaPorDireccionYNumero (String direccion, String numeroApartamento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlViviendaUniversitaria.eliminarViviendaUniversitariaPorDireccionYNumero(pm, direccion, numeroApartamento);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public long eliminarViviendaUniversitariaPorDireccion (String direccion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlViviendaUniversitaria.eliminarViviendaUniversitariaPorDireccion(pm, direccion);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public long eliminarViviendaUniversitariaPorNumeroApto (String numeroApartamento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlViviendaUniversitaria.eliminarViviendaUniversitariaPorNumeroApto(pm, numeroApartamento);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public ViviendaUniversitaria darViviendaUniversitariaPorDireccionYNumero ( String direccion, String numeroApartamento) 
	{
		return sqlViviendaUniversitaria.darViviendaUniversitariaPorDireccionYNumero (pmf.getPersistenceManager(), direccion, numeroApartamento);
	}

    public List<ViviendaUniversitaria> darViviendaUniversitariaPorDireccion (String direccion)
	{
		return sqlViviendaUniversitaria.darViviendaUniversitariaPorDireccion (pmf.getPersistenceManager(), direccion);
	}

    public List<ViviendaUniversitaria> darViviendaUniversitariaPorNumero (String numeroApartamento)
	{
		return sqlViviendaUniversitaria.darViviendaUniversitariaPorNumero (pmf.getPersistenceManager(), numeroApartamento);
	}

	public List<ViviendaUniversitaria> darTodasViviendasUniversitaria ()
	{
		return sqlViviendaUniversitaria.darTodasViviendasUniversitaria (pmf.getPersistenceManager());
	}
	
    /* ****************************************************************
	 * 			Métodos para manejar las RESERVA
	 *****************************************************************/
    public Reserva adicionarReserva(String idCliente, String idOferta, int reservaColectiva, Date fechaInicial, int precioEspecialTomado, int duracionDias)
	{
        
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin(); 
            long id = nextVal1 ();           
            long tuplasInsertadas = sqlReserva.adicionarReserva(pm, id, idCliente, idOferta, precioEspecialTomado, reservaColectiva, fechaInicial, duracionDias);
            tx.commit();
            
            log.trace ("Inserción Resrva: "+ id + ","+ idCliente + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Reserva (id, idCliente, precioEspecialTomado);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarReservaPorIdOferta (long idOferta)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlReserva.eliminarReservaPorIdOferta(pm, idOferta);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

    public long eliminarReservaPorIdCliente (String idCliente)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlReserva.eliminarReservaPorIdCliente(pm, idCliente);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public Reserva darReservaPorIdOferta (long idOferta) 
	{
		return sqlReserva.darReservaPorIdOferta(pmf.getPersistenceManager(), idOferta);
	}

    public List<Object[]> darReservasPorIdCliente (String idCliente)
	{
		return sqlReserva.darReservasPorIdCliente(pmf.getPersistenceManager(), idCliente);
	}

	public List<Object[]> darReservas ()
	{
		return sqlReserva.darReservas(pmf.getPersistenceManager());
	}

    public long eliminarReservaPorIdReserva(long idReserva){
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlReserva.eliminarReservaPorIdReserva(pm, idReserva);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
            log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
    }

    public Reserva deshabilitarOferta(long idReserva, String idOferta, Date fechaInicial, int duracionDias) 
	{
        
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin(); 
            long id = nextVal1 ();           
            long tuplasInsertadas = sqlReserva.deshabilitarOferta(pm, idReserva,idOferta, fechaInicial, duracionDias);
            tx.commit();
            
            log.trace ("Inserción Reserva: "+ id + ","+ idOferta + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Reserva (id, "0", 0);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
}


