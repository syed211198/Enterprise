package Utils;

import javax.servlet.http.HttpServletResponse;

public class Utils {

	public static String getFormat(String format, HttpServletResponse response){
	
		String outputPage = " ";
	    
	    if ("xml".equals(format)) {
	    	
	      response.setContentType("text/xml");
	      
	      outputPage = "/WEB-INF/results/films-xml.jsp";
	      
	    } else if ("json".equals(format)) {
	    	
	      response.setContentType("application/json");
	      
	      outputPage = "/WEB-INF/results/films-json.jsp";
	      
	    } else {
	    	
	      response.setContentType("text/plain");
	      
	      outputPage = "/WEB-INF/results/films-string.jsp";
	      
	    }
	    
	    return outputPage;
		
	}
	
}
