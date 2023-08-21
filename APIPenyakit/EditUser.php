<?php

require_once('koneksi.php');

$id_user = $_POST['id_user'];
$nama_user = $_POST['nama_user'];
$username = $_POST['username'];
$password = $_POST['password'];

$queryUpdate = "UPDATE tb_user SET
    nama_user = '$nama_user',
    username = '$username',
    password = '$password'
    WHERE id_user = '$id_user'";
    
$Update = mysqli_query($koneksi, $queryUpdate);

if($Update){

    $data["message"] = "data saved successfully";
    $data["status"] = "Ok";

}else {
    $data["message"] = "data not saved successfully";
    $data["status"] = "error";   
}

echo json_encode($data);




header('Content-Type: application/json')
?>