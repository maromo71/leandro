$(document).ready(function(){
	$.get( "http://localhost:8080/biblio-restws/biblioteca/livros/", function(data) {
		console.log('encontrei ' + data.length + ' registros');
		for(linha=0; linha < data.length; linha++)
		{
			console.log('');
			console.log('REGISTROS DA LINHA' + linha);
					
		}		
	});
});

