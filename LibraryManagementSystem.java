// Abstract class representing a library item
abstract class LibraryItem implements Reservable {
    private String itemId;
    private String title;
    private String author;
    private boolean isReserved;

    LibraryItem(String itemId, String title, String author) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
        this.isReserved = false;
    }

    public void getItemDetails() {
        System.out.println("Item ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Reserved: " + (isReserved ? "Yes" : "No"));
    }

    public void reserveItem() {
        if (!isReserved) {
            isReserved = true;
            System.out.println(title + " has been reserved.");
        } else {
            System.out.println(title + " is already reserved.");
        }
    }

    public boolean checkAvailability() {
        return !isReserved;
    }

    abstract int getLoanDuration(); // Must be implemented by subclasses
}

// Interface for reservable items
interface Reservable {
    void reserveItem();
    boolean checkAvailability();
}

// Subclass for books
class Book extends LibraryItem {
    Book(String itemId, String title, String author) {
        super(itemId, title, author);
    }

    @Override
    int getLoanDuration() {
        return 14; // Books can be borrowed for 14 days
    }
}

// Subclass for magazines
class Magazine extends LibraryItem {
    Magazine(String itemId, String title, String author) {
        super(itemId, title, author);
    }

    @Override
    int getLoanDuration() {
        return 7; // Magazines can be borrowed for 7 days
    }
}

// Subclass for DVDs
class DVD extends LibraryItem {
    DVD(String itemId, String title, String author) {
        super(itemId, title, author);
    }

    @Override
    int getLoanDuration() {
        return 5; // DVDs can be borrowed for 5 days
    }
}

// Main class to test the system
public class LibraryManagementSystem {
    public static void main(String[] args) {
        LibraryItem book = new Book("B101", "The Great Gatsby", "F. Scott Fitzgerald");
        LibraryItem magazine = new Magazine("M202", "National Geographic", "Various Authors");
        LibraryItem dvd = new DVD("D303", "Inception", "Christopher Nolan");

        LibraryItem[] items = {book, magazine, dvd};

        for (LibraryItem item : items) {
            item.getItemDetails();
            System.out.println("Loan Duration: " + item.getLoanDuration() + " days");
            item.reserveItem();
            System.out.println("Available: " + (item.checkAvailability() ? "Yes" : "No"));
            System.out.println("--------------------");
        }
    }
}
