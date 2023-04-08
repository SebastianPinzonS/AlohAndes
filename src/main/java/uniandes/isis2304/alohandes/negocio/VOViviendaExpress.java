package uniandes.isis2304.alohandes.negocio;

public interface VOViviendaExpress
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return 
	 */
	public String getNombre();

	/**
	 * @return 
	 */
	public String getDireccion();

	/**
	 * @return 
	 */
	public int getNumeroHabitacion();

	/**
	 * @return 
	 */
	public String getMenaje();

	/**
	 * @return 
	 */
	public float getCalificacion();

	
	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
	
}
