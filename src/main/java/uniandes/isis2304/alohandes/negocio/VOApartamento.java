package uniandes.isis2304.alohandes.negocio;

public interface VOApartamento
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
	public String getNumero();

	/**
	 * @return 
	 */
	public int getAmoblado();

	/**
	 * @return 
	 */
	public int getNumeroHabitaciones();

	/**
	 * @return 
	 */
	public String getDireccion();

	/**
	 * @return 
	 */
	public int getIncluyeServicios();

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
