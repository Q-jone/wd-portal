////////////////////////////////////////////
// 换肤
// 有style1_xx.css和style-xx.css，默认使用style-xx.css，
// 如要使用另一个，在引用本js前定义style_name_prefix='style1_'
if (style_name_prefix == undefined)
	var style_name_prefix = 'style-';
var skin_cookie_key = 'stpt.skin';

/*
 * 设置换肤样式
 * 改用从数据库读取并保存在session
 * cookie中继续保存，用于stoa
 */
function setSkin(skin) {
	var tmp = window.confirm('变换样式会刷新页面并转向到首页，确定要变换样式吗？');
	if (tmp) {
		addCookie(skin_cookie_key, skin, 365);
		document.location.href = 'updatecss.action?css=' + skin;
	}
}

/*
 * 应用到页面
 */
function getSkin() {
	var skin_cookie_val = getCookie(skin_cookie_key);
	//alert(skin_cookie_val);
	if (skin_cookie_val == '') skin_cookie_val = 'blue';
	
	var tmp = window.location.pathname.match(/\//g);
	var n = 0;
	if (tmp != null) n = tmp.length;
	
	var pref = '';
	for (var i=1; i<n; i++) {
		pref += '../';
	}

	var css_link = '<link href="'+ pref +'css/desktop/' + style_name_prefix + skin_cookie_val +'.css" rel="stylesheet" type="text/css">';
	document.write(css_link);
}

//getSkin();
////////////////////////////////////////////