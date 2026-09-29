package com.chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentCRUD {

    // INSERT
    public static void insertStudent(int id, String name, String department, int age) {
        String sql = "INSERT INTO student (id, name, department, age) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, department);
            ps.setInt(4, age);

            ps.executeUpdate();

            System.out.println("Student inserted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT
    public static void displayStudents() {
        String sql = "SELECT * FROM student";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n--- Student Details ---");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + "  " +
                    rs.getString("name") + "  " +
                    rs.getString("department") + "  " +
                    rs.getInt("age")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public static void updateStudent(int id, int age) {
        String sql = "UPDATE student SET age = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, age);
            ps.setInt(2, id);

            ps.executeUpdate();

            System.out.println("Student updated successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public static void deleteStudent(int id) {
        String sql = "DELETE FROM student WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Student deleted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // INSERT
        insertStudent(3, "Karthik", "IT", 20);

        // SELECT
        displayStudents();

        // UPDATE
        updateStudent(3, 21);

        // SELECT after UPDATE
        displayStudents();

        // DELETE
        deleteStudent(3);

        // SELECT after DELETE
        displayStudents();
    }
}

