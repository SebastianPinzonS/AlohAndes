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
        Query qSirven = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaSirven ());
        Query qVisitan = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaVisitan ());
        Query qBebida = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaBebida ());
        Query qTipoBebida = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaTipoBebida ());
        Query qBebedor = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaBebedor ());
        Query qBar = pm.newQuery(SQL, "DELETE FROM " + pa.darTablaBar ());

        long gustanEliminados = (long) qGustan.executeUnique ();
        long sirvenEliminados = (long) qSirven.executeUnique ();
        long visitanEliminadas = (long) qVisitan.executeUnique ();
        long bebidasEliminadas = (long) qBebida.executeUnique ();
        long tiposBebidaEliminados = (long) qTipoBebida.executeUnique ();
        long bebedoresEliminados = (long) qBebedor.executeUnique ();
        long baresEliminados = (long) qBar.executeUnique ();
        return new long[] {gustanEliminados, sirvenEliminados, visitanEliminadas, bebidasEliminadas, 
        		tiposBebidaEliminados, bebedoresEliminados, baresEliminados};
	}

}
