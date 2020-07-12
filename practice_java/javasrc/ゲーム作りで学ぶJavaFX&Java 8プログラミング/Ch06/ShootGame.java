// ShootGame.java

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ShootGame extends Application {

    final int WIDTH = 400;
    final int HEIGHT = 300;
    final int TARGET_W = 30;
    final int TARGET_H = 20;
    Rectangle target = new Rectangle(TARGET_W, TARGET_H, Color.RED);
    Rectangle myGun = new Rectangle();
    Rectangle bullet = new Rectangle();
    double cx = 0; // ターゲットのX座標
    double dx = 5; // ターゲットの進む量
    boolean fInPlay = false;
    int point = 0;
    Timeline timeline = null;
    TimerTask shotTask = null;
    Timer shotTimer = null;

    public void start(Stage stage) throws Exception {

        stage.setTitle("ShotGame");
        stage.initStyle(StageStyle.UTILITY);

        // 銃
        myGun.setWidth(5);
        myGun.setHeight(35);
        myGun.setFill(Color.BLUE);

        initGame();

        // メニューバーとメニュー項目を作成する
        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("ファイル");
        menuBar.getMenus().add(fileMenu);
        MenuItem mnuSente = new MenuItem("スタート");
        mnuSente.setOnAction(event -> startGame(stage));
        MenuItem mnuExit = new MenuItem("終了");
        mnuExit.setOnAction(event -> {
            cleanup(); Platform.exit();});

        fileMenu.getItems().addAll(mnuSente, mnuExit);

        stage.setOnCloseRequest(event -> cleanup()); // ウィンドウが閉じるとき

        BorderPane root = new BorderPane();
        AnchorPane center = new AnchorPane();
        center.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT )));
        center.getChildren().addAll(target, myGun, bullet);
        center.setFocusTraversable(true);
        center.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT)
                myGun.setX(myGun.getX() - 3);
            if (event.getCode() == KeyCode.RIGHT)
                myGun.setX(myGun.getX() + 3);
            if (event.getCode() == KeyCode.S)  // [S}キーでスタート
                startGame(stage);
            if (event.getCode() == KeyCode.SPACE)  // [Space]キーで弾を撃つ
            shot(stage);
        });
        root.setTop(menuBar);
        root.setCenter(center);
        stage.setScene(new Scene(root, WIDTH, HEIGHT));
        stage.show();
        stage.setOnCloseRequest(event -> cleanup());
    }

    void startGame(Stage stage)
    {
        initGame();
        fInPlay = true;

        timeline = new Timeline(
                new KeyFrame(Duration.millis(500),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        // ターゲットを移動する
                        moveTarget();
                        point -= 1;
                    }
                }));
        timeline.setCycleCount(100);
        timeline.setOnFinished(event -> {
        gameEnd(stage);
            cleanup();
            Platform.exit();
        });

        // 乱数を初期化する
        Random rnd = new Random( Instant.now().getNano());
        dx = (rnd.nextInt(3) - 2) * 7;
        if (dx == 0)
            dx = 7;
        cx = target.getX();

        timeline.play();
    }

    void initGame()
    {
        myGun.setX((WIDTH - myGun.getWidth()) / 2);
        myGun.setY(210);
        target.setX((WIDTH - TARGET_W)/ 2.0);
        target.setY(20.0);
        point = 100;
    }

    void shot(Stage stage)
    {
        if (fInPlay == false)
        return;;

        Toolkit.getDefaultToolkit().beep();

        bullet.setWidth(3);
        bullet.setHeight(12);
        bullet.setFill(Color.BLUE);
        bullet.setX(myGun.getX() + 2);
        bullet.setY(myGun.getY() - 16);

        cleanupShot();

        class ShotTask extends TimerTask {
            @Override
            public void run() {
                bullet.setY(bullet.getY() - 15);
                if (hitCheck(stage)){
                    if (shotTask != null)
                        shotTask.cancel();
                    if (shotTimer != null)
                        shotTimer.cancel();
                }
            }
        }
        shotTask = new ShotTask();
        shotTimer = new Timer();

        shotTimer.schedule(shotTask, 0, 300);
    }

    void moveTarget()
    {
        cx = cx + dx;
        if (cx < 30 || cx > WIDTH - 35) // 左右境界の近くなら
            dx *= -1;                  // ターゲットの方向を変える
        target.setX(cx);
    }

    boolean hitCheck(Stage stage)
    {
    	// 目標に当たったか？
        double bx = bullet.getX();
        if (bx > cx && bx < cx + TARGET_W
                && target.getY() > bullet.getY()) { // Hit!
            Toolkit.getDefaultToolkit().beep();
            if (shotTask != null)
                shotTask.cancel();
            if (shotTimer != null)
                shotTimer.cancel();
            shotTask = null;
            shotTimer = null;
            if (timeline != null)
                timeline.stop();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    cleanup();
                    gameEnd(stage);
                    Platform.exit();
                }
            });
            return true;
        }
        if (bullet.getY() + 8 < 0) { // ウィンドウ外に出た
            bullet.setY(300);  // 見えなくする
            if (shotTask != null)
                shotTask.cancel();
            if (shotTimer != null)
                shotTimer.cancel();
            shotTask = null;
            shotTimer = null;
            return true;
        }
        return false;
    }

    void gameEnd(Stage stage)
    {
        Alert dlg = new Alert(AlertType.INFORMATION);
        dlg.setTitle("ゲームオーバー");
        dlg.setHeaderText(String.format("ゲームオーバー：得点=%d", point));
        dlg.initOwner(stage);
        dlg.showAndWait();
    }

    void cleanup()
    {
        // クリーンアップする
        if (timeline != null){
        	timeline.stop();
            timeline = null;
        }
        cleanupShot();
    }
    
    void cleanupShot()
    {
        if (shotTask != null){
        	shotTask.cancel();
            shotTask = null;
        }
        if (shotTimer != null)
        {
        	shotTimer.cancel();
            shotTimer = null;
        }
    }
}
