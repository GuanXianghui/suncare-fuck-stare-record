/**
 * 初始化
 */
$(document).ready(function(){
    var html = EMPTY;
    for(var i=0;i<userArray.length;i++){
        html += "<option value='" + userArray[i] + "'>" + userArray[i] + "</option>";
    }
    $("#winner").html(html);
    $("#first").html(html);

    //改变赢家姓名
    changeWinner();

    //更新所有输家描述
    resetAllLoseDesc();
});

/**
 * 改变赢家姓名
 * @param t
 */
function changeWinner(){
    var winner = $("#winner").val();
    var html = EMPTY;
    for(var i=0;i<userArray.length;i++){
        if(userArray[i] == winner){
            continue;
        }
        html += "<span id='lose_user_" + (i+1) + "'>" + userArray[i] + "</span>" +
            "手上剩余<select class='text-input very-small-input' id='lose_pieces_" + (i+1) + "' onchange='changeLosePieces(" + (i+1) + ")'>";
        for(j=1;j<=5;j++){
            html += "<option value='" + j + "'>" + j + "</option>";
        }
        html += "</select>张";
        html += " <span>描述</span>&nbsp;&nbsp;<input id='lose_desc_" + (i+1) + "' class=\"text-input medium-input\" type=\"text\"/>";
        html += "<br>"
    }
    $("#loser").html(html);

    //更新所有输家描述
    resetAllLoseDesc();
}

/**
 * 更新所有输家描述
 */
function resetAllLoseDesc(){
    var winner = $("#winner").val();
    for(var i=0;i<userArray.length;i++){
        if(userArray[i] == winner){
            continue;
        }
        changeLosePieces(i+1);
    }
}

/**
 * 改变输家剩余张数
 * @param index
 */
function changeLosePieces(index){
    var loseUser = $("#lose_user_" + index).html();
    var first = $("#first").val();
//    姚洁,5,5张*炸弹翻2倍*0.5元/张=5元
    var losePieces = parseInt($("#lose_pieces_" + index).val());
    var isClose = first != loseUser && losePieces == 5;//是否被关
    var times = parseInt($("#times").val());
    var eachMoney = parseFloat($("#eachMoney").val());
    var loseMoney = losePieces;
    var loseDesc = losePieces + "张*";
    if(isClose){
        loseMoney = loseMoney * 2;
        loseDesc += "2(被关)*";
    }
    loseMoney = loseMoney * times * eachMoney;
    loseDesc += times + "(炸弹翻倍)*" + eachMoney + "元/张=" + loseMoney + "元";
    $("#lose_desc_" + index).val(loseDesc);

    //计算赢的金额
    calculateWinMoney();
}

/**
 * 计算赢的金额
 */
function calculateWinMoney(){
    var winner = $("#winner").val();
    var winMoney = 0;
    for(var i=0;i<userArray.length;i++){
        if(userArray[i] == winner){
            continue;
        }
        var index = i +1;
        var desc = $("#lose_desc_" + index).val();
        var equalIndex = desc.indexOf("=");
        if(equalIndex == -1){
            continue;
        }
        desc = desc.substr(equalIndex + 1);
        var yuanIndex = desc.indexOf("元");
        desc = desc.substr(0, yuanIndex);
        winMoney += parseFloat(desc);
    }
    $("#win_money").val(winMoney);
}

/**
 * 记录战绩
 */
function createRecord(){
    if(!confirm("您确定要记录战绩吗？")){
        return;
    }
    var winner = $("#winner").val();
    var times = parseInt($("#times").val());
    var first = $("#first").val();
    var winMoney = $("#win_money").val();
    var detail = EMPTY;
    var winner = $("#winner").val();
    for(var i=0;i<userArray.length;i++){
        if(userArray[i] == winner){
            continue;
        }
        if(detail != EMPTY){
            detail += SYMBOL_STAND;
        }
        var index = i +1;
        var loseUser = $("#lose_user_" + index).html();
        var losePieces = parseInt($("#lose_pieces_" + index).val());
        var desc = $("#lose_desc_" + index).val();
        detail += loseUser + SYMBOL_COMMA + losePieces + SYMBOL_COMMA + desc;
    }

    //创建用户
    var SUCCESS_STR = "success";//成功编码
    $.ajax({
        type:"post",
        async:false,
        url:baseUrl + "createRecord.do",
        data:"winner=" + winner + "&times=" + times + "&first=" + first + "&winMoney=" + winMoney + "&detail=" + detail + "&token=" + token,
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