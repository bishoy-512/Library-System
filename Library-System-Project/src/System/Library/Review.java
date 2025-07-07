package System.Library;
import java.util.Scanner;

public class Review {
    private static int reviewCounter = 1;
    private final int reviewId;
    private final Person reviewer;
    private double rate;
    private String review;
    private final Book book;
    public Review(Book book, Person reviewer, double rate, String review){
        this.reviewId = reviewCounter++;
        this.book = book;
        this.reviewer = reviewer;
        this.rate = rate;
        this.review = review;
    }

    public String getReview() {
        return review;
    }

    public double getRate() {
        return rate;
    }

    public Person getReviewer() {
        return reviewer;
    }

    public Book getBook() {
        return book;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void displayReviewDetails() {
        System.out.println("Review ID: " + reviewId);
        System.out.println("Book ID: " + book.getBookId());
        System.out.println("Book Title: " + book.getTitle());
        System.out.println("Reviewer: " + reviewer.getName());
        System.out.println("Rating: " + rate);
        System.out.println("Review: " + review);
    }
    
    public void validateRate(){
        double rating;
        do {
            System.out.println("Enter your rating (1-5):");
            Scanner scanner = new Scanner(System.in);
            rating = scanner.nextDouble();

            if (rating < 1 || rating > 5) {
                System.out.println("Invalid rating! Please enter a value between 1 and 5.");
            }
        } while (rating < 1 || rating > 5);
        this.rate = rating;
    }
}