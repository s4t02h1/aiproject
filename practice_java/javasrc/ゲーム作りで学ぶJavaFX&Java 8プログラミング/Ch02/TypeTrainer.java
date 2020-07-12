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

    Label aLabel = new Label("�^�C�v�F");
    Label qLabel = new Label("��@��F");
    Random rnd = null;
    boolean fInPlay = false;   // �Q�[������true
    int point = 0;      // ���_�i�������^�C�v���ꂽ�񐔁j
    int count = 0;      // ��萔
    String qKey = "";   // ���̃L�[
    String aKey = "";   // �^�C�v���ꂽ�L�[
    Instant expTime = null ;   // �Q�[���̏I������
    final int DURATION = 30;  // �Q�[������鎞�ԁi�b�j

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("TypeTrainer");
        stage.initStyle(StageStyle.UTILITY);
        stage.centerOnScreen();
        stage.setResizable(false);

        // ����������������
        rnd = new Random( Instant.now().getNano());

        // ���j���[�o�[�ƃ��j���[���ڂ��쐬����
        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("�t�@�C��");
        MenuItem mnuStart = new MenuItem("�X�^�[�g");
        mnuStart.setOnAction(event -> {
        	fInPlay = true;
            expTime = Instant.now().plusSeconds(DURATION);  // DURATION�b��ɏI��
            newQKey();
            });
        // [File]���j���[�̎q�m�[�h�Ƃ���[Exit]��ǉ�����
        menuBar.getMenus().add(fileMenu);
        MenuItem mnuExit = new MenuItem("�I��");
        // �C�x���g�n���h��
        mnuExit.setOnAction(event -> Platform.exit());

        fileMenu.getItems().addAll(mnuStart, mnuExit);

        BorderPane root = new BorderPane();
        VBox center = new VBox();
        center.setSpacing(10);
        qLabel.setFont(new Font(24));
        aLabel.setFont(new Font(24));
        aLabel.setFocusTraversable(false);
        // �C���[�W��`��
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

    // �L�[�������ꂽ���̏���
    void keyTyped(KeyEvent event)
    {
        if (fInPlay == false)   // �v���C���łȂ���Ή������Ȃ�
            return;

        boolean fGood = true;

        // �g��Ȃ��L�[�̏ꍇ�͂������^�[������
        if (event.getCode() == KeyCode.ENTER)  fGood = false;
        if (event.getCode() == KeyCode.BACK_SPACE)  fGood = false;
        if (event.getCode() == KeyCode.DELETE)  fGood = false;
        if (event.getCode() == KeyCode.CLEAR)  fGood = false;
        if (fGood == false) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }

        // �^�C�v���ꂽ�L�[��\������
        aKey = event.getCharacter();
        aLabel.setText("�^�C�v�F" + aKey);
        if (aKey.equals(qKey))
            point += 1;                          // ����
        else
            Toolkit.getDefaultToolkit().beep();  // �G���[

        if (Instant.now().isAfter(expTime))
        {
           gameOver();
           return;
        }
        newQKey();
    }

    // �V�����L�[���o�肷��
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
        qLabel.setText("��@��F" + qKey);
        count += 1;
    }

    // �Q�[���I�[�o�[�ɂȂ������̏���
    void gameOver()
    {
        fInPlay = false;
        Alert dlg = new Alert(AlertType.INFORMATION);
        dlg.setTitle("�Q�[���I�[�o�[");
        dlg.setHeaderText(String.format("�Q�[���I�[�o�[�F���_=%d / ��=%d",
        		point, count));
        dlg.showAndWait();
    }
}
