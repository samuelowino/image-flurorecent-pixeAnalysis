/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facedetectionbiometricssoft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @User access to the application's database
 */
public class UsersDaos {

    /**
     * Obtains the url stored in the database for this specific user emailAddress
     * @param useremail
     * @return 
     */
    public static String getUserImageUrl(String useremail) {
        String imageUrl = "";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/facialrecogition", "root", "")) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT image_Url FROM users WHERE emailAddress = ?");
            preparedStatement.setString(1, useremail);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                imageUrl = resultSet.getString(1);
            }
            return imageUrl;
        } catch (SQLException e) {
            e.printStackTrace();
            return imageUrl;
        }
    }

    /**
     * registers a new user and logs his/her details into the database
     * including images
     * @param firstName
     * @param lastName
     * @param userID
     * @param imageUrl 
     */
    
    public static void registerUsers(
            String firstName,
            String lastName,
            String userID,
            String imageUrl
    ) {
        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/facialrecogition", "root", "")) {
            PreparedStatement preparedStatement = connection.prepareStatement(""
                    + "INSERT INTO users VALUES(?,?,?,?)");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, userID);
            preparedStatement.setString(4, imageUrl);
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "User registration was successfull");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
