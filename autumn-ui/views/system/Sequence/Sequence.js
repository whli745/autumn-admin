$(function () {
	//定义搜索区域字段
	let searchItems = [
		{
			id: "textSearchCode",
			field: "code",
			title: "规则编码"
		},
		{
			id: "textSearchName",
			field: "name",
			title: "规则名称"
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



					Autumn.confirm("是否确认删除数据？", function(){
                        $.each(selections, function (i, item) {

							$.when(Autumn.customAjax({
                                url: "system/barcode/listAll",
                                params: {
                                	sequenceId: item.id
								}
							})).done(function (res) {

								if (res && res.length > 0){
									Autumn.error('【'+item.name+'】已被绑定，无法删除');
									return;
								}

                                $.when(Autumn.customAjax({
                                    url: "system/sequence/deleteByPK",
                                    params: {
                                    	id: item.id
									}
                                })).done(function(res){
                                    if(res){
                                        $("#tb_data").bootstrapTable("refresh");
                                        $("#myModal").modal("hide");
                                    }
                                });
                            });

                        });
					});
				}
			}
		],
		id: "tb_data",
		url: "system/sequence/listByPage",
		mainSearch: searchItems,
		columns: [
			{
                checkbox: true
			},
			{
				field: "code",
				title: "规则编码"
			},
			{
				field: "name",
				title: "规则名称"
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
		],
		onCheck: function (row,$element) {
			$("#tb_detail").bootstrapTable('refresh');
        },
		onUncheck: function (row,$element) {
            $("#tb_detail").bootstrapTable('refresh');
        }
	});

	//初始化明细表格
    Autumn.initTable({
        buttonItems:[
            {
                id: "btn_addSub",
                css: "btn green",
                iconCss: "fa fa-plus",
                title: "新增",
                options:[
                    {
                        id: "tb_data",
                        selectNum: 1
                    }
                ],
                onClick: function () {
                    showSubDialog();
                }
            },
            {
                id: "btn_editSub",
                css: "btn blue",
                iconCss: "fa fa-edit",
                title: "编辑",
                options:[
                    {
                        id: "tb_data",
                        selectNum: 1
                    },
                    {
                        id: "tb_detail",
                        selectNum: 1
                    }
                ],
                onClick: function () {
                    let selections = $("#tb_detail").bootstrapTable("getSelections");
                    if (selections.length !== 1){
                        Autumn.error("请选择一条需要修改的数据");
                        return;
                    }
                    showSubDialog('edit');
                }
            },
            {
                id: "btn_deleteSub",
                css: "btn red",
                iconCss: "fa fa-times",
                title: "删除",
                options:[
                    {
                        id: "tb_detail",
                        selectMinNum: 1
                    }
                ],
                onClick: function () {
                    let selections = $("#tb_detail").bootstrapTable("getSelections");
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
                            url: "system/sequenceDetail/deleteBatchByPK",
                            params: ids
                        })).done(function(res){
                            if(res){
                                $("#tb_detail").bootstrapTable("refresh");
                                $("#myModal").modal("hide");
                            }
                        });
                    });
                }
            }
        ],
        id: "tb_detail",
        url: "system/sequenceDetail/listByPage",
        params: function(){
        	let sequenceId = -1;
            let selections = $("#tb_data").bootstrapTable("getSelections");
            if (selections.length !== 0) {
                sequenceId = selections[0].id;
			}
            return {sequenceId: sequenceId};
		},
        columns: [
            {
                checkbox: true
            },
            {
                field: "type",
                title: "类型",
				formatter: function (val,row,index) {
					if (val){
						return dicts['SEQUENCE_TYPE'][val];
					}
                }
            },
            {
                field: "value",
                title: "值"
            },
            {
                field: "sort",
                title: "排序"
            },
            {
                field: "length",
                title: "长度"
            },
            {
                field: "format",
                title: "日期格式化",
                formatter: function (val,row,index) {
                    if (val){
                        return dicts['SEQUENCE_FORMAT'][val];
                    }
                }
            },
            {
                field: "delimit",
                title: "分隔符"
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
                    let url = "system/sequence/save";
                    if (defaultTable){
                        url = "system/sequence/updateByPK";
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
					title: "规则编码",
                    disable: change === 'edit',
                    valid: {
						required: true,
						remote: {
							url: apiUrl+"system/sequence/validate",
							type: "post",
							data: {
								id: $('#hideId').val()
							}
						}
					}
				},
				{
					id: "textName",
					field: "name",
					title: "规则名称",
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
                    title: "备注信息"
                }
            ]
        }
    })
}


function showSubDialog(change){
    let defaultTable = change === "edit" ? "tb_detail" : null;
    Autumn.initDialog({
        buttonItems:[
            {
                id: "btn_apply",
                css: "btn green",
                title: "确认",
                pasteForm: 'editForm',
                onClick: function (data) {
                    let url = "system/sequenceDetail/save";
                    if (defaultTable){
                        url = "system/sequenceDetail/updateByPK";
                    }
                    $.when(Autumn.customAjax({
                        url: url,
                        params: data
                    })).done(function (res) {
                        if (res){
                            $("#tb_detail").bootstrapTable("refresh");
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
                    id: "hideSequenceId",
                    field: "sequenceId",
					defaultValue: $("#tb_data").bootstrapTable("getSelections").length > 0 ?$("#tb_data").bootstrapTable("getSelections")[0].id:''
                },
                {
                    id: "comboType",
                    field: "type",
                    title: "类型",
                    valid: 'required',
                    combo: {
                        url: "system/dict/listByCode",
                        params: {
                            code: "SEQUENCE_TYPE"
                        },
                        optionId: "code",
                        optionText: "name",
                        onChange: function () {
							let value = $(this).val();
                            $("#textValue").attr("disabled",false);
                            $("#textLength").attr("disabled",false);
                            $("#comboFormat").attr("disabled",false);
							switch (value) {
								case '1':
									$("#textLength").attr("disabled",true);
									$("#comboFormat").attr("disabled",true);
									break;
								case '2':
									$("#textValue").attr("disabled",true);
									$("#comboFormat").attr("disabled",true);
									break;
								case '3':
									$("#textValue").attr("disabled",true);
									$("#textLength").attr("disabled",true);
									break;
                            }
                        }
                    }
                },
                {
                    id: "textValue",
                    field: "value",
                    title: "值"
                },
                {
                    id: "textSort",
                    field: "sort",
                    title: "排序",
                    valid: 'required'
                },
                {
                    id: "textLength",
                    field: "length",
                    title: "长度"
                },
                {
                    id: "comboFormat",
                    field: "format",
                    title: "日期格式化",
                    combo: {
                        url: "system/dict/listByCode",
                        params: {
                            code: "SEQUENCE_FORMAT"
                        },
                        optionId: "code",
                        optionText: "name"
                    }

                },
                {
                    id: "textDelimit",
                    field: "delimit",
                    title: "分隔符"
                },
            ]
        }
    })
}