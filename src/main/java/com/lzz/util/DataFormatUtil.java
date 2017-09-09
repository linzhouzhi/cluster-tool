package com.lzz.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * Created by lzz on 2017/9/9.
 */
public class DataFormatUtil {
    public static String formatAccessLog(Map<String, String> machine, String file, String[] fields, int rowNum, String filterField, int sort){
        int maxNum = 200;
        if( rowNum > maxNum ){
            rowNum = maxNum;
        }
        String cmd = "";
        String sortStr = "";
        if( sort == 0 ){
            cmd = "tail -" + rowNum + " " + file;
        }else{
            cmd = "tail -2000 " + file;
            sortStr = " | sort -r -t ' '  -k " + sort +" | head -n " +maxNum;
        }
        if( !StringUtils.isBlank( filterField ) ){
            cmd += " | grep " + filterField;
        }
        cmd += sortStr;

        String ip = machine.get("ip");
        String username = machine.get("username");
        String password = machine.get("password");
        RemoteShellTool rms = new RemoteShellTool(ip, username, password, "utf-8");
        System.out.println( cmd );
        String result = rms.exec( cmd );
        String res = DataFormatUtil.table(fields, result);
        return res;
    }

    public static String table(String[] fields, String contentStr){
        String[] contents = contentStr.split("\n");
        int fieldsSize = fields.length;
        String res = "<table>";
        res += "<tr>";
        for(String field : fields){
            res += "<th>" + field + "</th>";
        }
        res += "</tr>";
        for(int i = 0; i < contents.length; i++){
            String content = contents[i];
            String[] contentFields = content.split(" ");
            if( null != contentFields && contentFields.length >= fieldsSize ){
                res += "<tr>";
                for(int j = 0; j < fieldsSize; j++){
                    res += "<td>" + contentFields[j] + "</td>";
                }
                res += "</tr>";
            }
        }
        res += "</table>";
        return res;
    }
}
