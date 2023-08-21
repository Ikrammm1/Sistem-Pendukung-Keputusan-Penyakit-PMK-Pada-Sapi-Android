<?php

require_once('koneksi.php');

$id_user = $_POST['id_user'];

$queryRanking = mysqli_query($koneksi, 
"SELECT 
    *, 
    RANK()OVER(ORDER BY tb_hasil.nilai DESC) as ranking
FROM tb_hasil
RIGHT JOIN tb_datasapi ON tb_datasapi.id_datasapi = tb_hasil.id_datasapi 
WHERE tb_hasil.id_datasapi = tb_datasapi.id_datasapi
AND tb_datasapi.id_user = '$id_user'");


//data sapi
$result = array(); 
while($rank  = mysqli_fetch_array($queryRanking)) :


 //data nilai Ranking
array_push($result, array(
    'id_datasapi'   =>$rank['id_datasapi'],
    'alternatif'    =>$rank['alternatif'],
    'airliur'       =>$rank['airliur'],
    'kakipincang'   =>$rank['kakipincang'],
    'nafsumakan'    =>$rank['nafsumakan'],
    'lidahbengkak'  =>$rank['lidahbengkak'],
    'postur'        =>$rank['postur'],
    'nilai'         =>$rank['nilai'],
    'ranking'       =>$rank['ranking'],
    'kesimpulan'    => $rank['kesimpulan'],


 ));


endwhile;
echo json_encode ( array('Ranking' =>$result));

header('Content-Type: application/json')
?>