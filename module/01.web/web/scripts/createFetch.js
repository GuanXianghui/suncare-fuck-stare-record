/**
 * 创建批次
 */
function createFetch(){
    var maxTimes = $("#maxTimes").val();
    if(maxTimes == EMPTY){
        showAttention("请输入最大倍数");
        return;
    }
    var eachMoney = $("#eachMoney").val();
    if(eachMoney == EMPTY){
        showAttention("请输入每张牌价格");
        return;
    }
    var pieces = $("#pieces").val();
    if(pieces == EMPTY){
        showAttention("请输入手持牌数");
        return;
    }
    var users = $("#users").val();
    if(users == EMPTY){
        showAttention("请输入玩家姓名");
        return;
    }
    // 判断字符串是否含有非法字符
    var result = checkStr(users, SYMBOL_ARRAY_1);
    if(result["isSuccess"] == false) {
        showAttention("玩家姓名包含非法字符："  + result["symbol"]);
        return;
    }
    if(!confirm("您确定要创建批次吗？")){
        return;
    }

    //创建用户
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "createFetch.do",
        data:"maxTimes=" + maxTimes + "&eachMoney=" + eachMoney + "&pieces=" + pieces + "&users=" + filterStr(users) + "&token=" + token,
        success:function (data, textStatus) {
            if ((SUCCESS_STR == textStatus) && (null != data)) {
                data = eval("(" + data + ")");
                //判是否成功
                if (false == data["isSuccess"]) {
                    showError(data["message"]);
                } else {
                    //成功
                    showSuccess(data["message"]);
                }
                //判是否有新token
                if (data["hasNewToken"]) {
                    token = data["token"];
                }
            } else {
                showAttention("服务器连接异常，请稍后再试！");
            }
        },
        error:function (data, textStatus) {
            showAttention("服务器连接异常，请稍后再试！");
        }
    });
}