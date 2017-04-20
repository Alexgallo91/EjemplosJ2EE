//nos var a retornar la tabla a una variable
function obtenerTabla()
{
	var tabla = document.getElementById("tablaBD");
	return tabla;
}

function mostrarDetalleTabla()
{
	var tabla = obtenerTabla();
	var numeroFilas = tabla.rows.length;
	var numeroCeldas = 0;

	//conteo de filas
	for(var i = 0; i < numeroFilas; i++)
	{
		//conteo de celdas
		for(var j = 0; j < tabla.rows[i].cells.length; j++)
		{
			numeroCeldas++;
		}	
	}

	alert("Numero de filas: " + numeroFilas +"\n" 
		 +"Numero de celdas: " + numeroCeldas + "\n" 
		 +"Numero de registros: " + obtenerNumeroRegistros());
}

function salarioConAjuste(a,b)
{
	var salario = Number(a);
	var ajuste = Number(b);
	var salarioAjuste = ((ajuste / 100) * salario) + salario; 
	//alert(salario + " y " + ajuste + " y " + salarioAjuste);
	return salarioAjuste;

}

function obtenerNumeroRegistros()
{
	var numeroRegistros = Number(document.getElementById("numeroRegistros").value);
	return numeroRegistros;
}

function establecerSalarioConAjuste()
{
	var tabla = obtenerTabla();
	var salario;
	var ajuste;
	var numeroRegistros = obtenerNumeroRegistros();
	var salarioTotal;

	for(filas = 0; filas <= numeroRegistros; filas++)
	{
		if(filas == 0)
		{
			continue;
		}

		for(celdas = 0; celdas < tabla.rows[filas].cells.length; celdas++)
		{
			if(celdas == 1)
			{
				ajuste = Number(tabla.rows[filas].cells[3].children[0].value);
				salario = Number(tabla.rows[filas].cells[4].children[0].value);

				salarioTotal = salarioConAjuste(salario, ajuste);
				
				document.getElementById("sa" + filas).value = salarioTotal;
			}
			
		}
	}
}


