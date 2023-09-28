package ua.javarush.encoder;

public class Application {
    public static void main(String[] args) {
        FileService fileService = new FileService();
        CaesarCipher caesar = new CaesarCipher();
        Runner runner = new Runner(args);
        runner.run(caesar, fileService);
    }
}
