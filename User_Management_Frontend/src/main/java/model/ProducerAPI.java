package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProducerAPI
 */
@WebServlet("/ProducerAPI")
public class ProducerAPI extends HttpServlet {
	
	Producer producerObj = new Producer();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProducerAPI() {
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
		// TODO Auto-generated method stub
		//plantId`,`projectId`,`firstName`,`lastName`, `email`, `userName`,`telephoneNo`,`password`
		String output = producerObj.insertProducer(
				request.getParameter("plantId"), 
				request.getParameter("projectId"), 
				request.getParameter("firstName"),
		        request.getParameter("lastName"),
		        request.getParameter("email"),
		        request.getParameter("userName"),
		        request.getParameter("telephoneNo"),
		        request.getParameter("password"));		  
				response.getWriter().write(output);
		
	}
	//producerId`,`plantId`,`projectId`,`firstName`,`lastName`, `email`, `userName`,`telephoneNo`,`password
	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request); 
		 String output = producerObj.updateProducer(paras.get("hidProducerIDSave").toString(), 
		 paras.get("plantId").toString(), 
		paras.get("projectId").toString(), 
		paras.get("firstName").toString(), 
		paras.get("lastName").toString(),
		 paras.get("email").toString(),
		 paras.get("userName").toString(), 
		 paras.get("telephoneNo").toString(),
		 paras.get("password").toString()); 
		response.getWriter().write(output);
	}

	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
	 String[] p = param.split("="); 
	 map.put(p[0], p[1]); 
	 } 
	 } 
	catch (Exception e) 
	 { 
	 } 
	return map; 
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request); 
		 String output = producerObj.deleteProducer(paras.get("producerId").toString()); 
		response.getWriter().write(output);
	}

}
