package ua.miratech.rudenko;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.miratech.rudenko.docstore.controller.ArticlesController;
import ua.miratech.rudenko.docstore.service.ArticlesService;


/**
 * Created by RomanR on 2/14/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
@ContextConfiguration( "file:src/main/webapp/WEB-INF/applicationContext.xml")
public class ArticlesTests {

    @Autowired
    ArticlesService articlesService;
    @Autowired
    ArticlesController articlesController;

    @Test
    public void testFileDuplicate() {
        String md5 = "42734362";
        String path = "/STORAGE/MAIN_CATALOGUE/139.pdf";

//        assertEquals("/STORAGE/MAIN_CATALOGUE/132.pdf", articlesController.checkFileDuplicate(md5, path));
    }
}
