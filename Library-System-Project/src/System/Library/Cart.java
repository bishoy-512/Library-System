package System.Library;
import java.util.Vector;

public class Cart {
    Vector<Book> items = new Vector<>();
    int total_quantity;
    double total_price;
    double total_price_Discount;
    public Cart () {
        this.total_quantity = 0;
        this.total_price = 0.0;
        this.total_price_Discount = 0.0;
    }

    public Vector<Book> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return total_price;
    }

    public double getTotal_price_Discount() {
        return total_price_Discount;
    }

    public void addToCartforload(Book book) {
        items.add(book);
        calculateTotal();
        applyDiscount();
    }
    
    public void addToCart(Book book) {
        if(book.isAvailable()) {
            items.add(book);
            book.count--;
            calculateTotal();
            applyDiscount();
        }
    }

    public void removeFromCart(Book book, int quantity) {
        int currentQuantity = (int) items.stream().filter(x -> x.equals(book)).count();

        if (quantity > currentQuantity) {
            System.out.printf("You only have %d of %s in the cart. Cannot remove %d items.%n", currentQuantity, book.getTitle(), quantity);
            return;
        }

        for (int i = 0; i < quantity; i++) {
            items.remove(book);
            book.count++;
        }

        System.out.printf("Removed %d of %s from the cart.%n", quantity, book.getTitle());
        calculateTotal();
        applyDiscount();
    }

    public void updateQuantity(Book book, int quantity) {
        if (quantity < 0) {
            System.out.println("Quantity Can't be Negative");
            return;
        }
        // Current quantity in the cart
        int currentQuantity = (int) items.stream().filter(x -> x.equals(book)).count();
        // Update the book's count in the inventory
        book.count += currentQuantity - quantity;
        // Remove all instances of the specified book from the cart
        items.removeIf(x -> x.equals(book));
        // Add the updated quantity of the book to the cart
        for (int i = 0; i < quantity; i++) {
            items.add(book);
        }
        // Display confirmation
        System.out.println("Updated cart: " + book.getTitle() + ", Quantity: " + quantity);
        // Recalculate the total and apply discount
        calculateTotal();
        applyDiscount();
    }

    public void viewCartDetails(){
        Vector<Book> processedBooks = new Vector<>();
        for (Book book : items) {
            if (!processedBooks.contains(book)) {
                int quantity = 0;
                for (Book b : items) {
                    if (b.equals(book)) {
                        quantity++;
                    }
                }
                System.out.printf("Book: %s, Quantity: %d%n", book.getTitle(), quantity);
                processedBooks.add(book);
            }
        }
        System.out.printf("Total Cart Quantity : %d\n", this.total_quantity);
        System.out.printf("Total Cart Price Before Discount : %.2f\n", this.total_price);
        System.out.printf("Total Cart Price After Discount : %.2f\n", this.total_price_Discount);
    }

    public void applyDiscount(){
        if(this.total_quantity >= 5){
            this.total_price_Discount = this.total_price - (this.total_price * 0.1f);
        }
        else
            this.total_price_Discount = this.total_price;
    }

    public void calculateTotal(){
        this.total_price=0.0;
        this.total_quantity = items.size();
        for(Book i : items){
            this.total_price += i.getPrice();
        }

    }

    public void clearCart() {
        items.clear();
        total_price = 0.0;
        total_price_Discount = 0.0;
        total_quantity = 0;
        System.out.println("Cart cleared.");
    }
}