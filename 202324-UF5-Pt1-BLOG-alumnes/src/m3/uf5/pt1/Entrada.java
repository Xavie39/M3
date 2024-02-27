package m3.uf5.pt1;

import java.util.Date;
import java.util.Deque;

public class Entrada extends Publicacio implements Comparable<Entrada> {
	public static final String SEPARADOR = "I";
	public static final String NOT_PROVIDED = "NA";
	private String titol;
	// Pila
	private Deque<Comentari> comentaris;

	public Entrada(Usuari usuari, String titol, String text) {
		super(usuari, text);
		this.titol = titol;
	}

	@Override
	public String imprimirPublicacio(String ident, int width) {
		// TODO Auto-generated method stub
		return null;
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
}
