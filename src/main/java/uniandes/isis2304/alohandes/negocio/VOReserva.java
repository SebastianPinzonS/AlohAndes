package uniandes.isis2304.alohandes.negocio;

public interface VOReserva {
    /* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return 
	 */
	public long getIdOperador();

	/**
	 * @return 
	 */
	public String getIdCliente();

	/**
	 * @return 
	 */
	public int getEspecialTomado();

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
    
}
