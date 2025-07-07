package System.Library;
import  java.util.Vector;
import java.util.Scanner;
import java.time.LocalDate;

public class Borrower extends Person {
    Library library = new Library();
    Vector<Transaction> transactions = new Vector<>();
    Vector<Review> reviews = new Vector<>();
    Vector<Notification> notifications = new Vector<>();
    Vector<Book> reservedBooks = new Vector<>();
    int borrowerId;
    public Borrower(String name, int id, String phone ,String password) {
        super(name, id, phone, password);
        this.borrowerId = id;
    }

    public Vector<Book> getReservedBooks() {
        return reservedBooks;
    }

    public void addReservedBook(Book book) {
        reservedBooks.add(book);
    }
    
    public Vector<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public void displayInfo() {
        System.out.printf("Name : %s\n", getName());
        System.out.printf("ID : %s\n", getId());
        System.out.printf("Phone : %s\n", getPhone());
        System.out.printf("Password : %s\n", getPassword());
        System.out.println("==========");
    }
    
    @Override
    public int getId(){
        return borrowerId;
    }
    
    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            Transaction tran = new Transaction(book, this,LocalDate.now());
            transactions.add(tran);
            book.count--;
            System.out.println("Book borrowed successfully!");
        } 
        else {
            System.out.println("Do you want to reserve this book?(y/n)");
            Scanner input = new Scanner(System.in);
            String ch = input.next();
            if(ch.equalsIgnoreCase("y")){
                String text = String.format("%s Notification\n[Reservation Complete : %s, we will send you ,when it Available]",
                        NotificationType.RESERVATION_CONFIRMATION, book.getTitle());
                System.out.println(text);
                reservedBooks.add(book);
            }
            else {
                System.out.println("Book is currently unavailable there is some recommended books: ");
                library.showAvailableBooks();
            }
        }
    }
    
    public void return_book(Book book) {
        boolean found = false;
        for (Transaction t : transactions) {
            if (t.getBook() == book && t.state == TransactionType.Open) {
                found = true;
                System.out.println("Book returned successfully!");
                t.returnBook(LocalDate.now());
                break;
            }
        }
        if(!found)
            System.out.println("Sorry, But you don't borrowed this book.");
    }
    
    public void viewBorrowingHistory(Borrower borrower){
        for (Transaction t : transactions) {
            if(t.getBorrower().getId() == getId()) {
                System.out.printf("Book Name : %s\n", t.getBook().getTitle());
                System.out.printf("Borrow Date : %s\n", t.getFromDate());
                System.out.printf("Returning Date : %s\n", t.getReturnDate());
                System.out.printf("Returned Date : %s\n", t.getToDate());
                System.out.printf("Book Fines : %s\n", t.getFine());
                System.out.println("==========");
            }
        }
    }

    public void leaveBookReview (Book book,int rating, String reviewText){
        Review review = new Review(book, this, rating, reviewText);
        //review.validateRate();
        book.addReview(review);
        reviews.add(review);
        System.out.println("Review added for book: " + book.getTitle());
    }

    public void displayNotifications() {
        checkOVERDUE_ALERT();
        checkDUE_DATE_REMINDER();
        checkReservedBook();
        if (notifications.isEmpty()) {
            System.out.println("No new notifications.");
        } 
        else {
            System.out.println("Here are your notifications:");
            for (Notification notification : notifications) {
                System.out.printf("[%s] %s - %s\n", notification.getDate(), notification.getType(), notification.getText());
            }
        }
    }
    
    public void addNotification(Book book, NotificationType type, String message) {
        Notification notification = new Notification(this, book, message, type, LocalDate.now());
        notifications.add(notification);
    }
    
    public void checkDUE_DATE_REMINDER(){
        for(Transaction transaction : transactions){
            if(transaction.state == TransactionType.Open && 
                    transaction.getFromDate().plusDays(6).equals(LocalDate.now())){
                String text = String.format("[You should return book : %s , Return date : %s, You have only 2 days]", 
                        transaction.getBook().getTitle(),transaction.getToDate());
                addNotification(transaction.getBook(),NotificationType.DUE_DATE_REMINDER, text);
            }
        }
    }

    public void checkOVERDUE_ALERT(){
        for(Transaction transaction : transactions){
            if(transaction.state == TransactionType.Open && transaction.userLate()){
                String text = String.format("[You should return book : %s , Return date : %s, Fines : %.2f]",
                        transaction.getBook().getTitle(),transaction.getToDate(), transaction.getFine());
                addNotification(transaction.getBook(),NotificationType.OVERDUE_ALERT, text);
            }
        }
    }
    
    public void checkReservedBook(){
        for(Book book : reservedBooks){
            if(book.isAvailable()){
                String text = String.format("[You should Pickup : %s, Is Available]", book.getTitle());
                addNotification(book,NotificationType.RESERVATION_AVAILABLE, text);
            }
        }
    }

    public void updatePersonal(String name, String phone, String password) {
        if (name != null) {
            setName(name);
            System.out.println("Updated Borrower name: " + name);
        }

        if (phone != null) {
            setPhone(phone);
            System.out.println("Updated phone number: " + phone);
        }

        if (password != null) {
            setPassword(password);
            System.out.println("Password Updated Successfully");
        }
    }
}