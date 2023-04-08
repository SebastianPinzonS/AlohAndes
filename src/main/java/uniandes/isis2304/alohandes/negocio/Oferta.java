
package uniandes.isis2304.alohandes.negocio;

public class Oferta implements VOOferta
{

	private String id;
	
	private int duracionContratoDias;

	private int costoContrato;

	private int precioEspecial;

	private String condicionPrecioEspecial;

	private int costoAdicionalServicios;

	private int costoSeguroArrendamiento;

	private String idOperador;

	private String idCliente;

	private String direccionHostal;

	private String numeroHabitacionHostal;

	private String direccionHotelHabitacionHotel;

	private String numeroHabitacionHabitacionHotel;

	private String direccionViviendaUniversitaria;

	private String numeroApartamentoViviendaUniversitaria;

	private String direccionViviendaHabitacion;

	private String numeroApartamentoViviendaHabitacion;

	private String direccionApartamento;

	private String numeroApartamento;

	private String direccionViviendaExpress;


	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Oferta() 
	{
		this.id = "";
		this.duracionContratoDias = 0;
		this.costoContrato = 0;
		this.precioEspecial = 0;
		this.condicionPrecioEspecial = "";
		this.costoAdicionalServicios = 0;
		this.costoSeguroArrendamiento = 0;
		this.idOperador = "";
		this.idCliente = "";
		this.direccionHostal = "";
		this.numeroHabitacionHostal = "";
		this.direccionHotelHabitacionHotel = "";
		this.numeroHabitacionHabitacionHotel = "";
		this.direccionViviendaUniversitaria = "";
		this.numeroApartamentoViviendaUniversitaria = "";
		this.direccionViviendaHabitacion = "";
		this.numeroApartamentoViviendaHabitacion = "";
		this.direccionApartamento = "";
		this.numeroApartamento = "";
		this.direccionViviendaExpress = "";
	}
	
