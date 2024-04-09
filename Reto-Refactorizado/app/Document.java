package app;

import java.util.ArrayList;

public class Document {
    private int id;
    private String title;
    private int publishingYear;
    private DocumentType documentType;
    private ArrayList<String> keyWords = new ArrayList<>();

    public Document(int id, String title, int publishingYear, DocumentType documentType, ArrayList<String> keyWords ) {
        this.id = id;
        this.title = title;
        this.publishingYear = publishingYear;
        this.documentType = documentType;
        this.keyWords = keyWords;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int year) {
        this.publishingYear = year;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public ArrayList<String> getKeyWords() {
        return keyWords;
    }

    public int searchKeyWordIndex(String word) {
        int index = keyWords.indexOf(word);
        return index;
    }

    private String toStringKeyWords() {
        StringBuilder sb = new StringBuilder();
        for (String kW : keyWords) {
            sb.append(" | ");
            sb.append(kW);
            sb.append(" | ");
        }

        return sb.toString();
    }

    
    public String toStringDocument() {
        String words = toStringKeyWords();
        String fullDocumentInformation = "Document's Information: Id - " + id + ", Title - " + title + ", Publishing Year - " 
                                        + publishingYear + ", Type - " + documentType.getDocumentTypeName() + ", Key Words " + words;

        return fullDocumentInformation;
    }
}
