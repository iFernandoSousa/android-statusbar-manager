/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/
package br.com.hotforms;

import android.app.Activity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONException;

public class StatusBarManager extends CordovaPlugin {
    private static final String TAG = "StatusBarManager";
	private static final int currentapiVersion = android.os.Build.VERSION.SDK_INT;
    /**
     * Sets the context of the Command. This can then be used to do things like
     * get file paths associated with the Activity.
     *
     * @param cordova The context of the main Activity.
     * @param webView The CordovaWebView Cordova is running in.
     */
    @Override
    public void initialize(final CordovaInterface cordova, CordovaWebView webView) {
        Log.v(TAG, "StatusBarManager: initialization");
        super.initialize(cordova, webView);
		
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Window window = cordova.getActivity().getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            }
        });
    }

    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback id used when calling back into JavaScript.
     * @return                  True if the action was valid, false otherwise.
     */
    @Override
    public boolean execute(String action, CordovaArgs args, final CallbackContext callbackContext) throws JSONException {
        Log.v(TAG, "Executing action: " + action);
        final Activity activity = this.cordova.getActivity();
        final Window window = activity.getWindow();
        
		if ("_ready".equals(action)) {
            boolean statusBarVisible = (window.getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) == 0;
            callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, statusBarVisible));
        }
		else if ("show".equals(action)) {
            this.cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
					window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                }
            });
            return true;
        }
		else if ("hide".equals(action)) {
            this.cordova.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
					
					if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP) {
						window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
						window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
						window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
					}
                }
            });
            return true;
        }
		else if ("setTranslucent".equals(action)) {
			if (currentapiVersion >= android.os.Build.VERSION_CODES.KITKAT) {
				this.cordova.getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
						if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP) {
							window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
							window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
						}
						
						window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
					}
				});
				return true;
			}
			else {
				return false;	
			}
		}
		else if ("setTransparent".equals(action)) {
			if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP) {
				this.cordova.getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {						
						window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
						
						window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
						window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
						window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
					}
				});
				return true;
			}
			else {
				return false;
			}
		}
		else if ("setColor".equals(action)) {
			if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP) {
				this.setColor(args.getInt(0), args.getInt(1), args.getInt(2));
				return true;
			}
			else {
				return false;
			}
        }

        return false;
    }
	
	public synchronized void setColor(final int r, final int g, final int b) {
		final Activity activity = this.cordova.getActivity();
        final Window window = activity.getWindow();
		
		this.cordova.getActivity().runOnUiThread(new Runnable() {
		@Override
		public void run() {
			window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			
			window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			
			window.setStatusBarColor(android.graphics.Color.rgb(r,g,b));
		}});
	}
}
