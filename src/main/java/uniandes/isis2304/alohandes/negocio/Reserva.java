package uniandes.isis2304.alohandes.negocio;

public class Reserva implements VOReserva{
    



    private long idOferta;
	
	private String idCliente;

	private int precioEspecialTomado;

	

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Reserva() 
	{
		this.idOferta = 0;
		this.idCliente = "";
		this.precioEspecialTomado = 0;
		
    }

    public Reserva(long idOferta, String idCliente, int precioEspecialTomado) {
            this.idOferta = idOferta;
            this.idCliente = idCliente;
            this.precioEspecialTomado = precioEspecialTomado;
        }
	
	




    public long getIdOferta() {
        return idOferta;
    }



    public void setidOferta(long idOferta) {
        this.idOferta = idOferta;
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
		return "Reserva [idOferta=" + idOferta + ", idCliente=" + idCliente + ", precioEspecialTomado=" + precioEspecialTomado + "]";
	}
}
