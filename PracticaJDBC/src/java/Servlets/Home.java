package Servlets;

import Enum.DepartamentosEnum;
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
import java.util.ArrayList;

public class Home extends HttpServlet {

    private final String URL = "jdbc:postgresql://localhost:5432/Diplomado";
    private final String USER = "postgres";
    private final String PASS = "admin";
    private Connection con;
    private boolean ordenarPorID = false;
    private boolean ordenarPorNombre = false;
    private boolean ordenarPorSalario = false;
    private boolean ordenarPorDepartamento = false;
    //private int simular = 0;
    private ArrayList<Double>salarioAjuste;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    }

    /*GET*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        
        /*DECICIONES POR ORDENAMIENTO*/
       
        ordenarPorID = Boolean.valueOf(request.getParameter("ordenarPorID"));
        ordenarPorNombre = Boolean.valueOf(request.getParameter("ordenarPorNombre"));
        ordenarPorSalario = Boolean.valueOf(request.getParameter("ordenarPorSalario"));
        ordenarPorDepartamento = Boolean.valueOf(request.getParameter("ordenarPorDepartamento"));
        
        Class driver; //si se usa driver (aunque diga que no el IDE)
        Statement query; //objeto statement para llevar acabo instrucciones SQL
        String llenarTabla; //String de la orden en SQL
        ResultSet miTabla; //resultados obtenidos de la sentencia
        HttpSession miSesion; //sesion del usuario
        Boolean simularTabla = false; //decision para simular la tabla

        int contadorRegistros = 0; //contador para los registros
        String indiceLetra; // obtenemos el indice de pagina (la letra inicial del nombre)
        String ordenamiento;/*query para ordenar la tabla*/

        //Escribimos la parte principal del documento HTML
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>PracticaJDBC</title>");
        out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/tabla.css\">");
        out.println("<script src=\"js/simulacion.js\"></script>");
        out.println("<Style>");
        out.println("table{");
        out.println("margin: 0px auto;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        /*establecemos conexion a la base de datos*/
        try {
            driver = Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
            query = con.createStatement();

            /*establecemos la sesion*/
            miSesion = request.getSession();
           
            //obtenemos el indice de pagina 'letra', obteniendo la letra dentro 
            //de la url request
            indiceLetra = request.getParameter("inicial");
            
            //añadimos tambien la letra al request como un parametro
            request.setAttribute("indiceLetra", indiceLetra);

            if (indiceLetra == null || indiceLetra.equals("")) {
                indiceLetra = "A";
            }
            
            /*de la sesion actual obtenemos
            lo que es la tabla con nuestros registros*/
            miTabla = (ResultSet) miSesion.getAttribute("miTabla");

            if (miTabla == null) {
                llenarTabla = "select * from employees;";
                miTabla = query.executeQuery(llenarTabla);

                /*guardamos la tabla en nuestra sesion*/
                miSesion.setAttribute("miTabla", miTabla);
            }
            
            //ESTA PARTE DEL CODIGO HACE ORDENAR LAS PAGINAS
            //CONFORME A QUE LETRA PRINCIPAL DEL NOMBRE DEL EMPLEADO
            //TENGA
            llenarTabla = "select * from employees where first_name like '"+indiceLetra+"%'";
            miTabla = query.executeQuery(llenarTabla);
            
            if(ordenarPorID == true)
            {
                llenarTabla = "select * from employees where first_name like '"+indiceLetra+"%' order by employee_id";
                miTabla = query.executeQuery(llenarTabla);
            }
            else if(ordenarPorNombre == true)
            {
                llenarTabla = "select * from employees where first_name like '"+indiceLetra+"%' order by first_name";
                miTabla = query.executeQuery(llenarTabla);
            }
            else if(ordenarPorSalario == true)
            {
                llenarTabla = "select * from employees where first_name like '"+indiceLetra+"%' order by salary";
                miTabla = query.executeQuery(llenarTabla);
            }
            else if(ordenarPorDepartamento == true)
            {
                llenarTabla = "select * from employees where first_name like '"+indiceLetra+"%' order by department_id";
                miTabla = query.executeQuery(llenarTabla);
            }
            
            /*desarrollo del table*/
            out.println("<table id=\"tablaBD\">");
            out.println("<tr>");

            /*ID EMPLEADO*/
            out.println("<th>");
            out.println("<a href=\"OrderIDEmpleado?inicial=" + indiceLetra + "\">ID EMPLEADO</a>");
            out.println("</th>");

            /*NOMBRE*/
            out.println("<th>");
            out.println("<a href=\"OrderNombre?inicial=" + indiceLetra + "\">NOMBRE</a>");
            out.println("</th>");

            /*DEPARTAMENTO*/
            out.println("<th>");
            out.println("<a href=\"OrderDepartamento?inicial=" + indiceLetra + "\">DEPARTAMENTO</a>");
            out.println("</th>");

            /*AJUSTE*/
            out.println("<th>");
            out.println("<h4>AJUSTE</h4>");
            out.println("</th>");

            /*SALARIO*/
            out.println("<th>");
            out.println("<a href=\"OrderSalario?inicial=" + indiceLetra + "\">SALARIO</a>");
            out.println("</th>");

            /*SALARIO AJUSTADO*/
            out.println("<th>");
            out.println("<h4>SALARIO AJUSTADO</h4>");
            out.println("</tr>");

            out.println("<form action=\"Actualizar?inicial="+indiceLetra+"\" method=\"post\">");

            ArrayList<Integer> id = new ArrayList<Integer>();

            /*TODA LA MIERDA DEL FORM*/
            while (miTabla.next()) {
                //incrementa el contador de registros
                contadorRegistros++;

                /*id del empleado*/
                out.println("<tr>");
                out.println("<td>");
                out.println("<input type=\"text\" name=\"id" + contadorRegistros + "\" value=\"" + miTabla.getString("employee_id") + "\" readonly>");

                /*añadimos el id al arraylist */
                id.add(Integer.parseInt(miTabla.getString("employee_id")));

                out.println("</td>");

                /*Nombre del empleado*/
                out.println("<td>");
                out.println(miTabla.getString("first_name") + " " + miTabla.getString("last_name"));
                out.println("</td>");

                /*Nombre del departamento*/
                out.println("<td>");

                int numeroDepartamento = Integer.parseInt(miTabla.getString("department_id"));
                DepartamentosEnum dep = DepartamentosEnum.ACCOUNTING;

                out.println("<select name=\"d" + contadorRegistros + "\">");
                for (DepartamentosEnum departamentos : dep.values()) {
                    if (numeroDepartamento != departamentos.getNumeroDep()) {
                        out.println("<option value=\"" + departamentos.getNumeroDep() + "\">");
                        out.println(departamentos.toString() + " " + departamentos.getNumeroDep());
                        out.println("</option>");
                    } else if (numeroDepartamento == departamentos.getNumeroDep()) {
                        out.println("<option value=\"" + departamentos.getNumeroDep() + "\" selected>");
                        out.println(departamentos.toString() + " " + departamentos.getNumeroDep());
                        out.println("</option>");
                    }
                }
                out.println("</select");
                out.println("</td>");
                
                /*ESTABLECEMOS LAS ID PARA USARLAS EN CONDIGO JAVASCRIPT*/
                
                /*% de ajuste*/
                out.println("<td>");
                out.println("<input type=\"number\" id=\"a"+contadorRegistros+"\" name=\"a" + contadorRegistros + ""
                        + "\" min=\"-100\" max=\"100\" value=0>");
                out.println("</td>");

                /*salario*/
                out.println("<td>");
                out.println("<input type=\"text\" id=\"s"+contadorRegistros+"\" name=\"s" + contadorRegistros + "\" "
                        + "value=\"" + miTabla.getString("salary") + "\" readonly>");
                out.println("</td>");

                /*salario ajustado*/
                out.println("<td>");
                out.println("<input type=\"text\" id=\"sa"+contadorRegistros+"\" name=\"sa" + contadorRegistros + "\" "
                        + "value=\"" + /*AQUI NECESITA EL VALOR DE SALARIO AJUSTADO*/0 + "\" readonly>");
                out.println("</td>");

                /*fin de la fila*/
                out.println("</tr>");
            }
            /*establecemos el arraylist en la session*/
            miSesion.setAttribute("id", id);

            //out.println("</form>");
            out.println("</table>");

            /*los dos input para los cambios de la tabla*/
            out.println("<div class=\"botones\">");//////////LE QUITE LA ETIQUETA DEL FORM 
            out.println("<input type=\"submit\" value=\"actualizar\" name=\"opcion\" >");
            /*boton manejado con javascript para manejar el salario simulado*/
            out.println("<button type=\"button\" onclick=\"establecerSalarioConAjuste()\">Simular</button>");
            //out.println("<input type=\"submit\" value=\"simular\" name=\"opcion\" >");
            out.println("</form>");
            out.println("</div>");

            /*navegacion por indice (letras)*/
            out.println("<center>");

            /*arreglo de chars*/
            char[] abecedario = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
                'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                'V', 'W', 'X', 'Y', 'Z'};

            for (char letras : abecedario) {
                String let = String.valueOf(letras);
                if (letras != 'Z') {
                    out.printf("<a href=\" ?inicial=%s\">%s</a>, ", let, letras);
                } else {
                    out.printf("<a href=\" ?inicial=%s\">%s</a>", let, letras);
                }
            }
            out.println("</center>");

            /*Mumero de registros*/
            out.println("<h2>Numero Registros: " + contadorRegistros + "</h2>");
            out.println("<input type=\"hidden\" name=\"nRegistros\" id=\"numeroRegistros\" value=\""+contadorRegistros+"\">");
            
            /*En nuestra sesion, establecemos el numero de registros como un atributo*/
            miSesion.setAttribute("contadorRegistros", contadorRegistros);

        } catch (ClassNotFoundException e) {
            out.println("<h1>" + e.getMessage() + "</h1>");
        } catch (SQLException e) {
            out.println("<h1>" + e.getMessage() + "</h1>");
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                out.println("<h1>" + e.getMessage() + "</h1>");
            }
        }
        
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
