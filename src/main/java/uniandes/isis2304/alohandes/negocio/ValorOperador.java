package uniandes.isis2304.alohandes.negocio;

public class ValorOperador implements VOValorOperador {

    private String idOperador;
	
	private String nombre;

	private Integer dinero;

    public ValorOperador() {
        this.idOperador = "";
        this.nombre = "";
        this.dinero = 0;
    }

    public ValorOperador(String idOperador, String nombre, int dinero) {
        this.idOperador = idOperador;
        this.nombre = nombre;
        this.dinero = dinero;
    }

    public  String getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(String idOperador) {
        this.idOperador = idOperador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public String toString( )
    {
         return ( "ValorOperador [idOperador=" + idOperador + ", nombre=" + nombre + ", dinero=" + dinero + "]");
    }
	
}
