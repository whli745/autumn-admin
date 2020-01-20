$(function () {
	//定义搜索区域字段
	let searchItems = [
		{
			id: "textSearchKey",
			field: "key",
			title: "文件标识"
		},
		{
			id: "textSearchName",
			field: "name",
			title: "文件名称"
		},
		{
			id: "comboSearchType",
			field: "type",
			title: "文件类型",
			combo: {
			    url: "system/dict/listByCode",
			    params: {
			        code: "FILE_TYPE"
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
                id: "btn_upload",
                css: "btn green",
                iconCss: "fa fa-plus",
                title: "上传文件",
                onClick: function () {
                    showDialog();
                }
            },
			{
			    id: "btn_download",
			    css: "btn blue",
			    iconCss: "fa fa-plus",
			    title: "下载文件",
				options:[
				    {
				        id: "tb_data",
				        selectNum: 1
				    }
				],
			    onClick: function () {
			        let selections = $("#tb_data").bootstrapTable("getSelections");
			        if (selections.length !== 1){
			            Autumn.error("请选择一条需要下载的数据");
			            return;
			        }
					window.open(apiUrl+selections[0].url,'_blank')
			    }
			}
		],
		id: "tb_data",
		url: "system/upload/listByPage",
		mainSearch: searchItems,
		params: function(){
			return {companyId: sessionStorage.getItem("loginCompanyId")}
		},
		columns: [
			{
                checkbox: true
			},
			{
				field: "uniqueKey",
				title: "文件标识"
			},
			{
				field: "name",
				title: "文件名称"
			},
			{
				field: "type",
				title: "文件类型",
				formatter: function(val,row,index){
					if(val){
						return dicts['FILE_TYPE'][val];
					}
				}
			},
			{
				field: "url",
				title: "文件路径"
			},
			{
				field: "version",
				title: "版本号"
			},
			
			{
				field: "updated",
				title: "强制更新",
				formatter: function(val,row,index){
					if(val == '1'){
						return '是';
					}
					return '否';
				}
			},
			{
				field: "remark",
				title: "备注"
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
					let el = $("#editForm");
					if(el.valid()){
						Autumn.upload("system/upload/uploadFile",new FormData(el[0]))
					}
                    
                }
            }
        ],
        id: 'myModal',
        title: defaultTable ? '编辑' : '新增',
        form: {
            id: 'editForm',
			multipart: true,
            pasteTable: defaultTable,
            formItems:[
				{
					id: "searchUniqueKey",
					field: "uniqueKey",
					title: "文件标识",
                    valid: 'required'
				},
				{
					id: "comboType",
					field: "type",
					title: "文件类型",
                    valid: 'required',
					combo: {
					    url: "system/dict/listByCode",
					    params: {
					        code: "FILE_TYPE"
					    },
					    optionId: "code",
					    optionText: "name"
					}
				},
				{
					id: "fileFile",
					field: "file",
					title: "选择文件",
				    valid: 'required'
				},
				{
					id: "comboUpdated",
					field: "updated",
					title: "强制更新",
                    valid: 'required',
					defaultValue: '0',
					combo: {
					    url: "system/dict/listByCode",
					    params: {
					        code: "YES_NO"
					    },
					    optionId: "code",
					    optionText: "name"
					}
				},
				{
					id: "areaRemark",
					field: "remark",
					title: "备注",
                    colspan: 10
				},
            ]
        }
    })
}
