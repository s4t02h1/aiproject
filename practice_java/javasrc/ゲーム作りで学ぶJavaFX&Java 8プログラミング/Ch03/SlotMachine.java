// SlotMachine.java

import java.awt.Toolkit;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

class SlotTask extends TimerTask {

    // 乱数を初期化する
    Random rnd = new Random( Instant.now().getNano());

    SlotTask() {
        this.setReelPosition( rnd.nextInt(99) % 7 );
    }

    // ReelPositionプロパティ
    private IntegerProperty reelPos =
            new SimpleIntegerProperty(this, "reelPos");

    public IntegerProperty reelPositionProperty() {
        return reelPos;
    }
    public Integer getReelPosition() {
        return reelPos.get();
    }
    public void setReelPosition(Integer value) {
    	reelPos.set(value);
    }

    @Override
    public void run() {
        int reelPos = this.getReelPosition() + 1;
        if (reelPos>7)
            reelPos = 0;
        this.setReelPosition(reelPos);
        String strNumber = String.format("%d",  reelPos);
    }
}

public class SlotMachine extends Application {

    Canvas canvas [] = new Canvas[3];
    Button startButton = new Button("Start");
    Button stopButton[] = new Button[3];
    Label statusLabel = new Label("Slot Machine");
    AudioClip startClip = null;
    SlotTask task[] = new SlotTask[3];
    Timer timer[] = new Timer[3];
    int reelPos[] = {1, 2, 3};
    int reeldata [][] = {
            {1, 6, 5, 2, 3, 4, 7, 1},
            {2, 1, 5, 4, 2, 4, 7, 1},
            {3, 7, 5, 2, 1, 4, 6, 1}
    };
    Image image [] = new Image[8];

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("SlotMachine");
        stage.initStyle(StageStyle.UTILITY);
        stage.centerOnScreen();
        stage.setResizable(false);

        // メニューバーとメニュー項目を作成する
        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("ファイル");
        menuBar.getMenus().add(fileMenu);
        MenuItem mnuExit = new MenuItem("終了");
        mnuExit.setOnAction(event -> exitProgram());

        fileMenu.getItems().addAll(mnuExit);

        startButton.setOnAction(event -> startTry());
        for (int i=0; i<3; i++) {
            canvas[i] = new Canvas();
            canvas[i].setHeight(50.0);
            canvas[i].setWidth(50.0);
            stopButton[i] = new Button("Stop");
            stopButton[i].setOnAction(event -> stopTry(event));
            stopButton[i].setDisable(true);
        }
        for (int i=0; i<8; i++){
            String fname = String.format("image%d.png",  i);
            image[i] = new Image(Paths.get( fname ).toUri().toString());
        }

        // レイアウト
        BorderPane root = new BorderPane();
        VBox center = new VBox();
        center.setSpacing(10);
        center.setAlignment(Pos.CENTER);
        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(canvas);
        hbox1.setSpacing(10);
        hbox1.setAlignment(Pos.CENTER);
        HBox hbox2 = new HBox();
        hbox2.setSpacing(30);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(stopButton);
        center.getChildren().addAll(startButton, hbox1, hbox2);
        root.setTop(menuBar);
        root.setCenter(center);
        root.setBottom(statusLabel);

        // サウンド
        startClip = new AudioClip (Paths.get("SymbalRoll.wav").toUri().toString());

        stage.setOnCloseRequest(event -> cleanup());
        stage.setScene(new Scene(root, 240, 180));
        stage.show();
    }

    void startTry()
    {
        startButton.setDisable(true);

        for (int i=0; i<3; i++)
        {
            task[i] = new SlotTask();
            timer[i] = new Timer();
            stopButton[i].setDisable(false);
            int ii = i;

            // 表示する番号が変わった
            task[i].reelPositionProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> arg0,
                       Number arg1, Number arg2) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                	int pos = (int) arg1;
                                    GraphicsContext gc = canvas[ii].getGraphicsContext2D();
                                    gc.drawImage(image[pos], 0, 0);
                                    reelPos[ii] = pos;
                                }
                            });
                        }
                    });

                }
            });
            timer[i].schedule(task[i], 0, 200+i);
        }
        // 乱数を初期化する
        Random rnd = new Random( Instant.now().getNano());
        for (int i=0; i<3;i++)
            task[i].setReelPosition( rnd.nextInt(99) % 8 );

        startButton.setDisable(true);
        startClip.play(0.5);
    }

    void stopTry(ActionEvent event)
    {
    	int reelNo = 99;
        for (int i=0; i<3; i++) {
            if (event.getTarget().equals(stopButton[i]))
            {
                reelNo = i;
                break;
            }
        }
        stopButton[reelNo].setDisable(true);
        if (task[reelNo] != null)
            task[reelNo].cancel();
        boolean fDone = true;
        for (int i=0; i<3; i++)
        {
            // 1個でも有効なボタンがあったら終了しない
            if (stopButton[i].isDisabled() == false)
            {
                fDone = false;
                break;
             }
        }
        if (!fDone)   // 終了していなければリターンする
            return;

        if (startClip != null)
            startClip.stop();

        Toolkit.getDefaultToolkit().beep();
        cleanup();

        // 得点の計算
        int point = 0;
        if (reelPos[0] == 0 && reelPos[1] == 0 && reelPos[2] == 0)
        	point = 100;
        if (reelPos[0] == 1 && reelPos[1] == 1 && reelPos[2] == 1)
        	point = 80;
        if (reelPos[0] == 2 && reelPos[1] == 2 && reelPos[2] == 2)
        	point = 60;
        int cherrycount = 0;
        for (int i=0; i<3; i++)
            if (reelPos[i] == 2)
               cherrycount += 1;
        if (cherrycount == 2)
        	point = 20;
        if (cherrycount == 1)
        	point = 5;
        if (reelPos[0] == 3 && reelPos[1] == 3 && reelPos[2] == 3)
        	point = 80;
        if (reelPos[0] == 4 && reelPos[1] == 4 && reelPos[2] == 4)
        	point = 40;

        statusLabel.setText( String.format("ポイント=%d", point));
        startButton.setDisable(false);
    }

    void exitProgram()
    {
    	cleanup();
        Platform.exit();
    }

    void cleanup()
    {
    	for (int i=0; i<3; i++) {
            if (task[i] != null)
                task[i].cancel();
            if (timer[i] != null)
                timer[i].cancel();
    	}
    }
}

