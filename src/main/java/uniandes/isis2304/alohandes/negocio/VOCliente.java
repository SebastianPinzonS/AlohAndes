package uniandes.isis2304.alohandes.negocio;

public interface VOCliente
{
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * @return 
	 */
	public String getId();

	/**
	 * @return 
	 */
	public String getTipoId();

	/**
	 * @return 
	 */
	public String getNombre();

	/**
	 * @return 
	 */
	public String getTipo();


	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
	
}
