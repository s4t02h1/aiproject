// Shogi.java

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Shogi extends Application {

    HBox banHBox[] = new HBox[81];
    Image image[] = new Image[18];
    int data[] = {16, 15, 14, 13, 10, 13, 14, 15, 16,
                  99, 12, 99, 99, 99, 99, 99, 11, 99,
                  17, 17, 17, 17, 17, 17, 17, 17, 17,
                  99, 99, 99, 99, 99, 99, 99, 99, 99,
                  99, 99, 99, 99, 99, 99, 99, 99, 99,
                  99, 99, 99, 99, 99, 99, 99, 99, 99,
                   7,  7,  7,  7,  7,  7,  7,  7,  7,
                  99,  1, 99, 99, 99, 99, 99,  2, 99,
                   6,  5,  4,  3,  0,  3,  4,  5,  6
    };
    int fromPos = 99;
    int toPos = 99;
    Label statusLabel = new Label("将棋");

    @Override
    public void start(Stage stage) {

        stage.setTitle("Shogi");
        stage.initStyle(StageStyle.UTILITY);

        BorderPane root = new BorderPane();

        BorderStroke stroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                 CornerRadii.EMPTY, BorderWidths.DEFAULT);
        Border border = new Border(stroke);

        GridPane center = new GridPane();
        center.setPadding(new Insets(5.0));
        for (int i=0; i<81; i++) {
            banHBox[i] = new HBox();
            banHBox[i].setBorder(border);
            int x = i % 9;
            int y = i / 9;
            GridPane.setConstraints(banHBox[i], x, y);
            center.getChildren().add(banHBox[i]);
            configureDrop(banHBox[i]);
        }

        // イメージを読み込む
        for (int i = 0; i < 18; i++) {
            if (i>7 && i<10)
            	image[i] = new Image(Paths.get( "koma99.png").toUri().toString());
            else {
                String fname = String.format("koma%d.png",  i);
                image[i] = new Image(Paths.get( fname ).toUri().toString());
            }
        }

        // 将棋盤に駒を並べる
        for (int i = 0; i < 81; i++) {
            int imageNo = data[i];
             ImageView view = null;
            if (imageNo == 99)
                view = new ImageView(image[8]);
            else
                view = new ImageView(image[imageNo]);
            view.setFocusTraversable(true);
            banHBox[i].getChildren().add(view);
            configureDrag(view);
        }

        root.setCenter(center);
        root.setBottom(statusLabel);
        Scene scene = new Scene(root, 578, 598);
        stage.setScene(scene);
        stage.show();
    }

    private void configureDrag(final ImageView view) {

        // ドラッグを開始した時のイベントハンドラを設定する
        view.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // DragBoardを生成する
                Dragboard dragboard
                        = view.startDragAndDrop(TransferMode.MOVE);
                // DragBoardに保存する内容を生成して保存する
                ClipboardContent content = new ClipboardContent();
                content.putImage(view.getImage());
                dragboard.setContent(content);
                HBox parent = (HBox) view.getParent();
                for (int i=0; i<81; i++){
                    if (parent.equals(banHBox[i])) {
                    	fromPos = i;
                        break;
                    }
                }
                view.setOpacity(0.6);
            }
        });

        // ドラッグを終了した時のイベントハンドラを設定する
        view.setOnDragDone(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Pane parent = (Pane) view.getParent();
                if (parent != null) {
                    // 表示を更新する
                    parent.getChildren().clear();
                    parent.getChildren().add(new ImageView(image[8]));
                }
            }
        });
    }

    private void configureDrop(final HBox parent) {

        // ドラッグオブジェクトがドロップするオブジェクトの上に
        // ある時のイベントハンドラを設定する
        parent.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                Dragboard dragboard = event.getDragboard();
                if (dragboard.hasImage()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
            }
        });

        // ドロップしたときの時のイベントハンドラを設定する
        parent.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard dragboard = event.getDragboard();
                if (dragboard.hasImage()) {
                    ImageView view = new ImageView(dragboard.getImage());
                    int toType = 99;     //移動先の駒の種類
                    for (int i=0; i<81; i++){
                        if (parent.equals(banHBox[i])) {
                        	toPos = i;
                        	toType = data[toPos];
                            break;
                        }
                    }
                    parent.getChildren().clear();
                    parent.getChildren().add(view);
                    int fromType = data[fromPos]; //移動元の駒の種類
                    // 駒の種類と位置をラベルに表示する
                    data[toPos] = data[fromPos];
                    data[fromPos] = 99;
                    String str = String.format("(%d,%d) -> (%d,%d) %s [取った駒:%s]",
                            fromPos % 9, fromPos / 9, toPos % 9, toPos / 9,
                            data2koma(fromType), data2koma(toType));
                    statusLabel.setText(str);
                    // ドラッグできるようにする、
                    configureDrag(view);
                }
            }
        });
    }

    // 駒の種類番号が表す駒の名前を返す
    String data2koma(int type) {
        String sType = "";
        if (type == 0 || type == 10)
            sType = "王将";
        if (type == 1 || type == 11)
            sType = "飛車";
        if (type == 2 || type == 12)
            sType = "角行";
        if (type == 3 || type == 13)
            sType = "金将";
        if (type == 4 || type == 14)
            sType = "銀将";
        if (type == 5 || type == 15)
            sType = "桂馬";
        if (type == 6 || type == 16)
            sType = "香車";
        if (type == 7 || type == 17)
            sType = "歩兵";
        if (type > 7)
        	sType = sType;
        return (sType);
    }
}
