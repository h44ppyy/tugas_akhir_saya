<?php
/*
Metode Mamdani


Metode Mamdani sering juga dikenal dengan nama Metode Max-Min.
Metode ini diperkenalkan oleh Ebrahim Mamdani pada tahun 1975. Untuk mendapatkan output, diperlukan 4 tahapan:
1. Pembentukan himpunan fuzzy
2. Aplikasi fungsi implikasi (aturan)
3. Komposisi aturan
4. Penegasan (deffuzy) 




1. Pembentukan himpunan fuzzy 
            Pada Metode Mamdani, baik variabel input maupun variabel output dibagi menjadi satu atau lebih himpunan fuzzy.
2. Aplikasi fungsi implikasi  Pada Metode Mamdani, fungsi implikasi yang digunakan adalah Min.
3. Komposisi Aturan
          Tidak seperti penalaran monoton, apabila sistem terdiri-dari beberapa aturan, maka inferensi diperoleh dari kumpulan dan korelasi antar aturan. Ada 3 metode yang digunakan dalam melakukan inferensi sistem fuzzy, yaitu: max, additive dan probabilistik OR (probor).
1. Metode Max (Maximum)
          Pada metode ini, solusi himpunan fuzzy diperoleh dengan cara mengambil nilai maksimum aturan, kemudian menggunakannya untuk memodifikasi daerah fuzzy, dan mengaplikasikannya ke output dengan menggunakan operator OR (union). Jika semua proposisi telah dievaluasi, maka output akan berisi suatu himpunan fuzzy yang merefleksikan konstribusi dari tiap-tiap proposisi. Secara umum dapat dituliskan:
                                        탎f[xi] <-- max(탎f[xi], 탃f[xi])
dengan:
탎f[xi] = nilai keanggotaan solusi fuzzy sampai aturan ke-i;-
탃f[xi] = nilai keanggotaan konsekuen fuzzy aturan ke-i;

2. Metode Additive (Sum)
           Pada metode ini, solusi himpunan fuzzy diperoleh dengan cara melakukan bounded-sum terhadap semua output daerah fuzzy. Secara umum dituliskan:
                                      탎f[xi] <-- min(1,탎f[xi]+ 탃f[xi])
dengan:
탎f[xi] = nilai keanggotaan solusi fuzzy sampai aturan ke-i;
탃f[xi] = nilai keanggotaan konsekuen fuzzy aturan ke-i;
3. Metode Probabilistik OR (probor)
           Pada metode ini, solusi himpunan fuzzy diperoleh dengan cara melakukan product terhadap semua output daerah fuzzy. Secara umum dituliskan:
                                  탎f[xi] <-- (탎f[xi]+ 탃f[xi]) - (탎f[xi] * 탃f[xi])
dengan:
탎f[xi] = nilai keanggotaan solusi fuzzy sampai aturan ke-i;
탃f[xi] = nilai keanggotaan konsekuen fuzzy aturan ke-i;

4.Penegasan (defuzzy)
Input dari proses defuzzifikasi adalah suatu himpunan fuzzy yang diperoleh dari komposisi aturan-aturan fuzzy, sedangkan output yang dihasilkan merupakan suatu bilangan pada domain himpunan fuzzy tersebut. Sehingga jika diberikan suatu himpunan fuzzy dalam range tertentu, maka harus dapat diambil suatu nilai crsip tertentu sebagai output


Ada beberapa metode defuzzifikasi pada komposisi aturan MAMDANI, antara lain:
a. Metode Centroid (Composite Moment)
Pada metode ini, solusi crisp diperoleh dengan cara mengambil titik pusat (z*) daerah fuzzy.

b. Metode Bisektor
Pada metode ini, solusi crisp diperoleh dengan cara mengambil nilai pada domain fuzzy yang memiliki nilai keanggotaan separo dari jumlah total nilai keanggotaan pada daerah fuzzy.
c. Metode Mean of Maximum (MOM)
Pada metode ini, solusi crisp diperoleh dengan cara mengambil nilai rata-rata domain yang memiliki nilai keanggotaan maksimum.

d. Metode Largest of Maximum (LOM)
Pada metode ini, solusi crisp diperoleh dengan cara mengambil nilai terbesar dari domain yang memiliki nilai keanggotaan maksimum.

e. Metode Smallest of Maximum (SOM)
Pada metode ini, solusi crisp diperoleh dengan cara mengambil nilai terkecil dari domain yang memiliki nilai keanggotaan maksimum.
*/
   $A1  = isset($_POST['A1'])?$_POST['A1']:NULL;
   $A2  = isset($_POST['A2'])?$_POST['A2']:NULL;
   $B1  = isset($_POST['B1'])?$_POST['B1']:NULL;
   $B2  = isset($_POST['B2'])?$_POST['B2']:NULL;
   $C1  = isset($_POST['C1'])?$_POST['C1']:NULL;
   $C2  = isset($_POST['C2'])?$_POST['C2']:NULL;
   $D1 = isset($_POST['C2'])?$_POST['C2']:NULL;
   $D2 = isset($_POST['C2'])?$_POST['C2']:NULL;
   $X  = isset($_POST['X'])?$_POST['X']:NULL;
   $Y  = isset($_POST['Y'])?$_POST['Y']:NULL;
   $Q = isset($_POST['Q'])?$_POST['Q']:NULL;

