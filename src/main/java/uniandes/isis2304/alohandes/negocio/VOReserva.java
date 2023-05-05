package uniandes.isis2304.alohandes.negocio;

public interface VOReserva {
    /* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return 
	 */
	public long getIdOferta();

	/**
	 * @return 
	 */
	public String getIdCliente();

	/**
	 * @return 
	 */
	public int getPrecioEspecialTomado();

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
    
}
