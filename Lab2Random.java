import java.util.Random;

public class Lab2Random extends Random {
    public int nextInt(int min, int max) {
        return nextInt(max - min + 1) + min;
    }
}