package uniandes.isis2304.alohandes.negocio;

public interface VOOferta
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
	public int getDuracionContratoDias();

	/**
	 * @return 
	 */
	public int getCostoContrato();

	/**
	 * @return 
	 */
	public int getPrecioEspecial();

	/**
	 * @return 
	 */
	public String getCondicionPrecioEspecial();

	/**
	 * @return 
	 */
	public int getCostoAdicionalServicios();

	/**
	 * @return 
	 */
	public int getCostoSeguroArrendamiento();

	/**
	 * @return 
	 */
	public String getIdOperador();

	/**
	 * @return 
	 */
	public String getIdCliente();

	/**
	 * @return 
	 */
	public String getDireccionHostal();

	/**
	 * @return 
	 */
	public String getNumeroHabitacionHostal();

	/**
	 * @return 
	 */
	public String getDireccionHotelHabitacionHotel();

	/**
	 * @return 
	 */
	public String getNumeroHabitacionHabitacionHotel();

	/**
	 * @return 
	 */
	public String getDireccionViviendaUniversitaria();


	/**
	 * @return 
	 */
	public String getNumeroApartamentoViviendaUniversitaria();

	/**
	 * @return 
	 */
	public String getDireccionViviendaHabitacion();

	/**
	 * @return 
	 */
	public String getNumeroApartamentoViviendaHabitacion();

	/**
	 * @return 
	 */
	public String getDireccionApartamento();

	/**
	 * @return 
	 */
	public String getNumeroApartamento();

	/**
	 * @return 
	 */
	public String getDireccionViviendaExpress();

	
}
