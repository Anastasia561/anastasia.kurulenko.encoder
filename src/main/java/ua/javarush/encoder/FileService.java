package ua.javarush.encoder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static ua.javarush.encoder.Runner.startFileName;

public class FileService {

    public static ArrayList<Character> source = new ArrayList<>();

    public static ArrayList<Character> result = new ArrayList<>();

    private static int realCharRead;

    private static String fileEnding;

    public void readFile() {
        try (FileReader reader = new FileReader(startFileName)) {
            char[] buffer = new char[656532];
            while (reader.ready()) {
                realCharRead = reader.read(buffer);
            }
            for (int i = 0; i < realCharRead; i++) {
                source.add(buffer[i]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public void writeFile(String commandToAdd) {
        String resultFileName = getFileName(startFileName) + commandToAdd + "." + fileEnding;
        Path dest = Path.of(resultFileName);
        try {
            Files.createFile(dest);
        } catch (FileAlreadyExistsException e) {
            System.out.println("File already exist");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Error");
        }
        try (FileWriter writer = new FileWriter(resultFileName)) {
            char[] buffer = new char[result.size()];
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = result.get(i);
            }
            writer.write(buffer);
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    private static String getFileName(String startFileName) {
        String[] array = startFileName.split("[.]");
        fileEnding = array[1];
        return array[0];
    }
}
