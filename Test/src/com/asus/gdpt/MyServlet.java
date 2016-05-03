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

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mysql.jdbc.Connection;
import com.sun.javafx.sg.prism.web.NGWebView;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	 // JDBC 驱动器名称和数据库的 URL
   static final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
   static final String DB_URL="jdbc:mysql://localhost:3306/jdbc";
//
   //  数据库的凭据
   static final String USER = "root";
   static final String PASS = "12345";
   private static Connection conn = null;
   
   private ArrayList<TeamItem> mData = new ArrayList();
      
   /**
    * @see HttpServlet#HttpServlet()
    */
   public MyServlet() {
       super();
       // TODO Auto-generated constructor stub
   }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    response.setContentType("text/json");
    response.setCharacterEncoding("utf-8");
    PrintWriter out = response.getWriter();

  
   try {
		Class.forName(JDBC_DRIVER);
		 conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
		 java.sql.Statement stmt = conn.createStatement();
		 ResultSet rSet = stmt.executeQuery("select * from teamitem");
		 mData.clear();
		 while( rSet.next())
		 {
			 TeamItem teamItem = new TeamItem(rSet.getInt("id"),rSet.getString("iconUrl"),rSet.getString("actionUrl"),rSet.getString("title"));
			 mData.add(teamItem);
		 }
		 
		TeamMain teamMain = new TeamMain(10086,mData);
	    Gson gson = new Gson();
	    String gsonstr = gson.toJson(teamMain);
	       
//		 JSONArray jsonArray = new JSONArray(mData);
		 out.write(gsonstr);
		 
		 
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
