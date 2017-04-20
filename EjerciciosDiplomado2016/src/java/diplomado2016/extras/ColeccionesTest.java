/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomado2016.extras;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Chaz
 */
public class ColeccionesTest {
    public static void main (String[] args) throws Exception{
        Vector vector = new Vector(0);
        Objeto o = new Objeto();
        o.setClave("1");
        o.setPrecio(15);
        vector.addElement(o);
        vector.addElement(o);
        System.out.println(vector.size());
        for (int i=0; i < vector.size(); i++){
            Objeto o1 = (Objeto)vector.get(i);        
            System.out.println(o1.getClave()+"="+o1.getPrecio());
        }
        
        List lista = new ArrayList();
        lista.add(o);
        lista.add(o);
        lista.add(o);
        System.out.println("Inicio con List");
        for (Object o1: lista){
            System.out.println(((Objeto)o1).getClave()+
                    "="+((Objeto)o1).getPrecio());
        }
    }
    
    static class Objeto{
        private String clave;
        private double precio;

        public String getClave() {
            return clave;
        }

        public void setClave(String clave) {
            this.clave = clave;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }
        
        
    }
}
