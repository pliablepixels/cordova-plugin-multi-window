
package com.pliablepixels;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import java.util.ArrayList; 
import java.util.Iterator;

// Cordova-required packages
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MultiWindowPlugin extends CordovaPlugin {
  private static final String TAG = "MultiWindowPlugin";

  public class MultiCallback {
      String handle;
      CallbackContext cb;

      
      public MultiCallback () {
        this.handle = "";
        this.cb = null;
      }

      public MultiCallback(CallbackContext cb) {
        this.handle = "";
        this.cb = cb;
      }

      public MultiCallback(CallbackContext cb, String handle) {
        this.handle = handle;
        this.cb = cb;
      }

  }

  private ArrayList<MultiCallback> callbackStopList = new ArrayList<MultiCallback>();
  private ArrayList<MultiCallback> callbackStartList = new ArrayList<MultiCallback>();


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
      String handle = args.getString(0);
      MultiCallback cbk = new MultiCallback(callbackContext, handle);
      callbackStopList.add(cbk);
      Log.d(TAG, "onStop() callback registered, handle="+ cbk.handle+" total registered = " + callbackStopList.size());
   
      return true;

    }

    else if (action.equals("registerOnStart")) {
      String handle = args.getString(0);
      MultiCallback cbk = new MultiCallback(callbackContext, handle);
      callbackStartList.add(cbk);
      Log.d(TAG, "onStart() callback registered, handle="+cbk.handle+" total registered = " + callbackStartList.size());
      return true;

    }

    else if (action.equals("deregisterOnStart")) {
      String handle = args.getString(0);
      Log.d(TAG, "deregisterOnStart with handle="+handle);

      Iterator<MultiCallback> iter = callbackStartList.iterator();
      while (iter.hasNext()) {
        String val = iter.next().handle;
        if (val.equals(handle)) {
          iter.remove(); 
        }
      }
      Log.d(TAG, "onStart() callback deregistered, remaining registered = " + callbackStartList.size());
      return true;

    }

    else if (action.equals("deregisterOnStop")) {
      String handle = args.getString(0);
      Log.d(TAG, "deregisterOnStop with handle="+handle);
  
      Iterator<MultiCallback> iter = callbackStopList.iterator();
      while (iter.hasNext()) {
        String val = iter.next().handle;
        if (val.equals(handle)) {
          iter.remove(); 
        }
      }
      Log.d(TAG, "onStop() callback deregistered, remaining registered = " + callbackStopList.size());
      return true;

    }

    else if (action.equals ("deregisterAll")) {
        callbackStartList.clear();
        callbackStopList.clear();
        Log.d(TAG, "ALL onStart() & onStop() callbacks cleared");
        return true;
    }

    else {
      return false;
    }
  }

  

  public void onStop() {
    Log.d(TAG, "onStop() called");
    if (callbackStopList.size() >0) {
      try {

        for (MultiCallback mcb: callbackStopList) {
          JSONObject parameter = new JSONObject();
          parameter.put("result", true);
          PluginResult result = new PluginResult(PluginResult.Status.OK, parameter);
          result.setKeepCallback(true); // for multiple callbacks
          mcb.cb.sendPluginResult(result);
        }
        
      }
      catch (JSONException e) {Log.e(TAG, e.toString());}
    }
    else {
      Log.d(TAG, "no JS callback registered for onStop(), not sending to JS land");
    }
  }

  public void onStart() {
    Log.d(TAG, "onStart() called");
    if (callbackStartList.size() >0 ) {
      try {

        for (MultiCallback mcb: callbackStartList) {
          JSONObject parameter = new JSONObject();
          parameter.put("result", true);
          PluginResult result = new PluginResult(PluginResult.Status.OK, parameter);
          result.setKeepCallback(true); // for multiple callbacks
          mcb.cb.sendPluginResult(result);
        }
        
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
