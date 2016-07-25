package edu.val.cice.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Puntuacion
 */
@WebServlet("/Puntuacion")
public class Puntuacion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private Map<String, Integer> map_puntuaciones;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Puntuacion() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	
    	
    	/**
    	 * Este método se ejecutará una vez. La primera que se llame a este servlet.
    	 * Lo vamos a utilizar para instanciar un "Mapa de puntuaciones" donde se almacenarán
    	 * las puntuaciones de cada jugador y que después será consultado
    	 */
    	map_puntuaciones = new HashMap<String, Integer>();
    	
    	map_puntuaciones.put("Maria", 8);
    	map_puntuaciones.put("Juan", 18);
    	map_puntuaciones.put("Paco", 28);
    	map_puntuaciones.put("Pepa", 86);
    	
    	getServletContext().setAttribute("clasificacion", map_puntuaciones);
    	
    	
    }
    
    /**
     * Consultar la puntuación en la base de datos donde esté almacenada
     * o donde esté: Otro servidor, una estrucutura en memoria...etc..
     * Por simplificar, suponemos un mapa en memoria (ver método init)
     * 
     * @param nombre
     * @return
     */
    private int obtenerPuntuacion (String nombre)
    {
    	int puntos = -1;
    	
    		puntos = map_puntuaciones.get(nombre);;
    		
    	return puntos;
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre_player = null;
		int puntuacion = 0;
		PrintWriter pw = null;
		
			nombre_player = request.getParameter("nombre"); //obtengo el nombre del jugador de la petición que irá en la URL
			
			puntuacion = obtenerPuntuacion(nombre_player);//obtengo su puntuación
			
			response.setContentType("text/plain");//seteo la respuesta
			response.setStatus(HttpURLConnection.HTTP_OK);//seteo el código http de que ha ido bien la cosa! OK = 200
			
			
			pw = response.getWriter();//obtengo acceso al body
			pw.print(puntuacion);//y escribo la puntación
			
			
		
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BufferedReader br = null;
		String puntuacion = null;
		
				
				br = request.getReader();
				
				puntuacion = br.readLine();
				System.out.println("Saludo recibido = " + puntuacion);
				
	}

	

}
