package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Film;

import java.sql.*;


public class FilmDAO {
	
	Film oneFilm = null;
	
	Connection conn = null;
    
	Statement stmt = null;
    
	ResultSet resultSet = null;
	
	PreparedStatement preparedStatement = null; 
    
	String database = "u471452213_Films";
	
	String user = "u471452213_Sayyab";
    
	String password = "Imran0087";
    
    // Note none default port used, 6306 not 3306
    
	String url = "jdbc:mysql://153.92.6.127:3306/" + database + "?characterEncoding=latin1&useConfigs=maxPerformance";

	public FilmDAO() {}

	
	private void openConnection(){
		
		// loading jdbc driver for mysql
		
		try{
			
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    
		} catch(Exception e) { System.out.println(e); }

		// connecting to database
		
		try{
			
			// connection string for demos database, username demos, password demos
 			
			conn = DriverManager.getConnection(url, user, password);
		    
			stmt = conn.createStatement();
		
		} catch(SQLException se) { System.out.println(se); }	   
    
	}

	private void closeConnection(){
		
		try {
		
			conn.close();
		
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		
		}
	
	}

	private Film getNextFilm(ResultSet rs){
    	
		Film thisFilm=null;
		
		try {
			
			thisFilm = new Film(
					
					rs.getInt("id"),
					
					rs.getString("title"),
					
					rs.getInt("year"),
					
					rs.getString("director"),
					
					rs.getString("stars"),
					
					rs.getString("review"));
		
		} catch (SQLException e) {
			
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		
		}
    	
		return thisFilm;		
	
	}
	
	
	
   public ArrayList<Film> getAllFilms(){
	   
		ArrayList<Film> allFilms = new ArrayList<Film>();
		
		String selectSQL = "SELECT * FROM films";
		
		openConnection();
		
	    // Create select statement and execute it
		try{
		    
		    resultSet = stmt.executeQuery(selectSQL);
		    
		    System.out.println("Get all Films query: " + selectSQL);
		    
	    // Retrieve the results
		    
		    while(resultSet.next()){
		    	
		    	oneFilm = getNextFilm(resultSet);
		    	
		    	allFilms.add(oneFilm);
		    	
		    }

		    stmt.close();
		    
		    closeConnection();
		
		} catch(SQLException se) { System.out.println(se); }
		
		System.out.println("Get all Films (toString): " + allFilms.toString());

	   return allFilms;
   
   }

   public ArrayList<Film> getFilmByID(int id){
	   
	   	ArrayList<Film> allFilms = new ArrayList<Film>();
	   
	   	String selectSQL = "SELECT * FROM films WHERE id = ?;";
	   
		openConnection();
		
	    // Create select statement and execute it
		
		try{ 
		    
		    preparedStatement = conn.prepareStatement(selectSQL);
		    
		    preparedStatement.setInt(1, id);
		    
		    resultSet = preparedStatement.executeQuery();
		    
		    System.out.println("Get a Film by ID query: " + selectSQL);
		    
		    // Retrieve the results
		    
		    while(resultSet.next()){
		    	
		    	oneFilm = getNextFilm(resultSet);
		    	
		    	allFilms.add(oneFilm);
		    	
		    }

		    preparedStatement.close();
		    
		    closeConnection();
		
		} catch(SQLException se) { System.out.println(se); }
		
		System.out.println("Get a Film by ID (toString): " + allFilms.toString());

		return allFilms;
	   
   }
   
   
   public ArrayList<Film> searchFilms(String searchBy, String userInput){
	   
		ArrayList<Film> allFilms = new ArrayList<Film>();
		
		String searchSQL = " ";
		
		int criteriaInt = 0;
		
		String criteriaStr = "";
		
		openConnection();
		
	    // Create select statement and execute it
		try{
			
			if(searchBy.equals("id") || searchBy.equals("year")) {
				
				criteriaInt = Integer.parseInt(userInput);
				
				searchSQL = "SELECT * FROM films WHERE ? = ?;";
				
				preparedStatement = conn.prepareStatement(searchSQL);
			    
			    preparedStatement.setString(1, searchBy);
			    
			    preparedStatement.setInt(2, criteriaInt);
			    
			    resultSet = preparedStatement.executeQuery();
				
				
			}else {
				
				criteriaStr = userInput;
				
				searchSQL = "SELECT * FROM films WHERE ? like '%?%';";
				
				preparedStatement = conn.prepareStatement(searchSQL);
			    
			    preparedStatement.setString(1, searchBy);
			    
			    preparedStatement.setString(2, criteriaStr);
			    
			    resultSet = preparedStatement.executeQuery();
				
			}
			
			System.out.println("Search Query: " + searchSQL);
		    
		    while(resultSet.next()){
		    	
		    	oneFilm = getNextFilm(resultSet);
		    	
		    	allFilms.add(oneFilm);
		   
		    }

		    preparedStatement.close();
		    
		    closeConnection();
		
		} catch(SQLException se) { System.out.println(se); }
		
		System.out.println("Search Query (toString): " + allFilms.toString());

		return allFilms;
	   
   }
   