?>
<html>
<head>
<title>protoMamdani</title>
</head>
<body>
 
 <h3>Fuzzy Mamdani</h3> 
    
  <form action='' method='post'>
    <table width="427" border="1">
  <tr>
    <td width="205">&nbsp;Maksimal gold from hero (kill) @ 10 min
      <input type='text' class='masukan' name='A1' value='<?=isset($A1)?$A1:'0';?>'></td>
       <td width="206">&nbsp;Minimal gold from hero (kill) @ 10 min
      <input type='text' class='masukan' name='A2' value='<?=isset($A2)?$A2:'500';?>'></td>
  </tr>
  <tr>
    <td>&nbsp;Maksimal Last Hit @ 10 min  <input type='text' class='masukan' name='B1' value='<?=isset($B1)?$B1:'60';?>'> </td>
    <td>&nbsp;Minimal Last Hit @ 10 min <input type='text' class='masukan' name='B2' value='<?=isset($B2)?$B2:'0';?>'> </td>
  </tr>
   <tr>
    <td>&nbsp;Maksimal Bounty Rune @ 10 min  <input type='text' class='masukan' name='D1' value='<?=isset($B1)?$B1:'600';?>'> </td>
    <td>&nbsp;Minimal Bounty Rune @ 10 min <input type='text' class='masukan' name='D2' value='<?=isset($B2)?$B2:'0';?>'> </td>
  </tr>
  <tr>
    <td>&nbsp; Networth Maksimal
     <input type='text' class='masukan' name='C1' value='<?=isset($C1)?$C1:'40000';?>'></td>
    <td>&nbsp;Networth Minimal
     <input type='text' class='masukan' name='C2' value='<?=isset($C2)?$C2:'0';?>'> </td>
  </tr>
  <tr>
    <td>&nbsp; gold from hero @ 10 min (kill)
     <input type='text' class='masukan' name='X' value='<?=isset($X)?$X:'0';?>'> </td>
    <td>&nbsp; last hit @ 10 min <br>
     <input type='text' class='masukan' name='Y' value='<?=isset($Y)?$Y:'0';?>'></td>
     <td>&nbsp; total bounty @ 10 min <br>
     <input type='text' class='masukan' name='Y' value='<?=isset($Y)?$Y:'0';?>'></td>
  </tr>
    </table>
    <input type='submit' >
  </form>
<?php
  if($_POST)
  
