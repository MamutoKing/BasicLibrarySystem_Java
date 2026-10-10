import java.util.ArrayList;
import java.util.Comparator;

//Inventory manages Library Availability and Library Items
public class Inventory {

    private final ArrayList<Book> inventory;
    private final ArrayList<Book> borrowedBooks;

    //Create both lists when Inventory is created
    public Inventory() {

        inventory = new ArrayList<>();
        borrowedBooks = new ArrayList<>();
    }

    //Add book if ID is not already in use.
    //Return true if added, False if rejected.
    public boolean addBook(Book book) {

        //Reject null objects and duplicate IDs
        if (book == null || containsID(book.getId())) {

            return false;
        }

        inventory.add(book);

        return true;
    }

    //Checks Lists so an ID cannot be reused
    //while OG book is borrowed
    private boolean containsID(int id) {

        for (Book book : inventory) {

            if (book.getId() == id) {
                return true;
            }
        }

        for (Book book : borrowedBooks) {

            if (book.getId() == id) {
                return true;
            }
        }

        return false;
    }
    
    //Move available book into borrowedBooks
    //Return False if ID is not available
    public boolean borrowBook(int id) {

        //Use index loop so removal is safe while iterating
        for (int i = 0; i < inventory.size(); i++) {
            Book book = inventory.get(i);

            if (book.getId() == id) {

                borrowedBooks.add(book);
                inventory.remove(i);

                return true;
            }
        }

        return false;
    }

    //Move borrowed book back into inventory
    //Return False if ID is not borrowed
    public boolean returnBook(int id) {

        //Use index loop so removal is safe 
        for (int i = 0; i < borrowedBooks.size(); i++) {
            Book book = borrowedBooks.get(i);

            if (book.getId() == id) {

                inventory.add(book);
                borrowedBooks.remove(i);

                return true;
            }
        }

        return false;
    }

    //Display every book currently available
    public void printAvailableBooks() {

        System.out.println("            Available Books:");
        System.out.println("---------------------------------------");

        if (inventory.isEmpty()) {
            System.out.println("No books are currently available.");
            return;
        }
        for (Book book : inventory) {
            book.printBookInfo();
        }
    }

    //Search available books by full or partial title.
    //  Case insensitive.
    public void searchByTitle(String title) {

        if (title == null || title.isBlank()){
            System.out.println("Please enter a title to search.");
            return;
        }

        boolean found = false;
        String searchText = title.trim().toLowerCase();

        for (Book book : inventory) {

            if (book.getTitle() != null
                    && book.getTitle()
                        .toLowerCase()
                        .contains(searchText)) {

                book.printBookInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found with the title \"" + title + "\".");
        }
    }

    public void printSortedByTitle(){
            
        if (inventory.isEmpty()) {

            System.out.println("No books are currently available.");
            return;
        }

        //Make a copy so sorting does not rearrange inventory
        ArrayList<Book> sortedList = new ArrayList<>(inventory);

        //Compare titles without considering capitalization.
        sortedList.sort(
            Comparator.comparing(
                Book::getTitle, String.CASE_INSENSITIVE_ORDER
            )
        );

        for(Book book : sortedList) {
            book.printBookInfo();
        }
    }

    //Returns the number of books available to borrow
    public int getAvailableCount() {

        return inventory.size();
    }

    //Compatibility method used by the menu loop.
    public int getMainInventoryCount() {

        return getAvailableCount();
    }

    //Compatibility method used by the menu loop.
    public boolean containsId(int id) {

        return containsID(id);
    }

    //Returns the number of books currently borrowed
    public int getBorrowedCount() {

        return borrowedBooks.size();
    }
}

