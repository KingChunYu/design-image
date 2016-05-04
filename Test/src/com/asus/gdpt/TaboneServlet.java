package com.asus.gdpt;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class TaboneServlet
 */
@WebServlet("/TaboneServlet")
public class TaboneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// // JDBC 驱动器名称和数据库的 URL
		static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc";
		//
		// 数据库的凭据
		static final String USER = "root";
		static final String PASS = "12345";
		private static Connection conn = null;

		/**
		 * @see HttpServlet#HttpServlet()
		 */
		public TaboneServlet() {
			super();
			// TODO Auto-generated constructor stub
		}

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
			response.setContentType("text/json");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			try {
				Class.forName(JDBC_DRIVER);
				conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
				java.sql.Statement stmt = conn.createStatement();
				ResultSet rSet = stmt.executeQuery("select * from newsdata");
				while (rSet.next()) {
					out.write(rSet.getString("content"));
					break;
				}

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

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}

}
