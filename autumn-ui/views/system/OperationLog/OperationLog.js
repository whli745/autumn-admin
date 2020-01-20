$(function () {
	//定义搜索区域字段
	let searchItems = [
		{
			id: "comboSearchOpertionType",
			field: "opertionType",
			title: "执行类型",
            combo:{
                url: "system/dict/listByCode",
                params: {
                    code: "OPERTION_TYPE"
                },
                optionId: "code",
                optionText: "name"
            }
		},
		{
			id: "textSearchTableName",
			field: "tableName",
			title: "执行语句"
		},
		{
			id: "textSearchRequestUri",
			field: "requestUri",
			title: "请求URI"
		},
		{
			id: "textSearchIp",
			field: "ip",
			title: "执行IP"
		},
		{
            id: "textSearchCreateBy",
            field: "createBy",
            title: "执行人"
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
		id: "tb_data",
		url: "system/operationLog/listByPage",
		mainSearch: searchItems,
		columns: [
			{
                title: "序号",
                width: 30,
                formatter: function (val,row,index) {
                    return index+1;
                }
			},
			{
				field: "opertionType",
				title: "执行类型",
				formatter: function (val,row,index) {
					if (val){
						return dicts['OPERTION_TYPE'][val];
					}
                }
			},
			{
				field: "tableName",
				title: "执行语句"
			},
			{
				field: "operationDetail",
				title: "执行参数"
			},
			{
				field: "requestUri",
				title: "请求URI"
			},
			{
				field: "ip",
				title: "执行IP"
			},
            {
                field: "createBy",
                title: "执行人"
            },
            {
                field: "createDate",
                title: "执行时间"
            }
		]
	});
});
