// MoveBall.java

import java.awt.Toolkit;
import java.time.Instant;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MoveBall extends Application {

    int cxBlack = 0; // �}�[�N�̍��W
    int cyBlack = 0;
    int cxGray = 0; // �}�[�N�̍��W
    int cyGray = 0;
    int dxBlack = 2; // �}�[�N�̐i�ޗ�
    int dyBlack = 0;
    int dxGray = 2; // �}�[�N�̐i�ޗ�
    int dyGray = 0;
    TimerTask task = null;
    Timer grayTimer = null;
    Timeline blackTimer = null;

    public void start(Stage stage) throws Exception {

        stage.setTitle("MoveBall");
        stage.initStyle(StageStyle.UTILITY);

        Circle blackBall = new Circle(140, 120, 20, Color.BLACK);
        Circle grayBall = new Circle(240, 120, 20, Color.GRAY);

        // ����������������
        Random rnd = new Random( Instant.now().getNano());

        cxBlack = (int) blackBall.getLayoutX();
        cyBlack = (int) blackBall.getLayoutY();

        blackTimer = new Timeline(
            new KeyFrame(Duration.millis(500),
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // �{�[�����ړ�����
                    dxBlack = rnd.nextInt(20) - 10;
                    dyBlack = rnd.nextInt(20) - 10;
                    cxBlack = (int) (cxBlack + dxBlack);
                    cyBlack = (int) (cyBlack + dyBlack);
                    blackBall.setLayoutX(cxBlack);
                    blackBall.setLayoutY(cyBlack);
                }
            }));
        blackTimer.setCycleCount(Timeline.INDEFINITE);


        cxGray = (int) (stage.getWidth() / 2);
        cyGray = (int) (stage.getHeight() / 2);

        class GameTask extends TimerTask {
            @Override
            public void run() {
                dxGray = rnd.nextInt(20) - 10;
                dyGray = rnd.nextInt(20) - 10;
                cxGray += dxGray;
                cyGray += dyGray;
                grayBall.setLayoutX(cxGray);
                grayBall.setLayoutY(cyGray);
            }
        }
        task = new GameTask();
        grayTimer = new Timer();


        // ���j���[�o�[�ƃ��j���[���ڂ��쐬����
        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("�t�@�C��");
        menuBar.getMenus().add(fileMenu);
        MenuItem mnuSente = new MenuItem("�u���b�N");
        mnuSente.setOnAction(event -> blackTimer.play());
        MenuItem mnuGote = new MenuItem("�O���[");
        mnuGote.setOnAction(event -> grayTimer.schedule(task, 1000, 500));
        MenuItem mnuExit = new MenuItem("�I��");
        mnuExit.setOnAction(event -> {
        	cleanup(); Platform.exit();});

        fileMenu.getItems().addAll(mnuSente, mnuGote, mnuExit);

        stage.setOnCloseRequest(event -> cleanup()); // �E�B���h�E������Ƃ�

        BorderPane root = new BorderPane();
        AnchorPane center = new AnchorPane();
        center.getChildren().addAll(blackBall, grayBall);
        center.setFocusTraversable(true);
        center.setOnKeyPressed(event -> {
        	Toolkit.getDefaultToolkit().beep();
            if (event.getCode() == KeyCode.UP)
                blackBall.setRadius(blackBall.getRadius() + 3);
            if (event.getCode() == KeyCode.DOWN)
                blackBall.setRadius(blackBall.getRadius() - 3);
            if (event.getCode() == KeyCode.ADD)
                grayBall.setRadius(grayBall.getRadius() + 3);
            if (event.getCode() == KeyCode.SUBTRACT)
                grayBall.setRadius(grayBall.getRadius() - 3);
        });
        root.setTop(menuBar);
        root.setCenter(center);
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }

    void cleanup()
    {

        if (task != null)              // �N���[���A�b�v����
        {
            task.cancel();
            task = null;
        }
        if (grayTimer != null)
        {
            grayTimer.cancel();
            grayTimer = null;
        }
        if (blackTimer != null)
        {
            blackTimer.stop();
            blackTimer = null;
        }
    }
}

