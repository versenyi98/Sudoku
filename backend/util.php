<?php
    header('Content-type:application/json');
    include_once("errors.php");
    
    function dieWithError($httpCode, $errorCode, $errorMsg, $conn = null)
    {
        // set the http response code
        http_response_code($httpCode);

        // collect the error into a format that can be easily jsonified (array)
        $error = array(
            "success" => false,
            "errorCode" => $errorCode,
            "errorMessage" => $errorMsg
        );

        // if there is a DB connection, close it before dying
        if($conn != null)
        {
            $conn->close();
        }

        // die and send out the error message and code
        die(json_encode($error));
    }

    // getToken is an alias for getSalt, the format is the same
    function getToken()
    {
        return getSalt();
    }

    function getSalt()
    {
        $alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        $result = "";
        for($i = 0; $i < 32; $i ++)
        {
            $result = $result . $alphabet[mt_rand(0, strlen($alphabet) - 1)];
        }

        return $result;
    }

    function getDBConnection()
    {
        $username = "root";
        $host = "localhost";
        $password = "sajtládakecske";
        $database = "Sudoku";

        $conn = new mysqli($host, $username, $password, $database);

        if($conn->connect_error)
            return false;
        return $conn;
    }
?>