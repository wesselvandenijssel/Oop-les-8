import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConvertCurrency {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Gebruiker invoer
            System.out.print("Voer de naam van het bronbestand in: ");
            String sourceFileName = scanner.nextLine();

            System.out.print("Voer de naam van het bestemmingsbestand in: ");
            String destinationFileName = scanner.nextLine();

            System.out.print("Voer de waarde van 1 US dollar in Eurocenten in: ");
            double exchangeRate = Double.parseDouble(scanner.nextLine());

            // Invoerbestand lezen
            File sourceFile = new File(sourceFileName);
            Scanner fileScanner = new Scanner(sourceFile);

            // Uitvoerbestand schrijven
            PrintWriter writer = new PrintWriter(destinationFileName);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" : ");

                if (parts.length == 2) {
                    String itemName = parts[0];
                    double priceInDollars = Double.parseDouble(parts[1]);
                    double priceInEuros = priceInDollars * (exchangeRate / 100);

                    writer.println(itemName + " : " + String.format("%.2f", priceInEuros));
                } else {
                    System.out.println("Ongeldige invoer in het bronbestand: " + line);
                }
            }

            // Sluit bestanden
            fileScanner.close();
            writer.close();

            System.out.println("Conversie voltooid. Resultaten zijn opgeslagen in " + destinationFileName);

        } catch (FileNotFoundException e) {
            System.out.println("Bronbestand niet gevonden.");
        } catch (NumberFormatException e) {
            System.out.println("Ongeldige waarde voor de koers.");
        } catch (Exception e) {
            System.out.println("Er is een fout opgetreden: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
