package com.rombit.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import android.content.Context;
import java.io.File;

import org.openalpr.OpenALPR;
import org.openalpr.model.Results;
import org.openalpr.model.ResultsError;

public class ALPR extends CordovaPlugin {
    static final String ANDROID_DATA_DIR = "/data/data/com.rombit.plugin";

    final String openAlprConfFile = ANDROID_DATA_DIR + File.separatorChar + "runtime_data" + File.separatorChar + "openalpr.conf";

    @Override
    public boolean execute(
        String action,
        JSONArray args,
        CallbackContext callbackContext
        ) throws JSONException {
        if ("alpr".equals(action)) {
            alpr(args.getString(0), callbackContext);
            return true;
        }

        return false;
    }

    private void alpr(
            String imagePath,
            CallbackContext callbackContext
    ) {
        String result = OpenALPR.Factory.create(this.cordova.getActivity().getApplicationContext(), ANDROID_DATA_DIR).recognizeWithCountryRegionNConfig("eu", "", imagePath, openAlprConfFile, 10);
        callbackContext.success(result);
    }
}