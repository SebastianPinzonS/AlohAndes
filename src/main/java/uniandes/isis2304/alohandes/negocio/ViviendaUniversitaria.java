
package uniandes.isis2304.alohandes.negocio;


public class ViviendaUniversitaria implements VOViviendaUniversitaria
{

	private String nombre;

	private String direccion;
	
	private String numeroApartamento;

	private int viviendaCompartida;

	private int capacidad;

	private String menaje;

	private float calificacion;



	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public ViviendaUniversitaria() 
	{
		this.nombre = "";
		this.direccion = "";
		this.numeroApartamento = "";
		this.viviendaCompartida = 0;
		this.capacidad = 0;
		this.menaje = "";
		this.calificacion = 0;


	}

	/**
	 * Constructor con valores
	 * @param nombre -
	 * @param direccion 
	 * @param numeroApartamento
	 * @param viviendaCompartida
	 * @param capacidad
	 * @param menaje
	 * @param calificacion
	 */

	public ViviendaUniversitaria(String nombre, String direccion, String numeroApartamento, int viviendaCompartida, int capacidad, String menaje, float calificacion) 
	{
		this.nombre = nombre;
		this.direccion = direccion;
		this.numeroApartamento = numeroApartamento;
		this.viviendaCompartida = viviendaCompartida;
		this.capacidad = capacidad;
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
	public String getNumeroApartamento() 
	{
		return numeroApartamento;
	}

	/**
	 * @param numeroApartamento - 
	 */
	public void setNumeroApartamento(String numeroApartamento) 
	{
		this.numeroApartamento = numeroApartamento;
	}

	/**
	 * @return 
	 */
	public int getViviendaCompartida() 
	{
		return viviendaCompartida;
	}

	/**
	 * @param viviendaCompartida - 
	 */
	public void setViviendaCompartida(int viviendaCompartida) 
	{
		this.viviendaCompartida = viviendaCompartida;
	}

	/**
	 * @return 
	 */
	public int getCapacidad() 
	{
		return capacidad;
	}

	/**
	 * @param capacidad - 
	 */
	public void setCapacidad(int capacidad) 
	{
		this.capacidad = capacidad;
	}

	/**
	 * @return 
	 */
	public String getMenaje() 
	{
		return menaje;
	}

	/**
	 * @param menaje - 
	 */
	public void setMenajed(String menaje) 
	{
		this.menaje = menaje;
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
		return "ViviendaUniversitaria [nombre=" + nombre + ", direccion=" + direccion +  ", numeroApartamento" + numeroApartamento + ", viviendaCompartida" + viviendaCompartida +", capacidad" + capacidad + ", menaje" + menaje + ", calificacion" + calificacion +"]";
	}
	
}
