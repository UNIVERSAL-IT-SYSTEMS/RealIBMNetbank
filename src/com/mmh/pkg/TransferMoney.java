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

@WebServlet("/TransferMoney")
public class TransferMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private Statement statement;
	private String url = "jdbc:db2://192.86.32.54:5040/DALLASB";

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("YOU KNOW IT!!");
		String transaction_id = null;
		String account_from = null;
		String account_to = null;
		String amount = null;
		String description = null;
		double current_balance_from = 0;
		double current_balance_to = 0;
	
		Properties properties = new Properties();
		properties.put("user","DTU22");
		properties.put("password", "FAGP2016");
		properties.put("retrieveMessagesFromServerOnGetMessage", "true");
		properties.put("emulateParameterMetaDataForZCalls", "1");
	
		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			connection = DriverManager.getConnection(url, properties);
			statement = connection.createStatement(); 
			System.out.println("Vi kom sørme også ind i den første try");
		} catch (Exception e) {
			// TODO Automatisk genereret catch-blok
			response.getWriter().print(e);
			e.printStackTrace();
		}
	
		PrintWriter out = response.getWriter();
		ResultSet result1;
		ResultSet result2;
		ResultSet result3;
		ResultSet result4;
		
		// first try and see if the ID and password match with any of the clients in the CLIENT tabel
		try {
			System.out.println("nummer 2 try");
			String current_account_from;
			result1 = statement.executeQuery("SELECT \"balance\", \"acc_id\" FROM \"DTUGRP08\".\"ACCOUNT\"");
			while(result1.next()) {
				System.out.println("2");
				current_account_from = result1.getString(2);
				System.out.println(current_account_from);
				if(current_account_from.equals(request.getParameter("account_from"))){
					System.out.println("3");
					current_balance_from = Double.parseDouble(result1.getString(1));
					System.out.println("Hvem skulle have troet?");
				}
			}
			
			String current_account_to;
			result1 = statement.executeQuery("SELECT \"balance\", \"acc_id\" FROM \"DTUGRP08\".\"ACCOUNT\"");
			while(result1.next()) {
				System.out.println("4");
				current_account_to = result1.getString(2);
				if(current_account_to.equals(request.getParameter("account_to"))){
					current_balance_to = Double.parseDouble(result1.getString(1));
					System.out.println("On fire!!");
				}
			}
			
			transaction_id = request.getParameter("transaction_id");
			account_from = request.getParameter("account_from");
			account_to = request.getParameter("account_to");
			amount = request.getParameter("amount");
			double amount_int = Double.parseDouble(amount);
			description = request.getParameter("description");
			double new_amount1 =  current_balance_from - amount_int;
			double new_amount2 = current_balance_to + amount_int;
			System.out.println("^^ det der gik fint nok");
			statement.executeUpdate("UPDATE \"DTUGRP08\".\"ACCOUNT\" SET \"balance\"=" + new_amount1 + " WHERE \"acc_id\"='" + account_from + "'" );
			statement.executeUpdate("UPDATE \"DTUGRP08\".\"ACCOUNT\" SET \"balance\"=" + new_amount2 + " WHERE \"acc_id\"='" + account_to + "'");
			System.out.println("virkede det nu også?!");
			String query = "INSERT INTO \"DTUGRP08\".\"TRANSACTION\" VALUES (?, ?, ?, ?, ?)";
			try {
				System.out.println("4.2");
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, transaction_id);
				ps.setString(2, account_from);
				ps.setString(3, account_to);
				ps.setString(4, amount);
				ps.setString(5, description);
				ps.executeUpdate();
				System.out.println("nu tror jeg ikke så meget på det mere");
				
				} catch (SQLException e1) {
					System.out.println("5");
				// TODO Automatisk genereret catch-blok
				e1.printStackTrace();
			}
	
			request.getRequestDispatcher("transactionSucces.jsp").forward(request, response);

						return;
			}
		 catch (Exception e) {
			System.out.println("catch - transfermoney");
			e.printStackTrace();
		}
		
			
	}
}