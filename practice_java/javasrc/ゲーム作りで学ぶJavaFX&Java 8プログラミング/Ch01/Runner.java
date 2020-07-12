// Runner.java

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Runner extends Application {

    final int WIDTH = 280;
    final int HEIGHT = 241;
    int ClientWidth = WIDTH;
    int ClientHeight = HEIGHT;

    int cx = 0; // マークの座標
    int cy = 0;
    int dx = 2; // マークの進む量
    int dy = 0;
    boolean data[][] = new boolean[WIDTH][HEIGHT];
    int point = 0;
    Alert dlg = new Alert(AlertType.INFORMATION);

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Runner");
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
        class GameTask extends TimerTask {

            private BooleanProperty gameover =
            		new SimpleBooleanProperty(this, "gameover", false);

            public ReadOnlyBooleanProperty gameoverProperty() {
                return gameover;
            }
            @SuppressWarnings("unused")
            public boolean isGameOver() {
                return gameover.get();
            }
            private void setGameover(boolean value) {
                gameover.set(value);
            }

            @Override
            public void run() {
                cx += dx;
                cy += dy;
                gc.fillRect(cx,  cy,  2,  2);
                point += 1;
                if (isTheGameOver()) {
                    Toolkit.getDefaultToolkit().beep();
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            setGameover(true);
                        }
                    });
                    this.cancel();
                }
                data[cx][cy] = true;
            }
            boolean isTheGameOver(){
                if (cx < 0) return true;
                if (cy < 0) return true;
                if (cx > ClientWidth) return  true;
                if (cy > ClientHeight-2) return  true;
                if (data[cx][cy] == true) return true;
                return false;
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

        task.gameoverProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0,
                Boolean arg1, Boolean arg2) {
            	if (task.gameoverProperty().get())
                    gameOver();
            }
        });
    }

    // キーが押された時の処理
    void onKeyPressed(KeyEvent event)
    {
        if (event.getCode() == KeyCode.UP) { dx = 0; dy = -2;}
        if (event.getCode() == KeyCode.DOWN) { dx = 0; dy = 2;}
        if (event.getCode() == KeyCode.RIGHT) { dx = 2; dy = 0;}
        if (event.getCode() == KeyCode.LEFT) { dx =- 2; dy = 0;}
    }

    // ゲームオーバーになった時の処理
    void gameOver()
    {
        dlg.setTitle("ゲームオーバー");
        dlg.setHeaderText(String.format("ゲームオーバー ： 得点=%d",  point));
        dlg.showAndWait();
        Platform.exit();
    }
}
