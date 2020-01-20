$(function () {
    initDict();

    //获取用户授权菜单
    $.when(Autumn.customAjax({
        url:'system/menu/listMenuByUserAndCompanyAndParent',
        params:{
            parentId:'1194499298376736769',
            userId: sessionStorage.getItem("loginId"),
            companyId: sessionStorage.getItem("loginCompanyId")
        },
        async: false
    })).done(function (res) {
        $(".layui-input-search").data("menus",res);
        $('#LAY-system-side-menu').bootstrapMenu({data: res});
    });


    $("#loginName").text(sessionStorage.getItem("loginName"))

    //退出
    $("#logOut").on("click",function(){
        $.when(Autumn.customAjax({
            url: "auth/logout"
        })).done(function(result){
            if(result){
                sessionStorage.clear();
                location.href = "../login.html";
            }
        })
    });

    //搜索框Enter事件
    $(".layui-input-search").keydown(function (event) {
        if (event.which == 13){
            let menus = $(".layui-input-search").data('menus');
            let value = $(".layui-input-search").val();
            let temp = [];
            if (value){
                searchMenu(temp,value,menus);
            } else {
                temp = menus;
            }
            $('#LAY-system-side-menu li:gt(0)').remove();
            $('#LAY-system-side-menu').bootstrapMenu({
                data: temp
            });
            layui.element.init();
        }
    });
});

//搜索菜单
function searchMenu(temp,value,menus) {
    $.each(menus,function (i,item) {
        if (item.name.indexOf(value) > -1 && item.href){
            temp.push(item);
        }
        if (item.children && item.children.length > 0){
            searchMenu(temp,value,item.children);
        }
    });
}

//字典
function initDict() {
    let codes = ['YES_NO','MENU_TARGET','OFFICE_TYPE','OPERTION_TYPE','TASK_GROUP','TASK_STATUS',
	'SEQUENCE_TYPE','SEQUENCE_FORMAT','FILE_TYPE','MODEL_TYPE'];

    $.when(Autumn.customAjax({
        url: 'system/dict/listCodeKeyByCodes',
        params: codes
    })).done(function (res) {
        localStorage.setItem("dict",JSON.stringify(res));
    })
}
