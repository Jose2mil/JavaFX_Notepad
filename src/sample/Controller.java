package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
    public Menu menuFile;
    public MenuItem itemSave;
    public MenuItem itemExit;
    public TextArea textArea;
    public Label bottomLabel;
    public MenuItem itemOpen;
    public MenuBar menuBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        itemOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                File selectedFile = fileChooser.showOpenDialog(new Stage());
                String path = selectedFile.getPath();
                BufferedReader textFile;

                try
                {
                     textFile = new BufferedReader(
                            new FileReader(path));
                     String contentFile = "";
                     String line = "";
                     int numberOfLines = 0;

                     do
                     {
                         line = textFile.readLine();
                         if(line != null)
                         {
                             if (contentFile != "")
                                contentFile += "\n" + line;
                             else
                                 contentFile += line;

                             numberOfLines++;
                         }
                     }
                     while(line != null);

                     textArea.setText(contentFile);
                     bottomLabel.setText("Nº Lines: " + numberOfLines);
                     textFile.close();
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });

        itemSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                File selectedFile = fileChooser.showOpenDialog(new Stage());
                String path = selectedFile.getPath();
                FileWriter textFile = null;

                try
                {
                    textFile = new FileWriter(path);
                    textFile.write(textArea.getText());
                    textFile.close();
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    if (textFile != null)
                    {
                        try
                        {
                            textFile.close();
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        bottomLabel.setText("Saved!");
                    }

                    else
                        bottomLabel.setText("Not Saved!");
                }
            }
        });

        itemExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                System.exit( 0);
            }
        });
    }
}
