<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Java Projekt RPG game</title>

    <!-- Bootstrap core CSS -->
    
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
	

    <!-- Custom styles for this template -->
    <link href="jumbotron.css" rel="stylesheet">
  </head>

  <body>

    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
      <a class="navbar-brand" href="#">Java Project</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item ">
            <a class="nav-link" href="index.php">Home <span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="register.php">Register</a>
          </li>
          <li class="nav-item">
            <a class="nav-link " href="#">Login</a>
          </li>
        </ul>
      </div>
    </nav>
<body>
  


<?php
$db = new PDO('mysql:host=mn26.webd.pl;dbname=marekb93_rpggame', 'marekb93_rpggame', 'xBGG)2Jn&b?E?kC+');

if (! empty($_POST)) {
    $username = $_POST['username'];
    $password = $_POST['password'];
    $role = $_POST['role'];
    
    $db->query("INSERT INTO players (login, password, role, gold, skill, magic, exp) VALUES ('" . $username . "', '" . $password . "', '" . $role . "', 100, 1, 1, 1)");
    echo "Account was created";
}

?>
	

<br><br><br>

	<form method="post">
		<div class="form-group">
			<label for="exampleInputEmail1">Login</label> <input type="login"
				name="username" class="form-control" id="exampleInputEmail1"
				aria-describedby="emailHelp" placeholder="Enter login"> <small
				id="emailHelp" class="form-text text-muted">Login has to be
				available!</small>
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Password</label> <input
				type="password" name="password" class="form-control"
				id="exampleInputPassword2" placeholder="Password">
		</div>
		<div class="form-check">
			<input class="form-check-input" value="knight" type="radio"
				name="role" id="exampleRadios1" value="option1" checked> <label
				class="form-check-label" for="exampleRadios1"> Knight </label>
		</div>
		<div class="form-check">
			<input class="form-check-input" type="radio" value="sorcerer"
				name="role" id="exampleRadios2" value="option2"> <label
				class="form-check-label" for="exampleRadios2"> Sorcerer </label>
		</div>


		<button type="submit" value="Create account" class="btn btn-primary">Submit</button>
	</form>





</body>
</html>
