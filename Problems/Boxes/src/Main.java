import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] box1 = new int[3];
        int[] box2 = new int[3];
        int[] comp = new int[3];

        for (int i = 0; i < box1.length; i++) {
            box1[i] = scanner.nextInt();
        }

        for (int i = 0; i < box2.length; i++) {
            box2[i] = scanner.nextInt();
        }

        Arrays.sort(box1);
        Arrays.sort(box2);

        for (int i = 0; i < box1.length; i++) {
            comp[i] = Integer.compare(box1[i], box2[i]);
        }

        if (sum(comp) == -3) {
            System.out.println("Box 1 < Box 2");
        } else if (sum(comp) == 3) {
            System.out.println("Box 1 > Box 2");
        } else {
            System.out.println("Incompatible");
        }
    }

    public static int sum(int[] arr) {
        int sum = 0;
        for (int el: arr) {
            sum += el;
        }
        return sum;
    }
}