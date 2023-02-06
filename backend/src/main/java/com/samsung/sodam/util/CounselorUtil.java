package com.samsung.sodam.util;

import com.samsung.sodam.db.entity.Career;
import com.samsung.sodam.db.entity.Education;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CounselorUtil {

    public static List<Education> educationStringToObjectList(String data){
        List<Education> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(data, "#");
        StringTokenizer stInLoop;

        while(st.hasMoreTokens()){
            stInLoop = new StringTokenizer(st.nextToken(), "^");
            list.add(Education.builder()
                    .degree(stInLoop.hasMoreTokens()?stInLoop.nextToken():"")
                    .school(stInLoop.hasMoreTokens()?stInLoop.nextToken():"")
                    .major(stInLoop.hasMoreTokens()?stInLoop.nextToken():"")
                    .isGraduate(stInLoop.hasMoreTokens()?stInLoop.nextToken():"")
                    .build());
        }
        return list;
    }
    public static String educationObjectToString(List<Education> list){
        StringBuilder sb = new StringBuilder();
        for(Education o : list){
            sb.append(o.getDegree()).append('^');
            sb.append(o.getSchool()).append('^');
            sb.append(o.getMajor()).append('^');
            sb.append(o.getIsGraduate());
            sb.append('#');
        }
        String result = sb.toString();
        return result.substring(0, result.length()-1);
    }

    public static List<Career> careerStringToObjectList(String data){
        List<Career> list = new ArrayList<Career>();
        StringTokenizer st = new StringTokenizer(data, "#");
        StringTokenizer stInLoop;

        while(st.hasMoreTokens()){
            stInLoop = new StringTokenizer(st.nextToken(), "^");
            list.add(Career.builder()
                    .careerName(stInLoop.hasMoreTokens()?stInLoop.nextToken():"")
                    .careerContent(stInLoop.hasMoreTokens()?stInLoop.nextToken():"")
                    .careerPeriod(stInLoop.hasMoreTokens()?stInLoop.nextToken():"")
                    .build());
        }
        return list;
    }

    public static String careerObjectToString(List<Career> list){
        StringBuilder sb = new StringBuilder();
        for(Career o : list){
            sb.append(o.getCareerName()).append('^');
            sb.append(o.getCareerContent()).append('^');
            sb.append(o.getCareerPeriod());
            sb.append('#');
        }
        String result = sb.toString();
        return result.substring(0, result.length()-1);
    }

}
