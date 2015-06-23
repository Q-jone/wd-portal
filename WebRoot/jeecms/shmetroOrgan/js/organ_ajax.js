//显示头版头条新闻图片
var basePath = "";
function setbasePath(path) {
    basePath = path;
}
function setShortTitle(shortTitle, Title) {
    if (shortTitle == "null") {
        return shortTitle;
    } else {
        return Title;
    }
}
//head cms
//头条新闻
function getHeadNews(channelID) {
    $.ajax({
        url: basePath + "/jeecms/getJeecmsInfo.action?random=" + Math.random(),
        type: "post",
        dataType: 'json',
        cache: false,
        data: {
            method: "JEECMS.SEARCH_CONTENT_LIST",
            channelId: channelID,
            hasTitleImg: "0,1",
            typeId: '1,2,3,4',
            rownum: '5'
        },
        error: function () {
            alert("系统连接失败，请稍后再试！");
        },
        success: function (obj) {
            if (obj) {
                $.each(obj, function (entryIndex, entry) {
                    $("#newsCenter hgroup a").attr("href", basePath + "/jeecms/findByPage.action?channelId=" + channelID);
                    //$("#newsCenter hgroup a").attr("href",basePath+"/jeecms/findByPage.action?channelId="+channelID);
                    $("#newsCenter section a").attr("href", "../contentDetail.jsp?content=" + entry.contentId);
                    $("#newsCenter section a h5").html(entry.title);
                    //$("#newsCenter section a h5").attr("title",obj[i].copyTitle);
                    $("#newsCenter section a p").html(entry.description);
                    //$("#newsCenter section a p").attr("title",obj[i].copyMemo);
                    $("#newsCenter section a").attr("title", entry.description);
                });
            }
        }
    });
}

//xxgl cms
function getXXGLNews(channelID) {
    $.ajax({
        url: basePath + "/jeecms/getJeecmsInfo.action?random=" + Math.random(),
        type: "post",
        dataType: 'json',
        cache: false,
        data: {
            method: "JEECMS.SEARCH_CONTENT_LIST",
            channelId: channelID,
            hasTitleImg: "0,1",
            typeId: '1,2,3,4',
            rownum: '6'
        },
        error: function () {
            alert("系统连接失败，请稍后再试！");
        },
        success: function (obj) {

            var newsLi = "";
            if (obj) {
                $.each(obj, function (entryIndex, entry) {
                    newsLi += "<li><a target='_blank' title='" + setShortTitle(entry.shortTitle, entry.title) + "' href='../contentDetail.jsp?content=" + entry.contentId + "'>" + entry.title + "</a><span>" + entry.releaseDate.substring(5, 10) + "</span></li>";
                });
                $("#newsCenter ul").html(newsLi);
            }
        }
    });

}

function getXWTTPicNews(cmsUrl, channelID) {
    $.ajax({
        url: basePath + "/jeecms/getJeecmsInfo.action?random=" + Math.random(),
        type: "post",
        dataType: 'json',
        cache: false,
        data: {
            method: "JEECMS.SEARCH_CONTENT_LIST",
            channelId: channelID,
            hasTitleImg: '1',
            typeId: '3',
            rownum: '5'
        },
        error: function () {
            alert("系统连接失败，请稍后再试!");
        },
        success: function (obj) {
            var playList = "";
            var playText = "";
            var playInfo = "";
            if (obj != null && obj != "undefined") {
                $.each(obj, function (entryIndex, entry) {
                    playList += "<li><a target='_blank' title='" + setShortTitle(entry.shortTitle, entry.title) + "' href='" + "../contentDetail.jsp?content=" + entry.contentId + "'>" +
                            "<img src='" + cmsUrl + entry.titleImg.replace("/jeecms/u/", "/u/") + "' title='" + entry.title + "'></img></a></li>";
                    playInfo += "<li><a target='_blank' title='" + setShortTitle(entry.shortTitle, entry.title) + "' href='" + "../contentDetail.jsp?content=" + entry.contentId + "'>"
                    + entry.title + "</a></li>";

                    playText += "<li>" + (entryIndex + 1) + "</li>";
                });
                $("#play_list").html(playList);
                $("#play_info").html(playInfo);
                $("#play_text").html(playText);
                $("#play_list li").hide();
                $("#play_info li").hide();
                $("#play_list li").eq(0).show();
                $("#play_info li").eq(0).show();
                $("#play_text li").eq(0).addClass("current");
            }
        }
    });
}

