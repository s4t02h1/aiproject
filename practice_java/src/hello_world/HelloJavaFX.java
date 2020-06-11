package hello_world;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {

	public static void main(String[] args) {

		@Override
		public void start(Stage stage) {

			stage.setTitle("Hello, JavaFX");
			stage.setWidth(540);
			stage.setHeight(220);

			Label lblMsg = new Label("Hello, JavaFX");
			lblMsg.setAlignment(Pos.CENTER);
			lblMsg.setFont(new Font(64));

			stage.setScene(new Scene(lblMsg));
			stage.show();
		}
	}
}
