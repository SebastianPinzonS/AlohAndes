package uniandes.isis2304.alohandes.negocio;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import uniandes.isis2304.alohandes.persistencia.PersistenciaAlohAndes;

public class AlohAndes 
{

	private static Logger log = Logger.getLogger(AlohAndes.class.getName());
	

	private PersistenciaAlohAndes pa;
	

	public AlohAndes ()
	{
		pa = PersistenciaAlohAndes.getInstance ();
	}

	public AlohAndes (JsonObject tableConfig)
	{
		pa = PersistenciaAlohAndes.getInstance (tableConfig);
	}
	
	
	public void cerrarUnidadPersistencia ()
	{
		pa.cerrarUnidadPersistencia ();
	}

	public void getTablas(int index)
	{
		log.info(pa.getTabla(index));
	}


	/* ****************************************************************
	 * 			Métodos para manejar las APARTAMENTO
	 *****************************************************************/

	public Apartamento adicionarApartamento (String nombre, String numero, int amoblado, int numeroHabitaciones,String direccion, int incluyeServicios, float calificacion)
	{
        log.info ("Adicionando Apartamento numero: " + numero + " en la direccion: " + direccion);
        Apartamento apartamento = pa.adicionarApartamento(nombre, numero, amoblado, numeroHabitaciones, direccion, incluyeServicios, calificacion);		
        log.info ("Adicionando Apartamento: " + apartamento);
        return apartamento;
	}
	

	public long eliminarApartamentoPorNombreYNumero (String nombre, String numero)
	{
		log.info ("Eliminando Apartamento numero: " + numero + " del edificio: " + nombre);
        long resp = pa.eliminarApartamentoPorNombreYNumero (nombre, numero);		
        log.info ("Eliminando Apartamento: " + resp + " tuplas eliminadas");
        return resp;
	}

	public long eliminarApartmentoPorDireccionYNumero (String direccion, String numero)
	{
		log.info ("Eliminando Apartamento numero: " + numero + " en la direccion: " + direccion);
        long resp = pa.eliminarApartmentoPorDireccionYNumero (direccion, numero);		
        log.info ("Eliminando Apartamento: " + resp + " tuplas eliminadas");
        return resp;
	}
	
	public Apartamento darApartamentoPorDireccionYNumero (String direccion, String numero)
	{
		log.info ("Consultando Apartamento");
        Apartamento apartamento = pa.darApartamentoPorDireccionYNumero (direccion, numero);	
		log.info ("Buscando Apartamento: " + direccion + " " + numero != null ? apartamento : "NO EXISTE");
        return apartamento;
	}

	public List<VOApartamento> darVOApartamentos ()
	{
		log.info ("Generando los VO de Apartamento");        
        List<VOApartamento> voApartamentos = new LinkedList<VOApartamento> ();
        List<Apartamento> apartamentos = pa.darApartamento ();
        for (Apartamento ap : apartamentos )
        {
        	voApartamentos.add (ap);
        }
        log.info ("Generando los VO de Tipos de Apartamento: " + voApartamentos.size() + " existentes");
        return voApartamentos;
	}

	public List<Apartamento> darApartamentoPorNombreYNumero (String nombre, String numero)
	{
		log.info ("Consultando Apartamentos");
        List<Apartamento> apartamentos = pa.darApartamentoPorNombreYNumero (nombre, numero);	
		log.info ("Buscando Apartamentos: " + nombre + " " + numero != null ? apartamentos : "NO EXISTE");
        return apartamentos;
	}

	public List<Apartamento> darApartamentosPorDireccion (String direccion)
	{
		log.info ("Consultando Apartamentos");
        List<Apartamento> apartamentos = pa.darApartamentosPorDireccion (direccion);	
		log.info ("Buscando Apartamentos: " + direccion != null ? apartamentos : "NO EXISTE");
        return apartamentos;
	}

