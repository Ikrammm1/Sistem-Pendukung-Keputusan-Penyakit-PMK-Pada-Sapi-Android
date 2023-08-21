<?php

require_once('koneksi.php');

$sqlsapi = mysqli_query($koneksi, 
"SELECT 
    *
FROM tb_datasapi");
$sqlnilaisapi = mysqli_query($koneksi, 
"SELECT 
    *, 
    MAX(tb_datasapi.C1) as maxC1,
    MAX(tb_datasapi.C2) as maxC2,
    MIN(tb_datasapi.C3) as minC3,
    MAX(tb_datasapi.C4) as maxC4,
    MIN(tb_datasapi.C5) as minC5
FROM tb_datasapi");



//data sapi
$result = array(); 
while($sapi  = mysqli_fetch_array($sqlsapi)) :

    while($nilaisapi  = mysqli_fetch_array($sqlnilaisapi)) :

        $MAX1 = $nilaisapi['maxC1'];
        $MAX2 = $nilaisapi['maxC2'];
        $MIN3 = $nilaisapi['minC3'];
        $MAX4 = $nilaisapi['maxC4'];
        $MIN5 = $nilaisapi['minC5'];

    endwhile;
//perhitungan normalisasi data
    $AC1 = ( $sapi['C1']/$MAX1);
    $AC2 = ( $sapi['C2']/$MAX2);
    $AC3 = ( $MIN3/$sapi['C3']);
    $AC4 = ( $sapi['C4']/$MAX4);
    $AC5 = ( $MIN5/$sapi['C5']);

//data ternormalisasi 
array_push($result, array(
    'id_datasapi' => $sapi['id_datasapi'],
    'normalisasi_C1' =>"$AC1",
    'normalisasi_C2' =>"$AC2",
    'normalisasi_C3' =>"$AC3",
    'normalisasi_C4' =>"$AC4",
    'normalisasi_C5' =>"$AC5"

 ));

endwhile;
echo json_encode ( array('Normalisasi' =>$result));

header('Content-Type: application/json')
?>