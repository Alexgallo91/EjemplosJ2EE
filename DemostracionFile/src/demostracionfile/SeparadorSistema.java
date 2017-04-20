
package demostracionfile;

import java.io.File;

public class SeparadorSistema {
    
    private static String separador;
    
    private static void establecerSeparadorDelSistema()
    {
        separador = File.separator;
    }
    
    public static String obtenerSeparadorDelSistema()
    {
        establecerSeparadorDelSistema();
        return separador;
    }
    
}
