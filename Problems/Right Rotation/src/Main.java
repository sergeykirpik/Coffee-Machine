import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] arr = scanner.nextLine().split(" ");
        int rotations = scanner.nextInt();

        String[] res = rotate(arr, rotations);

        for (String el: res) {
            System.out.printf("%s ", el);
        }
        System.out.println();
    }

    public static String[] rotate(String[] arr, int rotations) {
        String[] res = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int newIndex = (i + rotations) % arr.length;
            res[newIndex] = arr[i];
        }
        return res;
    }
}