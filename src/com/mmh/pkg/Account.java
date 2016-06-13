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

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Account {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private Statement statement;
	private String url = "jdbc:db2://192.86.32.54:5040/DALLASB";

	private String client_id;
	private String account_id;
	private String balance;
	private String currency;
	private String interest_rate;
	// private List<Account> accountArray = new ArrayList();
	private String[] account = new String[5];
	
	
	public Account(String client_id/*, int account_Nr*/) {

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
			e.printStackTrace();
		}
		
		
		String query = "SELECT \"acc_id\", \"balance\", \"client_id\", \"currency\", \"interest_rate\", FROM \"DTUGRP08\".\"ACCOUNT\" WHERE \"client_id\" = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, client_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				this.account_id = rs.getString("acc_id");
				this.balance = rs.getString("balance");
				this.client_id = rs.getString("client_id");
				this.currency = rs.getString("currency");
				this.interest_rate = rs.getString("interest_rate");

			}
			account[0] = account_id;
			account[1] = balance;
			account[2] = client_id;
			account[3] = currency;
			account[4] = interest_rate;

			for (String s: account)
	        {
	          System.out.println(s);
	        }
			
			
			} catch (SQLException e1) {
			// TODO Automatisk genereret catch-blok
			e1.printStackTrace(); 
			}
			
			
		
		
		
	}

	public String getClient_id() {
		return client_id;
	}


	public String getAccount_id() {
		return account_id;
	}

	public String getBalance() {
		return balance;
	}
	

	/*public void setBalance(String balance) {
		this.balance = balance;
	}*/
	
	
}
