<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
$(function () {
	//定义搜索区域字段
	let searchItems = [
		<#list table.columns as column>
		<#if !column.pk && column.columnNameLower != 'createBy'
		&& column.columnNameLower != 'createDate' && column.columnNameLower != 'updateBy'
		&& column.columnNameLower != 'updateDate' && column.columnNameLower != 'password'
		&& column.columnNameLower != 'version'>
		{
			id: "textSearch${column.columnName}",
			field: "${column.columnNameLower}",
			title: "${column.columnAlias!}"
		}<#if column_has_next>,</#if>
		</#if>
		</#list>
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
							url: "/${moduleName}/${classNameLower}/deleteBatchByPK", 
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
		url: "/${moduleName}/${classNameLower}/listByPage",
		mainSearch: searchItems,
		params: function(){},
		columns: [
			{
                checkbox: true
			},
			<#list table.columns as column>
			<#if column.columnNameLower != 'createBy' && column.columnNameLower != 'createDate' 
			&& column.columnNameLower != 'updateBy' && column.columnNameLower != 'updateDate' >
			{
				field: "${column.columnNameLower}",
				title: "${column.columnAlias!}"
			}<#if column_has_next>,</#if>
			</#if>
			</#list>
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
                    let url = "/${moduleName}/${classNameLower}/save";
                    if (defaultTable){
                        url = "/${moduleName}/${classNameLower}/updateByPK";
                        let selections = $("#"+defaultTable).bootstrapTable("getSelections")[0];
                        data.id = selections.id;
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
                <#list table.columns as column>
				<#if !column.pk && column.columnNameLower != 'createBy'
				&& column.columnNameLower != 'createDate' && column.columnNameLower != 'updateBy'
				&& column.columnNameLower != 'updateDate' && column.columnNameLower != 'password'
				&& column.columnNameLower != 'version'>
				{
					id: "text${column.columnName}",
					field: "${column.columnNameLower}",
					title: "${column.columnAlias!}",
                    valid: 'required'
				}<#if column_has_next>,</#if>
				</#if>
				</#list>
            ]
        }
    })
}
