
package CalcularFactorial;


public class CalcularFactorial {
    
    public long factorial(long numero)
    {
        long resultado = 1;
        
        for(long i = numero; i >= 1; i--)
        {
            resultado *= i;
        }
        return resultado;
    }
    
    public void mostrarFactoriales()
    {
        for(int contador = 0; contador <= 10; contador++)
        {
            System.out.printf("%d! = %d\n",contador, factorial(contador));
        }
    }
}
