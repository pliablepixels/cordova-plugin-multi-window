
package com.pliablepixels;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_COLOR_BURNPeer;

// Cordova-required packages
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MultiWindowPlugin extends CordovaPlugin {
  private static final String TAG = "MultiWindowPlugin";
  private CallbackContext callbackStop = null;
  private CallbackContext callbackStart = null;

  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {

    if (action.equals("get")) {
      boolean imw = checkMultiWindow();
      Log.d(TAG, "plugin detected multi-window as:"+imw);
      JSONObject parameter = new JSONObject();
      parameter.put("state", imw);
      PluginResult result = new PluginResult(PluginResult.Status.OK, parameter);
      callbackContext.sendPluginResult(result);
      return true;
    }

    else if (action.equals("registerOnStop")) {
      callbackStop = callbackContext;
      Log.d(TAG, "onStop() callback registered");
      return true;

    }

    else if (action.equals("registerOnStart")) {
      callbackStart = callbackContext;
      Log.d(TAG, "onStart() callback registered");
      return true;

    }

    else if (action.equals("deregisterOnStart")) {
      callbackStart = null;
      Log.d(TAG, "onStart() callback deregistered");
      return true;

    }

    else if (action.equals("deregisterOnStop")) {
      callbackStop = null;
      Log.d(TAG, "onStop() callback deregistered");
      return true;

    }

    else if (action.equals("deregisterAll")) {
      callbackStop = null;
      callbackStart = null;
      Log.d(TAG, "All callbacks deregistered");
      return true;

    }

    else {
      return false;
    }
  }

  

  public void onStop() {
    Log.d(TAG, "onStop() called");
    if (callbackStop !=null ) {
      try {
        JSONObject parameter = new JSONObject();
        parameter.put("result", true);
        PluginResult result = new PluginResult(PluginResult.Status.OK, parameter);
        result.setKeepCallback(true); // for multiple callbacks
        callbackStop.sendPluginResult(result);
      }
      catch (JSONException e) {Log.e(TAG, e.toString());}
    }
    else {
      Log.d(TAG, "no JS callback registered for onStop(), not sending to JS land");
    }
  }

  public void onStart() {
    Log.d(TAG, "onStart() called");
    if (callbackStart !=null ) {
      try {
        JSONObject parameter = new JSONObject();
        parameter.put("result", true);
        PluginResult result = new PluginResult(PluginResult.Status.OK, parameter);
        result.setKeepCallback(true); // for multiple callbacks
        callbackStart.sendPluginResult(result);
      }
      catch (JSONException e) {Log.e(TAG, e.toString());}
    }
    else {
      Log.d(TAG, "no JS callback registered for onStart() not sending to JS land");
    }
  }

  private boolean checkMultiWindow() {
    boolean isInMultiWindowMode = false;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      isInMultiWindowMode = cordova.getActivity().isInMultiWindowMode();
    }
    return isInMultiWindowMode;
  }

}
