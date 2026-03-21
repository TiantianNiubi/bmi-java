import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LibrarySystem {
    // Core Collections (matches technical requirements)
    private final List<Book> bookList;          // ArrayList: Store books in insertion order
    private final Set<String> uniqueGenres;     // HashSet: Store unique genres (auto-deduplicate)
    private final Map<Integer, Book> bookMap;   // HashMap: O(1) lookup by book ID

    // Constructor: Initialize all collections
    public LibrarySystem() {
        this.bookList = new ArrayList<>();
        this.uniqueGenres = new HashSet<>();
        this.bookMap = new HashMap<>();
    }

    /**
     * Add a book to the library (populates all 3 collections)
     * @param book Book object to add
     */
    public void addBook(Book book) {
        if (book == null) return;

        // 1. Add to ArrayList (preserve insertion order)
        bookList.add(book);
        // 2. Add to HashSet (auto-ignore duplicate genres)
        uniqueGenres.add(book.getGenre());
        // 3. Add to HashMap (ID as key for fast lookup)
        bookMap.put(book.getId(), book);

        System.out.println("✅ Book added successfully: " + book.getTitle());
    }

    /**
     * Remove books by title keyword (uses Iterator to avoid ConcurrentModificationException)
     * @param keyword Keyword to match in book title
     */
    public void removeBooksByKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            System.out.println("❌ Invalid keyword!");
            return;
        }

        System.out.println("\n🔍 Removing books with keyword: " + keyword);
        // Use Iterator for safe removal during iteration
        Iterator<Book> iterator = bookList.iterator();
        int removedCount = 0;

        while (iterator.hasNext()) {
            Book book = iterator.next();

            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {

                iterator.remove();

                bookMap.remove(book.getId());

                removedCount++;
                System.out.println("🗑️ Removed: " + book.getTitle());
            }
        }

        if (removedCount == 0) {
            System.out.println("ℹ️ No books found with the keyword: " + keyword);
        }
    }

    /**
     * Display the current status of all collections
     */
    public void displayStatus() {
        System.out.println("\n=====================================");
        System.out.println("        LIBRARY CURRENT STATUS");
        System.out.println("=====================================");

        System.out.println("\n📚 All Books (ArrayList - Insertion Order):");
        if (bookList.isEmpty()) {
            System.out.println("No books in library");
        } else {
            bookList.forEach(System.out::println);
        }

        System.out.println("\n🏷️ Unique Genres (HashSet - No Duplicates):");
        if (uniqueGenres.isEmpty()) {
            System.out.println("No genres available");
        } else {
            uniqueGenres.forEach(genre -> System.out.println("- " + genre));
        }


        System.out.println("\n🔑 Book ID Lookup (HashMap - O(1) Access):");
        if (bookMap.isEmpty()) {
            System.out.println("No books in ID map");
        } else {
            bookMap.forEach((id, book) -> System.out.println("ID " + id + ": " + book.getTitle()));
        }
        System.out.println("=====================================\n");
    }


    public static void main(String[] args) {

        LibrarySystem library = new LibrarySystem();


        library.addBook(new Book(101, "Java Programming", "Technology"));
        library.addBook(new Book(102, "Data Structures", "Technology"));
        library.addBook(new Book(103, "Harry Potter", "Fiction"));
        library.addBook(new Book(104, "The Great Gatsby", "Fiction"));
        library.addBook(new Book(105, "Python Basics", "Technology"));

        library.displayStatus();

        library.removeBooksByKeyword("java");
        library.removeBooksByKeyword("fiction");


        library.displayStatus();
    }
}