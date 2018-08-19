# cordova-multi-window
Tests if app is running in multi-window mode for Android devices
This is my first cordova plugin, scraped together looking at [this](https://medium.com/ionic-and-the-mobile-web/how-to-write-cordova-plugins-864e40025f2) tutorial. I am also not an android native developer, so the code in the java file, again,
is from trying to resolve errors as they popped along and mostly I don't have a clue on how to write a proper android class file.

If you can improve it, please PR. 

## Test Repo
Feel free to clone [this test repo](https://github.com/pliablepixels/is-multiwindow-test)

## Usage inside your app
`cordova plugin add cordova-plugin-multiwindow --save`

To use:

```
    window.MultiWindowPlugin.get(function (state) {
         console.log ("Multi-Window state is:" + state);
    },
    function (err) { 
        console.log (" *************** ERR:"+JSON.stringify(err));
    });
```
