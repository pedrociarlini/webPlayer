/**
 * 
 */
var setListApp = new Vue({
	el : '#setListApp',
	data : {
		dados : {
			musicaAtual : '--  carregando... --',
			proximaMusica : '',
			musicas : [],
			dono : "",
			chave : ""
		},
		tela : {
			adicionando : false,
			novaMusica : {
				musica : {
					nome : ''
				},
				proxima : true
			},
			musicasIniciais : ""
		}
	}
});

setListApp.post = function(url, data, callback) {
	// console.log(JSON.stringify(data));
	return jQuery.ajax({
		'type' : 'POST',
		'url' : url,
		'contentType' : 'application/json',
		'data' : JSON.stringify(data),
		'dataType' : 'json',
		'success' : callback
	});
}
setListApp.get = function(url, data, callback) {
	return jQuery.ajax({
		'type' : 'GET',
		'url' : url,
		'contentType' : 'application/json',
		'data' : JSON.stringify(data),
		'dataType' : 'json',
		'success' : callback
	});
}
setListApp.put = function(url, data, callback) {
	return jQuery.ajax({
		'type' : 'PUT',
		'url' : url,
		'contentType' : 'application/json',
		'data' : JSON.stringify(data),
		'dataType' : 'json',
		'success' : callback
	});
}

// FIM Métodos utilitários

entrarSessao = function(chave) {
	setListApp.get("services/setlist/entrarNaSessao?k=" + chave, null, null);
}

atualizarMusicas = function(novoSetList) {
	if (!novoSetList) {
		setListApp.get('services/setlist/obterAtualizado', '', function(dados) {
			setListApp.dados = dados;
		}).fail(function() {
			exibirErro("Algo deu errado ao atualizar as músicas...");
		});
	} else {
		setListApp.dados = novoSetList;
	}
}

proximaMusica = function() {
	setListApp.put('services/setlist/avancar', null, function(dados) {
		atualizarMusicas(dados);
	});
}

adicionarMusica = function() {
	setListApp.post('services/setlist/adicionarMusica', setListApp.tela.novaMusica, function(dados) {
		atualizarMusicas(dados);
		setListApp.tela.adicionando = false;
		setListApp.tela.novaMusica.musica.nome = '';
		$.notify({
			icon : "glyphicon glyphicon-ok",
			message : 'Musica adicionada! :)'
		}, {
			type : 'success'
		});
		// alert("");
	}).fail(function(msg) {
		exibirErro("Algo deu errado ao adicionar as músicas: " + msg);
	});
}
exibirQrCode = function() {
	$("#qrCodePanel").css("display", "block");
	$("#qrcodeBtn").attr("onclick", "esconderQrCode()");
}
esconderQrCode = function() {
	$("#qrCodePanel").css("display", "none");
	$("#qrcodeBtn").attr("onclick", "exibirQrCode()");
}

preparaAdd = function() {
	setListApp.tela.adicionando = true;
}

cancelaAdd = function() {
	setListApp.tela.adicionando = false;
}

tornarProxima = function(numero, nome) {
	setListApp.put("services/setlist/alterarProximaMusica", {
		"posicao" : numero,
		musica : {
			"nome" : nome
		}
	}, function(dados) {
		atualizarMusicas(dados);
	}).fail(function(msg) {
		exibirErro("Algo deu errado ao atualizar as músicas: " + msg);
	});
}


function exibirErro(msg) {
	$.notify({
		icon : "glyphicon glyphicon-warning-sign",
		message : msg
	}, {
		type : 'danger'
	});
}

iniciarSessao = function() {
	if (setListApp.dados.dono != null && setListApp.dados.dono.length > 3) {
		var musicasIniciais = setListApp.tela.musicasIniciais.split("\n");
		setListApp.post('services/setlist/iniciarSessao', {
			dono : setListApp.dados.dono,
			musicas : musicasIniciais
		}, function(dados) {
			console.log(dados);
			if (!dados) {
				exibirErro("Algo deu errado ao iniciar seu show...");
			} else {
				if (dados.erro && dados.erro.length > 0) {
					$.notify({
						icon : "glyphicon glyphicon-warning-sign",
						message : dados.erro
					}, {
						type : 'danger'
					});
				} else {
					window.location.href = 'comandante.html?k=' + dados.chave;
				}
			}
		}).fail(function(msg) {
			console.log(msg);
			exibirErro("Algo deu errado ao iniciar seu show: " + msg);
		});
	} else {
		exibirErro("Seu nome deve ter ao menos 4 caracteres ;) ");
	}
}

function getParameterByName(name, url) {
	if (!url)
		url = window.location.href;

	name = name.replace(/[\[\]]/g, "\\$&");
	var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"), results = regex.exec(url);
	if (!results)
		return null;
	if (!results[2])
		return '';
	return decodeURIComponent(results[2].replace(/\+/g, " "));
}