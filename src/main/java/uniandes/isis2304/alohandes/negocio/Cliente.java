
package uniandes.isis2304.alohandes.negocio;


public class Cliente implements VOCliente
{

	private String id;
	
	private String tipoId;

	private String nombre;

	private String tipo;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Cliente() 
	{
		this.id = "";
		this.tipoId = "";
		this.nombre = "";
		this.tipo = "";

	}

	/**
	 * Constructor con valores
	 * @param id 
	 * @param tipoId 
	 * @param nombre
	 * @param tipo
	 */
	public Cliente(String id, String tipoId, String nombre, String tipo) 
	{
		this.id = id;
		this.tipoId = tipoId;
		this.nombre = nombre;
		this.tipo = tipo;
	}

	/**
	 * @return El id
	 */
	public String getId() 
	{
		return id;
	}

	/**
	 * @param id - 
	 */
	public void setId(String id) 
	{
		this.id = id;
	}

	/**
	 * @return El tipoId
	 */
	public String getTipoId() 
	{
		return tipoId;
	}

	/**
	 * @param tipId - 
	 */
	public void settipoId(String tipoId) 
	{
		this.tipoId = tipoId;
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
	 * @return tipo
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
		return "Cliente [id=" + id + ", tipoId=" + tipoId +  ", nombre=" + nombre + ", tipo=" + tipo +"]";
	}
	
}
