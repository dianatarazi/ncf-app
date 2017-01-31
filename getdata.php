<?php
require "conn.php";

$response = array();

$result = mysqli_query($conn, "SELECT * FROM ncf_faculty") or die(mysql_error());

if (mysqli_num_rows($result) > 0) {
	$response["ncf_faculty"] = array();

	while ($row = mysqli_fetch_array($result)) {
		$faculty = array();
		$faculty["members_first_name"] = $row["members_first_name"];
		$faculty["members_last_name"] = $row["members_last_name"];
		$faculty["members_position"] = $row["members_position"];
		$faculty["members_email_address"] = $row["members_email_address"];
		$faculty["members_office_location"] = $row["members_office_location"];
		$faculty["members_mail_location"] = $row["members_mail_location"];
		$faculty["members_user_image"] = $row["members_user_image"];
		
		array_push($response["ncf_faculty"], $faculty);
	}

	$response["success"] = 1;

	echo json_encode($response);
} else {
	$response["success"] = 0;
	$response["message"] = "No results found";

	echo json_encode($response);
}
?>