//cms
function getLatestNews(channelID, pos) {
    $.ajax({
        url: basePath + "/jeecms/getJeecmsInfo.action?random=" + Math.random(),
        type: "post",
        dataType: 'json',
        cache: false,
        data: {
            method: "JEECMS.SEARCH_CONTENT_LIST",
            channelId: channelID,
            hasTitleImg: "0,1",
            typeId: '1,2,3,4',
            rownum: '5'
        },
        error: function () {
            alert("系统连接失败，请稍后再试！");
        },
        success: function (obj) {
            var newsLi = "";
            var newsP = "javascript:void(0)";
            if (obj) {
                $.each(obj, function (entryIndex, entry) {
                    newsLi += "<li class='clearfix'><a target='_blank' style=\"width:85%;\"  title='" + setShortTitle(entry.shortTitle, entry.title) + "'";
                    newsLi += "  href='../contentDetail.jsp?content=" + entry.contentId + "'>" + entry.title + "</a><span style=\"width:15%;\">"+entry.releaseDate+"</span></li>";
                });

                newsP = basePath + "/jeecms/findByPage.action?channelId=" + channelID;

                $(".reportDiv:eq(" + pos + ") ul").html(newsLi);
                $(".reportDiv:eq(" + pos + ") p").html("<a target='_self' class='more_3' href='" + newsP + "'>更多</a>");
                if (obj == null) {
                    $(".reportDiv:eq(" + pos + ") ul").html("&nbsp;&nbsp;无相关信息。");
                    $(".reportDiv:eq(" + pos + ") p a").hide();
                }
            }

        }
    });
}

function getDYFCPicNews(cmsUrl, channelID) {
    $.ajax({
        url: basePath + "/jeecms/getJeecmsInfo.action?random=" + Math.random(),
        type: "post",
        dataType: 'json',
        cache: false,
        data: {
            method: "JEECMS.GET_CONTENT_LIST",
            channelId: channelID,
            hasTitleImg: "1",
            typeId: '3',
            rownum: '1'
        },
        error: function () {
            alert("系统连接失败，请稍后再试!");
        },
        success: function (obj) {
            var playList = "";
            var playText = "";
            var playInfo = "";
            if (obj != null && obj != "undefined") {
                $.each(obj, function (entryIndex, entry) {
                    playList += "<li><a target='_blank' title='" + setShortTitle(entry.shortTitle, entry.title) + "' href='" + "../contentDetail.jsp?content=" + entry.contentId + "'>" +
                            "<img id='img' src='" + cmsUrl + entry.titleImg.replace("/jeecms/u/", "/u/") + "' title='" + entry.title + "'></img></a></li>";
                    playInfo += "<li><a target='_blank' title='" + setShortTitle(entry.shortTitle, entry.title) + "' href='" + "../contentDetail.jsp?content=" + entry.contentId + "'>"
                    + entry.title + "</a></li>";

                    playText += "<li>" + (entryIndex + 1) + "</li>";
                    $("#fengcai hgroup a").attr("href", basePath + "/jeecms/findByPage.action?channelId=" + channelID);
                });

                $("#list").html(playList);
                $("#info").html(playInfo);
                $("#text").html(playText);
                $("#list li").hide();
                $("#info li").hide();
                $("#list li").eq(0).show();
                $("#info li").eq(0).show();
                $("#text li").eq(0).addClass("current");
            }
        }
    });
}


//自动显示
function showAuto() {
    var listTmp = $("#play_list li").filter(function () {
        return $(this).css("display") != "none";
    });
    var infoTmp = $("#play_info li").filter(function () {
        return $(this).css("display") != "none";
    });
    var textTmp = $("#play_text li.current");
    // alert(listTmp.html());
    $("#play_list li").hide();
    $("#play_info li").hide();
    $("#play_text li").removeClass("current");
    if (listTmp.next().length == 0) {
        $("#play_list li").eq(0).show();
        $("#play_info li").eq(0).show();
        $("#play_text li").eq(0).addClass("current");
    } else {
        listTmp.fadeOut(500).next().fadeIn(1000);
        infoTmp.fadeOut(500).next().fadeIn(1000);
        textTmp.next().addClass("current");
    }
}