var datos = {
    method: "POST"
};

fetch('https://p1-w2.herokuapp.com/Sesion', datos)
    .then( res => res.json())
    .then( data => {
        document.getElementById("h1").value = `Bienvenido ${data.nombre}`;
        document.getElementById("cedula").value = data.cedula;
        document.getElementById("nombre").value = data.cedula;
        document.getElementById("apellido").value = data.cedula;
        document.getElementById("fdn").value = data.cedula;
        document.getElementById("password").value = data.cedula;
        document.getElementById("email").value = data.cedula;
    })
    .catch(error => console.error());