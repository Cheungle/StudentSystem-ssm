package org.controller;

import beans.notice;
import dao.NoticeDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Servlet implementation class GetNoticeByStu
 */
@WebServlet(name="/GetNoticeByStu",urlPatterns= {"/GetNoticeByStu"})
public class GetNoticeByStu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeDao nd = new NoticeDao(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetNoticeByStu() {
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
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<notice> notices = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			con=DriverManager.getConnection(nd.getURL(),nd.getUSER(),nd.getPASSWORD());
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("select* from notice order by pubdate DESC");
			rs.last();
			int totalCount = rs.getRow();
			int totalPage = 0;
			int perPageSize=5;
			if(totalCount ==0 )totalPage=0;
			else totalPage=(int)Math.ceil((double)totalCount/perPageSize);
			String pageCrull=request.getParameter("pageCur");
			if(pageCrull==null)pageCrull="1";
			int pageCur = Integer.parseInt(pageCrull);
			if((pageCur-1)*perPageSize>totalCount)pageCur--;
			int startIndex = (pageCur-1)*perPageSize;
			ps=con.prepareStatement("select* from notice order by pubdate DESC limit ?,?");
			ps.setInt(1, startIndex);
			ps.setInt(2, perPageSize);
			rs=ps.executeQuery();
			notices = new ArrayList<notice>();
			while(rs.next()) {
				notice n = new notice();
				n.setIdNotice(rs.getInt("idnotice"));
				n.setTitle(rs.getString("title"));
				n.setContent(rs.getString("content"));
				n.setPubdate(rs.getString("pubdate"));
				n.setPubperson(rs.getString("pubperson"));
				notices.add(n);
			}
			request.setAttribute("notices", notices);
			request.setAttribute("totalCount", totalCount);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("pageCur", pageCur);
			rs.close();
			ps.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dis=request.getRequestDispatcher("notice.jsp");
		dis.forward(request,response);
	}
}


