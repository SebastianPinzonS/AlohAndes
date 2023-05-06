
package uniandes.isis2304.alohandes.negocio;


public class Operador implements VOOperador
{

	private String id;
	
	private String nombre;

	private String tipo;

	private Integer validacionCamaraDeComercioEmpresa;

	private Integer validacionSuperTurismoEmpresa;

	private Integer miembroComunidadUniversitariaPersona;


	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Operador() 
	{
		this.id = "";
		this.nombre = "";
		this.tipo = "";
		this.validacionCamaraDeComercioEmpresa = 0;
		this.validacionSuperTurismoEmpresa = 0;
		this.miembroComunidadUniversitariaPersona = 0;
	}

	/**
	 * Constructor con valores
	 * @param id 
	 * @param tipoId 
	 * @param nombre
	 * @param tipo
	 */
	public Operador(String id, String nombre, String tipo, Integer validacionCamaraDeComercioEmpresa, Integer validacionSuperTurismoEmpresa, Integer miembroComunidadUniversitariaPersona) 
	{
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.validacionCamaraDeComercioEmpresa = validacionCamaraDeComercioEmpresa;
		this.validacionSuperTurismoEmpresa = validacionSuperTurismoEmpresa;
		this.miembroComunidadUniversitariaPersona = miembroComunidadUniversitariaPersona;
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
	 * @return validacionCamaraDeComercioEmpresa
	 */
	public Integer getValidacionCamaraDeComercioEmpresa() 
	{
		return validacionCamaraDeComercioEmpresa;
	}

	/**
	 * @param validacionCamaraDeComercioEmpresa - 
	 */
	public void setValidacionCamaraDeComercioEmpresa(Integer validacionCamaraDeComercioEmpresa) 
	{
		this.validacionCamaraDeComercioEmpresa = validacionCamaraDeComercioEmpresa;
	}

	/**
	 * @return validacionSuperTurismoEmpresa
	 */
	public Integer getValidacionSuperTurismoEmpresa() 
	{
		return validacionSuperTurismoEmpresa;
	}

	/**
	 * @param validacionSuperTurismoEmpresa - 
	 */
	public void setValidacionSuperTurismoEmpresa(Integer validacionSuperTurismoEmpresa) 
	{
		this.validacionSuperTurismoEmpresa = validacionSuperTurismoEmpresa;
	}

	/**
	 * @return miembroComunidadUniversitariaPersona
	 */
	public Integer getMiembroComunidadUniversitariaPersona() 
	{
		return miembroComunidadUniversitariaPersona;
	}

	/**
	 * @param miembroComunidadUniversitariaPersona - 
	 */
	public void setMiembroComunidadUniversitariaPersona(Integer miembroComunidadUniversitariaPersona) 
	{
		this.miembroComunidadUniversitariaPersona = miembroComunidadUniversitariaPersona;
	}

	
	/** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "Operador [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", validacionCamaraDeComercioEmpresa=" + validacionCamaraDeComercioEmpresa + ", validacionSuperTurismoEmpresa=" + validacionSuperTurismoEmpresa + ", miembroComunidadUniversitariaPersona=" + miembroComunidadUniversitariaPersona +"]";
	}
	
}
