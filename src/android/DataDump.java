package com.cajarural.data;

import org.apache.cordova.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import android.content.Context;
import android.content.SharedPreferences;

public class DataDump extends CordovaPlugin {

    public Context ctx;

    public CallbackContext callback;
    public static String[] actions = {"volcado"};

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        callback = callbackContext;

        if (action.equals(actions[0])) {
            realizarVolcado();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        ctx = cordova.getActivity();
    }

    public void realizarVolcado() throws JSONException {
        SharedPreferences pref = ctx.getSharedPreferences("banca_settings", 0);
        boolean firstTime = pref.getBoolean("VOLCADO", false);

        String obj;

        if(!firstTime){
            String active = pref.getString("jsonactive_user", "");
            String list = pref.getString("jsonlist_users", "");

            active = active.replaceAll("CCifrado", "campoCifrado");
            list = list.replaceAll("CCifrado", "campoCifrado");

            obj = "{ "
                + "\"resultado\": \"ok\", "
                + "\"active_user\": " + active + ", "
                + "\"list_users\": " + list + ""
                + " }";

        } else {
            obj = "{ "
                + "\"resultado\": \"error\""
                + " }";
        }

        SharedPreferences.Editor ed = pref.edit();
        ed.putBoolean("VOLCADO", true);
        ed.apply();

        JSONObject result = new JSONObject(obj);
        callback.success(result);
    }
}