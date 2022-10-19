<%-- 
    Document   : index
    Created on : 18 oct. 2022, 21:56:37
    Author     : CJ
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Almacen"%>
<%
    ArrayList<Almacen> lista = (ArrayList<Almacen>)session.getAttribute("listaalma");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado</title>
    </head>
    <body>
        <h1>Listado de Almacen</h1>
        <a href="MainController?op=nuevo">Nuevo</a>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>DESCRIPCION</th>
                <th>CANTIDAD</th>
                <th>PRECIO</th>
                <th></th>
                <th></th>
            </tr>
            <%
                if(lista != null){
                    for (Almacen almacen : lista) {
            %>
                <tr>
                    <td><%=almacen.getId() %></td>
                    <td><%=almacen.getDescripcion()%></td>
                    <td><%=almacen.getCantidad()%></td>
                    <td><%=almacen.getPrecio()%></td>
                    <td><a href="MainController?op=editar&id=<%=almacen.getId() %>">Modificar</a></td>
                    <td><a href="MainController?op=eliminar&id=<%=almacen.getId() %>"
                           onclick="return confirm("Esta seguro de eliminar?")">Eliminar</a></td>
                </tr>
            <%
                    }
                }
            %>
        </table>
    </body>
</html>
