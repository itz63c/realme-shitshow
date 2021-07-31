package com.coloros.deeptesting.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import com.coloros.deeptesting.R;

public class ApplyActivity extends Activity {

    /* renamed from: a */
    private Context f294a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public Button f295b;

    /* renamed from: c */
    private CheckBox f296c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public boolean f297d = false;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public ProgressDialog f298e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public Handler f299f = new C0154a(this);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.apply);
        this.f294a = getApplicationContext();
        this.f296c = (CheckBox) findViewById(R.id.check_box);
        this.f295b = (Button) findViewById(R.id.agree);
        this.f295b.setEnabled(this.f297d);
        this.f296c.setOnClickListener(new C0155b(this));
        this.f295b.setOnClickListener(new C0156c(this));
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
    public final void mo184a() {
        this.f298e = new ProgressDialog(this);
        this.f298e.setProgressStyle(0);
        this.f298e.setMessage(getString(R.string.dialog_applying));
        this.f298e.show();
        this.f298e.setCancelable(false);
    }
}
