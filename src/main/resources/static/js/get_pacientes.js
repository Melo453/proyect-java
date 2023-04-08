(function(){
    //con fetch invocamos a la API de pacientes con el método GET
    //nos devolverá un JSON con una colección de pacientes
    const url = '/pacientes';
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
            for(const paciente of data){
                //logeo paciente
                console.log(paciente);
                //por cada paciente armaremos una fila de la tabla
                //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos
                //el paciente
                const table = document.getElementById("pacientesTable");
                const pacientesRow = table.insertRow();
                const tr_id = 'tr_' + paciente.id;
                pacientesRow.id = tr_id;

                //por cada paciente creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
                //dicho boton invocara a la funcion de java script deleteByKey que se encargará
                //de llamar a la API para eliminar al paciente
                const deleteButton = '<button' +
                    ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                    ' type="button" onclick="deleteByPaciente('+paciente.id+')" class="btn btn-danger btn_delete">' +
                    '&times' +
                    '</button>';

                //por cada paciente creamos un boton que muestra el id y que al hacerle clic invocará
                //a la función de java script findBy que se encargará de buscar al paciente que queremos
                //modificar y mostrar los datos del mismo en un formulario.
                const updateButton = '<button' +
                    ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                    ' type="button" onclick="findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                    paciente.id +
                    '</button>';

                //armamos cada columna de la fila
                //como primer columna pondremos el boton modificar
                //luego los datos del paciente
                //como ultima columna el boton eliminar
                pacientesRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class="td_first_name">' + paciente.nombre.toUpperCase() + '</td>' +
                    '<td class="td_last_name">' + paciente.apellido.toUpperCase() + '</td>' +
                    '<td class="td_dni">' + paciente.dni + '</td>' +
                    '<td class="td_fecha_ingreso">' + paciente.fechaingreso.toUpperCase() + '</td>' +
                    '<td class="td_domicilio_calle">' + paciente.domicilio.calle.toUpperCase() + '</td>' +
                    '<td class="td_domicilio_numero">' + paciente.domicilio.numero + '</td>' +
                    '<td class="td_domicilio_localidad">' + paciente.domicilio.localidad.toUpperCase() + '</td>' +
                    '<td class="td_domicilio_provincia">' + paciente.domicilio.provincia.toUpperCase() + '</td>' +
                    '<td>' + deleteButton + '</td>';

            };
        });
})();

(function(){
    const pathname = window.location.pathname;
    if (pathname === "/pacientes.html") {
        document.querySelector(".nav .nav-item:last-child a").classList.add("active");
    }
});
