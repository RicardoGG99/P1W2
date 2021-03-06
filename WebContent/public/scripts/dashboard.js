var form = document.getElementById("form");
var fd = new FormData(form);
var datos = {
        method: "POST",
        body: fd 
    };

    fetch('https://p1-w2.herokuapp.com/Sesion', datos)
    .then( res => res.json())
    .then( data => {
        console.log(data.cedula);
        console.log(data.nombre);
        console.log(data.apellido);
        console.log(data.fdn);
        console.log(data.password);
        console.log(data.email);
        document.getElementById("h1").value = `Bienvenido ${data.nombre}`;
        document.getElementById("cedula").value = data.cedula;
        document.getElementById("nombre").value = data.nombre;
        document.getElementById("apellido").value = data.apellido;
        document.getElementById("fdn").value = data.fdn;
        document.getElementById("password").value = data.password;
        document.getElementById("email").value = data.email;
        window.self();
    })
    .catch(error => console.error());

