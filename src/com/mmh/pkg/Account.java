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
	
	
	private String account_id;
	private String balance;
	private String client_id;


	
	public Account(String account_id, String balance, String client_id) {
		this.account_id = account_id;
		this.balance = balance;
		this.client_id = client_id;
	}



	public String getAccount_id() {
		return account_id;
	}



	public String getBalance() {
		return balance;
	}



	public String getClient_id() {
		return client_id;
	}
	
}

