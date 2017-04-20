
package LeerArchivo;

import CrearArchivo.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LeerArchivo {
    
    private Scanner entrada;
    
    public void abrirArchivo()
    {
        try
        {
            entrada = new Scanner(new File("Clientes.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.err.println("No se encuentra el archivo o no se puede abrir");
            System.exit(1);
        }
    }
    
    public void leerRegistros()
    {
        Cliente miCliente = new Cliente();
        System.out.printf("%-9s%-15s%-18s%10s\n",
                        "CUENTA", "NOMBRE",
                        "APELLIDO", "SALDO");
        try
        {
            while(entrada.hasNext())
            {
                miCliente.setCuenta(entrada.nextInt());
                miCliente.setNombre(entrada.next());
                miCliente.setApellido(entrada.next());
                miCliente.setSaldo(entrada.nextDouble());

                System.out.printf("%-9d%-15s%-18s%10.2f\n",
                        miCliente.getCuenta(), miCliente.getNombre(),
                        miCliente.getApellido(), miCliente.getSaldo());
            }
        }
        catch(NoSuchElementException e)
        {
            System.err.println("El archivo no esta bien formado");
            entrada.close();
            System.exit(1);
        }
        catch(IllegalStateException e)
        {
            System.err.println("Error al leer del archivo");
            System.exit(1);
        }
    }
    
    public void cerrarArchivo()
    {
        if(entrada!=null)
        {
            entrada.close();
        }
    }
    
}
