package io.teknek.collections.evolving.gpt;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.TreeSet;

public class Gpt {

    public final static String NEWLINE = "\n";
    public static void main(String [] args) throws IOException {
        byte [] bytes = Files.readAllBytes(Paths.get("input.txt"));

        StringBuilder fileContents = new StringBuilder();
        Scanner scanner = new Scanner(Paths.get("input.txt"));
        while (scanner.hasNextLine()) {
            fileContents.append(scanner.nextLine()).append(NEWLINE);
        }

        char[] contentsInCharArray = fileContents.toString().toCharArray();
		System.out.println(contentsInCharArray);

        TreeSet<Character> chars = new TreeSet<>();
        for (char c : contentsInCharArray) {
            chars.add(c);
        }


        int vocabSize = chars.size();
    }
}
