package business;

import java.util.Scanner;

public class BusinessCLI {

    private final BusinessDatamart datamart;

    public BusinessCLI(BusinessDatamart datamart) {
        this.datamart = datamart;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== BUSINESS UNIT ===");
            System.out.println("1. Ver número de eventos de noticias");
            System.out.println("2. Ver número de eventos de bolsa");
            System.out.println("3. Ver noticias por fuente");
            System.out.println("4. Ver últimas cotizaciones por símbolo");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            String option = scanner.nextLine();

            switch (option) {
                case "1" -> System.out.println("Noticias recibidas: " + datamart.getNewsEvents().size());
                case "2" -> System.out.println("Cotizaciones recibidas: " + datamart.getStockEvents().size());
                case "3" -> System.out.println(datamart.getNewsCountBySource());
                case "4" -> System.out.println(datamart.getLatestStockBySymbol());
                case "0" -> {
                    System.out.println("Saliendo...");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}
