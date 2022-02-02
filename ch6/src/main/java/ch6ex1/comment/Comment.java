package ch6ex1.comment;

public class Comment {

    private final String text;

    private final String author;


    public Comment(String text, String author) {
        this.text = text;
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }
}
