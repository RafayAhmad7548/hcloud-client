package com.rutils;

import java.io.File;
import java.net.MalformedURLException;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HClient extends Application{

    static Stage stage;

    static Scene loginScene;
    static Scene registerScene;
    static Scene mainScene;

    public static void main(String[] args){
        HttpsUtil.init();
        launch(args);
    }

    @Override
    public void start(Stage arg1){

        stage = new Stage();
        stage.setMinWidth(800);
        stage.setMinHeight(750);

        loginScene = createLoginScene();
        registerScene = createRegisterScene();

        try{
            File style = new File("src/main/java/com/rutils/styles/darktheme.css");
            loginScene.getStylesheets().add(style.toURI().toURL().toExternalForm());
            registerScene.getStylesheets().add(style.toURI().toURL().toExternalForm());
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }

        stage.setScene(loginScene);
        stage.show();
    }

    private void handleLogin(TextField username, PasswordField password, Label info){
        int verified = HttpsUtil.verifyCredentials(username.getText(), password.getText());
        resetStyles(username, password, info);
        switch(verified){
            case 0: // user not registered
                username.setStyle("-fx-border-color: red;");
                info.setText("user not registered");
                info.setStyle("-fx-text-fill: red;");
            break;
            case 1: // succesful verification
                username.setStyle("");
                password.setStyle("");
                info.setStyle("");
                info.setText("success");
                info.setStyle("-fx-text-fill: green;");
            break;
            case 2: // incorrect passwd
                username.setStyle("");
                password.setStyle("-fx-border-color: red;");
                info.setStyle("");
                info.setText("incorrect password");
                info.setStyle("-fx-text-fill: red;");
        }
        
    }

    private void handleRegister(TextField username, PasswordField password, PasswordField passwordAgain, Label info){
        int status = HttpsUtil.registerUser(username.getText(), password.getText(), passwordAgain.getText());       
        resetStyles(username, password, passwordAgain, info);
        System.out.println(status);
        switch(status){
            case 0: // password not match
                password.setStyle("-fx-border-color: red;");
                passwordAgain.setStyle("-fx-border-color: red;");
                info.setText("passwords do not match");
                info.setStyle("-fx-text-fill: red;");
            break;
            case 1: // registered succesfully
                info.setText("registered successfully");
                info.setStyle("-fx-text-fill: green;");
                password.setStyle("-fx-border-color: #222222;");
                passwordAgain.setStyle("-fx-border-color: #222222;");
            break;
            case 2:
                // user already exists
                info.setText("user already registered");
                info.setStyle("-fx-text-fill: red;");
                username.setStyle("-fx-border-color: red;");
        }
    }

    private void resetStyles(Node... nodes){
        for(Node node : nodes){
            node.setStyle(null);
        }
    }

    private Scene createLoginScene(){
        VBox root = new VBox();
        Scene scene = new Scene(root);

        root.requestFocus();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(25);
        root.minWidthProperty().bind(stage.widthProperty());
        root.minHeightProperty().bind(stage.heightProperty());

        Label label = new Label("Login");
        label.setFont(new Font(40));

        Region spcr1 = new Region();
        spcr1.setPrefHeight(20);

        TextField username = new TextField();
        username.setPromptText("username");
        username.setFont(new Font(20));
        username.maxWidthProperty().bind(scene.widthProperty().divide(3));
        username.setPrefHeight(50);

        PasswordField password = new PasswordField();
        password.setPromptText("password");
        password.setFont(new Font(20));
        password.maxWidthProperty().bind(scene.widthProperty().divide(3));
        password.setPrefHeight(50);

        Label info = new Label();
        info.setPrefHeight(20);

        Button login = new Button("Login");
        login.setFont(new Font(20));
        login.maxWidthProperty().bind(scene.widthProperty().divide(3));
        login.setPrefHeight(50);
        login.setOnAction(e -> handleLogin(username, password, info));

        Label or = new Label("OR");
        or.setFont(new Font(30));

        Button signup = new Button("Sign Up");
        signup.setFont(new Font(20));
        signup.maxWidthProperty().bind(scene.widthProperty().divide(3));
        signup.setPrefHeight(50);
        signup.setOnAction(e -> stage.setScene(registerScene));

        root.getChildren().addAll(label, spcr1, username, password, info, login, or, signup);
        return scene;
    }

    private Scene createRegisterScene(){
        VBox root = new VBox();
        Scene scene = new Scene(root);

        root.requestFocus();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(25);
        root.minWidthProperty().bind(stage.widthProperty());
        root.minHeightProperty().bind(stage.heightProperty());

        Label label = new Label("Sign Up");
        label.setFont(new Font(40));

        Region spcr1 = new Region();
        spcr1.setPrefHeight(20);

        TextField username = new TextField();
        username.setPromptText("username");
        username.setFont(new Font(20));
        username.maxWidthProperty().bind(scene.widthProperty().divide(3));
        username.setPrefHeight(50);

        PasswordField password = new PasswordField();
        password.setPromptText("password");
        password.setFont(new Font(20));
        password.maxWidthProperty().bind(scene.widthProperty().divide(3));
        password.setPrefHeight(50);

        PasswordField passwordAgain = new PasswordField();
        passwordAgain.setPromptText("confirm password");
        passwordAgain.setFont(new Font(20));
        passwordAgain.maxWidthProperty().bind(scene.widthProperty().divide(3));
        passwordAgain.setPrefHeight(50);

        Label info = new Label();
        info.setPrefHeight(20);
        info.setStyle("-fx-text-fill: red;");

        Button signup = new Button("Sign Up");
        signup.setFont(new Font(20));
        signup.maxWidthProperty().bind(scene.widthProperty().divide(3));
        signup.setPrefHeight(50);
        signup.setOnAction(e -> handleRegister(username, password, passwordAgain, info));

        Label or = new Label("OR");
        or.setFont(new Font(30));

        Button login = new Button("Login Instead");
        login.setFont(new Font(20));
        login.maxWidthProperty().bind(scene.widthProperty().divide(3));
        login.setPrefHeight(50);
        login.setOnAction(e -> stage.setScene(loginScene));

        root.getChildren().addAll(label, spcr1, username, password, passwordAgain, info, signup, or, login);
        return scene;
    }
    
}
