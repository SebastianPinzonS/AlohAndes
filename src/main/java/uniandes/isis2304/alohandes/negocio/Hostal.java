
package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;

public class Hostal implements VOHotel
{

	private String nombre;

	private String direccion;
	
	private Timestamp horarioApertura;

	private Timestamp horarioCierre;

	private float calificacion;



	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Hostal() 
	{
		this.nombre = "";
		this.direccion = "";
		this.horarioApertura = new Timestamp(0, 0, 0, 0, 0, 0, 0);
		this.horarioCierre = new Timestamp(0, 0, 0, 0, 0, 0, 0);


	}

	/**
	 * Constructor con valores
	 * @param nombre - El identificador del bebedor. Debe exixtir un bebedor con dicho identificador
	 * @param calificacion - El identificador de la bebida. Debe existir una bebida con dicho identificador
	 * @param direccion
	 */
	public Hostal(String nombre, float calificacion,String direccion) 
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
		return "Hotel [nombre=" + nombre + ", calificacion=" + calificacion +  ", direccion" + direccion +"]";
	}
	
}
