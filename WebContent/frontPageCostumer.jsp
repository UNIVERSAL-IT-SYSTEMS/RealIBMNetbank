<!DOCTYPE>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Trygbank - Forside</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<%@ page import="com.mmh.pkg.Account" %>
		<%@ page import="com.mmh.pkg.GenerateAccountInfo" %>
		<%@ page import="java.sql.ResultSet" %>
		
	</head>
	<body>
		<header class="banner">
			<h1>Forside</h1>
		</header>
	
		<% ArrayList<Account> accounts = (ArrayList<Account>) request.getAttribute("accounts");%>
      
      <nav>
      <ul>
        <li><a href="frontPageCostumer.jsp">Forside</a></li>
        <li><a href="transferMoney.jsp">Overførsel</a></li>
        <li><a href="kontobevaegelser.jsp">Kontobevægelser</a></li> 
        <li><a href="infoCostumer.jsp">Info</a></li>
      </ul>
    </nav>
    	
    	
		
		<main>
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

		
		</main>
		
		<footer>
				<p>TrygBank</p>
		</footer>
		
	</body>
	
</html>