package io.teknek.collections.evolving.gpt;



import io.teknek.collections.evolving.firstorder.Pair;
import io.teknek.collections.evolving.lighter.Light;
import io.teknek.collections.set.TreeSet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
        Character[] contentsInCharacterArray = new Character[contentsInCharArray.length];
        for (int i=0; i < contentsInCharacterArray.length;i++){
            contentsInCharacterArray[i] = contentsInCharArray[i];
        }
		System.out.println(contentsInCharArray);


        TreeSet<Character> chars = new TreeSet<>(Comparator.naturalOrder(), contentsInCharacterArray);

/*
        int vocabSize = chars.size();
        SortedMap<Integer, Character> itos = enumerate(chars);
        SortedMap<Character, Integer> stoi = enumerateRev(chars);

        Function<String, List<Integer>> encode = (input) -> {
            List<Integer> x = new ArrayList<>();
            for (char c : input.toCharArray()) {
                x.add(stoi.get(c));
            }
            return x;
        };

        Function<Character, Integer> encodeItem = stoi::get;

        Function<List<Integer>, String> decode = (input) -> {
            StringBuilder sb = new StringBuilder();
            for (Integer i: input){
                sb.append(itos.get(i));
            }
            return sb.toString();
        };
        List<Integer> data = Light.tensor(encodeItem, contentsInCharacterArray);

 */
    }




    public static <T> SortedMap<Integer,T> enumerate(Collection<T> input){
        int i =0;
        SortedMap<Integer, T> result = new TreeMap<>();
        for (T t: input){
            result.put(i, t);
        }
        return result;
    }

    public static <T> SortedMap<T, Integer> enumerateRev(Collection<T> input){
        int i = 0;
        SortedMap<T, Integer> result = new TreeMap<>();
        for (T t: input){
            result.put(t, i);
        }
        return result;
    }



}
