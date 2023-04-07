
package uniandes.isis2304.alohandes.negocio;


public class InstalacionHabitacionHotel implements VOInstalacionHabitacionHotel
{

	private String tipoInstalacion;
	
	private String numeroHabitacionHabitacionHotel;

	private String direccionHotelHabitacionHotel;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public InstalacionHabitacionHotel() 
	{
		this.tipoInstalacion = "";
		this.numeroHabitacionHabitacionHotel = "";
		this.direccionHotelHabitacionHotel = "";
	}

	/**
	 * Constructor con valores
	 * @param tipoInstalacion 
	 * @param numeroHabitacionHabitacionHotel
	 * @param direccionHotelHabitacionHotel
	 */
	public InstalacionHabitacionHotel(String tipoInstalacion, String numeroHabitacionHabitacionHotel, String direccionHotelHabitacionHotel) 
	{
		this.tipoInstalacion = tipoInstalacion;
		this.numeroHabitacionHabitacionHotel = numeroHabitacionHabitacionHotel;
		this.direccionHotelHabitacionHotel = direccionHotelHabitacionHotel;

	}

	/**
	 * @return 
	 */
	public String getTipoInstalacion() 
	{
		return tipoInstalacion;
	}

	/**
	 * @param tipoInstalacion
	 */
	public void setTipoInstalacion(String tipoInstalacion) 
	{
		this.tipoInstalacion = tipoInstalacion;
	}

	/**
	 * @return 
	 */
	public String getNumeroHabitacionHabitacionHotel() 
	{
		return numeroHabitacionHabitacionHotel;
	}

	/**
	 * @param numeroHabitacionHabitacionHotel
	 */
	public void setNumeroHabitacionHabitacionHotel(String numeroHabitacionHabitacionHotel) 
	{
		this.numeroHabitacionHabitacionHotel = numeroHabitacionHabitacionHotel;
	}


	/**
	 * @return 
	 */
	public String getDireccionHotelHabitacionHotel() 
	{
		return direccionHotelHabitacionHotel;
	}

	/**
	 * @param direccionHotelHabitacionHotel
	 */
	public void setDireccionHotelHabitacionHotel(String direccionHotelHabitacionHotel) 
	{
		this.direccionHotelHabitacionHotel = direccionHotelHabitacionHotel;
	}
	

	
	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "InstalacionHabitacionHotel [tipoInstalacion=" + tipoInstalacion + ", numeroHabitacionHabitacionHotel=" + numeroHabitacionHabitacionHotel + "]";
	}
	
}
