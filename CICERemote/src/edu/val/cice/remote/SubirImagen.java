package edu.val.cice.remote;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class SubirImagen
 */
@WebServlet("/SubirImagen")
public class SubirImagen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubirImagen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre_fichero_salida = "sun44.jpg";
		
		BASE64Decoder bd = null;
		OutputStream os  = null;
		byte[] imgbytes = null;
				
			
			System.out.println("POST RECIBIDO! ...");	
			
			bd = new BASE64Decoder();
			imgbytes = bd.decodeBuffer(request.getInputStream());
		

			System.out.println("Imagen Decodificada! ...");	
		try 
			{
				File file = new File (nombre_fichero_salida);
				FileOutputStream fos = new FileOutputStream(file);
				
			    os = new BufferedOutputStream(fos);
			    os.write(imgbytes);            
			    
		
			    System.out.println("Fichero creado!  en " + file.getAbsolutePath());
			} 
		catch (Exception e)
			{
				e.printStackTrace();
			}
		finally 
			{
			    if (os != null) 
			    {
			        os.close();
			    }
			}
	}

}
