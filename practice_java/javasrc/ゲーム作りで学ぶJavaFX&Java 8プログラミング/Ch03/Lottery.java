// Lottery.java

import java.awt.Toolkit;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

class LotteryTask extends TimerTask {

    Random rnd = null;

    LotteryTask(){
        // 乱数を初期化する
        rnd = new Random( Instant.now().getNano());
    }

    // SelNumberプロパティ
    private StringProperty selNumber =
            new SimpleStringProperty(this, "selNumber");

    public StringProperty selNumberProperty() {
        return selNumber;
    }
    public String getSelNumber() {
        return selNumber.get();
    }
    public void setSelNumber(String value) {
    	selNumber.set(value);
    }

    @Override
    public void run() {
    	String strNumber = String.format("%d",  rnd.nextInt(99));
        setSelNumber(strNumber);
    }
}

public class Lottery extends Application {

    Label numberLabel = new Label("-");
    Button startButton = new Button("Start");
    Button stopButton = new Button("Stop");    Random rnd = null;
    AudioClip startClip = null;
    MenuItem mnuStart = new MenuItem("スタート");
    MenuItem mnuStop = new MenuItem("ストップ");
    LotteryTask task = null;
    Timer timer = null;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Lottery");
        stage.initStyle(StageStyle.UTILITY);
        stage.centerOnScreen();
        stage.setResizable(false);

        // メニューバーとメニュー項目を作成する
        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("ファイル");
        menuBar.getMenus().add(fileMenu);
        mnuStart.setOnAction(event -> startRottery());
        mnuStop.setOnAction(event -> stopRottery());
        mnuStop.setDisable(true);
        MenuItem mnuExit = new MenuItem("終了");
        mnuExit.setOnAction(event -> exitProgram());

        fileMenu.getItems().addAll(mnuStart, mnuStop, mnuExit);

        startButton.setOnAction(event -> startRottery());
        stopButton.setOnAction(event -> stopRottery());
        stopButton.setDisable(true);
        numberLabel.setFont(new Font(36));

        // レイアウト
        BorderPane root = new BorderPane();
        VBox center = new VBox();
        center.setSpacing(20);
        center.setAlignment(Pos.CENTER);
        HBox hbox = new HBox();
        hbox.setSpacing(30);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(startButton, stopButton);
        center.getChildren().addAll(numberLabel, hbox);
        root.setTop(menuBar);
        root.setCenter(center);

        // サウンド
        startClip = new AudioClip (Paths.get("SymbalRoll.wav").toUri().toString());

        stage.setOnCloseRequest(event -> {
            if (task != null)
                task.cancel();
            if (timer != null)
                timer.cancel();
        });
        stage.setScene(new Scene(root, 170, 140));
        stage.show();
    }

    void startRottery()
    {
        startButton.setDisable(true);
        mnuStart.setDisable(true);

        task = new LotteryTask();
        timer = new Timer();
        // 表示する番号が変わった
        task.selNumberProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0,
                   String arg1, String arg2) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                    	numberLabel.setText( task.selNumberProperty().get() );
                    }
                });

            }
        });
        timer.schedule(task, 0, 100);
        stopButton.setDisable(false);
        mnuStop.setDisable(false);
        startClip.play(0.5);
    }

    void stopRottery()
    {
        if (task != null)
            task.cancel();
        if (timer != null)
            timer.cancel();

        if (startClip != null)
            startClip.stop();
        Toolkit.getDefaultToolkit().beep();
        startButton.setDisable(false);
        stopButton.setDisable(true);
        mnuStart.setDisable(false);
        mnuStop.setDisable(true);
    }

    void exitProgram()
    {
        if (task != null)
            task.cancel();
        if (timer != null)
            timer.cancel();
        Platform.exit();
    }
}


