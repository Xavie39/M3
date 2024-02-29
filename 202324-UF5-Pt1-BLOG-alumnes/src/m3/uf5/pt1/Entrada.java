package m3.uf5.pt1;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

public class Entrada extends Publicacio implements Comparable<Entrada>, Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SEPARADOR = "|";
	public static final String NOT_PROVIDED = "NA";
	private String titol;
	// Pila
	private Deque<Comentari> comentaris = new LinkedList<>();

	public Entrada(Usuari usuari, String text) {
		super(usuari, text);
	}

	public Entrada(Usuari usuari, String text, String titol) {
		super(usuari, text);
		this.titol = titol;
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
		int total = 0;

		for (Comentari comentari : comentaris) {
			suma += comentari.getValoracio();
			total++;
		}

		float res = suma / total;

		if (res != 0) {
			return String.format("%.1f", res);
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

	@Override
	public String imprimirPublicacio(String ident, int width) {
		String[] meses = { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE",
				"OCTUBRE", "NOVIEMBRE", "DICIEMBRE" };
		Calendar calendar = Calendar.getInstance();
		int mesActual = calendar.get(Calendar.MONTH);
		String nombreMesActual = meses[mesActual];
		int añoActual = calendar.get(Calendar.YEAR);

		// Formatear la fecha con el nombre del mes actual y el año actual
		SimpleDateFormat sdf = new SimpleDateFormat("'" + nombreMesActual + "' yyyy");
		String fechaFormateada = sdf.format(calendar.getTime());
		StringBuilder comentarisst = new StringBuilder();

		for (Comentari comen : comentaris) {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			String fechaFormateadacomen = formatoFecha.format(comen.getData());
			comentarisst.append(comen.usuari.getNick()).append(".- ").append(comen.getText())
					.append(System.lineSeparator()).append(StringUtils.leftPad("|", 16))
					.append(StringUtils.leftPad(" ", 54)).append(fechaFormateadacomen).append(", valoracio: ")
					.append(comen.getValoracio()).append("-Stars\n").append(StringUtils.leftPad("|", 16))
					.append(StringUtils.leftPad(" ", 2)).append(System.lineSeparator())
					.append(StringUtils.leftPad("|", 16)).append(StringUtils.leftPad(" ", 16));
		}

		int[] totalValoracionesPorEstrella = new int[4];

		// Calcular el total de valoraciones para cada estrella
		for (Comentari comen : comentaris) {
			int valoracion = comen.getValoracio();
			if (valoracion >= 0 && valoracion <= 3) {
				totalValoracionesPorEstrella[valoracion]++;
			}
		}

		// Construir la cadena de valoraciones por estrella
		StringBuilder valoracionesPorEstrellaStr = new StringBuilder();
		for (int i = 0; i < totalValoracionesPorEstrella.length; i++) {
			valoracionesPorEstrellaStr.append(i).append("-Stars: ").append(totalValoracionesPorEstrella[i])
					.append(StringUtils.leftPad("|", 6)).append("\n");
		}

		// Envolver el texto de la entrada para ajustarse al ancho especificado
		String textoEnvuelto = WordUtils.wrap(this.text, 120, "\n", true);

		String[] lineas = StringUtils.split(textoEnvuelto, "\n");
		for (int i = 0; i < lineas.length; i++) {
			lineas[i] = StringUtils.leftPad("|", 16) + StringUtils.leftPad(" ", 6) + lineas[i] + " ";
		}

		// Unir las líneas con un salto de línea
		String textoConEspacios = StringUtils.join(lineas, "\n");

		String res = fechaFormateada + StringUtils.leftPad("|", 4) + StringUtils.leftPad(" ", 30) + this.titol
				+ System.lineSeparator() + this.usuari.getNick() + " lv:" + this.usuari.nivellUsuari()
				+ textoConEspacios + System.lineSeparator() + valoracionesPorEstrellaStr.toString() + "Mitja : "
				+ this.valoracioMitjaEntrada() + StringUtils.leftPad("|", 6) + System.lineSeparator()
				+ StringUtils.leftPad(" ", 14) + StringUtils.leftPad("|", 2) + StringUtils.leftPad(" ", 2)
				+ comentarisst.toString() + System.lineSeparator();

		return res;
	}
}
