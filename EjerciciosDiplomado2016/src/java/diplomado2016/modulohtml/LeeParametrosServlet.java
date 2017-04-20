package diplomado2016.modulohtml;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LeeParametrosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*PrintWriter out = response.getWriter();
        out.println("<html>");        
        out.println("<body>");        
        out.println("El servlet Recibió una petición GET");        
        out.println("</body>");        
        out.println("</html>");    
        out.close();*/
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");        
        out.println("<body>");        
        out.println("El servlet Recibió una petición POST");  
        
        Enumeration<String> enu = request.getParameterNames();
        while(enu.hasMoreElements()){
            String nombreParam = enu.nextElement();
            String[] valores = request.getParameterValues(nombreParam);
            if (valores != null )
                for (int i=0; i <  valores.length; i++)
                    out.println("<br>"+nombreParam + " = " + valores[i]);
        }
         
        out.println("</body>");        
        out.println("</html>");    
        out.close();
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
