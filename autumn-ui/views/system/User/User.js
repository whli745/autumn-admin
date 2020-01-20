$(function () {
	//定义搜索区域字段
	let searchItems = [
		{
			id: "textSearchLoginName",
			field: "loginName",
			title: "用户名"
		},
		{
			id: "textSearchTradeNumber",
			field: "tradeNumber",
			title: "工牌号"
		},
		{
			id: "textSearchUserName",
			field: "userName",
			title: "姓名"
		},
		{
			id: "comboSearchEnabled",
			field: "enabled",
			title: "启用",
            combo:{
                url: "system/dict/listByCode",
                params: {
                    code: "YES_NO"
                },
                optionId: "code",
                optionText: "name"
            }
		},
	];

	//初始化搜索区域
	Autumn.initSearch({
		id: "search",
        searchItems: searchItems,
        pasteTable: "tb_data" //关联表格id
	});

	//初始化表格
	Autumn.initTable({
        buttonItems:[
			{
                id: "btn_add",
                css: "btn green",
                iconCss: "fa fa-plus",
                title: "新增",
                onClick: function () {
                    showDialog();
                }
            },
			{
				id: "btn_edit",
				css: "btn blue",
                iconCss: "fa fa-edit",
				title: "编辑",
                options:[
                    {
                        id: "tb_data",
                        selectNum: 1
                    }
                ],
				onClick: function () {
					let selections = $("#tb_data").bootstrapTable("getSelections");
                    if (selections.length !== 1){
                        Autumn.error("请选择一条需要修改的数据");
                        return;
                    }
					showDialog('edit');
				}
			},
			{
				id: "btn_delete",
				css: "btn red",
                iconCss: "fa fa-times",
				title: "删除",
                options:[
                    {
                        id: "tb_data",
                        selectMinNum: 1
                    }
                ],
				onClick: function () {
					let selections = $("#tb_data").bootstrapTable("getSelections");
                    if (selections.length === 0){
                        Autumn.error("请选择需要删除的数据");
                        return;
                    }
					let ids = [];
					$.each(selections, function (i, item) {
						ids.push(item.id);
					});

					Autumn.confirm("是否确认删除数据？", function(){
						$.when(Autumn.customAjax({
							url: "system/user/deleteBatchByPK", 
							params: ids
						})).done(function(res){
							if(res){
								$("#tb_data").bootstrapTable("refresh");
							}
						});
					});
				}
			},
			{
				id: "btn_enabled",
				css: "btn yellow",
                iconCss: "fa fa-unlock",
				title: "启用",
                options:[
                    {
                        id: "tb_data",
                        selectMinNum: 1
                    }
                ],
				onClick: function () {
					let selections = $("#tb_data").bootstrapTable("getSelections");
                    if (selections.length === 0){
                        Autumn.error("请选择需要启用的数据");
                        return;
                    }
					let ids = [];
					$.each(selections, function (i, item) {
						ids.push(item.id);
					});

                    $.when(Autumn.customAjax({
                        url: "system/user/updateEnabledByPks",
                        params: {
                        	ids: ids,
							enabled: '1'
                        }
                    })).done(function(res){
                        if(res){
                            $("#tb_data").bootstrapTable("refresh");
                        }
                    });
				}
			},
			{
				id: "btn_disabled",
				css: "btn default",
                iconCss: "fa fa-lock",
				title: "禁用",
                options:[
                    {
                        id: "tb_data",
                        selectMinNum: 1
                    }
                ],
				onClick: function () {
					let selections = $("#tb_data").bootstrapTable("getSelections");
                    if (selections.length === 0){
                        Autumn.error("请选择需要禁用的数据");
                        return;
                    }
					let ids = [];
					$.each(selections, function (i, item) {
						ids.push(item.id);
					});

					$.when(Autumn.customAjax({
						url: "system/user/updateEnabledByPks",
						params: {
							ids: ids,
							enabled: '0'
						}
					})).done(function(res){
						if(res){
							$("#tb_data").bootstrapTable("refresh");
						}
					});
				}
			},
			{
				id: "btn_password",
				css: "btn btn-warning",
                iconCss: "fa fa-refresh",
				title: "重置密码",
                options:[
                    {
                        id: "tb_data",
                        selectMinNum: 1
                    }
                ],
				onClick: function () {
					let selections = $("#tb_data").bootstrapTable("getSelections");
                    if (selections.length === 0){
                        Autumn.error("请选择需要重置密码的数据");
                        return;
                    }
					let ids = [];
					$.each(selections, function (i, item) {
						ids.push(item.id);
					});

					$.when(Autumn.customAjax({
						url: "system/user/resetPassword",
						params: ids
					})).done(function(res){
						if(res){
							$("#tb_data").bootstrapTable("refresh");
						}
					});
				}
			}
		],
		id: "tb_data",
		url: "system/user/listUserByPage",
		mainSearch: searchItems,
		params: function(){
        	let depart = $.fn.zTree.getZTreeObj('depart');
        	if (depart){
                let nodes = depart.getCheckedNodes(true);
                if (nodes.length > 0){
                    return {departId: nodes[0].id};
                }
			}
        	return {};
		},
		columns: [
			{
                checkbox: true
			},
			{
				field: "loginName",
				title: "用户名"
			},
			{
				field: "tradeNumber",
				title: "工牌号"
			},
			{
				field: "userName",
				title: "姓名"
			},
			{
				field: "email",
				title: "邮箱"
			},
			{
				field: "phone",
				title: "电话"
			},
			{
				field: "photo",
				title: "用户头像"
			},
			{
				field: "superAdmin",
				title: "超级管理员",
                formatter: function (val,row,index) {
                    if (val == 1){
                        return '<span class="label label-success">是</span>';
                    }
                    return '<span class="label label-danger">否</span>';
                }
			},
			{
				field: "enabled",
				title: "启用",
				formatter: function (val,row,index) {
					if (val == 1){
                        return '<span class="label label-success">是</span>';
					}
                    return '<span class="label label-danger">否</span>';
                }
			},
			{
				field: "remark",
				title: "备注信息"
			},
		]
	});

	Autumn.initZTree("#depart","system/depart/listAll",{companyId: sessionStorage.getItem("loginCompanyId")},{
        check: {
            chkStyle: "radio",
            radioType: "all"
		},
		callback: {
            onCheck: function(event, treeId, treeNode) {
                $("#tb_data").bootstrapTable("refresh");
            }
		}
	},function (obj) {
		obj.expandAll(true);
    });
});

