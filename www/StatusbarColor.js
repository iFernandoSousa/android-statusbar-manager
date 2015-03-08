function StatusBarColor () {
};

StatusBarColor.prototype.setRgbColor = function (red, green, blue) {
	cordova.exec(null, null, 'StatusBarColor', 'setColor', [red, green, blue]);
};

StatusBarColor.prototype.hexToRgb = function (hex) {
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

StatusBarColor.prototype.setColor = function (hex) {
	var rgb = this.hexToRgb(hex);
	this.setRgbColor(rgb.red, rgb.green, rgb.blue);
};

module.exports = new StatusBarColor();
