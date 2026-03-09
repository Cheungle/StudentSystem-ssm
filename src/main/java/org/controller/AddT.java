package org.controller;

import dao.TeacherDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class AddT
 */
@WebServlet(name="/AddT",urlPatterns= {"/AddT"})
public class AddT extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherDao td = new TeacherDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddT() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String id = request.getParameter("id");
	      String name = request.getParameter("name");
	      String office = request.getParameter("office");
	      String academy = request.getParameter("academy");
	  
	      try {	    	
	    	  td.add(id,name,office,academy);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