	public List<Apartamento> darApartamento ()
	{
		log.info ("Consultando Apartamentos");
        List<Apartamento> apartamentos = pa.darApartamento ();	
		log.info ("Buscando Apartamentos: "  != null ? apartamentos : "NO EXISTE");
        return apartamentos;
	}
	/* ****************************************************************
	 * 			Métodos para manejar las Hotel
	 *****************************************************************/

	public Hotel adicionarHotel (String nombre, float calificacion, String direccion)
	{
        log.info ("Adicionando Hotel de nombre: " + nombre + " en la direccion: " + direccion);
        Hotel Hotel = pa.adicionarHotel(nombre, calificacion, direccion);		
        log.info ("Adicionando Hotel: " + Hotel);
        return Hotel;
	}
	

	public long eliminarHotelPorDireccion (String direccion)
	{
		log.info ("Eliminando Hotel en direccion: " + direccion);
        long resp = pa.eliminarHotelPorDireccion (direccion);		
        log.info ("Eliminando Hotel: " + resp + " tuplas eliminadas");
        return resp;
	}

	public long eliminarHotelPorNombre (String nombre)
	{
		log.info ("Eliminando Hotel con nombre: " + nombre );
        long resp = pa.eliminarHotelPorNombre (nombre);		
        log.info ("Eliminando Hotel: " + resp + " tuplas eliminadas");
        return resp;
	}
	

	public List<Object[]> darHotelesPorNombre (String nombre)
	{
		 log.info ("Consultando Hotel");
		 List<Object[]> hoteles = pa.darHotelesPorNombre(nombre);
		 log.info ("Buscando Hotels: " + nombre != null ? hoteles : "NO EXISTE");
		 return hoteles;
	}

	public Hotel darHotelPorDireccion (String direccion)
	{
		log.info ("Consultando Hotels");
        Hotel hotel = pa.darHotelPorDireccion (direccion);	
		log.info ("Buscando Hotel: " + direccion  != null ? hotel : "NO EXISTE");
        return hotel;
	}

	public List<Object[]> listarHoteles ()
	{
		log.info ("Consultando Hotels");
        List<Object[]> Hotels = pa.darHoteles ();	
		log.info ("Buscando Hotels: "  != null ? Hotels : "NO EXISTE");
        return Hotels;
	}


	/* ****************************************************************
	 * 			Métodos para manejar los CLIENTE
	 *****************************************************************/

	 public Cliente adicionarCliente (String id, String tipoId, String nombre, String tipo)
	 {
		 log.info ("Adicionando Cliente id: " + id + "con el nombre: " + nombre);
		 Cliente cliente = pa.adicionarCliente(id, tipoId, nombre, tipo);		
		 log.info ("Adicionando Cliente: " + cliente);
		 return cliente;
	 }
	 
 
	 public long eliminarClientePorNombre  (String nombre)
	 {
		 log.info ("Eliminando cliente con el nombre: " + nombre);
		 long resp = pa.eliminarClientePorNombre (nombre);		
		 log.info ("Eliminando Cliente: " + resp + " tuplas eliminadas");
		 return resp;
	 }
 
	 public long eliminarClientePorId  (String id)
	 {
		log.info ("Eliminando cliente con el id: " + id);
		long resp = pa.eliminarClientePorId (id);		
		log.info ("Eliminando Cliente: " + resp + " tuplas eliminadas");
		return resp;
	 }
	 
	 public Cliente darClientePorId  (String id)
	 {
		 log.info ("Consultando Cliente");
		 Cliente cliente = pa.darClientePorId (id);	
		 log.info ("Buscando Cliente: " + id  != null ? cliente : "NO EXISTE");
		 return cliente;
	 }
 
	 public List<VOCliente> darVOClientes ()
	 {
		 log.info ("Generando los VO de Cliente");        
		 List<VOCliente> voClientes = new LinkedList<VOCliente> ();
		 List<Cliente> clientes = pa.darClientes();
		 for (Cliente cl : clientes )
		 {
			voClientes.add (cl);
		 }
		 log.info ("Generando los VO de Tipos de Cliente: " + voClientes.size() + " existentes");
		 return voClientes;
	 }
 
