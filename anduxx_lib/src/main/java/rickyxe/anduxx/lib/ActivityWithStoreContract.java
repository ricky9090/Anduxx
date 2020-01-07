package rickyxe.anduxx.lib;

import androidx.annotation.NonNull;

public interface ActivityWithStoreContract<T extends StateObject> {

    @NonNull
    Storeable<T> createStore();

    void destroyStore();

    @NonNull
    Storeable<T> getStore();
}