	public Oferta(String id, int duracionContratoDias, int costoContrato, int precioEspecial,
			String condicionPrecioEspecial, int costoAdicionalServicios, int costoSeguroArrendamiento,
			String idOperador, String idCliente, String direccionHostal, String numeroHabitacionHostal,
			String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel,
			String direccionViviendaUniversitaria, String numeroApartamentoViviendaUniversitaria,
			String direccionViviendaHabitacion, String numeroApartamentoViviendaHabitacion, String direccionApartamento,
			String numeroApartamento, String direccionViviendaExpress) 
	{
		this.id = id;
		this.duracionContratoDias = duracionContratoDias;
		this.costoContrato = costoContrato;
		this.precioEspecial = precioEspecial;
		this.condicionPrecioEspecial = condicionPrecioEspecial;
		this.costoAdicionalServicios = costoAdicionalServicios;
		this.costoSeguroArrendamiento = costoSeguroArrendamiento;
		this.idOperador = idOperador;
		this.idCliente = idCliente;
		this.direccionHostal = direccionHostal;
		this.numeroHabitacionHostal = numeroHabitacionHostal;
		this.direccionHotelHabitacionHotel = direccionHotelHabitacionHotel;
		this.numeroHabitacionHabitacionHotel = numeroHabitacionHabitacionHotel;
		this.direccionViviendaUniversitaria = direccionViviendaUniversitaria;
		this.numeroApartamentoViviendaUniversitaria = numeroApartamentoViviendaUniversitaria;
		this.direccionViviendaHabitacion = direccionViviendaHabitacion;
		this.numeroApartamentoViviendaHabitacion = numeroApartamentoViviendaHabitacion;
		this.direccionApartamento = direccionApartamento;
		this.numeroApartamento = numeroApartamento;
		this.direccionViviendaExpress = direccionViviendaExpress;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getDuracionContratoDias() {
		return duracionContratoDias;
	}

	public void setDuracionContratoDias(int duracionContratoDias) {
		this.duracionContratoDias = duracionContratoDias;
	}

	public int getCostoContrato() {
		return costoContrato;
	}

	public void setCostoContrato(int costoContrato) {
		this.costoContrato = costoContrato;
	}

	public int getPrecioEspecial() {
		return precioEspecial;
	}

	public void setPrecioEspecial(int precioEspecial) {
		this.precioEspecial = precioEspecial;
	}

	public String getCondicionPrecioEspecial() {
		return condicionPrecioEspecial;
	}

	public void setCondicionPrecioEspecial(String condicionPrecioEspecial) {
		this.condicionPrecioEspecial = condicionPrecioEspecial;
	}

	public int getCostoAdicionalServicios() {
		return costoAdicionalServicios;
	}

	public void setCostoAdicionalServicios(int costoAdicionalServicios) {
		this.costoAdicionalServicios = costoAdicionalServicios;
	}

	public int getCostoSeguroArrendamiento() {
		return costoSeguroArrendamiento;
	}

	public void setCostoSeguroArrendamiento(int costoSeguroArrendamiento) {
		this.costoSeguroArrendamiento = costoSeguroArrendamiento;
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

	public String getDireccionHostal() {
		return direccionHostal;
	}

	public void setDireccionHostal(String direccionHostal) {
		this.direccionHostal = direccionHostal;
	}

	public String getNumeroHabitacionHostal() {
		return numeroHabitacionHostal;
	}

	public void setNumeroHabitacionHostal(String numeroHabitacionHostal) {
		this.numeroHabitacionHostal = numeroHabitacionHostal;
	}

	public String getDireccionHotelHabitacionHotel() {
		return direccionHotelHabitacionHotel;
	}

	public void setDireccionHotelHabitacionHotel(String direccionHotelHabitacionHotel) {
		this.direccionHotelHabitacionHotel = direccionHotelHabitacionHotel;
	}

	public String getNumeroHabitacionHabitacionHotel() {
		return numeroHabitacionHabitacionHotel;
	}

	public void setNumeroHabitacionHabitacionHotel(String numeroHabitacionHabitacionHotel) {
		this.numeroHabitacionHabitacionHotel = numeroHabitacionHabitacionHotel;
	}

	public String getDireccionViviendaUniversitaria() {
		return direccionViviendaUniversitaria;
	}

	public void setDireccionViviendaUniversitaria(String direccionViviendaUniversitaria) {
		this.direccionViviendaUniversitaria = direccionViviendaUniversitaria;
	}

	public String getNumeroApartamentoViviendaUniversitaria() {
		return numeroApartamentoViviendaUniversitaria;
	}

	public void setNumeroApartamentoViviendaUniversitaria(String numeroApartamentoViviendaUniversitaria) {
		this.numeroApartamentoViviendaUniversitaria = numeroApartamentoViviendaUniversitaria;
	}

	public String getDireccionViviendaHabitacion() {
		return direccionViviendaHabitacion;
	}

	public void setDireccionViviendaHabitacion(String direccionViviendaHabitacion) {
		this.direccionViviendaHabitacion = direccionViviendaHabitacion;
	}

	public String getNumeroApartamentoViviendaHabitacion() {
		return numeroApartamentoViviendaHabitacion;
	}

	public void setNumeroApartamentoViviendaHabitacion(String numeroApartamentoViviendaHabitacion) {
		this.numeroApartamentoViviendaHabitacion = numeroApartamentoViviendaHabitacion;
	}

	public String getDireccionApartamento() {
		return direccionApartamento;
	}

	public void setDireccionApartamento(String direccionApartamento) {
		this.direccionApartamento = direccionApartamento;
	}

	public String getNumeroApartamento() {
		return numeroApartamento;
	}

	public void setNumeroApartamento(String numeroApartamento) {
		this.numeroApartamento = numeroApartamento;
	}

	public String getDireccionViviendaExpress() {
		return direccionViviendaExpress;
	}

	public void setDireccionViviendaExpress(String direccionViviendaExpress) {
		this.direccionViviendaExpress = direccionViviendaExpress;
	}
	
}
