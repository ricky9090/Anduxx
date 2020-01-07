package rickyxe.anduxx.lib;

public interface StateChangeListener<S extends StateObject> {

    void onStateChange(S state);
}
