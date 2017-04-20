package Fibonacci;

public class Fibonacci {
    
    public long fibonacci(long numero)
    {
        if((numero == 0) || (numero == 1))
        {
            return 1;
        }
        else
        {
            return fibonacci(numero - 1) + fibonacci(numero - 2);
        }
    }
    
    public void mostrarFibonacci()
    {
        for(int contador = 0; contador <= 10; contador++)
        {
            System.out.printf("Fibonacci de %d es: %d\n",
                              contador, fibonacci(contador));
        }
    }
    
}
