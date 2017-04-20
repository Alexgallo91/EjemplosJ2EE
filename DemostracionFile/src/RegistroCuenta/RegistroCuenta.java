
package RegistroCuenta;

public class RegistroCuenta 
{
    private int cuenta;
    private String nombre;
    private String apellido;
    private double saldo;
    
    //constructor sin parametros
    public RegistroCuenta()
    {
        this(0,"","" ,0.0);
    }
    
    //constructor con 4 paramentos
    public RegistroCuenta(int cuenta, 
            String nombre, String apellido, double saldo)
    {
        this.setCuenta(cuenta);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setSaldo(saldo);
    }

    //METODOS PARA ESTABLECER LOS CAMPOS PRIVATE DE LA CLASE    
    public void setCuenta(int cuenta)
    {
        this.cuenta = cuenta;
    }
    
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }
    
    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }
    
    public void setSaldo(double saldo)
    {
        this.saldo = saldo;
    }
    
    //METODOS PARA OBTENER LOS VALORES DE LOS CAMPOS DE LA CLASE
    public int getCuenta()
    {
        return this.cuenta;
    }
    
    public String getNombre()
    {
        return this.nombre;
    }
    
    public String getApellido()
    {
        return this.apellido;
    }
    
    public double getSaldo()
    {
        return this.saldo;
    }
}
