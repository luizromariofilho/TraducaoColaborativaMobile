package br.com.luizromariofilho.TraducaoColaborativaMobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Luiz Romario Filho on 11/23/2014.
 */
public class FormActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_layout);
    }

    public void btnSalvarOnClick(View view){
        this.finish();
    }
}