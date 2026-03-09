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
 * Servlet implementation class UpdateT
 */
@WebServlet(name="/UpdateT",urlPatterns= {"/UpdateT"})
public class UpdateT extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherDao td = new TeacherDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateT() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
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
	    	if(academy!=""&&academy!=null)td.update(id,name,office,academy);
	    	else td.update(id, name, office);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
