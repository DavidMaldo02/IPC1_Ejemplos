package ipc1.lab.paquete;

import java.util.Scanner;

public class laboratorio03 {

    static int global = 10;

    public void printMenu() {
        System.out.println("----------- Menu -------------");
        System.out.println("1. Imprimir los números de 1 a 10");
        System.out.println("2. Verificar si el numero es primo");
        System.out.println("3. Dia de la semana");
        System.out.println("4. Salir");
        System.out.print("Ingrese la opción que desea ejecutar: ");
    }

    public boolean verificarPrimo(int n) {
        int x = 2;
        while (true) {
            if (n % x == 0) {
//                System.out.println("El numero ingresado no es primo!");
                return false;
//                break;
            }
            x = x + 1;
            if (x == n) {
//                System.out.println("El numero ingresado es primo!");
                return true;
//                break;
            }
        }
    }

    public void ejecutar() {
        boolean end = false;
        Scanner sc = new Scanner(System.in);
        int option;
        // Imprimir variable con ámbito global
        System.out.println(global);
        while (!end) {
            printMenu();
            option = sc.nextInt();
            sc.nextLine();
            // Limpiar consola
            System.out.print("\033[H\033[2J");
            System.out.flush();
            switch (option - 1) {
                case 0:
                    for (int i = 1; i <= 10; i++) {
                        System.out.println(i);
                    }
                    break;
                case 1:
                    System.out.print("Ingrese el numero a verificar: ");
                    int n = sc.nextInt();
                    sc.nextLine();
                    boolean valor = verificarPrimo(n);
                    if (valor) {
                        System.out.println("Es un numero primo");
                    } else {
                        System.out.println("No es un numero primo");
                    }
                    break;
                case 2:
                    String entrada;
                    entrada = sc.nextLine();
                    switch (entrada) {
                        case "Lunes":
                            System.out.println(1);
                            break;
                        case "Martes":
                            System.out.println(2);
                            break;
                        default:
                            System.out.println("error");
                    }
                    break;
                case 3:
                    end = true;
                    break;
                default:
                    System.out.println("La opción escogida no es valida!");
            }
            // Esperar a la tecla enter para continuar ----------
            System.out.println("Persione enter para continuar!");
            try {
                int x = System.in.read();
            } catch (Exception e) {
                System.out.println("Error!");
            }
            // --------------------------------------------------
        }
        sc.close();
    }

}
