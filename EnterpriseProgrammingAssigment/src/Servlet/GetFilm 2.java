package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.FilmDAO;
import Model.Film;
import Utils.Utils;

/**
 * Servlet implementation class GetFilm
 */
@WebServlet("/GetFilm")
public class GetFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFilm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String film_id = request.getParameter("id");
		
		FilmDAO dao = new FilmDAO();
		
		ArrayList<Film> film = dao.getFilmByID(Integer.parseInt(film_id));
		
		request.setAttribute("films", film);
		
		String path = "/WEB-INF/results/film-layout.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		
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
