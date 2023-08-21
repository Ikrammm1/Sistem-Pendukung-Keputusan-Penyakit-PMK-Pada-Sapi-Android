<?php

require_once('koneksi.php');

$id_datasapi = $_POST['id_datasapi'];
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

$queryUpdatesapi = "UPDATE tb_datasapi SET alternatif = '$alternatif',
    airliur = '$airliur',
    C1 ='$C1', 
    kakipincang = '$kakipincang',
    C2 = '$C2', 
    nafsumakan = '$nafsumakan',
    C3 = '$C3', 
    lidahbengkak='$lidahbengkak',
    C4='$C4', 
    postur='$postur',
    C5='$C5', 
    status_hitung='Belum' 
    WHERE id_datasapi = '$id_datasapi'";
    
$Updatesapi = mysqli_query($koneksi, $queryUpdatesapi);

if($Updatesapi){

    $data["message"] = "data saved successfully";
    $data["status"] = "Ok";

}else {
    $data["message"] = "data not saved successfully";
    $data["status"] = "error";   
}
echo json_encode($data);



header('Content-Type: application/json')
?>