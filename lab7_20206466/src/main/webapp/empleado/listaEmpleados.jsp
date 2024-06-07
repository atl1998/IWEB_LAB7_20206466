<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/06/2024
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab7_20206466.beans.Employees" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="lista" scope="request" type="ArrayList<Employees>" />
<html>
    <head>
        <title>Empleados</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
              crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <div class="clearfix mt-3 mt-2">
                <a href="<%=request.getContextPath()%>/EmployeeServlet">
                    <h1 class="float-start link-dark">Lista de Empleados</h1>
                </a>
                <a class="btn btn-primary float-end mt-1" href="<%=request.getContextPath() %>/EmployeeServlet?action=new">Registrar empleado</a>
            </div>
            <hr/>
            <form method="post" action="<%=request.getContextPath()%>/EmployeeServlet?action=s">
                <div class="form-floating mt-3">
                    <input type="text" class="form-control" id="floatingInput"
                           placeholder="Por ID o por nombre" name="textoBuscar" value="<%= request.getAttribute("busqueda") != null ? request.getAttribute("busqueda") : "" %>">
                    <label for="floatingInput">Buscar empleado</label>
                </div>
            </form>
            <table class="table table-striped mt-3">
                <tr class="table-primary">
                    <th>ID</th>
                    <th>nombre completo</th>
                    <th>Correo</th>
                    <th>Numero</th>
                    <th>Fecha</th>
                    <th>ID Trabajo</th>
                    <th>Salario</th>
                    <th></th>
                    <th></th>
                </tr>
                <% for (Employees empleado : lista) { %>
                <tr>
                    <td><%=empleado.getEmployeeId()  %>
                    </td>
                    <td><%=empleado.getFullNameEmployee()%>
                    </td>
                    <td><%=empleado.getCorreo()%>
                    </td>
                    <td><%=empleado.getNumeroDeTelefono()%>
                    </td>
                    <td><%=empleado.getHire_date()%>
                    </td>
                    <td><%=empleado.getJobId()%>
                    </td>
                    <td><%=empleado.getSalario()%>
                    </td>
                    <td><a class="btn btn-success" href="<%=request.getContextPath()%>/EmployeeServlet?action=edit&id=<%= empleado.getEmployeeId() %>">Editar</a></td>
                    <td><a onclick="return confirm('¿Esta seguro de borrar?')" class="btn btn-danger" href="<%=request.getContextPath()%>/EmployeeServlet?action=del&id=<%= empleado.getEmployeeId() %>">Borrar</a></td>
                </tr>
                <% } %>
            </table>
        </div>
    </body>
</html>
