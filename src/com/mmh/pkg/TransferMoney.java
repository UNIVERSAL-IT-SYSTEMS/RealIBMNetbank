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

@WebServlet("/TransferMoney")
public class TransferMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private Statement statement;
	private String url = "jdbc:db2://192.86.32.54:5040/DALLASB";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String transaction_id = null;
		String account_from = null;
		String account_to = null;
		String amount = null;
		String description = null;
		double current_balance_from = 0;
		double current_balance_to = 0;
		ArrayList<String> accountArray = new ArrayList();

		Properties properties = new Properties();
		properties.put("user", "DTU22");
		properties.put("password", "FAGP2016");
		properties.put("retrieveMessagesFromServerOnGetMessage", "true");
		properties.put("emulateParameterMetaDataForZCalls", "1");

		try {
			Class.forName("com.ibm.db2.jcc.DB2Driver");
			connection = DriverManager.getConnection(url, properties);
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

		ResultSet result1;

		// Check if the transaction id is longer that 7 characters or if it is
		// allready in use.
		try {
			transaction_id = request.getParameter("transaction_id");
			result1 = statement
					.executeQuery("SELECT \"trans_id\" FROM \"DTUGRP08\".\"TRANSACTION\"");
			while (result1.next()) {
				if (transaction_id.length() > 7
						|| transaction_id.equals(result1.getString(1))) {
					request.getRequestDispatcher("invalidTransferInfo.jsp")
							.forward(request, response);
					return;
				}
			}

			// Check if the transactions are being made to and from the same
			// account, if the accounts are in the database and if the
			// description is longer that 20 characters
			account_from = request.getParameter("account_from");
			account_to = request.getParameter("account_to");
			description = request.getParameter("description");
			result1 = statement
					.executeQuery("SELECT \"acc_id\" FROM \"DTUGRP08\".\"ACCOUNT\"");
			while (result1.next()) {
				accountArray.add(result1.getString(1));
			}
			if (account_from.equals(account_to)
					|| !accountArray.contains(account_from)
					|| !accountArray.contains(account_to)
					|| description.length() > 20) {
				request.getRequestDispatcher("invalidTransferInfo.jsp")
						.forward(request, response);
				return;
			}

			// Try parsing the amount to a double. If not possible, send the
			// user to the fail page
			amount = request.getParameter("amount");
			double amount_double = 0;
			try {
				amount_double = Double.parseDouble(amount);
			} catch (Exception e) {
				request.getRequestDispatcher("invalidTransferInfo.jsp")
						.forward(request, response);
				e.printStackTrace();
				return;
			}

			// Find the current balance of the account sending the money.
			String current_account_from;
			result1 = statement.executeQuery("SELECT \"balance\", \"acc_id\" FROM \"DTUGRP08\".\"ACCOUNT\"");
			while (result1.next()) {
				current_account_from = result1.getString(2);
				if (current_account_from.equals(request.getParameter("account_from"))) {
					current_balance_from = Double.parseDouble(result1.getString(1));
				}
			}

			// Find the current balance of the account receiving the money.
			String current_account_to;
			result1 = statement
					.executeQuery("SELECT \"balance\", \"acc_id\" FROM \"DTUGRP08\".\"ACCOUNT\"");
			while (result1.next()) {
				current_account_to = result1.getString(2);
				if (current_account_to.equals(request.getParameter("account_to"))) {
					current_balance_to = Double.parseDouble(result1.getString(1));
				}
			}

			// Test to see if the transfer amount is negative
			// or the transfer account will be negative after the transaction
			double new_amount1 = current_balance_from - amount_double;
			if (new_amount1 < 0 || amount_double < 0) {
				request.getRequestDispatcher("invalidTransferInfo.jsp").forward(request, response);
				return;
			}

			// Update the balances and create a transaction in the database
			double new_amount2 = current_balance_to + amount_double;
			statement.executeUpdate("UPDATE \"DTUGRP08\".\"ACCOUNT\" SET \"balance\"="
							+ new_amount1
							+ " WHERE \"acc_id\"='"
							+ account_from + "'");
			statement.executeUpdate("UPDATE \"DTUGRP08\".\"ACCOUNT\" SET \"balance\"="
							+ new_amount2
							+ " WHERE \"acc_id\"='"
							+ account_to
							+ "'");
			String query = "INSERT INTO \"DTUGRP08\".\"TRANSACTION\" VALUES (?, ?, ?, ?, ?)";
			try {
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, transaction_id);
				ps.setString(2, account_from);
				ps.setString(3, account_to);
				ps.setString(4, amount);
				ps.setString(5, description);
				ps.executeUpdate();

				// if invalid arguments are passed, the user is transfered to a
				// fail page
			} catch (SQLException e1) {
				request.getRequestDispatcher("invalidTransferInfo.jsp")
						.forward(request, response);
				e1.printStackTrace();
			}

			request.getRequestDispatcher("transactionSucces.jsp").forward(
					request, response);

			return;
		}

		catch (Exception e) {
			request.getRequestDispatcher("invalidTransferInfo.jsp").forward(
					request, response);
			e.printStackTrace();
		}

	}
}