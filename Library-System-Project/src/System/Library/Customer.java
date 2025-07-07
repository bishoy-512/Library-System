package System.Library;
import java.util.Scanner;
import java.util.Vector;
import java.time.format.DateTimeFormatter;
public class Customer extends Person {
    public Cart cart;
    public Vector<Order> orders = new Vector<>();
    public Vector<Review> reviews = new Vector<>();
    int customerId;

    public Customer(String name, int id, String phone, String password, Cart cart) {
        super(name, id, phone, password);
        this.cart = cart;
        this.customerId = id;
    }

    @Override
    public void displayInfo() {
        System.out.printf("Name : %s\n", getName());
        System.out.printf("ID : %s\n", getId());
        System.out.printf("Phone : %s\n", getPhone());
        System.out.printf("Password : %s\n", getPassword());
    }

    @Override
    public int getId() {
        return customerId;
    }

    public void updatePersonal(String name, String phone, String password) {
        if (name != null) {
            setName(name);
            System.out.println("Updated Customer name: " + name);
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

    public int getCustomerId() {
        return customerId;
    }
    
    public void viewOrderHistory() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Order Date: " + order.getOrderDate().format(formatter));
            System.out.println("Order Total Price: " + order.getTotalPrice_Discount());
            System.out.println("Order Books:....");
            for (Book book : order.getOrderedBooks())
                System.out.println("- " + book.getTitle());
        }
    }
    
    public void placeOrder() {
        if (cart.items.isEmpty()) {
            System.out.println("Cart is empty. Cannot place order.");
            return;
        }
        Order order = new Order(this, cart);
        order.generateInvoice();
        order.getPayment();
        orders.add(order);
        cart.clearCart();
    }

    public void showBookReviews(Library library) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Book ID to show reviews: ");
        int bookId = scanner.nextInt();
        Book book = library.searchBookById(bookId);

        if (book != null) {
            book.showingReviews();  // Display reviews for the selected book
        } else {
            System.out.println("Book not found.");
        }
    }

    public void addReviewToBook(Library library) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Book ID to review:");
        int bookId = scanner.nextInt();
        Book book = library.searchBookById(bookId);
        if (book == null) {
            System.out.println("Book not found!");
            return;
        }
        System.out.println("Enter your review text:");
        scanner.nextLine();
        String reviewText = scanner.nextLine();
        Review review = new Review(book, this, 0, reviewText);
        review.validateRate();
        double validRating = review.getRate();
        review = new Review(book, this, validRating, reviewText);
        book.addReview(review);
        reviews.add(review);
        System.out.println("Review added successfully!");
        System.out.println("\n");
        System.out.println("Thank you for your review!");
        System.out.println("\n");
        while (true) {
            System.out.println("Press 0 to go back to the main menu.");
            int backOption = scanner.nextInt();
            if (backOption == 0) {
                break;
            } else {
                System.out.println("Invalid input. Please press 0 to go back.");
            }
        }
    }
    
    public void addBooksToCart(Library library) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Available Books:");
            library.showAvailableBooks();
            System.out.print("Enter Book ID to add to cart: ");
            int bookId = scanner.nextInt();
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            Book bookToAdd = library.searchBookById(bookId);
            if (bookToAdd != null && bookToAdd.isAvailable()) {
                if (bookToAdd.count >= quantity) {
                    for (int i = 0; i < quantity; i++) {
                        cart.addToCart(bookToAdd);
                    }
                    System.out.println(quantity + " copies of " + bookToAdd.getTitle() + " added to cart.");
                } else {
                    System.out.println("The Available Quantity is " + bookToAdd.count);
                    while (true) {
                        System.out.print("Enter quantity: ");
                        quantity = scanner.nextInt();
                        if (bookToAdd.count >= quantity) {
                            for (int i = 0; i < quantity; i++) {
                                cart.addToCart(bookToAdd);
                            }
                            System.out.println(quantity + " copies of " + bookToAdd.getTitle() + " added to cart.");
                            break;
                        } else {
                            System.out.println("The Available Quantity is " + bookToAdd.count);
                        }
                    }
                }
            } else {
                System.out.println("Book not available or invalid ID.");
            }
            System.out.println("Press 1 to add another book or 0 to go back to the main menu.");
            int nextAction = scanner.nextInt();
            if (nextAction == 0) {
                break;
            } else if (nextAction != 1) {
                System.out.println("Invalid input. Returning to the main menu.");
                break;
            }
        }
        cart.applyDiscount();
    }
    public void updateBookQuantityInCart(Library library) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Book ID to update quantity: ");
        int updateBookId = scanner.nextInt();
        System.out.print("Enter new quantity: ");
        int newQuantity = scanner.nextInt();
    
        Book bookToUpdate = library.searchBookById(updateBookId);
        if (bookToUpdate != null) {
            cart.updateQuantity(bookToUpdate, newQuantity);
            System.out.println("Quantity updated successfully.");
        } else {
            System.out.println("Book not found in cart.");
        }
    }
    public void removeBookFromCart(Library library) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Book ID to remove from cart: ");
        int removeBookId = scanner.nextInt();
        System.out.print("Enter quantity to remove: ");
        int quantity = scanner.nextInt();
    
        Book bookToRemove = library.searchBookById(removeBookId);
        if (bookToRemove != null) {
            cart.removeFromCart(bookToRemove, quantity);
            cart.applyDiscount();
            System.out.println(quantity + " copies of " + bookToRemove.getTitle() + " removed from cart.");
        } else {
            System.out.println("Book not found in cart.");
        }
    }


}
