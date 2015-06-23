// JavaScript Document
$(function(){
	function tabs(tabTit, on, tabCon) {
		$(tabCon).each(function() {
			$(this).children().eq(0).show();
		});
		$(tabTit).each(function() {
			$(this).children().eq(0).addClass(on);
		});
		$(tabTit).children().click(function() { 
			$(this).addClass(on).siblings().removeClass(on);
			var index = $(tabTit).children().index(this);
			$(tabCon).children().eq(index).show().siblings().hide();
		});
	}
	//tab code
	
	$(".table_body tr:even").addClass("even");        
    $(".table_body tr").hover(
    function(){
        $(this).addClass("over");
    },
    function(){
        $(this).removeClass("over");
    });
	
	
	//tab code2
	
	$(".R_c_body tr:even").addClass("even");        
    $(".R_c_body tr").hover(
    function(){
        $(this).addClass("over");
    },
    function(){
        $(this).removeClass("over");
    });
	
});


