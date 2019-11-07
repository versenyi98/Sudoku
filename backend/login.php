<?php
    header('Content-type:application/json');
    include_once('util.php');

    // login is only called to get a random salt
    if(isset($_POST['getsalt']))
    {
        $salt = getSalt();
        echo json_encode($salt);
        die();
    }

    if(!isset($_POST['username']) || !isset($_POST['hash']) || !isset($_POST['salt']))
    {
        http_response_code(400);
        die("{\"errorMessage\": \"A username, password hash, or password salt hasn't been provided.\"}");
    }
?>