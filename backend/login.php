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

    if(!isset($_POST['username']) || !isset($_POST['hash']))
    {
        http_response_code(400);
        $error = array(
            $error_out_code => $error_no_auth,
            $error_out_msg => "A username or password hash hasn't been provided."
        );
        $conn->close();
        die(json_encode($error));
    }


?>