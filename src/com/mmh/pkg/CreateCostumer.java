package com.mmh.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.util.Properties;

@WebServlet("/CreateCostumer")
public class CreateCostumer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private Statement statement;
	private String url = "jdbc:db2://192.86.32.54:5040/DALLASB";
	
	String client_id = null;
	String client_name = null;
	String client_postalnr = null;
	String client_password = null;
	String advisor_id = null;

	
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
		}
	
		PrintWriter out = response.getWriter();
		ResultSet result;
		
		this.client_id = request.getParameter("client_id");
		this.client_name = request.getParameter("client_name");
		this.client_postalnr = request.getParameter("client_postalnr");
		this.client_password = request.getParameter("client_password");
		this.advisor_id = request.getParameter("advisor_id");
		
		
		String query = "INSERT INTO \"DTUGRP08\".\"CLIENT\" VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, client_id);
			ps.setString(2, client_name);
			ps.setString(3, client_postalnr);
			ps.setString(4, client_password);
			ps.setString(5, advisor_id);
			ResultSet rs = ps.executeQuery();
			
			} catch (SQLException e1) {
			// TODO Automatisk genereret catch-blok
			e1.printStackTrace();
		}
		
	}
	
}