package com.rutils;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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

    public static void main(String[] args){
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

    EventHandler<ActionEvent> handleLogin = new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
            
        }
        
    };

    EventHandler<ActionEvent> handleRegister = new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){

        }
        
    };

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

        Region spcr2 = new Region();
        spcr1.setPrefHeight(20);

        Button login = new Button("Login");
        login.setFont(new Font(20));
        login.maxWidthProperty().bind(scene.widthProperty().divide(3));
        login.setPrefHeight(50);
        login.setOnAction(handleLogin);

        Label or = new Label("OR");
        or.setFont(new Font(30));

        Button signup = new Button("Sign Up");
        signup.setFont(new Font(20));
        signup.maxWidthProperty().bind(scene.widthProperty().divide(3));
        signup.setPrefHeight(50);
        signup.setOnAction(e -> stage.setScene(registerScene));

        root.getChildren().addAll(label, spcr1, username, password, spcr2, login, or, signup);
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

        Region spcr2 = new Region();
        spcr1.setPrefHeight(20);

        Button signup = new Button("Sign Up");
        signup.setFont(new Font(20));
        signup.maxWidthProperty().bind(scene.widthProperty().divide(3));
        signup.setPrefHeight(50);
        signup.setOnAction(handleRegister);

        Label or = new Label("OR");
        or.setFont(new Font(30));

        Button login = new Button("Login Instead");
        login.setFont(new Font(20));
        login.maxWidthProperty().bind(scene.widthProperty().divide(3));
        login.setPrefHeight(50);
        login.setOnAction(e -> stage.setScene(loginScene));

        root.getChildren().addAll(label, spcr1, username, password, passwordAgain, spcr2, signup, or, login);
        return scene;
    }
    
}
