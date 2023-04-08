
package uniandes.isis2304.alohandes.negocio;


public class ViviendaHabitacionServicioHabitacion implements VOViviendaHabitacionServicioHabitacion
{

	private String direccionViviendaHabitacion;
	
	private String numeroViviendaHabitacion;

	private String tipo;

	



	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public ViviendaHabitacionServicioHabitacion() 
	{
		this.direccionViviendaHabitacion = "";
		this.numeroViviendaHabitacion = "";
		this.tipo = "";
		


	}

	/**
	 * Constructor con valores
	 * @param direccionViviendaHabitacion -
	 * @param numeroViviendaHabitacion 
	 * @param tipo
	 */

	public ViviendaHabitacionServicioHabitacion(String direccionViviendaHabitacion, String numeroViviendaHabitacion, String tipo) 
	{
		this.direccionViviendaHabitacion = direccionViviendaHabitacion;
		this.numeroViviendaHabitacion = numeroViviendaHabitacion;
		this.tipo = tipo;
	}

	/**
	 * @return El nombre
	 */
	public String getDireccionViviendaHabitacion() 
	{
		return direccionViviendaHabitacion;
	}

	/**
	 * @param direccionViviendaHabitacion - 
	 */
	public void setDireccionViviendaHabitacion(String direccionViviendaHabitacion) 
	{
		this.direccionViviendaHabitacion = direccionViviendaHabitacion;
	}


	/**
	 * @return 
	 */
	public String getNumeroViviendaHabitacion() 
	{
		return numeroViviendaHabitacion;
	}

	/**
	 * @param numeroViviendaHabitacion - 
	 */
	public void setNumeroViviendaHabitacion(String numeroViviendaHabitacion) 
	{
		this.numeroViviendaHabitacion = numeroViviendaHabitacion;
	}

	/**
	 * @return 
	 */
	public String getTipo() 
	{
		return tipo;
	}

	/**
	 * @param tipo - 
	 */
	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}

	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "ViviendaHabitacionServicio [direccionViviendaHabitacion=" + direccionViviendaHabitacion + ", numeroViviendaHabitacion=" + numeroViviendaHabitacion +  ", tipo=" + tipo +  "]";
	}
	
}
