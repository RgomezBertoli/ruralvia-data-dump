var exec = require('cordova/exec');

exports.volcado = function(success, error) {
    exec(success, error, "ruralvia-data-dump", "volcado", []);
};
