
package Prueba;

import Enum.DepartamentosEnum;

public class Prueba {
    
    public static void main(String []args)
    {
        
        DepartamentosEnum dep = DepartamentosEnum.ACCOUNTING;

        for (DepartamentosEnum departamentos : dep.values()) 
        {
            System.out.println(departamentos.getNumeroDep());
        }
                    
    }
    
}
