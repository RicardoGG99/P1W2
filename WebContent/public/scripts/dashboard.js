var form = document.getElementById("form");
var update = document.getElementById("update");
var fd = new FormData(form);
var help = 0;

var datos = {
        method: "POST",
        body: fd 
    };

    fetch('https://p1-w2.herokuapp.com/Sesion', datos)
    .then( res => res.json())
    .then( data => {
        if(help == 0){
            console.log(data.cedula);
            console.log(data.nombre);
            console.log(data.apellido);
            console.log(data.fdn);
            console.log(data.password);
            console.log(data.email);
            console.log(data.segundoNombre);
            console.log(data.segundoApellido);
            console.log(data.telf);
            document.getElementById("h1").value = `Bienvenido ${data.nombre}`;
            document.getElementById("cedula").value = data.cedula;
            document.getElementById("nombre").value = data.nombre;
            document.getElementById("apellido").value = data.apellido;
            document.getElementById("fdn").value = data.fdn;
            document.getElementById("password").value = data.password;
            document.getElementById("email").value = data.email;
            document.getElementById("email").value = data.segundoNombre;
            document.getElementById("email").value = data.segundoApellido;
            document.getElementById("email").value = data.telf;
            help++;
            console.log(help)
            window.self();
        }
            
    })
    .catch(error => console.error());

    update.addEventListener('click', function(e1){
        e1.preventDefault();

    var datosUpdate = {
        method: "PUT",
        body: fd 
    };

    fetch('https://p1-w2.herokuapp.com/Sesion', datosUpdate)
    .then( res => res.json())
    .then( data => {
        if(data.status == 200){
            window.open('https://p1-w2.herokuapp.com/public/views/updated.html', "_self");
            alert(data.message);
        }else{
            window.open('https://p1-w2.herokuapp.com/public/views/notupdated.html', "_self");
            alert(data.message);
        }
    })
    .catch(error => console.error());
    });





