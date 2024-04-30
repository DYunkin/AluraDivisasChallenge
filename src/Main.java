import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConversorDeDivisas conversor = new ConversorDeDivisas();

        System.out.println("\n");
        System.out.println("Bienvenido al conversor de divisas");

        String menu = """
                    1.- Dolar a Peso Argentino
                    2.- Peso Argentino a Dolar
                    3.- Dolar a Real Brasileño
                    4.- Real Brasileño a Dolar
                    5.- Dolar a Peso Colombiano
                    6.- Peso Colombiano a Dolar
                    7.- Dolar a Peso dominicano
                    8.- Peso dominicano a Dolar
                    9.- Salir
                """;

        while (true) {

              System.out.println(menu);

            int option = scanner.nextInt();
            if (option == 9)
                break;
            if (option > 9){
                System.out.println("Opción Inválida");
            } else {
                System.out.println("Ingrese la cantidad a convertir: ");
                double amount = scanner.nextDouble();

                String result = conversor.convertir(option, amount);
                System.out.println("Conversión: " + result);
            }

            }
        System.out.println("Gracias por usar la app");

        }
    }

