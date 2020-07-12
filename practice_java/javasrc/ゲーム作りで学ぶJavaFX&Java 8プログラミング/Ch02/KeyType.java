// KeyType.java

import java.awt.Toolkit;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class KeyType extends Application {

    Label aLabel = new Label("タイプ：");

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("KeyType");
        stage.initStyle(StageStyle.UTILITY);
        stage.centerOnScreen();
        stage.setResizable(false);

        // メニューバーとメニュー項目を作成する
        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("ファイル");
        menuBar.getMenus().add(fileMenu);
        MenuItem mnuExit = new MenuItem("終了");
        // イベントハンドラ
        mnuExit.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent e) {
               Platform.exit();
           }
        });
        // [File]メニューの子ノードとして[Exit]を追加する
        fileMenu.getItems().addAll(mnuExit);

        BorderPane root = new BorderPane();
        VBox center = new VBox();
        center.setSpacing(10);
        aLabel.setFont(new Font(24));
        aLabel.setFocusTraversable(false);
        // イメージを描く
        final Canvas canvas = new Canvas();
        Image img = new Image(Paths.get( "KeyBoard.png" ).toUri().toString());
        canvas.setWidth(img.getWidth());
        canvas.setHeight(img.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(img, 0, 0);

        canvas.setOnKeyTyped(event-> keyTyped(event));
        canvas.setFocusTraversable(true);
        center.setAlignment(Pos.CENTER);
        center.getChildren().addAll(canvas, aLabel);
        root.setTop(menuBar);
        root.setCenter(center);

        stage.setScene(new Scene(root, 240, 190));
        stage.show();
    }

    // キーが押された時の処理
    void keyTyped(KeyEvent event)
    {
        // 使わないキーの場合はすぐリターンする
        if (event.getCode() == KeyCode.ENTER) return;
        if (event.getCode() == KeyCode.BACK_SPACE) return;
        if (event.getCode() == KeyCode.DELETE) return;
        if (event.getCode() == KeyCode.CLEAR) return;

        // タイプされたキーを表示する
        Toolkit.getDefaultToolkit().beep();
        String aChar = event.getCharacter();
        aLabel.setText("タイプ：" + aChar);
    }
}
