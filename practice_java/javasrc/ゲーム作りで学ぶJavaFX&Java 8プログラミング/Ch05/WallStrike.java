// WallStrike.java

import java.awt.Toolkit;
import java.time.Instant;
import java.util.Random;

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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class WallStrike extends Application {

    final int WIDTH = 400;
    final int HEIGHT = 300;
    final int RADIUS = 20;
    Circle ball = new Circle(0, 40, RADIUS, Color.BLUE);
    Rectangle racket = new Rectangle();
    double cx = 0; // ボールの座標
    double cy = 0;
    double dx = 2; // ボールの進む量
    double dy = 7;
    double y0Client = 0; // 上辺のクライアントアンド座標
    Timeline timeline = null;
    int point = 0;

    public void start(Stage stage) throws Exception {

        stage.setTitle("WallStrike");
        stage.initStyle(StageStyle.UTILITY);

        // ラケット
        racket.setWidth(35);
        racket.setHeight(5);
        racket.setFill(Color.BLACK);

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
        center.getChildren().addAll(ball, racket);
        center.setFocusTraversable(true);
        center.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT)
                racket.setX(racket.getX() - 3);
            if (event.getCode() == KeyCode.RIGHT)
                racket.setX(racket.getX() + 3);
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

        timeline = new Timeline(
                new KeyFrame(Duration.millis(150),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        // ボールを移動する
                        boundCheck(stage);
                    	moveBall();
                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setOnFinished(event -> gameEnd(stage));

        // 乱数を初期化する
        Random rnd = new Random( Instant.now().getNano());
        dx = rnd.nextInt(10) - 5;
        if (dx == 0)
            dx = 1;
        dy = 7;
        cx = ball.getCenterX();
        cy = ball.getCenterY();

        timeline.play();
    }

    void initGame()
    {
        racket.setX((WIDTH - racket.getWidth()) / 2);
        racket.setY(250);
        ball.setCenterX(WIDTH/ 2.0);
        ball.setCenterY(40.0);
        point = 0;
    }

    void moveBall()
    {
        cx = cx + dx;
        cy = cy + dy;
        ball.setCenterX(cx);
        ball.setCenterY(cy);
        point += 1;
    }

    void boundCheck(Stage stage)
    {
        if (cx < RADIUS + 2 || cx > WIDTH - RADIUS * 2.0 + 4) { // 左右の境界に衝突
            dx = -dx;
        }
        if (cy < RADIUS + 2) { // 上の境界に衝突
            dy = -dy * 1.05;  // 少し速くする
        }
        if (cy > racket.getY() - RADIUS - 2 &&     // ラケットに衝突
                cx > racket.getX() - 8 && cx < racket.getX() + RADIUS * 2 + 8) {
                dy = -dy;
            Toolkit.getDefaultToolkit().beep();
        }
        if (cy > HEIGHT - 2) { // 下の境界に衝突
            gameEnd(stage);
            cleanup();
        }
    }

    void gameEnd(Stage stage)
    {
        timeline.stop();
        timeline = null;
        Alert dlg = new Alert(AlertType.INFORMATION);
        dlg.setTitle("ゲームオーバー");
        dlg.setHeaderText(String.format("ゲームオーバー：得点=%d", point));
        dlg.initOwner(stage);
        dlg.show();
    }

    void cleanup()
    {
        // クリーンアップする
        if (timeline != null)
            timeline = null;
    }
}
