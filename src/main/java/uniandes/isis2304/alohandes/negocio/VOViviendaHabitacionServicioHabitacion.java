package uniandes.isis2304.alohandes.negocio;

public interface VOViviendaHabitacionServicioHabitacion 
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return 
	 */
	public String getDireccionViviendaHabitacion();

	/**
	 * @return 
	 */
	public String getNumeroViviendaHabitacion();

	/**
	 * @return 
	 */
	public String getTipo();


	
	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
	
}
