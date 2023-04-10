package uniandes.isis2304.alohandes.negocio;

public class ServicioHabitacion {
    private String tipo;
	
	private int precio;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public ServicioHabitacion() 
	{
		this.tipo = "";
		this.precio = 0;

	}

	public ServicioHabitacion(String tipo,int precio) 
	{
		this.tipo = tipo;
		this.precio = precio;
	}

	/**
	 * @return El nombre
	 */
	public String getTipo() 
	{
		return tipo;
	}

	/**
	 * @param nombre - 
	 */
	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}

	/**
	 * @return El nombre
	 */
	public int getPrecio() 
	{
		return precio;
	}

	/**
	 * @param nombre - 
	 */
	public void setPrecio(int precio) 
	{
		this.precio = precio;
	}

	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "ServicioHabitacion [tipo=" + tipo + ", precio=" + precio +"]";
	}
	
}
