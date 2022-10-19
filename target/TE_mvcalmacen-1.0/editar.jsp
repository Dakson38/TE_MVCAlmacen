<%-- 
    Document   : editar
    Created on : 18 oct. 2022, 21:56:45
    Author     : CJ
--%>

<%@page import="com.emergentes.modelo.Almacen"%>
<%
    Almacen almacen = (Almacen)request.getAttribute("almacen");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Almacen</title>
    </head>
    <body>
        <h1><%=(almacen.getId())==0 ? "Nuevo Registro":"Editar Registro" %></h1>
        <<form action="MainController" method="post">
            <input type="hidden" name="id" value="<%= almacen.getId() %>">
            <table border="1">
                <tr>
                    <td>Descripción</td>
                    <td><input type="text" name="descripcion" value="<%=almacen.getDescripcion()%>"></td>
                </tr>
                <tr>
                    <td>Cantidad</td>
                    <td><input type="text" name="cantidad" value="<%=almacen.getCantidad()%>"></td>
                </tr>
                <tr>
                    <td>Precio</td>
                    <td><input type="text" name="precio" value="<%=almacen.getPrecio()%>"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