	 public List<Cliente> darClientesPorNombre  (String nombre)
	 {
		 log.info ("Consultando Clientes");
		 List<Cliente> clientes = pa.darClientesPorNombre (nombre);	
		 log.info ("Buscando Cliente: " + nombre  != null ? clientes : "NO EXISTE");
		 return clientes;
	 }
 
	 public List<Cliente> darClientes ()
	 {
		 log.info ("Consultando Clientes");
		 List<Cliente> clientes = pa.darClientes  ();	
		 log.info ("Buscando Clientes: "  != null ? clientes : "NO EXISTE");
		 return clientes;
	 }

	 public List<String> mostrarUsoAlohAndesClientes()
	 {
		log.info ("Consultando el uso de AlohAndes por clientes");
		List<String> respuesta = pa.mostrarUsoAlohAndesClientes();
		return respuesta;

	 }

	 public List<String> mostrarUsoAlohAndesParaUnUsuarioDado(String idCliente)
	 {
		log.info ("Consultando el uso de AlohAndes para un usuario dado");
		List<String> respuesta = pa.mostrarUsoAlohAndesParaUnUsuarioDado(idCliente);
		return respuesta;

	 }

	/* ****************************************************************
	 * 			Métodos para manejar los OPERADOR
	 *****************************************************************/

	 public Operador adicionarOperador (String id, String nombre, String tipo, int validacionCamaraDeComercioEmpresa, int validacionSuperTurismoEmpresa, int miembroComunidadUniversitariaPersona) 
	 {
		 log.info ("Adicionando Operador id: " + id + " con el nombre: " + nombre);
		 Operador operador = pa.adicionarOperador(id, nombre, tipo, validacionCamaraDeComercioEmpresa, validacionSuperTurismoEmpresa, miembroComunidadUniversitariaPersona);		
		 log.info ("Adicionando Operador: " + operador);
		 return operador;
	 }
	 
 
	 public long eliminarOperadorPorNombre (String nombre)
	 {
		 log.info ("Eliminando operador con el nombre: " + nombre);
		 long resp = pa.eliminarOperadorPorNombre (nombre);		
		 log.info ("Eliminando Operador: " + resp + " tuplas eliminadas");
		 return resp;
	 }
 
	 public long eliminarOperadorPorId (String id)
	 {
		log.info ("Eliminando operador con el id: " + id);
		long resp = pa.eliminarOperadorPorId (id);		
		log.info ("Eliminando Operador: " + resp + " tuplas eliminadas");
		return resp;
	 }

	 public Object[] darOperadorPorId (String id)
	 {
		 log.info ("Consultando Operador");
		 Object[] operador = pa.darOperadorPorId (id);	
		 log.info ("Buscando Operador: " + id  != null ? operador : "NO EXISTE");
		 return operador;
	 }
 
 
	 public List<Object[]> darOperadorPorNombre (String nombre)
	 {
		 log.info ("Consultando Operadores");
		 List<Object[]> operadores = pa.darOperadorPorNombre (nombre);	
		 log.info ("Buscando Operador: " + nombre  != null ? operadores : "NO EXISTE");
		 return operadores;
	 }
 
	 public List<Object[]> darOperadores ()
	 {
		 log.info ("Consultando Operadores");
		 List<Object[]> operadores = pa.darOperadores();	
		 log.info ("Buscando Operadores: "  != null ? operadores : "NO EXISTE");
		 return operadores;
	 }

	 public List<String> mostrarDineroRecibidoPorCadaOperador()
	 {
		log.info ("Consultando Dinero por Operador");
		List<String> respuesta = pa.mostrarDineroRecibidoPorCadaOperador();
		return respuesta;

	 }

