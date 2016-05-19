package org.mangosoft.leagoovn.smarthub.ui.activity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.mangosoft.leagoovn.smarthub.R;
import org.mangosoft.leagoovn.smarthub.components.DeviceInfo;
import org.mangosoft.leagoovn.smarthub.model.SMSWarrantyEvent;
import org.mangosoft.leagoovn.smarthub.service.MainService;
import org.mangosoft.leagoovn.smarthub.utils.AppDataHelper;
import org.mangosoft.leagoovn.smarthub.utils.Constants;
import org.mangosoft.leagoovn.smarthub.utils.Log;
import org.mangosoft.leagoovn.smarthub.utils.SettingKey;
import org.mangosoft.leagoovn.smarthub.utils.StringUtils;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.castorflex.android.circularprogressbar.CircularProgressBar;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class WarrantyConfirmationActivity extends AppCompatActivity {


    public static volatile boolean active = false;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.button_warranty_denial)
    Button buttonDeny;
    @Bind(R.id.warranty_confirm_text_guide)
    TextView textGuide;
    @Bind(R.id.txt_warranty_confirmation_welcome)
    TextView txtWelcome;
    @Bind(R.id.spinner_warranty_confirmation)
    CircularProgressBar spinner;
    private String imei1, model, activationSMS, querySMS;
    private Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String lauchDateStr = AppDataHelper.readValue(this, SettingKey.WARRANTY_ESTIMATE_BUY_DATE);
        Date launchDate = null;
        try {
            launchDate = StringUtils.deserializeDate(lauchDateStr);
            Calendar c = Calendar.getInstance();

        } catch (Exception ex) {
            Log.e(ex, "Could not parse date");
        }
        setContentView(R.layout.activity_warranty_confirmation);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Kien commit
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WarrantyConfirmationActivity.this, "Click title in ActionBar of Activity", Toast.LENGTH_SHORT).show();
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WarrantyConfirmationActivity.this, "Click icon in ActionBar of Activity", Toast.LENGTH_SHORT).show();
            }
        });

        // Set Text content
        imei1 = DeviceInfo.getImei1(this);
        model = DeviceInfo.getDeviceModel();
        activationSMS = String.format(Constants.SMS_ACTIVATION_FORMAT, imei1, model);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(String.format(getString(R.string.guide_text_1), Constants.SHORT_CODE_FEE)).append("  ");
        int startIdx = builder.length() - 1;
        builder.setSpan(new ImageSpan(this, R.drawable.ic_menu_send), startIdx, builder.length(), 0);
        builder.append(" ");
        builder.append(getString(R.string.guide_text_2)).append(Constants.NEW_LINE);
        builder.append(getString(R.string.guide_text_3)).append(Constants.NEW_LINE);

        startIdx = builder.length() - 1;
        builder.append(activationSMS);
        int endIdx = builder.length();
        builder.setSpan(new StyleSpan(Typeface.BOLD), startIdx, endIdx, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new AbsoluteSizeSpan((int) (textGuide.getTextSize() * 1.2)), startIdx, endIdx, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimaryDark)), startIdx, endIdx, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(Constants.NEW_LINE).append(getString(R.string.guide_text_4)).append(" ");
        startIdx = builder.length() - 1;
        builder.append(Constants.SHORT_CODE);
        endIdx = builder.length();
        builder.setSpan(new StyleSpan(Typeface.BOLD), startIdx, endIdx, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new AbsoluteSizeSpan((int) (textGuide.getTextSize() * 1.2)), startIdx, endIdx, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimaryDark)), startIdx, endIdx, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(" ").append(getString(R.string.guide_text_5)).append(Constants.NEW_LINE);
        startIdx = builder.length() - 1;
        builder.append(getString(R.string.guide_text_note_title));
        endIdx = builder.length();
        builder.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), startIdx, endIdx, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorAccent)), startIdx, endIdx, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(Constants.NEW_LINE);
        startIdx = builder.length() - 1;
        builder.append(getString(R.string.guide_text_note_text));
        endIdx = builder.length();
        builder.setSpan(new StyleSpan(Typeface.ITALIC), startIdx, endIdx, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorAccent)), startIdx, endIdx, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(Constants.NEW_LINE);
        builder.append(getString(R.string.guide_text_6)).append(Constants.NEW_LINE).append(getString(R.string.guide_text_7)).append(Constants.NEW_LINE);
        startIdx = builder.length() - 1;
        querySMS = String.format(Constants.SMS_QUERY_FORMAT, imei1, model);
        builder.append(querySMS);
        endIdx = builder.length();
        builder.setSpan(new StyleSpan(Typeface.BOLD), startIdx, endIdx, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new AbsoluteSizeSpan((int) (textGuide.getTextSize() * 1.2)), startIdx, endIdx, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimaryDark)), startIdx, endIdx, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(Constants.NEW_LINE).append(getString(R.string.guide_text_4)).append(" ");
        startIdx = builder.length() - 1;
        builder.append(Constants.SHORT_CODE);
        endIdx = builder.length();
        builder.setSpan(new StyleSpan(Typeface.BOLD), startIdx, endIdx, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new AbsoluteSizeSpan((int) (textGuide.getTextSize() * 1.2)), startIdx, endIdx, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.colorPrimaryDark)), startIdx, endIdx, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(" ").append(String.format(getString(R.string.guide_text_8), Constants.SHORT_CODE_FEE));
        builder.append(Constants.NEW_LINE).append(getString(R.string.warranty_confirmation_denial));
        textGuide.setText(builder);

    }

    @Override
    protected void onStart() {
        super.onStart();
        active = true;
        final Activity self = this;
        try {
            subscription = MainService.getEventBus()
                    .observeEvents(SMSWarrantyEvent.class)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<SMSWarrantyEvent>() {
                        @Override
                        public void call(SMSWarrantyEvent eventBase) {
                            if (eventBase.isSuccess()) {
                                //SMS was successfully sent and sms Received
                                textGuide.setText(eventBase.getSmsContent());
                                fab.setImageDrawable(ContextCompat.getDrawable(self, R.drawable.ic_verified_user));
                                if (guestActivationSuccess(eventBase.getSmsContent()) && AppDataHelper.readValue(self, SettingKey.WARRANTY_ACTIVATION_DATE) == null) {
                                    Date now = new Date();
                                    AppDataHelper.writeValue(self, SettingKey.WARRANTY_ACTIVATION_DATE, StringUtils.serializeDate(now));
                                }
                                buttonDeny.setText(getString(R.string.text_close));
                            } else {
                                //SMS sending failed
                                textGuide.setText(getString(R.string.warranty_sms_unsuccess_all));
                                buttonDeny.setText(getString(R.string.text_skip));
                            }
                            fab.setEnabled(!eventBase.isSuccess());
                            spinner.setVisibility(View.GONE);
                            buttonDeny.setVisibility(View.VISIBLE);
                            buttonDeny.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    self.finish();
                                }
                            });
                        }
                    });
        } catch (Exception ex) {
            Log.e(ex, "Could not subscribe to event bus from WarrantyConfirmationActivity.OnStart()");
        }
    }

    private boolean guestActivationSuccess(String smsContent) {
        return smsContent != null && smsContent.toLowerCase().contains(Constants.SMS_KEYWORD_SUCCESS);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (subscription == null || subscription.isUnsubscribed()) return;
        subscription.unsubscribe();
        active = false;
        this.finish();
    }

    @OnClick(R.id.fab)
    public void onSendClick(FloatingActionButton floatingActionButton) {
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        floatingActionButton.setEnabled(false);
        spinner.setVisibility(View.VISIBLE);
        textGuide.setText(getString(R.string.guide_text_9));
        txtWelcome.setVisibility(View.INVISIBLE);
        buttonDeny.setVisibility(View.GONE);
        Intent sendI = new Intent(Constants.INTENT_FILTER_SMS_SENT);
        sendI.putExtra(Constants.SMS_BODY, activationSMS);
        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, sendI, PendingIntent.FLAG_CANCEL_CURRENT);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(Constants.INTENT_FILTER_SMS_DELIVERED), 0);
        SmsManager smsMgr = SmsManager.getDefault();
        smsMgr.sendTextMessage(Constants.SHORT_CODE, null, activationSMS, sentPI, deliveredPI);
    }

    @OnClick(R.id.button_warranty_denial)
    public void onDenialClick(View view) {
        String activationDate = AppDataHelper.readValue(this, SettingKey.WARRANTY_ACTIVATION_DATE);
        if (activationDate == null) {
            AppDataHelper.writeValue(this, SettingKey.WARRANTY_ACTIVATION_DENIAL, true);
            AppDataHelper.writeValue(this, SettingKey.WARRANTY_ACTIVATION_DENIAL_DATE, StringUtils.serializeDate(new Date()));
        }
        this.finish();
    }
}
