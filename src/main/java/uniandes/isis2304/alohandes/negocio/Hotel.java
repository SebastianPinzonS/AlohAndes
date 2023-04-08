
package uniandes.isis2304.alohandes.negocio;


public class Hotel implements VOHotel
{

	private String nombre;
	
	private float calificacion;

	private String direccion;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Hotel() 
	{
		this.nombre = "";
		this.calificacion = 0;
		this.direccion = "";

	}

	/**
	 * Constructor con valores
	 * @param nombre - El identificador del bebedor. Debe exixtir un bebedor con dicho identificador
	 * @param calificacion - El identificador de la bebida. Debe existir una bebida con dicho identificador
	 * @param direccion
	 */
	public Hotel(String nombre, float calificacion,String direccion) 
	{
		this.nombre = nombre;
		this.calificacion = calificacion;
		this.direccion = direccion;

	}

	/**
	 * @return El nombre
	 */
	public String getNombre() 
	{
		return nombre;
	}

	/**
	 * @param nombre - 
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	/**
	 * @return El calificacion
	 */
	public float getCalificacion() 
	{
		return calificacion;
	}

	

	/**
	 * @param calificacion - 
	 */
	public void setCalificacion(long calificacion) 
	{
		this.calificacion = calificacion;
	}

	/**
	 * @return La direccion
	 */
	public String getDireccion() 
	{
		return direccion;
	}

	/**
	 * @param direccion - 
	 */
	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}
	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "Hotel [nombre=" + nombre + ", calificacion=" + calificacion +  ", direccion=" + direccion +"]";
	}
	
}
