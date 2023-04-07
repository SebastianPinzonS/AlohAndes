
package uniandes.isis2304.alohandes.negocio;


public class ViviendaHabitacion implements VOViviendaHabitacion
{

	private String nombre;

	private String direccion;

	private String numeroApartamento;
	
	private float calificacion;


	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public ViviendaHabitacion() 
	{
		this.nombre = "";
		this.direccion = "";
		this.numeroApartamento = "";
		this.calificacion = 0;

	}

	/**
	 * Constructor con valores
	 * @param nombre 
	 * @param direccion
	 * @param numeroApartamento
	 * @param calificacion 
	 */
	public ViviendaHabitacion(String nombre, String direccion, String numeroApartamento, float calificacion) 
	{
		this.nombre = nombre;
		this.direccion = direccion;
		this.numeroApartamento = numeroApartamento;
		this.calificacion = calificacion;


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
	 * @return La direccion
	 */
	public String getNumeroApartamento() 
	{
		return numeroApartamento;
	}

	/**
	 * @param direccion - 
	 */
	public void setNumeroApartamento(String numeroApartamento) 
	{
		this.numeroApartamento = numeroApartamento;
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
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "ViviendaHabitacion [nombre=" + nombre + ", direccion" + direccion + "numeroApartamento" + numeroApartamento + ", calificacion=" + calificacion +"]";
	}
	
}