function showDialog(change){
	let defaultTable = change === "edit" ? "tb_data" : null;
    Autumn.initDialog({
        buttonItems:[
            {
                id: "btn_apply",
                css: "btn green",
                title: "确认",
                pasteForm: 'editForm',
                onClick: function (data) {
                    let url = "system/user/saveUser";
                    if (defaultTable){
                        url = "system/user/updateByPK";
                    }
                    $.when(Autumn.customAjax({
                        url: url,
                        params: data
                    })).done(function (res) {
                        if (res){
                            $("#tb_data").bootstrapTable("refresh");
                            $("#myModal").modal("hide");
                        }
                    })
                }
            }
        ],
        id: 'myModal',
        title: defaultTable ? '编辑' : '新增',
        form: {
            id: 'editForm',
            pasteTable: defaultTable,
            formItems:[
                {
                    id: "hideId",
                    field: "id"
                },
                {
                    id: "hideEnabled",
                    field: "enabled",
                    defaultValue: "1"
                },
				{
					id: "textLoginName",
					field: "loginName",
					title: "用户名",
					disable: change === 'edit',
					valid: {
						required: true,
						remote: {
							url: apiUrl+"system/user/validate",
							type: "post",
							data: {
								id: $('#hideId').val()
							}
						}
					}
				},
				{
					id: "textTradeNumber",
					field: "tradeNumber",
					title: "工牌号"
				},
				{
					id: "textUserName",
					field: "userName",
					title: "姓名"
				},
				{
					id: "textEmail",
					field: "email",
					title: "邮箱",
					valid: {
						remote: {
							url: apiUrl+"system/user/validate",
							type: "post",
							data: {
								id: $('#hideId').val()
							}
						}
					}
				},
				{
					id: "textPhone",
					field: "phone",
					title: "电话",
					valid: {
						remote: {
							url: apiUrl+"system/user/validate",
							type: "post",
							data: {
								id: $('#hideId').val()
							}
						}
					}
				},
				{
					id: "textPhoto",
					field: "photo",
					title: "用户头像"
				},
				{
					id: "switchSuperAdmin",
					field: "superAdmin",
					title: "超级管理员",
                    defaultValue: '0'
				},
                {
                    id: "comboDepartId",
                    field: "departId",
                    title: "部门",
                    tree: true,
                    combo:{
                        url: "system/depart/listAll",
                        params: {
                            companyId: sessionStorage.getItem("loginCompanyId"),
							enabled: '1'
                        },
                        optionId: "id",
                        optionText: "name"
                    }
                },
                {
                    id: "areaRemark",
                    field: "remark",
                    title: "备注信息",
					colspan: 10
                }
            ]
        }
    })
}


