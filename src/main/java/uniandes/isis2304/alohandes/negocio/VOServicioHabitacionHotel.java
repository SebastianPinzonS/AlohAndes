package uniandes.isis2304.alohandes.negocio;

public interface VOServicioHabitacionHotel 
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return 
	 */
	public String getTipoServicio();

	/**
	 * @return 
	 */
	public String getNumeroHabitacionHabitacionHotel();

	/**
	 * @return 
	 */
	public String getDireccionHotelHabitacionHotel();
	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
	
}
