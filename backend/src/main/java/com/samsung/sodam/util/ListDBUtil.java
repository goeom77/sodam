package com.samsung.sodam.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ListDBUtil {
    public static List<Integer> DBDataToIntegerList(String dbData){
        List<Integer> list = new ArrayList<>();
        dbData = dbData.substring(1, dbData.length()-1);
        StringTokenizer st = new StringTokenizer(dbData, ",");
        while(st.hasMoreTokens()) list.add(Integer.parseInt(st.nextToken()));
        return list;
    }

// DBDataToIntegerList 테스트 함수
//    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        Gson gson = new Gson();
//        String jsonPlace = gson.toJson(list);
//
//        List<Integer> convertList = DBDataToIntegerList(jsonPlace);
//        for(Integer e : convertList){
//            System.out.println(e);
//        }
//    }
}
