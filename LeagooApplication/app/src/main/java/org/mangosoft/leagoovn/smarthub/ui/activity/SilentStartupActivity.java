package org.mangosoft.leagoovn.smarthub.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.mangosoft.leagoovn.smarthub.service.MainService;

public class SilentStartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent startServiceIntent = new Intent(this, MainService.class);
        this.startService(startServiceIntent);
        this.finish();
    }
}
