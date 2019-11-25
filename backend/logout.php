<?php
    include_once("util.php");

    if(!isset($_POST["token"]))
    {
        dieWithError(401, $error_no_auth, "A session token hasn't been provided.");
    }

    $conn = getDBConnection();
    if($conn === false)
    {
        dieWithError(500, $error_database, "A connection to the database server could not be established.");
    }

    $query = "UPDATE User SET Token = NULL WHERE Token = '{$_POST['token']}';";
    $result = $conn->real_query($query);
    if($result === false)
    {
        dieWithError(500, $error_database, "A DB query could not be executed.", $conn);
    }

    $resultArr = array(
        "success" => true
    );
    echo json_encode($resultArr, JSON_UNESCAPED_UNICODE);
?>