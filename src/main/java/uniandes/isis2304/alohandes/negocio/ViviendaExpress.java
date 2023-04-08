
package uniandes.isis2304.alohandes.negocio;


public class ViviendaExpress implements VOViviendaExpress
{

	private String nombre;
	
	private String direccion;

	private int numeroHabitacion;

	private String menaje;

	private float calificacion;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public ViviendaExpress() 
	{
		this.nombre = "";
		this.direccion = "";
		this.numeroHabitacion = 0;
		this.menaje = "";
		this.calificacion = 0;


	}

	/**
	 * Constructor con valores
	 * @param nombre 
	 * @param direccion 
	 * @param numeroHabitacion
	 * @param menaje
	 * @param calificacion
	 */
	public ViviendaExpress(String nombre, String direccion, int numeroHabitacion, String menaje, float calificacion) 
	{
		this.nombre = nombre;
		this.direccion = direccion;
		this.numeroHabitacion = numeroHabitacion;
		this.menaje = menaje;
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
	 * @return El numeroHabitacion
	 */
	public int getNumeroHabitacion() 
	{
		return numeroHabitacion;
	}

	/**
	 * @param numeroHabitacion - 
	 */
	public void setNumeroHabitacione(int numeroHabitacion) 
	{
		this.numeroHabitacion = numeroHabitacion;
	}

	/**
	 * @return  menaje
	 */
	public String getMenaje() 
	{
		return menaje;
	}

	/**
	 * @param menaje - 
	 */
	public void setMenaje(String menaje) 
	{
		this.menaje = menaje;
	}

	/**
	 * @return calificacion
	 */
	public float getCalificacion() 
	{
		return calificacion;
	}

	/**
	 * @param calificacion - 
	 */
	public void setCalificacion(float calificacion) 
	{
		this.calificacion = calificacion;
	}

	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "Hotel [nombre=" + nombre + ", direccion=" + direccion + ", numeroHabitacion=" + numeroHabitacion +  ", menaje=" + menaje +  ", calificacion=" + calificacion +"]";
	}
	
}
