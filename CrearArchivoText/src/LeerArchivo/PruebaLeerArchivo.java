
package LeerArchivo;

public class PruebaLeerArchivo {
    
    public static void main(String []args)
    {
        LeerArchivo app = new LeerArchivo();
        
        app.abrirArchivo();
        app.leerRegistros();
        app.cerrarArchivo();
    }
    
}
