package zh.learn.ch02_observables_subscribers;

import io.reactivex.Observable;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HotObservable extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        ToggleButton toggleButton = new ToggleButton("TOGGLE ME");
        Label label = new Label();

        Observable<Boolean> selectedStates = valuesOf(toggleButton.selectedProperty());
        selectedStates.map(selected -> selected ? "DOWN" : "UP")
                .subscribe(label::setText);

        VBox vBox = new VBox(toggleButton, label);

        stage.setScene(new Scene(vBox));
        stage.show();
    }

    private static <T> Observable<T> valuesOf(final ObservableValue<T> fxObservable) {
        return Observable.create(observableEmitter -> {
            observableEmitter.onNext(fxObservable.getValue());
            final ChangeListener<T> listener = (observableValue, prev, current) -> observableEmitter.onNext(current);
            fxObservable.addListener(listener);
        });
    }
}
