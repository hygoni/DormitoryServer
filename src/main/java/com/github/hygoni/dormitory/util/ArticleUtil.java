package com.github.hygoni.dormitory.util;

public class ArticleUtil {
    public static String getArticleCategory(int id){
        if (id == 1){
            return "한식";
        } else if(id == 2){
            return "중식";
        } else if(id == 3){
            return "양식";
        } else if(id == 4){
            return "일식";
        } else if(id == 5){
            return "간식";
        } else if(id == 6){
            return "음료";
        }
        return "전체";
    }
}
