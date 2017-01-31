<?php
$db_name = "id649347_faculty";
$mysql_username = "id649347_faculty";
$mysql_password = "root123";
$server_name = "localhost";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);

if (mysqli_connect_errno()) {
	echo "Failed to connect to database: " . mysqli_connect_error();
} else {
	echo "SUCCESS";
}
?>