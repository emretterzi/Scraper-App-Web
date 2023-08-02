package org.example;

import org.example.Dto.Product;
import org.example.SiteService.NN;
import org.example.SiteService.Teknosa;
import org.example.SiteService.Trendyol;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:postgresql://localhost:5432/WebScrapping";
        String username = "postgres";
        String password = "Trabzon_3461?";

        List<Product> teknosaList = new ArrayList<>();
        List<Product> n11list = new ArrayList<>();
        List<Product> trendyolList = new ArrayList<>();


        // Scrape data from different websites
        try {
            Teknosa teknosa = new Teknosa();
            teknosa.scrapeProductsTeknosa();
            teknosaList = teknosa.productList;

            NN productN11 = new NN();
            productN11.scrapeProductsN11();
            n11list = productN11.productList;

            Trendyol trendyol = new Trendyol();
            trendyol.scrapeProductsTrendyol();
            trendyolList = trendyol.productList;


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Product> mergedList = new ArrayList<>();
        mergedList.addAll(teknosaList);
        mergedList.addAll(n11list);
        mergedList.addAll(trendyolList);
        // Insert data into the database
        String PrSql = "INSERT INTO products (productsname,productsprice,productslink,productid) VALUES (?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            System.out.println("Connection Ok");
            PreparedStatement preparedStatement = connection.prepareStatement(PrSql);
            int i = 1;
            for (Product product : mergedList) {
                preparedStatement.setString(1, product.getProductName());
                preparedStatement.setString(2, product.getProductPrice());
                preparedStatement.setString(3, product.getProductLink());
                preparedStatement.setInt(4, i);
                preparedStatement.executeUpdate();
                i++;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

