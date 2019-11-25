<?php
    include_once("util.php");

    // no authentication
    if(!isset($_POST["token"]))
    {
        dieWithError(401, $error_no_auth, "No session token has been provided.");
    }

    if( !isset($_POST["username"]) ||
        !isset($_POST["type"]) ||
        !isset($_POST["mode"]) ||
        !isset($_POST["difficulty"]) ||
        !isset($_POST["time"]) ||
        !isset($_POST["won"]))
    {
        dieWithError(400, $error_argument, "One or more piece of information about the game has not been provided. See API specification.");
    }

    $conn = getDBConnection();
    if($conn === false)
    {
        dieWithError(500, $error_database, "A connection to the DB server could not be established.");
    }

    $query = "SELECT Id FROM User WHERE `Name` = '{$_POST['username']}' AND `Token` = '{$_POST['token']}';";
    $result = $conn->query($query); $userid;
    if($result == false || !($userid = $result->fetch_row()[0]))
    {
        echo mysqli_error($conn);
        dieWithError(500, $error_database, "User ID couldn't be fetched from the username.", $conn);
    }

    $query = "SELECT Id FROM GameType WHERE Name = '{$_POST['type']}';"; // SELECT Id FROM GameType WHERE Name = 0;
    $result = $conn->query($query); $typeid;
    if($result == false || !($typeid = $result->fetch_row()[0]))
    {
        dieWithError(500, $error_database, "Game type ID couldn't be fetched from the type name.", $conn);
    }

    $query = "SELECT Id FROM GameMode WHERE Name = '{$_POST['mode']}';";
    $result = $conn->query($query); $modeid;
    if($result == false || !($modeid = $result->fetch_row()[0]))
    {
        dieWithError(500, $error_database, "Game mode ID couldn't be fetched from the mode name.", $conn);
    }

    $query = "SELECT Id FROM Difficulty WHERE Name = '{$_POST['difficulty']}';";
    $result = $conn->query($query); $diffid;
    if($result == false || !($diffid = $result->fetch_row()[0]))
    {
        dieWithError(500, $error_database, "Difficulty ID couldn't be fetched from the difficulty name.", $conn);
    }

    $time = $_POST["time"];
    $gamewon = isTrueValue($_POST["won"]) ? "true" : "false";

    $query = "INSERT INTO Game(UserId, GameTypeId, GameModeId, DifficultyId, Length, Won) VALUES($userid, $typeid, $modeid, $diffid, SEC_TO_TIME($time), $gamewon);";
    $result = $conn->real_query($query);
    if($result === false)
    {
        dieWithError(500, $error_database, "The result insertion couldn't be done.", $conn);
    }

    $resultArr = array("success" => true);
    echo json_encode($resultArr, JSON_UNESCAPED_UNICODE);

    function isTrueValue($str)
    {
        return in_array($str, array("1", "true", "True"));
    }
?>