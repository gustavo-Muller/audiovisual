package br.com.audiovisual.Configure;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigureMessages {

	final static String CONF = "config";
	final static String IDIOMA = "idioma";
	final static String PAIS = "pais";
	final static String EXCECAO = "excecao";

	private static ResourceBundle initialize() {
		ResourceBundle bundle = ResourceBundle.getBundle("config");
		return bundle;
	}

	private static Locale returnLocale() {
		ResourceBundle bundle = initialize();
		Locale locale = new Locale(bundle.getString(IDIOMA), bundle.getString(PAIS));
		return locale;
	}

	private static String returnMessageException() {
		ResourceBundle bundle = initialize();
		String arquivoExcecao = bundle.getString(EXCECAO);
		return arquivoExcecao;
	}

	public static ResourceBundle returnBundleExeptionFile() {
		Locale locale = returnLocale();
		String baseName = returnMessageException();
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);

		return bundle;
	}

}
