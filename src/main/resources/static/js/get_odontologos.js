(function(){
    //con fetch invocamos a la API de pacientes con el método GET
    //nos devolverá un JSON con una colección de pacientes
    const url = '/odontologos';
    const settings = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    };
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            //recorremos la colección de pacientes del JSON
            for(const odontologo of data){
                //logeo odontologo
                console.log(odontologo);
                //por cada odontologo armaremos una fila de la tabla
                //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos
                //el odontologo
                const table = document.getElementById("odontologosTable");
                const odontologosRow = table.insertRow();
                const tr_id = 'tr_' + odontologo.id;
                odontologosRow.id = tr_id;

                //por cada odontologo creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
                //dicho boton invocara a la funcion de java script deleteByKey que se encargará
                //de llamar a la API para eliminar al odontologo
                const deleteButton = '<button' +
                    ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                    ' type="button" onclick="deleteByOdontologo('+odontologo.id+')" class="btn btn-danger btn_delete">' +
                    '&times' +
                    '</button>';

                //por cada odontologo creamos un boton que muestra el id y que al hacerle clic invocará
                //a la función de java script findBy que se encargará de buscar al odontologo que queremos
                //modificar y mostrar los datos del mismo en un formulario.
                const updateButton = '<button' +
                    ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                    ' type="button" onclick="findBy('+odontologo.id+')" class="btn btn-info btn_id">' +
                    odontologo.id +
                    '</button>';

                //armamos cada columna de la fila
                //como primer columna pondremos el boton modificar
                //luego los datos del odontologo
                //como ultima columna el boton eliminar
                odontologosRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class="td_first_name">' + odontologo.nombre.toUpperCase() + '</td>' +
                    '<td class="td_last_name">' + odontologo.apellido.toUpperCase() + '</td>' +
                    '<td class="td_matricula">' + odontologo.matricula + '</td>' +
                    '<td>' + deleteButton + '</td>';

            };
        });
})();

(function(){
    const pathname = window.location.pathname;
    if (pathname === "/odontologos.html") {
        document.querySelector(".nav .nav-item:last-child a").classList.add("active");
    }
});
