
package torresDeHanoi;

public class PruebaTorresDeHanoi 
{
    public static void main(String []args)
    {
        int agujaInicial = 1;
        int agujaFinal = 3;
        int agujaTemp = 2;
        int totalDiscos = 3;
        
        TorresDeHanoi torresDeHanoi = new TorresDeHanoi(totalDiscos);
        torresDeHanoi.resolverTorres(totalDiscos, agujaInicial, agujaFinal, agujaTemp);
    }
}
