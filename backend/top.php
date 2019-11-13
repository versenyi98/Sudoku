<?php
    include_once("util.php");
    if(!isset($_GET["mode"]) || !isset($_GET["difficulty"]) || !isset($_GET["type"]))
    {
        dieWithError(422, $error_argument, "The mode, difficulty, or type of game has not been supplied.");
    }

    $topnum = isset($_GET["topnum"]) ? $_GET["topnum"] : 10;

    $conn = getDBConnection();
    $modeID = $conn->query("SELECT Name FROM GameMode WHERE Id = " . $_GET["mode"] . ";")->fetch_row()[0];
    $diffID = $conn->query("SELECT Name FROM Difficulty WHERE Id = " . $_GET["difficulty"] . ";")->fetch_row()[0];
    $typeID = $conn->query("SELECT Name FROM GameType WHERE Id = " . $_GET["type"] . ";")->fetch_row()[0];
?>