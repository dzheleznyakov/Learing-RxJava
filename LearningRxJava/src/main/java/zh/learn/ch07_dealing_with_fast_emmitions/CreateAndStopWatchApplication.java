package zh.learn.ch07_dealing_with_fast_emmitions;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.TimeUnit;

public class CreateAndStopWatchApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        Label counterLabel = new Label("");
        ToggleButton startStopButton = new ToggleButton();

        Observable<Boolean> selectedStates = JavaFxObservable.valuesOf(startStopButton.selectedProperty())
                .publish()
                .autoConnect(2);

        selectedStates.switchMap(selected -> {
            if (selected)
                return Observable.interval(1, TimeUnit.MILLISECONDS);
            else
                return Observable.empty();
        })
                .observeOn(JavaFxScheduler.platform())
                .map(Object::toString)
                .subscribe(counterLabel::setText);

        selectedStates.subscribe(selected -> startStopButton.setText(selected ? "STOP" : "START"));

        root.getChildren().addAll(counterLabel, startStopButton);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
