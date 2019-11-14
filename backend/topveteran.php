<?php
    include_once("util.php");

    // build the where clause
    $WHERE = isset($_GET["type"]) ? "WHERE GameTypeId = {$_GET["type"]}" : "WHERE ";
    if(isset($_GET["difficulty"]))
    {
        if($WHERE != "WHERE ")
        {
            $WHERE = $WHERE . " AND ";
        }
        $WHERE = $WHERE . "DifficultyId = {$_GET["difficulty"]}";
    }

    if(isset($_GET["mode"]))
    {
        if($WHERE != "WHERE ")
        {
            $WHERE = $WHERE . " AND ";
        }
        $WHERE = $WHERE . "GameModeId = {$_GET["mode"]}";
    }

    if($WHERE == "WHERE ")
    {
        $WHERE = "";
    }

    $topnum = isset($_GET["topnum"]) ? $_GET["topnum"] : 10;

    $conn = getDBConnection();
    $query = "SELECT UserId, COUNT(UserId) FROM Game $WHERE GROUP BY UserId ORDER BY 2 DESC LIMIT $topnum;";
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
            "name" => $row[0],
            "value" => $row[1]
        );
    }
    $output = array(
        "success" => true,
        "list" => $resultArr
    );

    $conn->close();
    echo json_encode($output, JSON_UNESCAPED_UNICODE);
?>