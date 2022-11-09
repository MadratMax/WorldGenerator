package root;

import java.util.Random;
import java.util.stream.IntStream;

public class Randomizator {

    private final Random random;

    public Randomizator() {
        this.random = new Random();
    }

    public int getRandomFrom(int bound) {
        bound = Math.abs(bound);
        try {
            int r = random.nextInt(Math.abs(bound));
            return r;
        } catch (IllegalArgumentException ex) {
            return 0;
        }
    }

    public int getRandomFromRange(int startBound, int endBound) {
        int min=startBound;
        int max=endBound;

        return random.nextInt(max-min)+min;
    }

    public MathOperator getRandomOperator() {
        int r = getRandomFrom(2) ;
        if (r == 1) {
            return MathOperator.PLUS;
        } else {
            return MathOperator.MINUS;
        }
    }
}
