
package uniandes.isis2304.alohandes.negocio;


public class Hostal implements VOHostal
{

	private String nombre;

	private String direccion;
	
	private String horarioApertura;

	private String horarioCierre;

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
		this.horarioApertura = "00:00:00";
		this.horarioCierre = "00:00:00";
		this.calificacion = 0;


	}

	/**
	 * Constructor con valores
	 * @param nombre -
	 * @param direccion 
	 * @param horarioApertura
	 * @param horarioCierre
	 * @param calificacion
	 */

	public Hostal(String nombre, String direccion, String horarioApertura, String horarioCierre, float calificacion) 
	{
		this.nombre = nombre;
		this.direccion = direccion;
		this.horarioApertura = horarioApertura;
		this.horarioCierre = horarioCierre;
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
	 * @return 
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
	 * @return 
	 */
	public String getHorarioApertura() 
	{
		return horarioApertura;
	}

	/**
	 * @param horarioApertura - 
	 */
	public void setHorarioApertura(String horarioApertura) 
	{
		this.horarioApertura = horarioApertura;
	}

	/**
	 * @return 
	 */
	public String getHorarioCierre() 
	{
		return horarioCierre;
	}

	/**
	 * @param horarioCierre - 
	 */
	public void setHorarioCierre(String horarioCierre) 
	{
		this.horarioCierre = horarioCierre;
	}
	
	/**
	 * @return 
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
		return "Hostal [nombre=" + nombre + ", direccion=" + direccion +  ", horarioApertura" + horarioApertura + ", horarioCierre" + horarioCierre + ", calificacion" + calificacion +"]";
	}
	
}
