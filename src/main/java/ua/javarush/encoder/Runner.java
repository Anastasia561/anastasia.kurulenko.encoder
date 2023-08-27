package ua.javarush.encoder;

public class Runner {
    public static int key;
    public static String commandToAdd;
    public static String startFileName;
    public static String command;
    public String[] args;

    public Runner(String[] args) {
        this.args = args;
    }

    private void initialize() {
        if (CLI.checkArgs(args)) {
            CLI.cli();
        } else {
            command = args[0];
            startFileName = args[1];
            if (command.toLowerCase().startsWith("e")) {
                commandToAdd = "[ENCRYPTED]";
                key = Integer.parseInt(args[2]);
            } else if (command.toLowerCase().startsWith("d")) {
                commandToAdd = "[DECRYPTED]";
                key = -Integer.parseInt(args[2]);
            } else if (command.toLowerCase().startsWith("b")) {
                commandToAdd = "[BRUTE_FORCE]";
            } else {
                System.out.println("Invalid command");
            }
        }
    }

    public void run(CaesarCipher caesar, FileService fileService) {
        initialize();
        if (command.toLowerCase().startsWith("e") || command.toLowerCase().startsWith("d")) {
            fileService.readFile();
            caesar.code(key);
            fileService.writeFile(commandToAdd);
        } else if (command.toLowerCase().startsWith("b")) {
            fileService.readFile();
            caesar.bruteForce();
            fileService.writeFile(commandToAdd);
        } else {
            System.out.println("Invalid command");
        }
    }
}
