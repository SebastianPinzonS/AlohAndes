package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;
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
	 
	 public Operador darOperadorPorId (String id)
	 {
		 log.info ("Consultando Operador");
		 Operador operador = pa.darOperadorPorId (id);	
		 log.info ("Buscando Operador: " + id  != null ? operador : "NO EXISTE");
		 return operador;
	 }
 
	 public List<VOOperador> darVOOperadores ()
	 {
		 log.info ("Generando los VO de Operador");        
		 List<VOOperador> voOperadores = new LinkedList<VOOperador> ();
		 List<Operador> operadores = pa.darOperadores();
		 for (Operador op : operadores )
		 {
			voOperadores.add (op);
		 }
		 log.info ("Generando los VO de Tipos de Operadores: " + voOperadores.size() + " existentes");
		 return voOperadores;
	 }
 
	 public List<Operador> darOperadorPorNombre (String nombre)
	 {
		 log.info ("Consultando Operadores");
		 List<Operador> operadores = pa.darOperadorPorNombre (nombre);	
		 log.info ("Buscando Operador: " + nombre  != null ? operadores : "NO EXISTE");
		 return operadores;
	 }
 
	 public List<Operador> darOperadores ()
	 {
		 log.info ("Consultando Operadores");
		 List<Operador> clientes = pa.darOperadores  ();	
		 log.info ("Buscando Operadores: "  != null ? clientes : "NO EXISTE");
		 return clientes;
	 }

	 public List<String> mostrarDineroRecibidoPorCadaOperador()
	 {
		log.info ("Consultando Dinero por Operador");
		List<String> respuesta = pa.mostrarDineroRecibidoPorCadaOperador();
		return respuesta;

	 }
}
