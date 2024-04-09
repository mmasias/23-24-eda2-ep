import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;

import Types.DocType;
import Types.KeyWordTypes;

public class DocumentGenerator {
    static Random random = new Random();

    public Document generateRandomDocument(int index) {
        Document document = new Document(index);

        String[] titles = {"El arte de programar en Java", "Historia del mundo", "Introducción a la inteligencia artificial"};
        document.setTitle(titles[random.nextInt(titles.length)]);

        Author[] authors = {
            new Author("John Doe"),
            new Author("Jane Smith"),
            new Author("Alice Johnson")
        };
        document.setAuthor(authors[random.nextInt(authors.length)]);

        ArrayList<KeyWordTypes> keyWords = new ArrayList<>();
        int numTypes = DocType.values().length;
        int numKeywords = random.nextInt(numTypes); 
        for (int i = 0; i < numKeywords; i++) {
            KeyWordTypes keyword = KeyWordTypes.values()[random.nextInt(KeyWordTypes.values().length)];
            keyWords.add(keyword);
        }
        document.setKeyWords(keyWords);

        long millisSinceEpoch = random.nextLong() % (System.currentTimeMillis() - Date.valueOf("2000-01-01").getTime());
        Date date = new Date(millisSinceEpoch);
        document.setDatePublished(date);

        DocType[] types = DocType.values();
        document.setType(types[random.nextInt(types.length)]);

        return document;
    }
}
