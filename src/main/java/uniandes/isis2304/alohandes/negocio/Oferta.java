
package uniandes.isis2304.alohandes.negocio;

import java.sql.Date;

public class Oferta implements VOOferta
{

	private long id;

	private Date fechaInicial;

	private int costoContrato;

	private int precioEspecial;

	private String condicionPrecioEspecial;

	private int costoAdicionalServicios;

	private int costoSeguroArrendamiento;

	private String idOperador;

	private String direccionHostalHabitacionHostal;

	private String numeroHabitacionHabitacionHostal;

	private String direccionHotelHabitacionHotel;

	private String numeroHabitacionHabitacionHotel;

	private String direccionViviendaUniversitaria;

	private String numeroApartamentoViviendaUniversitaria;

	private String direccionViviendaHabitacion;

	private String numeroApartamentoViviendaHabitacion;

	private String direccionApartamento;

	private String numeroApartamento;

	private String direccionViviendaExpress;

	private int visitas;


	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public Oferta() 
	{
		this.id = 0;
		this.fechaInicial = Date.valueOf("0001-01-01");
		this.costoContrato = 0;
		this.precioEspecial = 0;
		this.condicionPrecioEspecial = "";
		this.costoAdicionalServicios = 0;
		this.costoSeguroArrendamiento = 0;
		this.idOperador = "";
		this.direccionHostalHabitacionHostal = "";
		this.numeroHabitacionHabitacionHostal = "";
		this.direccionHotelHabitacionHotel = "";
		this.numeroHabitacionHabitacionHotel = "";
		this.direccionViviendaUniversitaria = "";
		this.numeroApartamentoViviendaUniversitaria = "";
		this.direccionViviendaHabitacion = "";
		this.numeroApartamentoViviendaHabitacion = "";
		this.direccionApartamento = "";
		this.numeroApartamento = "";
		this.direccionViviendaExpress = "";
		this.visitas = 0;
	}
	
	public Oferta(long id, int costoContrato, int precioEspecial,
			String condicionPrecioEspecial,  int costoAdicionalServicios, int costoSeguroArrendamiento,
			String idOperador, String direccionHostalHabitacionHostal, String numeroHabitacionHabitacionHostal,
			String direccionHotelHabitacionHotel, String numeroHabitacionHabitacionHotel,
			String direccionViviendaUniversitaria, String numeroApartamentoViviendaUniversitaria,
			String direccionViviendaHabitacion, String numeroApartamentoViviendaHabitacion, String direccionApartamento,
			String numeroApartamento, String direccionViviendaExpress, int visitas) 
	{
		this.id = id; 
		long millis=System.currentTimeMillis();  
		java.sql.Date date = new java.sql.Date(millis);
		this.fechaInicial = date;
		this.costoContrato = costoContrato;
		this.precioEspecial = precioEspecial;
		this.condicionPrecioEspecial = condicionPrecioEspecial;
		this.costoAdicionalServicios = costoAdicionalServicios;
		this.costoSeguroArrendamiento = costoSeguroArrendamiento;
		this.idOperador = idOperador;
		this.direccionHostalHabitacionHostal = direccionHostalHabitacionHostal;
		this.numeroHabitacionHabitacionHostal = numeroHabitacionHabitacionHostal;
		this.direccionHotelHabitacionHotel = direccionHotelHabitacionHotel;
		this.numeroHabitacionHabitacionHotel = numeroHabitacionHabitacionHotel;
		this.direccionViviendaUniversitaria = direccionViviendaUniversitaria;
		this.numeroApartamentoViviendaUniversitaria = numeroApartamentoViviendaUniversitaria;
		this.direccionViviendaHabitacion = direccionViviendaHabitacion;
		this.numeroApartamentoViviendaHabitacion = numeroApartamentoViviendaHabitacion;
		this.direccionApartamento = direccionApartamento;
		this.numeroApartamento = numeroApartamento;
		this.direccionViviendaExpress = direccionViviendaExpress;
		this.visitas = visitas;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getFechaInicial() {
		return fechaInicial;
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

	public String getDireccionHostal() {
		return direccionHostalHabitacionHostal;
	}

	public void setDireccionHostal(String direccionHostalHabitacionHostal) {
		this.direccionHostalHabitacionHostal = direccionHostalHabitacionHostal;
	}

	public String getNumeroHabitacionHostal() {
		return numeroHabitacionHabitacionHostal;
	}

	public void setNumeroHabitacionHostal(String numeroHabitacionHabitacionHostal) {
		this.numeroHabitacionHabitacionHostal = numeroHabitacionHabitacionHostal;
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

	public int getVisitas() {
		return visitas;
	}

	public void setVisitas(int visitas) {
		this.visitas = visitas;
	}
	
}
