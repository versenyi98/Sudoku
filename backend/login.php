<?php
    header('Content-type:application/json');
    include_once('util.php');
    include_once('errors.php');

    $conn = getDBConnection();
    if($conn === false)
    {
        http_response_code(500);
        $error = array(
            $error_out_code => $error_database,
            $error_out_msg => "A connection to the database server could not be established."
        );
        
        die(json_encode($error));
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
        http_response_code(400);
        $error = array(
            $error_out_code => $error_no_auth,
            $error_out_msg => "A username or password hasn't been provided."
        );
        $conn->close();
        die(json_encode($error));
    }

	// try to get the password hash associated with this username
	$passhash = $conn->query("SELECT PasswordHash, PasswordSalt FROM User WHERE Name = '" . $_POST["username"] . "';")->fetch_row();
	// there is no such username
	if($passhash === null)
	{
		http_response_code(401);
		$error = array(
			$error_out_code => $error_wrong_auth,
			$error_out_msg => "The username provided doesn't exist."
		);
		$conn->close();
		die(json_encode($error));
	}

	// get the hash of the password sent, and check if it matches with our hash
	$clientPassHash = hash("SHA256", $_POST['password'] + $passhash[1]);
	if($passhash != $clientPassHash)
	{
		http_response_code(401);
		$error = array(
			$error_out_code => $error_wrong_auth,
			$error_out_msg => "The password provided is incorrect"
		);
		$conn->close();
		die(json_encode($error));
	}

	// correct login info, attempt to write new token to DB
	$token = getToken();
	if(!$conn->real_query("UPDATE User SET Token = '" . $token . "' WHERE Name = '" . $_POST["username"] . "';"))
	{
		http_response_code(500);
		$error = array(
			$error_out_code => $error_database,
			$error_out_msg => "The connection to the database server has been severed."
		);
		$conn->close();
		die(json_encode($error));
	}

	// at this point, the token has been successully written to the database
	// send the token out to the client
	$result = array("token" => $token);
	echo json_encode($result);
?>
