package uniandes.isis2304.alohandes.negocio;

public class Reserva {
    



    private String idOperador;
	
	private String idCliente;

	private String precioEspecialTomado;

	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Reserva() 
	{
		this.idOperador = "";
		this.idCliente = "";
		this.precioEspecialTomado = "";
		
    }

    public Reserva(String idOperador, String idCliente, String precioEspecialTomado) {
            this.idOperador = idOperador;
            this.idCliente = idCliente;
            this.precioEspecialTomado = precioEspecialTomado;
        }
	
	




    public String getIdOperador() {
        return idOperador;
    }



    public void setIdOperador(String idOperador) {
        this.idOperador = idOperador;
    }



    public String getIdCliente() {
        return idCliente;
    }



    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }



    public int getPrecioEspecialTomado() {
        return precioEspecialTomado;
    }



    public void setPrecioEspecialTomado(int precioEspecialTomado) {
        this.precioEspecialTomado = precioEspecialTomado;
    }

    /** 
	 * @return Una cadena con la información básica
	 */
	@Override
	public String toString() 
	{
		return "Reserva [idOperador=" + idOperador + ", idCliente=" + idCliente + ", precioEspecialTomado=" + precioEspecialTomado + "]";
	}
}
