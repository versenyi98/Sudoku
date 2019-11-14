<?php
    include_once("util.php");
    if(!isset($_GET["mode"]) || !isset($_GET["difficulty"]) || !isset($_GET["type"]))
    {
        dieWithError(422, $error_argument, "The mode, difficulty, or type of game has not been specified.");
    }

    $topnum = isset($_GET["topnum"]) ? $_GET["topnum"] : 10;

    $conn = getDBConnection();
    $query = "SELECT UserId, COUNT(UserId) FROM Game WHERE GameTypeId = {$_GET['type']} AND GameModeId = {$_GET['mode']} AND DifficultyId = {$_GET['difficulty']} GROUP BY UserId ORDER BY 2 DESC LIMIT $topnum;";
    $result = $conn->query($query);

    if(mysqli_errno($conn) != 0)
    {
        $err = mysqli_error($conn);
        dieWithError(500, $error_database, "An error has occured while executing a query: $err.", $conn);
    }

    $resultArr = array(); $rowNum = 0; $row;
    while($row = $result->fetch_row())
    {
        $resultArr[$rowNum ++] = array(
            "UserId" => $row[0],
            "Count" => $row[1]
        );
    }

    $conn->close();
    echo json_encode($resultArr, JSON_UNESCAPED_UNICODE);
?>