   public Boolean insertFilms(Film film) {
	   
	   
	   String insertFilmSQL = "INSERT INTO films (id, title, year, director, stars, review)"
	   						+ "VALUES (?, ?, ?, ?, ?, ?);";
	   
	   openConnection();
	   
	   try {
		  
		   preparedStatement = conn.prepareStatement(insertFilmSQL);
		   
		   preparedStatement.setInt(1, film.getId());
		    
		   preparedStatement.setString(2, film.getTitle());
		   
		   preparedStatement.setInt(3, film.getYear());
		    
		   preparedStatement.setString(4, film.getDirector());
		   
		   preparedStatement.setString(5, film.getStars());
		   
		   preparedStatement.setString(6, film.getReview());
		   
		   System.out.println("Insert Film Query: " + insertFilmSQL);
		   
		   preparedStatement.executeUpdate();
		   
		   closeConnection();
		   
		   preparedStatement.close();
		   
		   return true;
		   
		   
	   } catch(SQLException se) { System.out.println(se); }
	   
	   
	   return false;
	   
   }
   
   public Boolean deleteFilm(int id) {
	   
	   String deleteFilmSQL = "DELETE FROM films WHERE id = ?";
	   
	   openConnection();
		
	    // Create select statement and execute it
		
		try{ 
		    
		    preparedStatement = conn.prepareStatement(deleteFilmSQL);
		    
		    preparedStatement.setInt(1, id);
		    
		    preparedStatement.executeUpdate();
		    
		    System.out.println("Delete a Film by ID query: " + deleteFilmSQL);

		    preparedStatement.close();
		    
		    closeConnection();
		    
		    return true;
		
		} catch(SQLException se) { System.out.println(se); }
	   
	   return false;
	   
   }
   
   public Boolean updateFilm(Film film) {
	   
	   String updateFilmSQL = "UPDATE films SET title = ?, year = ?, director = ?, stars = ?, review = ? WHERE id = ?;";
	   
	   openConnection();
	   
	   try {
		  
		   preparedStatement = conn.prepareStatement(updateFilmSQL);
		    
		   preparedStatement.setString(1, film.getTitle());
		   
		   preparedStatement.setInt(2, film.getYear());
		    
		   preparedStatement.setString(3, film.getDirector());
		   
		   preparedStatement.setString(4, film.getStars());
		   
		   preparedStatement.setString(5, film.getReview());
		   
		   preparedStatement.setInt(6, film.getId());
		   
		   System.out.println("Update Film Query: " + updateFilmSQL);
		   
		   preparedStatement.executeUpdate();
		   
		   closeConnection();
		   
		   preparedStatement.close();
		   
		   return true;
		   
		   
	   } catch(SQLException se) { System.out.println(se); }
	   
	   return false;
	   
   }
  
   
}
