var form = document.getElementById("form");
var del = document.getElementById("delete");

del.addEventListener('click', function(e){
    e.preventDefault();
    var fd = new FormData(form);

    var datos = {
        method: "DELETE",
        body: fd 
    };

    fetch('https://p1-w2.herokuapp.com/Sesion', datos)
    .then( res => res.json())
    .then( data => {
        if(data.status == 200){
            window.open('https://p1-w2.herokuapp.com/public/views/delete.html', "_self");
            alert(data.message);
        }else{
            window.open('https://p1-w2.herokuapp.com/public/views/notdelete.html', "_self");
            alert(data.message);
        }
    })
    .catch(error => console.error());
    });