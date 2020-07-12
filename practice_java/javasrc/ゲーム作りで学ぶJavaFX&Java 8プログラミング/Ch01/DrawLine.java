// DrawLine.java

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DrawLine extends Application {

    final int WIDTH = 280;
    final int HEIGHT = 241;
    int ClientWidth = WIDTH;
    int ClientHeight = HEIGHT;

    int cx = 0; // マークの座標
    int cy = 0;
    int dx = 2; // マークの進む量
    int dy = 0;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("DrawLine");
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.initStyle(StageStyle.UTILITY);
        stage.centerOnScreen();
        stage.setResizable(false);

        Group root = new Group();

        final Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.setOnKeyPressed(event -> onKeyPressed(event));
        canvas.setFocusTraversable(true);

        root.getChildren().add(canvas);

        stage.setScene(new Scene(root));
        stage.show();

        // クライアント領域の幅と高さ
        ClientHeight = (int) stage.getScene().getHeight();
        ClientWidth = (int) stage.getScene().getWidth();
        // スタート時の座標
        cx = ClientWidth / 2;
        cy = ClientHeight / 2;

        Timer timer = new Timer();
        class GameTask extends TimerTask {//implements Runnable {

            @Override
            public void run() {
                cx += dx;
                cy += dy;
                gc.fillRect(cx,  cy,  2,  2);
            }
        }

        GameTask task = new GameTask();
        stage.setOnCloseRequest(event -> {
            if (task != null)
                task.cancel();
            if (timer != null)
                timer.cancel();
        });

        timer.schedule(task, 1000, 100);

        Toolkit.getDefaultToolkit().beep();
    }

    // キーが押された時の処理
    void onKeyPressed(KeyEvent event)
    {
        if (event.getCode() == KeyCode.UP) { dx = 0; dy = -2;}
        if (event.getCode() == KeyCode.DOWN) { dx = 0; dy = 2;}
        if (event.getCode() == KeyCode.RIGHT) { dx = 2; dy = 0;}
        if (event.getCode() == KeyCode.LEFT) { dx =- 2; dy = 0;}
    }
}
