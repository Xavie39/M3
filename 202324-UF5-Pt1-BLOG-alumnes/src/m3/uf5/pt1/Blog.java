package m3.uf5.pt1;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class Blog {
	public static final int AMPLE_LEFT = 15;
	public static final int GAP = 3;
	public static final int AMPLE_CONTENT = 60;
	public HashSet<Usuari> usuaris;
	public TreeSet<Entrada> entrades;

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
		for (Iterator<Entrada> iterator = entrades.iterator(); iterator.hasNext();) {
			Entrada entrada = iterator.next();
			if (entrada.getData().equals(data) && entrada.getTitol().equalsIgnoreCase(titol)) {
				return entrada;
			}

		}
		return null;
	}

	private Usuari cercarUsuariPerMail(String mail) {
		for (Iterator<Usuari> iterator = usuaris.iterator(); iterator.hasNext();) {
			Usuari usuari = iterator.next();
			if (usuari.getMail().equalsIgnoreCase(mail)) {
				return usuari;
			}

		}
		return null;
	}

	private Usuari cercarUsuariPerNick(String nick) {
		for (Iterator<Usuari> iterator = usuaris.iterator(); iterator.hasNext();) {
			Usuari usuari = iterator.next();
			if (usuari.getNick().equalsIgnoreCase(nick)) {
				return usuari;
			}

		}
		return null;
	}

	public void nouUsuari(String nick, String mail) throws Exception {
		try {
			for (Iterator<Usuari> iterator = usuaris.iterator(); iterator.hasNext();) {
				Usuari usuari = iterator.next();
				if (usuari.getNick().equalsIgnoreCase(nick) || usuari.getMail().equalsIgnoreCase(mail)) {
					throw new Exception("Ya existe un usuario con el mismo nombre o nick");
				}
				usuaris.add(new Usuari(nick, mail));
			}
		} catch (NullPointerException e) {
			System.out.println("Usuaris amb «mail» o «nick» sense valor o nuls.");
		}
	}

	public void afegirEntrada(String mail, String text, String titol) throws Exception {
		try {
			for (Iterator<Entrada> iterator = entrades.iterator(); iterator.hasNext();) {
				Entrada entrada = iterator.next();
				Date data = new Date();
				if (entrada.getData().equals(data) && entrada.getTitol().equalsIgnoreCase(titol)) {
					throw new Exception("Ya existe una entrada con el mismo titulo");
				}
				// TODO Revisar tema usuario
				entrades.add(new Entrada(null, titol, text));
			}
		} catch (NullPointerException e) {
			System.out.println("Entrades amb «titol» sense valor o nul.");
		}
	}

	public void comentarEntrada(String mail, Date data, String titol, String text, int valoracio) throws Exception {
		for (Iterator<Entrada> iterator = entrades.iterator(); iterator.hasNext();) {
			Entrada entrada = iterator.next();
			if (entrada.getData().equals(data) && entrada.getTitol().equalsIgnoreCase(titol)) {
				// TODO revisar usuario
				entrada.afegirComentari(null, text, valoracio);
			}
		}
		throw new Exception("Entrada no encontrada");

	}

	public String imprimirEntrada(Date data, String titol) throws Exception {
		for (Iterator<Entrada> iterator = entrades.iterator(); iterator.hasNext();) {
			Entrada entrada = iterator.next();
			if (entrada.getData().equals(data) && entrada.getTitol().equalsIgnoreCase(titol)) {
				entrada.imprimirPublicacio("", AMPLE_CONTENT);
			}
		}
		throw new Exception("Entrada no encontrada");
	}

	public String imprimirBlog() {

		return null;
	}

	public void desarDadesBlog(String blogFilename) {
		// TODO Auto-generated method stub

	}
}
