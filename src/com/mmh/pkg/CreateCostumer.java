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
	String account_id = null;
	String balance = null;
	String currency = null;
	String interest_rate = null;

	
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
		String account_id = request.getParameter("account_id");
		String balance = request.getParameter("balance");
		String currency = request.getParameter("currency");
		String interest_rate = request.getParameter("interest_rate");
		
		System.out.println("1");
		String query1 = "INSERT INTO \"DTUGRP08\".\"CLIENT\" VALUES (?, ?, ?, ?, ?)";
		String query2 = "INSERT INTO \"DTUGRP08\".\"ACCOUNT\" VALUES (?, ?, ?, ?, ?)";
		try {
			System.out.println("2");
			PreparedStatement ps1 = connection.prepareStatement(query1);
			System.out.println("3");
			PreparedStatement ps2 = connection.prepareStatement(query2);
			System.out.println("4");
			ps1.setString(1, client_id);
			ps1.setString(2, client_name);
			ps1.setString(3, client_postalnr);
			ps1.setString(4, client_password);
			ps1.setString(5, advisor_id);
			System.out.println("5");
			ps1.executeUpdate();
			System.out.println("6");
			ps2.setString(1, account_id);
			ps2.setString(2, balance);
			ps2.setString(3, client_id);
			ps2.setString(4, currency);
			ps2.setString(5, interest_rate);
			System.out.println("7");
			ps2.executeUpdate();
			
			} catch (SQLException e1) {
			// TODO Automatisk genereret catch-blok
			e1.printStackTrace();
		}
		
	}
	
}