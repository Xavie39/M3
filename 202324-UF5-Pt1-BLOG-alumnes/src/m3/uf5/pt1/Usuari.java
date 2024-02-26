package m3.uf5.pt1;

import java.util.Objects;
import java.util.Queue;

public class Usuari {
	public int JUNIOR_LIMIT = 2;
	public int SENIOR_LIMIT = 5;
	private String nick;
	private String mail;
	// Cola
	public Queue<Publicacio> publicacions;

	public Usuari(String nick, String mail) {
		super();
		this.nick = nick;
		this.mail = mail;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Queue<Publicacio> getPublicacions() {
		return publicacions;
	}

	public void setPublicacions(Queue<Publicacio> publicacions) {
		this.publicacions = publicacions;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mail);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Usuari other = (Usuari) obj;
		return Objects.equals(mail, other.mail);
	}

	public void afegirPublicacio(Publicacio pub) {
		if (pub != null) {
			publicacions.add(pub);
		}
	}

	public String nivellUsuari() {
		if (publicacions.size() <= 2) {
			return "Junior";
		}
		if (publicacions.size() > 2 && publicacions.size() <= 5) {
			return "Senior";
		}
		return "Master";
	}

}