package sample;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;

public class Controller {
    File file;
    String path, message;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane homePane;

    @FXML
    private Button findButton;

    @FXML
    private TextField messageID;

    @FXML
    private Button chooseButton;

    @FXML
    private TextField catalog;

    @FXML
    private Label label;

    @FXML
    void initialize() {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Выбор каталога:");
        chooseButton.setOnAction(event -> {
            try {
                file = dc.showDialog(Main.primaryStage);
                path = file.getAbsolutePath() + "\\";
            }catch(NullPointerException n){
                label.setText("Выберите файл!");
            }
            catalog.setText(path);
        });

        findButton.setOnAction(event -> {
            try {
                message = messageID.getText();
                label.setText(Process.returnNameOfFile(path, message));
                path = file.getAbsolutePath() + "\\";
            }catch(NullPointerException n){
                label.setText("Выберите файл!");
            }
        });
    }
}
