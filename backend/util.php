<?php
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
?>