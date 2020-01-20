$(function () {

    //搜索
    let searchItems = [
        {
            id: "textSearchName",
            title: "菜单名称",
            field: "name"
        },
        {
            id: "comboSearchTarget",
            title: "打开方式",
            field: "target",
            combo:{
                url: "system/dict/listByCode",
                params: {
                    code: "MENU_TARGET"
                },
                optionId: "code",
                optionText: "name"
            }
        },
        {
            id: "comboSearchEnabled",
            title: "启用",
            field: "enabled",
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

    Autumn.initSearch({
        id: "search",
        searchItems: searchItems,
        pasteTable: "tb_data"
    });


    //表格
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
                    $.each(selections,function (i,item) {
                        ids.push(item.id);
                    });

                    Autumn.confirm("是否确认删除数据？",function () {
                        $.when(Autumn.customAjax({
                            url: 'system/menu/deleteBatchByPK',
                            params: ids
                        })).done(function (res) {
                            if (res){
                                $("#tb_data").bootstrapTable("refresh");
                                $("#myModal").modal("hide");
                            }
                        })
                    })
                }
            }
        ],
        id: "tb_data",
        url: "system/menu/listAll",
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
                title: "名称"
            },
            {
                field: "href",
                title: "链接"
            },
            {
                field: "sort",
                title: "排序"
            },
            {
                field: "target",
                title: "打开方式",
                formatter: function (val,row,index) {
                    if (val){
                        return dicts['MENU_TARGET'][val];
                    }
                }
            },
            {
                field: "menuIcon",
                title: "图标",
                formatter: function (val,row,index) {
                    if (val) {
                        return '<span class="' + val + '"></span>';
                    }
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
            }
        ]

    });

});

function showDialog(change) {
    let defaultTable = change === "edit" ? "tb_data" : null;
    Autumn.initDialog({
        buttonItems:[
            {
                id: "btn_apply",
                css: "btn green",
                title: "确认",
                pasteForm: 'editForm',
                onClick: function (data) {
                    let url = "system/menu/save";
                    if (defaultTable){
                        url = "system/menu/updateByPK";
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
                    title: "上级菜单",
                    field: "parentId",
                    tree: true,
                    combo:{
                        url: "system/menu/listAllMenu",
                        optionId: "id",
                        optionText: "name"
                    }
                },
                {
                    id: "textName",
                    title: "菜单名称",
                    field: "name",
                    valid: {
						required: true,
						remote: {
							url: apiUrl+"system/menu/validate",
							type: "post",
							data: {
								id: $('#hideId').val()
							}
						}
					}
                },
                {
                    id: "textSort",
                    title: "排序",
                    field: "sort",
                    valid: {
						required: true,
						remote: {
							url: apiUrl+"system/menu/validate",
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
                    id: "textHref",
                    title: "地址",
                    field: "href"
                },
                {
                    id: "comboTarget",
                    title: "打开方式",
                    field: "target",
                    combo:{
                        url: "system/dict/listByCode",
                        params: {
                            code: "MENU_TARGET"
                        },
                        optionId: "code",
                        optionText: "name"
                    }
                },
                {
                    id: "textMenuIcon",
                    title: "图标",
                    field: "menuIcon"
                },
                {
                    id: "switchEnabled",
                    title: "启用",
                    field: "enabled",
                    defaultValue: "1"
                },
                {
                    id: "areaRemark",
                    title: "备注",
                    field: "remark"
                }
            ]
        }
    })
}