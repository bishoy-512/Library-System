package System.Library;

import java.util.Scanner;

public class Admin extends Person {
    public Admin(String name, int id,String phone, String password) {
        super(name, id,phone,password);
    }

    @Override
    public void displayInfo(){
        System.out.printf("Name : %s\n", getName());
        System.out.printf("ID : %s\n", getId());
        System.out.printf("Phone : %s\n", getPhone());
    }
    
    public void addBookFromUserInput(Library library) {
        library.addBookFromUserInput();
    }
    
    public void removeBook(Library library, int bookid) {
        library.removeBook(bookid);
    }
    
    public void addBorrower(Library library) {
        library.addBorrowerFromUserInput();
    }

    public void removeBorrower(Library library, int id) {
        library.removeBorrower(id);
    }
    
    public void updateBorrowerName(Library library, int id ,String name){
        if(library.checkborrower(id)) {
            library.updateBorrowerName(id , name);
        }
        else
            System.out.println("Borrower Doesn't Exist");
    }
    
    public void updateBorrowerPhone(Library library, int id ,String Phone){
        if(library.checkborrower(id)) {
            library.updateBorrowerPhone(id , Phone);
        }
        else
            System.out.println("Borrower Doesn't Exist");
    }
    
    public void updateBorrowerPassword(Library library, int id ,String Password){
        if(library.checkborrower(id)) {
            library.updateBorrowerPassword(id , Password);
        }
        else
            System.out.println("Borrower Doesn't Exist");
    }
}
