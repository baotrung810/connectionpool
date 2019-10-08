
package Entity;

public class BookDetailEntity {
   private int id;
   private String isbn;
   private int numberOfPage;
   private double price;
   //
   private BookEntity bookEntity;

    public BookDetailEntity() {
    }

    public BookDetailEntity(int id, String isbn, int numberOfPage, double price) {
        this.id = id;
        this.isbn = isbn;
        this.numberOfPage = numberOfPage;
        this.price = price;
    }

    public BookDetailEntity(String isbn, int numberOfPage, double price) {
        this.isbn = isbn;
        this.numberOfPage = numberOfPage;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }
   
  
}