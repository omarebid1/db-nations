package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String url = "jdbc:mysql://127.0.0.1:3306/db-nations";
        String user = "root";
        String passw = "root";

        System.out.print("Serach about : ");
        String search = scan.nextLine();

        String sqlQuery1 = "select countries.name , countries.country_id , regions.name , continents.name FROM countries INNER JOIN regions ON countries.region_id = regions.region_id INNER JOIN continents ON regions.continent_id = continents.continent_id ORDER BY countries.name;";
        String sqlQuery2 = "select countries.name , countries.country_id , regions.name , continents.name FROM countries INNER JOIN regions ON countries.region_id = regions.region_id INNER JOIN continents ON regions.continent_id = continents.continent_id where countries.name like '%"+search+"%' ORDER BY countries.name;";

        try (Connection conn = DriverManager.getConnection(url, user, passw);
             Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(sqlQuery2)
        ) {

            while (result.next()) {

                String countryName = result.getString("countries.name");
                int countryId = result.getInt("countries.country_id");
                String regionName = result.getString("regions.name");
                String continentName = result.getString("continents.name");

                System.out.println("[ " + "COUNTRY : " + countryName + " - ID : " + countryId + " - REGION : " + regionName + " - CONTINENT : " + continentName + " ]");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        scan.close();
    }
}
