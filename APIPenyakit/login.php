<?php
//koneksi database
require_once('koneksi.php');

//tangkap data yang dikirim dari android
$username = $_POST["username"];
$password = $_POST["password"];

//proses periksa username dan password di database
$query = "SELECT * FROM tb_user where username='$username' AND password ='$password'";
$obj_query = mysqli_query($koneksi, $query);
$data = mysqli_fetch_assoc($obj_query);

//periksa apakah login sudah benar
if ($data) {
    echo json_encode(
        array(
            'response' => true,
            'payload' => array(
                "id_user" => $data["id_user"],
                "nama_user" => $data["nama_user"],
                "username" => $data["username"],
                "password" => $data["password"],
                "level" => $data["level"],
                
            )
        )
    );
} else {
    echo json_encode(
        array(
            'response' => false,
            'payload' => null
        )
    );
}

//mengatur tampilan
header('Content-Type: application/json');