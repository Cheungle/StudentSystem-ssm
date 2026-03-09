package org.controller;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class Charts
 */
@WebServlet(name="/Charts",urlPatterns= {"/Charts"})
public class Charts extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScDao scd = new ScDao();      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Charts() {
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

		List<Double> aveGPA = new ArrayList();
		List<Double> allcre = new ArrayList();
	//	System.out.println(id);
		int t1=Integer.parseInt(id.substring(2, 4));
		int t2=t1+1;
		String start = String.valueOf(t1)+"-"+String.valueOf(t2)+" 1";
		String end = String.valueOf(t1)+"-"+String.valueOf(t2)+" 2";
	//	System.out.println(start);
		try {
		  Double GPA = scd.getAverageGPA(id, start);
		  int credit = scd.getAllCreditOfOne(id, start);
		  aveGPA.add(GPA);
		  allcre.add((double)credit);
		  GPA = scd.getAverageGPA(id, end);
		  credit = scd.getAllCreditOfOne(id, end);
		  aveGPA.add(GPA);
		  allcre.add((double)credit);
		  for(int i = 2;i<=4;i++) {
			t1++;t2++;
			start=String.valueOf(t1)+"-"+String.valueOf(t2)+" 1";
			end = String.valueOf(t1)+"-"+String.valueOf(t2)+" 2";
			GPA = scd.getAverageGPA(id, start);
			credit = scd.getAllCreditOfOne(id, start);
			aveGPA.add(GPA);
			allcre.add((double)credit);
			GPA = scd.getAverageGPA(id, end);
			credit = scd.getAllCreditOfOne(id, end);
			aveGPA.add(GPA);
			allcre.add((double)credit);
		}
		    aveGPA.addAll(allcre);
		    String CONTENT_TYPE = "application/json; charset=GBK";
		    response.setContentType(CONTENT_TYPE);
			PrintWriter out = response.getWriter();
			out.println(JSONArray.toJSONString(aveGPA));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
