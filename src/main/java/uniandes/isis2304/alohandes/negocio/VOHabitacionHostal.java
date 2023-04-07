package uniandes.isis2304.alohandes.negocio;

public interface VOHabitacionHostal 
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return 
	 */
	public String getDireccionHostal();

	/**
	 * @return 
	 */
	public String getNumeroHabitacion();

	/**
	 * @return 
	 */
	public float getTamano();


	
	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
	
}
