package m3.uf5.pt1;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

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
	        StringBuilder sb = new StringBuilder();

	        String firstColumn = StringUtils.repeat(" ", IDENT_COMMENT);

	        String secondColumn = ident + StringUtils.repeat(" ", width - this.text.length() - IDENT_COMMENT) + this.text;

	        
	        sb.append(firstColumn);
	        sb.append("| ");
	        sb.append(secondColumn);
	        sb.append("\n");

	        String[] wrappedLines = WordUtils.wrap(this.text, width - IDENT_COMMENT).split("\\r?\\n");
	        for (int i = 1; i < wrappedLines.length; i++) {
	            sb.append(StringUtils.repeat(" ", IDENT_INC * i)); 
	            sb.append(firstColumn);
	            sb.append("| "); 
	            sb.append(wrappedLines[i]);
	            sb.append("\n");
	        }

	        return sb.toString();
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
	
	 private String getValoracionMitjana() {
	        return String.valueOf(valoracio) + "-Stars";
	    }
}
