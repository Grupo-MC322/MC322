package com.poo.jogo2048;

import com.poo.jogo2048.Telas.TelaAbstrata;
import com.poo.jogo2048.Telas.TelaConfiguracoes;
import com.poo.jogo2048.Telas.TelaInicial;
import com.poo.jogo2048.Telas.TelaInstrucoes;

public enum ScreenEnum {
	
	TELA_INICIAL {
		public TelaAbstrata getScreen(Object... params) {
			return new TelaInicial();
		}
	},
	
	CONFIGURACOES {
		public TelaAbstrata getScreen(Object... params) {
			return new TelaConfiguracoes();
		}
	},
	
	INSTRUCOES {
		public TelaAbstrata getScreen(Object... params) {
			return new TelaInstrucoes();
		}
	};
	
	public abstract TelaAbstrata getScreen(Object... params);
}