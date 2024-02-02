package org.example.week01.SixStreamsAPI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Book {
    private String title;
    private String author;
    private int publicationYear;
    private double rating;
    private int pages;

    public Book(String title, String author, int publicationYear, double rating, int pages) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.rating = rating;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author=" + author +
                ", publicationYear=" + publicationYear +
                ", rating=" + rating +
                ", pages=" + pages +
                '}';
    }
}


public class StreamsAPI {
    public static void main(String[] args) {

        // Create a list of books
        List<Book> books = new ArrayList<>();
        books.add(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 4.5, 180));
        books.add(new Book("To Kill a Mockingbird", "Harper Lee", 1960, 4.8, 281));
        books.add(new Book("1984", "George Orwell", 1949, 4.2, 328));
        books.add(new Book("Pride and Prejudice", "Jane Austen", 1813, 4.6, 432));
        books.add(new Book("The Catcher in the Rye", "J.D. Salinger", 1951, 4.0, 224));
        books.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954, 4.9, 1178));
        books.add(new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 1997, 4.7, 320));
        books.add(new Book("Moby-Dick", "Herman Melville", 1851, 4.4, 704));
        books.add(new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", 1979, 4.3, 224));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", 1937, 4.8, 310));

        // Calculate the average rating of all books
        double avgRating = books.stream()
                .mapToDouble(Book::getRating)
                .average()
                .orElse(0.0);
        System.out.println("Average rating: "+ avgRating);

        // Filter and display books published after a specific year

        List<Book> booksAfterYear = books.stream()
                .filter(book -> book.getPublicationYear()>1950)
                .collect(Collectors.toList());

        System.out.println("----------Books after 1950---------");
        booksAfterYear.forEach(book -> System.out.println(book));

        // Sort books by rating in descending order
        List<Book> sortedByRatingDes = books.stream()
                .sorted(Comparator.comparingDouble(Book::getRating).reversed())
                .collect(Collectors.toList());
        System.out.println("----------Books sorted by rating in descending order---------");
        sortedByRatingDes.forEach(book -> System.out.println(book.getTitle() + " " + book.getRating()));

        // Find and display the title of the highest-rated book
        System.out.println("----------Highest rated book---------");
        System.out.println(sortedByRatingDes.get(0).getTitle());

        // Group books by author and calculate average rating for each author
        System.out.println("----------Average rating for each author---------");
        books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.averagingDouble(Book::getRating)))
                .forEach((author, avgRating1) -> System.out.println(author + " " + avgRating1));

        // Calculate the total number of pages for all books
        System.out.println("----------Total number of pages for all books---------");
        int totalNumberOfPages = books.stream()
                .mapToInt(Book::getPages)
                .sum();
        System.out.println(totalNumberOfPages);


    }
}

