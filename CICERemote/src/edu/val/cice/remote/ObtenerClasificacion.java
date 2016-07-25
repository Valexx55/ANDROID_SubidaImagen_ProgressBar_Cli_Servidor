package edu.val.cice.remote;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class ObtenerClasificacion
 */
@WebServlet("/ObtenerClasificacion")
public class ObtenerClasificacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObtenerClasificacion() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private String transformarPuntuacionesAJSON (Map<String, Integer> map_puntuaciones)
    {
    	String puntuaciones_json = null;
    	
    		Gson gson = new Gson();
    		puntuaciones_json = gson.toJson(map_puntuaciones);
    	
    	return puntuaciones_json;
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, Integer> map_puntuaciones;
		PrintWriter pw = null;
		
			map_puntuaciones = (Map<String, Integer>) getServletContext().getAttribute("clasificacion");
			
			response.setContentType("application/json");//seteo la respuesta
			response.setStatus(HttpURLConnection.HTTP_OK);//seteo el código http de que ha ido bien la cosa! OK = 200
			
			
			String puntuaciones_json = transformarPuntuacionesAJSON (map_puntuaciones);
			
			System.out.println("Puntuaciones como JSON = " + puntuaciones_json);
			
			pw = response.getWriter();//obtengo acceso al body
			pw.print(puntuaciones_json);//y escribo la puntación
			
			
	
	}

	
}
