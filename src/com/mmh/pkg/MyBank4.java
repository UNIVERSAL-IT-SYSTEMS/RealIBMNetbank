package com.mmh.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.util.Properties;

/**
 * Servlet implementation class MyBank4
 */
@WebServlet("/MyBank4")
public class MyBank4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private Statement statement;
	private String url = "jdbc:db2://192.86.32.54:5040/DALLASB";

    /**
     * Default constructor. 
     */
    public MyBank4() {
    	super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   // @Resource(name = "jdbc/exampleDS")
   	// DataSource ds1;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Properties properties = new Properties();
		properties.put("user","DTU22");
		properties.put("password", "FAGP2016");
		properties.put("retrieveMessagesFromServerOnGetMessage", "true");
		properties.put("emulateParameterMetaDataForZCalls", "1");
		
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			connection = DriverManager.getConnection(url, properties);
			statement = connection.createStatement();
		} catch (Exception e) {
			// TODO Automatisk genereret catch-blok
			response.getWriter().print(e);
			e.printStackTrace();
			return;
		}
		
		PrintWriter out = response.getWriter();
		out.print("Hello DTU Students");
		ResultSet result;
		if (statement == null){
			response.getWriter().print("den er null");
			return;
		}
		try {
			result = statement.executeQuery("SELECT * FROM \"DTUGRP08\".\"SAMPLE\"");
			while (result.next()) {
					String studnr = result.getString(1);
					String name = result.getString(2);
					String studtype = result.getString(3);
					response.getWriter().print("<br><font face=courier>Table Record = " + studnr + ", " + name + ", " + studtype + "</font>");
				}
		} catch (SQLException e) {
			// TODO Automatisk genereret catch-blok
			e.printStackTrace();
		}
		System.out.println("nummer4");
	}
		// TODO Auto-generated method stub
		
		/*Statement stmt = null;
		Connection con = null;
		
		response.getWriter().print("<html><body><font face=arial>");
		response.getWriter().print("<img src=\"/ATS_jdbc/banner_small.jpg\">");
		response.getWriter().print("<p>Servlet for validating JDBC Type 2 with <font color=blue><b>IBM WAS z/OS V8.5 Liberty</b></font>");
		response.getWriter().print("<p>This servlet selects from DTUGRP08.SAMPLE and displays results");
		  
		try {
 		    con = ds1.getConnection();
 		    response.getWriter().print("<p>Connection to DB2 successfully achieved");

		    stmt = con.createStatement();
		    ResultSet result = stmt.executeQuery("SELECT * FROM \"DTUGRP08\".\"SAMPLE\"");
		    System.out.println("### JDBC_select ### -- issued SELECT query");
		    response.getWriter().print("<p>Results selected ... will now display results");
		    response.getWriter().print("<br>");
		      
		    while (result.next()) {
				String studnr = result.getString(1);
				String name = result.getString(2);
				String studtype = result.getString(3);
				response.getWriter().print("<br><font face=courier>Table Record = " + studnr + ", " + name + ", " + studtype + "</font>");
			}
		    response.getWriter().print("<p>End of records");    
		  } 
		  catch (SQLException e) {
			response.getWriter().print("<p>Problem attempting to get a connection or executing query");
		    e.printStackTrace();
		  } 
	response.getWriter().print("</font></body></html>");	
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
