
package uniandes.isis2304.alohandes.negocio;


public class HabitacionHotel implements VOHabitacionHotel
{

	private String tipo;
	
	private String numeroHabitacion;

	private float tamano;

	private String direccionHotel;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public HabitacionHotel() 
	{
		this.tipo = "";
		this.numeroHabitacion = "";
		this.tamano = 0;
		this.direccionHotel = "";
	}

	/**
	 * Constructor con valores
	 * @param tipo 
	 * @param numeroHabitacion 
	 * @param tamano
	 * @param direccionHotel
	 */
	public HabitacionHotel(String tipo, String numeroHabitacion,float tamano, String direccionHotel) 
	{
		this.tipo = tipo;
		this.numeroHabitacion = numeroHabitacion;
		this.tamano = tamano;
		this.direccionHotel = direccionHotel;

	}

	/**
	 * @return 
	 */
	public String getTipo() 
	{
		return tipo;
	}

	/**
	 * @param tipo
	 */
	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}

	/**
	 * @return 
	 */
	public String getNumeroHabitacion() 
	{
		return numeroHabitacion;
	}

	/**
	 * @param tipo
	 */
	public void setNumeroHabitacion(String numeroHabitacion) 
	{
		this.numeroHabitacion = numeroHabitacion;
	}

	/**
	 * @return 
	 */
	public float getTamano() 
	{
		return tamano;
	}

	/**
	 * @param tipo
	 */
	public void setTamano(Float tamano) 
	{
		this.tamano = tamano;
	}

	/**
	 * @return 
	 */
	public String getDireccionHotel() 
	{
		return direccionHotel;
	}

	/**
	 * @param tipo
	 */
	public void setDireccionHotel(String direccionHotel) 
	{
		this.direccionHotel = direccionHotel;
	}

	
	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "HabitacionHotel [tipo=" + tipo + ", numeroHabitacion=" + numeroHabitacion +  ", tamano=" + tamano + ", direccionHotel=" + direccionHotel +"]";
	}
	
}
