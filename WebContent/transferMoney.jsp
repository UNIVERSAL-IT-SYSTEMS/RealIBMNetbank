<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Trygbank - Overfør</title>
		<link rel="stylesheet" type="text/css" href="style.css">
	</head>
	<body>
		<header class="banner">
			<h1>Overfør</h1>
		</header>
      
      <nav>
      <ul>
        <li><a href="Forside_Kunde.jsp">Forside</a></li>
        <li><a href="overfoer.jsp">Overf�rrsel</a></li>
        <li><a href="kontobevaegelser.jsp">Kontobev�gelser</a></li>        
        <li><a href="Info.jsp">Info</a></li>
      </ul>
    </nav>	
	
	<main>
		<article>
			<h2>Overfør penge</h2>
				<section>
				<ul>
				<form action="action_page.php">
					<p>Fra konto:</p>
					<select name="afsenderkonto">
						<option value="">konto</option>
						<option value="saab">konto</option>
					</select><br><br>
				
					<p>Til konto:</p>
					<input type="text" name="Modtager" value=""><br><br>
				
					<p>Beløb:</p>
					<input type="text" name="Beloeb" value=""><br><br>
					<input type="submit" value="Send">
	
				</form>
				</ul>
			</section>
		<article>
	</main>
	
	</body>
</html>