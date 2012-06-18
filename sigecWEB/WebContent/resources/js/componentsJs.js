
// run the currently selected effect
function runEffect(event, clase) {
    // get effect type from 
    //var selectedEffect = $( "#effectTypes" ).val();
    var selectedEffect = 'blind';

    // most effect types need no options passed by default
    var options = {};
    // some effects have required parameters
    if ( selectedEffect === "scale" ) {
            options = { percent: 0 };
    } else if ( selectedEffect === "size" ) {
            options = { to: { width: 200, height: 60 } };
    }

    // run the effect
    $(clase ).toggle( selectedEffect, options, 500 );
};