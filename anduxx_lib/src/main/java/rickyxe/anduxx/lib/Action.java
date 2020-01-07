package rickyxe.anduxx.lib;

import androidx.annotation.NonNull;

public class Action<D> {
    public final String type;
    public D data;

    public Action(@NonNull String type) {
        this.type = type;
        this.data = null;
    }

    public Action(@NonNull String type, D data) {
        this.type = type;
        this.data = data;
    }

    public boolean isTypeOf(String t) {
        if (t == null) {
            return false;
        }

        return type.equals(t);
    }

    @NonNull
    @Override
    public String toString() {
        return "[Action " + type + ":" + data + "]";
    }

    public static <D> Action<D> create(String type, D data) {
        return new Action<>(type, data);
    }
}
