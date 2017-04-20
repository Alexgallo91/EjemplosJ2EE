
package demostracionfile;

import java.io.File;
public class DemostracionFile {

   public void analizaRuta(String ruta)
   {
       File nombre = new File(ruta);
       
       if(nombre.exists())
       {
           System.out.println(nombre.getName() + " existe");
           System.out.println((nombre.isFile() ? "es un archivo" : "no es un archivo"));
           System.out.println((nombre.isDirectory() ? "es un directorio" : "no es un directorio"));
           System.out.println((nombre.isAbsolute()?"es una ruta absoluta" : "no es una ruta absoluta"));
           System.out.println("ultima modificacion: " + nombre.lastModified());
           System.out.println("Tamaño. " + nombre.length());
           System.out.println("ruta: " + nombre.getPath());
           System.out.println("ruta absoluta: " + nombre.getAbsolutePath());
           System.out.println("carpeta padre: " + nombre.getParent());
           
           if(nombre.isDirectory())
           {
               String[] archivos = nombre.list();
               System.out.println("\nARCHIVOS CONTENIDOS EN " + nombre.getName().toUpperCase());
                
               for(String archivo : archivos)
               {
                   System.out.println(archivo);
               }
           }
       }
       else //en caso de que no existe al archivo
       {
           System.out.println(ruta + " no existe");
       }
       
       System.out.println("separador del sistema '" + SeparadorSistema.obtenerSeparadorDelSistema()+"'");
   }
    
}
