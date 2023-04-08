package uniandes.isis2304.alohandes.negocio;

public interface VOOperador
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
	public String getNombre();

	/**
	 * @return 
	 */
	public String getTipo();

	/**
	 * @return 
	 */
	public int getValidacionCamaraDeComercioEmpresa();

	/**
	 * @return 
	 */
	public int getValidacionSuperTurismoEmpresa();

	/**
	 * @return 
	 */
	public int getMiembroComunidadUniversitariaPersona();


	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
	
}
