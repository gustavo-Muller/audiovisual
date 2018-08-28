package br.com.audiovisual.enuns;

public enum TipoUsuario {
	
		PROFESSOR("Professor", "PROFESSOR"),
		ADIMINISTRADOR("Adiministrdor", "ADMINISTRADOR");
	
	private final String nome;
	private final String valor;
	
	private TipoUsuario(String nome, String valor) {
	      this.nome = nome;
	      this.valor = valor;
	   }
	
	public static String[] nomes() {
	     TipoUsuario[] tipoUsuario = TipoUsuario.values();
	      int tamanho = tipoUsuario.length;
	      String[] nomes = new String[tamanho];
	      for (int i = 0; i < tamanho; i++) {
	         nomes[i] =  tipoUsuario[i].getNome();
	      }
	      return nomes;
	   }
	
	private TipoUsuario(String nome) {
		this(nome, "");
	}
	

	public String getNome() {
		return nome;
	}
	
	public String nome() {
		return getNome();
	}

	public String getValor() {
		return valor;
	}
	
	public String valor() {
		return getValor();
	}

}
