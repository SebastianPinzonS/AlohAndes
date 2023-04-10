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
		log.info ("Generando los VO de Tipos de bebida");        
        List<VOApartamento> voApartamentos = new LinkedList<VOApartamento> ();
        for (Apartamento ap : pa.darApartamento ())
        {
        	voApartamentos.add (ap);
        }
        log.info ("Generando los VO de Tipos de bebida: " + voApartamentos.size() + " existentes");
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
}
