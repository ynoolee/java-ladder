package nextstep.fp;

import java.util.List;

public class Lambda {
    public static void printAllOld(List<Integer> numbers) {
        System.out.println("printAllOld");

        for (int number : numbers) {
            System.out.println(number);
        }
    }

    public static void printAllLambda(List<Integer> numbers) {
        System.out.println("printAllLambda");

        numbers.forEach(System.out::println);
    }

    public static void runThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from thread");
            }
        }).start();
    }

    public interface NumberConditional {
        boolean isSatisfied(int number);
    }

    public static int sumOnlyConditionSatisfiedNumbers(List<Integer> numbers, NumberConditional condition) {
        return numbers.stream()
            .filter(condition::isSatisfied)
            .reduce(0, Integer::sum);
    }
}
