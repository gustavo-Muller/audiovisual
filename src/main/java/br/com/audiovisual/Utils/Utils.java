package br.com.audiovisual.Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Utils {

	private static void alertBase(Alert alert, String content) {
		if (alert != null) {
			alert.setHeaderText(null);
			alert.setContentText(content);
			alert.showAndWait();
		}
		return;
	}
	
	public static void showMessageError(String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error!");
		alert.setHeaderText(null);
		alert.setContentText(content);

		alert.showAndWait();
	}

	public static void showErrorMessage(AlertType alertType, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error!");
		alertBase(alert, content);
	}

	private static void showInformationMessage(AlertType alertType, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Atenção!");
		alertBase(alert, content);
	}

	private static void showConfirmationMessage(AlertType alertType, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Confirmado!");
		alertBase(alert, content);
	}

	public static void showMessage(AlertType alertType, String content) {
		if (alertType.equals(AlertType.INFORMATION)) {
			showInformationMessage(alertType, content);
		} else if (alertType.equals(AlertType.CONFIRMATION)) {
			showConfirmationMessage(alertType, content);
		} else if (alertType.equals(AlertType.ERROR)) {
			showErrorMessage(alertType, content);
		}
	}

}
