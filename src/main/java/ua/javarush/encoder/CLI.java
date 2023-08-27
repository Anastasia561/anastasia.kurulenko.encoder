package ua.javarush.encoder;

import java.util.Scanner;

import static ua.javarush.encoder.Runner.command;

public class CLI {
    public static boolean checkArgs(String[] args) {
        return args.length == 0;
    }

    public static void cli() {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter filepath ");
        Runner.startFileName = console.nextLine();
        System.out.println("Enter command: [e]ncrypt, [d]ecrypt, [b]rute_force ");
        command = console.next();
        if (command.startsWith("b")) {
            Runner.commandToAdd = "[BRUTE_FORCE]";
        } else if (command.startsWith("e")) {
            System.out.println("Enter key ");
            Runner.key = console.nextInt();
            Runner.commandToAdd = "[ENCRYPTED]";
        } else if (command.startsWith("d")) {
            System.out.println("Enter key: ");
            Runner.key = -console.nextInt();
            Runner.commandToAdd = "[DECRYPTED]";
        }
    }
}
