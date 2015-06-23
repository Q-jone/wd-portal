(function ($) {
    var contextPath = "";
    $.extend({
        initContextPath: function (ctx) {
            contextPath = ctx;
        },
        loadMatchThemes: function (param, callback, responseType) {
            var settings = initSettings({url: contextPath + "/theme/themes/", type: "GET", dataType: responseType, data: param, success: callback});
            $.ajax(settings);
        },
        deleteAttachment: function (id, callback, responseType) {
            var settings = initSettings({url: contextPath + "/attachment/" + id, type: "DELETE", dataType: responseType, success: callback});
            $.ajax(settings);
        }, loadDepartment: function (param, callback, responseType) {
            //http://10.1.48.101:8080/workflow/send-organTree/getChildNodes.action
            if(param != "")
            {
                param += "&typeId=-1&id=2500";
            }else{
                param = "typeId=-1&id=2500";
            }

            var settings = initSettings({url: contextPath + "/unionOrgan/getDepartments.action", data: param, type: "GET", dataType: responseType, success: callback});
            $.ajax(settings);

        }, loadEmployee: function (id, callback, responseType) {
            var settings = initSettings({url: contextPath + "/organization/" + id + "/employee", type: "GET", dataType: responseType, success: callback});
            $.ajax(settings);

        }, loadLeader: function (id, callback, responseType) {
            var settings = initSettings({url: contextPath + "/organization/" + id + "/leader", type: "GET", dataType: responseType, success: callback});
            $.ajax(settings);

        }, saveDepartment: function (matchId, param, callback, responseType) {
            var settings = initSettings({url: contextPath + "/match/" + matchId + "/prize/applicant", data: param, type: "POST", dataType: responseType, success: callback});
            $.ajax(settings);
        }, assignOperator: function (param, callback, responseType) {
            var settings = initSettings({url: contextPath + "/match/" + param.matchId + "/operator", data: "operatorId=" + param.operatorId + "&operator=" + param.operator, type: "PUT", dataType: responseType, success: callback});
            $.ajax(settings);
        }, loadApplicantDepartment: function (param, callback, responseType) {
            var settings = initSettings({url: contextPath + "/unionPrize/getApplyDepartments.action", type: "GET", dataType: responseType, success: callback});
            $.ajax(settings);
        }, dealFlow: function (param, callback, responseType) {
            var settings = initSettings({url: contextPath + "/flowworkThread/thread", data: param, type: "PUT", dataType: responseType, success: callback});
            $.ajax(settings);
        }, loadMatch: function (id, callback, responseType) {
            var settings = initSettings({url: contextPath + "/match/" + id + "/object", type: "GET", dataType: responseType, success: callback});
            $.ajax(settings);
        }
    });

    $.fn.extend({
        wrapMatchThemeSelect: function (settings) {
            var $target = $(this);
            $.loadMatchThemes("", function (data) {
                _wrapSelect($target, data, settings);
            });
        }, wrapMatchThemeAutoCompleted: function (settings) {
            var $target = $(this);
            settings = $.extend({label: "label", value: "value", root: "list", renderItem: ""}, settings);
            _wrapAutoCompleted($target, settings, function (request, response) {
                $.loadMatchThemes(settings.label + "=" + request.term + "&removed=0", function (data) {
                    response(data[settings.root]);
                });
            });
        },
        wrapDepartmentSelect:function(settings){
            var $target = $(this);
            $.loadDepartment("", function (data) {
                _wrapSelect($target, data, settings);
            });
        }
        , wrapDepartmentTree: function (settings) {
            var $target = $(this);
            $.loadDepartment("", function (data) {
                settings = $.extend({selectedMulti: false}, settings);
                $.fn.zTree.init($target, {check: {enable: settings.selectedMulti}, view: {selectedMulti: settings.selectedMulti}, data: {simpleData: {enable: true}}}, data).expandAll(true);
                var treeObj = $.fn.zTree.getZTreeObj($target.attr("id"));
                var nodes = treeObj.transformToArray(treeObj.getNodes());
                $target.trigger("initCompleted", [nodes]);

            });
        }, wrapLeaderTree: function (settings) {
            var $target = $(this);
            $.loadLeader(settings.deptId, function (data) {
                settings = $.extend({selectedMulti: false}, settings);
                $.fn.zTree.init($target, {check: {enable: settings.selectedMulti}, view: {selectedMulti: settings.selectedMulti}}, data.hashMap).expandAll(true);
            });
        }, wrapEmployeeTree: function (settings) {
            var $target = $(this);
            $.loadEmployee(settings.deptId, function (data) {
                settings = $.extend({selectedMulti: false}, settings);
                $.fn.zTree.init($target, {check: {enable: settings.selectedMulti}, view: {selectedMulti: settings.selectedMulti}}, data.hashMap).expandAll(true);
                var treeObj = $.fn.zTree.getZTreeObj($target.attr("id"));
                var nodes = treeObj.transformToArray(treeObj.getNodes());
                $target.trigger("initCompleted", [nodes]);
            });
        }, wrapDepartEmployeeTree: function (settings) {
            var $target = $(this);
            $.loadDepartment("", function (data) {
                settings = $.extend({selectedMulti: false}, settings);
                $.fn.zTree.init($target, {callback:{beforeCheck :function(treeId, treeNode) {if (treeNode.isParent) {alert("请选择叶子节点");return false;}}},check: {enable: settings.selectedMulti,chkStyle: "radio",radioType: "level"}, view: {selectedMulti: settings.selectedMulti}, data: {simpleData: {enable: true}},async: {enable: true,url: contextPath + "/unionOrgan/getEmployees.action",autoParam: ["id=deptId"]}}, data);
                var treeObj = $.fn.zTree.getZTreeObj($target.attr("id"));
                var nodes = treeObj.transformToArray(treeObj.getNodes());
                $target.trigger("initCompleted", [nodes]);

            });
        }, placeholder: function(){
        	var supportPlaceholder = 'placeholder'in document.createElement('input')
        	if(supportPlaceholder){
        		return;
        	}
        	
        	var input = $(this);
            var text = input.attr('placeholder'),
            defaultValue = input.defaultValue;
            
            if(!defaultValue){
              input.val(text).css("color","#999");
            }
         
            input.focus(function(){
              if(input.val() == text){
                $(this).val("");
              }
            });
          
            input.blur(function(){
              if(input.val() == ""){
                $(this).val(text).css("color","#999");
              }
            });
         
            //输入的字符不为灰色
            input.keydown(function(){
              $(this).css("color","#000");
            });
          }
    })

    function _wrapAutoCompleted(target, settings, callback) {

        target.autocomplete({
            source: callback,
            minLength: 2,
            select: function (event, ui) {
                target.val(ui.item[settings.label]);
                return false;
            },
            open: function () {
                $(this).removeClass("ui-corner-all").addClass("ui-corner-top");
            },
            close: function () {
                $(this).removeClass("ui-corner-top").addClass("ui-corner-all");
            }
        }).data("autocomplete")._renderItem = function (ul, item) {
            var html = "";
            if (settings.renderItem)
                html = settings.renderItem(ul, item);
            else {
                html = "<a>" + item[settings.label] + "</a>";
            }
            return $("<li>").data("item.autocomplete", item)
                .append(html)
                .appendTo(ul);
        };
    }


    function _wrapSelect(target, json, settings) {

        settings = $.extend({defaultLabel: "请选择", defaultValue: "", label: "label", value: "value", root: "list", selectVal: ""}, settings);

        var list = getJsonValByProperty(json, settings.root);

        target.find("option").remove();
        var html = '<option value=' + settings.defaultValue + '>' + settings.defaultLabel + '</option>';
        $.each(list, function (i, o) {
            var label = getJsonValByProperty(o, settings.label);
            var value = getJsonValByProperty(o, settings.value);

            html += '<option value="' + value + '">' + label + '</option>';
        });
        target.html(html);
        target.find("option[value=" + settings.selectVal + "]").attr("selected", "selected");
        target.trigger("completed");
    }

    function getJsonValByProperty(json, prop) {
        if(prop.indexOf(".") != -1){
            var props = prop.split(".");
            for(var i = 0 ; i<props.length; i++){
                json = getJsonValByProperty(json,props[i]);
            }
            return json;
        }else{
            for (var key in json) {
                if (key == prop)
                    return json[key];
            }
        }
    }

    function initSettings(settings) {
        settings.dataType = settings.dataType || "json";
        if (settings.data && settings.data.length > 0)
            settings.data = "format=" + settings.dataType + "&" + settings.data;
        else
            settings.data = "format=json";

        if (settings.type == "DELETE") {
            settings.type = "POST";
            settings.data += "&_method=delete";
        }
        else if (settings.type == "PUT") {
            settings.data += "&_method=put";
            settings.type = "POST";
        }

        return settings;
    }

})(jQuery);