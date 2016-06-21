<!DOCTYPE>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>G8Bank - Forside</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<%@ page import="com.mmh.pkg.Account" %>
		<%@ page import="com.mmh.pkg.Transaction" %>
		<%@ page import="com.mmh.pkg.GenerateAccountInfo" %>
		<%@ page import="java.sql.ResultSet" %>
		<%@ page import="com.mmh.pkg.Client" %>
		<%@ page import="com.mmh.pkg.AdvisorClient" %>
		
	</head>
	<body>
		<header class="banner">
			<h1>Forside</h1>
		</header>
	
		<% ArrayList<Account> accounts = (ArrayList<Account>) request.getAttribute("accounts");%>
		<% ArrayList<Transaction> transactions = (ArrayList<Transaction>) request.getAttribute("transactions");%>
		<%Client client = (Client) request.getAttribute("currentClient");%>
		<%AdvisorClient advisor = (AdvisorClient) request.getAttribute("currentAdvisorClient");%>
		
      
      <nav>
      <ul>
        <li><a href="transferMoney.jsp">Overførsel</a></li>
        <li><a href="index.jsp">Log ud</a></li>
      </ul>
    </nav>
    	
    	
		
		<main>
		
		<article>
				<h2>Kunde</h2>
				<section>
					<ul>
						<p>Navn: <%=client.getClient_name() %> </p>
						<p>ID: <%=client.getClient_id() %></p>
						<p>Post nummer: <%=client.getClient_postalnr() %></p>
						
				</ul>
				</section>
			</article>
			
			<article>
				<h2>Rådgiver</h2>
				<section>
					<ul>
						<p>Navn: <%=advisor.getAdvisor_name()%></p>
						<p>ID: <%=advisor.getAdvisor_id() %></p>
						<p>Telefon: <%=advisor.getAdvisor_phone() %></p>
						<p>Afdelings ID: <%=advisor.getAdvisor_dept() %></p>
					</ul>
				</section>
			</article>
			<article>
              <h2>Konti</h2>
				<section>
					<ul>
					
					<TH> Konto ID: </th>
					<TH> &nbsp &nbsp &nbsp &nbsp Saldo: </th>
					<table>
               			<c:forEach items="${accounts}" var="current">
               				<tr>
               					<td><c:out value="${current.account_id}" /><td>
               					<td> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <c:out value="${current.balance}" /><td>
               				</tr>
               			</c:forEach>
               			
               			
					</table>
				</ul>
				</section>
			</article>	
			
			
			<article>
              <h2>Kontobevægelser:</h2>
				<section>
					<ul>
               			
               			
					</table>
					
					<TH> Transaktion ID: &nbsp &nbsp &nbsp </th>
					<TH> Beløb: &nbsp &nbsp &nbsp </th>
					<TH> Afsender: &nbsp &nbsp &nbsp </th>
					<TH> Modtager: </th>
					<table>
               						<c:forEach items="${transactions}" var="current2">
               						<tr>
               							<td><c:out value="${current2.transaction_id}" /><td>
               							<td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <c:out value="${current2.amount}" /><td>
               							<td> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <c:out value="${current2.account_from}" /><td>
               							<td> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp <c:out value="${current2.account_to}" /><td>
               							</tr>
               						</c:forEach>
               						</table>
				</ul>
				</section>
			</article>	

		
		</main>
		
		<footer>
				<p>G8Bank</p>
		</footer>
		
	</body>
	
</html>