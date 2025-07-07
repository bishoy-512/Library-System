import System.Library.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        int choice, intdata, ID, bookid, borrowerid;
        double doubledata;
        String Password;
        String stringdata;

        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\t\t"
                + "\t\t   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\t\t"
                + "\t\t @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\t\t"
                + "\t\t@@@@ -------> WELCOME to: <------- @@@@\n\t\t"
                + "\t\t@@@@-> Library Management System <-@@@@\n\t\t"
                + "\t\t @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\t\t"
                + "\t\t   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n\n");
        library.loadSystemState();
        while (true) {
            // Login Sign-up
            System.out.println("1 - Login\n2 - Sign-Up\n3 - Exit And Save");
            choice = scanner.nextInt();

            // If choose login
            if (choice == 1) {
                System.out.println("1 - Admin\n2 - User\n3 - Return Back");
                choice = scanner.nextInt();
                // Admin Choice
                if (choice == 1) {
                    System.out.println("Enter Your ID: ");
                    ID = scanner.nextInt();
                    System.out.println("Enter Your Password: ");
                    Password = scanner.next();
                    Admin admin = library.admin_logIn(ID, Password); // Check if ID and password correct or no

                    if (library.logged_in) {
                        while (true) {
                            System.out.println("\n--- Admin Menu ---");
                            System.out.println("1 - Mange Books\n2 - Mange Borrower\n3 - Generate Report\n4 - Logout");
                            choice = scanner.nextInt();

                            // If Mange Books
                            if (choice == 1) {
                                while (true) {
                                    System.out.println("1 - Add Book\n2 - Remove Book\n3 - Update Book Data\n4 - Search Book\n5 - Show All Books\n6 - Return Back");
                                    choice = scanner.nextInt();

                                    // Add New Book
                                    if (choice == 1) {
                                        admin.addBookFromUserInput(library);
                                    }
                                    // Remove Book
                                    else if (choice == 2) {
                                        System.out.println("Enter Book ID That You Need To Remove: ");
                                        intdata = scanner.nextInt();
                                        if (library.CheckBook(intdata)) {
                                            admin.removeBook(library, intdata);
                                        }
                                        else
                                            System.out.println("Book Doesn't Exist in library");
                                    }
                                    // Update Book
                                    else if (choice == 3) {
                                        System.out.println("Enter Book ID that You Need To Update: ");
                                        bookid = scanner.nextInt();

                                        // Check if the book exists or not
                                        if (library.CheckBook(bookid)) {
                                            System.out.println("1 - Change Price\n2 - Change Count");
                                            choice = scanner.nextInt();

                                            // Update Book Price
                                            if (choice == 1) {
                                                System.out.println("Enter Book New Price: ");
                                                doubledata = scanner.nextInt();
                                                library.updateBookPrice(bookid, doubledata);
                                                System.out.println("Price Updated");
                                            }
                                            // Update Book Count
                                            else if (choice == 2) {
                                                System.out.println("Enter Book New Count: ");
                                                intdata = scanner.nextInt();
                                                library.updateBookCount(bookid, intdata);
                                                System.out.println("Count Updated");
                                            } 
                                            else {
                                                System.out.println("Invalid Choice");
                                            }
                                        } else {
                                            System.out.println("Book doesn't exist in library");
                                        }
                                    }
                                    // Search For Book
                                    else if (choice == 4) {
                                        System.out.println("1 - Search Book With Author\n2 - Search Book With Title\n3 - Search Book With ID");
                                        choice = scanner.nextInt();

                                        // Search Book With Author
                                        if (choice == 1) {
                                            System.out.println("Enter Author: ");
                                            stringdata = scanner.next();
                                            library.searchAuthor(stringdata);
                                        }
                                        // Search Book With Title
                                        else if (choice == 2) {
                                            System.out.println("Enter Title: ");
                                            stringdata = scanner.next();
                                            library.searchBook(stringdata);
                                        }
                                        // Search Book With ID
                                        else if (choice == 3) {
                                            System.out.println("Enter ID: ");
                                            intdata = scanner.nextInt();
                                            library.searchBook(intdata);
                                        } 
                                        
                                        else {
                                            System.out.println("Invalid Choice");
                                        }
                                    }
                                    // Show All Books
                                    else if (choice == 5) {
                                        library.showAllBooks();
                                    }
                                    // Return Back
                                    else if (choice == 6) {
                                        break;
                                    } 
                                    else {
                                        System.out.println("Invalid Choice");
                                    }
                                } // End Admin Mange Books Loop
                            } // End Mange Book If

                            // Mange Borrowers
                            else if (choice == 2) {
                                while (true) {
                                    System.out.println("1 - Add Borrower\n2 - Remove Borrower\n3 - Update Borrower Data\n4 - Search Borrower\n5 - Show All Borrowers Data\n6 - Return Back");
                                    choice = scanner.nextInt();

                                    // Add Borrower
                                    if (choice == 1) {
                                        admin.addBorrower(library);
                                    }
                                    // Remove Borrower
                                    else if (choice == 2) {
                                        System.out.println("Enter Borrower ID That You Need To Remove: ");
                                        intdata = scanner.nextInt();
                                        admin.removeBorrower(library, intdata);
                                    }
                                    // Update Borrower
                                    else if (choice == 3) {
                                        System.out.println("Enter Borrower ID That You Need To Update: ");
                                        borrowerid = scanner.nextInt();

                                        // Check If Borrower Exists
                                        if (library.checkborrower(borrowerid)) {
                                            System.out.println("1 - Update Name\n2 - Update Phone Number\n3 - Update Password\n4 - Return Back");
                                            choice = scanner.nextInt();

                                            // Update Name
                                            if (choice == 1) {
                                                System.out.println("Enter New Name: ");
                                                stringdata = scanner.next();
                                                admin.updateBorrowerName(library, borrowerid, stringdata);
                                            }
                                            // Update Phone Number
                                            else if (choice == 2) {
                                                System.out.println("Enter New Phone Number: ");
                                                stringdata = scanner.next();
                                                admin.updateBorrowerPhone(library, borrowerid, stringdata);
                                            }
                                            // Update Password
                                            else if (choice == 3) {
                                                System.out.println("Enter New Password: ");
                                                stringdata = scanner.next();
                                                admin.updateBorrowerPassword(library, borrowerid, stringdata);
                                            }
                                            // Return Back
                                            else if (choice == 4) {
                                                continue;
                                            } else {
                                                System.out.println("Invalid Choice");
                                            }
                                        } else {
                                            System.out.println("Borrower Doesn't Exist");
                                        }
                                    }
                                    // Search Borrower
                                    else if (choice == 4) {
                                        System.out.println("Enter Borrower ID: ");
                                        intdata = scanner.nextInt();
                                        library.searchBorrower(intdata);
                                    }
                                    // Show All Borrowers Data
                                    else if (choice == 5) {
                                        library.showAllBorrowers();
                                    }
                                    // Return Back
                                    else if (choice == 6) {
                                        break;
                                    } 
                                    else {
                                        System.out.println("Invalid Choice");
                                    }
                                }
                            }
                            
                            else if (choice == 3){
                                library.generateReports();
                            }
                            
                            // Logout
                            else if (choice == 4) {
                                library.logOut();
                                break;
                            }
                            
                            else
                                System.out.println("Invalid Choice");
                        }
                    }
                } // End Admin Loop
                // User Choice
                else if (choice == 2) {
                    System.out.println("1 - Borrower\n2 - Customer\n3 - Return Back");
                    choice = scanner.nextInt();
                    // Borrower
                    if(choice == 1) {
                        System.out.println("Enter ID: ");
                        ID = scanner.nextInt();
                        System.out.println("Enter Password: ");
                        Password = scanner.next();
                        Borrower borrower = library.borrower_logIn(ID, Password);
                        if (library.logged_in) {
                            while (true) {
                                System.out.println("\n--- Borrower Menu ---");
                                System.out.println("1 - Show My Details\n2 - Borrow Book\n3 - Return Book\n4 - View History\n5 - Leave Review For Book\n6 - Search Book With title\n7 - Search Book With Author\n8 - Show All Books\n9 - Show All Available Books\n10 - Update Personal Information\n11 - Log Out");
                                choice = scanner.nextInt();
                                // Show Borrower Details
                                if (choice == 1) {
                                    library.showborrowerdata(ID);
                                }
                                // Borrow Book
                                else if (choice == 2) {
                                    while (true) {
                                        library.showAllBooks();
                                        System.out.println("Enter The Id Of The Book That You Need To Borrow\nPress 0 To Save");
                                        choice = scanner.nextInt();
                                        if (choice == 0)
                                            break;
                                        if (library.CheckBook(choice))
                                            borrower.borrowBook(library.searchBookById(choice));
                                        else
                                            System.out.println("Book Not Exist");
                                    }
                                }
                                // Return Book
                                else if (choice == 3) {
                                    System.out.println("Enter Book ID");
                                    choice = scanner.nextInt();
                                    borrower.return_book(library.searchBookById(choice));
                                }
                                //Show History
                                else if (choice == 4) {
                                    borrower.viewBorrowingHistory(library.findBorrowerById(ID));
                                }
                                // Leave Review
                                else if (choice == 5) {
                                    System.out.print("Enter Book ID to review: ");
                                    int reviewBookId = scanner.nextInt();
                                    Book bookToReview = library.searchBookById(reviewBookId);
                                    if (bookToReview != null) {
                                        scanner.nextLine();  // Consume the newline
                                        while (true) {
                                            System.out.print("Enter your rating (1-5): ");
                                            int rating = scanner.nextInt();
                                            if (rating <= 5 && rating >= 1) {
                                                scanner.nextLine();  // Consume the newline
                                                System.out.print("Enter your review text: ");
                                                String reviewText = scanner.nextLine();
                                                borrower.leaveBookReview(bookToReview, rating, reviewText);
                                                break;
                                            } else
                                                System.out.println("rating must be from 1 to 5 only");
                                        }
                                    } else {
                                        System.out.println("Book not found!");
                                    }
                                }
                                // Search Book With Title
                                else if (choice == 6) {
                                    System.out.println("Enter Book Title");
                                    stringdata = scanner.next();
                                    library.searchBook(stringdata);
                                }
                                // Search Book With Author
                                else if (choice == 7) {
                                    System.out.println("Enter Book Author");
                                    stringdata = scanner.next();
                                    library.searchAuthor(stringdata);
                                }
                                // Show All Books
                                else if (choice == 8) {
                                    library.showAllBooks();
                                }
                                // Show All Available Books
                                else if (choice == 9) {
                                    library.showAvailableBooks();
                                }
                                //update personal information
                                else if (choice == 10) {
                                    System.out.println("1. Update Name");
                                    System.out.println("2. Update phone");
                                    System.out.println("3. Update password");
                                    int ch = scanner.nextInt();
                                    String update;
                                    if(ch == 1){
                                        System.out.print("Name : ");
                                        update = scanner.next();
                                        borrower.updatePersonal(update,null,null);
                                    }
                                    else if(ch == 2){
                                        System.out.print("Phone : ");
                                        update = scanner.next();
                                        borrower.updatePersonal(null,update,null);
                                    }
                                    else if(ch == 3){
                                        System.out.print("Password : ");
                                        update = scanner.next();
                                        borrower.updatePersonal(null,null,update);
                                    }
                                    else
                                        System.out.println("Invalid Choice");
                                }
                                // Log Out
                                else if (choice == 11) {
                                    library.logOut();
                                    break;
                                }
                                
                                else
                                    System.out.println("Invalid Choice");
                            } // End Borrower Loop
                        }
                    }
                    // Customer
                    else if (choice == 2) {
                        System.out.println("Enter ID: ");
                        ID = scanner.nextInt();
                        System.out.println("Enter Password: ");
                        Password = scanner.next();
                        Customer customer = library.customer_login(ID,Password);
                        if(library.logged_in){
                            while (true) {
                                System.out.println("\n--- Customer Menu ---");
                                System.out.println("1. View Books");
                                System.out.println("2. Add Book to Cart");
                                System.out.println("3. View Cart Details");
                                System.out.println("4. Update Quantity in Cart");
                                System.out.println("5. Remove Book from Cart");
                                System.out.println("6. Place Order");
                                System.out.println("7. Add Review for a Book");
                                System.out.println("8. Show Reviews for a Book");
                                System.out.println("9. Show Customer Data");
                                System.out.println("10. View Order History");
                                System.out.println("11. Search Book With title");
                                System.out.println("12. Search Book With Author");
                                System.out.println("13. Clear Cart");
                                System.out.println("14. Update Personal Information");
                                System.out.println("15. Delete My Account");
                                System.out.println("16. Exit");
                                choice = scanner.nextInt();
                                //show all books
                                if (choice == 1) {
                                    library.showAllBooks();
                                }
                                //add to cart 
                                else if (choice == 2) {
                                    customer.addBooksToCart(library);
                                }
                                //view Cart Details
                                else if (choice == 3) {
                                    customer.cart.viewCartDetails();
                                }
                                //update quantity in cart
                                else if (choice == 4) {
                                    customer.updateBookQuantityInCart(library);
                                }
                                //remove book from cart
                                else if (choice == 5) {
                                    customer.removeBookFromCart(library);
                                }
                                //place Order
                                else if (choice == 6) {
                                    customer.placeOrder();
                                }
                                // rate book
                                else if (choice == 7) {
                                    customer.addReviewToBook(library);
                                }
                                //show book review
                                else if (choice == 8) {
                                    customer.showBookReviews(library);
                                }
                                //show my Data
                                else if (choice == 9) {
                                    System.out.println("Enter your ID to view your data");
                                    library.showcustomerdata(ID);
                                }
                                //view order history
                                else if (choice == 10) {
                                    customer.viewOrderHistory();
                                } 
                                //search book by title
                                else if (choice == 11) {
                                    System.out.println("Enter Book Title");
                                    stringdata = scanner.next();
                                    library.searchBook(stringdata);
                                }
                                //search book by Author
                                else if (choice == 12) {
                                    System.out.println("Enter Book Author");
                                    stringdata = scanner.next();
                                    library.searchAuthor(stringdata);
                                }
                                //clear
                                else if (choice == 13) {
                                    customer.cart.clearCart();
                                }
                                //Update personal Information
                                else if (choice == 14) {
                                    System.out.println("1. Update Name");
                                    System.out.println("2. Update phone");
                                    System.out.println("3. Update password");
                                    int ch = scanner.nextInt();
                                    String update;
                                    if(ch == 1){
                                        System.out.print("Name : ");
                                        update = scanner.next();
                                        customer.updatePersonal(update,null,null);
                                    }
                                    else if(ch == 2){
                                        System.out.print("Phone : ");
                                        update = scanner.next();
                                        customer.updatePersonal(null,update,null);
                                    }
                                    else if(ch == 3){
                                        System.out.print("Password : ");
                                        update = scanner.next();
                                        customer.updatePersonal(null,null,update);
                                    }
                                    else
                                        System.out.println("Invalid Choice");
                                }
                                //Delete my account
                                else if (choice == 15) {
                                    library.deleteCustomer(customer);
                                    break;
                                }
                                //Exit
                                else if (choice == 16) {
                                    System.out.println("Thank you for shopping!");
                                    customer.cart.clearCart();
                                    library.logOut();
                                    break;
                                }
                                
                                else
                                    System.out.println("Invalid choice. Please try again.");
                                }
                            }
                        }
                    // Return Back
                    else if (choice == 3)
                        continue;
                    else
                        System.out.println("Invalid Choice");
                }
                // Return Back
                else if (choice == 3)
                    continue;
                else
                    System.out.println("Invalid Choice");
            }
            // Sign-Up
            else if (choice == 2) {
                System.out.println("1 - Admin\n2 - Customer\n3 - Return Back");
                choice = scanner.nextInt();
                // Admin Sign Up
                if(choice == 1)
                    library.addAdminFromUserInput();
                // Customer Sign UP
                else if (choice == 2)
                    library.addCustomerFromUserInput();
                // Return Back
                else if (choice == 3)
                    continue;
                else
                    System.out.println("Invalid Choice");
            }
            // Exit
            else if (choice == 3) {
                System.out.println("Exiting...");
                library.saveSystemState();
                return;
            }
            else
                System.out.println("Invalid Choice");
        }
    }
}