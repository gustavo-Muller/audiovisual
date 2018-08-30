package br.com.audiovisual.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	private Connection con = null;

	public Connection getConnection() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/audiovisual", "root", "root");
			return con;
		} catch (Exception e) {
			System.out.println("Erro ao conectar no Banco! " + e.getMessage());
		}

		return con;
	}
	
	public void setClose() {
		try {
			con.close();
		}catch(Exception e) {
			System.out.println("Erro ao desconectr com o Banco! " + e.getMessage());
		}
	}

}
