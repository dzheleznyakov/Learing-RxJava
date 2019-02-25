package zh.learn.ch06_concurrency_and_parallelization;

import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static zh.learn.Demonstration.getResponse;

public class Concurrency_JavaFx extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();

        ListView<String> listView = new ListView<>();
        Button refreshButton = new Button("REFRESH");

        JavaFxObservable.actionEventsOf(refreshButton)
                .observeOn(Schedulers.io())
                .flatMapSingle(a ->
                        Observable.fromArray(getResponse("https://goo.gl/S0xuOi")
                                .split("\\r?\\n")
                        ).toList()
                ).observeOn(JavaFxScheduler.platform())
                .subscribe(list -> listView.getItems().setAll(list));

        root.getChildren().addAll(listView, refreshButton);
        stage.setScene(new Scene(root));
        stage.show();

    }
}
