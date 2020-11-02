import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfCompanies = scanner.nextInt();
        int[] yearlyIncomes = new int[numberOfCompanies];
        int[] individualTaxes = new int[numberOfCompanies];

        for (int i = 0; i < numberOfCompanies; i++) {
            yearlyIncomes[i] = scanner.nextInt();
        }

        for (int i = 0; i < numberOfCompanies; i++) {
            individualTaxes[i] = scanner.nextInt();
        }

        int maxIndex = 0;
        double maxTax = yearlyIncomes[0] * individualTaxes[0];
        for (int i = 0; i < numberOfCompanies; i++) {
            double tax = yearlyIncomes[i] * individualTaxes[i];
            if (tax > maxTax) {
                maxIndex = i;
                maxTax = tax;
            }
        }
        System.out.println(maxIndex + 1);
    }
}