package ua.miratech.rudenko.docstore.textIndex;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by RomanR on 2/13/14.
 */
@Component
public class SearchQuery {

    private SearchFiles searchFiles = new SearchFiles();

    public List<String> foundPathList(String indexDir, String queryStr, Integer maxHits) {
        return searchFiles.searchIndex(indexDir, queryStr, maxHits);
    }

    public List<String> extFoundPathList(String indexDir, String stringAuthor, String stringSubject, String stringTitle,
                                         String stringKeywords, String stringContent, Integer maxHits) {
        return searchFiles.extSearchIndex(indexDir, stringAuthor, stringSubject, stringTitle, stringKeywords,
                stringContent, maxHits);
    }
}
