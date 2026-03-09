package org.controller;

import beans.gradewithmore;
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
 * Servlet implementation class GetCourseGrade
 */
@WebServlet(name="/GetCourseGrade",urlPatterns= {"/GetCourseGrade"})
public class GetCourseGrade extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScDao scd = new ScDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCourseGrade() {
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
		try {
			List<gradewithmore> gwms = scd.getAllCourseGradeOfOne(id, term);
			String CONTENT_TYPE = "application/json; charset=GBK";
		    response.setContentType(CONTENT_TYPE);
		    PrintWriter out = response.getWriter();
			out.println(JSONArray.toJSONString(gwms));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
