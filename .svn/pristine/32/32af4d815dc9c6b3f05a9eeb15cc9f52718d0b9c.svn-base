package org.mangosoft.leagoovn.smarthub.components;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

import org.mangosoft.leagoovn.smarthub.model.SMSWarrantyEvent;
import org.mangosoft.leagoovn.smarthub.service.MainService;
import org.mangosoft.leagoovn.smarthub.utils.Constants;
import org.mangosoft.leagoovn.smarthub.utils.Log;

public class SmsResultReceiver extends BroadcastReceiver {
    public SmsResultReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()) {
            case Constants.INTENT_FILTER_SMS_SENT:
                Log.d("SMS submitted.");
                int resultCode = getResultCode();
                if (resultCode != Activity.RESULT_OK) {
                    //Sending failed
                    SMSWarrantyEvent failureEvent = new SMSWarrantyEvent();
                    failureEvent.setSuccess(false);
                    MainService.getEventBus().post(failureEvent);
                    Log.d("Failed to send SMS");
                } else {
                    Toast.makeText(context, "Gửi SMS kích hoạt:" + Constants.NEW_LINE + intent.getStringExtra(Constants.SMS_BODY), Toast.LENGTH_LONG).show();
                }
                break;
            case Constants.INTENT_FILTER_SMS_DELIVERED:
                Log.d("SMS delivered.");
                break;
            case Telephony.Sms.Intents.SMS_RECEIVED_ACTION:
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    Log.d("SMS received. Parse to determine if this is warranty SMS...");
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    @SuppressWarnings("deprecation")
                    SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdus[0]);
                    String smsLowercased = sms.getMessageBody().toLowerCase();
                    if ((smsLowercased.contains(Constants.SMS_KEYWORD_BAO_HANH) &&
                            (smsLowercased.contains(Constants.SMS_KEYWORD_KICH_HOAT) || smsLowercased.contains(Constants.SMS_KEYWORD_LEAGOO)))) {
                        SMSWarrantyEvent event = new SMSWarrantyEvent();
                        event.setSmsContent(sms.getMessageBody());
                        event.setSmsCenter(sms.getServiceCenterAddress());
                        event.setFrom(sms.getOriginatingAddress());
                        event.setSuccess(true);
                        MainService.getEventBus().post(event);
                        Log.d(sms.getMessageBody() + Constants.NEW_LINE + sms.getServiceCenterAddress() + Constants.NEW_LINE + sms.getOriginatingAddress());
                    }
                }
                break;
            default:
                break;
        }
    }


}
