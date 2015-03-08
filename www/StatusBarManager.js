/*
 *
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
function StatusBarManager() {
	this.isVisible = false;
};

/*
 * Show Status Bar
 */
StatusBarManager.prototype.show = function () {
	cordova.exec(null, null, "StatusBarManager", "show", []);
	this.isVisible = true;
};

/*
 * Hide Status Bar
 */
StatusBarManager.prototype.hide = function () {
	cordova.exec(null, null, "StatusBarManager", "hide", []);
	this.isVisible = false;
};

/*
 * Show Status Bar Transparent
 */
StatusBarManager.prototype.setTransparent = function () {
	cordova.exec(null, null, "StatusBarManager", "setTransparent", []);
	this.isVisible = true;
};

/*
 * Show Status Bar setTranslucent
 */
StatusBarManager.prototype.setTranslucent = function () {
	cordova.exec(null, null, "StatusBarManager", "setTranslucent", []);
	this.isVisible = true;
};

/*
 * Show Status Bar and Set This RGB color
 */
StatusBarManager.prototype.setRgbColor = function (red, green, blue) {
	cordova.exec(null, null, "StatusBarManager", "setRgbColor", [red, green, blue]);
	this.isVisible = true;
};

/*
 * Show Status Bar and Set This Hex color
 */
StatusBarManager.prototype.setColor = function (hex) {
	var rgb = this.hexToRgb(hex);
	this.setRgbColor(rgb.red, rgb.green, rgb.blue);
}

/*
 * Internal, Convert Hex Color to RGB
 */
StatusBarManager.prototype.hexToRgb = function (hex) {
	var shorthandRegex = /^#?([a-f\d])([a-f\d])([a-f\d])$/i;
	hex = hex.replace(shorthandRegex, function (m, r, g, b) {
		return r + r + g + g + b + b;
	});

	var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
	return result ? {
		red: parseInt(result[1], 16),
		green: parseInt(result[2], 16),
		blue: parseInt(result[3], 16)
	} : null;
};

module.exports = new StatusBarManager();
