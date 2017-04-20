
package pruebaarraylist;

import java.util.ArrayList;

public class PruebaArrayList {


    public static void main(String[] args) {
        
        ArrayList<Integer> enteros = new ArrayList<>();
        
        System.out.println(enteros.size());
        
        enteros.add(2);
        
        System.out.println(enteros.size());
        System.out.println(enteros.get(0));
    }
    
}
