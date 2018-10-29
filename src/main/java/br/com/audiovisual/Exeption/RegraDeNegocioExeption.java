package br.com.audiovisual.Exeption;

import java.util.ResourceBundle;

import br.com.audiovisual.Configure.ConfigureMessages;
import br.com.audiovisual.Utils.Utils;

public class RegraDeNegocioExeption extends Exception {

	private static final long serialVersionUID = 1L;

	private ResourceBundle configure() {
		return ConfigureMessages.returnBundleExeptionFile();
	}

	public RegraDeNegocioExeption(String message) {
		super(message);
	}

	public RegraDeNegocioExeption() {
		Utils.showMessageError(getMessage());
	}

	@Override
	public String getMessage() {
		ResourceBundle resource = configure();
		String msg = resource.getString(getClass().getSimpleName());

		return msg;
	}

}
