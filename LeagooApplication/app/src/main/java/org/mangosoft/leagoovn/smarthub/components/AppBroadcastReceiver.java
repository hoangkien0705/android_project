package org.mangosoft.leagoovn.smarthub.components;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.mangosoft.leagoovn.smarthub.service.MainService;
import org.mangosoft.leagoovn.smarthub.ui.activity.WarrantyConfirmationActivity;
import org.mangosoft.leagoovn.smarthub.utils.AppDataHelper;
import org.mangosoft.leagoovn.smarthub.utils.Constants;
import org.mangosoft.leagoovn.smarthub.utils.Log;
import org.mangosoft.leagoovn.smarthub.utils.SettingKey;
import org.mangosoft.leagoovn.smarthub.utils.StringUtils;

import java.util.Date;

public class AppBroadcastReceiver extends BroadcastReceiver {
    private void performWarrantyProcess(Context ctx) {
        String denied = AppDataHelper.readValue(ctx, SettingKey.WARRANTY_ACTIVATION_DENIAL);
        String activationDate = AppDataHelper.readValue(ctx, SettingKey.WARRANTY_ACTIVATION_DATE);
        // If user denied warranty or has already activated, do nothing
        if (Boolean.valueOf(denied) || activationDate != null) return;
        // Check for activation deadline
        String firstLaunch = AppDataHelper.readValue(ctx, SettingKey.WARRANTY_ESTIMATE_BUY_DATE);
        if (firstLaunch != null) {
            try {
                Date firstDate = StringUtils.deserializeDate(firstLaunch);
                long diffDays = (System.currentTimeMillis() - firstDate.getTime()) / (1000 * 60 * 60 * 24);
                if (diffDays > Constants.WARRANTY_DUE_DATE) return;
            } catch (Exception ex) {
                Log.e(ex, "Could not calculate activation deadline from config file");
                AppDataHelper.writeValue(ctx, SettingKey.WARRANTY_ESTIMATE_BUY_DATE, StringUtils.serializeDate(new Date()));
            }
        }
        // Check if power circle reachs to limit
        String pwrCycleCountStr = AppDataHelper.readValue(ctx, SettingKey.POWER_CYCLE_COUNT);
        int pwrCycleCount = 0;
        if (pwrCycleCountStr != null) {
            pwrCycleCount = Integer.parseInt(pwrCycleCountStr);
        }
        if (pwrCycleCount >= Constants.LIMIT_POWER_CYCLE_COUNT) {
            // Start warranty action activity
            if (AppDataHelper.readValue(ctx, SettingKey.WARRANTY_ESTIMATE_BUY_DATE) == null)
                AppDataHelper.writeValue(ctx, SettingKey.WARRANTY_ESTIMATE_BUY_DATE, StringUtils.serializeDate(new Date()));
            if (WarrantyConfirmationActivity.active) return;
            Intent i = new Intent(ctx, WarrantyConfirmationActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(i);
        } else {
            pwrCycleCount += 1;
            AppDataHelper.writeValue(ctx, SettingKey.POWER_CYCLE_COUNT, String.valueOf(pwrCycleCount));
        }
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        switch (intent.getAction()) {
            case Intent.ACTION_BOOT_COMPLETED:
            case Constants.INTENT_FILTER_HTC_QUICKBOOT_POWER_ON:
                Intent startServiceIntent = new Intent(context, MainService.class);
                context.startService(startServiceIntent);
                break;
            case Intent.ACTION_POWER_CONNECTED:
                try {
                    performWarrantyProcess(context);
                } catch (Exception ex) {
                    Log.e(ex, "Error to process warranty confirmation dialog");
                }
                break;
        }
    }
}
