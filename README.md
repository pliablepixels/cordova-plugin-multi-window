# cordova-plugin-multi-window
Tests if app is running in multi-window mode for Android devices. Also lets you trap `onStop()` and `onStart()` which are important to differentiate
from `onPause` events while in multi-window.

This is my first cordova plugin, scraped together looking at [this](https://medium.com/ionic-and-the-mobile-web/how-to-write-cordova-plugins-864e40025f2) tutorial. I am  not an android native developer, so the code in the java file is a result of looking at other examples and trying to resolve compile/run time errors as they came along. I don't have a clue on how to write a proper android class file.

If you can improve it, please PR. 

## Test Repo
Feel free to clone [this test repo](https://github.com/pliablepixels/is-multiwindow-test)

## Usage inside your app
`cordova plugin add cordova-plugin-multiwindow --save`

### To get multi-window state:

```
window.MultiWindowPlugin.get(function (result) {
        console.log ("Multi-Window state is:" + result.state);
},
function (err) { 
    console.log (" *************** ERR:"+JSON.stringify(err));
});
```

### To register for onStop() and onStart():

**NOTE: onStart() and onStop() are also invoked in non-multi window mode**

This plugin allows multiple callbacks to register to the events, because in practice,
while building an app you'll want to trap these events in different controllers to manage pause/resume
states in different views. To do this, you can pass a handle (string) during registration which you can use to de-register that specific instance.

```
// Make sure you do this AFTER deviceReady
window.MultiWindowPlugin.registerOnStop("my-stop-handle-thiscontroller", onStop);
window.MultiWindowPlugin.registerOnStart("my-start-handle", onStart);
// and in a different controller, maybe
window.MultiWindowPlugin.registerOnStop("my-stop-handle-othercontroller", onOtherStopHandler);
```

### To de-register for onStop() and onStart():

```
// Make sure you do this AFTER deviceReady
window.MultiWindowPlugin.deregisterOnStop("my-stop-handle-othercontroller");
window.MultiWindowPlugin.deregisterOnStart("my-start-handle");
```

Or, all together:

**NOTE: This will de-register ALL callbacks in all controllers across the app, so use with caution**

```
// Make sure you do this AFTER deviceReady
window.MultiWindowPlugin.deregisterAll();
```


