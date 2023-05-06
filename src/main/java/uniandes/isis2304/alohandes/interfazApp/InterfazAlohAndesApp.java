package uniandes.isis2304.alohandes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.alohandes.negocio.*;

@SuppressWarnings("serial")

public class InterfazAlohAndesApp extends JFrame implements ActionListener
{

	private static Logger log = Logger.getLogger(InterfazAlohAndesApp.class.getName());
	

	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 
	
	
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 
	
    private JsonObject tableConfig;
    
    private AlohAndes alohAndes;

    private JsonObject guiConfig;

    private PanelDatos panelDatos;
    

    private JMenuBar menuBar;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
    /**
     * Construye la ventana principal de la aplicación. <br>
     * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
     */
    public InterfazAlohAndesApp( )
    {
        // Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
        
        tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
        alohAndes = new AlohAndes (tableConfig);
        
    	String path = guiConfig.get("bannerPath").getAsString();
        panelDatos = new PanelDatos ( );

        setLayout (new BorderLayout());
        add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
        add( panelDatos, BorderLayout.CENTER );        
    }
    
	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String tipo, String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "AlohAndes App", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }
    
    /**
     * Método para configurar el frame principal de la aplicación
     */
    private void configurarFrame(  )
    {
    	int alto = 0;
    	int ancho = 0;
    	String titulo = "";	
    	
    	if ( guiConfig == null )
    	{
    		log.info ( "Se aplica configuración por defecto" );			
			titulo = "AlohAndes APP Default";
			alto = 300;
			ancho = 500;
    	}
    	else
    	{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
    		titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
    	}
    	
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocation (50,50);
        setResizable( true );
        setBackground( Color.WHITE );

        setTitle( titulo );
		setSize ( ancho, alto);        
    }

    /**
     * Método para crear el menú de la aplicación con base em el objeto JSON leído
     * Genera una barra de menú y los menús con sus respectivas opciones
     * @param jsonMenu - Arreglo Json con los menùs deseados
     */
    private void crearMenu(  JsonArray jsonMenu )
    {    	
    	// Creación de la barra de menús
        menuBar = new JMenuBar();       
        for (JsonElement men : jsonMenu)
        {
        	// Creación de cada uno de los menús
        	JsonObject jom = men.getAsJsonObject(); 

        	String menuTitle = jom.get("menuTitle").getAsString();        	
        	JsonArray opciones = jom.getAsJsonArray("options");
        	
        	JMenu menu = new JMenu( menuTitle);
        	
        	for (JsonElement op : opciones)
        	{       	
        		// Creación de cada una de las opciones del menú
        		JsonObject jo = op.getAsJsonObject(); 
        		String lb =   jo.get("label").getAsString();
        		String event = jo.get("event").getAsString();
        		
        		JMenuItem mItem = new JMenuItem( lb );
        		mItem.addActionListener( this );
        		mItem.setActionCommand(event);
        		
        		menu.add(mItem);
        	}       
        	menuBar.add( menu );
        }        
        setJMenuBar ( menuBar );	
    }
    
	/* ****************************************************************
	 * 			CRUD de Apartamento
	 *****************************************************************/
    /**
     * Adiciona un tipo de bebida con la información dada por el usuario
     * Se crea una nueva tupla de tipoBebida en la base de datos, si un tipo de bebida con ese nombre no existía
     */

    public void adicionarApartamento( )
    {
    	try 
    	{
    		String nombreApartamento = JOptionPane.showInputDialog (this, "Nombre del edificio?", "Adicionar Apartamento", JOptionPane.QUESTION_MESSAGE);
			String numeroApartamento = JOptionPane.showInputDialog (this, "Numero de apartamento?", "Adicionar Apartamento", JOptionPane.QUESTION_MESSAGE);
			int amobladoApartamento = Integer.parseInt(JOptionPane.showInputDialog (this, "Es amoblado? 1 para si, 0 para no", "Adicionar Apartamento", JOptionPane.QUESTION_MESSAGE));
			int numeroHabitacionesApartamento = Integer.parseInt(JOptionPane.showInputDialog (this, "Cuantas habitaciones tiene?", "Adicionar Apartamento", JOptionPane.QUESTION_MESSAGE));
			String direccionApartamento = JOptionPane.showInputDialog (this, "Cual es la direccion?", "Adicionar Apartamento", JOptionPane.QUESTION_MESSAGE);
			int incluyeServiciosApartamento = Integer.parseInt(JOptionPane.showInputDialog (this, "Incluye servicios? 1 para si, 0 para no", "Adicionar Apartamento", JOptionPane.QUESTION_MESSAGE));
			float calificacionApartamento = Float.parseFloat(JOptionPane.showInputDialog (this, "Cual es la Calificacion?", "Adicionar Apartamento", JOptionPane.QUESTION_MESSAGE));


    		if (nombreApartamento != null && numeroApartamento != null && direccionApartamento != null )
    		{
        		VOApartamento ap = alohAndes.adicionarApartamento (nombreApartamento, numeroApartamento, amobladoApartamento, numeroHabitacionesApartamento, direccionApartamento, incluyeServiciosApartamento, calificacionApartamento);
        		if (ap == null)
        		{
        			throw new Exception ("No se pudo crear un tipo de bebida con nombre: " + nombreApartamento + " y numero: " + numeroApartamento);
        		}
        		String resultado = "En adicionarApartamento\n\n";
        		resultado += "Apartamento adicionado exitosamente: " + ap;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

    public void listarApartamentos( )
    {
    	try 
    	{
			List <VOApartamento> lista = alohAndes.darVOApartamentos();

			String resultado = "En listarApartamento";
			resultado +=  "\n" + listarApartamento (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

    public void eliminarApartamentoPorNombreYNumero ( )
    {
    	try 
    	{
    		String nombreApartmanetoStr = JOptionPane.showInputDialog (this, "Nombre del edificio del apartamento?", "Borrar Apartamento en el edificio: ", JOptionPane.QUESTION_MESSAGE);
    		String numeroApartmanetoStr = JOptionPane.showInputDialog (this, "Numero de apartamento?", "Borrar Apartamento en el edificio: ", JOptionPane.QUESTION_MESSAGE);

    		if (nombreApartmanetoStr != null && numeroApartmanetoStr != null)
    		{
    		
    			long apEliminados = alohAndes.eliminarApartamentoPorNombreYNumero(nombreApartmanetoStr, numeroApartmanetoStr);

    			String resultado = "En eliminar Apartamento\n\n";
    			resultado += apEliminados + " Apartmentos eliminados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

    public void eliminarApartamentoPorDireccionYNumero ( )
    {
    	try 
    	{
    		String direccionApartmanetoStr = JOptionPane.showInputDialog (this, "Direccion del edificio del apartamento?", "Borrar Apartamento en el edificio: ", JOptionPane.QUESTION_MESSAGE);
    		String numeroApartmanetoStr = JOptionPane.showInputDialog (this, "Direccion del edificio del apartamento?", "Borrar Apartamento en el edificio: ", JOptionPane.QUESTION_MESSAGE);

    		if (direccionApartmanetoStr != null && numeroApartmanetoStr != null)
    		{
    		
    			long apEliminados = alohAndes.eliminarApartmentoPorDireccionYNumero(direccionApartmanetoStr, numeroApartmanetoStr);
											

    			String resultado = "En eliminar Apartamento\n\n";
    			resultado += apEliminados + " Apartmentos eliminados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	

    public void buscarApartamentosPorDireccionYNumero ()
    {
    	try 
    	{
    		String direccionAp = JOptionPane.showInputDialog (this, "Direccion del edificio del apartamento?", "Buscar Apartamento por direccion", JOptionPane.QUESTION_MESSAGE);
			String numeroAp = JOptionPane.showInputDialog (this, "Numero del apartamento?", "Buscar Apartamento por direccion", JOptionPane.QUESTION_MESSAGE);

    		if (numeroAp != null && direccionAp != null)
    		{
    			VOApartamento apartamento = alohAndes.darApartamentoPorDireccionYNumero(direccionAp, numeroAp) ;
    			String resultado = "En buscar Apartamentos\n\n";
    			if (apartamento != null)
    			{
        			resultado += "El Apartamento es: " + apartamento;
    			}
    			else
    			{
        			resultado += "Un Apartamento con direccion y numero: " + direccionAp +" "+numeroAp  + " NO EXISTE\n";    				
    			}
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	public void buscarApartamentosPorDireccion( )
    {
		String direccionAp = JOptionPane.showInputDialog (this, "Direccion del edificio del apartamento?", "Buscar Apartamento por direccion", JOptionPane.QUESTION_MESSAGE);

    	try 
    	{
			
			List<Apartamento> lista = alohAndes.darApartamentosPorDireccion(direccionAp);
			String resultado = "En listarApartamento";
			resultado +=  "\n" + listarApartamentoNVO (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	/* ****************************************************************
	 * 			CRUD de Habitacion Hotel
	 *****************************************************************/

	 public void adiccionarHabitacionHotel( )
	 {
		 try 
		 {
			 String tipo = JOptionPane.showInputDialog (this, "Tipo de habitacion?", "Adicionar Habitacion Hotel", JOptionPane.QUESTION_MESSAGE);
			 String numeroHabitacion = JOptionPane.showInputDialog (this, "Numero de habitacion Apartamento?", "Adicionar Habitacion Hotel", JOptionPane.QUESTION_MESSAGE);
			 int tamano = Integer.parseInt(JOptionPane.showInputDialog (this, "Ingrese el tamano en metros de la habitación?", "Adicionar Habitacion Hotel", JOptionPane.QUESTION_MESSAGE));
			 String direccionHotel = JOptionPane.showInputDialog (this, "Cual es la direccion del hotel?", "Adicionar Habitacion Hotel", JOptionPane.QUESTION_MESSAGE);
 
			 if (tipo != null && numeroHabitacion != null && direccionHotel != null && tamano != 0)
			 {
				 VOHabitacionHotel ap = alohAndes.adicionarHabitacionHotel (tipo, numeroHabitacion, tamano, direccionHotel);
				 if (ap == null)
				 {
					 throw new Exception ("No se pudo crear una habitacion de hotel con direccion: " + direccionHotel + " y numero: " + numeroHabitacion);
				 }
				 String resultado = "En adicionarHabitacionHotel\n\n";
				 resultado += "Habitacion Hotel adicionada exitosamente: " + ap;
				 resultado += "\n Operación terminada";
				 panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
 
	 public void listarHabitacionesHotel( )
	 {
		 try 
		 {
			 List <VOHabitacionHotel> lista = alohAndes.darVoHabitacionesHotel();
 
			 String resultado = "En listarHabitacionesHotel";
			 resultado +=  "\n" + listarHabitacionHotel (lista);
			 panelDatos.actualizarInterfaz(resultado);
			 resultado += "\n Operación terminada";
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
 
	 public void eliminarHabitacionHotelPorDireccionHotel ( )
	 {
		 try 
		 {
			 String direccionHotel = JOptionPane.showInputDialog (this, "Direccion del Hotel que tienen las habitaciones?", "Borrar las Habitaciones en el Hotel: ", JOptionPane.QUESTION_MESSAGE);
			 
			 if (direccionHotel != null)
			 {
				 long apEliminados = alohAndes.eliminarHabitacionHotelPorDireccionHotel(direccionHotel);

				 String resultado = "En eliminar Habitacion Hotel\n\n";
				 resultado += apEliminados + " Habitaciones Hotel eliminadas\n";
				 resultado += "\n Operación terminada";
				 panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }

	 public void  eliminarHabitacionHotelPorDireccionYNumero ( )
	 {
		 try 
		 {
			 String direccionHotel = JOptionPane.showInputDialog (this, "Direccion del Hotel que tiene la habitacion?", "Borrar la Habitacion en el Hotel: ", JOptionPane.QUESTION_MESSAGE);
			 String numeroHabitacion = JOptionPane.showInputDialog (this, "Numero de habitacion?", "Borrar la Habitacion en el Hotel", JOptionPane.QUESTION_MESSAGE);
 
			 if (direccionHotel != null && numeroHabitacion != null)
			 {
				 long apEliminados = alohAndes.eliminarHabitacionHotelPorDireccionYNumero(direccionHotel, numeroHabitacion);
 
				 String resultado = "En eliminar Habitacion Hotel\n\n";
				 resultado += apEliminados + " Habitaciones Hotel eliminadas\n";
				 resultado += "\n Operación terminada";
				 panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
			 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
 
	 public void buscarHabitacionHotelPorDireccionYNumeroHabitacion ()
	 {
		 try 
		 {
			 String direccionHotel = JOptionPane.showInputDialog (this, "Direccion del Hotel que tiene la habitacion?", "Buscar Habitacion Hotel por direccion", JOptionPane.QUESTION_MESSAGE);
			 String numeroHabitacion = JOptionPane.showInputDialog (this, "Numero de habitacion?", "Buscar Habitacion Hotel por direccion", JOptionPane.QUESTION_MESSAGE);
 
			 if (direccionHotel != null && numeroHabitacion != null)
			 {
				 VOHabitacionHotel habitacionHotel = alohAndes.darHabitacionHotelPorNumeroHabitacionYDireccionHotel(numeroHabitacion, direccionHotel) ;
				 String resultado = "En buscar Habitacion Hotel\n\n";
				 if (habitacionHotel != null)
				 {
					 resultado += "La Habitacion Hotel es: " + habitacionHotel;
				 }
				 else
				 {
					 resultado += "Una Habitacion Hotel con direccion y numero: " + direccionHotel +" "+numeroHabitacion  + " NO EXISTE\n";    				
				 }
				 resultado += "\n Operación terminada";
				 panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
			 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }

	 public void buscarHabitacionHotelPorDireccion( )
	 {
		 String direccionHotel = JOptionPane.showInputDialog (this, "Direccion del Hotel que tiene la habitacion?", "Buscar Habitacion Hotel por direccion", JOptionPane.QUESTION_MESSAGE);
 
		 try 
		 {
			 List<HabitacionHotel> lista = alohAndes.darHabitacionHotelesPorDireccion(direccionHotel);
			 String resultado = "En listarHabitacionesHotel";
			 resultado +=  "\n" + listarHabitacionHotelNVO (lista);
			 panelDatos.actualizarInterfaz(resultado);
			 resultado += "\n Operación terminada";
		 } 
		 catch (Exception e) 
		 {
			 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }

	 public void darHabitacionesHotel ()
	 {
		 try 
		 {
			 List<HabitacionHotel> lista = alohAndes.darHabitacionesHotel();
			 String resultado = "En listarHabitacionesHotel";
			 resultado +=  "\n" + listarHabitacionHotelNVO (lista);
			 panelDatos.actualizarInterfaz(resultado);
			 resultado += "\n Operación terminada";
		 } 
		 catch (Exception e) 
		 {
			 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }

	/* ****************************************************************
	 * 			CRUD de Cliente
	 *****************************************************************/

	 public void adicionarCliente( )
	 {
		 try 
		 {
			 String idCliente = JOptionPane.showInputDialog (this, "Id del Cliente?", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			 String tipoIdCliente = JOptionPane.showInputDialog (this, "Tipo del id del Cliente?", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			 String nombreCliente = JOptionPane.showInputDialog (this, "Nombre del Cliente?", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			 String tipoCliente = JOptionPane.showInputDialog (this, "Tipo de Cliente?", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			 
 
			 if (idCliente != null && tipoIdCliente != null && nombreCliente != null && tipoCliente != null )
			 {
				 VOCliente ap = alohAndes.adicionarCliente(idCliente, tipoIdCliente, nombreCliente, tipoCliente); 
				 if (ap == null)
				 {
					 throw new Exception ("No se pudo crear un Cliente con nombre: " + nombreCliente + " y id: " + idCliente);
				 }
				 String resultado = "En adicionarCliente\n\n";
				 resultado += "Cliente adicionado exitosamente: " + ap;
				 resultado += "\n Operación terminada";
				 panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
 
	 public void listarClientes( )
	 {
		 try 
		 {
			 List <VOCliente> lista = alohAndes.darVOClientes();
 
			 String resultado = "En listarClientes";
			 resultado +=  "\n" + listarCliente(lista);
			 panelDatos.actualizarInterfaz(resultado);
			 resultado += "\n Operación terminada";
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }

 
	 public void eliminarClientePorNombre ( )
	 {
		 try 
		 {
			 String nombreClienteStr = JOptionPane.showInputDialog (this, "Nombre del cliente?", "Borrar Cliente: ", JOptionPane.QUESTION_MESSAGE);
			 
			 if (nombreClienteStr != null)
			 {
			 
				 long apEliminados = alohAndes.eliminarClientePorNombre(nombreClienteStr);
 
				 String resultado = "En eliminar Cliente\n\n";
				 resultado += apEliminados + " Clientes eliminados\n";
				 resultado += "\n Operación terminada";
				 panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
 
	 public void eliminarClientePorId ( )
	 {
		 try 
		 {
			 String idClienteStr = JOptionPane.showInputDialog (this, "Id del Cliente?", "Borrar Cliente en el edificio: ", JOptionPane.QUESTION_MESSAGE);
			 
			 if (idClienteStr != null)
			 {
			 
				 long apEliminados = alohAndes.eliminarClientePorId(idClienteStr);
											 
 
				 String resultado = "En eliminar Cliente\n\n";
				 resultado += apEliminados + " Clientes eliminados\n";
				 resultado += "\n Operación terminada";
				 panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
 
	 
 
	 public void buscarClientePorId ()
	 {
		 try 
		 {
			 String idCl = JOptionPane.showInputDialog (this, "Id del Cliente?", "Buscar Cliente por id", JOptionPane.QUESTION_MESSAGE);
			 
			 if (idCl != null)
			 {
				 VOCliente cliente = alohAndes.darClientePorId(idCl) ;
				 String resultado = "En buscar Apartamentos\n\n";
				 if (cliente != null)
				 {
					 resultado += "El Cliente es: " + cliente;
				 }
				 else
				 {
					 resultado += "Un Cliente con id: " + idCl  + " NO EXISTE\n";    				
				 }
				 resultado += "\n Operación terminada";
				 panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
 
	 public void buscarClientePorNombre( )
	 {
		 String nombreCl = JOptionPane.showInputDialog (this, "Nombre del Cliente?", "Buscar Cliente por nombre", JOptionPane.QUESTION_MESSAGE);
 
		 try 
		 {
			 
			 List<Cliente> lista = alohAndes.darClientesPorNombre(nombreCl);
			 String resultado = "En listarCliente";
			 resultado +=  "\n" + listarClienteNVO (lista);
			 panelDatos.actualizarInterfaz(resultado);
			 resultado += "\n Operación terminada";
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }

	 /* ****************************************************************
	 * 			CRUD de Instalacion Habitacion Hotel
	 * *****************************************************************/
	public void adicionarInstalacionHabitacionHotel()
	{
		try 
		 {
			 String tipoInstalacion = JOptionPane.showInputDialog (this, "Tipo de Instalacion?", "Adicionar Instalacion", JOptionPane.QUESTION_MESSAGE);
			 String direccionHotel = JOptionPane.showInputDialog (this, "Direccion del Hotel?", "Adicionar Instalacion", JOptionPane.QUESTION_MESSAGE);
			 String numeroHabitacion = JOptionPane.showInputDialog (this, "Numero de Habitacion?", "Adicionar Instalacion", JOptionPane.QUESTION_MESSAGE);
 
			 if (tipoInstalacion != null && direccionHotel != null && numeroHabitacion != null)
			 {
				 VOInstalacionHabitacionHotel ap = alohAndes.adicionarInstalacionHabitacionHotel(tipoInstalacion, numeroHabitacion, direccionHotel); 
				 if (ap == null)
				 {
					 throw new Exception ("No se pudo crear una Instalacion Habitacion Hotel con la direccion del Hotel: " + direccionHotel + ", el numero de Habitacion: " + numeroHabitacion + " y el tipo de Instalacion: " + tipoInstalacion);
				 }
				 String resultado = "En adicionarInstalacionHabitacionHotel\n\n";
				 resultado += "Instalacion Habitacion Hotel adicionado exitosamente: " + ap;
				 resultado += "\n Instalacion Habitacion Hotel terminada";
				 panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
 
	public void listarInstalacionesHabitacionHotel( )
	{
		 try 
		 {
			 List <VOInstalacionHabitacionHotel> lista = alohAndes.darVOInstalacionesHabitacionHotel();
 
			 String resultado = "En listarInstalacionesHabitacionHotel";
			 resultado +=  "\n" + listarInstalacionHabitacionHotel(lista);
			 panelDatos.actualizarInterfaz(resultado);
			 resultado += "\n Operación terminada";
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
 
	 public void eliminarInstalacionHabitacionHotelPorTipoInstalacionYDireccionHotelHabitacionHotel( )
	 {
		try
		{
			String tipoInstalacion = JOptionPane.showInputDialog (this, "Tipo de Instalacion?", "Borrar Instalacion Habitacion Hotel por tipo de Instalacion y direccion del Hotel", JOptionPane.QUESTION_MESSAGE);
			String direccionHotel = JOptionPane.showInputDialog (this, "Direccion del Hotel?", "Borrar Instalacion Habitacion Hotel por tipo de Instalacion y direccion del Hotel", JOptionPane.QUESTION_MESSAGE);
			 
			 if (tipoInstalacion != null && direccionHotel != null)
			 {
				 long apEliminados = alohAndes.eliminarInstalacionHabitacionHotelPorTipoInstalacionYDireccionHotelHabitacionHotel(tipoInstalacion, direccionHotel);
				 String resultado = "En eliminar Instalacion Habitacion Hotel\n\n";
				 resultado += apEliminados + " Instalaciones Habitacion Hotel eliminados\n";
				 resultado += "\n Operación terminada";
				 panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
 
	 public void eliminarInstalacionHabitacionHotelPorTipoInstalacionDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel( )
	 {
		try
		{
			String tipoInstalacion = JOptionPane.showInputDialog (this, "Tipo de Instalacion?", "Borrar Instalacion Habitacion Hotel por tipo de Instalacion, direccion del Hotel y numero de Habitacion", JOptionPane.QUESTION_MESSAGE);
			String direccionHotel = JOptionPane.showInputDialog (this, "Direccion del Hotel?", "Borrar Instalacion Habitacion Hotel por tipo de Instalacion, direccion del Hotel y numero de Habitacion", JOptionPane.QUESTION_MESSAGE);
			String numeroHabitacion = JOptionPane.showInputDialog (this, "Numero de Habitacion?", "Borrar Instalacion Habitacion Hotel por tipo de Instalacion, direccion del Hotel y numero de Habitacion", JOptionPane.QUESTION_MESSAGE);
 
			 if (tipoInstalacion != null && direccionHotel != null && numeroHabitacion != null)
			 {
				 long apEliminados = alohAndes.eliminarInstalacionHabitacionHotelPorTipoInstalacionDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel(tipoInstalacion, direccionHotel, numeroHabitacion);
				 String resultado = "En eliminar Instalacion Habitacion Hotel\n\n";
				 resultado += apEliminados + " Instalaciones Habitacion Hotel eliminados\n";
				 resultado += "\n Operación terminada";
				 panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
			 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
	 
	 public void eliminarInstalacionHabitacionHotelPorDireccionHotelHabitacionHotel()
	 {
		try
		{
			String direccionHotel = JOptionPane.showInputDialog (this, "Direccion del Hotel?", "Borrar Instalacion Habitacion Hotel por direccion del Hotel", JOptionPane.QUESTION_MESSAGE);
 
			 if (direccionHotel != null)
			 {
				 long apEliminados = alohAndes.eliminarInstalacionHabitacionHotelPorDireccionHotelHabitacionHotel(direccionHotel);
				 String resultado = "En eliminar Instalacion Habitacion Hotel\n\n";
				 resultado += apEliminados + " Instalaciones Habitacion Hotel eliminados\n";
				 resultado += "\n Operación terminada";
				 panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
			 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
	 
	 public void eliminarInstalacionHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel()
	 {
		try
		{
			String direccionHotel = JOptionPane.showInputDialog (this, "Direccion del Hotel?", "Borrar Instalacion Habitacion Hotel por direccion del Hotel y numero de Habitacion", JOptionPane.QUESTION_MESSAGE);
			String numeroHabitacion = JOptionPane.showInputDialog (this, "Numero de Habitacion?", "Borrar Instalacion Habitacion Hotel por direccion del Hotel y numero de Habitacion", JOptionPane.QUESTION_MESSAGE);
 
			 if (direccionHotel != null && numeroHabitacion != null)
			 {
				 long apEliminados = alohAndes.eliminarInstalacionHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel(direccionHotel, numeroHabitacion);
				 String resultado = "En eliminar Instalacion Habitacion Hotel\n\n";
				 resultado += apEliminados + " Instalaciones Habitacion Hotel eliminados\n";
				 resultado += "\n Operación terminada";
				 panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
			 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
	 
	 public void buscarInstalacionesHabitacionHotel()
	 {
		try 
		 {
			 List<InstalacionHabitacionHotel> lista = alohAndes.darInstalacionesHabitacionHotel();
			 String resultado = "En listarHabitacionesHotel";
			 resultado +=  "\n" + listarInstalacionHabitacionHotelNVO(lista);
			 panelDatos.actualizarInterfaz(resultado);
			 resultado += "\n Operación terminada";
		 } 
		 catch (Exception e) 
		 {
			 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }

	 public void buscarInstalacionHabitacionHotelPorDireccionHotelHabitacionHotel( )
	 {
		 try 
		 { 
			String direccionHotel = JOptionPane.showInputDialog (this, "Direccion del Hotel que tiene la habitacion?", "Buscar Habitacion Hotel por direccion", JOptionPane.QUESTION_MESSAGE);
		
			 if(direccionHotel != null)
			 {
			 List<InstalacionHabitacionHotel> lista = alohAndes.darInstalacionHabitacionHotelPorDireccionHotelHabitacionHotel(direccionHotel);
			 String resultado = "En listarHabitacionesHotel";
			 resultado +=  "\n" + listarInstalacionHabitacionHotelNVO(lista);
			 panelDatos.actualizarInterfaz(resultado);
			 resultado += "\n Operación terminada";
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
			 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }

	 public void buscarInstalacionHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel()
	 {
		try
		{
			 String direccionHotel = JOptionPane.showInputDialog (this, "Direccion del Hotel que tiene la habitacion?", "Buscar Habitacion Hotel por direccion y numero de habitacion", JOptionPane.QUESTION_MESSAGE);
			 String numeroHabitacion = JOptionPane.showInputDialog (this, "Numero de Habitacion?", "Buscar Habitacion Hotel por direccion y numero de habitacion", JOptionPane.QUESTION_MESSAGE);

			 if(direccionHotel != null && numeroHabitacion != null)
			 {
			 List<InstalacionHabitacionHotel> lista = alohAndes.darInstalacionHabitacionHotelPorDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel(direccionHotel, numeroHabitacion);
			 String resultado = "En listarHabitacionesHotel";
			 resultado +=  "\n" + listarInstalacionHabitacionHotelNVO(lista);
			 panelDatos.actualizarInterfaz(resultado);
			 resultado += "\n Operación terminada";
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 }
		 catch (Exception e)
		 {
			 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }

	 public void buscarInstalacionHabitacionHotelPorTipoInstalacionDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel()
	 {
		 try
		 {
			String tipoInstalacion = JOptionPane.showInputDialog (this, "Tipo de Instalacion?", "Buscar Habitacion Hotel por tipo de instalacion, direccion y numero de habitacion", JOptionPane.QUESTION_MESSAGE);
			String direccionHotel = JOptionPane.showInputDialog (this, "Direccion del Hotel que tiene la habitacion?", "Buscar Habitacion Hotel por tipo de instalacion, direccion y numero de habitacion", JOptionPane.QUESTION_MESSAGE);
			String numeroHabitacion = JOptionPane.showInputDialog (this, "Numero de Habitacion?", "Buscar Habitacion Hotel por tipo de instalacion, direccion y numero de habitacion", JOptionPane.QUESTION_MESSAGE);

			if (tipoInstalacion != null && direccionHotel != null && numeroHabitacion != null)
				{
					VOInstalacionHabitacionHotel instalacionHabitacionHotel = alohAndes.darInstalacionHabitacionHotelPorTipoInstalacionDireccionHotelHabitacionHotelYNumeroHabitacionHabitacionHotel(tipoInstalacion, direccionHotel, numeroHabitacion) ;
					String resultado = "En buscar Instalacion Habitacion Hotel\n\n";
					if (instalacionHabitacionHotel != null)
					{
						resultado += "La Instalacion Habitacion Hotel es: " + instalacionHabitacionHotel;
					}
					else
					{
						resultado += "Una Instalacion Habitacion Hotel con direccion y numero: " + direccionHotel +" "+numeroHabitacion  + " y tipo de instalacion: " + tipoInstalacion + " NO EXISTE\n";    				
					}
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}
		 } 
		 catch (Exception e) 
		 {
			 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }

	 /* ****************************************************************
	 * 			CRUD de Operador
	 *****************************************************************/

	 public void adicionarOperador()
	 {
		 try 
		 {
			 String idOperador = JOptionPane.showInputDialog (this, "Id del Operador?", "Adicionar Operador", JOptionPane.QUESTION_MESSAGE);
			 String nombreOperador = JOptionPane.showInputDialog (this, "Nombre del Operador?", "Adicionar Operador", JOptionPane.QUESTION_MESSAGE);
			 String tipoOperador = JOptionPane.showInputDialog (this, "Tipo del documento del Operador?", "Adicionar Operador", JOptionPane.QUESTION_MESSAGE);
			 int validacionCamaraDeComercioEmpresa = Integer.parseInt(JOptionPane.showInputDialog (this, "Tiene validacion de la camara de comercio? 1 para si, 0 para no o no aplica", "Adicionar Operador", JOptionPane.QUESTION_MESSAGE));
			 int validacionSuperTurismoEmpresa = Integer.parseInt(JOptionPane.showInputDialog (this, "Tiene validacion de la super intendecia de turismo? 1 para si, 0 para no o no aplica", "Adicionar Operador", JOptionPane.QUESTION_MESSAGE));
			 int miembroComunidadUniversitariaPersona = Integer.parseInt(JOptionPane.showInputDialog (this, "Pertenece a la comunidad Universitaria? 1 para si, 0 para no o no aplica", "Adicionar Operador", JOptionPane.QUESTION_MESSAGE));
 
			 if (idOperador != null && nombreOperador != null && tipoOperador != null)
			 {
				 VOOperador op = alohAndes.adicionarOperador(idOperador, nombreOperador, tipoOperador, validacionCamaraDeComercioEmpresa, validacionSuperTurismoEmpresa, miembroComunidadUniversitariaPersona);
				 if (op == null)
				 {
					 throw new Exception ("No se pudo crear un Operador con nombre: " + nombreOperador + " y id: " + idOperador);
				 }
				 String resultado = "En adicionarOperador\n\n";
				 resultado += "Operador adicionado exitosamente: " + op;
				 resultado += "\n Operación terminada";
				 panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
 
	 public void listarOperadores( )
	 {
		 try 
		 {
			 List <VOOperador> lista = alohAndes.darVOOperadores();
 
			 String resultado = "En listarOperadores";
			 resultado +=  "\n" + listarOperador(lista);
			 panelDatos.actualizarInterfaz(resultado);
			 resultado += "\n Operación terminada";
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
 
	 public void eliminarOperadorPorNombre ( )
	 {
		 try 
		 {
			 String nombreOperadorStr = JOptionPane.showInputDialog (this, "Nombre del operador?", "Borrar Operador: ", JOptionPane.QUESTION_MESSAGE);
			 
			 if (nombreOperadorStr != null)
			 {
			 
				 long apEliminados = alohAndes.eliminarOperadorPorNombre(nombreOperadorStr);
 
				 String resultado = "En eliminar Operador\n\n";
				 resultado += apEliminados + " Operadores eliminados\n";
				 resultado += "\n Operación terminada";
				 panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
 
	 public void eliminarOperadorPorId ( )
	 {
		 try 
		 {
			 String idOperadorStr = JOptionPane.showInputDialog (this, "Id del Operador?", "Borrar Operador en el edificio: ", JOptionPane.QUESTION_MESSAGE);
			 
			 if (idOperadorStr != null)
			 {
			 
				 long apEliminados = alohAndes.eliminarOperadorPorId(idOperadorStr);
											 
 
				 String resultado = "En eliminar Operador\n\n";
				 resultado += apEliminados + " Operador eliminados\n";
				 resultado += "\n Operación terminada";
				 panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
 
	 
 
	 public void buscarOperadorPorId ()
	 {
		 try 
		 {
			 String idOP = JOptionPane.showInputDialog (this, "Id del Operador?", "Buscar Operador por id", JOptionPane.QUESTION_MESSAGE);
			 
			 if (idOP != null)
			 {
				 VOOperador operador = alohAndes.darOperadorPorId(idOP) ;
				 String resultado = "En buscar Operador\n\n";
				 if (operador != null)
				 {
					 resultado += "El Operador es: " + operador;
				 }
				 else
				 {
					 resultado += "Un Operador con id: " + idOP  + " NO EXISTE\n";    				
				 }
				 resultado += "\n Operación terminada";
				 panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
 
	 public void buscarOperadorPorNombre( )
	 {
		 String nombreOp = JOptionPane.showInputDialog (this, "Nombre del Operador?", "Buscar Operador por nombre", JOptionPane.QUESTION_MESSAGE);
 
		 try 
		 {
			 
			 List<Operador> lista = alohAndes.darOperadorPorNombre(nombreOp);
			 String resultado = "En listarOperador";
			 resultado +=  "\n" + listarOperadorNVO(lista);
			 panelDatos.actualizarInterfaz(resultado);
			 resultado += "\n Operación terminada";
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }

	 public void mostrarDineroPorOperador()
	 {
		try 
		 {
			 List <String> lista = alohAndes.mostrarDineroRecibidoPorCadaOperador();
 
			 String resultado = "En listarOperadores";
			 resultado +=  "\n" + listarDineroPorOperador(lista);
			 panelDatos.actualizarInterfaz(resultado);
			 resultado += "\n Operación terminada";
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	 }
	 /* ****************************************************************
	 * 			CRUD de Oferta
	 *****************************************************************/
	public void adicionarOferta( )
    {
    	try 
    	{
    		String opcion = JOptionPane.showInputDialog (this, "Que oferta desea? HabitacionHostal, HabitacionHotel, Apartamento, ViviendaUniversitaria, ViviendaHabitacion o ViviendaExpress", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);
			if (opcion.equals("HabitacionHotel"))
			{
				Date fechaInicial = Date.valueOf(JOptionPane.showInputDialog (this, "Cual es la fecha Inicial? yyyy-mm-dd", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				int duracionContratoDias = Integer.parseInt(JOptionPane.showInputDialog (this, "Cuanto dura el contrato? ", JOptionPane.QUESTION_MESSAGE));
				int costoContrato = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual es el costo del contrato?", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				int precioEspecial = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual es el precio especial? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				String condicionPrecioEspecial = JOptionPane.showInputDialog (this, "Cual es la condicion de oferta especial? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);
				int costoAdicionalServicios = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual el costo adicional de los servicios? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				int costoSeguroArrendamiento = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual el costo del seguro de arrendamiento? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				String idOperador = JOptionPane.showInputDialog (this, "Cual es el id del operador? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);
				/* -------------------------------------------------------------------------------------------------**/
				String direccionHotelHabitacionHotel = JOptionPane.showInputDialog (this, "Cual es la direccion del hotel? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);
				String numeroHabitacionHabitacionHotel = JOptionPane.showInputDialog (this, "Cual es el numero de habitacion? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);

				VOOferta ap = alohAndes.adicionarOfertaHabitacionHotel (fechaInicial,duracionContratoDias, costoContrato, precioEspecial, condicionPrecioEspecial, costoAdicionalServicios, costoSeguroArrendamiento, idOperador,direccionHotelHabitacionHotel, numeroHabitacionHabitacionHotel);
        		if (ap == null)
        		{
        			throw new Exception ("No se pudo crear la oferta");
        		}
        		String resultado = "En adicionarOferta\n\n";
        		resultado += "Oferta adicionado exitosamente: " + ap;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
			} 
			else if (opcion.equals("HabitacionHostal"))
			{
				Date fechaInicial = Date.valueOf(JOptionPane.showInputDialog (this, "Cual es la fecha Inicial? yyyy-mm-dd", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				int duracionContratoDias = Integer.parseInt(JOptionPane.showInputDialog (this, "Cuanto dura el contrato? ", JOptionPane.QUESTION_MESSAGE));
				int costoContrato = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual es el costo del contrato?", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				int precioEspecial = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual es el precio especial? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				String condicionPrecioEspecial = JOptionPane.showInputDialog (this, "Cual es la condicion de oferta especial? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);
				int costoAdicionalServicios = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual el costo adicional de los servicios? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				int costoSeguroArrendamiento = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual el costo del seguro de arrendamiento? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				String idOperador = JOptionPane.showInputDialog (this, "Cual es el id del operador? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);
				/* -------------------------------------------------------------------------------------------------**/
				String direccionHotelHabitacionHostal = JOptionPane.showInputDialog (this, "Cual es la direccion del hostal? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);
				String numeroHabitacionHabitacionHostal = JOptionPane.showInputDialog (this, "Cual es el numero de habitacion? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);

				VOOferta ap = alohAndes.adicionarOfertaHabitacionHostal (fechaInicial,duracionContratoDias, costoContrato, precioEspecial, condicionPrecioEspecial, costoAdicionalServicios, costoSeguroArrendamiento, idOperador,direccionHotelHabitacionHostal, numeroHabitacionHabitacionHostal);
        		if (ap == null)
        		{
        			throw new Exception ("No se pudo crear la oferta");
        		}
        		String resultado = "En adicionarOferta\n\n";
        		resultado += "Oferta adicionado exitosamente: " + ap;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
			} 
			else if (opcion.equals("Apartamento"))
			{
				Date fechaInicial = Date.valueOf(JOptionPane.showInputDialog (this, "Cual es la fecha Inicial? yyyy-mm-dd", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				int duracionContratoDias = Integer.parseInt(JOptionPane.showInputDialog (this, "Cuanto dura el contrato? ", JOptionPane.QUESTION_MESSAGE));
				int costoContrato = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual es el costo del contrato?", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				int precioEspecial = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual es el precio especial? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				String condicionPrecioEspecial = JOptionPane.showInputDialog (this, "Cual es la condicion de oferta especial? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);
				int costoAdicionalServicios = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual el costo adicional de los servicios? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				int costoSeguroArrendamiento = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual el costo del seguro de arrendamiento? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				String idOperador = JOptionPane.showInputDialog (this, "Cual es el id del operador? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);
				/* -------------------------------------------------------------------------------------------------**/
				String direccionApartamento = JOptionPane.showInputDialog (this, "Cual es la direccion del edificio? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);
				String numeroApartamento = JOptionPane.showInputDialog (this, "Cual es el numero de apartamento? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);

				VOOferta ap = alohAndes.adicionarOfertaApartamento (fechaInicial,duracionContratoDias, costoContrato, precioEspecial, condicionPrecioEspecial, costoAdicionalServicios, costoSeguroArrendamiento, idOperador,direccionApartamento, numeroApartamento);
        		if (ap == null)
        		{
        			throw new Exception ("No se pudo crear la oferta");
        		}
        		String resultado = "En adicionarOferta\n\n";
        		resultado += "Oferta adicionado exitosamente: " + ap;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
			} 
			else if (opcion.equals("ViviendaUniversitaria"))
			{
				Date fechaInicial = Date.valueOf(JOptionPane.showInputDialog (this, "Cual es la fecha Inicial? yyyy-mm-dd", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				int duracionContratoDias = Integer.parseInt(JOptionPane.showInputDialog (this, "Cuanto dura el contrato? ", JOptionPane.QUESTION_MESSAGE));
				int costoContrato = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual es el costo del contrato?", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				int precioEspecial = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual es el precio especial? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				String condicionPrecioEspecial = JOptionPane.showInputDialog (this, "Cual es la condicion de oferta especial? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);
				int costoAdicionalServicios = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual el costo adicional de los servicios? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				int costoSeguroArrendamiento = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual el costo del seguro de arrendamiento? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				String idOperador = JOptionPane.showInputDialog (this, "Cual es el id del operador? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);
				/* -------------------------------------------------------------------------------------------------**/
				String direccionViviendaUniversitaria = JOptionPane.showInputDialog (this, "Cual es la direccion de la vivienda universitaria? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);
				String numeroApartamentoViviendaUniversitaria = JOptionPane.showInputDialog (this, "Cual es el numero del apartamento? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);

				VOOferta ap = alohAndes.adicionarOfertaViviendaUniversitaria (fechaInicial,duracionContratoDias, costoContrato, precioEspecial, condicionPrecioEspecial, costoAdicionalServicios, costoSeguroArrendamiento, idOperador,direccionViviendaUniversitaria, numeroApartamentoViviendaUniversitaria);
        		if (ap == null)
        		{
        			throw new Exception ("No se pudo crear la oferta");
        		}
        		String resultado = "En adicionarOferta\n\n";
        		resultado += "Oferta adicionado exitosamente: " + ap;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
			}
			else if (opcion.equals("ViviendaHabitacion"))
			{
				Date fechaInicial = Date.valueOf(JOptionPane.showInputDialog (this, "Cual es la fecha Inicial? yyyy-mm-dd", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				int duracionContratoDias = Integer.parseInt(JOptionPane.showInputDialog (this, "Cuanto dura el contrato? ", JOptionPane.QUESTION_MESSAGE));
				int costoContrato = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual es el costo del contrato?", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				int precioEspecial = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual es el precio especial? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				String condicionPrecioEspecial = JOptionPane.showInputDialog (this, "Cual es la condicion de oferta especial? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);
				int costoAdicionalServicios = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual el costo adicional de los servicios? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				int costoSeguroArrendamiento = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual el costo del seguro de arrendamiento? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				String idOperador = JOptionPane.showInputDialog (this, "Cual es el id del operador? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);
				/* -------------------------------------------------------------------------------------------------**/
				String direccionViviendaHabitacion = JOptionPane.showInputDialog (this, "Cual es la direccion de la vivienda habitacion? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);
				String numeroApartamentoViviendaHabitacion = JOptionPane.showInputDialog (this, "Cual es el numero de Apartamento? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);

				VOOferta ap = alohAndes.adicionarOfertaViviendaHabitacion (fechaInicial,duracionContratoDias, costoContrato, precioEspecial, condicionPrecioEspecial, costoAdicionalServicios, costoSeguroArrendamiento, idOperador,direccionViviendaHabitacion, numeroApartamentoViviendaHabitacion);
        		if (ap == null)
        		{
        			throw new Exception ("No se pudo crear la oferta");
        		}
        		String resultado = "En adicionarOferta\n\n";
        		resultado += "Oferta adicionado exitosamente: " + ap;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
			}
			else if (opcion.equals("ViviendaExpress"))
			{
				Date fechaInicial = Date.valueOf(JOptionPane.showInputDialog (this, "Cual es la fecha Inicial? yyyy-mm-dd", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				int duracionContratoDias = Integer.parseInt(JOptionPane.showInputDialog (this, "Cuanto dura el contrato? ", JOptionPane.QUESTION_MESSAGE));
				int costoContrato = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual es el costo del contrato?", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				int precioEspecial = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual es el precio especial? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				String condicionPrecioEspecial = JOptionPane.showInputDialog (this, "Cual es la condicion de oferta especial? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);
				int costoAdicionalServicios = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual el costo adicional de los servicios? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				int costoSeguroArrendamiento = Integer.parseInt(JOptionPane.showInputDialog (this, "Cual el costo del seguro de arrendamiento? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE));
				String idOperador = JOptionPane.showInputDialog (this, "Cual es el id del operador? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);
				/* -------------------------------------------------------------------------------------------------**/
				String direccionViviendaExpress = JOptionPane.showInputDialog (this, "Cual es la direccion de vivienda express? ", "Adicionar Oferta", JOptionPane.QUESTION_MESSAGE);

				VOOferta ap = alohAndes.adicionarOfertaViviendaExpress (fechaInicial,duracionContratoDias, costoContrato, precioEspecial, condicionPrecioEspecial, costoAdicionalServicios, costoSeguroArrendamiento, idOperador,direccionViviendaExpress);
        		if (ap == null)
        		{
        			throw new Exception ("No se pudo crear la oferta");
        		}
        		String resultado = "En adicionarOferta\n\n";
        		resultado += "Oferta adicionado exitosamente: " + ap;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
			} else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
	public void eliminarOfertaPorId ( )
    {
    	try 
    	{
    		long idOferta = Long.parseLong(JOptionPane.showInputDialog (this, "Cual es el id?", "Borrar Oferta por id: ", JOptionPane.QUESTION_MESSAGE));

    		if (idOferta > -1)
    		{
    		
    			long ofEliminado = alohAndes.eliminarOfertaPorId(idOferta);
											

    			String resultado = "En eliminar Oferta\n\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }

	/* ****************************************************************
	 * 			CRUD de Reserva
	 *****************************************************************/
	public void listarReservas( )
	{
		 try 
		 {
			 List <Reserva> lista = alohAndes.darReservas();
 
			 String resultado = "En listarReservas";
			 resultado +=  "\n" + listarReservas(lista);
			 panelDatos.actualizarInterfaz(resultado);
			 resultado += "\n Operación terminada";
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	}
	
	public void listarReservasPorIdCliente( )
	{
		 String idCliente = JOptionPane.showInputDialog (this, "Id del Cliente?", "Adicionar Reserva", JOptionPane.QUESTION_MESSAGE);
		 
		 try 
		 {
			 List <Reserva> lista = alohAndes.darReservasPorIdCliente(idCliente);
 
			 String resultado = "En listarReservasPorIdCliente";
			 resultado +=  "\n" + listarReservas(lista);
			 panelDatos.actualizarInterfaz(resultado);
			 resultado += "\n Operación terminada";
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	}
	
	public void listarReservaPorIdOferta( )
	{
		 long idOferta = Long.valueOf(JOptionPane.showInputDialog (this, "Id de Oferta?", "Adicionar Reserva", JOptionPane.QUESTION_MESSAGE));
		 if (idOferta != 0)
		 {
			 Reserva reserva = alohAndes.darReservaPorIdOferta(idOferta);
			 String resultado = "En buscar Reserva por IdOferta\n\n";
			 if (reserva != null)
			 {
				 resultado += "La Reserva es: " + reserva;
			 }
			 else
			 {
				 resultado += "Una Reserva con IdOferta: " + idOferta  + " NO EXISTE\n";    				
			 }
			 resultado += "\n Operación terminada";
			 panelDatos.actualizarInterfaz(resultado);
		 }
		 else
		 {
			 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
		 }
	}
	
	public void adicionarReserva( )
	{
		 try 
		 {
			 long idOferta = Long.valueOf(JOptionPane.showInputDialog (this, "Id del operador?", "Adicionar Reserva", JOptionPane.QUESTION_MESSAGE));
			 String idCliente = JOptionPane.showInputDialog (this, "Id del Cliente?", "Adicionar Reserva", JOptionPane.QUESTION_MESSAGE);
			 int nombreCliente = Integer.valueOf(JOptionPane.showInputDialog (this, "Nombre del Cliente?", "Adicionar Reserva", JOptionPane.QUESTION_MESSAGE));
			 
			 
 
			 if (idCliente != null)
			 {
				 Reserva ap = alohAndes.adicionarReserva(idOferta, idCliente, nombreCliente); 
				 if (ap == null)
				 {
					 throw new Exception ("No se pudo crear una Reserva con idOferta: " + idOferta );
				 }
				 String resultado = "En adicionarOferta\n\n";
				 resultado += "Oferta adicionado exitosamente: " + ap;
				 resultado += "\n Operación terminada";
				 panelDatos.actualizarInterfaz(resultado);
			 }
			 else
			 {
				 panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			 }
		 } 
		 catch (Exception e) 
		 {
 //			e.printStackTrace();
			 String resultado = generarMensajeError(e);
			 panelDatos.actualizarInterfaz(resultado);
		 }
	}
	
	 
	

	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de AlohAndes
	 */
	public void mostrarLogAlohAndes ()
	{
		mostrarArchivo ("alohandes.log");
	}
	
	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}
	
	/**
	 * Limpia el contenido del log de alohAndes
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogAlohAndes ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("alohAndes.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de alohAndes ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	

	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		mostrarArchivo ("data/00-ST-AlohAndesJDO.pdf");
	}
	
	/**
	 * Muestra el modelo conceptual de AlohAndes
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Modelo Conceptual AlohAndes.pdf");
	}
	
	/**
	 * Muestra el esquema de la base de datos de AlohAndes
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/Esquema BD AlohAndes.pdf");
	}
	
	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaAlohAndes.sql");
	}
	
	/**
	 * Muestra la arquitectura de referencia para AlohAndes
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}
	
	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}
	
	/**
     * Muestra la información acerca del desarrollo de esta apicación
     */
    public void acercaDe ()
    {
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: AlohAndes Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Germán Bravo\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += " * Revisado por: Claudia Jiménez, Christian Ariza\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
    }
    

	/* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/
    /**
     * Genera una cadena de caracteres con la lista de los tipos de bebida recibida: una línea por cada tipo de bebida
     * @param lista - La lista con los tipos de bebida
     * @return La cadena con una líea para cada tipo de bebida recibido
     */
    private String listarApartamento(List<VOApartamento> lista) 
    {
    	String resp = "Los apartamentos existentes son:\n";
    	int i = 1;
        for (VOApartamento ap : lista)
        {
        	resp += i++ + ". " + ap.toString() + "\n";
        }
        return resp;
	}
    
    private String listarReservas(List<Reserva> lista) 
    {
    	String resp = "Los apartamentos existentes son:\n";
    	int i = 1;
        for (Reserva ap : lista)
        {
        	resp += i++ + ". " + ap.toString() + "\n";
        }
        return resp;
	}
	private String listarApartamentoNVO(List<Apartamento> lista) 
    {
    	String resp = "Los apartamentos existentes son:\n";
    	int i = 1;
        for (Apartamento ap : lista)
        {
        	resp += i++ + ". " + ap.toString() + "\n";
        }
        return resp;
	}

	/* -------------------- LISTAR CLIENTE ----------------------------- */
	private String listarCliente(List<VOCliente> lista) 
    {
    	String resp = "Los clientes existentes son:\n";
    	int i = 1;
        for (VOCliente cl : lista)
        {
        	resp += i++ + ". " + cl.toString() + "\n";
        }
        return resp;
	}
	private String listarClienteNVO(List<Cliente> lista) 
    {
    	String resp = "Los apartamentos existentes son:\n";
    	int i = 1;
        for (Cliente cl : lista)
        {
        	resp += i++ + ". " + cl.toString() + "\n";
        }
        return resp;
	}

	/* -------------------- LISTAR HABITACION HOTEL ----------------------------- */
	private String listarHabitacionHotel(List<VOHabitacionHotel> lista) 
    {
    	String resp = "Las habitaciones hotel existentes son:\n";
    	int i = 1;
        for (VOHabitacionHotel cl : lista)
        {
        	resp += i++ + ". " + cl.toString() + "\n";
        }
        return resp;
	}
	private String listarHabitacionHotelNVO(List<HabitacionHotel> lista)
	{
		String resp = "Las habitaciones existentes son:\n";
		int i = 1;
		for (HabitacionHotel cl : lista)
		{
			resp += i++ + ". " + cl.toString() + "\n";
		}
		return resp;
	}

	/* -------------------- LISTAR INSTALACION HABITACION HOTEL ----------------------------- */
	private String listarInstalacionHabitacionHotel(List<VOInstalacionHabitacionHotel> lista) 
    {
    	String resp = "Las Instalaciones habitacion hotel existentes son:\n";
    	int i = 1;
        for (VOInstalacionHabitacionHotel cl : lista)
        {
        	resp += i++ + ". " + cl.toString() + "\n";
        }
        return resp;
	}
	private String listarInstalacionHabitacionHotelNVO(List<InstalacionHabitacionHotel> lista)
	{
		String resp = "Las Instalaciones habitacion hotel existentes son:\n";
		int i = 1;
		for (InstalacionHabitacionHotel cl : lista)
		{
			resp += i++ + ". " + cl.toString() + "\n";
		}
		return resp;
	}

	/* -------------------- LISTAR OPERADOR ----------------------------- */
	private String listarOperador(List<VOOperador> lista) 
    {
    	String resp = "Los operadores existentes son:\n";
    	int i = 1;
        for (VOOperador op : lista)
        {
        	resp += i++ + ". " + op.toString() + "\n";
        }
        return resp;
	}
	private String listarDineroPorOperador(List<String> lista) 
    {
    	String resp = "Los operadores existentes son:\n";
    	int i = 1;
        for (String op : lista)
        {
        	resp += i++ + ". " + op.toString() + "\n";
        }
        return resp;
	}
	private String listarOperadorNVO(List<Operador> lista) 
    {
    	String resp = "Los apartamentos existentes son:\n";
    	int i = 1;
        for (Operador op : lista)
        {
        	resp += i++ + ". " + op.toString() + "\n";
        }
        return resp;
	}

    /**
     * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
     * @param e - La excepción recibida
     * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
     */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y alohAndes.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
    /**
     * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
     * Invoca al método correspondiente según el evento recibido
     * @param pEvento - El evento del usuario
     */
    @Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
        try 
        {
			Method req = InterfazAlohAndesApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		} 
	}
    
	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Arreglo de argumentos que se recibe por línea de comandos
     */
    public static void main( String[] args )
    {
        try
        {
        	
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
            InterfazAlohAndesApp interfaz = new InterfazAlohAndesApp( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
	
}
