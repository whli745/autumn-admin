$(function () {
	//定义搜索区域字段
	let searchItems = [
		{
			id: "textSearchCode",
			field: "code",
			title: "部门编码"
		},
		{
			id: "textSearchName",
			field: "name",
			title: "部门名称"
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
							url: "system/depart/deleteBatchByPK", 
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
		url: "system/depart/listAll",
		page: false,
		mainSearch: searchItems,
		treeView:{
        	enable: true
		},
		params: function () {
			return {
                parentId: "0",
                companyId: sessionStorage.getItem("loginCompanyId")
			};
        },
		columns: [
			{
                checkbox: true
			},
            {
                field: "name",
                title: "部门名称"
            },
			{
				field: "code",
				title: "部门编码"
			},

			{
				field: "sort",
				title: "排序"
			},
			{
				field: "enabled",
				title: "启用",
                formatter: function (val, row, index) {
                    if (val == 1) {
                        return '<span class="label label-success">是</span>';
                    }
                    return '<span class="label label-danger">否</span>';
                }
			},
			{
				field: "remark",
				title: "备注"
			}
		]
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
                    let url = "system/depart/save";
                    if (defaultTable){
                        url = "system/depart/updateByPK";
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
					id: "comboParentId",
					field: "parentId",
					title: "上级部门",
					tree: true,
                    combo:{
                        url: "system/depart/listAll",
                        params: {
                            enable: "1"
                        },
                        optionId: "id",
                        optionText: "name"
                    }
				},
				{
					id: "textCode",
					field: "code",
					title: "部门编码",
					disable: change === 'edit',
                    valid: {
						required: true,
						remote: {
							url: apiUrl+"system/depart/validate",
							type: "post",
							data: {
								id: $('#hideId').val(),
								parentId: $("#comboParentId").val() ? $("#comboParentId").val() : '0',
								companyId: $("#hideCompanyId").val()
							}
						}
					}
				},
				{
					id: "textName",
					field: "name",
					title: "部门名称",
                    valid: 'required'
				},
				{
					id: "textSort",
					field: "sort",
					title: "排序",
                    valid: {
						required: true,
						remote: {
							url: apiUrl+"system/depart/validate",
							type: "post",
							data: {
								id: $('#hideId').val(),
								parentId: function(){
                                    return $('input[name="parentId"]').val() || 0;
                                },
								companyId: $("#hideCompanyId").val()
							}
						}
					}
				},
				{
					id: "switchEnabled",
					field: "enabled",
					title: "启用",
                    defaultValue: '1'
				},
				{
					id: "textRemark",
					field: "remark",
					title: "备注"
				}
            ]
        }
    })
}
