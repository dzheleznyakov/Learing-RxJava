package zh.learn;

import io.reactivex.disposables.Disposable;

import java.util.Arrays;

public class DisposableArray implements Disposable {
    private final Disposable[] disposables;

    private DisposableArray(Disposable... disposables) {
        this.disposables = disposables;
    }

    @Override
    public void dispose() {
        for (Disposable d : disposables) {
            if (d != null && !d.isDisposed())
                d.dispose();
        }
    }

    @Override
    public boolean isDisposed() {
        return Arrays.stream(disposables).allMatch(d -> d.isDisposed());
    }

    public static DisposableArray of(Disposable... disposables) {
        return new DisposableArray(disposables);
    }
}
