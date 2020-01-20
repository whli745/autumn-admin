const context = window.document.location.origin;
//验证登录权限
$(function () {
    const pathName = window.document.location.pathname;
    if (pathName.indexOf("login.html") == -1) {
        const token = sessionStorage.getItem('token');
        if (!token) {
            window.open(context+"/views/login.html","_top");
        }
    }
});

/**
 * 日志格式化
 * */
Date.prototype.format = function (fmt) {
    const o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (let k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};

//解析字典为JSON数据
let dicts = JSON.parse(localStorage.getItem("dict"));

let Autumn = function () {

    /*========================================== 表格  ============================================================*/
    //定义Bootstrap数据表格
    let initTable = function(options){

        let selector = $("#"+options.id);

        //初始化按钮
        if (options.buttonItems) {
            let toolBar = $('<div id="toolBar_' + options.id + '" class="btn-group"></div>');
            toolBar.appendTo(selector.parent());

            //获取用户关联的按钮
            let buttons = getButtonByUser();

            //过滤掉无效的定义按钮
            if(buttons && buttons.length > 0){
                options.buttonItems = options.buttonItems.filter(function(item){
                    return item.id && $.inArray(item.id,buttons) > -1;
                })
            }

            $.each(options.buttonItems, function (i, item) {
                initButton('#toolBar_' + options.id, item);
            });
        }

        if (options.url.indexOf("http") === -1 &&  options.url.indexOf("https") === -1) {
            options.url = apiUrl + options.url;
        }

        options.treeView = options.treeView || {};

        //初始化表格
        selector.bootstrapTable({
            url: options.url || "", //请求后台的URL（*）
            method: 'post', //请求方式（*）
            toolbar: options.buttonItems ? "#toolBar_" + options.id : "", //工具按钮用哪个容器
            striped: true, //是否显示行间隔色
            cache: false, //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            height:options.height || window.innerHeight-$("#search").innerHeight(),
            sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
            queryParamsType: '',
            pageNumber: 1, //初始化加载第一页，默认第一页
            pageSize: 10, //每页的记录行数（*）
            pageList: [10, 20, 30, 50, 100,200, 500,1000], //可供选择的每页的行数（*）
            showToggle: false, //是否显示详细视图和列表视图的切换按钮
            clickToSelect: true, //是否启用点击选中行
            singleSelect: options.singleSelect || false,
            uniqueId: "id", //每一行的唯一标识，一般为主键列
            undefinedText: '',
            showRefresh: options.showRefresh || false, //是否显示刷新按钮
            pagination: options.page !== false, //是否显示分页（*）
            queryParams: function (params) {
                //搜索框查询条件解析
                if (options.mainSearch) {
                    params = $.extend(params,parseObject(options.mainSearch, "field", "id"));
                }

                if (typeof options.params === "function"){
                    params = $.extend(params,options.params());
                }
                return params;
            }, //传递参数（*）
            treeView: options.treeView.enable || false , //treeView视图
            treeField: options.treeView.fieldKey || "name", //treeView视图字段
            treeId: options.treeView.idKey || "id",
            treeCollapseAll: options.treeView.collapseAll || false, //是否全部展开
            columns: options.columns,
            onCheck: function (row, $element) {
                if (options.buttonItems) {
                    initButtonStatus(options.buttonItems);
                }
                if(typeof options.onCheck === "function" ){
                    options.onCheck(row,$element);
                }
            },
            onUncheck: function (row, $element) {
                if (options.buttonItems) {
                    initButtonStatus(options.buttonItems);
                }
                if(typeof options.onUncheck === "function" ){
                    options.onUncheck(row,$element);
                }
            },
            onLoadSuccess: function (data) {
                if (options.buttonItems) {
                    initButtonStatus(options.buttonItems);
                }
                if(typeof options.onLoadSuccess === "function" ){
                    options.onLoadSuccess(data);
                }
            },
            onClickRow: function (row, $element, field) {
                if(typeof options.onClickRow === "function" ){
                    options.onClickRow(row,$element,field);
                }
            },
            responseHandler: function (res) {
                return {
                    total: res && res.data ? res.count : 0, //总页数,前面的key必须为"total"
                    rows: res && res.data ? res.data : [] //行数据，前面的key要与之前设置的dataField的值一致.
                };
            }
        });

    };

    //初始化按钮
    let initButton = function (selector, options, formItems) {

        let iconCss = "";
        if (options.iconCss){
            iconCss = '<li class="'+options.iconCss+'"></li>';
        }

        let button = $('<button id="' + options.id + '" type="button" class="' + options.css + '">' + iconCss + options.title + '</button>');
        $(selector).append(button);
        button.on("click", function () {

            if (typeof options.onClick == "function") {
                if (options.pasteForm) { //提交表单
                    //手动验证表单
                    if ($("#" + options.pasteForm).valid()) {
                        options.onClick(parseObject(formItems, 'field', 'id'))
                    }
                }else{
                    options.onClick();
                }
            }
        });
    };

    //初始化按钮状态
    let initButtonStatus = function (buttonItems) {
        $.each(buttonItems, function (index, item) {
            let flag = true;
            if (item.options) {
                $.each(item.options, function (i, param) {
                    if (typeof param == 'boolean') {
                        flag = flag && param;
                    } else if (param.selectNum) {
                        flag = flag && ($('#' + param.id).bootstrapTable('getSelections').length == param.selectNum);
                    } else if (param.selectMinNum) {
                        flag = flag && ($('#' + param.id).bootstrapTable('getSelections').length >= param.selectMinNum);
                    } else if (param.textId) {
                        flag = flag && ($('#' + param.textId).val() != '');
                    } else if(param.isEdit){
                        let selects = $('#' + param.id).bootstrapTable('getSelections');
                        $.each(selects,function(index2,temp){
                            if(temp.edit != true && temp.edit != 1){
                                flag = flag && false;
                                return;
                            }
                        });
                    }
                    $('#' + item.id).attr('disabled', !flag);
                })
            }
        });
    };

    //依据用户查询授权的按钮
    let getButtonByUser = function () {
        let result;
        let url = window.document.location.pathname;
        $.when(customAjax(
            {
                url: "system/menu/listButtonByUserAndCompanyAndParentUrl",
                async: false,
                params:{
                    href: url.substring(url.lastIndexOf("/") + 1),
                    userId: sessionStorage.getItem("loginId"),
                    companyId: sessionStorage.getItem("loginCompanyId")
                }
            }
        )).done(function (res) {
            result = res.map(function (item) {
                return item.href;
            });
        });

        return result;
    };

    /*=========================================== 搜索框 =========================================================*/

    let initSearch = function (options) {
        let panel = $("#"+options.id);

        if (!options.searchItems || options.searchItems.length <= 0) {
            return;
        }

        if (options.title) {
            panel.append('<div class="panel-heading">' + options.title + '</div>');
        }

        let panelBody = $('<div class="panel-body"></div>');
        panelBody.appendTo(panel);
        let form = $('<form autocomplete="off" class="form-horizontal" role="form" id="noFrom"></form>')
        form.appendTo(panelBody);

        let i = 0;
        let rules = {};
        let fromGroup;

        //组合搜索条件
        $.each(options.searchItems, function (m, item) {
            
            if (i % 4 === 0) {
                fromGroup = $('<div class="form-group" id="fromGroup"></div>');
                fromGroup.appendTo(form);
            }
            i++;

            //封装表单组件
            initInput(item, fromGroup, null, 2);

            //表单组件验证规则
            if (item.valid) {
                rules[item.field] = item.valid;
            }
        });

        //赋予表单验证规则
        if (Object.keys(rules).length > 0){
            initValid("#noFrom", rules);
        }

        let btnFromGroup = $('<div class="form-group" id="fromGroupBtn"></div>');
        btnFromGroup.append('<div class="col-xs-12 text-right">' +
            '<button type="button" id="btn_search" class="btn btn-sm btn-primary">查询</button>' +
            '<button type="button" id="btn_reset" class="btn btn-sm btn-light">重置</button></div>');
        btnFromGroup.appendTo(form);

        $("#btn_search").on("click", function () {
            if (Object.keys(rules).length > 0) {
                //手动验证表单
                if ($("#noFrom").valid()) {
                    $("#" + options.pasteTable).bootstrapTable("refresh");
                }
            } else {
                $("#" + options.pasteTable).bootstrapTable("refresh");
            }
        });

        $("#btn_reset").on("click", function () {
            $('#noFrom')[0].reset();
            $("#" + options.pasteTable).bootstrapTable("refresh");
        });
    };


    /*================================================ 弹框 ====================================================*/

    let initDialog = function (options) {

        let container = $("#" + options.id);

        container.empty();

        //定义弹出框
        let modalDialog = $('<div class="modal-dialog" style="width:800px;"></div>');
        modalDialog.appendTo(container);

        //定义弹出框内容
        let modalContent = $('<div class="modal-content"></div>');
        modalContent.appendTo(modalDialog);

        //定义弹出框头部
        if (options.title) {
            let modalHeader = $('<div class="modal-header">' +
                '<h4 class="modal-title">' + options.title + '</h4></div>');
            modalHeader.appendTo(modalContent);
        }

        //定义弹出窗主体
        let modalBody = $('<div class="modal-body"></div>');
        modalBody.appendTo(modalContent);

        //定义表单
        let formItems = {};
        if (options.form) {
            initForm(".modal-body", options.form);
            formItems = options.form.formItems;
        }


        let modalFooter = $('<div class="modal-footer"></div>');
        modalFooter.appendTo(modalContent);
        if (options.buttonItems) {
            $.each(options.buttonItems, function (index, item) {
                initButton(".modal-footer", item, formItems)
            });
        }

        //取消按钮
        let btnCancel = $('<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>');
        btnCancel.appendTo(modalFooter);
        //显示
        container.modal("show");

    };

    /*================================================= 表单 ===================================================*/

    let initForm = function (selector, options) {
        let container = $(selector);
        //定义表单
        let form = $('<form autocomplete="off" class="form-horizontal" role="form" id="' + options.id + '"></form>');
        if(options.multipart){
            form.attr("enctype","multipart/form-data");
        }
        form.appendTo(container);

        //获取表格选中的行
        let rows = {};
        if (options.pasteTable) {
            rows = $("#" + options.pasteTable).bootstrapTable("getSelections")[0];
        }

        //表单验证条件
        let rules = {};
        let num = 0;
        let formGroup;
        //创建表单组件
        $.each(options.formItems, function (m, item) {

            if (item.id.indexOf('hide') > -1){
                let defaultValue = item.defaultValue ? item.defaultValue : '';
                defaultValue = Object.keys(rows).length > 0 ? rows[item.field] : defaultValue;
                let input = $('<input type="hidden" autocomplete="off" id="' + item.id + '" name="' + item.field + '" value="'+defaultValue+'" />')
                input.appendTo(form);
            } else {
                if (num % 2 === 0) {
                    formGroup = $('<div class="form-group"></div>');
                    formGroup.appendTo(form);
                }
                num ++;
                let colspan = item.colspan || 4;
                //封装组件
                initInput(item, formGroup, rows,colspan,2);
            }

            //表单组件验证规则
            if (item.valid) {
                rules[item.field] = item.valid;
            }

        });
        //赋予表单验证规则
        initValid("#"+options.id, rules);
    };

    //自定义生成表单控件
    let initInput = function (item, formGroup, formData, colspan,lablespan) {

        let require = '';

        lablespan = lablespan || 1;

        if (item.valid) {
            let type = typeof item.valid; //判断验证规则类型
            if (type == "string") {
                if (item.valid == "required") {
                    require = '<span style="color:red;">*</span>';
                }
            } else {
                for (let key in item.valid) {
                    if (key == "required") {
                        require = '<span style="color:red;">*</span>';
                        break;
                    }
                }
            }
        }

        //定义输入控件标题
        let label = $('<label for="' + item.id + '" class="control-label col-xs-'+lablespan+'">' + item.title + require + '</label>');
        label.appendTo(formGroup);

        let div = $('<div class="col-xs-' + colspan + '"></div>');
        div.appendTo(formGroup)

        //定义控件默认值
        let defaultValue = item.defaultValue;

        if (formData && Object.keys(formData).length > 0) {
            defaultValue = formData[item.field];
        }

        let input;
        //根据不同类型定义控件
        if (item.id.indexOf('text') > -1) {
            input = $('<input autocomplete="off" type="text" class="form-control" id="' + item.id + '" name="' + item.field + '"/>');
            input.attr("value", defaultValue);
        } else if (item.id.indexOf('area') > -1) {
            input = $('<textarea class="form-control" style="resize: none;" id="' + item.id + '" name="' + item.field +
                '"></textarea>');
            input.html(defaultValue);
        } else if (item.id.indexOf('combo') > -1) {

            if (item.tree){
                input = $('<input autocomplete="off" type="text" class="form-control" id="' + item.id + '" name="' + item.field + '" placeholder="请选择..." textLabel="jasontext" readonly/>');
                input.attr('checks',defaultValue);

                let checkStyle = '';

                if(item.combo.multiple){
                    checkStyle = 'checkbox';
                }

                if (item.combo.data) {
                    initTreeSelect("#"+item.id, item.combo.data,checkStyle, item.combo.onChange);
                }else{
                    let comboData = item.combo.params || {};
                    $.when(customAjax({
                        url: item.combo.url,
                        params: comboData
                    })).done(function (res) {
                        initTreeSelect("#"+item.id, res,checkStyle, item.combo.onChange);
                    });
                }

            }else{
                input = $('<select class="form-control" id="' + item.id + '" name="' + item.field +
                    '"></select>');

                item.combo = item.combo || {};

                if(item.combo.multiple){
                    input.attr("multiple",true);
                }
                if (item.combo.data) {
                    initSelect("#"+item.id, item.combo.data,item.combo.optionId, item.combo.optionText, defaultValue);
                }else{
                    let comboData = item.combo.params || {};
                    $.when(customAjax({
                        url: item.combo.url,
                        params: comboData
                    })).done(function (res) {
                        initSelect("#"+item.id, res,item.combo.optionId, item.combo.optionText, defaultValue);
                    });

                    //自定义选择框事件

                    input.on("change", function () {
                        if (typeof item.combo.onChange === "function" ) {
                            item.combo.onChange();
                        }
                    });
                }

            }

        } else if (item.id.indexOf('icon') > -1) {
            input = $('<button type="button" class="btn btn-white" id="' + item.id + '" name="' + item.field +
                '" role="iconpicker"></button>');

            input.iconpicker({
                iconset: 'fontawesome',
                selectedClass: 'btn-success',
                unselectedClass: '',
                icon: defaultValue
            });
        } else if (item.id.indexOf('switch') > -1) {
            input = $('<div class="switch"></div>');
            let target = $('<input autocomplete="off" type="checkbox" id="' + item.id + '" name="' + item.field + '"/>');
            target.attr("value", defaultValue);
            input.append(target);
            target.bootstrapSwitch({
                onText: "是",
                offText: "否",
                onColor: "success",
                offColor: "danger",
                size: "small",
                state: defaultValue && defaultValue != '0' ? true : false,
                onSwitchChange: function (event, state) {
                    if (state == true) {
                        target.attr("value", 1);
                    } else {
                        target.attr("value", 0);
                    }
                    if (typeof item.onChange == "function") {
                        item.onChange();
                    }
                }
            });
        }else if (item.id.indexOf('file') > -1) {
            input = $('<input autocomplete="off" type="file" class="form-control" id="' + item.id + '" name="' + item.field + '"/>');

            input.attr("value", defaultValue);

        }else if (item.id.indexOf('date') > -1){
            input = $('<input autocomplete="off" type="text" class="form-control" id="' + item.id + '" name="' + item.field + '" readonly/>');

            input.attr("value", defaultValue);

            input.datepicker({
                language: 'zh-CN',
                format: 'yyyy-mm-dd',
                autoclose: true,
                clearBtn: true,
                todayBtn: "linked",
                todayHighlight: true
            });

        }else if (item.id.indexOf('time') > -1){
            input = $('<input autocomplete="off" class="form-control" type="text" readonly id="' + item.id + '" name="' + item.field + '"/>');

            input.attr("value", defaultValue);

            input.datetimepicker({
                language: 'zh-CN',
                format: 'yyyy-mm-dd hh:ii:ss',
                weekStart: 1,
                todayBtn:  true,
                clearBtn: true,
                autoclose: true,
                todayHighlight: true,
                startView: 2,
                showMeridian: true
            });

        }else if(item.id.indexOf('search') > -1){
            input = $('<div class="input-group"></div>')
            let subInput = $('<input autocomplete="off" class="form-control" type="text" id="' + item.id + '" name="' + item.field + '"/>');
            subInput.attr("value", defaultValue);
            subInput.appendTo(input);
            let button = $('<a class="input-group-addon btn blue" href="#">搜索</a>');
            button.on('click',function(){
                if(typeof item.search == 'function'){
                    item.search();
                }
            });
            button.appendTo(input);
        }

        input.attr("disabled", item.disable || false);
        div.append(input);
    };

    let initSelect = function (selector, items, optionId, optionText, defaultValue) {

        let elem = $(selector);
        elem.empty();
        elem.append('<option value="">请选择...</option>');
        $.each(items, function (n, item) {

            let option = $('<option></option>');
            option.attr('value',item[optionId]);
            if(optionId === 'id'){
                option.text(item[optionText]);
            }else{
                option.text(item[optionId] + '-' + item[optionText]);
            }
            if (defaultValue && (defaultValue == item[optionId])) {
                option.attr("selected", "selected");
            }
            
            elem.append(option);
        });
    };

    let initTreeSelect = function (selector,data,chkStyle,onchange){
        let elem = $(selector);
        let defaults = {
            zNodes: data,
            height:233,
            chkStyle: chkStyle ? chkStyle : "radio",
            callback: {
                onCheck: function (treeNode) {
                    if (typeof onchange == 'function'){
                        onchange(treeNode);
                    }
                }
            },
            simpleData : {
                enable : true //是否之用简单数据
                ,idKey : 'id' //对应json数据中的ID
                ,pIdKey : 'parentId' //对应json数据中的父ID
                ,rootPId: 0
            }
        };
        elem.drawMultipleTree(defaults);
    };

    //解析对象
    let parseObject = function (array, filed, id) {
        let temp = {};
        array.forEach(function (item, index) {
            if (item[id].indexOf('radio') != -1) {
                temp[item[filed]] = $('input:radio[name="' + item[filed] + '"]:checked').val()
            } else if (item[id].indexOf('area') != -1) {
                temp[item[filed]] = $('#' + item[id]).val()
            } else if (item[id].indexOf('switch') != -1) {
                temp[item[filed]] = $('#' + item[id]).attr("value");
            } else if (item[id].indexOf('file') != -1) {
                temp[item[filed]] = new FormData($('#' + item[id] + '_form')[0])
            } else if (item[id].indexOf('combo') != -1) {
                if(item.tree){
                    temp[item[filed]] = $('input[name='+item[filed]+']').val();
                }else{
                    if ($('#' + item[id]).val() == '请选择' || $('#' + item[id]).val() == ' ' || $('#' + item[id]).val() == '') {
                        temp[item[filed]] = null;
                    } else {
                        temp[item[filed]] = $('#' + item[id]).val();
                    }
                }
            } else if (item[id].indexOf('icon') != -1) {
                temp[item[filed]] = $('#' + item[id] + ' input[name="' + item[filed] + '"]').val();
            } else if (item[id].indexOf('date') != -1) {
                if (item[id].toLowerCase().indexOf('sarttime') != -1){
                    temp[item[filed]] = $('#' + item[id]).val() + ' 00:00:00';
                }
                if (item[id].toLowerCase().indexOf('endtime') != -1){
                    temp[item[filed]] = $('#' + item[id]).val() + ' 23:59:59';
                }
            } else {
                temp[item[filed]] = $('#' + item[id]).val()
            }
        });
        return temp;
    };


    //定义表单验证规则
    let initValid = function (selector,rules) {

        $(selector).validate({
            errorElement: "i",
            errorPlacement: function (error, element) {
                $(element).tooltip({
                    title: $(error).text(),
                });
                $(element).tooltip('show');
            },
            success: function(label, element){
                $(element).tooltip('destroy');
            },
            highlight: function (element, errorClass, validClass) {
                $(element).parent("div").addClass("has-error").removeClass("has-success");
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).parent("div").addClass("has-success").removeClass("has-error");
            },
            rules: rules
        });

    };

    /* ====================================== 其它 =================================================================== */

    //自定义Ajax方法
    let customAjax = function (options) {
        let def = $.Deferred();

        if (options.url.indexOf("http") == -1 &&  options.url.indexOf("https") == -1) {
            options.url = apiUrl + options.url;
        }

        let contentType = options.contentType || "json";

        options.params = options.params || {};

        $.ajax({
            url: options.url,
            type: options.method || "post",
            contentType: "application/" + contentType + ";charset=UTF-8",
            data: contentType != 'json' ? options.params : JSON.stringify(options.params),
            dataType: "json",
            async: options.async === false ? false : true,
            success: function (result) {

				if(typeof result.code == "undefined"){
					def.resolve(result);
					return;
				}
				
				if(0 == result.code){
				    if (result.message) {
				        success(result.message);
				    }
				    def.resolve(result.data ? result.data : "true");
				    return;
				}
				
				if(-10002 == result.code || -10003 == result.code){
				    confirm(result.message,function(){
				        window.open(context+"/views/login.html","_top");
				    });
				    return;
				}
				
				if (0 != result.code){
				    if (result.message) {
				        error(result.message);
				    }
				    return;
				}
				
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                error("请求错误");
                return;
            }
        });
        return def.promise();
    };

    //初始化ZTree
    let initZTree = function(selecter,url,param,setting,callback){
        let options = {
            data: {
                simpleData : {
                    enable : true //是否之用简单数据
                    ,idKey : 'id' //对应json数据中的ID
                    ,pIdKey : 'parentId' //对应json数据中的父ID
                    ,rootPId: 0
                },
            },
            check: {
                enable: true
                ,chkStyle: "checkbox"
                ,chkboxType: {"Y":"", "N":"ps"}
            }
        };

        if (setting){
            options = $.extend(true,options,setting);
        }

        $.when(customAjax({
			url: url, 
			params: param
		})).done(function(res){
            if(res){
                let temp = $.fn.zTree.init($(selecter), options,res);
                if (typeof callback === "function"){
                    callback(temp);
                }
            }
        });
    };

    //自定义成功消息提示
    let success = function (message) {
        top.toastr.success(message);
    };

    //自定义错误消息提示
    let error = function (message) {
        top.toastr.error(message);
    };

    //自定义确认消息提示
    let confirm = function (message,callBack) {
        layer.confirm(message, {
                btn: ["确认", "取消"],
                icon: 0,
                title: "警告"
            },
            function () {
                if (typeof callBack === "function") {
                    callBack();
                }
                layer.closeAll();
            }
        );
    };

    //自定义上传函数
    let upload= function(url,data){

        if (url.indexOf("http") === -1 &&  url.indexOf("https") === -1) {
            url = apiUrl + url;
        }

        $.ajax({
            type: "POST",
            url: url,
            data: data,
            // 下面三个参数要指定，如果不指定，会报一个JQuery的错误
            cache: false,
            contentType: false,
            processData: false,
            async: false,
            success: function(result) {
                if(0 === result.code){
                    if (result.message) {
                        success(result.message);
                    }
                }

                if(-10003 === result.code || -10004 === result.code){
                    confirm(result.message,function(){
                        window.open("../../views/login.html","_top");
                    });
                }

                if (0 !== result.code){
                    if (result.message) {
                        error(result.message);
                    }
                }
                $("#myModal").modal("hide");
                $("#tb_data").bootstrapTable("refresh");
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                error("请求错误");
                return;
            }
        });
    };

    //定义回调函数
    return {
         initSearch: initSearch
        ,initTable: initTable
        ,initDialog: initDialog
        ,initZTree: initZTree
        ,initSelect: initSelect
		,initTreeSelect: initTreeSelect
        ,customAjax: customAjax
        ,success: success
        ,error: error
        ,confirm: confirm
        ,upload: upload
    };

}();