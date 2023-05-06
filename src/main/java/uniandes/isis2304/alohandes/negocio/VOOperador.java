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
	public Integer getValidacionCamaraDeComercioEmpresa();

	/**
	 * @return 
	 */
	public Integer getValidacionSuperTurismoEmpresa();

	/**
	 * @return 
	 */
	public Integer getMiembroComunidadUniversitariaPersona();


	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString();
	
}
