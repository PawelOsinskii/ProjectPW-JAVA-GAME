<!doctype html>
<html>
<head>
<title>Register to game</title>
<meta charset="UTF-8" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
  


<?php
$db = new PDO('mysql:host=mn26.webd.pl;dbname=marekb93_rpggame', 'marekb93_rpggame', 'xBGG)2Jn&b?E?kC+');

if (! empty($_POST)) {
    $username = $_POST['username'];
    $password = $_POST['password'];
    $role = $_POST['role'];
    
    $db->query("INSERT INTO players (login, password, role, gold, skill, magic) VALUES ('" . $username . "', '" . $password . "', '" . $role . "', 100, 20, 40)");
    echo "Account was created";
}

?>
	<a class="btn btn-primary" href="register.php">Register!</a>
	<a class="btn btn-primary" href="index.php">MainMenu</a>
	<a class="btn btn-primary" href="about.php">About Game!</a>
	<a class="btn btn-primary" href="we.php">About Us!</a>



	<form method="post">
		<div class="form-group">
			<label for="exampleInputEmail1">Login</label> <input type="text"
				name="username" class="form-control" id="exampleInputEmail1"
				aria-describedby="emailHelp" placeholder="Enter login"> <small
				id="emailHelp" class="form-text text-muted">Login has to be
				available!</small>
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Password</label> <input
				type="password" name="password" class="form-control"
				id="exampleInputPassword1" placeholder="Password">
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
