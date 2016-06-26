package eduardo.tipcalc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.inputTotal)
    EditText inputTotal;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.inputPercentage)
    EditText inputPercentage;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.btnSubtract)
    Button btnSubtract;
    @BindView(R.id.btnClear)
    Button btnClear;
    @BindView(R.id.outputTip)
    TextView outputTip;

    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_PERCENTAGE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            TipCalcApp app = (TipCalcApp) getApplication();
            String url = app.getUrlAbout();

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnSubmit)
    public void handleClickSubmit() {
        hideKeyboard();
        String strInputTotal = inputTotal.getText().toString();
        if (!strInputTotal.isEmpty()) {
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPercentage();
            double tip = total * (tipPercentage / 100d);
            String strTip = String.format(getString(R.string.global_message_tip), tip);
            outputTip.setVisibility(View.VISIBLE);
            outputTip.setText(strTip);
        }
    }

    @OnClick(R.id.btnAdd)
    public void handleClickAdd(){
        hideKeyboard();
        handleTipChange(TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnSubtract)
    public void handleClickSubtract(){
        hideKeyboard();
        handleTipChange(-TIP_STEP_CHANGE);
    }

    private void handleTipChange(int change) {
        int tipPercentage = getTipPercentage();
        tipPercentage += change;
        if(tipPercentage > 0){
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
    }

    private int getTipPercentage() {
        int tipPercentage = DEFAULT_TIP_PERCENTAGE;
        String strInputTipPercentage = inputPercentage.getText().toString().trim();
        if(!strInputTipPercentage.isEmpty()){
            tipPercentage = Integer.parseInt(strInputTipPercentage);
        }else{
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
        return tipPercentage;
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken()
                    , InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException e) {
            Log.e(getLocalClassName(), Log.getStackTraceString(e));
        }
    }
}
