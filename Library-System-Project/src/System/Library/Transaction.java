package System.Library;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
public class Transaction {
    private static int transactionCounter = 1;
    private final int transactionId;
    private final Book book;
    private final Borrower borrower;
    private final LocalDate fromDate;
    private LocalDate toDate;
    private LocalDate returnDate;
    private double fine;
    TransactionType state;
    public Transaction(Book book, Borrower borrower, LocalDate fromDate){
        this.transactionId = transactionCounter++;
        this.book = book;
        this.borrower = borrower;
        this.fromDate = fromDate;
        this.toDate = fromDate.plusDays(8);
        this.returnDate = null;
        this.fine = 0.0;
        this.state = TransactionType.Open;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public double getFine() {
        return fine;
    }
    
    public int getTransactionId(){
        return transactionId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public Book getBook(){
        return book;
    }

    public boolean userLate() {
        return returnDate == null && LocalDate.now().isAfter(toDate);
    }
    
    public boolean returnLate() {
        return returnDate != null && returnDate.isAfter(toDate);
    }

    public void calculateFine(){
        try {
            long num_of_days = ChronoUnit.DAYS.between(toDate, returnDate);
            this.fine = num_of_days * 10;
        }
        catch(NullPointerException e){
            System.out.println("NullPointerException");
        }
        System.out.printf("You must pay : %.2f$\n", fine);
    }
    
    public void getPayment(){
        Scanner input = new Scanner(System.in);
        String credit_card;
        System.out.println("To pay cash please press 1 and to pay visa please press 2");
        int payOption = input.nextInt();
        boolean paymentDone = false;
        while(!paymentDone) {
            switch (payOption) {
                case 1:
                    System.out.println("Payment Done");
                    paymentDone = true;
                    break;
                case 2:
                    boolean b = true;
                    while (b) {
                        System.out.println("Enter Your Credit Card: ");
                        credit_card = input.next();
                        if (credit_card.length() == 16)
                            b = false;
                        else {
                            System.out.println("Invalid Credit Card!!");
                        }
                    }
                    System.out.println("Payment Done");
                    paymentDone = true;
                    break;
                default:
                    System.out.println("Invalid Way to Pay!!");
            }
        }
    }

    public void returnBook(LocalDate returnDate){
        this.returnDate = returnDate;
        this.state = TransactionType.Closed;
        book.count++;
        if(returnLate()) {
            calculateFine();
            getPayment();
        }
        else
            System.out.println("You are on the time");
        System.out.println("Transaction marked as returned: " + transactionId);
    }
}
