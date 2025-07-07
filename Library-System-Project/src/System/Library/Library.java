package System.Library;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Vector;

public class Library {
    public boolean logged_in = false;
    Vector<Book> bookInventory = new Vector<>();
    Vector<Customer> customers = new Vector<>();
    Vector<Borrower> borrowers = new Vector<>();
    Vector<Admin> admins = new Vector<>();
    
    // =============================================== Books Methods ================================================
    public void addBook(Book book) {
        boolean found = false;
        for (Book i : bookInventory) {
            if (i.getBookId() == book.getBookId()) { // Compare books by details
                i.count += book.count; // Increment the count of the existing book
                found = true;
                break;
            }
        }
        if (!found) {
            bookInventory.add(book); // Add a new book if no match is found
        }
        System.out.println("Book added to inventory: " + book.getTitle());
    }

    public void addBookFromUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();

        System.out.print("Enter Book Title: ");
        String title = scanner.next();

        System.out.print("Enter Author: ");
        String author = scanner.next();

        System.out.print("Enter Publication Year: ");
        String publicationYear = scanner.next();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Count: ");
        int count = scanner.nextInt();

        Book newBook = new Book(bookId, title, author, publicationYear, price, count);

        addBook(newBook);
    }

    public void searchBook(int id) {
        boolean f = false;
        for (Book book : bookInventory) {
            if (book.getBookId() == id) {
                book.display_book_details();
                f = true;
            }
        }
        if (!f)
            System.out.println("No Book With This ID Exist");
    }

    public boolean CheckBook(int id) {
        for (Book book : bookInventory) {
            if (book.getBookId() == id) {
                return true;
            }
        }
        return false;
    }
    
    public void searchBook(String title) {
        boolean f = false;
        for (Book book : bookInventory) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                book.display_book_details();
                f = true;
            }
        }
        if (!f)
            System.out.println("No Books With This Title Exist");
    }

    public void searchAuthor(String author) {
        boolean f = false;
        for (Book book : bookInventory) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                book.display_book_details();
                f = true;
            }
        }
        if (!f)
            System.out.println("No Books With This Author Exist");
    }

    public void removeBook(int bookid) {
        for (Book book : bookInventory) {
            if (book.getBookId() == bookid) {
                bookInventory.remove(book);
                System.out.println("Book removed");
                break;
            }
        }

    }

    public void updateBookPrice(int bookId, double newprice) {
        for (Book book : bookInventory) {
            if (book.getBookId() == bookId) {
                book.setPrice(newprice);
                break;
            }
        }
    }

    public void updateBookCount(int bookId, int count) {
        for (Book book : bookInventory) {
            if (book.getBookId() == bookId) {
                book.setCount(count);
                break;
            }
        }
    }

    public Book searchBookById(int id) {
        for (Book book : bookInventory) {
            if (book.getBookId() == id)
                return book;
        }
        return null;
    }

    public void showAvailableBooks() {
        for (Book book : bookInventory) {
            if (book.isAvailable()) {
                book.display_book_details();
                book.showingReviews();
                System.out.println("==========");
            }
        }
    }

    public void showAllBooks() {
        if(bookInventory.isEmpty())
            System.out.println("Inventory has no books yet");
        else {
            for (Book book : bookInventory) {
                book.display_book_details();
                book.showingReviews();
                System.out.println("==========");
            }
        }
    }

    private Book findBookByTitle(String title) {
        for (Book book : bookInventory) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }
    
    // =============================================== Borrower Methods ================================================
    
    public void addBorrower(Borrower borrower) {
        for (Borrower borrower1 : borrowers) {
            if (borrower1.getId() == borrower.getId()) {
                System.out.println("Failed To Add Borrower , Already Exist");
                break;
            }
        }
        borrowers.add(borrower);
        System.out.println("Borrower Added");
    }

    public void addBorrowerFromUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Borrower Name: ");
        String name = scanner.next();

        System.out.print("Enter ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter Phone Number: ");
        String Phone = scanner.next();

        System.out.print("Enter Password: ");
        String password = scanner.next();

        Borrower borrower = new Borrower(name, id, Phone, password);

        addBorrower(borrower);
    }

    public void removeBorrower(int id) {
        for (Borrower borrower : borrowers) {
            if (borrower.getId() == id) {
                borrowers.remove(borrower);
                System.out.println("Borrower Removed");
                return;
            }
        }
        System.out.println("Borrower Doesn't Exist");
    }

    public void searchBorrower(int id) {
        for (Borrower borrower : borrowers) {
            if (borrower.getId() == id) {
                System.out.println("Borrower Found");
                borrower.displayInfo();
                return;
            }
        }
        System.out.println("Borrower Doesn't Exist");
    }

    public boolean checkborrower(int id) {
        for (Borrower borrower : borrowers) {
            if (borrower.getId() == id)
                return true;
        }
        return false;
    }

    public void showborrowerdata(int id) {
        for (Borrower borrower : borrowers) {
            if (borrower.getId() == id) {
                borrower.displayInfo();
                break;
            }
        }
    }

    public void updateBorrowerName(int id, String name) {
        for (Borrower borrower : borrowers) {
            if (borrower.getId() == id) {
                borrower.setName(name);
                break;
            }
        }
        System.out.println("Name Updated");
    }

    public void updateBorrowerPhone(int id, String Phone) {
        for (Borrower borrower : borrowers) {
            if (borrower.getId() == id) {
                borrower.setPhone(Phone);
                break;
            }
        }
        System.out.println("Phone Number Updated");
    }

    public void updateBorrowerPassword(int id, String Password) {
        for (Borrower borrower : borrowers) {
            if (borrower.getId() == id) {
                borrower.setPassword(Password);
                break;
            }
        }
        System.out.println("Password Updated");
    }

    public void showAllBorrowers() {
        if(borrowers.isEmpty())
            System.out.println("No Borrowers in library");
        else {
            for (Borrower borrower : borrowers) {
                borrower.displayInfo();
                System.out.println("==========");
            }
        }
    }

    public Borrower borrower_logIn(int id, String password) {
        for (Borrower borrower : borrowers) {
            if (borrower.getId() == id) {
                if (borrower.getPassword().equals(password)) {
                    System.out.println("Logged In");
                    logged_in = true;
                    borrower.displayNotifications();
                    return borrower;
                } else {
                    System.out.println("Password is Wrong");
                    break;
                }
            }
        }
        System.out.println("User doesn't exist on the System");
        return null;
    }

    public Borrower findBorrowerById(int id) {
        for (Borrower borrower : borrowers) {
            if (borrower.getId() == id) {
                return borrower;
            }
        }
        return null;
    }
    // =============================================== Admin Methods ================================================

    public void addAdminFromUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Admin Name: ");
        String name = scanner.next();

        System.out.print("Enter ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter Phone Number: ");
        String Phone = scanner.next();

        System.out.print("Enter Password: ");
        String password = scanner.next();

        Admin admin = new Admin(name, id, Phone, password);

        add_admin(admin);
    }

    public Admin admin_logIn(int id, String password) {
        for (Admin admin : admins) {
            if (admin.getId() == id) {
                if (admin.getPassword().equals(password)) {
                    System.out.println("Logged In");
                    logged_in = true;
                    return admin;
                } else {
                    System.out.println("Password is Wrong");
                    break;
                }
            }
        }
        System.out.println("Admin doesn't exist on the System");
        return null;
    }

    public void add_admin(Admin admin) {
        if (!admins.contains(admin)) {
            admins.add(admin);
            System.out.println("Admin Added");
        } else
            System.out.println("Admin is Exist");
    }
    // =============================================== Customer Methods ================================================
    
    public void showcustomerdata(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customer.displayInfo();
                System.out.println("==========");
                break;
            }
        }
    }

    public void addCustomerFromUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Customer Name: ");
        String name = scanner.next();

        System.out.print("Enter ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter Phone Number: ");
        String Phone = scanner.next();

        System.out.print("Enter Password: ");
        String password = scanner.next();

        Customer customer = new Customer(name, id, Phone, password, new Cart());

        newCustomer(customer);
    }

    public Customer customer_login(int id, String password) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                if (customer.getPassword().equals(password)) {
                    System.out.println("Logged In");
                    logged_in = true;
                    return customer;
                } else {
                    System.out.println("Password is Wrong");
                    break;
                }
            }
        }
        System.out.println("User doesn't exist on the System");
        return null;
    }

    public void newCustomer(Customer customer) {
        for (Customer cus : customers) {
            if (cus.getCustomerId() == customer.getCustomerId()) {
                System.out.println("Customer already exist");
                return;
            }
        }
        customers.add(customer);
        System.out.println("Customer Added");
    }
    
    public void deleteCustomer(Customer customer) {
        for (Customer cus : customers) {
            if (cus.getCustomerId() == customer.getCustomerId()) {
                customers.remove(customer);
                System.out.println("Customer Deleted");
                logOut();
                return;
            }
        }
        System.out.println("Customer doesn't exist in System");
    }
    
    public Customer findCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }
    // =============================================== Library Methods ================================================

    public void generateReports() {
        System.out.println("Generating library report:");
        System.out.println("- Total books: " + bookInventory.size());
        System.out.println("- Total customers: " + customers.size());
        System.out.println("- Total borrowers: " + borrowers.size());
    }
    
    public void logOut() {
        if (logged_in) {
            logged_in = false;
            System.out.println("GoodBye\n");
        } else
            System.out.println("Your are already Logged Out");
    }

    //============================================ Read & Write From Files =============================================
    private void saveBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/System/Library/Files/books.txt"))) {
            for (Book book : bookInventory) {
                writer.write(book.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private void saveAdminsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/System/Library/Files/admins.txt"))) {
            for (Admin admin : admins) {
                writer.write(admin.getName() + "," + admin.getId() + "," + admin.getPhone() + "," + admin.getPassword());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving admins to file: " + e.getMessage());
        }
    }

    private void saveBorrowersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/System/Library/Files/borrowers.txt"))) {
            for (Borrower borrower : borrowers) {
                // Save borrower details
                writer.write(borrower.getName() + "," + borrower.getId() + "," + borrower.getPhone() + "," + borrower.getPassword());

                // Save reserved book IDs (if any)
                writer.write(",");
                Vector<Book> reservedBooks = borrower.getReservedBooks();
                for (int i = 0; i < reservedBooks.size(); i++) {
                    writer.write(String.valueOf(reservedBooks.get(i).getBookId()));
                    if (i < reservedBooks.size() - 1) {
                        writer.write(","); // Separator for book IDs
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving borrowers to file: " + e.getMessage());
        }
    }
    
    private void saveCustomersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/System/Library/Files/customers.txt"))) {
            for (Customer customer : customers) {
                writer.write(String.format("%s,%d,%s,%s",
                        customer.getName(),
                        customer.getId(),
                        customer.getPhone(),
                        customer.getPassword()));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving customers: " + e.getMessage());
        }
    }

    private void loadBooksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/System/Library/Files/books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                bookInventory.add(Book.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private void loadAdminsFromFile() {
        admins.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/System/Library/Files/admins.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Admin admin = new Admin(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3]);
                    admins.add(admin);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading admins from file: " + e.getMessage());
        }
    }

    private void loadBorrowersFromFile() {
        borrowers.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/System/Library/Files/borrowers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    Borrower borrower = new Borrower(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3]);
                    if (parts.length > 4 && !parts[4].isEmpty()) {
                        String[] bookIds = parts[4].split(",");
                        for (String bookId : bookIds) {
                            int id = Integer.parseInt(bookId);
                            Book reservedBook = searchBookById(id); // Assuming Library has a method to find books by ID
                            if (reservedBook != null) {
                                borrower.addReservedBook(reservedBook); // Assuming Borrower has a method to add reserved books
                            }
                        }
                    }
                    borrowers.add(borrower);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading borrowers from file: " + e.getMessage());
        }
    }

    private void loadCustomersFromFile() {
        customers.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/System/Library/Files/customers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    // Create new cart for each customer
                    Cart cart = new Cart();
                    Customer customer = new Customer(parts[0], Integer.parseInt(parts[1]),
                            parts[2], parts[3], cart);
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            if (!(e instanceof FileNotFoundException)) {
                System.out.println("Error loading customers: " + e.getMessage());
            }
        }
    }

    private void saveAllTransactions() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/System/Library/Files/transactions.txt"))) {
            for (Borrower borrower : borrowers) {
                for (Transaction transaction : borrower.getTransactions()) {
                    writer.write(String.format("%d,%d,%s,%s,%s,%s,%.2f,%s",
                            transaction.getTransactionId(),
                            transaction.getBorrower().getId(),
                            transaction.getBook().getTitle(),
                            transaction.getFromDate(),
                            transaction.getToDate(),
                            transaction.getReturnDate() != null ? transaction.getReturnDate() : "null",
                            transaction.getFine(),
                            transaction.state));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    private void loadAllTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/System/Library/Files/transactions.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {  // Verify we have all required fields
                    try {
                        // Find the borrower and book
                        int borrowerId = Integer.parseInt(parts[1]);
                        String bookTitle = parts[2];
                        Borrower borrower = findBorrowerById(borrowerId);
                        Book book = findBookByTitle(bookTitle);
                        if (borrower != null && book != null) {
                            LocalDate fromDate = LocalDate.parse(parts[3]);  // Borrow date
                            LocalDate toDate = LocalDate.parse(parts[4]);    // Default return date
                            LocalDate returnDate = parts[5].equals("null") ? null : LocalDate.parse(parts[5]); // Actual return date
                            Transaction transaction = new Transaction(
                                    book,
                                    borrower,
                                    fromDate
                            );
                            transaction.setToDate(toDate);
                            transaction.setReturnDate(returnDate);// Set actual return date if exists
                            transaction.setFine(Double.parseDouble(parts[6]));
                            transaction.state = TransactionType.valueOf(parts[7]);
                            borrower.transactions.add(transaction);
                        }
                    } catch (NumberFormatException | DateTimeParseException e) {
                        System.out.println("Error parsing transaction data: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            if (!(e instanceof FileNotFoundException)) {
                System.out.println("Error loading transactions: " + e.getMessage());
            }
        }
    }

    private void saveAllOrdersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/System/Library/Files/orders.txt"))) {
            for (Customer customer : customers) {
                for (Order order : customer.orders) {
                    StringBuilder orderLine = new StringBuilder();
                    orderLine.append(String.format("%d,%d,%s,%.2f,%.2f",
                            order.getOrderId(),
                            customer.getId(),
                            order.getOrderDate(),
                            order.getTotalPrice(),
                            order.getTotalPrice_Discount()));
                    for (Book book : order.getOrderedBooks()) {
                        orderLine.append(",").append(book.getBookId());
                    }
                    writer.write(orderLine.toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving orders: " + e.getMessage());
        }
    }

    private void loadAllOrdersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/System/Library/Files/orders.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {  // At least basic order info + one book
                    try {
                        int customerId = Integer.parseInt(parts[1]);
                        Customer customer = findCustomerById(customerId);
                        if (customer != null) {
                            // Create temporary cart for order creation
                            Cart tempCart = new Cart();
                            // Load books into temp cart
                            for (int i = 5; i < parts.length; i++) {
                                int bookId = Integer.parseInt(parts[i]);
                                Book book = searchBookById(bookId);
                                if (book != null) {
                                    tempCart.addToCartforload(book);
                                }
                            }
                            // Create order with loaded data
                            Order order = new Order(customer, tempCart);
                            // Add order to customer's orders
                            customer.orders.add(order);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing order data: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            if (!(e instanceof FileNotFoundException)) {
                System.out.println("Error loading orders: " + e.getMessage());
            }
        }
    }

    private void saveAllReviewsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/System/Library/Files/reviews.txt"))) {
            for (Borrower borrower : borrowers) {
                for (Review review : borrower.reviews) {
                    writer.write(String.format("%d,%s,%d,%d,%.2f,%s",
                            review.getReviewId(),
                            "BORROWER",
                            review.getReviewer().getId(),
                            review.getBook().getBookId(),
                            review.getRate(),
                            review.getReview()));
                    writer.newLine();
                }
            }

            for (Customer customer : customers) {
                for (Review review : customer.reviews) {
                    writer.write(String.format("%d,%s,%d,%d,%.2f,%s",
                            review.getReviewId(),
                            "CUSTOMER",
                            review.getReviewer().getId(),
                            review.getBook().getBookId(),
                            review.getRate(),
                            review.getReview()));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving reviews: " + e.getMessage());
        }
    }

    private void loadAllReviewsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/System/Library/Files/reviews.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    try {
                        int reviewId = Integer.parseInt(parts[0]);
                        String reviewerType = parts[1];
                        int reviewerId = Integer.parseInt(parts[2]);
                        int bookId = Integer.parseInt(parts[3]);
                        Book book = searchBookById(bookId);
                        if (book == null) continue;
                        Person reviewer;
                        if (reviewerType.equals("BORROWER")) {
                            reviewer = findBorrowerById(reviewerId);
                        } else {
                            reviewer = findCustomerById(reviewerId);
                        }
                        if (reviewer != null) {
                            double rate = Double.parseDouble(parts[4]);
                            String reviewText = parts[5];
                            Review review = new Review(book, reviewer, rate, reviewText);
                            book.addReview(review);

                            // Add review to reviewer's list
                            if (reviewer instanceof Borrower) {
                                ((Borrower) reviewer).reviews.add(review);
                            }
                            else if (reviewer instanceof Customer) {
                                ((Customer) reviewer).reviews.add(review);
                            }
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing review data: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            if (!(e instanceof FileNotFoundException)) {
                System.out.println("Error loading reviews: " + e.getMessage());
            }
        }
    }
    
    public void loadSystemState() {
        loadAdminsFromFile();
        loadBooksFromFile();
        loadCustomersFromFile();
        loadBorrowersFromFile();
        loadAllTransactions();
        loadAllOrdersFromFile();
        loadAllReviewsFromFile();
    }

    public void saveSystemState() {
        saveAdminsToFile();
        saveBooksToFile();
        saveCustomersToFile();
        saveBorrowersToFile();
        saveAllTransactions();
        saveAllOrdersToFile();
        saveAllReviewsToFile();
    }
}
