package eduardo.tipcalc;

import android.app.Application;

/**
 * Created by Eduardo on 6/12/2016.
 */
public class TipCalcApp extends Application {
    private final static String URL_ABOUT="https://about.me/eduardodaniel.03";

    public String getUrlAbout() {
        return URL_ABOUT;
    }
}
