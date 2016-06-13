package com.mmh.pkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Client {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private Statement statement;
	private String url = "jdbc:db2://192.86.32.54:5040/DALLASB";

	
	private String client_id;
	private String client_name;
	private String client_postalnr;
	private String client_password;
	private String client_advisor;
	
	
	// Constructor which from a given client ID gets the remaining information from the database
	public Client(String client_id) {
		
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
			result = statement.executeQuery("SELECT \"client_name\", \"client_postalnr\", \"client_password\", \"advisor_id\" FROM \"DTUGRP08\".\"CLIENT\" WHERE client_id=" + client_id);
			this.client_id = client_id;
			this.client_name = result.getString(1);
			this.client_postalnr = result.getString(2);
			this.client_password = result.getString(3);
			this.client_advisor = result.getString(4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	public String getClient_id() {
		return client_id;
	}

	public String getClient_name() {
		return client_name;
	}

	/*public void setClient_name(String client_name) {
		this.client_name = client_name;
	}*/

	public String getClient_postalnr() {
		return client_postalnr;
	}

	/*public void setClient_postalnr(String client_postalnr) {
		this.client_postalnr = client_postalnr;
	}*/

	public String getClient_password() {
		return client_password;
	}

	/*public void setClient_password(String client_password) {
		this.client_password = client_password;
	}*/

	public String getClient_advisor() {
		return client_advisor;
	}

	/*public void setClient_advisor(String client_advisor) {
		this.client_advisor = client_advisor;
	}*/
}
