package StudentManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.ResultSet;

public class StudentDAO {

    private final String url = "jdbc:mysql://127.0.0.1:3306/student_management";
    private final String userName = "root";
    private final String password = "password";

    public void addStudent(Student student) throws SQLException{
        int rollNum = student.getRollNum();
        String name = student.getName();
        int marks = student.getMarks();

        String query = "INSERT INTO students (roll_num,name,marks) values (?,?,?);";

        try (Connection con = DriverManager.getConnection(url,userName,password);
             PreparedStatement preparedStatement = con.prepareStatement(query)){

            preparedStatement.setInt(1,rollNum);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(3,marks);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " Student Added Successfully");

        }catch (Exception e){
            System.out.println(e);
        }
    }



    public ArrayList<Student> getAllStudents() throws SQLException{
        ArrayList<Student> students = new ArrayList<>();

        String query = "SELECT * FROM students;";

        try (Connection con = DriverManager.getConnection(url,userName,password);
             PreparedStatement preparedStatement = con.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){
                int rollNum = resultSet.getInt("roll_num");
                String name = resultSet.getString("name");
                int marks = resultSet.getInt("marks");

                Student student = new Student(rollNum,name,marks);
                students.add(student);
            }

        }
        return students;
    }

    public Student searchStudent(int rollNum)throws SQLException{
        String query = "SELECT * from students WHERE roll_num = ?;";

        try (Connection con = DriverManager.getConnection(url,userName,password);
             PreparedStatement preparedStatement = con.prepareStatement(query)){

            preparedStatement.setInt(1,rollNum);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    int rollNumber = resultSet.getInt("roll_num");
                    String name = resultSet.getString("name");
                    int marks = resultSet.getInt("marks");
                    return new Student(rollNumber,name,marks);
                }
            }
        }
        return null;
    }

    public void deleteStudent(int rollNum) throws SQLException{
        String query = "DELETE FROM STUDENTS WHERE roll_num = ?;";

        try (Connection con = DriverManager.getConnection(url,userName,password);
             PreparedStatement preparedStatement = con.prepareStatement(query)){

            preparedStatement.setInt(1,rollNum);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("Student Deleted");
            }else {
                System.out.println("Student Not Found !!");
            }
        }
    }


    public void updateStudent(int rollNum,String name,int marks) throws SQLException{
        String query = "UPDATE students SET  name = ?, marks = ? where roll_num = ?";

        try (Connection con = DriverManager.getConnection(url,userName,password);
             PreparedStatement preparedStatement = con.prepareStatement(query)){

            preparedStatement.setInt(3,rollNum);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,marks);


            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("Student Updated");
            }else {
                System.out.println("Student Not Found !!");
            }

        }
    }
}
