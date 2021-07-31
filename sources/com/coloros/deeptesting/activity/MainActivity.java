package com.coloros.deeptesting.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import com.coloros.deeptesting.R;
import com.coloros.deeptesting.p007a.C0151h;
import com.coloros.neton.BuildConfig;

public class MainActivity extends Activity {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public int f300a = 99;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public int f301b = 10;

    /* renamed from: c */
    private Button f302c;

    /* renamed from: d */
    private TextView f303d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public ProgressDialog f304e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public Handler f305f = new C0157d(this);

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.activity_main);
        m373b();
        this.f302c = (Button) findViewById(R.id.apply);
        this.f302c.setOnClickListener(new C0158e(this));
        this.f303d = (TextView) findViewById(R.id.status);
        this.f303d.setOnClickListener(new C0159f(this));
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        switch (this.f300a) {
            case -2:
                this.f302c.setText(getString(R.string.dialog_exittest));
                this.f303d.setText(getString(R.string.main_query));
                return;
            case -1:
                this.f302c.setText(getString(R.string.main_apply));
                this.f303d.setText(getString(R.string.main_query));
                return;
            case 0:
                this.f302c.setText(getString(R.string.dialog_exittest));
                this.f303d.setText(BuildConfig.FLAVOR);
                return;
            default:
                this.f302c.setText(getString(R.string.main_apply));
                this.f303d.setText(BuildConfig.FLAVOR);
                return;
        }
    }

    /* renamed from: a */
    public final void mo187a() {
        this.f304e = new ProgressDialog(this);
        this.f304e.setProgressStyle(0);
        this.f304e.setMessage(getString(R.string.dialog_applying));
        this.f304e.show();
        this.f304e.setCancelable(false);
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m373b() {
        this.f300a = C0151h.m358b(this);
        if (this.f300a == 0) {
            return;
        }
        if (C0151h.m356a((Context) this)) {
            mo187a();
            this.f301b = 10;
            C0151h.m354a(this, 1003, this.f305f);
            return;
        }
        C0151h.m355a(this, getString(R.string.dialog_title), getString(R.string.dialog_nonetwork), (Handler) null);
    }
}
