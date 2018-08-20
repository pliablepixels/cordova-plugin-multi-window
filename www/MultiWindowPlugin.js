var exec = require('cordova/exec');
var PLUGIN_NAME='MultiWindowPlugin';

var MultiWindowPlugin = {

  get: function (cb) {
    exec(cb, null, PLUGIN_NAME, 'get',[]);
  },
  registerOnStart: function (cb) {
    exec (cb, null, PLUGIN_NAME,'registerOnStart',[]);
  },
  registerOnStop: function (cb) {
    exec (cb, null, PLUGIN_NAME,'registerOnStop',[]);
  },
  deregisterOnStart: function (cb) {
    exec (cb, null, PLUGIN_NAME,'deregisterOnStart',[]);
  },
  deregisterOnStop: function (cb) {
    exec (cb, null, PLUGIN_NAME,'deregisterOnStop',[]);
  },
  deregisterAll: function (cb) {
    exec (cb, null, PLUGIN_NAME,'deregisterAll',[]);
  }


};

module.exports = MultiWindowPlugin;
