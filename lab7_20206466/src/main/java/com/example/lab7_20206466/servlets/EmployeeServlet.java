package com.example.lab7_20206466.servlets;

import com.example.lab7_20206466.beans.Employees;
import com.example.lab7_20206466.daos.DaoEmployee;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "EmployeeServlet", value = "/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        DaoEmployee employeeDao = new DaoEmployee();

        switch (action){
            case "lista":
                //utilizamos el metodo listar empleados de employeeDao
                ArrayList<Employees> list = employeeDao.listarEmpleados();

                //mandar la lista a la vista -> empleado/listaEmpleados.jsp
                request.setAttribute("lista",list);
                RequestDispatcher rd = request.getRequestDispatcher("empleado/listaEmpleados.jsp");
                rd.forward(request,response);
                break;
            case "new":
                request.getRequestDispatcher("empleado/nuevoEmpleado.jsp").forward(request,response);
                break;
            case "edit":
                String id1 = request.getParameter("id");
                Employees employee = employeeDao.buscarEmpleadoPorId(Integer.parseInt(id1));

                if(employee != null){
                    request.setAttribute("employee",employee);
                    request.getRequestDispatcher("empleado/editarEmpleado.jsp").forward(request,response);
                }else{
                    response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                }
                break;
            case "del":
                String id2 = request.getParameter("id");
                Employees employee2 = employeeDao.buscarEmpleadoPorId(Integer.parseInt(id2));

                if(employee2 != null){
                    try {
                        employeeDao.borrar(Integer.parseInt(id2));
                    } catch (SQLException e) {
                        System.out.println("Log: excepcion: " + e.getMessage());
                    }
                }
                response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");


        DaoEmployee employeeDao = new DaoEmployee();

        String action = request.getParameter("action") == null ? "crear" : request.getParameter("action");

        switch (action){

            case "crear"://vamos a registrar a un nuevo empleado
                String newEmployeeId = request.getParameter("nuevoEmployeeIdJSP");
                String newNombre = request.getParameter("nuevoNombreJSP");
                String newApellido = request.getParameter("nuevoApellidoJSP");
                String newCorreo = request.getParameter("nuevoCorreoJSP");
                String newFecha = request.getParameter("nuevaFechaJSP");
                String newJobId = request.getParameter("nuevoJobIdJSP");

                boolean isAllValid = true;

                if(newEmployeeId.length() > 100){
                    isAllValid = false;
                }

                if(isAllValid){

                    Employees empleado1 = employeeDao.buscarEmpleadoPorId(Integer.parseInt(newEmployeeId));

                    if(empleado1 == null){
                        employeeDao.registrarEmpleado(Integer.parseInt(newEmployeeId),newNombre,newApellido,newCorreo,newFecha,newJobId);
                        response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                    }else{
                        request.getRequestDispatcher("empleado/nuevoEmpleado.jsp").forward(request,response);
                    }
                }else{
                    request.getRequestDispatcher("empleado/nuevoEmpleado.jsp").forward(request,response);
                }
                break;

            case "e": //voy a actualizar

                String editEmployeeId = request.getParameter("editarEmployeeIdJSP");
                String editName = request.getParameter("editarNombreJSP");
                String editLastName = request.getParameter("editarApellidoJSP");
                String editFullName=editName+" "+editLastName;
                String editCorreo = request.getParameter("editarCorreoJSP");
                String editPassword = request.getParameter("editarPasswordJSP");
                String editTelefono = request.getParameter("editarTelefonoJSP");
                String editFecha = request.getParameter("editarFechaJSP");
                String editJobId = request.getParameter("editarJobIdJSP");
                String editSalario = request.getParameter("editarSalarioJSP");
                boolean isAllValid2 = true;

                if(editEmployeeId.length() > 35){
                    isAllValid2 = false;
                }
                /*

                if(jobId2.length() > 10){
                    isAllValid2 = false;
                }*/

                if(isAllValid2){
                    Employees empleado = new Employees();
                    empleado.setEmployeeId(Integer.parseInt(editEmployeeId));
                    empleado.setFullNameEmployee(editFullName);
                    empleado.setCorreo(editCorreo);
                    empleado.setPassword(editPassword);
                    empleado.setNumeroDeTelefono(editTelefono);
                    empleado.setHire_date(editFecha);
                    empleado.setJobId(editJobId);
                    empleado.setSalario(Integer.parseInt(editSalario));

                    employeeDao.actualizar(empleado);
                    response.sendRedirect(request.getContextPath() + "/EmployeeServlet");
                }else{
                    request.setAttribute("employee",employeeDao.buscarEmpleadoPorId(Integer.parseInt(editEmployeeId)));
                    request.getRequestDispatcher("empleado/editarEmpleado.jsp").forward(request,response);
                }
                break;
            case "s":
                String textBuscar = request.getParameter("textoBuscar");
                ArrayList<Employees> lista = employeeDao.buscarIdOrTitle(textBuscar);

                request.setAttribute("lista",lista);
                request.setAttribute("busqueda",textBuscar);
                request.getRequestDispatcher("empleado/listaEmpleados.jsp").forward(request,response);

                break;
        }
    }
}


