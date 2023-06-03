import java.util.Scanner;

public class UserInterface {
    Scanner scanner;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    private enum Command {
        CREATE("c"),
        READ("r"),
        UPDATE("u"),
        DELETE("d"),
        QUIT("q");

        public static Command getCommand(String key) {
            for (Command c : Command.values()) {
                if (c.getKey().equals(key)) {
                    return c;
                }
            }
            return null;
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
            Command userCommand = Command.getCommand(userString);
            switch (userCommand) {
                case CREATE:
                    getNewBook();
                    break;
                case READ:
                    break;
                case DELETE:
                    break;
                case UPDATE:
                    break;
                case QUIT:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }

    public void close() {
        this.scanner.close();
    }

    public static void main(String[] args) {
        LibraryManager lib = new LibraryManager();
        lib.deleteTable();
        lib.createTable();

        UserInterface ui = new UserInterface();
        ui.run();
    }
}
