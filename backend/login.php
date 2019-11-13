<?php
    include_once('util.php');
    include_once('errors.php');

    $conn = getDBConnection();
    if($conn === false)
    {
		dieWithError(500, $error_database, "A connection to the database server could not be established.");
    }

    // login is only called to get a random salt
    if(isset($_POST['getsalt']))
    {
        $output = array("salt" => getSalt());
        
        $conn->close();
        die(json_encode($output));
    }

	// a username or password hasn't been provided
    if(!isset($_POST['username']) || !isset($_POST['password']))
    {
		dieWithError(400, $error_no_auth, "A username or password hasn't been provided.", $conn);
    }

	// try to get the password hash associated with this username
	$passhash = $conn->query("SELECT PasswordHash, PasswordSalt FROM User WHERE Name = '" . $_POST["username"] . "';")->fetch_row();
	// there is no such username
	if($passhash === null)
	{
		dieWithError(401, $error_wrong_auth, "The username provided doesn't exist.", $conn);
	}

	// get the hash of the password sent, and check if it matches with our hash
	$clientPassHash = hash("SHA256", $_POST['password'] . $passhash[1]);
	if($passhash[0] != $clientPassHash)
	{
		dieWithError(401, $error_wrong_auth, "The password provided is incorrect.", $conn);
	}

	// correct login info, attempt to write new token to DB
	$token = getToken();
	if(!$conn->real_query("UPDATE User SET Token = '" . $token . "' WHERE Name = '" . $_POST["username"] . "';"))
	{
		dieWithError(500, $error_database, "The connection to the database server has been severed.", $conn);
	}
	$conn->close();

	// at this point, the token has been successully written to the database
	// send the token out to the client
	$result = array("token" => $token);
	echo json_encode($result);
?>
