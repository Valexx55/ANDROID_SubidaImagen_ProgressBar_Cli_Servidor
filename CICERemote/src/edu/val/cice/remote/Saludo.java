package edu.val.cice.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Saludo
 */
@WebServlet("/Saludo")
public class Saludo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Saludo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String saludo = null;
		PrintWriter pw = null;
		
			saludo = "HOLA ANDROIDE";
			
			response.setContentType("text/plain"); //seteo el tipo de mensaje que irá en la cabera del mensaje de respuesta, idenficando el tipo del contenido en el cuerpo
			response.setStatus(HttpURLConnection.HTTP_OK);//seteo el código http de que ha ido bien la cosa! OK = 200
			
			pw = response.getWriter(); //obtengo una referencia al fichero que me permitirá escribir en la salida (el body del paquete de respuesta)
			pw.write(saludo); //escribo el saludo que quiero mandar al cliente
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BufferedReader br = null;
		String saludo = null;
		
				
				br = request.getReader();
				
				saludo = br.readLine();
				System.out.println("Saludo recibido = " + saludo);
				
	}

}
