var exec = require('cordova/exec');

exports.alpr = function(arg0, success, error) {
    exec(success, error, "ALPR", "alpr", [arg0]);
};
