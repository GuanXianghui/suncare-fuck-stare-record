/**
 * 查询战绩记录
 */
function queryRecords() {
    var fetchId = $("#fetchId").val();
    $("#viewRecordsFetchId").val(fetchId);
    document.forms["viewRecordsForm"].submit();
}

/**
 * 跳到某一页
 *
 * @param pageNum
 */
function jump2page(pageNum) {
    $("#pageNum").val(pageNum);
    document.forms["viewRecordsForm"].submit();
}