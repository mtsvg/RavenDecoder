package com.example.ravendecoder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.util.*;
import org.junit.jupiter.api.Test;

import java.sql.*;

/***
 * Holds global variables and start method
 */
public class HelloApplication extends Application {
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button buttonTest;
    Button button20;
    Label label;
    VBox vbox;
    HBox hbox;
    ListView list;
    ArrayList<String> answers = new ArrayList<String>();




    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("The Raven HTML Decoder");

        label = new Label();
        label.setText("Make a selection to see the most used words in the poem");

        button1 = new Button();
        button1.setText("Top 5 words");
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {


                try {
                    Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wordoccurrences", "root", "password");
                    Statement statement = myConn.createStatement();
                    String sql = "select * from words order by count DESC;";
                    ResultSet rs = statement.executeQuery(sql);
                    for (int i = 0; i<5; i++){
                        rs.next();
                        String word = rs.getString(1);
                        int count = rs.getInt(2);
                        System.out.println(word);
                        System.out.println(count);
                        answers.add(word +" : " + count);
                    }



                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                list.getItems().setAll(answers);
            }
        });

        button2 = new Button();
        button2.setText("Top 10 words");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {


                try {
                    Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wordoccurrences", "root", "password");
                    Statement statement = myConn.createStatement();
                    String sql = "select * from words order by count DESC;";
                    ResultSet rs = statement.executeQuery(sql);
                    for (int i = 0; i<10; i++){
                        rs.next();
                        String word = rs.getString(1);
                        int count = rs.getInt(2);
                        System.out.println(word);
                        System.out.println(count);
                        answers.add(word +" : " + count);
                    }



                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                list.getItems().setAll(answers);
            }
        });

        buttonTest = new Button();
        buttonTest.setText("Decode");
        buttonTest.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                try {
                    ravenDecodedb();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                //list.getItems().setAll(answers);
            }
        });

        button20 = new Button();
        button20.setText("Top 20");
        button20.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {


                try {
                    Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wordoccurrences", "root", "password");
                    Statement statement = myConn.createStatement();
                    String sql = "select * from words order by count DESC;";
                    ResultSet rs = statement.executeQuery(sql);
                    for (int i = 0; i<20; i++){
                        rs.next();
                        String word = rs.getString(1);
                        int count = rs.getInt(2);
                        System.out.println(word);
                        System.out.println(count);
                        answers.add(word +" : " + count);
                    }



                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                list.getItems().setAll(answers);
            }
        });

        list = new ListView<>();
        list.setMinHeight(500);

        hbox = new HBox();
        hbox.getChildren().addAll(buttonTest, button1, button2, button20);

        vbox = new VBox();
        vbox.getChildren().addAll(label, hbox, list);

        StackPane layout = new StackPane();
        layout.getChildren().add(vbox);
        Scene scene = new Scene(layout, 400, 600);
        stage.setScene(scene);
        stage.show();
    }

    /***
     * generates array list of specified length of the most frequent word occurrences in the Raven html file
     * @return returns nothing
     * @throws IOException can throw input error if the file is not correctly read
     */



    public static void ravenDecodedb() throws IOException{
        //import the file from my local hard drive
        File file = new File("/Users/matthew/IdeaProjects/SDLCAssignment/theraven.html");
        // use jsoup to remove html tags
        String ravenNoHMTL = Jsoup.parse(file).text();

        //locates the beginning and ending phrase of the poem to create
        //a substring containing only the poem
        int leadingIndex = ravenNoHMTL.indexOf("The Raven by Edgar Allan Poe");
        int followingIndex = ravenNoHMTL.indexOf("*** END OF THE PROJECT GUTENBERG EBOOK THE RAVEN ***");
        String ravenTrimmed = ravenNoHMTL.substring(leadingIndex, followingIndex);

        //initialize scanner on the substring
        Scanner theRaven = new Scanner(ravenTrimmed);

        //begin to scan each word of the poem
        while (theRaven.hasNext()){
            //creates a temporary word to store that is lowercase and removes any punctuation
            String word = theRaven.next().toLowerCase().replaceAll("[^a-zA-Z0-9]", "");

                String url = "jdbc:mysql://localhost:3306/wordoccurrences";
                String user = "root";
                String password = "password";

                try {
                    Connection myConn = DriverManager.getConnection(url, user, password);
                    Statement statement = myConn.createStatement();
                    Statement search = myConn.createStatement();
                    String searchsql = "select * from words where word= '"+ word +"';";

                    ResultSet rs = search.executeQuery(searchsql);
                    if (!rs.next()){
                        String insertsql = "replace into words values ('"+ word + "', 1);";
                        statement.executeUpdate(insertsql);


                    } else {
                        String findCount = "select * from words where word='" + word + "';";
                        ResultSet temprs = statement.executeQuery(findCount);
                        temprs.next();
                        System.out.println(temprs.getInt("count"));
                        int tempCount = temprs.getInt("count");
                        tempCount = tempCount + 1;
                        String updatesql = "replace into words values ('"+ word + "', "+tempCount+");";
                        statement.executeUpdate(updatesql);

                    }


                    myConn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    /***
     * Main method launches the javafx classes
     * @param args ""
     * @throws IOException Needed to handle errors with the poem inputs
     */
    public static void main(String[] args) throws IOException {

        launch();
    }
}