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

	private ArrayList<String> account_ID_Array = new ArrayList();
	private ArrayList<String> account_Balance_Array = new ArrayList();
	
	
	public Account(String client_id) {

		Properties properties = new Properties();
		properties.put("user","DTU22");
		properties.put("password", "FAGP2016");
		properties.put("retrieveMessagesFromServerOnGetMessage", "true");
		properties.put("emulateParameterMetaDataForZCalls", "1");
	
		try {
			Class.forName("com.ibm.db2.jc c.DB2Driver");
			connection = DriverManager.getConnection(url, properties);
			statement = connection.createStatement();
		} catch (Exception e) {
			// TODO Automatisk genereret catch-blok
			e.printStackTrace();
		}
		
		

		String query = "SELECT \"acc_id\", \"balance\" FROM \"DTUGRP08\".\"ACCOUNT\" WHERE \"client_id\" = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,client_id);
			ResultSet rs = ps.executeQuery();
			//request.setAttribute("accounts",rs);
			
			
			/*ps.setString(1, client_id);
			ResultSet rs = ps.executeQuery();
			this.client_id = client_id;
			while(rs.next()) {
				this.account_ID_Array.add(rs.getString("acc_id"));
				this.account_Balance_Array.add(rs.getString("balance"));
				System.out.println(account_ID_Array + "   " + account_Balance_Array);
			}*/
			} catch (SQLException e1) {
			// TODO Automatisk genereret catch-blok
				e1.printStackTrace();
			}
	}



	public ArrayList<String> getAccount_ID_Array() {
		return account_ID_Array;
	}

	public String getSingleAccount_ID(int numb) {
		return account_ID_Array.get(numb);
	}



	public ArrayList<String> getAccount_Balance_Array() {
		return account_Balance_Array;
	}

	public String getSingleAccount_Balance(String numb) {
		int numbInt = Integer.parseInt(numb);
		return account_Balance_Array.get(numbInt);
	}

}
