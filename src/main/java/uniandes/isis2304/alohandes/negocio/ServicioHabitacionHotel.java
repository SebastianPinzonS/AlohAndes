
package uniandes.isis2304.alohandes.negocio;


public class ServicioHabitacionHotel implements VOServicioHabitacionHotel
{

	private String tipoServicio;
	
	private String numeroHabitacionHabitacionHotel;

	private String direccionHotelHabitacionHotel;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public ServicioHabitacionHotel() 
	{
		this.tipoServicio = "";
		this.numeroHabitacionHabitacionHotel = "";
		this.direccionHotelHabitacionHotel = "";

	}

	/**
	 * Constructor con valores
	 * @param tipoServicio - El identificador del bebedor. Debe exixtir un bebedor con dicho identificador
	 * @param numeroHabitacionHabitacionHotel - El identificador de la bebida. Debe existir una bebida con dicho identificador
	 * @param direccionHotelHabitacionHotel
	 */
	public ServicioHabitacionHotel(String tipoServicio, String numeroHabitacionHabitacionHotel,String direccionHotelHabitacionHotel) 
	{
		this.tipoServicio = tipoServicio;
		this.numeroHabitacionHabitacionHotel = numeroHabitacionHabitacionHotel;
		this.direccionHotelHabitacionHotel = direccionHotelHabitacionHotel;

	}

	/**
	 * @return El nombre
	 */
	public String getTipoServicio() 
	{
		return tipoServicio;
	}

	/**
	 * @param nombre - 
	 */
	public void setTipoServicio(String tipoServicio) 
	{
		this.tipoServicio = tipoServicio;
	}

	/**
	 * @return El nombre
	 */
	public String getNumeroHabitacionHabitacionHotel() 
	{
		return numeroHabitacionHabitacionHotel;
	}

	/**
	 * @param nombre - 
	 */
	public void setNumeroHabitacionHabitacionHotel(String numeroHabitacionHabitacionHotel) 
	{
		this.numeroHabitacionHabitacionHotel = numeroHabitacionHabitacionHotel;
	}

	/**
	 * @return El nombre
	 */
	public String getDireccionHotelHabitacionHotel() 
	{
		return direccionHotelHabitacionHotel;
	}

	/**
	 * @param nombre - 
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
		return "ServicioHabitacionHotel [tipoServicio=" + tipoServicio + ", numeroHabitacionHabitacionHotel=" + numeroHabitacionHabitacionHotel +  ", direccionHotelHabitacionHotel=" + direccionHotelHabitacionHotel +"]";
	}
	
}
