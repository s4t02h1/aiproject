// SpaceWar.java

import java.awt.Toolkit;
import java.nio.file.Paths;
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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
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

class Ship {

    private double x;
    private double y;
    public Rectangle gun;
    public Rectangle body;

    Ship () {
        gun = new Rectangle(3, 30);
        body = new Rectangle(21, 20);
    }
    public void setX(double xx){
        this.x = xx;
        gun.setX(x - 1);
        body.setX(x - 10);
    }
    public double getX(){
        return x;
    }
    public void setY(double yy){
        y = yy;
        gun.setY(yy);
        body.setY(yy);
    }
    public double getY(){
        return y;
    }
}

class AlienShip extends Ship{

    AlienShip () {
        super();
        this.setY(20);
        gun.setFill(Color.RED);
        body.setFill(Color.RED);
        gun.setY(getY());
        body.setY(getY());
    }
}

class UserShip extends Ship{

    UserShip () {
        super();
        this.setY(320);
        gun.setFill(Color.GRAY);
        body.setFill(Color.GRAY);
        gun.setY(getY() - 10);
        body.setY(getY());
    }
}

public class SpaceWar extends Application {

    final int WIDTH = 600;
    final int HEIGHT = 400;
    AlienShip alienShip = new AlienShip();
    Rectangle alienBullet = new Rectangle(2, 8, Color.YELLOWGREEN);
    UserShip userShip = new UserShip();
    Rectangle userBullet = new Rectangle(2, 8, Color.WHITESMOKE);
    double cx = 0; // Alien��X���W
    double dx = 5; // Alien�̐i�ޗ�
    boolean fInPlay = false;
    int point = 0;
    Timeline timeline = null;
    TimerTask userShotTask = null;
    Timer userShotTimer = null;
    TimerTask alienShotTask = null;
    Timer alienShotTimer = null;

