var exec = require('cordova/exec');
var PLUGIN_NAME='IsMultiWindowPlugin';

var IsMultiWindowPlugin = {

  get: function (cb) {
    //console.log ("*************** JS LAND inside  Get");
    exec(cb, null, PLUGIN_NAME, 'get',[]);
  }
};

module.exports = IsMultiWindowPlugin;