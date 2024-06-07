<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 7/06/2024
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="employee" type="com.example.lab7_20206466.beans.Employees" scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
              crossorigin="anonymous">
        <title>Editar un empleado</title>
    </head>
    <body>
        <div class='container'>
            <h1 class='mb-3'>Editar los datos de un empleado</h1>
            <form method="post" action="<%=request.getContextPath()%>/EmployeeServlet?action=e">
                <div class="mb-3">
                    <input type="hidden" class="form-control" name="editarEmployeeIdJSP" value="<%=employee.getEmployeeId()%>">
                </div>
                <div class="mb-3">
                    <label>Nombre</label>
                    <input type="text" class="form-control" name="editarNombreJSP" value="<%=(employee.getFullNameEmployee() != null ? employee.getFullNameEmployee().split(" ")[0] : "") %>">
                </div>
                <div class="mb-3">
                    <label>Apellido</label>
                    <input type="text" class="form-control" name="editarApellidoJSP" value="<%=(employee.getFullNameEmployee() != null ? employee.getFullNameEmployee().split(" ")[1] : "") %>">
                </div>

                <div class="mb-3">
                    <label>correo</label>
                    <input type="text" class="form-control" name="editarCorreoJSP" value="<%=employee.getCorreo()%>">
                </div>
                <div class="mb-3">
                    <label>password</label>
                    <input type="text" class="form-control" name="editarPasswordJSP" value="<%=employee.getPassword()%>">
                </div>
                <div class="mb-3">
                    <label>telefono</label>
                    <input type="text" class="form-control" name="editarTelefonoJSP" value="<%=employee.getNumeroDeTelefono()%>">
                </div>
                <div class="mb-3">
                    <label>fecha</label>
                    <input type="text" class="form-control" name="editarFechaJSP" value="<%=employee.getHire_date()%>">
                </div>
                <div class="mb-3">
                    <label>Id del job</label>
                    <input type="text" class="form-control" name="editarJobIdJSP" value="<%=employee.getJobId()%>">
                </div>
                <div class="mb-3">
                    <label>Salario</label>
                    <input type="text" class="form-control" name="editarSalarioJSP" value="<%=employee.getSalario()%>">
                </div>
                <a href="<%=request.getContextPath()%>/EmployeeServlet" class="btn btn-danger">Regresar</a>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
                crossorigin="anonymous"></script>
    </body>
</html>
