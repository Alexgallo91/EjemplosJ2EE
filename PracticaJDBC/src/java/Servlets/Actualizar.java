package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Actualizar extends HttpServlet {
    
    private final String URL = "jdbc:postgresql://localhost:5432/Diplomado";
    private final String USER = "postgres";
    private final String PASS = "admin";
    private Connection con;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");    
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    /*USAREMOS EL METODO DOPOST PARA REALIZAR LAS OPERACIONES*/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<head>");
        out.println("<title>Actualizar tabla</title>");
        out.println("</head>");
        out.println("<body>");
       
        out.println("<h1>SERVLET ACTUALIZACION DE LA TABLA</h1>");
        
        Class driver; //si se usa driver (aunque diga que no el IDE)
        Statement query; //objeto statement para llevar acabo instrucciones SQL
        String llenarTabla; //String de la orden en SQL
        ResultSet miTabla; //resultados obtenidos de la sentencia
        HttpSession miSesion; //sesion del usuario
        int arregloRegistros[]; //arreglo de registros ejecutados por el batch
        
        /*En caso de haber dado clic en simular, nos mandara al servlet simular,
        sino seguir procesando el request en actualizar*/
        if(request.getParameter("opcion").equals("simular"))
        {request.getRequestDispatcher("Simular").forward(request, response);}
        
        //iniciamos sesion
        miSesion = request.getSession();
        
        /*obtenemos el numero de registros que establecimos en nuestra sesion*/
        int numeroRegistros = (Integer)miSesion.getAttribute("contadorRegistros");
        
        out.println("<h1>El contador de registros es: " + numeroRegistros + "</h1>");
        
         /*establecemos conexion a la base de datos*/
        try {
            driver = Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
            query = con.createStatement();
            con.setAutoCommit(false);
            
            String querySQL; //sentencia que se agregara al batch
            
            for (int i = 0; i < numeroRegistros; i++) 
            {
                String id = "id";
                String departamento = "d";
                String ajuste = "a";
                String salario = "s";
                id += Integer.toString(i + 1);
                departamento += Integer.toString(i + 1);
                ajuste += Integer.toString(i + 1);
                salario += Integer.toString(i + 1);
                
                //valores para el query
                double salarioD;
                double ajusteD;
                int departamentoI;
                int idEmpleadoI;

                out.println("<br>valor de id: " + request.getParameter(id) + ", departamento: "
                        + request.getParameter(departamento) + ", ajuste: ");

                int a = 0;

                if (request.getParameter(ajuste).equals("")) 
                {
                    out.println(a);
                } 
                else 
                {
                    a = Integer.parseInt(request.getParameter(ajuste));
                    out.println(a);
                }

                out.println(", salario: " + request.getParameter(salario));
                
                //obtenemos los valores para las variables del query
                idEmpleadoI = Integer.parseInt(request.getParameter(id));
                departamentoI = Integer.parseInt(request.getParameter(departamento));
                salarioD = Double.parseDouble(request.getParameter(salario));
                ajusteD = a/100.00;
                double salarioConAjuste = salarioD + (salarioD * ajusteD);
                
                out.println("AJUSTE: " + ajusteD);
                //creamos la sentencia SQL
                querySQL = String.format(
                        "update employees set salary = %.2f, department_id = %d where "
                                + "employee_id = %d;", salarioConAjuste, departamentoI, idEmpleadoI);
                
                out.println(querySQL);
                
                //añadimnos la sentencia al batch al query (statement)
                query.addBatch(querySQL);
            }
            //al terminar el ciclo for se ejecuta el batch
            arregloRegistros = query.executeBatch();
            con.commit();
            query.clearBatch();
            
            //cerramos la conexion
            con.close();
            
            out.println("</body>");
            out.println("</html>");
            
            //nos redirige a la pagina que estabamos
            response.sendRedirect("Home?inicial="+request.getParameter("inicial"));
            
            out.close();
        }
        catch(ClassNotFoundException e)
        {
            out.println("<h1>ERROR: "+e.getMessage()+"</h1>");
        }
        catch(SQLException e)
        {
            out.println("<h1>ERROR: "+e.getMessage()+"</h1>");
        }  
        catch(Exception e)
        {
            out.println("<h1>ERROR: "+e.getMessage()+"</h1>");
            out.println(e.getLocalizedMessage());
            out.println(e.toString());
            out.println(e.hashCode());
        }
    }

    @Override
    public String getServletInfo() 
    {
        return "Short description";
    }

}
