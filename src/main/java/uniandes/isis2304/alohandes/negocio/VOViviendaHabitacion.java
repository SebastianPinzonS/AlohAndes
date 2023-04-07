package uniandes.isis2304.alohandes.negocio;

public interface VOViviendaHabitacion
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
	public String getNumeroApartamento();


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
