import java.util.InputMismatchException;
import java.util.Scanner;

//Main class coordinates user interaction
public class LibrarySystem {

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        Inventory inventory = new Inventory();

        boolean running = true;

        //Repeat the menu until the user chooses "Exit"
        while (running) {

            displayMenu();
            int choice = readInt(input, "Enter your choice: ");

            //Keep prompting until whole number is entered
            switch(choice){

                case 1:
                    addBook(input, inventory);
                    break;

                case 2:
                    borrowBook(input, inventory);
                    break;

                case 3:
                    returnBook(input, inventory);
                    break;

                case 4:
                    searchBooks(input, inventory);
                    break;

                case 5:
                    inventory.printAvailableBooks();
                    break;

                case 6:
                    System.out.println(
                        "Available books: " 
                        + inventory.getMainInventoryCount()
                    );

                    System.out.println(
                        "Borrowed books: " 
                        + inventory.getBorrowedCount()
                    );
                    break;

                case 7:
                    inventory.printSortedByTitle();
                    break;
                
                case 8:
                    System.out.println(
                        "Exiting the Library. Goodbye!"
                    );
                    running = false;
                    break;
                default:
                    System.out.println(
                        "Invalid choice. Please enter a number between 1 and 8."
                    );    
            }
        }
        input.close();
    }

    //Keeps the menu in one method to avoid duplicate code
    private static void displayMenu(){

        System.out.println("\n-------Library Menu:-------");
        System.out.println("1. Add a Book");
        System.out.println("2. Borrow a Book");
        System.out.println("3. Return a Book");
        System.out.println("4. Search for a Book by Title");
        System.out.println("5. Display Available Books");
        System.out.println("6. Display Inventory Counts");
        System.out.println("7. Display Available Books Alphabetically by Title");
        System.out.println("8. Exit");
    }
    private static int readInt(Scanner input, String prompt){

        while (true){
            System.out.print(prompt);

            try{
                int number = input.nextInt();

                input.nextLine(); // Consume the newline character
                        
                return number;
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a whole number.");
                        
                input.nextLine(); // Clear the invalid input
            }
        }
    }
    private static void addBook( Scanner input, Inventory inventory){
        int id = readInt(input, "Enter Book ID: ");

        //Reject a duplicate before collecting additional info
        if (inventory.containsId(id)){

            System.out.println("A book with this ID already exists.");
            return;
        }
        System.out.print("Enter title: ");
        String title = input.nextLine().trim();

        System.out.print("Enter author: ");
        String author = input.nextLine().trim(); 
          
        System.out.print("Enter ISBN: ");
        String isbn = input.nextLine().trim();
         
        int pages = readInt(input, "Enter number of pages: ");

        //Validation checks
        if (title.isBlank() || author.isBlank() || isbn.isBlank()){

            System.out.println("Title, author, and ISBN cannot be blank.");

            System.out.println("Book not added.");
            return;
        }

        if (pages <= 0){

            System.out.println("Number of pages must be positive.");

            System.out.println("Book not added.");
            return;
        }

        //Create a Book object w/ parameterized constructor
        try{
            Book newBook = new Book(id, title, author, isbn, pages);

            //Inventory performs a second Duplicate-ID check
            if (inventory.addBook(newBook)){

                System.out.println("\"" + title + "\" has been added to Library");
            }
            else{
                System.out.println("Failed to add book. Duplicate ID detected.");
            }
        }
        catch (IllegalArgumentException e){

            System.out.println("Book not added: " + e.getMessage());
        }
    }
    
    //Borrows a book using its unique ID.
    private static void borrowBook(Scanner input, Inventory inventory){

        int id = readInt(input, "Enter the ID of the book to borrow: ");

        //Find the book before attempting to borrow
        Book book = inventory.findBookById(id);

        //Prevent NullPointerException if ID doesnt exist
        //when attempting to call book.getTitle.
        //Program will stop here
        if (book == null){
            
            System.out.println( "Book ID " + id + "cannot be found.");
            return;
        }

        if (inventory.borrowBook(id)){

            System.out.println("\"" + book.getTitle() + "\" borrowed successfully");
        }
        else{

            System.out.println("\"" + book.getTitle() + "\" is not available for borrowing.");
        }
    }

    //Returns a book using its unique ID.
    private static void returnBook(Scanner input, Inventory inventory){

        int id = readInt(input, "Enter the ID of the book to return: ");

        //Find the book before Attempting to return iterating
        Book book = inventory.findBookById(id);

        //Prevent NullPointerException if ID doesnt exist
        //when attempting to call book.getTitle.
        //Program will stop here
        if (book == null){

            System.out.println("Book ID " + id + "cannot be found.");
            return;
        }

        if (inventory.returnBook(id)){

            System.out.println("\"" + book.getTitle() + "\" returned successfully");
        }
        else{

            System.out.println("\"" + book.getTitle() + "\" found in borrowed list.");
        }
    }
    
    //Searches Books by title (full or partial).
    private static void searchBooks(Scanner input, Inventory inventory){

        System.out.print("Enter the title  to search: ");
        String searchTerm = input.nextLine().trim();

        if (searchTerm.isBlank()){

            System.out.println("\"" + searchTerm + "\" cannot be located.");
            return;
        }

        inventory.searchByTitle(searchTerm);
    }
}    
