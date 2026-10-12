import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//JavaFX version of the Library System... 
//If it doesnt work will probably just make the whole thing one big comment
//So I can at least show my working attmpt.

public class LibrarySystemGUI extends Application {

    //Object for the GUI
    private Inventory inventory = new Inventory();

    //Input Controls
    private TextField idField = new TextField();
    private TextField titleField = new TextField();
    private TextField authorField = new TextField();
    private TextField isbnField = new TextField();
    private TextField pagesField = new TextField();
    private TextField searchField = new TextField();

    //Output Area
    private TextArea outputArea = new TextArea();

    @Override
    private void start(Stage primaryStage){

        //Window Title
        primaryStage.setTitle("Library Management System");

        //Labels
        Label idLabel = new Label("Book ID:");
        Label titleLabel = new Label("Title:")
        Label authorLabel = new Label("Author:");
        Label isbnLabel = new Label("ISBN:");
        Label pagesLabel = new Label("Number of Pages:");
        Label searchLabel = new Label("Search:");

        //Layout for Inputs
        GridPane form = new GridPane();

        form.setPadding(new Insets(10));
        form.setVgap(10);
        form.setHgap(10);

        //Add Labels and Fields to grid
        form.add(idLabel, 0, 0);
        form.add(idField, 1, 0);

        form.add(titleLabel, 0, 1);
        form.add(titleField, 1, 1);

        form.add(authorLabel, 0, 2);
        form.add(authorField, 1, 2);

        form.add(isbnLabel, 0, 3);
        form.add(isbnField, 1, 3);

        form.add(pagesLabel, 0, 4);
        form.add(pagesField, 1, 4);

        form.add(searchLabel, 0, 5);
        form.add(searchField, 1, 5);

        //Buttons
        Button addButton = new Button("Add Book");
        Button borrowButton = new Button("Borrow Book");
        Button returnButton = new Button("Return Book");
        Button searchButton = new Button("Search Library");
        Button displayButton = new Button("Display Available Books");
        Button countButton = new Button("Display Counts");

        //Output Area Settings
        outputArea.SetEditable(false);
        outputArea.setPrefHeight(300);

        //Add Book Button
        addButton.setOnAction(event -> {

            try{
                int id = Integer.parseInt(idField.getText());
                int pages = Integer.parseInt(pagesField.getText());

                Book newBook = new Book(
                    id, 
                    titleField.getText().trim(),
                    author.fireld.getText().trim(),
                    isbn.getText().trim(),
                    pages
                );

                if (inventory.addBook(newBook)) {

                    outputArea.appendText("\"" + newBook.getTitle() + "\" added succesfully.\n");
                }
                else {
                    outputArea.appendText("Duplicate ID detected.\n");
                }
            }
            catch (NumberFormatException e){

                outputArea.appendText("ID and Pages must be positive numbers.\n");
            }
            catch (IllegalArgumentException e){

                outputArea.appendText(e.getMessage() + "\n");
            }
        });

        //Borrow Book Button
        borrowButton.setOnAction(event -> {

            try{
                int id = Integer.parseInt(idField.getText());

                Book book = inventory.findBookById(id);

                if (book == null) {

                    outputArea.appendText("Book with ID: " + id + " cannot be located \n");
                    return;
                }
                if (inventory.borrowBook(id));{

                    outputArea.appendText("\"" + book.getTitle() + "\" borrowed successfully. \n");
                }
                else{

                    outputArea.appendText("\"" + book.getTitle() + "\" is unavailable. \n");
                }
            }
            catch (NumberFormatException e){

                outputArea.appendText("Enter Valid numeric ID.\n");
            }

        });

        //Return Book Button
        returnButton.setOnAction(event -> {

            try{
                int id = Integer.parseInt(idField.getText());

                Book book = inventory.findBookById(id);

                if (book == null) {

                    outputArea.appendText("Book with ID: " + id + " cannot be located \n");
                    return;
                }
                if (inventory.returnBook(id));{

                    outputArea.appendText("\"" + book.getTitle() + "\" returned successfully. \n");
                }
                else{

                    outputArea.appendText("\"" + book.getTitle() + "\" is currently in stock, cannot process return. \n");
                }
            }
            catch (NumberFormatException e){

                outputArea.appendText("Enter Valid numeric ID.\n");
            }

        });

        //Search Button
        searchButton.setAction(event -> {

            String search = searchField.getText().trim();

            outputArea.appendText("Searching for: " + search + "\n");

            inventory.searchByTitle(search);
        });

        //Display Counts Button
        countButton.setOnAction(event -> {

            outputArea.appendText("Available Books: " + inventory.getAvailableCount() +"\n");

            outputArea.appendText("Borrowed Books: " + inventory.getBorrowedCount() + "\n");
        });

        //Display Books Button
        displayButton.setOnAction(event -> {

            outputArea.appendText( "\nDisplaying Books... \n");

            inventory.printAvailableBooks();
        });

        //Layout Container
        VBox root = new VBox();
        
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        root.getChildren().addAll(
            form,
            addButton,
            borrowButton,
            returnButton,
            searchButton,
            displayButton,
            countButton,
            otputArea
        );

        //Create Scene
        Scene scene = new Scene(root, 700, 700);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //JavaFX Launch
    public static vois main(String[] args){
        launch(args);
    }  
}
