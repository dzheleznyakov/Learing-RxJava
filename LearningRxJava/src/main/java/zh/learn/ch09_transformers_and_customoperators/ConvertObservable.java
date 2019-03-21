package zh.learn.ch09_transformers_and_customoperators;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observers.JavaFxObserver;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.application.Application;
import javafx.beans.binding.Binding;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class ConvertObservable extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        Label label = new Label("");

        Binding<String> binding = Observable.interval(1, TimeUnit.SECONDS)
                .map(Object::toString)
                .observeOn(JavaFxScheduler.platform())
                .to(JavaFxObserver::toBinding);

        label.textProperty().bind(binding);

        root.setMinSize(200, 100);
        root.getChildren().addAll(label);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
