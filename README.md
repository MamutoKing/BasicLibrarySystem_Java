# BasicLibrarySystem_Java


For your Portfolio project, you are tasked with implementing a basic library system in Java. 
The program should allow users to add books to the library, borrow books, and return books. 
The system should also maintain a record of each book's available quantity.

Book class

You will create a class “Book” which will have these 5 member variables:

    id (unique), title, author, ISBN number, and number of pages.

Choose the appropriate data types and declare them private.

    Implement all the getter and setter methods and define them public.
    Implement a default constructor.
    Implement a parameterized constructor that has 5 parameters to be used to initialize those 5 member variables.
    Create a member method named “printBookInfo()”, which prints a specific book's info.

Inventory class

Next, you will create an “Inventory” class which keeps track of total number of books and other inventory information.
This Inventory class should have a Java ArrayList or LinkedList as a data structure to handle the database. 
Optionally, create a second ArrayList or LinkedList to track currently borrowed books. 
This class should have the following necessary methods:

    1.) addBook() —
    This method creates a new Book object and adds it to the main inventory.
    It can be implemented using either an ArrayList or LinkedList.

    2.) borrowBook() – 
    This method handles the borrowing process by removing a book from the main inventory and adding it to the lending inventory.

    3.) returnBook() – 
    This method processes book returns by moving the book from the lending inventory back to the main inventory.

    4.) printAll() – 
    This method iterates over the book collection (ArrayList or LinkedList) and calls the printBookInfo() method from the Book class to display details of each book.

    5.) searchByTitle(String title) – 
    method in the Inventory class.
    It should iterate through the book list and return all books with matching or partially matching titles (case-insensitive)

    6.) You may implement additional helper methods such as getMainInventoryCount() to retrieve the number of books available.
    These methods can also assist in handling edge cases, such as when a user attempts to return a book even though no books have been lent out.

Main program or method

In your main() method, create and display a menu like below:

1. Add Book
2. Borrow Book
3. Return Book
4. Search by Title
5. Print All Books
6. Exit
 

Add Book

    The user is prompted to enter all book details:
        id, title, author, ISBN number, and number of pages.

    Once the information is entered, the book is added to the main inventory, and a confirmation message is displayed:
        “Book added to the library.”

Borrow Book

    The user is asked to enter the unique id of the book they want to borrow.

    If the book exists and is available, it is moved from the main inventory to the lending inventory, and the system displays:
        “Book successfully borrowed.”

    If the book is not found or already borrowed, an appropriate message is shown.

Return Books

    The user enters the id of the book to return.
    
    If the book is currently in the lending inventory, it is returned to the main inventory, and the user sees:
        “Book successfully returned.”

    If the book is not in the lending list, an error message is shown.

Search by Title

    The user inputs a full or partial book title.

    The system searches the main inventory and displays all matching results.

    If no books are found, it displays:
        “No matching book found.”

Print All Books

    Displays a list of all books currently available in the main inventory.

    Each book’s details are shown using the printBookInfo() method.

Exit

    Ends the program execution gracefully.

    Displays a message like:
        “Exiting the program. Goodbye!”

Exception Handling

    Your program must utilize exception handling using Java try…catch approach. 
    One example would be handling invalid inputs.

Other Guidelines

    Make sure to handle invalid options.
    Display successful message after each operation.
    Display an unsuccessful message where applicable.
    Input validations is not required but you may choose to do so.