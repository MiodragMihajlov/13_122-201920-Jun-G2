package model;

public class Artikal implements Comparable<Artikal> {

	private Izvodjac izvodjac;
	private String naslov;
	private int godinaIzdanja;
	private String zanr;
	private TipNosacaZvuka tip;
	private int brojKomada;
	private int cena;
	private String kategorija;
	
	public Artikal(Izvodjac izvodjac, String naslov, int godinaIzdanja, String zanr, TipNosacaZvuka tip, int brojKomada,
			int cena, String kategorija) {
		super();
		this.izvodjac = izvodjac;
		this.naslov = naslov;
		this.godinaIzdanja = godinaIzdanja;
		this.zanr = zanr;
		this.tip = tip;
		this.brojKomada = brojKomada;
		this.cena = cena;
		this.kategorija = kategorija;
	}

	
	@Override
	public String toString() {
		return izvodjac + " ,," + naslov + "\" " + cena + "din.";
	}
	
	public String artikalZaFajl() {
		return "\t" + naslov + " - " + tip + " (" + godinaIzdanja + ")";
	}

	public Izvodjac getIzvodjac() {
		return izvodjac;
	}

	public void setIzvodjac(Izvodjac izvodjac) {
		this.izvodjac = izvodjac;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public int getGodinaIzdanja() {
		return godinaIzdanja;
	}

	public void setGodinaIzdanja(int godinaIzdanja) {
		this.godinaIzdanja = godinaIzdanja;
	}

	public TipNosacaZvuka getTip() {
		return tip;
	}

	public void setTip(TipNosacaZvuka tip) {
		this.tip = tip;
	}

	public int getBrojKomada() {
		return brojKomada;
	}

	public void setBrojKomada(int brojKomada) {
		this.brojKomada = brojKomada;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public String getKategorija() {
		return kategorija;
	}

	public void setKategorija(String kategorija) {
		this.kategorija = kategorija;
	}

	public String getZanr() {
		return zanr;
	}

	public void setZanr(String zanr) {
		this.zanr = zanr;
	}

	@Override
	public int compareTo(Artikal o) {
		int izvodjaci = izvodjac.compareTo(o.izvodjac);
		
		if(izvodjaci == 0) {
			//Isti izvodjac je u pitanju, pa nastavljamo poredjenje po nazivu
			
			return naslov.compareTo(o.naslov);
			
		} else 
			return izvodjaci;

		
	}
	
	
	
}
