package com.example.lab7_20206466.daos;
import com.example.lab7_20206466.beans.Employees;
import com.example.lab7_20206466.beans.Job;

import java.sql.*;
import java.util.ArrayList;

public class DaoEmployee {
    //metodo que lista a todos los empleados
    public ArrayList<Employees> listarEmpleados(){

        ArrayList<Employees> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "select employee_id, concat(first_name,' ', last_name) AS fullNameEmployee," +
                " email, password, phone_number,hire_date,job_id,salary \n" +
                "FROM employees;";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employees empleado = new Employees();
                empleado.setEmployeeId(rs.getInt(1));
                empleado.setFullNameEmployee(rs.getString(2));
                empleado.setCorreo(rs.getString(3));
                empleado.setPassword(rs.getString(4));
                empleado.setNumeroDeTelefono(rs.getString(5));
                empleado.setHire_date(rs.getString(6));
                empleado.setJobId(rs.getString(7));
                empleado.setSalario(rs.getInt(8));

                lista.add(empleado);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
    public void registrarEmpleado(int empleadoId, String nuevoNombre,
                                  String nuevoApellido, String correo, String fecha, String jobId ){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";
        /*en esta parte solo nos centramos en la base de datos, no nos importa  crear
        * una clase employee ni validar nada, solo agregar datos al sql */
        String sql = "insert into employees (employee_id,first_name, last_name,email,hire_date,job_id)" +
                " values (?,?,?,?,?,?)";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1,empleadoId);
            pstmt.setString(2,nuevoNombre);
            pstmt.setString(3,nuevoApellido);
            pstmt.setString(4,correo);
            pstmt.setString(5,fecha);
            pstmt.setString(6,jobId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //metodo para buscar empleados por su id
    public Employees buscarEmpleadoPorId(int id){

        Employees empleadoBuscado = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "select employee_id, concat(first_name,' ', last_name) AS fullNameEmployee," +
                " email, password, phone_number,hire_date,job_id,salary \n" +
                "FROM employees where employee_id = ?";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1,id);

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    empleadoBuscado = new Employees();
                    empleadoBuscado.setEmployeeId(rs.getInt(1));
                    empleadoBuscado.setFullNameEmployee(rs.getString(2));
                    empleadoBuscado.setCorreo(rs.getString(3));
                    empleadoBuscado.setPassword(rs.getString(4));
                    empleadoBuscado.setNumeroDeTelefono(rs.getString(5));
                    empleadoBuscado.setHire_date(rs.getString(6));
                    empleadoBuscado.setJobId(rs.getString(7));
                    empleadoBuscado.setSalario(rs.getInt(8));

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return empleadoBuscado;
    }


    //editamos los datos de un empleado
    public void actualizar(Employees empleadoActualizado){
        String nombre="";
        String apellido="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "update employees set employee_id=?, first_name= ?, last_name= ?, email= ?, password= ?, phone_number= ?,hire_date= ?,job_id= ?,salary= ? where employee_id = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setInt(1,empleadoActualizado.getEmployeeId());
            String fullName= empleadoActualizado.getFullNameEmployee();
            String[] words = fullName.split(" ");
            nombre = words[0];
            apellido = words[1];
            pstmt.setString(2,nombre);
            pstmt.setString(3,apellido);
            pstmt.setString(4,empleadoActualizado.getCorreo());
            pstmt.setString(5,empleadoActualizado.getPassword());
            pstmt.setString(6,empleadoActualizado.getNumeroDeTelefono());
            pstmt.setString(7,empleadoActualizado.getHire_date());
            pstmt.setString(8,empleadoActualizado.getJobId());
            pstmt.setInt(9,empleadoActualizado.getSalario());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //metodo para borrar empleado*/
    public void borrar(int empleadoId) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "delete from employees where employee_id = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1,empleadoId);
            pstmt.executeUpdate();

        }
    }
    public ArrayList<Employees> buscarIdOrTitle(String name){

        ArrayList<Employees> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String username = "root";
        String password = "root";

        String sql = "select * from employees where employee_id = ? or lower(first_name) like lower(?);";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,name);
            pstmt.setString(2,"%" + name + "%");

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    Employees empleado2 = new Employees();
                    empleado2.setEmployeeId(rs.getInt(1));
                    String nombre=rs.getString(2);
                    String apellido=rs.getString(3);
                    String full=nombre+" "+apellido;
                    empleado2.setFullNameEmployee(full);
                    empleado2.setCorreo(rs.getString(4));
                    empleado2.setPassword(rs.getString(5));
                    empleado2.setNumeroDeTelefono(rs.getString(6));
                    empleado2.setHire_date(rs.getString(7));
                    empleado2.setJobId(rs.getString(8));
                    empleado2.setSalario(rs.getInt(9));

                    lista.add(empleado2);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }


}

