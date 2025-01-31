import java.util.Scanner;
import java.util.ArrayList;

class Book {
    String title, author;
    int bookId;
    boolean isAvailable;

    public Book() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter book ID: ");
        bookId = sc.nextInt();
        sc.nextLine(); // Consume newline left-over
        System.out.print("Title of the book: ");
        title = sc.nextLine();
        System.out.print("Author name: ");
        author = sc.nextLine();
        this.isAvailable = true; // Assuming the book is available when created
    }

    public void displayBookInfo() {
        System.out.println("Title: " + title + ", Author: " + author + ", Available: " + isAvailable);
    }

    public boolean borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    public void returnBook() {
        isAvailable = true;
    }
}

class Library {
    ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(int bookId) {
        books.removeIf(book -> book.bookId == bookId); // Using removeIf to safely remove books
    }

    public void listAvailableBooks() {
        for (Book book : books) {
            if (book.isAvailable) {
                book.displayBookInfo();
            }
        }
    }

    public Book findBook(String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

class Student {
    int studentId;
    String name;
    ArrayList<Book> borrowedBooks = new ArrayList<>();

    public Student() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        name = sc.nextLine();

        System.out.print("Enter your ID: ");
        studentId = sc.nextInt();
    }

    public void borrowBook(Book book) {
        if (book.isAvailable && borrowedBooks.size() < 3) {
            borrowedBooks.add(book);
            book.borrowBook();
        } else {
            System.out.println("The book is not available or you have reached your borrowing limit!");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            book.returnBook();
        } else {
            System.out.println("The book was not borrowed!");
        }
    }

    public void displayBorrowedBooks() {
        for (Book book : borrowedBooks) {
            book.displayBookInfo();
        }
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();
        Student student = new Student();
    }
}

