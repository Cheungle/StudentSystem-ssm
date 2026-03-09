package org.controller;

import beans.course;
import com.alibaba.fastjson.JSONArray;
import dao.ScDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Servlet implementation class ShowCourseDay
 */
@WebServlet(name="/ShowCourseDay",urlPatterns="/ShowCourseDay")
public class ShowCourseDay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScDao scd =new ScDao(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowCourseDay() {
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
		String term = request.getParameter("term");
		String today = request.getParameter("today");
		try {
			List<course> courses = scd.getAllCourseOfOne(id, term,today);
			String CONTENT_TYPE = "application/json; charset=GBK";
		    response.setContentType(CONTENT_TYPE);
			PrintWriter out = response.getWriter();
			out.println(JSONArray.toJSONString(courses));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
