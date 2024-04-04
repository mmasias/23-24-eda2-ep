package clases;

import java.util.Scanner;

public class MyScanner{
    
    private Scanner scanner;

    public MyScanner(){
        scanner = new Scanner(System.in);
    }

    public String nextLine(){
        scanner= new Scanner(System.in);
        return scanner.nextLine();
    }

    public String nextLine(String mensaje){
        scanner= new Scanner(System.in);
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public long nextLong(){
        scanner= new Scanner(System.in);
        return scanner.nextLong();
    }

    public long nextLong(String mensaje){
        scanner= new Scanner(System.in);
        System.out.print(mensaje);
        return scanner.nextLong();
    }
    
    public int nextInt(){
        return scanner.nextInt();
    }

    public double nextDouble(){
        return scanner.nextDouble();
    }

    public void close(){
        scanner.close();
    }

    public int nextInt(int min, int max) {
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
    public int nextInt(String mensaje, int min, int max) {
        int valor;
        while (true) {
            scanner= new Scanner(System.in);
            System.out.println(mensaje);
            try {
                valor = scanner.nextInt();
                if (valor >= min && valor <= max) {
                    break;
                } else {
                    System.out.println("El valor no está dentro del rango especificado. Intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intente de nuevo.");
              
            }
        }
        return valor;
    }
    
}