	 public List<String> mostrarUsoAlohAndesOperador()
	 {
		log.info ("Consultando el uso de AlohAndes por operadores");
		List<String> respuesta = pa.mostrarUsoAlohAndesOperador();
		return respuesta;

	 }

	/* ****************************************************************
	 * 			Métodos para manejar los Oferta
	 *****************************************************************/

	public Oferta adicionarOfertaHabitacionHostal (  int costoContrato, int precioEspecial, String condicionPrecioEspecial,  int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionHostalHabitacionHostal, String numeroHabitacionHabitacionHostal)
	{
        log.info ("Adicionando Oferta Habitacion Hostal");
        Oferta oferta = pa.adicionarOfertaHabitacionHostal( costoContrato, precioEspecial, condicionPrecioEspecial, costoAdicionalServicios, costoSeguroArrendamiento, idOperador,direccionHostalHabitacionHostal, numeroHabitacionHabitacionHostal);		
        log.info ("Adicionando Oferta: " + oferta);
        return oferta;
	}

	public Oferta adicionarOfertaHabitacionHotel (  int costoContrato, int precioEspecial, String condicionPrecioEspecial,  int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel)
	{
        log.info ("Adicionando Oferta Habitacion Hotel");
        Oferta oferta = pa.adicionarOfertaHabitacionHotel( costoContrato, precioEspecial, condicionPrecioEspecial, costoAdicionalServicios, costoSeguroArrendamiento, idOperador,direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);		
        log.info ("Adicionando Oferta: " + oferta);
        return oferta;
	}

	public Oferta adicionarOfertaViviendaUniversitaria (  int costoContrato, int precioEspecial, String condicionPrecioEspecial,  int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionViviendaUniversitaria, String numeroApartamentoViviendaUniversitaria)
	{
        log.info ("Adicionando Oferta Vivienda Universitaria");
        Oferta oferta = pa.adicionarOfertaViviendaUniversitaria( costoContrato, precioEspecial, condicionPrecioEspecial, costoAdicionalServicios, costoSeguroArrendamiento, idOperador,direccionViviendaUniversitaria, numeroApartamentoViviendaUniversitaria);		
        log.info ("Adicionando Oferta: " + oferta);
        return oferta;
	}

	public Oferta adicionarOfertaViviendaHabitacion (  int costoContrato, int precioEspecial, String condicionPrecioEspecial,  int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionViviendaHabitacion, String numeroApartamentoViviendaHabitacion)
	{
        log.info ("Adicionando Oferta Vivienda Habitacion");
        Oferta oferta = pa.adicionarOfertaHabitacionHostal( costoContrato, precioEspecial, condicionPrecioEspecial, costoAdicionalServicios, costoSeguroArrendamiento, idOperador,direccionViviendaHabitacion, numeroApartamentoViviendaHabitacion);		
        log.info ("Adicionando Oferta: " + oferta);
        return oferta;
	}

	public Oferta adicionarOfertaApartamento (  int costoContrato, int precioEspecial, String condicionPrecioEspecial,  int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionApartamento, String numeroApartamento)
	{
        log.info ("Adicionando Oferta Apartamento");
        Oferta oferta = pa.adicionarOfertaApartamento( costoContrato, precioEspecial, condicionPrecioEspecial, costoAdicionalServicios, costoSeguroArrendamiento, idOperador,direccionApartamento, numeroApartamento);		
        log.info ("Adicionando Oferta: " + oferta);
        return oferta;
	}

	public Oferta adicionarOfertaViviendaExpress (  int costoContrato, int precioEspecial, String condicionPrecioEspecial,  int costoAdicionalServicios, int costoSeguroArrendamiento, String idOperador, String direccionViviendaExpress)
	{
        log.info ("Adicionando Vivienda Express");
        Oferta oferta = pa.adicionarOfertaViviendaExpress( costoContrato, precioEspecial, condicionPrecioEspecial, costoAdicionalServicios, costoSeguroArrendamiento, idOperador,direccionViviendaExpress);		
        log.info ("Adicionando Oferta: " + oferta);
        return oferta;
	}

