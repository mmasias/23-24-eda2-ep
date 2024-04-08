import java.util.HashMap;
import java.util.Map;

public class DocumentKeyword {
    private Map<Integer, Integer> documentKeywords;

    public DocumentKeyword() {
        documentKeywords = new HashMap<>();
    }

    public void addKeyword(int documentId, int keywordId) {
        documentKeywords.put(documentId, keywordId);
    }

    public int getKeyword(int documentId) {
        return documentKeywords.get(documentId);
    }

    public void removeKeyword(int documentId) {
        documentKeywords.remove(documentId);
    }

    public boolean containsDocument(int documentId) {
        return documentKeywords.containsKey(documentId);
    }

    public boolean containsKeyword(int keywordId) {
        return documentKeywords.containsValue(keywordId);
    }
}