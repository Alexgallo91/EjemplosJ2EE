
package torresDeHanoi;

public class TorresDeHanoi 
{
    private int numDiscos;
    
    public TorresDeHanoi(int discos)
    {
        numDiscos = discos;
    }
    
    public void resolverTorres(int discos, int agujaOrigen, 
                               int agujaDestino, int agujaTemp)
    {
        if(discos == 1)
        {
            System.out.printf("\n%d --> %d", agujaOrigen, agujaDestino);
            return;
        }
        
        resolverTorres(discos - 1, agujaOrigen, agujaTemp, agujaDestino);
        
        System.out.printf("\n%d --> %d", agujaOrigen, agujaDestino);
        
        resolverTorres(discos - 1, agujaTemp, agujaDestino, agujaOrigen);
    }

}
