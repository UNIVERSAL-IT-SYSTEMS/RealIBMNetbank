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

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private Statement statement;
	private String url = "jdbc:db2://192.86.32.54:5040/DALLASB";

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = null;
		String password = null;
	
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
		}
	
		PrintWriter out = response.getWriter();
		out.print("Hello DTU Students");
		ResultSet result;
		try {
			result = statement.executeQuery("SELECT \"client_id\", \"client_password\" FROM \"DTUGRP08\".\"CLIENT\"");
			while (result.next()) {
					id = result.getString(1);
					password = result.getString(2);
					if (id.equals(request.getParameter("username")) && password.equals(request.getParameter("password"))) {
						request.getRequestDispatcher("Forside_Kunde.jsp").forward(request, response);
						return;
					}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			result = statement.executeQuery("SELECT \"advisor_id\", \"adv_password\" FROM \"DTUGRP08\".\"ADVISOR\"");
			while (result.next()) {
					id = result.getString(1);
					password = result.getString(2);
					System.out.println(id + ", " + password);
					if (id.equals(request.getParameter("username")) && password.equals(request.getParameter("password"))) {
						request.getRequestDispatcher("Forside_raadgiver.jsp").forward(request, response);
						return;
					}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			request.getRequestDispatcher("overfoer.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Automatisk genereret catch-blok
			e.printStackTrace();
		}
			
	}
}