<?php
$A1  = isset($_POST['A1'])?$_POST['A1']:NULL;
   $A2  = isset($_POST['A2'])?$_POST['A2']:NULL;
   $B1  = isset($_POST['B1'])?$_POST['B1']:NULL;
   $B2  = isset($_POST['B2'])?$_POST['B2']:NULL;
   $C1  = isset($_POST['C1'])?$_POST['C1']:NULL;
   $C2  = isset($_POST['C2'])?$_POST['C2']:NULL;
   $X  = isset($_POST['X'])?$_POST['X']:NULL;
   $Y  = isset($_POST['Y'])?$_POST['Y']:NULL;
   $Q = isset($_POST['Q'])?$_POST['Q']:NULL;
   $D1 = isset($_POST['D1'])?$_POST['D1']:NULL;
   $D2 = isset($_POST['D2'])?$_POST['D2']:NULL;
   ?>

   <html>
<head>
<title>Fuzzy Mamdani Hitung Networth</title>
</head>
<body>
	<form action='' method='post'>
    <table width="427" border="0">
  <tr>
    <td width="205">&nbsp;Maksimal gold from hero (kill) @ 10 min
      <input type='text' class='masukan' name='A1' value='<?=isset($A1)?$A1:'500';?>'></td>
       <td width="206">&nbsp;Minimal gold from hero (kill) @ 10 min
      <input type='text' class='masukan' name='A2' value='<?=isset($A2)?$A2:'0';?>'></td>
  </tr>
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
     <input type='text' class='masukan' name='Q' value='<?=isset($Q)?$Q:'0';?>'></td>
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
     <td>Kill from hero @ 10 min</td>
     <td align='center'>$A1</td>
     <td align='center'>$A2</td>
    </tr>
    <tr>
     <td NoWrap>Last hit @ 10 min</td>
     <td align='center'>$B1</td>
     <td align='center'>$B2</td>
    </tr>
    <tr>
     <td NoWrap>Bounty rune @ 10 min</td>
     <td align='center'>$D1</td>
     <td align='center'>$D2</td>
    </tr>
    <tr>
     <td NoWrap>Networth</td>
     <td align='center'>$C1</td>
     <td align='center'>$C2</td>
    </tr>
    <tr>
     <td>Nilai x gold from hero @ 10 min</td>
     <td colspan='2' align='center'>$X</td>
    </tr>
    <tr>
     <td>Nilai y last hit @ 10 min</td>
     <td colspan='2' align='center'>$Y</td>
    </tr>
     <tr>
     <td>Nilai Q bounty rune @ 10 min</td>
     <td colspan='2' align='center'>$Q</td>
    </tr>

   </table>";
   
   
     if ( $X > 300 AND $X < 500){
     	$banyak1=round(($X - 300) /( 500 -300),3);
      }else{
  	
  	$banyak1=0;
     }
      
     
     if ( $X < 250 AND $X > 100){
     	$sedang1=round(($X - 100) /( 250 -100),3);
     }elseif ($X < 400 AND $X > 250){
      $sedang1=round((400 - $X) /( 400-250 ),3);	
  }else
  
  {
  	
  	$sedang1=0;
     } 
     if ( $X > 0 AND $X < 150){
     	$sedikit1=round((150 - $X) /( 150 - 0),3);
      }else{
  	
  	$sedikit1=0;
     }

     if ( $Y > 30 AND $Y < 60){
     	$banyak2=round(($Y - 30) /( 60 -30),3);
      }else{
  	
  	$banyak2=0;
     }
      
     
     if ( $Y < 30 AND $Y > 10){
     	$sedang2=round(($Y - 10) /( 30 -10),3);
     }elseif ($Y < 50 AND $Y > 30){
      $sedang2=round((50 - $Y) /( 50-30 ),3);	
  }else
  
  {
  	
  	$sedang2=0;
     } 
     if ( $Y > 0 AND $Y < 20){
     	$sedikit2=round((20 - $Y) /( 20 - 0),3);
      }else{
  	
  	$sedikit2=0;
     }

     if ( $Q > 300 AND $Q < 600){
     	$banyak3=round(($Q - 300) /( 600 -300),3);
      }else{
  	
  	$banyak3=0;
     }
      
     
     if ( $Q < 300 AND $Q > 100){
     	$sedang3=round(($Q - 100) /( 300 -100),3);
     }elseif ($Q < 500 AND $Q > 300){
      $sedang3=round((500 - $Q) /( 500-300 ),3);	
  }else
  
  {
  	
  	$sedang3=0;
     } 
     if ( $Q > 0 AND $Q < 200){
     	$sedikit3=round((200 - $Q) /( 200 - 0),3);
      }else{
  	
  	$sedikit3=0;
     }
 
    
    

    $P1=Min($sedikit1,$sedikit2,$sedikit3);
    $a1=$P1*10000+10000;
    $P2=Min($sedikit1,$sedikit2,$sedang3);
    $a2=$P2*10000+10000;
    $P3=Min($sedikit1,$sedikit2,$banyak3);
    $a3=$P3*10000+10000;
    $P4=Min($sedang1,$sedikit2,$sedikit3);
    $a4=15000-$P4*10.000;
    $P5=Min($banyak1,$sedang2,$sedikit3);
    $a5=15000-$P5*10.000;
    $P6=Min($banyak1,$banyak2,$banyak2);
    $a6=$P6*35000+15000;
    $P7=Min($sedang1,$banyak2,$banyak2);
    $a7=$P7*35000+15000;
    $P8=Min($sedikit1,$banyak2,$sedang3);
    $a8=$P8*35000+15000;
    $P9=Min($sedikit1,$banyak2,$banyak2);
    $a9=$P9*35000+15000;
    $P10=Min($sedang1,$sedang2,$sedang3);
    $a10=$P10*35000+15000;
    $P11=Min($banyak3,$banyak3,$sedang3);
    $a11=$P11*35000+15000;
    $P12=Min($banyak1,$sedikit2,$sedikit3);
    $a12=$P12*10000+10000;
    $P13=Min($banyak1,$sedikit2,$banyak3);
    $a13=15000-$P13*10.000;
    $P14=Min($sedikit1,$banyak2,$sedikit3);
    $a14=15000-$P14*10.000;
    $P15=Min($sedang1,$banyak2,$sedikit3);
    $a15=$P15*35000+15000;
    $P16=Min($sedang1,$sedang2,$sedang3);
    $a16=15000-$P16*10.000;
    $P17=Min($sedang1,$sedang2,$sedikit3);
    $a17=15000-$P17*10.000;
    $P18=Min($sedang1,$sedang2,$banyak3); 
    $a18=15000-$P18*10.000;

    //

    

    //
    echo"
    
    <table>
    <tr><td>Z1</td><td>";$Z1 = (($P1*$a1+$P2*$a2+$P3*$a3+$P4*$a4+$P5*$a5+$P6*$a6+$P7*$a7+$P8*$a8+$P9*$a9+$P10*$a10+$P11*$a11+$P12*$a12+$P13*$a13+$P14*$a14+$P15*$a15+$P16*$a16+$P17*$a17*$P18*$a18)); echo " $Z1</td></tr>
    <tr><td>Z2</td><td>";$Z2 = (($P1+$P2+$P3+$P4+$P5+$P6+$P7+$P8+$P9+$P10+$P11+$P12+$P13+$P14+$P15+$P16+$P17+$P18)); echo " $Z2</td></tr>
    <tr><td>Z total = <td>
    <td>";$Z3 = ($Z1/$Z2); echo " $Z3</td></tr>";

 

}
?>
