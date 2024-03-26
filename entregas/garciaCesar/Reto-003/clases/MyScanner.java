package clases;

import java.util.Scanner;

public class MyScanner{
    
    private Scanner scanner;

    public MyScanner(){
        scanner = new Scanner(System.in);
    }

    public String nextLine(){
        return scanner.nextLine();
    }

    public long nextLong(){
        return scanner.nextLong();
    }

    public int nextInt(){
        return scanner.nextInt();
    }

    public int nextInt2() {
        int valor;
        while (true) {
            try {
                valor = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intente de nuevo.");
                scanner.nextLine();
            }
        }
        return valor;
    }

    public double nextDouble(){
        return scanner.nextDouble();
    }

    public void close(){
        scanner.close();
    }

    public int nextIntInRange(int min, int max) {
        int valor;
        while (true) {
            try {
                valor = scanner.nextInt();
                if (valor >= min && valor <= max) {
                    break;
                } else {
                    System.out.println("El valor no está dentro del rango especificado. Intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intente de nuevo.");
                scanner.nextLine();
            }
        }
        return valor;
    }
    
}
