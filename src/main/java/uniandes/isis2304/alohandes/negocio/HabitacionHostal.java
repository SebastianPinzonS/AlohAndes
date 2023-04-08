
package uniandes.isis2304.alohandes.negocio;


public class HabitacionHostal implements VOHabitacionHostal
{

	private String direccionHostal;
	
	private String numeroHabitacion;

	private float tamano;

	



	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public HabitacionHostal() 
	{
		this.direccionHostal = "";
		this.numeroHabitacion = "";
		this.tamano = 0;
		


	}

	/**
	 * Constructor con valores
	 * @param direccionHostal -
	 * @param numeroHabitacion 
	 * @param tamano
	 */

	public HabitacionHostal(String direccionHostal, String numeroHabitacion, float tamano) 
	{
		this.direccionHostal = direccionHostal;
		this.numeroHabitacion = numeroHabitacion;
		this.tamano = tamano;
	}

	/**
	 * @return El nombre
	 */
	public String getDireccionHostal() 
	{
		return direccionHostal;
	}

	/**
	 * @param direccionHostal - 
	 */
	public void setDireccionHostal(String direccionHostal) 
	{
		this.direccionHostal = direccionHostal;
	}


	/**
	 * @return 
	 */
	public String getNumeroHabitacion() 
	{
		return numeroHabitacion;
	}

	/**
	 * @param numeroHabitacion - 
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
	 * @param tamano - 
	 */
	public void setTamano(float tamano) 
	{
		this.tamano = tamano;
	}

	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "HabitacionHostal [direccionHostal=" + direccionHostal + ", numeroHabitacion=" + numeroHabitacion +  ", tamano=" + tamano +  "]";
	}
	
}
