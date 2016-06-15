package com.mmh.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
		ResultSet result;
		
		// first try and see if the ID and password match with any of the clients in the CLIENT tabel
		try {
			result = statement.executeQuery("SELECT \"client_id\", \"client_password\" FROM \"DTUGRP08\".\"CLIENT\"");
			while (result.next()) {
					id = result.getString(1);
					password = result.getString(2);
					if (id.equals(request.getParameter("username")) && password.equals(request.getParameter("password"))) {
						Client client = new Client(id);
						AdvisorClient advisor = new AdvisorClient(id);
						//Account account = new Account(id);
						request.setAttribute("currentClient", client);
						request.setAttribute("currentAdvisorClient", advisor);
						//request.setAttribute("currentAccount", account);
						
						
						request.setAttribute("ID", id);
						System.out.println("Før - Login");

						//ServletContext context = getServletContext();
						request.getRequestDispatcher("/GenerateAccountInfo").forward(request, response);
						System.out.println("Efter - Login");

						
						return;
					}
			}
		} catch (SQLException e) {
			System.out.println("catch - Login");
			e.printStackTrace();
		}
		
		// If it does not match with any of the clients, then check if it matches with any advisors from the ADVISOR database
		try {
			result = statement.executeQuery("SELECT \"adv_id\", \"adv_password\" FROM \"DTUGRP08\".\"ADVISOR\"");
			while (result.next()) {
					id = result.getString(1);
					password = result.getString(2);
					if (id.equals(request.getParameter("username")) && password.equals(request.getParameter("password"))) {
						request.getRequestDispatcher("frontPageAdvisor.jsp").forward(request, response);
						return;
					}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Lastly if it does not match with any of them, take the user to a page, where they get the opportunity to try again
		try {
			request.getRequestDispatcher("wrongLoginInformation.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Automatisk genereret catch-blok
			e.printStackTrace();
		}
		
		
			
	}
}