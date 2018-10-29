package br.com.audiovisual.Exeption;

import br.com.audiovisual.Utils.Utils;

public class DadosInvalidosExeption extends RegraDeNegocioExeption {

	private static final long serialVersionUID = 1L;
	
	public DadosInvalidosExeption() {
		super();
	}
	
	public DadosInvalidosExeption(String message) {
		Utils.showMessageError("Dados Invalidos");
		Utils.showMessageError(message);
	}
	

}
