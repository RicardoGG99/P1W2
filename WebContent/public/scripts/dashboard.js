var form = document.getElementById("form");

form.addEventListener('submit', function(e){
    e.preventDefault();
    var fd = new FormData(form);

    var datos = {
        method: "POST",
        body: fd 
    };

    

    fetch('https://p1-w2.herokuapp.com/Sesion', datos)
    .then( res => res.json())
    .then( data => {
        console.log(data.cedula)
        document.getElementById("h1").value = `Bienvenido ${data.nombre}`;
        document.getElementById("cedula").value = data.cedula;
        document.getElementById("nombre").value = data.nombre;
        document.getElementById("apellido").value = data.apellido;
        document.getElementById("fdn").value = data.fdn;
        document.getElementById("password").value = data.password;
        document.getElementById("email").value = data.email;
        window.open('https://p1-w2.herokuapp.com/public/views/logged.html', "_self");
    })
    .catch(error => console.error());

});