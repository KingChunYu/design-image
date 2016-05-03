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
import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class DormitoryRecordServlet
 */
@WebServlet("/DormitoryRecordServlet")
public class DormitoryRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// // JDBC 驱动器名称和数据库的 URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc";
	//
	// 数据库的凭据
	static final String USER = "root";
	static final String PASS = "12345";
	private static Connection conn = null;

	private ArrayList<RecordItem> mData = new ArrayList();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DormitoryRecordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
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
				if (!realpassword.equals(password)) {
					//标示密码错误
					out.write("000");
					return;
				}
			}
			else
			{   //标识账号不存在
				out.write("001");
				return;
			}
			
			
		    Statement stmt1 = (Statement) conn.createStatement();
		    ResultSet rSet2 = stmt1.executeQuery("select * from dtrecord where account = "+"'"+account+"'");
			
			
			mData.clear();
			while (rSet2.next()) {
				RecordItem recordItem = new RecordItem(rSet2.getInt("id"), rSet2.getString("num"),
						rSet2.getString("account"), rSet2.getString("content"));
				mData.add(recordItem);
			}
			if (mData.size()==0) {
				//标识没有查到具体数据
				out.write("002");
				return;
			}

			RecordMain recordMain = new RecordMain(10084, mData);
			Gson gson = new Gson();
			String gsonstr = gson.toJson(recordMain);
			// JSONArray jsonArray = new JSONArray(mData);
			out.write(gsonstr);
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
