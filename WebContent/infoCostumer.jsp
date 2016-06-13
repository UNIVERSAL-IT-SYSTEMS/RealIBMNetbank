<!DOCTYPE>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Trygbank - Info</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<%@ page import="com.mmh.pkg.Client" %>
		<%@ page import="com.mmh.pkg.AdvisorClient" %>
		
	</head>
	<body>
		<header class="banner">
			<h1>Info</h1>
		</header>
	<%Client client = (Client) request.getAttribute("currentClient");%>
	<%AdvisorClient advisor = (AdvisorClient) request.getAttribute("currentAdvisorClient");%>
      
      <nav>
      <ul>
        <li><a href="frontPageCostumer.jsp">Forside</a></li>
        <li><a href="transferMoney.jsp">Overførsel</a></li>
        <li><a href="accountTransactions">Kontobevægelser</a></li>        
        <li><a href="infoCostumer.jsp">Info</a></li>
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
						<p>Password: <%=client.getClient_password() %></p>
						
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
		
		</main>
		
		<footer>
				<p>TrygBank</p>
		</footer>
		
	</body>
	
</html>