package CrearArchivo;

public class PruebaCrearArchivo {
	
	public static void main(String []args)
	{
		CrearArchivo app = new CrearArchivo();
		
		app.AbrirArchivo();
		app.IngresarRegistros();
		app.cerrarArchivo();
	}

}
