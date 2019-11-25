<?php
    include_once("util.php");

    if(!isset($_POST['username']) || !isset($_POST['password']))
    {
        dieWithError(400, $error_no_auth, "A username or password hasn't been provided.", $conn);
    }

    $userSalt = getSalt();
    $passHash = hash("SHA256", $_POST['password'] . $userSalt);

    $conn = getDBConnection();
    $query = "INSERT INTO User(Name, PasswordHash, PasswordSalt) VALUES('" . $_POST["username"] . "', '$passHash', '$userSalt');";
    $result = mysqli_query($conn, $query);
    
    switch(mysqli_errno($conn))
    {
        case 0: // no error
            $conn->close();

            $result = array("success" => true);
            exit(json_encode($result));
            break;
        case 1062: // duplication - there is already an entry with this name
            dieWithError(403, $error_wrong_auth, "This username already exists.", $conn);
            break;
        default:
            dieWithError(500, $error_database, "Unknown database error.", $conn);
            break;
    }
?>