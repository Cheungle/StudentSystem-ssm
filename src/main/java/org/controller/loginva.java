package org.controller;

import beans.student;
import dao.AdminDao;
import dao.StudentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class loginva
 */
@WebServlet(name = "loginva", urlPatterns= {"/loginva"})
public class loginva extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao stdao = new StudentDao();
	private AdminDao amdao = new AdminDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginva() {
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
		String username = request.getParameter("loginUsername");
	    String password = request.getParameter("loginPassword");
	    
			try {
				if(amdao.login(username, password)) {
					HttpSession session = request.getSession();
				    session.setAttribute("username", username); 	
				    request.getRequestDispatcher("adminindex.jsp").forward(request,response);
				}
				else {
					if(stdao.login(username, password))
						  { 
						    HttpSession session = request.getSession();
					        session.setAttribute("username", username); 
					        student st = stdao.select(username);
					        session.setAttribute("nowuser", st); 
					        request.getRequestDispatcher("index.jsp").forward(request,response);
						  }
					  else {
						  HttpSession session = request.getSession();
					      session.setAttribute("error", "error"); 
					      response.sendRedirect("login.jsp");
					      System.out.println(session.getAttribute("error"));
					  }
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}


