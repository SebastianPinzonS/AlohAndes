package uniandes.isis2304.alohandes.negocio;

public interface VOHabitacionHotel
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return 
	 */
	public String getTipo();

	/**
	 * @return 
	 */
	public String getNumeroHabitacion();

	/**
	 * @return 
	 */
	public float getTamano();

	/**
	 * @return 
	 */
	public String getDireccionHotel();
	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
	
}
