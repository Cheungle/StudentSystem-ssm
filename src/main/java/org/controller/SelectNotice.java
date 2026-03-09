package org.controller;

import beans.notice;
import com.alibaba.fastjson.JSONArray;
import dao.NoticeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class SelectNotice
 */
@WebServlet(name="/SelectNotice",urlPatterns= {"/SelectNotice"})
public class SelectNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeDao nd = new NoticeDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectNotice() {
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
	      try {
	    	 notice thent = new notice();
	    	 thent = nd.select(id);
	    	 String CONTENT_TYPE = "application/json; charset=GBK";
			 response.setContentType(CONTENT_TYPE);
	    	 PrintWriter out = response.getWriter();
			 out.println(JSONArray.toJSONString(thent));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