	public long eliminarOfertaPorId(long id)
	{
		return pa.eliminarOfertaPorId(id);
	}

	public List<Object[]> darOfertas(){
		return pa.darOfertas();
	}

	public List<String> mostrarIndiceDeOcupacion()
	 {
		log.info ("Consultando los indices de ocupacion por Ofertas");
		List<String> respuesta = pa.mostrarIndiceDeOcupacion();
		return respuesta;

	 }
	 
	 public List<String> mostrarOfertasAlojamientoPocaDemanda()
	 {
		log.info ("Consultando las ofertas con poca demanda");
		List<String> respuesta = pa.mostrarOfertasAlojamientoPocaDemanda();
		return respuesta;

	 }

	public List<Object[]> darOfertasPopulares(){
		return pa.darOfertasPopulares();
	}

	public List<Object[]> darOfertasDisponibles(){
		return pa.darOfertasDisponibles();
	}

	public List<Object[]> darClientesFrecuentes(String tipo, String identificador){
		return pa.darClientesFrecuentes(tipo, identificador);
	}


	/* ****************************************************************
	 * 			Métodos para manejar las HABITACION HOTEL
	 *****************************************************************/

	 public HabitacionHotel adicionarHabitacionHotel (String tipo, String numeroHabitacion, int tamano, String direccionHotel) 
	 {
		 log.info ("Adicionando Habitacion Hotel");
		 HabitacionHotel habitacionHotel = pa.adicionarHabitacionHotel(tipo, numeroHabitacion, tamano, direccionHotel);		
		 log.info ("Adicionando Hanitacion Hotel: " + habitacionHotel);
		 return habitacionHotel;
	 }

	 public long eliminarHabitacionHotelPorDireccionHotel (String direccionHotel)
	 {
		 log.info ("Eliminando habitaciones hotel con la direccion: " + direccionHotel);
		 long resp = pa.eliminarHabitacionHotelPorDireccionHotel (direccionHotel);		
		 log.info ("Eliminando Habitacion Hotel: " + resp + " tuplas eliminadas");
		 return resp;
	 }
 
	 public long eliminarHabitacionHotelPorDireccionYNumero (String numeroHabitacion, String direccionHotel)
	 {
		log.info ("Eliminando habitacion hotel con la direccion: " + direccionHotel + " y numero: " + numeroHabitacion);
		long resp = pa.eliminarHabitacionHotelPorNumeroHabitacionYDireccionHotel(numeroHabitacion, direccionHotel);		
		log.info ("Eliminando Habitacion Hotel: " + resp + " tuplas eliminadas");
		return resp;
	 }
	 
	 public HabitacionHotel darHabitacionHotelPorNumeroHabitacionYDireccionHotel (String numero, String direccion)
	{
		log.info ("Consultando Habitacion Hotel");
        HabitacionHotel habitacionHotel = pa.darHabitacionHotelPorNumeroHabitacionYDireccionHotel(numero, direccion);	
		log.info ("Buscando Habtiacion Hotel con direccion: " + direccion + " y numero habitacion " + numero != null ? habitacionHotel : "NO EXISTE");
        return habitacionHotel;
	}

	
	public List<Object[]> darHabitacionHotelesPorDireccion (String direccion)
	{
		log.info ("Consultando Habitacion Hotel");
        List<Object[]> habitacionHotel = pa.darHabitacionHotelesPorNombre(direccion);	
		log.info ("Buscando Habitacion Hotel: " + direccion != null ? habitacionHotel : "NO EXISTE");
        return habitacionHotel;
	}

	public List<Object[]> darHabitacionesHotel ()
	{
		log.info ("Consultando Habitacion Hotel");
        List<Object[]> habitacionHotel = pa.darHabitacionHoteles();	
		log.info ("Buscando Habitacion Hotel: "  != null ? habitacionHotel : "NO EXISTE");
        return habitacionHotel;
	}

