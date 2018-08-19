var exec = require('cordova/exec');
var PLUGIN_NAME='MultiWindowPlugin';

var MultiWindowPlugin = {

  get: function (cb) {
    //console.log ("*************** JS LAND inside  Get");
    exec(cb, null, PLUGIN_NAME, 'get',[]);
  }
};

module.exports = MultiWindowPlugin;
