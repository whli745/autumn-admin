$(function () {
    //定义搜索区域字段
    let searchItems = [
        {
            id: "textSearchKey",
            field: "key",
            title: "模型标识"
        },
        {
            id: "textSearchName",
            field: "name",
            title: "模型名称"
        },
        {
            id: "comboSearchCategory",
            field: "category",
            title: "模型类型",
            combo:{
                url: "system/dict/listByCode",
                params: {
                    value: "MODEL_TYPE"
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
        buttonItems: [
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
                    if (selections.length !== 1) {
                        Autumn.error("请选择需要修改的数据");
                        return;
                    }

                    window.open(oaUrl + "modeler.html?modelId=" + selections[0].id, "_blank");
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
                    if (selections.length !== 1) {
                        JEE.errMsg("请选择需要删除的数据");
                        return;
                    }

                    if (selections[0].deploymentId) {
                        JEE.errMsg("流程模型已部署，无法删除");
                        return;
                    }

                    Autumn.confirm("是否确认删除数据？", function () {
                        $.when(Autumn.customAjax({
                            url:oaUrl + "activity/customModel/deleteModel", 
                            params: {id: selections[0].id}
                        })).done(function (res) {
                            if (res) {
                                $("#myModal").modal("hide");
                                $("#tb_data").bootstrapTable("refresh");
                            }
                        });
                    });
                }
            },
            {
                id: "btn_deploy",
				css: "btn red",
                iconCss: "fa fa-times",
				title: "发布",
                options:[
                    {
                        id: "tb_data",
                        selectMinNum: 1
                    }
                ],
                onClick: function () {
                    let selections = $("#tb_data").bootstrapTable("getSelections");
                    if (selections.length != 1) {
                        Autumn.error("请选择需要发布的流程模型！");
                        return;
                    }

                    Autumn.confirm("是否确认发布流程模型？", function () {
                        $.when(Autumn.customAjax({
                            url: oaUrl + "/activity/customModel/deployModel", 
                            params: {id: selections[0].id}
                        })).done(function (res) {
                            if (res) {
                                $("#myModal").modal("hide");
                                $("#tb_data").bootstrapTable("refresh");
                            }
                        });
                    });
                }
            }
        ],
        id: "tb_data",
        mainSearch: searchItems,
        url: oaUrl + "activity/customModel/listByPage",
        columns: [
            {
                checkbox: true
            },
            {
                field: "key",
                title: "模型标识"
            },
            {
                field: "name",
                title: "模型名称"
            },
            {
                field: "category",
                title: "模型分类",
                formatter: function (val, row, index) {
                    if (val) {
                        return dicts['MODEL_TYPE'][val];
                    }
                }
            },
            {
                field: "version",
                title: "版本号"
            },
            {
                field: "deploymentId",
                title: "已布署",
                formatter: function (val, row, index) {
                    if (val) {
                        return '是';
                    }
                    return '否';
                }
            }
        ]
    });
});

//弹出框
function showDialog(change, formData) {
    let defaultTable = change === "edit" ? "tb_data" : null;
    Autumn.initDialog({
        buttonItems: [
            {
                id: "btn_apply",
                css: "btn green",
                title: "确认",
                pasteForm: 'editForm',
                onClick: function (data) {
                    let url = oaUrl + "activity/customModel/addModel";
                    $.when(Autumn.customAjax({
                        url: url,
                        params: data
                    })).done(function (result) {
                        if (result) {
                            window.open(oaUrl + "modeler.html?modelId=" + result, "_blank");
                            $("#myModal").modal("hide");
                            $("#tb_data").bootstrapTable("refresh");
                        }
                    })
                }
            }
        ],
        id: "myModal",
        title: "新增",
        form: {
            id: "editForm",
            pasteTable: defaultTable,
            formItems: [
                {
                    id: "textKey",
                    field: "key",
                    title: "流程标识",
                    disable: false,
                    valid: "required"
                },
                {
                    id: "textName",
                    field: "name",
                    title: "流程名称",
                    disable: false,
                    valid: "required"
                },
                {
                    id: "comboCategory",
                    field: "category",
                    title: "流程类型",
                    disable: false,
                    combo:{
                        url: "system/dict/listByCode",
                        params: {
                            value: "MODEL_TYPE"
                        },
                        optionId: "code",
                        optionText: "name"
                    }
                },
                {
                    id: "areaDescription",
                    field: "description",
                    title: "流程描述",
                    disable: false
                }
            ]
        }
    });
}
