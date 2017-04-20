
package demostracionfile;

import java.util.Scanner;

public class Principal {
    
    public static void main(String []args)
    {
        Scanner input = new Scanner(System.in);
        DemostracionFile aplicacion = new DemostracionFile();
        
        System.out.println("Escriba el nombre del archivo o directorio");
        aplicacion.analizaRuta(input.nextLine());
    }
    
}