	/* ****************************************************************
	 * 			Métodos para manejar las HABITACION HOSTAL
	 *****************************************************************/

	 public HabitacionHostal adicionarHabitacionHostal(String direccionHostal, String numeroHabitacion, float tamano)  
	 {
		 log.info ("Adicionando Habitacion Hostal");
		 HabitacionHostal habitacionHostal = pa.adicionarHabitacionHostal(direccionHostal, numeroHabitacion, tamano);		
		 log.info ("Adicionando Habitacion Hostal: " + habitacionHostal);
		 return habitacionHostal;
	 }

	 public long eliminarHabitacionHostalPorDireccionHostal (String direccionHostal)
	 {
		 log.info ("Eliminando habitaciones hostal con la direccion: " + direccionHostal);
		 long resp = pa.eliminarHabitacionHostalPorDireccionHostal (direccionHostal);		
		 log.info ("Eliminando Habitacion Hostal: " + resp + " tuplas eliminadas");
		 return resp;
	 }
 
	 public long eliminarHabitacionHostalPorDireccionYNumero (String direccionHotel, String numeroHabitacion)
	 {
		log.info ("Eliminando habitacion hostal con la direccion: " + direccionHotel + " y numero habitacion: " + numeroHabitacion);
		long resp = pa.eliminarHostalPorDireccionHostalYNumeroHabitacion(direccionHotel, numeroHabitacion);		
		log.info ("Eliminando Habitacion Hostal: " + resp + " tuplas eliminadas");
		return resp;
	 }
	 
	 public HabitacionHostal darHabitacionHostalPorDireccionHostalYNumeroHabitacion (String direccion, String numero)
	{
		log.info ("Consultando Habitacion Hostal");
        HabitacionHostal habitacionHostal = pa.darHabitacionHostalPorDireccionHostalYNumeroHabitacion(direccion, numero);	
		log.info ("Buscando Habtiacion Hostal con direccion: " + direccion + " y numero habitacion " + numero != null ? habitacionHostal : "NO EXISTE");
        return habitacionHostal;
	}

	
	
	public List<Object[]> darHabitacionHostalPorDireccion (String direccion)
	{
		log.info ("Consultando Habitacion Hotel");
        List<Object[]> habitacionHotel = pa.darHabitacionHotelesPorNombre(direccion);	
		log.info ("Buscando Habitacion Hotel: " + direccion != null ? habitacionHotel : "NO EXISTE");
        return habitacionHotel;
	}

	public List<Object[]> darHabitacionesHostal ()
	{
		log.info ("Consultando Habitacion Hotel");
        List<Object[]> habitacionHotel = pa.darTodasHabitacionHostal();	
		log.info ("Buscando Habitacion Hotel: "  != null ? habitacionHotel : "NO EXISTE");
        return habitacionHotel;
	}
	/* ****************************************************************
	 * 			Métodos para manejar los Reservas
	 *****************************************************************/

	 public Reserva adicionarReserva (long idOferta, String idCliente, int precioEspecialTomado)
	 {
		 log.info ("Adicionando Reserva");
		 Reserva reserva = pa.adicionarReserva(idOferta, idCliente, precioEspecialTomado);		
		 log.info ("Adicionando Reserva: " + reserva);
		 return reserva;
	 }

	 public List<Reserva> darReservas()
	 {
		 return pa.darReservas();
	 }

	 public List<Reserva> darReservasPorIdCliente(String idCliente)
	 {
		 return pa.darReservasPorIdCliente(idCliente);
	 }

	 public Reserva darReservaPorIdOferta(long idOferta)
	 {
		 return pa.darReservaPorIdOferta(idOferta);
	 }
	 
	 public long eliminarReservaPorIdOferta(long idOferta)
	 {
		 return pa.eliminarReservaPorIdOferta(idOferta);
	 }