    public void start(Stage stage) throws Exception {

        stage.setTitle("SpaceWar");
        stage.initStyle(StageStyle.UTILITY);

        initGame();

        // ���j���[�o�[�ƃ��j���[���ڂ��쐬����
        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fileMenu = new Menu("�t�@�C��");
        menuBar.getMenus().add(fileMenu);
        MenuItem mnuSente = new MenuItem("�X�^�[�g");
        mnuSente.setOnAction(event -> startGame(stage));
        MenuItem mnuExit = new MenuItem("�I��");
        mnuExit.setOnAction(event -> {
            cleanup(); Platform.exit();});

        fileMenu.getItems().addAll(mnuSente, mnuExit);

        stage.setOnCloseRequest(event -> cleanup()); // �E�B���h�E������Ƃ�

        BorderPane root = new BorderPane();
        AnchorPane center = new AnchorPane();
        center.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, null, BorderWidths.DEFAULT )));
        center.getChildren().addAll(alienShip.gun, alienShip.body, alienBullet);
        center.getChildren().addAll(userShip.gun, userShip.body, userBullet);

        center.setFocusTraversable(true);
        center.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT)
                userShip.setX(userShip.getX() - 3);
            if (event.getCode() == KeyCode.RIGHT)
                userShip.setX(userShip.getX() + 3);
            if (event.getCode() == KeyCode.S)  // [S}�L�[�ŃX�^�[�g
                startGame(stage);
            if (event.getCode() == KeyCode.SPACE)  // [Space]�L�[�Œe������
                userShot(stage);
        });

        // �F���̃o�b�N�O���E���h�C���[�W��\������
        Image img = new Image( Paths.get("space.png").toUri().toString());
        Background bg = new Background(new BackgroundImage(img, null, null, null, null));
        center.setBackground(bg);

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
                        // Alien���ړ�����
                        moveTarget();
                        point -= 1;
                    }
                }));
        // 200�T�C�N���ŃQ�[���I�[�o�[
        timeline.setCycleCount(200);
        timeline.setOnFinished(event -> {
            gameEnd(stage);
            cleanup();
            Platform.exit();
        });

        // ����������������
        Random rnd = new Random( Instant.now().getNano());
        dx = (rnd.nextInt(3) - 2) * 7;
        if (dx == 0)
            dx = 7;
        cx = alienShip.getX();

        timeline.play();

        alienShot(stage);
    }

    void initGame()
    {
        userShip.setX((WIDTH - userShip.body.getWidth()) / 2.0);
        alienShip.setX((WIDTH - alienShip.body.getWidth()) / 2.0);
        point = 100;
    }

    void userShot(Stage stage)
    {
        if (fInPlay == false)
        return;;

        Toolkit.getDefaultToolkit().beep();

        userBullet.setWidth(3);
        userBullet.setHeight(12);
        userBullet.setX(userShip.getX() + 1);
        userBullet.setY(userShip.getY() - 16);

        cleanupUserShot();

        class ShotTask extends TimerTask {
            @Override
            public void run() {
                userBullet.setY(userBullet.getY() - 15);
                if (hitCheck(stage)){
                    if (userShotTask != null)
                        userShotTask.cancel();
                    if (userShotTimer != null)
                        userShotTimer.cancel();
                }
            }
        }
        userShotTask = new ShotTask();
        userShotTimer = new Timer();

        userShotTimer.schedule(userShotTask, 0, 300);
    }

    void alienShot(Stage stage)
    {
        if (fInPlay == false)
        return;;

        Toolkit.getDefaultToolkit().beep();

        alienBullet.setWidth(3);
        alienBullet.setHeight(12);
        alienBullet.setX(alienShip.getX());
        alienBullet.setY(alienShip.getY() + 16);

        cleanupAlienShot();

        class ShotTask extends TimerTask {
            @Override
            public void run() {
            	alienBullet.setY(alienBullet.getY() + 16);
                if (hitCheck(stage)){
                    if (alienShotTask != null)
                    	alienShotTask.cancel();
                    if (alienShotTimer != null)
                    	alienShotTimer.cancel();
                }
            }
        }
        alienShotTask = new ShotTask();
        alienShotTimer = new Timer();

        alienShotTimer.schedule(alienShotTask, 0, 300);
    }

    void moveTarget()
    {
        cx = alienShip.getX()+ dx;
        if (cx < 30 || cx > WIDTH - 35) // ���E���E�̋߂��Ȃ�
            dx *= -1;                  // Alien�̕�����ς���
        alienShip.setX(cx);
    }

    boolean hitCheck(Stage stage)
    {
        // ���[�U�[�e���ڕW�ɓ����������H
        double bx = userBullet.getX();
        if (bx > cx && bx < cx + alienShip.body.getWidth() - 2
                && alienShip.getY() > userBullet.getY()) { // Hit!
            Toolkit.getDefaultToolkit().beep();
            if (userShotTask != null)
                userShotTask.cancel();
            if (userShotTimer != null)
                userShotTimer.cancel();
            userShotTask = null;
            userShotTimer = null;
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
        // ���[�U�[�e���E�B���h�E�O�ɏo�����H
        if (userBullet.getY() + 8 < 0) {
            userBullet.setY(380);  // �����Ȃ�����
            if (userShotTask != null)
                userShotTask.cancel();
            if (userShotTimer != null)
                userShotTimer.cancel();
            userShotTask = null;
            userShotTimer = null;
            return true;
        }

        // �G�C���A���e���ڕW�ɓ����������H
        bx = alienBullet.getX();
        double ux = userShip.getX();
        if (bx > ux && bx < ux + userShip.body.getWidth() - 2
                && userShip.getY() < alienBullet.getY()) { // Hit!
            Toolkit.getDefaultToolkit().beep();
            if (alienShotTask != null)
                alienShotTask.cancel();
            if (alienShotTimer != null)
                alienShotTimer.cancel();
            alienShotTask = null;
            alienShotTimer = null;
            if (timeline != null)
                timeline.stop();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    cleanup();
                    point = 0;
                    gameEnd(stage);
                    Platform.exit();
                }
            });
            return true;
        }
        // �G�C���A���e���E�B���h�E�O�ɏo�����H
        if (alienBullet.getY() > HEIGHT) {
            alienBullet.setY(alienShip.getY() + 16);  // �ēx����
            alienBullet.setX(alienShip.getX());
            return false;
        }
        return false;
    }

    void gameEnd(Stage stage)
    {
        Alert dlg = new Alert(AlertType.INFORMATION);
        dlg.setTitle("�Q�[���I�[�o�[");
        dlg.setHeaderText(String.format("�Q�[���I�[�o�[�F���_=%d", point));
        dlg.initOwner(stage);
        dlg.showAndWait();
    }

    void cleanup()
    {
        // �N���[���A�b�v����
        if (timeline != null){
            timeline.stop();
            timeline = null;
        }
        cleanupUserShot();
        cleanupAlienShot();
    }

    void cleanupUserShot()
    {
        if (userShotTask != null){
            userShotTask.cancel();
            userShotTask = null;
        }
        if (userShotTimer != null)
        {
            userShotTimer.cancel();
            userShotTimer = null;
        }
    }

    void cleanupAlienShot()
    {
        if (userShotTask != null){
            userShotTask.cancel();
            userShotTask = null;
        }
        if (userShotTimer != null)
        {
            userShotTimer.cancel();
            userShotTimer = null;
        }
    }
}
