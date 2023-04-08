
package uniandes.isis2304.alohandes.negocio;


public class Apartamento implements VOApartamento
{

	private String nombre;
	
	private String numero;

	private int amoblado;

	private int numeroHabitaciones;

	private String direccion;

	private int incluyeServicios;

	private float calificacion;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Apartamento() 
	{
		this.nombre = "";
		this.numero = "";
		this.amoblado = 0;
		this.numeroHabitaciones = 0;
		this.direccion = "";
		this.incluyeServicios = 0;
		this.calificacion = 0;


	}

	/**
	 * Constructor con valores
	 * @param nombre 
	 * @param numero 
	 * @param amoblado
	 * @param numeroHabitaciones
	 * @param direccion
	 * @param incluyeServicios
	 * @param calificacion
	 */
	public Apartamento(String nombre, String numero,int amoblado, int numeroHabitaciones, String direccion, int incluyeServicios, float calificacion) 
	{
		this.nombre = nombre;
		this.numero = numero;
		this.amoblado = amoblado;
		this.numeroHabitaciones = numeroHabitaciones;
		this.direccion = direccion;
		this.incluyeServicios = incluyeServicios;
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
	 * @return El numero
	 */
	public String getNumero() 
	{
		return numero;
	}

	/**
	 * @param numero - 
	 */
	public void setNumero(String numero) 
	{
		this.numero = numero;
	}

	/**
	 * @return  amoblado
	 */
	public int getAmoblado() 
	{
		return amoblado;
	}

	/**
	 * @param amoblado - 
	 */
	public void setAmoblado(int amoblado) 
	{
		this.amoblado = amoblado;
	}

	/**
	 * @return El numeroHabitaciones
	 */
	public int getNumeroHabitaciones() 
	{
		return numeroHabitaciones;
	}

	/**
	 * @param numero - 
	 */
	public void setNumeroHabitaciones(int numeroHabitaciones) 
	{
		this.numeroHabitaciones = numeroHabitaciones;
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
	 * @return incluyeServicio
	 */
	public int getIncluyeServicios() 
	{
		return incluyeServicios;
	}

	/**
	 * @param incluyeServicio - 
	 */
	public void setIncluyeServicios(int incluyeServicios) 
	{
		this.incluyeServicios = incluyeServicios;
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
		return "Apartamento [nombre=" + nombre + ", numero=" + numero +  ", amoblado=" + amoblado + ", numeroHabitaciones=" + numeroHabitaciones + ", direccion=" + direccion + ", incluyeServicios=" + incluyeServicios + ", calificacion=" + calificacion +"]";
	}
	
}
