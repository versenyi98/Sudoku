<?php
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