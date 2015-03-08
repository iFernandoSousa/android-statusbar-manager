<!---
 license: Licensed to the Apache Software Foundation (ASF) under one
         or more contributor license agreements.  See the NOTICE file
         distributed with this work for additional information
         regarding copyright ownership.  The ASF licenses this file
         to you under the Apache License, Version 2.0 (the
         "License"); you may not use this file except in compliance
         with the License.  You may obtain a copy of the License at

           http://www.apache.org/licenses/LICENSE-2.0

         Unless required by applicable law or agreed to in writing,
         software distributed under the License is distributed on an
         "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
         KIND, either express or implied.  See the License for the
         specific language governing permissions and limitations
         under the License.
-->

Android-StatusBar-Manager
============================

Manager the Android Status bar, and allow using Translucent with KITKAT and set Color or Transparent for Lollipop.

Plugin for Cordova >= 3.0
#### Required
Android SDK API 21

#### Installation

For Cordova:

        cordova plugin add https://github.com/iFernandoSousa/android-statusbar-manager.git

#### Using the plugin
If you use this plugin, you do not need use Cordova StatusBar, because this has all features, like (Show and Hide) status bar.

You can set the StatusBar Color using RGB (Only available to LOLLIPOP), to use RGB call:

    StatusBarManager.setRgbColor(204, 0, 204);

To set StatusBar Color using Hex colors (Only available to LOLLIPOP), call:

    StatusBarManager.setColor('#CC00CC');
	

Or you can use short hex, (Only available to LOLLIPOP), call:

    StatusBarManager.setColor('#C0C');

To Hide StatusBar call:

    StatusBarManager.hide();
    
To Show StatusBar call:

    StatusBarManager.show();
    
To set StatusBar Transparent (Only available to LOLLIPOP), call:

    StatusBarManager.setTransparent();

To set StatusBar Translucent (Only available to KITKAT and LOLLIPOP), call:

    StatusBarManager.setTranslucent();
