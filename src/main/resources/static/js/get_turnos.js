(function(){
    //con fetch invocamos a la API de turnos con el método GET
    //nos devolverá un JSON con una colección de turnos
    const url = '/turnos';
    const settings = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    };
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            //recorremos la colección de turnos del JSON
            for(const turno of data){
                //logeo turno
                console.log(turno);
                //por cada turno armaremos una fila de la tabla
                //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos
                //el turno
                const table = document.getElementById("turnosTable");
                const turnosRow = table.insertRow();
                const tr_id = 'tr_' + turno.id;
                turnosRow.id = tr_id;

                //por cada turno creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
                //dicho boton invocara a la funcion de java script deleteByKey que se encargará
                //de llamar a la API para eliminar al turno
                const deleteButton = '<button' +
                    ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                    ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                    '&times' +
                    '</button>';

                //por cada turno creamos un boton que muestra el id y que al hacerle clic invocará
                //a la función de java script findBy que se encargará de buscar al turno que queremos
                //modificar y mostrar los datos del mismo en un formulario.
                const updateButton = '<button' +
                    ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                    ' type="button" onclick="findBy('+turno.id+')" class="btn btn-info btn_id">' +
                    turno.id +
                    '</button>';

                //armamos cada columna de la fila
                //como primer columna pondremos el boton modificar
                //luego los datos del turno
                //como ultima columna el boton eliminar
                turnosRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class="td_fecha">' + turno.fechaTurno.toUpperCase() + '</td>' +
                    '<td class="td_first_name">' + turno.odontologo.nombre.toUpperCase() + '</td>' +
                    '<td class="td_last_name">' + turno.odontologo.apellido.toUpperCase() + '</td>' +
                    '<td class="td_matricula">' + turno.odontologo.matricula + '</td>' +
                    '<td class="td_first_namePaciente">' + turno.paciente.nombre.toUpperCase() + '</td>' +
                     '<td class="td_last_namePaciente">' + turno.paciente.apellido.toUpperCase() + '</td>' +
                     '<td class="td_dni">' + turno.paciente.dni + '</td>' +
                    '<td>' + deleteButton + '</td>';

            };
        });
})();

(function(){
    const pathname = window.location.pathname;
    if (pathname === "/turnos.html") {
        document.querySelector(".nav .nav-item:last-child a").classList.add("active");
    }
});
