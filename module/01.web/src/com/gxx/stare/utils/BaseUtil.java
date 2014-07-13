package com.gxx.stare.utils;

import com.gxx.stare.dao.FetchDao;
import com.gxx.stare.dao.RecordDao;
import com.gxx.stare.entities.Fetch;
import com.gxx.stare.entities.Record;
import com.gxx.stare.interfaces.SymbolInterface;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 基础工具类
 *
 * @author Gxx
 * @module oa
 * @datetime 14-3-30 12:09
 */
public class BaseUtil implements SymbolInterface {
    /**
     * 统计战绩结果
     * @param fetchId
     * @return
     */
    public static String calculateRecordResult(String fetchId) throws Exception {
        Fetch fetch = FetchDao.getFetchById(Integer.parseInt(fetchId));
        List<Record> recordList = RecordDao.queryAllRecordsByFetchId(Integer.parseInt(fetchId));
        String[] userArray = fetch.getUsers().split(SYMBOL_COMMA);
        float[] moneyArray = new float[userArray.length];
        int[] winCountArray = new int[userArray.length];
        int[] loseCountArray = new int[userArray.length];
        for(Record record : recordList){
            int index = getIndexOfArray(record.getWinner(), userArray);
            winCountArray[index] = ++winCountArray[index];
            moneyArray[index] = moneyArray[index] + record.getWinMoney();
            String[] detailArray = record.getDetail().split("\\|");
            for(String detail : detailArray){
                String detailUser = detail.substring(0, detail.indexOf(SYMBOL_COMMA));
                detail = detail.substring(detail.indexOf(SYMBOL_EQUAL) + 1);
                float detailMoney = Float.parseFloat(detail.substring(0, detail.indexOf("元")));
                index = getIndexOfArray(detailUser, userArray);
                loseCountArray[index] = ++loseCountArray[index];
                moneyArray[index] = moneyArray[index] - detailMoney;
            }
        }
        String result = StringUtils.EMPTY;
        for(int i=0;i<userArray.length;i++){
            if(StringUtils.isNotBlank(result)){
            }
            result += "<tr>";
            result += "<td>" + userArray[i] + "</td>";
            result += "<td>" + winCountArray[i] + "</td>";
            result += "<td>" + loseCountArray[i] + "</td>";
            result += "<td>" + (moneyArray[i]>0?"+":"") + moneyArray[i] + "</td>";
            result += "</tr>";
        }
        return result;
    }

    /**
     * 返回数组中的序号
     * @param user
     * @param userArray
     * @return
     */
    public static int getIndexOfArray(String user, String[] userArray){
        for(int i=0;i<userArray.length;i++){
            if(StringUtils.equals(user, userArray[i])){
                return i;
            }
        }
        return -1;
    }
}
