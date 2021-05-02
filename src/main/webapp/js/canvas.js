// canvas.js
var pos = {
    drawable: false,
    x: -1,
    y: -1
};
var canvas, ctx;
 
window.onload = function(){
    canvas = document.getElementById("canvas");
    ctx = canvas.getContext("2d");
	ctx.strokeStyle = "#223";
    ctx.lineWidth = 5;

    canvas.addEventListener("mousedown", listener);
    canvas.addEventListener("mousemove", listener);
    canvas.addEventListener("mouseup", listener);
    canvas.addEventListener("mouseout", listener);
}

document.getElementById('saveMyArtBtn').addEventListener('click', function(e) {
    // e.preventDefault();
    e.target.href = canvas.toDataURL();
});

function listener(event){
    switch(event.type){
        case "mousedown":
            initDraw(event);
            break;
 
        case "mousemove":
            if(pos.drawable)
                draw(event);
            break;
 
        case "mouseout":
        case "mouseup":
            finishDraw();
            break;
    }
}


function initDraw(event){
    ctx.beginPath();
    pos.drawable = true;
    var coors = getPosition(event);
    pos.X = coors.X;
    pos.Y = coors.Y;
    ctx.moveTo(pos.X, pos.Y);
}
 
function draw(event){
    var coors = getPosition(event);
    ctx.lineTo(coors.X, coors.Y);
    pos.X = coors.X;
    pos.Y = coors.Y;
    ctx.stroke();
}
 
function finishDraw(){
    pos.drawable = false;
    pos.X = -1;
    pos.Y = -1;
}
 
function getPosition(event){
    var x = event.pageX - canvas.offsetLeft;
    var y = event.pageY - canvas.offsetTop;
    return {X: x, Y: y};
}

// color picker
var colorPicker = new iro.ColorPicker('#picker');

$(function () {
    $("#picker").stop().hide();

    $('.color').click(function() {
       ctx.strokeStyle = $(this).text();
    });

    $('#brushSize').change(function() {
        console.log(this.value);
        ctx.lineWidth = $(this).val();
    });

    $("#pickerBtn").toggle(function() {
        $("#picker").stop().show();
    }, function() {
        $("#picker").stop().hide();
    });

    $("#picker").click(function () {
        var hex = colorPicker.color.hexString;
        ctx.strokeStyle = hex;
    });
});