{

   echo"<table width='100%' border='1' style='border-collapse:collapse; border-color:#CCCCFF;'>
    <tr align='center'>
     <td>VARIABEL</td>
     <td width='10%'>MAX</td>
     <td width='10%'>MIN</td>
    </tr>
    <tr>
     <td>Kill from hero @ 10min</td>
     <td align='center'>$A1</td>
     <td align='center'>$A2</td>
    </tr>
    <tr>
     <td NoWrap>Last hit @ 10min</td>
     <td align='center'>$B1</td>
     <td align='center'>$B2</td>
    </tr>
    <tr>
     <td NoWrap>bounty rune @ 10min</td>
     <td align='center'>$B1</td>
     <td align='center'>$B2</td>
    </tr>
    <tr>
     <td NoWrap>Networth</td>
     <td align='center'>$C1</td>
     <td align='center'>$C2</td>
    </tr>
    <tr>
     <td>Nilai x permintaan</td>
     <td colspan='2' align='center'>$X</td>
    </tr>
    <tr>
     <td>Nilai y persediaan</td>
     <td colspan='2' align='center'>$Y</td>
    </tr>
   </table>
   
   <br>

   <table width='100%' border='0' style='border-collapse:collapse;'>
    <tr><td Colspan='7'><b>Permintaan</b> :terkecil dan terbesar</td></tr>
    <tr>
     <td width='30%' NoWrap>
      Permintaan naik[$X]
     </td>
     <td align='center'>=</td>
     <td NoWrap align='center'>
      x - Min <hr> Max - Min
     </td>
     <td align='center'>=</td>
     <td NoWrap align='center'>
      $X - $A2 <hr> $A1 - $A2
     </td>
     <td align='center'>=</td>
     <td>"; 
      
      $naik=round(($X - $A2) /($A1 - $A2),3);
     echo "$naik</td>
    </tr>

    <tr>
     <td>
      Permintaan turun[$X]
     </td>
     <td width='5%' align='center'>=</td>
     <td NoWrap width='20%' align='center'>
      Max - x <hr> Max - Min
     </td>
     <td width='5%' align='center'>=</td>
     <td NoWrap width='20%' align='center'>
      $A1 - $X <hr> $A1 - $A2
     </td>
     <td width='5%' align='center'>=</td>
     <td>";
     
      $turun1=round(($A1 - $X) /($A1 - $A2),3);
     echo "$turun1</td>
    </tr>

   <tr><td Colspan='7'><br><b>Persediaan</b> : sedikit dan banyak</td></tr>
   <tr>
    <td NoWrap>
     Persediaan banyak[$Y]
    </td>
    <td align='center'>=</td>
    <td NoWrap align='center'>
     y - Min <hr> Max - Min
    </td>
    <td align='center'>=</td>
    <td NoWrap align='center'>
     $Y - $B2 <hr> $B1 - $B2
    </td>
    <td align='center'>=</td>
    <td>"; 
     $banyak = round(($Y - $B2) /($B1 - $B2),3);
    echo "$banyak</td>
   </tr>
   <tr>
    <td NoWrap>
     Persediaan sedikit[$Y]
    </td>
    <td align='center'>=</td>
    <td NoWrap align='center'>
     Max - y <hr> Max - Min
    </td>
    <td align='center'>=</td>
    <td NoWrap align='center'>
     $B1 - $Y <hr> $B1 - $B2
    </td>
    <td align='center'>=</td>
    <td>"; 
    
     $sedikit = round(($B1 - $Y) /($B1 - $B2),3);
    echo "$sedikit</td>
   </tr>
  </table>

  <br> 
  <table width='98%' border='1'>

   <tr>
    <td width='5%'>&nbsp;</td>
   
    <td width='10%' NoWrap>
     predikat1
    </td>
    <td width='5%' align='center'>=</td>
    <td>
     Permintaan turun[$X] 
     
     Persediaan banyak[$Y]
    </td>
   </tr>
   <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td align='center'>=</td>
    <td>
     Min ( $turun1 , $banyak )
    </td>
   </tr>
   <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td align='center'>=</td>
    <td>"; $P1 = Min($turun1,$banyak); echo "$P1</td>
   </tr>
  </table>
  <table width='98%' border='1'>
   <tr>
    <td width='5%'>&nbsp;</td>
    <td width='10%' NoWrap>
     predikat2
    </td>
    <td width='5%' align='center'>=</td>
    <td>
     Permintaan Turun[$X] 
     
     Persediaan sedikit[$Y]
    </td>
   </tr>
   <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td align='center'>=</td>
    <td>
     Min ( $turun1 , $sedikit )
    </td>
   </tr>
   <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td align='center'>=</td>
    <td>"; $P2 = Min($turun1,$sedikit); echo "$P2</td>
   </tr>
  </table>

  <table width='98%' border='1'>
   <tr>
    <td width='5%'>&nbsp;</td>
  
    <td width='10%' NoWrap>
     predikat3
    </td>
    <td width='5%' align='center'>=</td>
    <td>
     Permintaan naik[$X] 
     
     Persediaan banyak[$Y]
    </td>
   </tr>
   <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td align='center'>=</td>
    <td>
     Min ( $naik , $banyak )
    </td>
   </tr>
   <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td align='center'>=</td>
    <td>"; $P3 = Min($naik,$banyak); echo "$P3</td>
   </tr>
  </table>
  <table width='98%' border='1'>
   <tr>
    <td width='5%'>&nbsp;</td>
   
    <td width='10%' NoWrap>
     predikat4
    </td>
    <td width='5%' align='center'>=</td>
    <td>
     Persediaan naik[$X] 
     Persediaan Sedikit[$Y]
    </td>
   </tr>
   <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td align='center'>=</td>
    <td>
     Min ( $naik , $sedikit )
    </td>
   </tr>
   <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td align='center'>=</td>
    <td>"; $P4 = Min($naik,$sedikit); echo "$P4</td>
   </tr>
  </table>

 <table>
   <tr>
    <td>Nilai keanggotaan Terkecil &nbsp;</td>
    <td>&nbsp;</td>
    <td>=</td>
    <td>"; $MIN = Min($P1,$P2,$P3,$P4); echo "$MIN</td>
   </tr>
 
   <tr>
    <td>Nilai keanggotaan Terbesar </td>
    <td>&nbsp;</td>
    
    <td>=</td>
    <td>"; $MAX = MAX($P1,$P2,$P3,$P4); echo "$MAX</td>
   </tr>
   <tr>
    <td>Nilai a1 </td>
    <td>&nbsp;</td>
    
    <td>=</td>
    <td>"; $a1 = ( $C2 + ( $MIN * ( $C1 - $C2 ))); echo "$a1</td>
   </tr>
   <tr>
    <td>Nilai a2 </td>
    <td>&nbsp;</td>
    
    <td>=</td>
    <td>"; $a2 = ( $C2 + ( $MAX * ( $C1 - $C2 ))); echo "$a2</td>
   </tr>
 
     <tr>
        <td>Nilai A1 </td>
       <td>&nbsp;</td>
       <td>Moment 1 = </td>
       
       <td>"; $M1 = ($MIN/2*($a1*$a1)) ; echo " $M1</td>
       
     </tr>
     <tr>
      <td>Nilai A2 </td>
      <td>&nbsp;</td>
      <td >Moment 2 = </td>"; 
      $Ma=(($a2*$a2*$a2)/(3*($C1 - $C2)));
      $Mb=(($a2*$a2)*($C1 - $C2)/(2*($C1 - $C2)));
      $Mc = ($Ma-$Mb) ;
      $Md=(($a1*$a1*$a1)/(3*($C1 - $C2)));
      $Me=(($a1*$a1)*($C1 - $C2)/(2*($C1 - $C2)));
      $Mf = ($Md-$Me) ;
      $M2 = ($Mc-$Mf) ; echo "<td> $M2</td>
          
    </tr>
    <tr>
      <td >Nilai A3 </td>
      <td>&nbsp;</td>
      <td >Moment 3 = </td>
     <td>"; $M3 = (($MAX/2*($C1*$C1)) - ($MAX/2*($a2*$a2))); echo " $M3</td>
    </tr>
    </table>
    <table>
    <tr>
       <td>Luas 1 = <td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>"; $L1 = (($MIN)* ($a1)) ; echo "$L1</td>
    </tr>
    <tr>
       <td>Luas 2 = <td>
       <td>&nbsp;</td>";
      $La=(($a2*$a2)/(2*($C1 - $C2)));
      $Lb=(($C1 - $C2)/($C1 - $C2)*$a2);
      $Lc = ($La-$Lb) ;
      $Ld=(($a1*$a1)/(2*($C1 - $C2)));
      $Le=(($C1 - $C2)/($C1 - $C2)*$a1);
      $Lf = ($Ld-$Le);
      $L2 = ($Lc-$Lf) ; 
      echo "<td>$L2</td>
   </tr>
   <tr>
       <td>Luas 3 = <td>
       <td>&nbsp;</td>
       <td>&nbsp;</td>
       <td>";$L3 = (($MAX*($C1)) - ($MAX*($a2))); echo " $L3</td></tr>
    </tr>
    </table>
    <br>
    <table>
    <tr><td>Z1</td><td>";$Z1 = (($M1+$M2+$M3)); echo " $Z1</td></tr>
    <tr><td>Z2</td><td>";$Z2 = (($L1+$L2+$L3)); echo " $Z2</td></tr>
    <tr><td>Z total = <td>
    <td>";$Z3 = ($Z1/$Z2); echo " $Z3</td></tr>
    </table>";
}
 
  ?> 
 
</body>
</html>