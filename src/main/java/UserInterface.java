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
                if (c.key.equals(key)) {
                    return c;
                }
            }
            return null;
        }

        public final String key;

        private Command(String key) {
            this.key = key;
        }

    }

    public void showCommands() {
        System.out.println("Commands: ");
        for (Command c : Command.values()) {
            System.out.println(c.name() + ": " + c.key);
        }
    }

    public void run() {
        showCommands();
        boolean cont = true;
        while (cont) {
            System.out.println("Enter a command: ");
            String userString = this.scanner.nextLine();
            Command userCommand = Command.valueOf("userString");
            switch (userCommand) {
                case CREATE:
                case READ:
                case DELETE:
                case UPDATE:
                case QUIT:
                    cont = false;
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
        Book myBook = new Book("The Lord of the Rings", "J.R.R Tolkien", 1950);
        LibraryManager libDao = new LibraryManager();
        libDao.createTable();
        libDao.addBook(myBook);

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        System.out.println();
    }
}
