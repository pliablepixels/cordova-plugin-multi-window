var exec = require('cordova/exec');
var PLUGIN_NAME='MultiWindowPlugin';

var MultiWindowPlugin = {

  get: function (cb) {
    exec(cb, null, PLUGIN_NAME, 'get',[]);
  },
  registerOnStart: function (handle, onStartCallback) {
    exec (onStartCallback, null, PLUGIN_NAME,'registerOnStart',[handle]);
  },
  registerOnStop: function (handle, onStopCallback) {
    exec (onStopCallback, null, PLUGIN_NAME,'registerOnStop',[handle]);
  },
  deregisterOnStart: function (handle) {
    exec (null, null, PLUGIN_NAME,'deregisterOnStart',[handle]);
  },
  deregisterOnStop: function (handle) {
    exec (null, null, PLUGIN_NAME,'deregisterOnStop',[handle]);
  },
  deregisterAll: function() {
    exec (null, null, PLUGIN_NAME, 'deregisterAll');
  }

};

module.exports = MultiWindowPlugin;
