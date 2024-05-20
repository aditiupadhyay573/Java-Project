package model;

public class Book {
    private int id;
    private String bookName;
    private String authorName;
    private String status;

    public Book(){

    }

    public Book(int id, String bookName, String authorName, String status){
        this.id = id;
        this.bookName = bookName;
        this.authorName = authorName;
        this.status = status;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
