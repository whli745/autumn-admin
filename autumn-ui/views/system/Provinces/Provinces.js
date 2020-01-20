$(function () {
	//定义搜索区域字段
	let searchItems = [
		{
			id: "textSearchCityName",
			field: "cityName",
			title: "城市名称"
		},
		{
			id: "textSearchCityCode",
			field: "cityCode",
			title: "城市代码"
		},
		{
			id: "textSearchZipCode",
			field: "zipCode",
			title: "城市邮编"
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
							url: "system/provinces/deleteBatchByPK", 
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
		url: "system/provinces/listAll",
		page: false,
		treeView:{
        	enable: true,
            fieldKey: 'cityName'
		},
		mainSearch: searchItems,
		params: function () {
			return {parentId: '0'};
        },
		columns: [
			{
                checkbox: true
			},
			{
				field: "cityName",
				title: "城市名称"
			},
			{
				field: "shortName",
				title: "城市缩写名称"
			},
			{
				field: "depth",
				title: "城市层级"
			},
			{
				field: "cityCode",
				title: "城市代码"
			},
			{
				field: "zipCode",
				title: "城市邮编"
			},
			{
				field: "mergerName",
				title: "城市组合名称"
			},
			{
				field: "longitude",
				title: "精度"
			},
			{
				field: "latitude",
				title: "维度"
			},
			{
				field: "pinyin",
				title: "城市拼音"
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
                    let url = "system/provinces/save";
                    if (defaultTable){
                        url = "system/provinces/updateByPK";
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
					title: "父级id",
                    tree: true,
                    combo:{
                        url: "system/provinces/listAll",
                        params: {
                            enable: "1"
                        },
                        optionId: "id",
                        optionText: "cityName"
                    }
				},
				{
					id: "textCityName",
					field: "cityName",
					title: "城市名称",
                    valid: 'required'
				},
				{
					id: "textShortName",
					field: "shortName",
					title: "城市缩写名称"
				},
				{
					id: "textDepth",
					field: "depth",
					title: "城市层级",
                    valid: 'required'
				},
				{
					id: "textCityCode",
					field: "cityCode",
					title: "城市代码",
                    valid: 'required'
				},
				{
					id: "textZipCode",
					field: "zipCode",
					title: "城市邮编"
				},
				{
					id: "textMergerName",
					field: "mergerName",
					title: "城市组合名称"
				},
				{
					id: "textLongitude",
					field: "longitude",
					title: "精度"
				},
				{
					id: "textLatitude",
					field: "latitude",
					title: "维度"
				},
				{
					id: "textPinyin",
					field: "pinyin",
					title: "城市拼音"
				},
				{
					id: "switchEnabled",
					field: "enabled",
					title: "启用",
                    defaultValue: '1'
				},
            ]
        }
    })
}
