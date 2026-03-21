public class Book {
    // Book attributes
    private int id;
    private String title;
    private String genre;

    // Constructor
    public Book(int id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }

    // Getters (essential for accessing private fields)
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    // Override toString() for readable printing
    @Override
    public String toString() {
        return "Book{id=" + id + ", title='" + title + "', genre='" + genre + "'}";
    }
}