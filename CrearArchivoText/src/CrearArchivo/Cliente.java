package CrearArchivo;

public class Cliente {
	
	private int cuenta;
	private String nombre;
	private String apellido;
	private double saldo;
	
	public Cliente()
	{
		this(0,"","",0.0);
	}
	
	public Cliente(int cuenta, String nombre, String apellido, double saldo)
	{
		this.setCuenta(cuenta);
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setSaldo(saldo);
	}
	
	public void setCuenta(int cuenta)
	{
		this.cuenta = cuenta;
	}
	
	public int getCuenta()
	{
		return this.cuenta;
	}
	
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public void setApellido(String apellido)
	{
		this.apellido = apellido;
	}
	
	public String getApellido()
	{
		return this.apellido;
	}
	
	public void setSaldo(double saldo)
	{
		this.saldo = saldo;
	}
	
	public double getSaldo()
	{
		return this.saldo;
	}
}
