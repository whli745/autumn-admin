$(function () {

    //定义搜索区域字段
    let searchItems = [
        {
            id: "textSearchKey",
            field: "key",
            title: "流程标识"
        },
        {
            id: "textSearchName",
            field: "name",
            title: "流程名称"
        },
        {
            id: "comboSearchCategory",
            field: "category",
            title: "模型类型",
            comboUrl: "/system/sysDict/listByParentValue",
            comboData: {
                value: "MODEL_TYPE"
            },
            comboId: "value",
            comboText: "name"
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
                id: "btn_import",
                css: "btn green",
                iconCss: "fa fa-plus",
                title: "新增",
                onClick: function () {
                    showDialog();
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

                    if (selections.length !== 1) {
                        Autumn.error("请选择需要删除的数据！");
                        return;
                    }

                    Autumn.confirm("该操作为级联,是否确认删除数据？", function () {
                        $.when(Autumn.customAjax({
                            url: oaUrl + "/activity/customProcess/deleteProcess",
                            params: {deploymentId: selections[0].deploymentId}
                        }))
                    });
                }
            }
        ],
        id: "tb_data",
        mainSearch: searchItems,
        url: oaUrl + "/activity/customProcess/listByPage",
        columns: [
            {
                checkbox: true
            },
            {
                field: "key",
                title: "流程标识"
            },
            {
                field: "name",
                title: "流程名称"
            },
            {
                field: "resourceName",
                title: "XML资源",
                formatter: function (val, row, index) {
                    return '<a href="' + oaUrl + '/activity/customProcess/getResource' +
                        '?resourceType=xml&processDefinitionId=' + row.id + '"' +
                        'target="_blank">' + val + '</a>';
                }
            },
            {
                field: "diagramResourceName",
                title: "图片资源",
                formatter: function (val, row, index) {
                    return '<a href="' + oaUrl + '/activity/customProcess/getResource' +
                        '?resourceType=image&processDefinitionId=' + row.id + '"' +
                        'target="_blank">' + val + '</a>';
                }
            },
            {
                field: "version",
                title: "版本"
            },
            {
                field: "description",
                title: "流程描述"
            }
        ]
    });
});

//弹出框
function showUploadDialog(change) {
    index = JEE.initDialog({
        title: "新增",
        data: data,
        url: "ProcessForm.html",
        disable: {}
    });
}
