import java.util.List;
import java.util.Scanner;
import dao.BookDao;
import dao.UserDao;
import model.Book;
import model.User;
import service.BookService;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static UserDao userDao = new UserDao();
    private static BookDao bookDao = new BookDao();
    private static BookService bookService = new BookService(bookDao,userDao);

    private static boolean isAdmin = false;

    public static void main(String[] args) {
        System.out.println("Welcome to Library Management System");

        authenticateOrRegisterUser();

        while (true) {
            if (isAdmin) {
                displayAdminMenu();
                int adminChoice = scanner.nextInt();
                scanner.nextLine();
                handleAdminChoice(adminChoice);
            } else {
                displayUserMenu();
                int userChoice = scanner.nextInt();
                scanner.nextLine();
                handleUserChoice(userChoice);
            }
        }
    }

    private static void authenticateOrRegisterUser() {
        System.out.print("Are you a registered user? (yes/no): ");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("no")) {

            register();
        } else {
            login();
        }
    }

    private static void login() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (username.equals("admin") && password.equals("1518")) {
            isAdmin = true;
            System.out.println("Admin login successful!");
        } else {

            userDao.loginUser(username, password);
            System.out.println("User login successful!");

        }
    }

    private static void register() {
        System.out.print("Enter a username: ");
        String userName = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        User newUser = new User(userName, password);
        userDao.addUser(newUser);
        System.out.println("Registration successful! You can now log in.");
        login();
    }

    private static void displayAdminMenu() {
        System.out.println("Admin Menu:");
        System.out.println("1. Add new book");
        System.out.println("2. View all books");
        System.out.println("3. Update book details");
        System.out.println("4. Delete book");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void displayUserMenu() {
        System.out.println("User Menu:");
        System.out.println("1. View all books");
        System.out.println("2. Issue a book");
        System.out.println("3. Return a book");
        System.out.println("4. Update username or password");
        System.out.println("5. Delete account");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void handleAdminChoice(int adminChoice) {
        switch (adminChoice) {
            case 1:
                addBook();
                break;
            case 2:
                viewAllBooks();
                break;
            case 3:
                updateBookDetails();
                break;
            case 4:
                deleteBook();
                break;
            case 5:
                System.out.println("Exiting the program");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void handleUserChoice(int userChoice) {
        switch (userChoice) {
            case 1:
                viewAllAvailableBooks();
                break;
            case 2:
                issueBook();
                break;
            case 3:
                returnBook();
                break;
            case 4:
                updateUsernameOrPassword();
                break;
            case 5:
                deleteUser();
                break;
            case 6:
                System.out.println("Exiting the program");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void viewAllBooks() {
        List<Book> books = bookService.getAllBooks();

        System.out.println("Book List:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void addBook() {
        System.out.print("Enter book name: ");
        String bookName = scanner.nextLine();
        System.out.print("Enter author name: ");
        String authorName = scanner.nextLine();
        Book book = new Book(0, bookName, authorName, "not issued");
        bookService.addBook(book);
        System.out.println("Book added successfully!");
    }

    private static void updateBookDetails() {
        System.out.print("Enter the ID of the book to update: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new name for the book: ");
        String name = scanner.nextLine();
        System.out.print("Enter new author name: ");
        String author = scanner.nextLine();
        Book book = new Book(bookId, name, author, "not issued");
        bookService.updateBook(book);
        System.out.println("Book details updated successfully!");
    }

    private static void deleteBook() {
        System.out.print("Enter the ID of the book to delete: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        bookService.deleteBook(bookId);
        System.out.println("Book deleted successfully!");
    }

    private static void viewAllAvailableBooks() {
        List<Book> books = bookService.getAllAvailableBooks();

        System.out.println("Book List:");
        for (Book book : books) {
            System.out.println(book);
        }
    }
    private static void issueBook() {
        System.out.print("Enter the ID of the book to issue: ");
        int bookId = scanner.nextInt();
        bookService.issueBook(bookId);
        System.out.println("Book Issued Sucessfully");

    }

    private static void returnBook() {
        System.out.print("Enter the ID of the book to return: ");
        int bookId = scanner.nextInt();
        bookService.returnBook(bookId);
        System.out.println("Book Returned Sucessfully");
    }

    private static void updateUsernameOrPassword() {
        System.out.print("Enter your user Id to change username and password: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter your new username: ");
        String newUserName = scanner.nextLine();
        System.out.print("Enter your new password: ");
        String newPassword = scanner.nextLine();

        User user = new User(userId,newUserName, newPassword);
        userDao.updateUser(user);
        System.out.println("Username and password updated successfully!");
    }

    private static void deleteUser() {
        System.out.print("Enter your user name:  ");
        String userName = scanner.nextLine();
        userDao.deleteUser(userName);
        System.out.println("Account deleted successfully!");
        System.exit(0);
    }
}
