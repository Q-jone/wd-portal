<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>上海申通地铁集团</title>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
<script type="text/javascript" src="js/skin.js"></script>
<link rel="stylesheet" href="css/style-blue.css" />
<SCRIPT LANGUAGE="JavaScript">
var background_am = '../images/desktop/images/26.jpg';		// 上午
var background_pm = '../images/desktop/images/25.jpg';		// 下午
var background_full = '../images/desktop/images/27.jpg';	// 全天

function setEventDateBackground(objfrm, k) {
	try {
		if (objfrm.document.location.href.trim() != "about:blank") {
			var yearStr = CLD.SY[CLD.SY.selectedIndex].value;
			var monthStr = parseInt(CLD.SM[CLD.SM.selectedIndex].value) + 1 < 10 ?
				'0' + (parseInt(CLD.SM[CLD.SM.selectedIndex].value) + 1) : parseInt(CLD.SM[CLD.SM.selectedIndex].value) + 1;
			eval('var bg = background_' + k + ';');
			eval('var obj = document.frames["dateManagerFrame"].document.getElementById("lst_' + k + '");');
			if (obj != undefined) {
				var txt = obj.innerHTML.trim();
				var arrDt = txt.split(',');
				if (arrDt != null && arrDt.length > 0) {
					for (var i=0; i<42; i++) {
						var objsd = document.getElementById('SD' + i);
						var objgd = document.getElementById('GD' + i);
						var obj_sd_txt = objsd.innerText.trim();
						var this_date = yearStr + '-' + monthStr + '-' + 
								(parseInt(obj_sd_txt) < 10 ? '0' + obj_sd_txt : obj_sd_txt);
						if (obj_sd_txt != '') {
							for (var j=0; j<arrDt.length; j++) {
								if (arrDt[j].trim() == this_date) {
									objgd.background = bg;
								}
							}
						}
					}
				}
			}
		}
	} catch (e) {}
}

function myPushBtm(K){
	pushBtm(K);
	getManagerDays();
}

function getManagerDays() {
	yearStr = CLD.SY[CLD.SY.selectedIndex].value;
    monthStr = parseInt(CLD.SM[CLD.SM.selectedIndex].value) + 1;

	for (var i=0; i<42; i++) {
		var objsd = document.getElementById('SD' + i);
		if (objsd.innerText.trim() != '') {
			objsd.innerHTML = '<a class="meeting" href="http://10.1.44.18/listDateManagerInfo.action?year='+yearStr+'&month='+monthStr+'&day='+objsd.innerText.trim()+'" target="_blank">'+ objsd.innerText.trim() +'</a>';
		}
	}

	//alert(myUrl);
	//document.frames["dateManagerFrame"].location.replace(myUrl);
}

function myChangeCld(){
	changeCld();
	getManagerDays();
}

/*function myPushBtm(K){
	pushBtm(K);
	getManagerDays();
}*/

window.onload = function() {
	myPushBtm('');
};
</SCRIPT>
</head>

<body>
<form name="CLD"  method="post" action="">
<table border="0" cellspacing="0" cellpadding="0">
<!-- 
<tr>
  <td class="calendar_title"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
	  <td align="right" height="20"><a href="#0" class="weather" title="新日程" onClick="">&nbsp;&nbsp;&nbsp;&nbsp;</a></td>
	</tr>
  </table></td>
</tr>
 -->

<tr>
  <td class="calendar_bg"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
	  <td class="calendar_td"><table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr class="calendar_title1">
		  <!--<td width="5" align="center"><img src="../images/desktop/triangle_left.jpg" width="4" height="7" onClick="myPushBtm('MU')"></td>-->
		  <td align="center"><select name="SY" style="font-size:9pt;width:55px;" onChange="myChangeCld()">
<script type="text/javascript">
for (var i=2000; i<2020; i++) {
	document.write('<option value="'+ i +'">'+ i +'</option>');
}
</script>
		  </select>年
		  <select name="SM" style="font-size:9pt;width:40px;" onChange="myChangeCld()">
<script type="text/javascript">
for (var i=1; i<13; i++) {
	document.write('<option value="'+ (i-1) +'">'+ i +'</option>');
}
</script>
		  </select>
		  月</td>
		  <!--<td width="5" align="center"><img src="../images/desktop/triangle_right.jpg" width="4" height="7" onClick="myPushBtm('MD')"></td>-->
		  <td align="center"><a href="javascript:myPushBtm()" class="meeting">本月</a></td>
		</tr>
	  </table>
		<table width="182" height="127" border="0" align="center" cellpadding="0" cellspacing="0">
		  <tr class="calendar_title2">
			<td width="26" height="12">日</td>
			<td width="26">一</td>
			<td width="26">二 </td>
			<td width="26">三</td>
			<td width="26">四</td>
			<td width="26">五</td>
			<td width="26">六</td>
		  </tr>
<script type="text/javascript">
var cldHTML = "";
for (var i=0; i<6; i++) {
	cldHTML += '<tr>';
	for (var j=0; j<7; j++) {
		n = i * 7 + j;
		cldHTML += '<td height="23"><table border="0" width="100%" height="100%" id="GD'+ n +'" border="0" cellpadding="0" cellspacing="0" align="center" onmouseover="mOvr(\''+ n +'\', 0)"><tr><td id="SD'+ n +'" class="calendar_td3" style="cursor:hand;text-align:center;verticle-align:middle;"></td></tr></table></td>';
	}
	cldHTML += '</tr>';
}
document.write(cldHTML);
</script>
		</table></td>
	</tr>
	<!--<tr>
	  <td class="calendar_td2" id="detail"><font id="detail" color="#000000" style="font-size:9pt;"></font></td>
	</tr>-->
  </table></td>
</tr>
</table>
</form>
<iframe name="dateManagerFrame" width=0 height=0 ></iframe>
<script type="text/javascript">
<!--
//myPushBtm('');
//-->
</script>
</body>
</html>
