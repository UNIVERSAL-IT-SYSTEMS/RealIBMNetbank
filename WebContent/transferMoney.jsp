<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>G8Bank - Overfør</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		
		<%@ page import="com.mmh.pkg.Account" %>
		<%@ page import="java.sql.ResultSet" %>
		<%@ page import="java.util.*" %>
	</head>
	<body>
		<header class="banner">
			<h1>Overfør</h1>
		</header>
		
		<% ArrayList<Account> accounts = (ArrayList<Account>) request.getAttribute("accounts");%>

      
      <nav>
      <ul>
        <li><a href="transferMoney.jsp">Overførsel</a></li>
        <li><a href="index.jsp">Log ud</a></li>
      </ul>
    </nav>	
	
	<main>
		<article>
			<h2>Overfør penge</h2>
				<section>
					<ul>
						<form class="form-signin" action="TransferMoney" method="post">
							Fra Konto:<br>
							<input type="text" name="account_from" value=""><br><br>
							Til Konto:<br>
							<input type="text" name="account_to" value=""><br><br>
							Beløb:<br>
							<input type="text" name="amount" value=""><br><br>
							Tekst til kontoudtog:
							<input type="text" name="description" value=""><br><br>
							Transaktions ID:<br>
							<input type="text" name="transaction_id" value=""><br><br>
							<input type="submit" value="Overfør">
						</form>
					</ul>
				</section>
		<article>
	</main>
	
			<footer>
				<p>G8Bank</p>
		</footer>
	
	
	</body>
</html>