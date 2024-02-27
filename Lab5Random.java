import java.util.Random;

/**
 * Added Lab2Random, cause Sam's environment is jdk 17.
 * Jakob runs on jdk 8 and this overload method does not exist.
 */
public class Lab5Random extends Random {
    public int nextInt(int min, int max) {
        return nextInt(max - min + 1) + min;
    }
}