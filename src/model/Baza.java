package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
 
public class Baza {
	
	private static Baza instance = null;
	
	private List<Artikal> artikli;
	private Set<Izvodjac> izvodjaci;
	
	private final String podaciFajl = "katalog.txt";
	
	private List<Artikal> kolekcija;
	private final String kolekcijaFajl = "kolekcija.txt";
	
	private List<IzvodjacAlbumi> kolekcijaPoIzvodjacima;
	
	
	
	
	
	private Baza() {
		this.artikli = new ArrayList<Artikal>();
		this.izvodjaci = new TreeSet<Izvodjac>();
		
		ucitavanjeFajla();
		
		this.kolekcija = new ArrayList<Artikal>();
		
		this.kolekcijaPoIzvodjacima = new ArrayList<IzvodjacAlbumi>();
		

	}
	
	private void ucitavanjeFajla() {
		
		this.artikli.clear();
		this.izvodjaci.clear();

		try {
			
			FileReader fr = new FileReader(new File(podaciFajl));
			BufferedReader br = new BufferedReader(fr);
			
			String linija;
			String zanr = "";
			
			while((linija = br.readLine()) != null) {
				
				String []delovi = linija.split(",");
				
				if(delovi.length == 1) {
					zanr = delovi[0];
					continue;
				}
				
				if(delovi.length != 6) {
					System.err.println("Nije moguce napraviti artikal jer nema dovoljno delova!\n\t" + linija);
					continue;
				}
				
				Izvodjac izvodjac = new Izvodjac(delovi[0]);
				String naslov = delovi[1];
				
				String stringGodinaIzdanja = delovi[2];
				int godinaIzdanja = Integer.parseInt(stringGodinaIzdanja);
				
			
				String []poddelovi = delovi[3].split("-");
				TipNosacaZvuka tip = TipNosacaZvuka.valueOf(poddelovi[0]);
				int brojKomada = Integer.parseInt(poddelovi[1]);
				
				int cena = Integer.parseInt(delovi[4]);
				String kategorija = delovi[5];
				
				Artikal a = new Artikal(izvodjac, naslov, godinaIzdanja, zanr, tip, brojKomada, cena, kategorija);
				
				this.artikli.add(a);
				this.izvodjaci.add(izvodjac);
				
			
				
			}
			
			
			br.close();
			fr.close();
			
		} catch (Exception e) {
			
			if(e instanceof FileNotFoundException)
				System.err.println("Greska prilikom otvaranja fajla ::: " + podaciFajl);
			else if(e instanceof NumberFormatException)
				System.err.println("Greska prilikom parsiranja godine izdanja.");
				
			
			e.printStackTrace();
		}
		
		
		System.out.println(artikli);
	}

	public void ispisKolekcijeUFajl() {
		
		try {
			
			FileWriter fw = new FileWriter(new File(kolekcijaFajl));
			PrintWriter pw = new PrintWriter(fw);
			
			Map<Izvodjac, List<Artikal>> mapa = new TreeMap<Izvodjac, List<Artikal>>();
			
			for(Artikal a : kolekcija) {
				
				mapa.putIfAbsent(a.getIzvodjac(), new ArrayList<Artikal>());
				
			
				
				mapa.get(a.getIzvodjac()).add(a);
				
			}
			
	
			
			int vrednostKolekcije = 0;
			
			for(Izvodjac i : mapa.keySet()) {
				
				pw.println(i.getNaziv());
				
			
				for(Artikal a : mapa.get(i)) {
					
					pw.println(a.artikalZaFajl());
					
					vrednostKolekcije += a.getCena();
				}
				
			}
			
			pw.println("\nUkupna vrednost kolekcije : " + vrednostKolekcije + " dinara");
			
			pw.close();
			fw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void izvodjacAlbumi() {
		kolekcijaPoIzvodjacima.clear();
		
		for(Artikal a : kolekcija) {
			
			IzvodjacAlbumi iz = new IzvodjacAlbumi(a.getIzvodjac());
			
			if(kolekcijaPoIzvodjacima.contains(iz)) {
				int index = kolekcijaPoIzvodjacima.indexOf(iz);
				kolekcijaPoIzvodjacima.get(index).getAlbumi().add(a.getNaslov());
			} else {
				iz.getAlbumi().add(a.getNaslov());
				kolekcijaPoIzvodjacima.add(iz);
			}
			
		}
	}
	
	public List<IzvodjacAlbumi> getKolekcijaPoIzvodjacima() {
		return kolekcijaPoIzvodjacima;
	}
	
	
	public static Baza getInstace() {
		if(instance == null)
			instance = new Baza();
		
		return instance;
	}

	public List<Artikal> getArtikli() {
		return artikli;
	}
	
	public Set<Izvodjac> getIzvodjaci() {
		return izvodjaci;
	}

	public List<Artikal> getKolekcija() {
		return kolekcija;
	}
}
