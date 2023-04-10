package uniandes.isis2304.alohandes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLUtil
{

	private final static String SQL = PersistenciaAlohAndes.SQL;


	private PersistenciaAlohAndes pa;

	public SQLUtil (PersistenciaAlohAndes pa)
	{
		this.pa = pa;
	}
	
	
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pa.darSeqAlohAndes () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}


	public long [] limpiarAlohAndes (PersistenceManager pm)
	{
        Query qOferta = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOferta ());          
        Query qInstalacionHabitacionHotel = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaInstalacionHabitacionHotel ());
        Query qServicioHabitacionHotel = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaServicioHabitacionHotel ());
        Query qViviendaHabitacionServicioHabitacion = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaViviendaHabitacionServicioHabitacion ());
        Query qCliente = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaCliente ());
        Query qOperador = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaOperador ());
        Query qServicioHabitacion = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaServicioHabitacion ());
        Query qHabitacionHotel = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHabitacionHotel ());
        Query qHotel = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHotel ());
        Query qHabitacionHostal = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHabitacionHostal ());
        Query qHostal = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaHostal ());
        Query qViviendaUniversitaria = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaViviendaUniversitaria ());
        Query qViviendaHabitacion = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaViviendaHabitacion ());
        Query qViviendaExpress = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaViviendaExpress ());
        Query qApartamento = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaApartamento ());


        long ofertaEliminados = (long) qOferta.executeUnique ();
        long instalacionHabitacionHotelEliminados = (long) qInstalacionHabitacionHotel.executeUnique ();
        long servicioHabitacionHotelEliminados = (long) qServicioHabitacionHotel.executeUnique ();
        long viviendaHabitacionServicioHabitacionEliminados = (long) qViviendaHabitacionServicioHabitacion.executeUnique ();
        long clienteEliminados = (long) qCliente.executeUnique ();
        long operadorEliminados = (long) qOperador.executeUnique ();
        long servicioHabitacionEliminados = (long) qServicioHabitacion.executeUnique ();
        long habitacionHotelEliminados = (long) qHabitacionHotel.executeUnique ();
        long hotelEliminados = (long) qHotel.executeUnique ();
        long habitacionHostalEliminados = (long) qHabitacionHostal.executeUnique ();
        long hostalEliminados = (long) qHostal.executeUnique ();
        long viviendaUniversitariaEliminados = (long) qViviendaUniversitaria.executeUnique ();
        long viviendaHabitacionEliminados = (long) qViviendaHabitacion.executeUnique ();
        long viviendaExpressEliminados = (long) qViviendaExpress.executeUnique ();
        long apartamentoEliminados = (long) qApartamento.executeUnique ();



        return new long[] {ofertaEliminados, instalacionHabitacionHotelEliminados, servicioHabitacionHotelEliminados, viviendaHabitacionServicioHabitacionEliminados, 
        		clienteEliminados, operadorEliminados, servicioHabitacionEliminados, habitacionHotelEliminados, hotelEliminados, habitacionHostalEliminados, hostalEliminados, viviendaUniversitariaEliminados, viviendaHabitacionEliminados, viviendaExpressEliminados, apartamentoEliminados};
	}
}
