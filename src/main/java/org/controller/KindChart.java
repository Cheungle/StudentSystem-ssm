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
 * Servlet implementation class KindChart
 */
@WebServlet(name="/KindChart",urlPatterns= {"/KindChart"})
public class KindChart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScDao scd = new ScDao();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KindChart() {
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
		String kind1 = request.getParameter("kind1");
		String kind2 = request.getParameter("kind2");
		String kind3 = request.getParameter("kind3");
		String kind4 = request.getParameter("kind4");
		String kind5 = request.getParameter("kind5");

		List<Double> gras = new ArrayList();
		List<Double> cres = new ArrayList();
		
		int t1=Integer.parseInt(id.substring(2, 4));
		int t2=t1+1;
		
		String year = String.valueOf(t1)+"-"+String.valueOf(t2);

		try {
		    Double gra = scd.getKindAverageGradeOfYear(id, year, kind1);	    
		    int cre = scd.getCreditOfKind(id, kind1);
		    gras.add(gra);
		    cres.add((double)cre);
		    gra = scd.getKindAverageGradeOfYear(id, year, kind2);
		    cre = scd.getCreditOfKind(id, kind2);
		    gras.add(gra);
		    cres.add((double)cre);
		    gra = scd.getKindAverageGradeOfYear(id, year, kind3);
		    cre = scd.getCreditOfKind(id, kind3);
		    gras.add(gra);
		    cres.add((double)cre);
		    gra = scd.getKindAverageGradeOfYear(id, year, kind4);
		    cre = scd.getCreditOfKind(id, kind4);
		    gras.add(gra);
		    cres.add((double)cre);
		    gra = scd.getKindAverageGradeOfYear(id, year, kind5);
		    cre = scd.getCreditOfKind(id, kind5);
		    gras.add(gra);
		    cres.add((double)cre);
			t1++;t2++;
			year=String.valueOf(t1)+"-"+String.valueOf(t2);		
			gra = scd.getKindAverageGradeOfYear(id, year, kind1);
			gras.add(gra);
			gra = scd.getKindAverageGradeOfYear(id, year, kind2);
			gras.add(gra);
			gra = scd.getKindAverageGradeOfYear(id, year, kind3);
			gras.add(gra);
			gra = scd.getKindAverageGradeOfYear(id, year, kind4);
			gras.add(gra);
			gra = scd.getKindAverageGradeOfYear(id, year, kind5);
			gras.add(gra);
			gras.addAll(cres);
			String CONTENT_TYPE = "application/json; charset=GBK";
			response.setContentType(CONTENT_TYPE);
			PrintWriter out = response.getWriter();
			out.println(JSONArray.toJSONString(gras));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
