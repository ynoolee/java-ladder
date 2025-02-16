package laddergame;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringParser {

    private final Pattern pattern;

    public StringParser(String delimiter) {
        this.pattern = Pattern.compile(delimiter);
    }

    public List<String> parse(String target) {
        return Arrays.stream(pattern.split(target))
            .collect(Collectors.toList());
    }
}
