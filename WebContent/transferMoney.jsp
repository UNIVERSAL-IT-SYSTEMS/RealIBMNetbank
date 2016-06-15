<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Trygbank - Overfør</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		
		<%@ page import="com.mmh.pkg.Account" %>
		<%@ page import="com.mmh.pkg.GenerateAccountInfo" %>
		<%@ page import="java.sql.ResultSet" %>
		<%@ page import="java.util.*" %>
	</head>
	<body>
		<header class="banner">
			<h1>Overf�r</h1>
		</header>
		
		<% ArrayList<Account> accounts = (ArrayList<Account>) request.getAttribute("accounts");%>

      
      <nav>
      <ul>
        <li><a href="frontPageCostumer.jsp">Forside</a></li>
        <li><a href="/transferMoney">Overf�rrsel</a></li>
        <li><a href="accountTransactions.jsp">Kontobev�gelser</a></li>        
        <li><a href="infoCostumer.jsp">Info</a></li>
      </ul>
    </nav>	
	
	<main>
		<article>
			<h2>Overf�r penge</h2>
				<section>
				<ul>
				<form action="action_page.php">
					<p>Fra konto:</p>
					<select>
						<c:forEach items="${accounts}" var="current">
               				<tr>
               					<option value=""><c:out value="${current.account_id}"/></option>
               				</tr>
               			</c:forEach>
					</select><br><br>
				
					<p>Til konto:</p>
					<input type="text" name="Modtager" value=""><br><br>
				
					<p>Bel�b:</p>
					<input type="text" name="Beloeb" value=""><br><br>
					<input type="submit" value="Send">
	
				</form>
				</ul>
			</section>
		<article>
	</main>
	
	</body>
</html>