package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Film;
import Model.FilmList;
import DAO.FilmDAO;
import Utils.Utils;

/**
 * Servlet implementation class GetAllFilms
 */
@WebServlet("/GetAllFilms")
public class GetAllFilms extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllFilms() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FilmDAO dao = new FilmDAO();
		
		ArrayList<Film> films = new ArrayList<Film>();
		
		films = dao.getAllFilms();
		
		request.setAttribute("films", films);
		
	    String format = request.getParameter("format");
	    
	    String outputPage = Utils.getFormat(format, response);
	    
	    System.out.println(outputPage);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher(outputPage); 
	    
	    dispatcher.include(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
