<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Trygbank - Opret Kunde</title>
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
	<body>
		<header class="banner">
			<h1>Opret Kunde</h1>
		</header>
      
      <nav>
      <ul>
         <li><a href="frontPageAdvisor.jsp">Forside</a></li>
        <li><a href="createCostumer.jsp">Opret ny kunde</a></li>
		</ul>
    </nav>	
	
	<main>
		<article>
			<h2>Opret kunde</h2>
					<section>
					<ul>
						<form class="form-signin" action="CreateCostumer" method="post">
							ID:<br>
							<input type="text" name="client_id" value=""><br><br>

							Navn:<br>
								<input type="text" name="client_name" value=""><br>
							Password:<br>
								<input type="text" name="client_password" value=""><br>
							Postnummer:
								<input type="text" name="client_postalnr" value=""><br>
							Rådgiver ID:
								<input type="text" name="advisor_id" value=""><br>
							Kontonummer:
								<input type="text" name="account_id" value=""><br>
							Start Beløb:
								<input type="text" name="balance" value=""><br>
							Valuta:
							<input type="text" name="currency" value=""><br>
							Rente Procent:
							<input type="text" name="interest_rate" value=""><br>
								<input type="submit" value="Opret Kunde">
		
								
						</form>
							
					</ul>
					</section>
					
		</article>
	</main>
	</body>
</html>