package ua.miratech.rudenko.test;

import ua.miratech.rudenko.docstore.domain.Articles;
import ua.miratech.rudenko.docstore.service.ArticlesService;

import java.util.ArrayList;

/**
 * Created by RomanR on 2/14/14.
 */

public class TestFileDuplicate {

    ArticlesService articlesService = new ArticlesService();


    private String checkFileDuplicate(String md5, String path, ArrayList<Articles> listMd5){
        String newPath = path;
        for(Articles article : listMd5){
            if (md5.equals(article.getMd5())){
                newPath = article.getPath();
            }
        }
        return newPath;
    }

    public static void main(String[] args) {
        String md5 = "1674638378";
        String path = "/STORAGE/MAIN_CATALOGUE/139.pdf";
        ArrayList<Articles> listMd5 = new ArrayList<Articles>();
        Articles a = new Articles();
        Articles b = new Articles();
        Articles c = new Articles();
        a.setMd5("1355746470");
        a.setPath("/STORAGE/MAIN_CATALOGUE/126.pdf");
        b.setMd5("1615565949");
        b.setPath("/STORAGE/MAIN_CATALOGUE/127.pdf");
        c.setMd5("1674638377");
        c.setPath("/STORAGE/MAIN_CATALOGUE/128.pdf");
        listMd5.add(a);
        listMd5.add(b);
        listMd5.add(c);
        TestFileDuplicate test = new TestFileDuplicate();
        System.out.println(test.checkFileDuplicate(md5, path, listMd5));
    }
}
