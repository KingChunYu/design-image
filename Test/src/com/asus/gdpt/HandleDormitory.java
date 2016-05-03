package com.asus.gdpt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.sun.xml.internal.bind.v2.runtime.Name;

/**
 * Servlet implementation class HandleDormitory
 */
@WebServlet("/HandleDormitory")
public class HandleDormitory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// // JDBC ���������ƺ����ݿ�� URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc";
	//
	// ���ݿ��ƾ��
	static final String USER = "root";
	static final String PASS = "12345";
	private static Connection conn = null;

	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleDormitory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String string = request.getParameter("name");
//		PrintWriter out = response.getWriter();
//		out.write("haha!");
		String num = request.getParameter("num");
		String account= request.getParameter("account");
		String password = request.getParameter("password");
		String content = request.getParameter("content");
		String name = request.getParameter("name");
		response.setContentType("text/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		try {
			Class.forName(JDBC_DRIVER);
			conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery("select * from student where account = "+"'"+account+"'");
			if(rSet.next())
			{
				String realpassword = rSet.getString("password");
				String realname = rSet.getString("name");
				if (!realpassword.equals(password)) {
					//��ʾ�������
					out.write("000");
					return;
				}else if (!realname.equals(name)) {
					//��ʶ������ƥ��
					out.write("002");
					return;
				}
			}
			else
			{   //��ʶ�˺Ų�����
				out.write("001");
				return;
			}
			
			String sql = "insert into dtrecord(num,account,content)"+"values(?,?,?)";
			PreparedStatement ptmt = (PreparedStatement) conn.prepareStatement(sql);
			ptmt.setString(1,num);
			ptmt.setString(2,account);
			ptmt.setString(3,content);
			ptmt.execute();
			
		    out.write("200");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
