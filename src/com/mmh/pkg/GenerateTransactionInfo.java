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

import java.util.ArrayList;
import java.util.Properties;

@WebServlet("/GenerateTransactionInfo")
public class GenerateTransactionInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private Statement statement;
	private String url = "jdbc:db2://192.86.32.54:5040/DALLASB";
	private ArrayList<Account> accountList = new ArrayList();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Nu er vi herinde i GenerateTransactionInfo");	
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
		
		String id = (String) request.getAttribute("ID");
		String account_id;
		String balance;

		try {
			String query = "SELECT \"acc_id\", \"balance\" FROM \"DTUGRP08\".\"ACCOUNT\" WHERE \"client_id\" = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,id);
			ResultSet rs = ps.executeQuery();
			int i = 0;
			while(rs.next()) {
				 account_id = rs.getString("acc_id");
				 balance = rs.getString("balance");
				 Account account = new Account(account_id, balance, id);
				 if (!accountList.contains(account)) {
					 accountList.add(account);
				 }
				 System.out.println(accountList.get(i).getAccount_id());
				 System.out.println(accountList.get(i).getBalance());
				 System.out.println(accountList.get(i).getClient_id());
				 i++;
			}
			for(int e = 0; i < accountList.size(); e++) {   
				System.out.println(accountList.get(e).getAccount_id());
			} 
			
			request.setAttribute("accounts",accountList);
			ServletContext context = getServletContext();
			context.getRequestDispatcher("transferMoney.jsp").forward(request, response);
			//request.getRequestDispatcher("frontPageCostumer.jsp").forward(request, response);
			System.out.println("Efter - GenerateTransactionInfo");
			accountList.clear();
			return;

	
	} catch (SQLException e) {
		System.out.println("Catch - GenerateAccountInfo");
		e.printStackTrace();
	}	
		
		

}


	public ArrayList<Account> getAccountList() {
		return accountList;
	}
	
}