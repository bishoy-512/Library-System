package System.Library;
import java.util.Scanner;
import java.util.Vector;
import java.time.LocalDateTime;
public class Order {
    private static int allOrders = 1;
    private final int orderId;
    private final Customer customer;
    private final Vector<Book> orderedBooks = new Vector<>();
    private double totalPrice;
    private double totalPrice_Discount;
    private final LocalDateTime orderDate;
    public Order(Customer customer, Cart cart){
        this.orderId = allOrders++;
        this.customer = customer;
        this.totalPrice = cart.getTotalPrice();
        this.totalPrice_Discount = cart.total_price_Discount;
        this.orderDate = LocalDateTime.now();
        this.orderedBooks.addAll(cart.getItems());
    }
    public double getTotalPrice_Discount(){
        return totalPrice_Discount;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Vector<Book> getOrderedBooks() {
        return orderedBooks;
    }
    
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    
    public void generateInvoice() {
        System.out.println("Invoice for Order ID: " + orderId);
        Vector<Book> processedBooks = new Vector<>();
        for (Book book : orderedBooks) {
            if (!processedBooks.contains(book)) {
                int quantity = 0;
                for (Book b : orderedBooks) {
                    if (b.equals(book)) {
                        quantity++;
                    }
                }
                double totalBookPrice = book.getPrice() * quantity;
                System.out.printf("- %s: Quantity: %d, Price per book: $%.2f, Total Price: $%.2f\n",
                        book.getTitle(), quantity, book.getPrice(), totalBookPrice);
                processedBooks.add(book);
            }
        }
        System.out.printf("- Total Price: %.2f\n", totalPrice);
        System.out.printf("- After Discount: %.2f\n", totalPrice_Discount);
    }

    public void getPayment() {
        Scanner input = new Scanner(System.in);
        String creditCard;
        String password;
        System.out.println("To pay cash please press 1 and to pay visa please press 2");
        int payOption = input.nextInt();
        boolean paymentDone = false;

        while (!paymentDone) {
            switch (payOption) {
                case 1:
                    System.out.println("Payment Done with Cash");
                    paymentDone = true;
                    break;

                case 2:
                    boolean validInput = false;

                    while (!validInput) {
                        System.out.print("Enter Your Credit Card Number (16 digits): ");
                        creditCard = input.next();
                        System.out.print("Enter Your Password (4 digits): ");
                        password = input.next();


                        if (creditCard.length() == 16 && creditCard.matches("\\d{16}") && password.length() == 4 && password.matches("\\d{4}")) {
                            validInput = true;
                        } else {
                            System.out.println("Invalid Credit Card or Password! "
                                    + "Credit Card must be 16 digits, and Password must be 4 digits.");
                        }
                    }

                    System.out.println("Payment Done with Visa");
                    paymentDone = true;
                    break;

                default:
                    System.out.println("Invalid Way to Pay!! Please try again.");
                    System.out.println("To pay cash please press 1 and to pay visa please press 2");
                    payOption = input.nextInt();
                    break;
            }
        }
    }
}
