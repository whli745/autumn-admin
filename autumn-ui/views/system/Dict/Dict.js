$(function () {
	//定义搜索区域字段
	let searchItems = [
		{
			id: "textSearchCode",
			field: "code",
			title: "数据值"
		},
		{
			id: "textSearchName",
			field: "name",
			title: "标签名"
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
                    },
					{
						isEdit: true,
						id: 'tb_data'
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
                    },
                    {
                        isEdit: true,
                        id: 'tb_data'
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
							url: "system/dict/deleteBatchByPK", 
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
		url: "system/dict/listByPage",
        page: false,
		mainSearch: searchItems,
        params: function () {
        	return {parentId: "0"};
        },
        treeView: {
            enable: true
        },
		columns: [
			{
                checkbox: true
			},
            {
                field: "name",
                title: "标签名"
            },
			{
				field: "code",
				title: "数据值"
			},
			{
				field: "sort",
				title: "排序"
			},
			{
				field: "edit",
				title: "可编辑",
                formatter: function (val, row, index) {
                    if (val == 1) {
                        return '<span class="label label-success">是</span>';
                    }
                    return '<span class="label label-danger">否</span>';
                }
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
				title: "备注信息"
			},
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
                    let url = "system/dict/save";
                    if (defaultTable){
                        url = "system/dict/updateByPK";
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
					id: "comboParentId",
					field: "parentId",
					title: "父级字典",
                    tree: true,
                    combo:{
                        url: "system/dict/listAll",
                        params: {
                            enabled: "1"
                        },
                        optionId: "id",
                        optionText: "name"
                    }
				},
				{
					id: "textCode",
					field: "code",
					title: "数据值",
                    valid: 'required',
					disable: change === 'edit',
					valid: {
						required: true,
						remote: {
							url: apiUrl+"system/dict/validate",
							type: "post",
							data: {
								id: $('#hideId').val(),
								parentId: function(){
                                    return $('input[name="parentId"]').val() || 0;
                                }
							}
						}
					}
				},
				{
					id: "textName",
					field: "name",
					title: "标签名",
                    valid: 'required'
				},
				{
					id: "textSort",
					field: "sort",
					title: "排序",
                    valid: {
						required: true,
						remote: {
							url: apiUrl+"system/dict/validate",
							type: "post",
							data: {
								id: $('#hideId').val(),
								parentId: $("#comboParentId").val() ? $("#comboParentId").val() : '0',
							}
						}
					}
				},
				{
					id: "switchEdit",
					field: "edit",
					title: "可编辑",
					defaultValue: '1',
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
					title: "备注信息",
                    colspan: 10
				},
            ]
        }
    })
}
