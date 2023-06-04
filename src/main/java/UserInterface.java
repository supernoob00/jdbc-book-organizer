import java.util.List;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner;
    LibraryManager lib;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
        this.lib = new LibraryManager();
    }

    private enum Command {
        CREATE("c"),
        READ("r"),
        UPDATE("u"),
        DELETE("d"),
        QUIT("q");

        public static Command getCommand(String key) throws Exception {
            for (Command c : Command.values()) {
                if (c.getKey().equals(key)) {
                    return c;
                }
            }
            throw new Exception("Not a valid command.");
        }

        private final String key;

        private Command(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    public void showCommands() {
        System.out.println("Commands: ");
        for (Command c : Command.values()) {
            System.out.println(c.name() + ": " + c.key);
        }
    }

    public Book getNewBook() {
        System.out.print("Enter book title: ");
        String title = this.scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = this.scanner.nextLine();
        System.out.print("Enter book publication year: ");
        int year = this.scanner.nextInt();
        scanner.nextLine();
        return new Book(title, author, year);
    }

    public void run() {
        showCommands();
        boolean running = true;
        while (running) {
            System.out.print("Enter a command: ");
            String userString = this.scanner.nextLine();
            try {
                Command userCommand = Command.getCommand(userString);
                switch (userCommand) {
                    case CREATE: {
                        Book userBook = getNewBook();
                        lib.addBook(userBook);
                        break;
                    }
                    case READ: {
                        List<Book> books = lib.getAllBooks();
                        String bookListString = Book.bookListString(books);
                        System.out.println(bookListString);
                        break;
                    }
                    case UPDATE: {
                        System.out.println("Enter a book id to update: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        Book current = lib.getBookById(id);
                        if (current == null) {
                            System.out.println("Book with given id does not exist.");
                        }
                        else {
                            Book updated = getNewBook();
                            updated.setId(id);
                            lib.updateBook(updated);
                        }
                        break;
                    }
                    case DELETE: {
                        System.out.println("Enter a book id to delete: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        Book book = lib.getBookById(id);
                        if (book == null) {
                            System.out.println("Book with given id does not exist.");
                        } else {
                            lib.deleteBookById(id);
                        }
                        break;
                    }
                    case QUIT: {
                        running = false;
                        break;
                    }
                }
            }
            catch (Exception e) {
                String errorMessage = e.getMessage();
                System.out.println(errorMessage);
            }
        }
        this.scanner.close();
        System.exit(0);
    }

    public static void main(String[] args) {
        LibraryManager lib = new LibraryManager();
        lib.deleteTable();
        lib.createTable();

        UserInterface ui = new UserInterface();
        ui.run();
    }
}
