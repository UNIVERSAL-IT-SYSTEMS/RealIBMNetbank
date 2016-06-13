package com.mmh.pkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class AdvisorClient {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private Statement statement;
	private String url = "jdbc:db2://192.86.32.54:5040/DALLASB";

	
	private String client_id;
	private String advisor_name;
	private String advisor_id;
	private String advisor_phone;
	private String advisor_dept;
	
	public AdvisorClient(String client_id) {
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
		
		String query = "SELECT \"advisor_id\" FROM \"DTUGRP08\".\"CLIENT\" WHERE \"client_id\" = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, client_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				this.advisor_id = rs.getString("advisor_id");
			}
			} catch (SQLException e1) {
			// TODO Automatisk genereret catch-blok
			e1.printStackTrace();
			
			}
		
		
		query = "SELECT \"adv_name\", \"adv_id\", \"adv_phone\", \"dept_id\" FROM \"DTUGRP08\".\"ADVISOR\" WHERE \"adv_id\" = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, advisor_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				this.advisor_name = rs.getString("adv_name");
				this.advisor_id = rs.getString("adv_id");
				this.advisor_phone = rs.getString("adv_phone");
				this.advisor_dept = rs.getString("dept_id");
			}
			} catch (SQLException e1) {
			// TODO Automatisk genereret catch-blok
			e1.printStackTrace();
			
			}
		}
		
/*		ResultSet result;
		try {
			result = statement.executeQuery("SELECT \"adv_name\", \"adv_id\", \"adv_phone\", \"dept_id\" FROM \"DTUGRP08\".\"ADVISOR\" NATURAL JOIN \"DTUGRP08\".\"CLIENT\" WHERE \"client_id\"='" + client_id + "'");
			
			this.client_id = client_id;
			this.advisor_name = result.getString(1);
			this.advisor_id = result.getString(2);
			this.advisor_phone = result.getString(3);
			this.advisor_dept = result.getString(4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
*/

	public String getClient_id() {
		return client_id;
	}

	public String getAdvisor_name() {
		return advisor_name;
	}

	/*public void setAdvisor_name(String advisor_name) {
		this.advisor_name = advisor_name;
	}*/

	public String getAdvisor_id() {
		return advisor_id;
	}

	public String getAdvisor_phone() {
		return advisor_phone;
	}

	/*public void setAdvisor_phone(String advisor_phone) {
		this.advisor_phone = advisor_phone;
	}*/

	public String getAdvisor_dept() {
		return advisor_dept;
	}

	/*public void setAdvisor_dept(String advisor_dept) {
		this.advisor_dept = advisor_dept;
	}*/

}
