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
	private List<Account> accountArray = new ArrayList();
	
	
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
		
		ResultSet result;
		try {
			result = statement.executeQuery("SELECT \"acc_id\", \"balance\" FROM \"DTUGRP08\".\"ACCOUNT\" WHERE client_id=" + client_id);
			this.client_id = client_id;
			this.account_id = result.getString(1);
			this.balance = result.getString(2);
			//accountArray.add();
		} catch (Exception e) {
			e.printStackTrace();
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
