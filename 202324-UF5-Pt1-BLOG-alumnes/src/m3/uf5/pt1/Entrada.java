package m3.uf5.pt1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

public class Entrada extends Publicacio implements Comparable<Entrada> {
	public static final String SEPARADOR = "I";
	public static final String NOT_PROVIDED = "NA";
	private String titol;
	// Pila
	private Deque<Comentari> comentaris = new LinkedList<>();

	public Entrada(Usuari usuari, String titol, String text) {
		super(usuari, text);
		this.titol = titol;
	}

	@Override
	public String imprimirPublicacio(String ident, int width) {
	    String[] meses = {"ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"};
	    Calendar calendar = Calendar.getInstance();
	    int mesActual = calendar.get(Calendar.MONTH);
	    String nombreMesActual = meses[mesActual];
	    int añoActual = calendar.get(Calendar.YEAR);

	    // Formatear la fecha con el nombre del mes actual y el año actual
	    SimpleDateFormat sdf = new SimpleDateFormat("'" + nombreMesActual + "' yyyy");
	    String fechaFormateada = sdf.format(calendar.getTime());
	    
	    String pepe;
		String res=fechaFormateada + StringUtils.leftPad("|", 4) + StringUtils.leftPad(" " ,15) + this.text + System.lineSeparator() + this.usuari.getNick() + " lv:" + this.usuari.nivellUsuari() + StringUtils.leftPad("|", 2) + 
	    		 StringUtils.leftPad(" " ,2) +this.titol +System.lineSeparator() + pepe +
	    		"Mitja : " + this.valoracioMitjaEntrada() + StringUtils.leftPad("|", 6) + System.lineSeparator(); 
	   for(Entry<Integer,String> entry :Comentari.)) {
		   pepe+="";
	   }
	    return res;
	}

	public String getTitol() {
		return titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public Deque<Comentari> getComentaris() {
		return comentaris;
	}

	public void setComentaris(Deque<Comentari> comentaris) {
		this.comentaris = comentaris;
	}

	@Override
	public int compareTo(Entrada o) {
		Date thisDate = this.getData();
		Date otherDate = o.getData();

		if (thisDate == null || otherDate == null) {
			return 0;
		}

		int compareDate = thisDate.compareTo(otherDate);
		if (compareDate != 0) {
			return compareDate;
		}

		// Si las fechas son iguales, compara por título
		return this.titol.compareTo(o.titol);
	}

	public void afegirComentari(Usuari usuari, String text, int valoracio) {
		comentaris.add(new Comentari(usuari, text, valoracio));
	}

	public int totalValoracionsPerValor(int valor) {
		int suma = 0;
		for (Comentari comentari : comentaris) {
			if (comentari.getValoracio() == valor) {
				suma++;
			}
		}
		if (suma != 0) {
			return suma;
		}
		return 0;
	}

	public String valoracioMitjaEntrada() {
		float suma = 0;

		for (Comentari comentari : comentaris) {
			suma += comentari.getValoracio();
		}

		if (suma != 0) {
			return String.format("%.1f", suma);
		}
		return NOT_PROVIDED;
	}
	
	private int getTotalValoracions() {
        int total = 0;
        for (Comentari comentari : comentaris) {
            total += comentari.getValoracio();
        }
        return total;
    }
}
