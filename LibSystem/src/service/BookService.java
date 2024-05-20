package service;
import java.util.List;
import dao.BookDao;
import dao.UserDao;
import model.Book;
import model.User;

public class BookService {
    private BookDao bookDAO;
    private UserDao userDao;

    public BookService(BookDao bookDAO, UserDao userDao) {
        this.bookDAO = bookDAO;
        this.userDao=userDao;
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    public void deleteBook(int bookId) {
        bookDAO.deleteBook(bookId);
    }
    public List<Book> getAllAvailableBooks(){
        return  userDao.getAllAvailableBooks();
    }

    public void issueBook(int BookId){

        userDao.issueBook(BookId);
    }

    public void returnBook(int bookId) {
        userDao.returnBook(bookId);
    }
    public BookService(UserDao userDAO) {
        this.userDao = userDAO;
    }


    public void addUser(User user) {
        userDao.addUser(user);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void deleteUser(String userName) {
        userDao.deleteUser(userName);
    }

}