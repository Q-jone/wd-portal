$(function(){
	$("#printButton").click(function(){
		window.open("print.jsp");
	});
})

function getContent(){
	var htmlContent = $("#mytable thead").html();
	$(".clk:checked").parent("td").parent("tr").each(function(i,obj){
		htmlContent += '<tr  class="list-td1" height="22" >';
		htmlContent += $(obj).html();
		htmlContent += "</tr>";
	})
	//alert(htmlContent);
	return htmlContent;
}
function getTitle(){
	return $("#printTitle").val();
}
