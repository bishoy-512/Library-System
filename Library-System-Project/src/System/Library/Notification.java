package System.Library;
import java.time.LocalDate;
public class Notification {
    private Book book;
    private Borrower borrower;
    private String text;
    private NotificationType type;
    private LocalDate date;
    
    public Notification (Borrower borrower, Book book, String text, NotificationType type, LocalDate date){
        this.book = book;
        this.borrower = borrower;
        this.text = text;
        this.type = type;
        this.date = date;
    }

    public NotificationType getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public Book getBook() {
        return book;
    }
    
    public LocalDate getDate() {
        return date;
    }
}