	public long eliminarReservaPorIdCliente(String idCliente)
	 {
		 return pa.eliminarReservaPorIdCliente(idCliente);
	 }

	 /* ****************************************************************
	 * 			Métodos para manejar las Instalacion Habitacion Hotel
	 *****************************************************************/

	 public InstalacionHabitacionHotel adicionarInstalacionHabitacionHotel (String tipoInstalacion, String numeroHabitacionHabitacionHotel, String direccionHotelHabitacionHotel)
	 {
		 log.info ("Adicionando Instalacion Habitacion Hotel");
		 InstalacionHabitacionHotel instalacionHabitacionHotel = pa.adicionarInstalacionHabitacionHotel(tipoInstalacion, numeroHabitacionHabitacionHotel, direccionHotelHabitacionHotel);
		 log.info ("Adicionando Instalacion Habitacion Hotel: " + instalacionHabitacionHotel);
		 return instalacionHabitacionHotel;
	 }

	 public List<VOInstalacionHabitacionHotel> darVOInstalacionesHabitacionHotel ()
	 {
		 log.info ("Generando los VO de Instalaciones Habitacion Hotel");        
		 List<VOInstalacionHabitacionHotel> voInstalacionHabitacionHotels = new LinkedList<VOInstalacionHabitacionHotel> ();
		 List<InstalacionHabitacionHotel> instalacionHabitacionHotel = pa.darTodasInstalacionHabitacionHotel();
		 for (InstalacionHabitacionHotel cl : instalacionHabitacionHotel )
		 {
			voInstalacionHabitacionHotels.add (cl);
		 }
		 log.info ("Generando los VO de Tipos de Cliente: " + voInstalacionHabitacionHotels.size() + " existentes");
		 return voInstalacionHabitacionHotels;
	 }

	 public List<InstalacionHabitacionHotel> darInstalacionesHabitacionHotel()
	 {
		 log.info ("Consultando Instalaciones Habitacion Hotel");
		 List<InstalacionHabitacionHotel> instalacionHabitacionHotel = pa.darTodasInstalacionHabitacionHotel();
		 log.info ("Consultando Instalaciones Habitacion Hotel: " != null ? instalacionHabitacionHotel : "NO EXISTE");
		 return instalacionHabitacionHotel;
	 }

	 public List<InstalacionHabitacionHotel> darInstalacionHabitacionHotelPorDireccionHotelHabitacionHotel(String direccionHotelHabitacionHotel)
	 {
		 log.info("Consultando Instalacion Habitacion Hotel por direccion de hotel");
		 List<InstalacionHabitacionHotel> instalacionHabitacionHotel = pa.darInstalacionHabitacionHotelPorDireccionHotelHabitacionHotel(direccionHotelHabitacionHotel);
		 log.info("Consultando Instalacion Habitacion Hotel por direccion de hotel: " + direccionHotelHabitacionHotel != null ? instalacionHabitacionHotel : "NO EXISTE");
		 return instalacionHabitacionHotel;
	 }

