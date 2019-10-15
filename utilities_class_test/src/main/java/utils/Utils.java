package utils;

import java.util.stream.LongStream;

public class Utils {
    public String concatenateWords(String... words) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String word : words) {
            if (word == null) {
                throw new NullPointerException();
            }
            stringBuilder.append(word);
        }
        return stringBuilder.toString();
    }

    public long computeFactorial(long number) {
        if (number < 0) {
            throw new IllegalArgumentException();
        }
        return LongStream.rangeClosed(1, number)
                .reduce(1, ((x, y) -> x * y));
    }
}
