package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class OrderDepartamento extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        processRequest(request, response);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        //indice de pagina segun la letra inicial del nombre
        String indiceLetra = request.getParameter("inicial");
        boolean ordenarPorDepartamento = true;
 
        out.println("<h2>El indice de la letra es: "+indiceLetra+"</h2>");
        out.println("<h2>Boolean ordenamiento: "+ordenarPorDepartamento+"</h2>");
        
        response.sendRedirect("Home?inicial="+indiceLetra+"&ordenarPorDepartamento="+ordenarPorDepartamento);
        
        out.close();
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
