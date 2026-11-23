package StudentManagement;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        System.out.println("~~~~~~~ STUDENT MANAGEMENT SYSTEM ~~~~~~~");
        while (true) {
            System.out.println("1: Add Student\n" +
                    "2: View All Students\n" +
                    "3: Search Student\n" +
                    "4: Update Student\n" +
                    "5: Delete Student\n" +
                    "6: Exit");
            System.out.println("-----------------------------");

            try {
                System.out.print("Enter Choice : ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:

                        System.out.print("Enter Roll Number : ");
                        int rollNumber = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Name : ");
                        String name = sc.nextLine();
                        System.out.print("Enter Marks : ");
                        int marks = sc.nextInt();
                        sc.nextLine();


                        Student student = new Student(rollNumber, name, marks);
                        try {

                            dao.addStudent(student);
                        } catch (SQLException e) {
                            System.out.println(e);
                        }
                        break;

                    case 2:

                        try {
                            ArrayList<Student> students = dao.getAllStudents();
                            if (students.isEmpty()) {
                                System.out.println("No students found!");
                            } else {
                                for (Student e : students) {
                                    System.out.println("Roll Num : " + e.getRollNum());
                                    System.out.println("Name     : " + e.getName());
                                    System.out.println("Marks    : " + e.getMarks());
                                    System.out.println("===================");
                                }
                            }
                        }catch (SQLException e){
                            System.out.println(e);
                        }
                        break;

                    case 3:
                        try {
                            System.out.print("Enter Roll Num : ");
                            int rollNum = sc.nextInt();
                            sc.nextLine();

                            Student s = dao.searchStudent(rollNum);
                            if (s == null){
                                System.out.println("Student Not Found !!");
                            }else {
                                System.out.println("Roll Num : " + s.getRollNum());
                                System.out.println("Name     : " + s.getName());
                                System.out.println("Marks    : " + s.getMarks());
                                System.out.println("===================");
                            }
                        }catch (InputMismatchException e){
                            System.out.println("Invalid Roll Number !! ");
                            sc.nextLine();
                        }catch (SQLException e){
                            System.out.println("DataBase Error :"+e);
                        }
                        break;

                    case 4:
                        try {
                            System.out.print("Enter Roll Num : ");
                            int rollNum = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter New Name : ");
                            String newName = sc.nextLine();
                            System.out.print("Enter New Marks: ");
                            int newMarks = sc.nextInt();
                            sc.nextLine();

                            dao.updateStudent(rollNum,newName,newMarks);
                        }catch (InputMismatchException e){
                            System.out.println("Invalid Roll Number !!");
                            sc.nextLine();
                        }catch (SQLException e){
                            System.out.println("DataBase Error :"+e);
                        }
                        break;

                    case 5:
                        try {
                            System.out.print("Enter Roll Number : ");
                            int rollNum = sc.nextInt();
                            sc.nextLine();

                            dao.deleteStudent(rollNum);
                        }catch (InputMismatchException e){
                            System.out.println("Invalid Roll Number !!");
                            sc.nextLine();
                        }catch (SQLException e){
                            System.out.println("Database Error :"+e);
                        }
                        break;
                    case 6:
                        System.out.println("Thank You ...");
                        System.out.println("            For Using Student Management System ...");
                        return;

                    default:
                        System.out.println("Invalid Option Number !!");

                }


            } catch (InputMismatchException e) {
                System.out.println("Invalid Input !!\n ");
                sc.nextLine();
                continue;
            }
        }

    }
}

