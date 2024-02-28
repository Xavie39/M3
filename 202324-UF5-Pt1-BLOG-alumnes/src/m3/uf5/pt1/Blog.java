package m3.uf5.pt1;

import java.util.Date;
import java.util.HashSet;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

public class Blog {
	public static final int AMPLE_LEFT = 15;
	public static final int GAP = 3;
	public static final int AMPLE_CONTENT = 60;
	public HashSet<Usuari> usuaris = new HashSet<Usuari>();
	public TreeSet<Entrada> entrades = new TreeSet<Entrada>();

	public Blog() {
		super();
	}

	public HashSet<Usuari> getUsuaris() {
		return usuaris;
	}

	public void setUsuaris(HashSet<Usuari> usuaris) {
		this.usuaris = usuaris;
	}

	public TreeSet<Entrada> getEntrades() {
		return entrades;
	}

	public void setEntrades(TreeSet<Entrada> entrades) {
		this.entrades = entrades;
	}

	private Entrada cercarEntradaPerDataTitol(Date data, String titol) {
		for (Entrada entrada : entrades) {
			Date endate = entrada.getData();
			if (endate.equals(data) && entrada.getText().equals(titol)) {
				System.out.println("hola");
				return entrada;
			}
		}
		return null;
	}

	private Usuari cercarUsuariPerMail(String mail) {
		for (Usuari usuari : usuaris) {
			if (usuari.getMail().equals(mail)) {
				return usuari;
			}

		}
		return null;
	}

	private Usuari cercarUsuariPerNick(String nick) {
		for (Usuari usuari : usuaris) {
			if (usuari.getNick().equals(nick)) {
				return usuari;
			}

		}
		return null;
	}

	public void nouUsuari(String nick, String mail) throws Exception {
		try {
			for (Usuari usuari : usuaris) {
				if (usuari.getNick().equals(nick) || usuari.getMail().equals(mail)) {
					throw new Exception("Dos usuaris amb el mateix «mail» o «nick».");
				}
			}
			usuaris.add(new Usuari(mail, nick));
		} catch (NullPointerException e) {
			System.out.println("Usuaris amb «mail» o «nick» sense valor o nuls.");
		}
	}

	public void afegirEntrada(String mail, String text, String titol) throws Exception {
		for (Entrada entrada : entrades) {
			if (entrada.getTitol().equals(titol)) {
				throw new Exception("Dues entrades del mateix dia amb el mateix «titol»");
			}
		}
		Usuari user = cercarUsuariPerMail(mail);
		entrades.add(new Entrada(user, titol, text));
	}

	public void comentarEntrada(String mail, Date data, String titol, String text, int valoracio) throws Exception {
		try {
			Entrada en = cercarEntradaPerDataTitol(data, titol);
			Usuari user = this.cercarUsuariPerMail(mail);
			for (Entrada entrada : entrades) {
				if (entrada.equals(en)) {
					entrada.afegirComentari(user, text, valoracio);
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Comentari nul");
		}
	}

	public String imprimirEntrada(Date data, String titol) throws Exception {
		Entrada en = cercarEntradaPerDataTitol(data, titol);
		for (Entrada entrada : entrades) {
			if (entrada.equals(en)) {
				entrada.imprimirPublicacio("", AMPLE_CONTENT);
			}
		}
		throw new Exception("Comentar o imprimir una entrada inexistent.");
	}

	public String imprimirBlog() {
		// Calcular el número de usuarios y el número de entradas en el blog
		int numUsuarios = usuaris.size();
		int numEntradas = entrades.size();

		// Crear la cabecera centrada con el número de usuarios y el número de entradas
		String header = StringUtils.center("Usuaris: " + numUsuarios + " ^ Entrades: " + numEntradas, 100, '^') + "\n";

		// Concatenar todas las entradas del blog
		StringBuilder sb = new StringBuilder();
		for (Entrada entrada : entrades) {
			sb.append(entrada.imprimirPublicacio("", AMPLE_CONTENT));
			sb.append("\n");
		}

		// Concatenar la cabecera y todas las entradas
		return header + sb.toString();
	}

	public void desarDadesBlog(String blogFilename) {
		// TODO Auto-generated method stub

	}
}
