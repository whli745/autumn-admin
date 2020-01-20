$(function () {
	//定义搜索区域字段
	let searchItems = [
		{
			id: "textSearchSequenceId",
			field: "sequenceId",
			title: "序列号规则"
		},
		{
			id: "textSearchCode",
			field: "code",
			title: "条码规则号"
		},
		{
			id: "textSearchName",
			field: "name",
			title: "条码规则"
		},
		{
			id: "comboSearchEnabled",
			field: "enabled",
			title: "启用",
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
							url: "system/barcode/deleteBatchByPK", 
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
		url: "system/barcode/listBarcodeByPage",
		mainSearch: searchItems,
		columns: [
			{
                checkbox: true
			},
			{
				field: "code",
				title: "条码标识"
			},
			{
				field: "name",
				title: "条码名称"
			},
			{
				field: "sequenceName",
				title: "条码规则"
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
                    let url = "system/barcode/save";
                    if (defaultTable){
                        url = "system/barcode/updateByPK";
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
                    id: "textCode",
                    field: "code",
                    title: "条码标识",
					disable: change === 'edit',
					valid: {
						required: true,
						remote: {
							url: apiUrl+"system/barcode/validate",
							type: "post",
							data: {
								id: $('#hideId').val()
							}
						}
					}
                },
				{
					id: "comboSequenceId",
					field: "sequenceId",
					title: "条码规则",
                    valid: 'required',
                    combo: {
                        url: "system/sequence/listAll",
                        params: {
                            enabled: "1"
                        },
                        optionId: "id",
                        optionText: "name"
                    }
				},
				{
					id: "textName",
					field: "name",
					title: "条码名称",
                    valid: 'required'
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
