$(function () {
	//定义搜索区域字段
	let searchItems = [
		{
			id: "textSearchJobName",
			field: "jobName",
			title: "任务名"
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
		},
        {
            id: "comboSearchState",
            field: "state",
            title: "任务状态",
            combo:{
                url: "system/dict/listByCode",
                params: {
                    code: "TASK_STATUS"
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
                        selectNum: 1
                    }
                ],
				onClick: function () {
					let selections = $("#tb_data").bootstrapTable("getSelections");
                    if (selections.length !== 1){
                        Autumn.error("请选择一条需要删除的数据");
                        return;
                    }

                    let item = selections[0];

					Autumn.confirm("是否确认删除数据？", function(){
						$.when(Autumn.customAjax({
							url: "scheduler/task/delete",
							params: item
						})).done(function(res){
							if(res){
								$("#tb_data").bootstrapTable("refresh");
							}
						});
					});
				}
			},
			{
				id: "btn_trigger",
				css: "btn yellow",
                iconCss: "fa fa-play",
				title: "立即执行",
                options:[
                    {
                        id: "tb_data",
                        selectNum: 1
                    }
                ],
				onClick: function () {
					let selections = $("#tb_data").bootstrapTable("getSelections");
                    if (selections.length !== 1){
                        Autumn.error("请选择一条立即执行的数据");
                        return;
                    }
					let item = selections[0];

                    $.when(Autumn.customAjax({
                        url: "scheduler/task/triggerJob",
                        params: item
                    })).done(function(res){
                        if(res){
                            $("#tb_data").bootstrapTable("refresh");
                        }
                    });
				}
			},
			{
				id: "btn_pause",
				css: "btn default",
                iconCss: "fa fa-pause",
				title: "暂停任务",
				onClick: function () {
					let url = "scheduler/task/pauseAll";
					let params = {};
					let message = '是否暂停所有任务';
					let selections = $("#tb_data").bootstrapTable("getSelections");
                    if (selections.length === 1){
                        url = "scheduler/task/pause";
                        params = selections[0];
                        message = '是否暂停该任务';
                    }

					Autumn.confirm(message,function () {
                        $.when(Autumn.customAjax({
                            url: url,
                            params: params
                        })).done(function(res){
                            if(res){
                                $("#tb_data").bootstrapTable("refresh");
                            }
                        });
                    });
				}
			},
			{
				id: "btn_resume",
				css: "btn btn-warning",
                iconCss: "fa fa-refresh",
				title: "恢复任务",
				onClick: function () {
                    let url = "scheduler/task/resumeAll";
                    let params = {};
                    let message = '是否恢复所有任务';
                    let selections = $("#tb_data").bootstrapTable("getSelections");
                    if (selections.length === 1){
                        url = "scheduler/task/resume";
                        params = selections[0];
                        message = '是否恢复该任务';
                    }

                    Autumn.confirm(message,function () {
                        $.when(Autumn.customAjax({
                            url: url,
                            params: params
                        })).done(function(res){
                            if(res){
                                $("#tb_data").bootstrapTable("refresh");
                            }
                        });
                    });
				}
			}
		],
		id: "tb_data",
		url: "scheduler/task/listByPage",
		mainSearch: searchItems,
		columns: [
			{
                checkbox: true
			},
			{
				field: "jobName",
				title: "任务名"
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
				title: "任务程序"
			},
			{
				field: "cronExpression",
				title: "定时表达式"
			},
			{
				field: "startTime",
				title: "开始执行时间"
			},
			{
				field: "prevTime",
				title: "上次执行时间"
			},
			{
				field: "nextTime",
				title: "下次执行时间"
			},
			{
				field: "state",
				title: "执行状态",
                formatter: function (val,row,index) {
                    if (val){
                        return dicts['TASK_STATUS'][val];
                    }
                    return '正常';
                }
			},
			{
				field: "jobDescription",
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
                    let url = "scheduler/task/save";
                    if (defaultTable){
                        url = "scheduler/task/updateByPK";
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
					id: "textJobName",
					field: "jobName",
					title: "任务名",
                    valid: 'required',
					disable: change === 'edit'
				},
				{
					id: "comboJobGroup",
					field: "jobGroup",
					title: "任务组",
                    valid: 'required',
                    disable: change === 'edit',
                    combo:{
                        url: "system/dict/listByCode",
                        params: {
                            code: "TASK_GROUP"
                        },
                        optionId: "code",
                        optionText: "name"
                    }
				},
				{
					id: "textJobClass",
					field: "jobClass",
					title: "任务程序",
                    valid: 'required',
                    disable: change === 'edit'
				},
				{
					id: "textCronExpression",
					field: "cronExpression",
					title: "cron表达式",
                    valid: 'required'
				},
                {
                    id: "areaJobDescription",
                    field: "jobDescription",
                    title: "备注信息",
					colspan: 10
                }
            ]
        }
    })
}


