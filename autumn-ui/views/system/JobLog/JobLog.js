$(function () {
	//定义搜索区域字段
	let searchItems = [
		{
			id: "textSearchJobName",
			field: "jobName",
			title: "任务名称"
		},
		{
			id: "comboSearchJobGroup",
			field: "jobGroup",
			title: "任务组",
            combo:{
                url: "system/dict/listByCode",
                params: {
                    code: "TASK_GROUP"
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
		id: "tb_data",
		url: "scheduler/jobLog/listByPage",
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
				field: "jobName",
				title: "任务名称"
			},
			{
				field: "jobGroup",
				title: "任务组",
				formatter: function (val,row,index) {
					if (val){
						return dicts['TASK_GROUP'][val];
					}
                }
			},
			{
				field: "jobClass",
				title: "任务类"
			},
			{
				field: "execTime",
				title: "运行时间"
			},
			{
				field: "duration",
				title: "总耗时（秒）"
			},
			{
				field: "jobMsg",
				title: "任务运行信息"
			},
		]
	});
});