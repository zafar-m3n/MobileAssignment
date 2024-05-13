package com.example.assignment1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.SmsMessage;
import android.widget.Toast;
import android.text.TextUtils;
import android.provider.Telephony;
import android.util.Log;



public class SMSReceiver4 extends BroadcastReceiver {
    public static final String SMS_FILTER = "SMS_FILTER";
    public static final String SMS_MSG_KEY = "SMS_MSG_KEY";

    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);

        for(int i=0; i<messages.length;i++){
            SmsMessage currentMessage = messages[i];
            String number =currentMessage.getDisplayOriginatingAddress();
            String message =currentMessage.getDisplayMessageBody();

            String messageToast = String.format("category ID: %s,category name: %s, event count: %s",number,message);
            Toast.makeText(context,messageToast,Toast.LENGTH_SHORT).show();

            Intent msgIntent = new Intent();
            msgIntent.setAction(SMS_FILTER);
            msgIntent.putExtra(SMS_MSG_KEY, message);
            context.sendBroadcast(msgIntent);
        }


    }
}