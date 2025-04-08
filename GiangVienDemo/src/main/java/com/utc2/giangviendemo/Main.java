package com.utc2.giangviendemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/utc2/giangviendemo/view/giangvien-view.fxml"));
            Scene scene = new Scene(root, 800, 600);
            primaryStage.setTitle("Quản Lý Giảng Viên");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Lỗi khi tải giao diện: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}