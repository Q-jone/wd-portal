<script src="js/jquery-1.7.1.js"></script>
<script>
	if($(parent.document).find("frameset[id=main]").length>0){
		if ($(parent.parent.document).find("frameset[id=main]").attr("cols") == "210,*") {
			$(parent.parent.document).find("frameset[id=main]").attr("cols","7,*");
			$(parent.parent.frames["leftFrame"].document).find(".demo").hide();
			$(parent.parent.frames["leftFrame"].document).find("#arrow").removeClass();
			$(parent.parent.frames["leftFrame"].document).find("#arrow").addClass("open_2");
			$("#show").attr("src","/portal/css/default/images/sideBar_arrow_right.jpg");
			$("#show").attr("title","展开");
		}else{
			$(parent.parent.document).find("frameset[id=main]").attr("cols","210,*");
			$(parent.parent.frames["leftFrame"].document).find(".demo").show();
			$(parent.parent.frames["leftFrame"].document).find("#arrow").removeClass();
			$(parent.parent.frames["leftFrame"].document).find("#arrow").addClass("close_2");
			$("#show").attr("src","/portal/css/default/images/sideBar_arrow_left.jpg");
			$("#show").attr("title","收起");
		}
	}
</script>