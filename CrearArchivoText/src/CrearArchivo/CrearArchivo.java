package CrearArchivo;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CrearArchivo {
	
	private Cliente miCliente = new Cliente();
	private Formatter salida;
	
	public void AbrirArchivo()
	{
		try
		{
			salida = new Formatter("Clientes.txt");
		}
		catch(FileNotFoundException e)
		{
			System.err.println("No se encuentra el archivo, "
					         + "o no se puede crear");
			System.exit(1);
		}
		catch(SecurityException e)
		{
			System.out.println("El archivo esta protegido");
			System.exit(1);
		}
	}
	
	public void IngresarRegistros()
	{
		Scanner input = new Scanner(System.in);
		
		int a = 1;
		while(a==1)
		{
			System.out.println("Ingresa el numero de cuenta,"
			         + " el nombre, el apellido y el saldo "
			         + "del cliente\n?");
			
			try
			{
				miCliente.setCuenta(input.nextInt());
				miCliente.setNombre(input.next());
				miCliente.setApellido(input.next());
				miCliente.setSaldo(input.nextDouble());
				
				if(miCliente.getCuenta() > 0)
				{
					salida.format("%d %s %s %.2f\n", miCliente.getCuenta(), 
							miCliente.getNombre(), miCliente.getApellido(),
							miCliente.getSaldo());
				}
				else
				{
					System.err.println("Ingresa un numero de "
								     + "cuenta mayor a 0!!!");
					continue;
				}
			}
			catch(FormatterClosedException e) 
			{
				System.err.println("Error al escribir en el archivo");
				return;
			}
			catch(NoSuchElementException e)
			{
				System.err.println("Entrada invalida, intente de nuevo");
				input.nextLine();
			}
			
			int b;
			while(true)
			{
				System.out.print("Desea ingresar otro registro?,"
						+ "\n1 para si\n2 para no ");
				
				try
				{
					b = input.nextInt();
					
					if(b > 2 || b < 0)
					{
						System.out.println("Tiene que ser un "
								         + "numero menor a 2 y mayor a 0");
						continue;
					}
					else
					{
						break;
					}
				}
				catch (InputMismatchException e) 
				{
					System.err.println("Ingresa solo numeros, no caracteres");
					input.nextLine();
					continue;
				}
			}
			
			if(b == 2)
			{
				a = 2;
			}
		}
	}
	
	public void cerrarArchivo()
	{
		if(salida != null)
		{
			salida.close();
		}
		
		System.out.println("FIN DEL PROGRAMA");
	}

}
