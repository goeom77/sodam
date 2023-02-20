package com.samsung.sodam.util;

import com.samsung.sodam.api.response.CounselorListResponse;
import com.samsung.sodam.db.entity.Career;
import com.samsung.sodam.db.entity.Counselor;
import com.samsung.sodam.db.entity.Education;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class CounselorUtil {

    public static List<Education> educationStringToObjectList(String data) {
        List<Education> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(data, "#");
        StringTokenizer stInLoop;

        while (st.hasMoreTokens()) {
            stInLoop = new StringTokenizer(st.nextToken(), "^");
            list.add(Education.builder()
                    .degree(stInLoop.hasMoreTokens() ? stInLoop.nextToken() : "")
                    .school(stInLoop.hasMoreTokens() ? stInLoop.nextToken() : "")
                    .major(stInLoop.hasMoreTokens() ? stInLoop.nextToken() : "")
                    .is_graduate(stInLoop.hasMoreTokens() ? stInLoop.nextToken() : "")
                    .build());
        }
        return list;
    }

    public static String educationObjectToString(List<Education> list) {
        StringBuilder sb = new StringBuilder();
        for (Education o : list) {
            sb.append(o.getDegree()).append('^');
            sb.append(o.getSchool()).append('^');
            sb.append(o.getMajor()).append('^');
            sb.append(o.getIs_graduate());
            sb.append('#');
        }
        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }

    public static List<Career> careerStringToObjectList(String data) {
        List<Career> list = new ArrayList<Career>();
        StringTokenizer st = new StringTokenizer(data, "#");
        StringTokenizer stInLoop;

        while (st.hasMoreTokens()) {
            stInLoop = new StringTokenizer(st.nextToken(), "^");
            list.add(Career.builder()
                    .name(stInLoop.hasMoreTokens() ? stInLoop.nextToken() : "")
                    .content(stInLoop.hasMoreTokens() ? stInLoop.nextToken() : "")
                    .period(stInLoop.hasMoreTokens() ? stInLoop.nextToken() : "")
                    .build());
        }
        return list;
    }

    public static String careerObjectToString(List<Career> list) {
        StringBuilder sb = new StringBuilder();
        for (Career o : list) {
            sb.append(o.getName()).append('^');
            sb.append(o.getContent()).append('^');
            sb.append(o.getPeriod());
            sb.append('#');
        }
        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }

    public static List<CounselorListResponse> counselorToResponse(List<Counselor> list) {
        return list.stream().map(it -> CounselorListResponse.builder()
                        .consultTypeList(it.getConsultTypeList())
                        .gender(it.getGender())
                        .name(it.getName())
                        .profileImg(it.getProfileImg())
                        .tel(it.getTel())
                        .build())
                .collect(Collectors.toList());
    }
}
