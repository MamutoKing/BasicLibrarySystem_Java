public class Book implements LibraryItem {
    
    //Private fields protect book's data
    private int id;
    private String title; 
    private String author;
    private String isbn;
    private int pages;

    //Constructor with default values
    public Book(){

        this.id = 0;
        this.title = "";
        this.author = "";
        this.isbn = "";
        this.pages = 0;
    }

    //Parameterized constructor to initialize book's data
    public Book(int id, String title, String author, String isbn, int pages) {

        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.pages = pages;
    }

    //Getters to read private fields
    @Override 
    public int getId() {
        return this.id;
    }

    @Override 
    public String getTitle() {
        return this.title;
    }

    
    public String getAuthor() {
        return this.author;
    }

    
    public String getIsbn() {
        return this.isbn;
    }

    
    public int getPages() {
        return this.pages;
    }

    //Setters to update private fields
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    //Display all information about the book
    @Override
    public void printBookInfo() {
        
        System.out.println("            Book Information:");
        System.out.println("---------------------------------------");
        System.out.println("Book ID: " + this.id);
        System.out.println("Title: " + this.title);
        System.out.println("Author: " + this.author);
        System.out.println("ISBN: " + this.isbn);
        System.out.println("Pages: " + this.pages);
    }
}
