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


public class HelloApplication extends Application {
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Label label;
    VBox vbox;
    HBox hbox;
    ListView list;


    ArrayList<String> answers;


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

                    answers = ravenDecode(5);
                } catch (IOException ex) {
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
                    answers = ravenDecode(10);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                list.getItems().setAll(answers);
            }
        });

        button3 = new Button();
        button3.setText("Top 15 words");
        button3.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                try {
                    answers = ravenDecode(15);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                list.getItems().setAll(answers);
            }
        });

        button4 = new Button();
        button4.setText("Top 20 words");
        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                try {
                    answers = ravenDecode(20);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                list.getItems().setAll(answers);
            }
        });

        list = new ListView<>();
        list.setMinHeight(500);

        hbox = new HBox();
        hbox.getChildren().addAll(button1, button2,button3,button4);

        vbox = new VBox();
        vbox.getChildren().addAll(label, hbox, list);

        StackPane layout = new StackPane();
        layout.getChildren().add(vbox);
        Scene scene = new Scene(layout, 400, 600);
        stage.setScene(scene);
        stage.show();
    }

    public ArrayList<String> ravenDecode(int selection) throws IOException{
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

        //create a hash map to store the words mapped to the counts
        Map<String, Integer> words = new HashMap<>();

        //begin to scan each word of the poem
        while (theRaven.hasNext()){
            //creates a temporary word to store that is lowercase and removes any punctuation
            String word = theRaven.next().toLowerCase().replaceAll("[^a-zA-Z0-9]", "");

            //if the word does not exist in the hashmap, add it with the value count of 1
            if (words.get(word) == null){
                words.put(word, 1);

                //if word does exist it will be replaced by the same word with an updated count
            } else {
                //words.put(word, words.get(word) + 1);
                words.replace(word, words.get(word), words.get(word) + 1);
            }
        }

        //this iterates over the hashmap and uses it to populate the array of words with WordObjects

        ArrayList<WordObject> theWordList =  new ArrayList<WordObject>();

        for (Map.Entry<String, Integer> entry : words.entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();

            theWordList.add(new WordObject(k,v));
        }

        // sorts the arraylist by word count
        Collections.sort(theWordList, Comparator.comparingInt(WordObject::getCount).reversed());

        // print words and counts to console

        ArrayList<String> answers = new ArrayList<>();
        for (int i = 0; i < selection; i++){
            //System.out.println(theWordList.get(i).word +" : "+theWordList.get(i).count);
            answers.add(theWordList.get(i).word +" : "+theWordList.get(i).count);
        }
        return answers;
    }


    public static void main(String[] args) throws IOException {
        launch();
    }
}