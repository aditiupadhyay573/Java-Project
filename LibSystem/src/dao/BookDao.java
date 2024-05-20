package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Book;

public class BookDao {

    private static final String URL = "jdbc:mysql://localhost:3306/rwi";
    private static final String USER = "root";
    private static final String PASSWORD = "WJ28@krhps";

    private Connection connection;

    public BookDao() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try {
            String query = "SELECT * FROM books";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Book book = new Book(resultSet.getInt("id"),
                            resultSet.getString("bookName"),
                            resultSet.getString("authorName"),
                            resultSet.getString("status"));
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public void addBook(Book book) {
        try {
            String query = "INSERT INTO books (bookName, authorName, status) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, book.getBookName());
                statement.setString(2, book.getAuthorName());
                statement.setString(3, book.getStatus());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(Book book) {
        try {
            String query = "UPDATE books SET bookName = ?, authorName = ?, status = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, book.getBookName());
                statement.setString(2, book.getAuthorName());
                statement.setString(3, book.getStatus());
                statement.setInt(4, book.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int bookId) {
        try {
            String query = "DELETE FROM books WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, bookId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
