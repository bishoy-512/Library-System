package System.Library;
import java.util.Vector;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String publicationYear;
    private double price;
    public int count;
    private double rate;
    Vector<Review> reviews = new Vector<>();
    public Book(int bookId,String title, String author, String publicationYear, double price , int count) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.price = price;
        this.count = count;
        this.rate = 0.0;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTitle(String title){this.title = title;}

    public void setBookId(int id){this.bookId = id;}

    public void setAuthor(String author){this.author = author;}

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public boolean isAvailable() {
        return count > 0;
    }

    public double getPrice() {
        return price;
    }

    public double getRate(){
        return rate;
    }

    public void display_book_details(){
        System.out.println("Book ID: " + getBookId() +" , Book Title: " + getTitle()+" , Author: " + getAuthor()+" ,Publication Year : " + getPublicationYear() + " , Price: " + getPrice() + " , Count: " + count);
    }

    public void calculate_average_rating(){
        int cnt = reviews.size();
        double total_rate = 0;
        for(Review r : reviews){
            if(r != null)
                total_rate += r.getRate();
        }
        this.rate = total_rate / cnt;
    }

    public void addReview(Review review){
        reviews.add(review);
    }

    public void showingReviews(){
        if(reviews.isEmpty()) {
            System.out.println("Book didn't rated before");
            return;
        }
        calculate_average_rating();
        System.out.printf("Average Rate for Book: %.1f\n" , this.rate);
        for (Review review : reviews){
            System.out.println("User rate: "+ review.getRate());
            System.out.println("User review: " + review.getReview());
        }
    }

    @Override
    public String toString() {
        // Format: bookId,title,author,publicationYear,price,count
        return bookId + "," + title + "," + author + "," + publicationYear + "," + price + "," + count + "," + rate;
    }

    public static Book fromString(String data) {
        String[] parts = data.split(",");
        int bookId = Integer.parseInt(parts[0]);
        String title = parts[1];
        String author = parts[2];
        String publicationYear = parts[3];
        double price = Double.parseDouble(parts[4]);
        int count = Integer.parseInt(parts[5]);
        double rate = Double.parseDouble(parts[6]);
        Book book = new Book(bookId, title, author, publicationYear, price, count);
        book.rate = rate;
        return book;
    }
}