	 public List<InstalacionHabitacionHotel> darInstalacionHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel(String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel)
	 {
		 log.info("Consultando Instalacion Habitacion Hotel por direccion de hotel y numero de habitacion");
		 List<InstalacionHabitacionHotel> instalacionHabitacionHotel = pa.darInstalacionHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel(direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
		 log.info("Consultando Instalacion Habitacion Hotel por direccion de hotel: " + direccionHotelHabitacionHotel + " y numero habitacion: " + numeroHabitacionHabitacionHotel != null ? instalacionHabitacionHotel : "NO EXISTE");
		 return instalacionHabitacionHotel;
	 }

	 public InstalacionHabitacionHotel darInstalacionHabitacionHotelPorTipoInstalacionDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel(String tipoInstalacion, String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel)
	 {
		 log.info("Consultando Instalacion Habitacion Hotel por tipo de instalacion, direccion de hotel y numero de habitacion");
		 InstalacionHabitacionHotel instalacionHabitacionHotel = pa.darInstalacionHabitacionHotelPorTipoInstalacionDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel(tipoInstalacion, direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
		 log.info("Consultando Instalacion Habitacion Hotel por tipo de instalacion: " + tipoInstalacion + " direccion de hotel: " + direccionHotelHabitacionHotel + " y numero habitacion: " + numeroHabitacionHabitacionHotel != null ? instalacionHabitacionHotel : "NO EXISTE");
		 return instalacionHabitacionHotel;
	 }
	 
	 public long eliminarInstalacionHabitacionHotelPorTipoInstalacionYDireccionHotelHabitacionHotel (String tipoInstlacion, String direccionHotelHabitacionHotel )
	 {
		 log.info("Eliminando Instalacion Habitacion Hotel con tipo instalacion: " + tipoInstlacion + " y direccion de hotel: " + direccionHotelHabitacionHotel);
		 long resp = pa.eliminarInstalacionHabitacionHotelPorTipoInstalacionYDireccionHotelHabitacionHotel(tipoInstlacion, direccionHotelHabitacionHotel);
		 log.info ("Eliminando Instalacion Habitacion Hotel: " + resp + " tuplas eliminadas");
		return resp;
	 }

	 public long eliminarInstalacionHabitacionHotelPorTipoInstalacionDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel (String tipoInstlacion, String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel)
	 {
		 log.info("Eliminando Instalacion Habitacion Hotel con tipo instalacion: " + tipoInstlacion + " direccion de hotel: " + direccionHotelHabitacionHotel + " y numero habitacion: " + numeroHabitacionHabitacionHotel);
		 long resp = pa.eliminarInstalacionHabitacionHotelPorTipoInstalacionDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel(tipoInstlacion, direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
		 log.info ("Eliminando Instalacion Habitacion Hotel: " + resp + " tuplas eliminadas");
		 return resp;
	 }

	 public long eliminarInstalacionHabitacionHotelPorDireccionHotelHabitacionHotel (String direccionHotelHabitacionHotel)
	 {
		 log.info("Eliminando Instalacion Habitacion Hotel con direccion de hotel: " + direccionHotelHabitacionHotel);
		 long resp = pa.eliminarInstalacionHabitacionHotelPorDireccionHotelHabitacionHotel(direccionHotelHabitacionHotel);
		 log.info ("Eliminando Instalacion Habitacion Hotel: " + resp + " tuplas eliminadas");
		 return resp;
	 }

	 public long eliminarInstalacionHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel (String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel)
	 {
		 log.info("Eliminando Instalacion Habitacion Hotel con direccion de hotel: " + direccionHotelHabitacionHotel + " y numero habitacion: " + numeroHabitacionHabitacionHotel);
		 long resp = pa.eliminarInstalacionHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel(direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
		 log.info ("Eliminando Instalacion Habitacion Hotel: " + resp + " tuplas eliminadas");
		 return resp;
	 }


	/* ****************************************************************
	 * 			Métodos para manejar los Hostales
	 *****************************************************************/
	public Hostal adicionarHostal(String nombre, String direccion, String horarioApertura, String horarioCierre, float calificacion ){
		return pa.adicionarHostal(nombre, direccion, horarioApertura, horarioCierre, calificacion);
	}

    public List<Object[]> darHostales() {
		return pa.darHostales();
    }

	public List<Object[]> darHostalesPorNombre(String nombre) {
		return pa.darHostalPorNombre(nombre);
	}

	public Object[] darHostalPorDireccion(String direccion) {
		return pa.darHostalesPorDireccion(direccion);
	}

	public long eliminarHostalesPorNombre(String nombre) {
		return pa.eliminarHostalPorNombre(nombre);
	}

	public long eliminarHostalPorDireccion(String direccion) {
		return pa.eliminarHostalPorDireccion(direccion);
	}
 
 
 
 }
