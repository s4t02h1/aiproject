// KeyCode.java

import java.awt.Toolkit;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class KeyCode extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("KeyCode");
        stage.setWidth(240);
        stage.setHeight(140);

        Group root = new Group();

        final Canvas canvas = new Canvas(240,140);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.setOnKeyPressed(event -> dispKey(gc, event));
        canvas.setFocusTraversable(true);

        root.getChildren().add(canvas);

        stage.setScene(new Scene(root));
        stage.show();
    }

    void dispKey(GraphicsContext gc, KeyEvent event) 
    {
        Toolkit.getDefaultToolkit().beep();
        gc.setFill(Color.WHITE);       // 白で塗る設定
        gc.fillRect(10, 20, 200, 40);  // 描かれているものをクリアする
        
        // 押されたキーを表示する
        gc.strokeText(event.getCode().toString(), 20,  50);
    }
}
