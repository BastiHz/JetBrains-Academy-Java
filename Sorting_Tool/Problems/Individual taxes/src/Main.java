import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nCompanies = scanner.nextInt();
        int[] income = new int[nCompanies];
        for (int i = 0; i < nCompanies; i++) {
            income[i] = scanner.nextInt();
        }
        double maxTax = 0;
        int companyNumberMostTaxes = 1;
        for (int i = 0; i < nCompanies; i++) {
            int taxRate = scanner.nextInt();
            double taxAmount = income[i] * taxRate / 100.0;
            if (taxAmount > maxTax) {
                maxTax = taxAmount;
                companyNumberMostTaxes = i + 1;
            }
        }
        System.out.println(companyNumberMostTaxes);
    }
}