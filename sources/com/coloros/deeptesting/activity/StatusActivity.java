package com.coloros.deeptesting.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import com.coloros.deeptesting.R;
import com.coloros.deeptesting.p007a.C0151h;

public class StatusActivity extends Activity {

    /* renamed from: a */
    private Button f306a;

    /* renamed from: b */
    private TextView f307b;

    /* renamed from: c */
    private TextView f308c;

    /* renamed from: d */
    private TextView f309d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public ProgressDialog f310e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public Handler f311f = new C0160g(this);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.status);
        if (!C0151h.m361c(this) || !C0151h.m351a().startsWith("RMX")) {
            finish();
        }
        this.f306a = (Button) findViewById(R.id.ok);
        this.f306a.setOnClickListener(new C0161h(this));
        this.f307b = (TextView) findViewById(R.id.status_message);
        this.f308c = (TextView) findViewById(R.id.status_describe);
        this.f309d = (TextView) findViewById(R.id.start_unlock);
        this.f309d.setOnClickListener(new C0162i(this));
        switch (getIntent().getIntExtra("resultCode", 100)) {
            case -1020:
                this.f307b.setText(getString(R.string.application_fail));
                this.f308c.setText(getString(R.string.detail_apk_change));
                return;
            case -1015:
                this.f307b.setText(getString(R.string.application_fail));
                this.f308c.setText(getString(R.string.detail_ota_version));
                return;
            case -1013:
                this.f307b.setText(getString(R.string.verification_fail));
                return;
            case -1008:
                this.f307b.setText(getString(R.string.application_nosubmitted));
                return;
            case -1006:
                this.f307b.setText(getString(R.string.verification_submitted));
                this.f308c.setText(getString(R.string.detail_submitted));
                return;
            case -1004:
                this.f307b.setText(getString(R.string.application_fail));
                this.f308c.setText(getString(R.string.detail_excess));
                return;
            case -1003:
                this.f307b.setText(getString(R.string.application_submitted));
                this.f308c.setText(getString(R.string.detail_submitted));
                return;
            case -1002:
                this.f307b.setText(getString(R.string.application_fail));
                this.f308c.setText(getString(R.string.detail_timelimit));
                return;
            case 0:
                this.f307b.setText(getString(R.string.application_submitted));
                this.f308c.setText(getString(R.string.detail_submitted));
                return;
            case 1:
                this.f307b.setText(getString(R.string.verification_pass));
                this.f309d.setText(getString(R.string.status_start));
                return;
            case 10:
                this.f307b.setText(getString(R.string.application_fail));
                this.f308c.setText(getString(R.string.detail_govcustom));
                return;
            case 11:
                this.f307b.setText(getString(R.string.application_fail));
                this.f308c.setText(getString(R.string.detail_mobilecustom));
                return;
            case 100:
                this.f307b.setText(getString(R.string.network_timeout));
                this.f308c.setText(getString(R.string.detail_networktimeout));
                return;
            default:
                this.f307b.setText(getString(R.string.application_fail));
                this.f308c.setText(getString(R.string.detail_wrongdata));
                return;
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /* renamed from: a */
    public final void mo190a() {
        this.f310e = new ProgressDialog(this);
        this.f310e.setProgressStyle(0);
        this.f310e.setMessage(getString(R.string.dialog_applying));
        this.f310e.show();
        this.f310e.setCancelable(false);
    }
}
