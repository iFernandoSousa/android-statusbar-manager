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

# br.com.hotforms.android.statusbarcolor

Android-statusbar-color
============================

Set the Android Lollipop statusbar color.

Plugin for Cordova >= 4.0
Required SDK API 21

#### Installation

For Cordova:

        cordova plugin add https://github.com/iFernandoSousa/android-statusbar-color.git

#### Using the plugin
This plugin not replace the Cordova StatusBar, just add new functionality to allow you change StatusBar Color.

You can set the Statusbar Color using RGB or Hex, to use RGB call:

    StatusBarColor.setRgbColor(204, 0, 204);

Where three parameters are the RGB colors.


To use Hexa colors call:

    StausBarColor.setColor('#CC00CC');
	

Or you can use short hexa

    StausBarColor.setColor('#C0C');
