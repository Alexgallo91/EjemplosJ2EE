package FactorialRecursividad;


public class Factorial {
    
    public long factorial(long numero)
    {
        if(numero <= 1)
        {
            return 1;
        }
        else
        {
            return numero * factorial(numero - 1);
        }
    }
    
    public void mostrarFactoriales()
    {
        for(int contador = 0; contador <= 10; contador++)
        {
            System.out.printf("%d! = %d\n",contador, factorial(contador) );
        }
    }
}
