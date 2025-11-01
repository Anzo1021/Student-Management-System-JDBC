import java.sql.*;
import java.util.Scanner;


public class Main {
    static Connection con = dbconnection.getconnection();
    static Scanner sc = new Scanner(System.in);

    public static void getinsert() {
        try {
            System.out.println("enter name");
            String name = sc.nextLine();
            System.out.println("enter course");
            String course = sc.nextLine();
            System.out.println("enter marks");
            int marks = sc.nextInt();
            System.out.println("enter id");
            int id=sc.nextInt();
            System.out.println("grade is");
            String grade=calculateGrade(marks);

            String sql = "Insert into student(name,course,marks,grade)values(? ,? ,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, course);
            stmt.setInt(3, marks);
            stmt.setString(4, grade);
            stmt.executeUpdate();
            System.out.println("row inserted");
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public static void viewStudent() {
        try {
            String sql = "SELECT *from student";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("name") + " | "  +
                        rs.getString("course") + " | " +
                        rs.getInt("marks")+" |" +
                        rs.getInt("Id") + "|" +
                        rs.getString("grade"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStudent() {
        try {
            System.out.print("Enter student ID to update: ");
            int id = sc.nextInt();
            sc.nextLine(); // clear buffer

            System.out.print("Enter new course: ");
            String course = sc.nextLine();
            System.out.print("Enter new marks: ");
            int marks = sc.nextInt();

            String grade = calculateGrade(marks);

            String sql = "UPDATE student SET course=?, marks=?, grade=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, course);
            ps.setInt(2, marks);
            ps.setString(3, grade);
            ps.setInt(4, id);
            ps.executeUpdate();

            System.out.println(" Student updated successfully with new grade: " + grade);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void deleteStudent() {
        try {
            System.out.println("enter id to delete");
            int id = sc.nextInt();
            sc.nextLine();
            String sql = "DELETE from student where id=? ";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("deleted row");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 public static String calculateGrade(int marks){
            if (marks >= 90) return "A";
            else if (marks >= 75) return "B";
            else if (marks >= 60) return "C";
            else if (marks >= 40) return "D";
            else return "F";

        }

    public static void main(String args[]) {
        while (true) {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();
        switch (choice){
            case 1:getinsert();break;
            case 2:viewStudent();break;
            case 3:updateStudent();break;
            case 4:deleteStudent();break;
            case 5:System.exit(0);break;
            default:System.out.println("wrong case");
        }

        }
    }
}