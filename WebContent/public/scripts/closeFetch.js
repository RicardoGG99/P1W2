var form = document.getElementById("form");
var closed = document.getElementById("closed");

closed.addEventListener('click', function(e){
    e.preventDefault();
    var fd = new FormData(form);

    var datos = {
        method: "PUT",
        body: fd,
        mensaje: "cerrar" 
    };

    fetch('https://p1-w2.herokuapp.com/Sesion', datos)
    .then( res => res.json())
    .then( data => {
        if(data.status == 200){
            window.open('https://p1-w2.herokuapp.com/login.html', "_self");
            alert(data.message);
        }else{
            window.open('https://p1-w2.herokuapp.com/public/views/notclose.html', "_self");
            alert(data.message);
        }
    })
    .catch(error => console.error());
    });