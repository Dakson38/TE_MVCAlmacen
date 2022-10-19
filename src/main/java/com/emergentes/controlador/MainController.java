package com.emergentes.controlador;

import com.emergentes.modelo.Almacen;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CJ
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                HttpSession session = request.getSession();
        
        if(session.getAttribute("listaalma") == null){
            //Creamos la conexion y la almacenamos en el objeto session
            ArrayList<Almacen> listaux = new ArrayList<Almacen>();
            //Colocar listaaux como atributo de sesion
            session.setAttribute("listaalma", listaux);
        }
        
        ArrayList<Almacen> lista = (ArrayList<Almacen>)session.getAttribute("listaalma");
        
        String op = request.getParameter("op");
        String opcion = op != null ? op : "view";
        
        Almacen almacen = new Almacen();
        int id, pos;
        
        switch (opcion) {
            case "nuevo":
                request.setAttribute("almacen", almacen);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "editar":
                id = Integer.parseInt(request.getParameter("id"));
                pos = buscarIndice(request, id);
                almacen = lista.get(pos);
                request.setAttribute("almacen", almacen);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            case "eliminar":
                pos = buscarIndice(request, Integer.parseInt(request.getParameter("id")));
                lista.remove(pos);
                session.setAttribute("listaalma", lista);
                response.sendRedirect("index.jsp");
                break;
            case "view":
                response.sendRedirect("index.jsp");
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                HttpSession session = request.getSession();
        ArrayList<Almacen> lista = (ArrayList<Almacen>)session.getAttribute("listaalma");
        
        Almacen almacen = new Almacen();
        int id = Integer.parseInt(request.getParameter("id"));
        String descripcion = request.getParameter("descripcion");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        double precio = Double.parseDouble(request.getParameter("precio"));
        
        almacen.setId(id);
        almacen.setDescripcion(descripcion);
        almacen.setCantidad(cantidad);
        almacen.setPrecio(precio);
        
        int idt = almacen.getId();
        if(idt == 0){
            int ultID;
            ultID = ultimoId(request);
            almacen.setId(ultID);
            lista.add(almacen);
        }else{
            lista.set(buscarIndice(request, idt), almacen);
        }
        
        session.setAttribute("listaalma", lista);
        response.sendRedirect("index.jsp");
    }

    private int ultimoId(HttpServletRequest request){
        HttpSession session = request.getSession();
        ArrayList<Almacen> lista = (ArrayList<Almacen>)session.getAttribute("listaalma");
        
        int idaux = 0;
        for (Almacen almacen : lista) {
            idaux = almacen.getId();
        }
        return idaux + 1;
    }
    
    private int buscarIndice(HttpServletRequest request, int id){
        HttpSession session = request.getSession();
        ArrayList<Almacen> lista = (ArrayList<Almacen>)session.getAttribute("listaalma");
        
        int i = 0;
        if(lista.size() > 0){
            while(i < lista.size()){
                if(lista.get(i).getId() == id){
                    break;
                }else{
                    i++;
                }
            }
        }
        return i;       
    }
}
