
package com.pliablepixels;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

// Cordova-required packages
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;


public class MultiWindowPlugin extends CordovaPlugin {
  private static final String TAG = "MultiWindowPlugin";

  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {

    if (action.equals("get")) {
      boolean imw = checkMultiWindow();
      Log.d(TAG, "plugin detected multi-window as:"+isInMultiWindowMode);
      final PluginResult result = new PluginResult(PluginResult.Status.OK, imw);
      callbackContext.sendPluginResult(result);
      return true;
    }
    else {
      return false;
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
