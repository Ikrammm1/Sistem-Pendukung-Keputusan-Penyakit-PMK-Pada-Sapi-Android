<?php

require_once('koneksi.php');

$id_user = $_POST['id_user'];
$alternatif = $_POST['alternatif'];
$airliur = $_POST['airliur'];
$kakipincang = $_POST['kakipincang'];
$nafsumakan = $_POST['nafsumakan'];
$lidahbengkak = $_POST['lidahbengkak'];
$postur = $_POST['postur'];

//merubah berat menjadi bobot kriteria C1
if($airliur =="Normal"){
    $C1 = 1;
}elseif($airliur=="Banyak"){
    $C1 = 3;
}
//merubah kecacatan menjadi bobot kriteria C2
if($kakipincang =="Tidak Pincang"){
    $C2 = 1;
}elseif($kakipincang=="Pincang"){
    $C2 = 5;
}
//merubah perilaku menjadi bobot kriteria C3
if($nafsumakan=="Rendah"){
    $C3 = 1;
}elseif($nafsumakan=="Normal"){
    $C3 = 3;
}

//merubah umur menjadi bobot kriteria C4
if($lidahbengkak=="Tidak Bengkak"){
    $C4 = 1;
}elseif($lidahbengkak=="Bengkak"){
    $C4 = 5;
}

//merubah jenis_kelamin menjadi bobot kriteria C3
if($postur=="Normal"){
    $C5 = 3;
}elseif($postur=="Kurus"){
    $C5 = 1;
}

$ceksapi = mysqli_query($koneksi, "SELECT * From tb_datasapi Where alternatif = '$alternatif'");
if($ceksapi->num_rows ==0){
$queryinputsapi = "INSERT INTO tb_datasapi (id_user, alternatif,airliur,C1, kakipincang,C2, nafsumakan,C3, lidahbengkak,C4, postur,C5) 
VALUES ('$id_user','$alternatif','$airliur','$C1', '$kakipincang','$C2', '$nafsumakan','$C3', '$lidahbengkak','$C4', '$postur','$C5')";
$inputsapi = mysqli_query($koneksi, $queryinputsapi);

if($inputsapi){

    $data["message"] = "data saved successfully";
    $data["status"] = "Ok";

}else {
    $data["message"] = "data not saved successfully";
    $data["status"] = "error";   
}
}else {
    $data["message"] = "data not saved successfully";
    $data["status"] = "already";   
}


echo json_encode($data);



header('Content-Type: application/json')
?>