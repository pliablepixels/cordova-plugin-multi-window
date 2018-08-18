
package com.pliablepixels;

import android.app.Activity;
import android.os.Build;
import android.util.Log;


// Cordova-required packages
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class IsMultiWindowPlugin extends CordovaPlugin {

  private static final String TAG = "IsMultiWindowPlugin";


  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
      super.initialize(cordova,webView);
      //Log.d(TAG, "*********** JAVA land Initializing plugin");
  }

 
  public boolean execute(String action, JSONArray args,
    final CallbackContext callbackContext) throws JSONException {

      //Log.d(TAG,"************ JAVA land action is "+action);
      if (action.equals("get")) {
          
                boolean imw = checkMultiWindow();
                Log.i(TAG, "******** JAVA LAND RETURNED FROM checkMultiWindow with "+imw);
                final PluginResult result = new PluginResult(PluginResult.Status.OK, imw);
                callbackContext.sendPluginResult(result);
      }
            return true;
       
    }


    private  boolean checkMultiWindow() {
        //Log.d(TAG,"*********** Java land Inside checkwindow");
         boolean isInMultiWindowMode = false;
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        isInMultiWindowMode = cordova.getActivity().isInMultiWindowMode();
      }
      
      Log.d(TAG,"*********** Java land checkWindow returning "+isInMultiWindowMode);
      return isInMultiWindowMode;

    }
    
  }
