package uniandes.isis2304.alohandes.negocio;

public interface VOServicioHabitacion {
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
	public String getPrecio();
	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
}
