package uniandes.isis2304.alohandes.negocio;

public interface VOHotel 
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
	public float getCalificacion();

	/**
	 * @return 
	 */
	public String getDireccion();
	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
	
}
