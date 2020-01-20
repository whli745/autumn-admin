$(function () {
	//定义搜索区域字段
	let searchItems = [
		{
			id: "textSearchCode",
			field: "code",
			title: "角色名称"
		},
		{
			id: "textSearchName",
			field: "name",
			title: "中文名称"
		},
		{
			id: "comboSearchEnabled",
			field: "enabled",
			title: "是否启用",
			combo: {
                url: "system/dict/listByCode",
                params: {
                    code: "YES_NO"
                },
                optionId: "code",
                optionText: "name"
			}
		}
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
							url: "system/role/deleteBatchByPK", 
							params: ids
						})).done(function(res){
							if(res){
								$("#tb_data").bootstrapTable("refresh");
                                $("#myModal").modal("hide");
							}
						});
					});
				}
			}
		],
		id: "tb_data",
		url: "system/role/listByPage",
		mainSearch: searchItems,
        params: function () {
            return {companyId: sessionStorage.getItem("loginCompanyId")};
        },
		columns: [
			{
                checkbox: true
			},
			{
				field: "code",
				title: "角色名称"
			},
			{
				field: "name",
				title: "中文名称"
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
            }
		],
        onCheck: function (row,$element) {
            $('#user').bootstrapTable('refresh');

            $.when(Autumn.customAjax({
                url: 'system/roleMenu/listByRole',
                params: {
                    roleId: row.id
                }
            })).done(function (res) {
                if (res) {
                    let menuTree = $.fn.zTree.getZTreeObj('menu');
                    $.each(res,function (i,item) {
                        let node = menuTree.getNodeByParam("id",item);
                        menuTree.checkNode(node,true);
                        menuTree.updateNode(node);
                    })
                }
            });
        },
        onUncheck: function (row,$element) {
            $('#user').bootstrapTable('refresh');

            let menuTree = $.fn.zTree.getZTreeObj('menu');
            menuTree.checkAllNodes(false);
        }
	});

	//初始化用户表
    Autumn.initTable({
        id: "user",
        url: "system/userRole/listUserByRole",
        params: function () {
            let selections = $('#tb_data').bootstrapTable('getSelections');
            return {roleId: selections.length > 0 ? selections[0].id : ''}
        },
        columns: [
            {
                checkbox: true,
                field: 'checked'
            },
            {
                field: "loginName",
                title: "用户名"
            },
            {
                field: "userName",
                title: "姓名"
            }
        ],
        onCheck: function (row,$element) {
            let selections = $('#tb_data').bootstrapTable('getSelections');
            if (selections.length != 1){
                Autumn.error("请选择角色");
                return;
            }

            $.when(Autumn.customAjax({
                url: 'system/userRole/createOrDeleteUserRole',
                params: {
                    urId: row.urId,
                    roleId: selections[0].id,
                    id: row.id
                }
            })).done(function (res) {
                if (res) {
                    $("#user").bootstrapTable("refresh");
                }
            });
        },
        onUncheck: function (row,$element) {
            let selections = $('#tb_data').bootstrapTable('getSelections');
            if (selections.length != 1){
                Autumn.error("请选择角色");
                return;
            }

            $.when(Autumn.customAjax({
                url: 'system/userRole/createOrDeleteUserRole',
                params: {
                    urId: row.urId,
                    roleId: selections[0].id,
                    id: row.id
                }
            })).done(function (res) {
                if (res) {
                    $("#user").bootstrapTable("refresh");
                }
            });
        }
    });

    //初始化菜单树
    Autumn.initZTree("#menu","system/menu/listAll",{enabled: '1'},null,function (obj) {
        obj.expandAll(true);
    });

    //去除用户表例全选按钮
    $("#user").find('input[name="btSelectAll"]').remove();

    //菜单授权
    $("#grant").on('click',function () {
        let selections = $('#tb_data').bootstrapTable('getSelections');
        if (selections.length != 1){
            Autumn.error("请选择角色");
            return;
        }
        let menuTree = $.fn.zTree.getZTreeObj('menu');
        let nodes = menuTree.getCheckedNodes(true);
        if (nodes.length == 0){
            Autumn.error("请选择至少一条菜单");
            return;
        }
        let ids = [];
        $.each(nodes,function (i,node) {
            ids.push(node.id);
        });

        $.when(Autumn.customAjax({
            url: 'system/roleMenu/grantMenu',
            params: {
                roleId: selections[0].id,
                menuIds: ids
            }
        }));
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
                    let url = "system/role/save";
                    if (defaultTable){
                        url = "system/role/updateByPK";
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
                    id: "hideCompanyId",
                    field: "companyId",
                    defaultValue: sessionStorage.getItem("loginCompanyId")
                },
				{
					id: "textCode",
					field: "code",
					title: "角色名称",
                    disable: change === 'edit',
                    valid: {
						required: true,
						remote: {
							url: apiUrl+"system/role/validate",
							type: "post",
							data: {
								id: $('#hideId').val(),
								parentId: $("#hideCompanyId").val(),
							}
						}
					}
				},
				{
					id: "textName",
					field: "name",
					title: "中文名称"
				},
				{
					id: "switchEnabled",
					field: "enabled",
					title: "启用",
					defaultValue: '1'
				},
                {
                    id: "areaRemark",
                    field: "remark",
                    title: "备注信息"
                }
            ]
        }
    })
}

