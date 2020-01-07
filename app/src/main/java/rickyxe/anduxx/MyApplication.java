package rickyxe.anduxx;

import android.app.Application;
import android.content.Context;

import rickyxe.anduxx.lib.StoreManager;

public class MyApplication extends Application {

    private StoreManager storeManager;

    @Override
    public void onCreate() {
        super.onCreate();

        storeManager = new StoreManager();
    }

    public StoreManager getStoreManager() {
        return storeManager;
    }

    public static StoreManager getAppStoreManager(Context context) {
        Application app = (Application) context.getApplicationContext();
        return ((MyApplication) app).getStoreManager();
    }

    public static StoreManager getAppStoreManager(Application application) {
        return ((MyApplication) application).getStoreManager();
    }
}
