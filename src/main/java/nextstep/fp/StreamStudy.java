package nextstep.fp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamStudy {

    public static long countWords() throws IOException {
        String contents = new String(Files.readAllBytes(Paths
            .get("src/main/resources/fp/war-and-peace.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        long count = 0;
        for (String w : words) {
            if (w.length() > 12) count++;
        }
        return count;
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = new String(Files.readAllBytes(Paths
            .get("src/main/resources/fp/war-and-peace.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        words.stream()
            .filter(word -> word.length() > 12)
            .sorted(Comparator.comparing(String::length).reversed())
//            .sorted(Comparator.comparing((String str) -> (-1) * str.length()))
            .distinct()
            .limit(100)
            .forEach(word -> System.out.println(word.toLowerCase()));
    }

    public static class Word {
        String word;
        int length;

        public Word(final String word, final int length) {
            this.word = word;
            this.length = length;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Word{");
            sb.append("word='").append(word).append('\'');
            sb.append(", length=").append(length);
            sb.append('}');
            return sb.toString();
        }
    }


    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        return numbers.stream().map(x -> 2 * x).collect(Collectors.toList());
    }

    public static long sumAll(List<Integer> numbers) {
        return numbers.stream().reduce(0, (x, y) -> x + y);
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {
        return numbers.stream()
            .filter(number -> number > 3)
            .map(number -> number * 2)
            .reduce(0, Integer::sum);
    }
}
