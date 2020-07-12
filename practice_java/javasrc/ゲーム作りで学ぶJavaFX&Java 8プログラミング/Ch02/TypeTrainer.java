// TypeTrainer.java

import java.awt.Toolkit;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

public class TypeTrainer extends Application {

    Label aLabel = new Label("タイプ：");
    Label qLabel = new Label("問　題：");
    Random rnd = null;
    boolean fInPlay = false;   // ゲーム中はtrue
    int point = 0;      // 得点（正しくタイプされた回数）
    int count = 0;      // 問題数
    String qKey = "";   // 問題のキー
    String aKey = "";   // タイプされたキー
    Instant expTime = null ;   // ゲームの終了時間
    final int DURATION = 30;  // ゲームをやる時間（秒）

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("TypeTrainer");
        stage.initStyle(StageStyle.UTILITY);
        stage.centerOnScreen();
        stage.setResizable(false);

        // 乱数を初期化する
        rnd = new Random( Instant.now().getNano());

        // メニューバーとメニュー項目を作成する
        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("ファイル");
        MenuItem mnuStart = new MenuItem("スタート");
        mnuStart.setOnAction(event -> {
        	fInPlay = true;
            expTime = Instant.now().plusSeconds(DURATION);  // DURATION秒後に終了
            newQKey();
            });
        // [File]メニューの子ノードとして[Exit]を追加する
        menuBar.getMenus().add(fileMenu);
        MenuItem mnuExit = new MenuItem("終了");
        // イベントハンドラ
        mnuExit.setOnAction(event -> Platform.exit());

        fileMenu.getItems().addAll(mnuStart, mnuExit);

        BorderPane root = new BorderPane();
        VBox center = new VBox();
        center.setSpacing(10);
        qLabel.setFont(new Font(24));
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
        center.getChildren().addAll(qLabel, canvas, aLabel);
        root.setTop(menuBar);
        root.setCenter(center);

        stage.setScene(new Scene(root, 240, 195));
        stage.show();
    }

    // キーが押された時の処理
    void keyTyped(KeyEvent event)
    {
        if (fInPlay == false)   // プレイ中でなければ何もしない
            return;

        boolean fGood = true;

        // 使わないキーの場合はすぐリターンする
        if (event.getCode() == KeyCode.ENTER)  fGood = false;
        if (event.getCode() == KeyCode.BACK_SPACE)  fGood = false;
        if (event.getCode() == KeyCode.DELETE)  fGood = false;
        if (event.getCode() == KeyCode.CLEAR)  fGood = false;
        if (fGood == false) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }

        // タイプされたキーを表示する
        aKey = event.getCharacter();
        aLabel.setText("タイプ：" + aKey);
        if (aKey.equals(qKey))
            point += 1;                          // 正解
        else
            Toolkit.getDefaultToolkit().beep();  // エラー

        if (Instant.now().isAfter(expTime))
        {
           gameOver();
           return;
        }
        newQKey();
    }

    // 新しいキーを出題する
    void newQKey()
    {
        int ch = 0;
        while (true) {
            ch = rnd.nextInt(0x7a);
            if (0x2f<ch && ch<0x3a) break; // 0-9
            if (0x40<ch && ch<0x5b) break; // A-Z
            if (0x60<ch && ch<0x7b) break; // a-z
        }
        if (Instant.now().isAfter(expTime))
            gameOver();

        qKey = String.valueOf((char)ch);
        qLabel.setText("問　題：" + qKey);
        count += 1;
    }

    // ゲームオーバーになった時の処理
    void gameOver()
    {
        fInPlay = false;
        Alert dlg = new Alert(AlertType.INFORMATION);
        dlg.setTitle("ゲームオーバー");
        dlg.setHeaderText(String.format("ゲームオーバー：得点=%d / 回数=%d",
        		point, count));
        dlg.showAndWait();
    }
}
