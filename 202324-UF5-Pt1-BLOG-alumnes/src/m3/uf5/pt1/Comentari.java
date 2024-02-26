package m3.uf5.pt1;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Comentari extends Publicacio {
	public static final int IDENT_COMMENT = 5;
	public static final int IDENT_INC = 2;
	private static Map<Integer, String> valoracions = new TreeMap<>();
	private int valoracio;

	static {
		valoracions.put(0, "0-Stars");
		valoracions.put(1, "1-Star");
		valoracions.put(2, "2-Stars");
		valoracions.put(3, "3-Stars");
	}

	public Comentari(Usuari usuari, String text, int valoracio) {
		super(usuari, text);
		if (containsValoracio(valoracio)) {
			this.valoracio = valoracio;
		} else {
			throw new IllegalArgumentException("La valoració proporcionada no és vàlida.");
		}
	}

	@Override
	public String imprimirPublicacio(String ident, int width) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getValoracio() {
		return valoracio;
	}

	public void setValoracio(int valoracio) {
		this.valoracio = valoracio;
	}

	public static boolean containsValoracio(int key) {
		for (Map.Entry<Integer, String> entry : valoracions.entrySet()) {
			Integer clave = entry.getKey();

			if (clave.equals(key)) {
				return true;
			}
		}
		return false;
	}

	public static String getTextValoracio(int key) {
		for (Map.Entry<Integer, String> entry : valoracions.entrySet()) {
			Integer clave = entry.getKey();
			String val = entry.getValue();

			if (clave.equals(key)) {
				return val;
			}
		}
		return null;
	}

	public static Set<Integer> getValoracions() {
		return valoracions.keySet();
	}
}
