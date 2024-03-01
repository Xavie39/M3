package m3.uf5.pt1;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
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

	public Entrada() {
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

	@Override
	public String imprimirPublicacio(int ident, int width) {
		String[] meses = { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE",
				"OCTUBRE", "NOVIEMBRE", "DICIEMBRE" };
		Calendar calendar = Calendar.getInstance();
		int mesActual = calendar.get(Calendar.MONTH);
		String nombreMesActual = meses[mesActual];

		// Formatear la fecha con el nombre del mes actual y el año actual
		SimpleDateFormat sdf = new SimpleDateFormat("'" + nombreMesActual + "' yyyy");
		String fechaFormateada = sdf.format(calendar.getTime());
		StringBuilder comentarisst = new StringBuilder();

		comentaris.stream().sorted(Comparator.comparingInt(Comentari::getValoracio)).forEach(comen -> {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			String fechaFormateadacomen = formatoFecha.format(comen.getData());

			// Envuelve el texto del comentario y agrega un espacio al principio de cada
			// línea
			String textoEnvuelto = WordUtils.wrap(comen.getText(), 52, "\n", true);
			String[] lineasComentario = textoEnvuelto.split("\\r?\\n");

			// Agrega el nombre de usuario, fecha y valoración del comentario
			comentarisst.append(comen.usuari.getNick()).append(".- ").append(lineasComentario[0])
					.append(System.lineSeparator());

			// Agrega las líneas restantes del comentario con la barra al principio de cada
			// línea
			for (int i = 1; i < lineasComentario.length; i++) {
				comentarisst.append(StringUtils.leftPad(SEPARADOR, 16)); // Agrega la barra al principio de la línea
				comentarisst.append(StringUtils.leftPad(" ", 28)); // Espacio después del prefijo "|"
				comentarisst.append(lineasComentario[i]).append(System.lineSeparator());
			}

			// Agrega la fecha y valoración del comentario
			comentarisst.append(StringUtils.leftPad(SEPARADOR, 16));
			comentarisst.append(StringUtils.leftPad(" ", 28)).append(fechaFormateadacomen).append(", valoracio: ");
			comentarisst.append(comen.getValoracio()).append("-Stars").append(System.lineSeparator());
			comentarisst.append(StringUtils.leftPad(SEPARADOR, 16));
			comentarisst.append(StringUtils.leftPad(" ", 2)).append(System.lineSeparator());
			comentarisst.append(StringUtils.leftPad(SEPARADOR, 16));
			comentarisst.append(StringUtils.leftPad(" ", 16));
		});

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
					.append(StringUtils.leftPad(SEPARADOR, 6)).append("\n");
		}

		// Envolver el texto de la entrada para ajustarse al ancho especificado
		String textoEnvuelto = WordUtils.wrap(this.text, 120, "\n", true);

		String[] lineas = StringUtils.split(textoEnvuelto, "\n");

		// Tratar la primera línea por separado sin agregar un espacio al principio
		lineas[0] = StringUtils.leftPad(SEPARADOR, 2) + StringUtils.leftPad(" ", 6) + lineas[0] + " ";

		// Para las líneas restantes, agregar un espacio al principio y una barra
		for (int i = 1; i < lineas.length; i++) {
			lineas[i] = StringUtils.leftPad(SEPARADOR, 16) + StringUtils.leftPad(" ", 5) + lineas[i] + " ";
		}

		// Unir las líneas con un salto de línea
		String textoConEspacios = StringUtils.join(lineas, "\n");

		String res = fechaFormateada + StringUtils.leftPad(SEPARADOR, 6) + StringUtils.leftPad(" ", 30)
				+ this.titol.toUpperCase() + System.lineSeparator() + this.usuari.getNick() + " lv:"
				+ this.usuari.nivellUsuari() + textoConEspacios + System.lineSeparator()
				+ valoracionesPorEstrellaStr.toString() + "Mitja : " + this.valoracioMitjaEntrada()
				+ StringUtils.leftPad(SEPARADOR, 5) + System.lineSeparator() + StringUtils.leftPad(" ", 14)
				+ StringUtils.leftPad(SEPARADOR, 2) + StringUtils.leftPad(" ", 16) + comentarisst.toString()
				+ System.lineSeparator();

		return res;
	}
}
