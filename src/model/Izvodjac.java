package model;

public class Izvodjac implements Comparable<Izvodjac>{

	private String naziv;
	
	public Izvodjac(String naziv) {
		this.naziv = naziv;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Izvodjac))
			return false;
		
		Izvodjac i = (Izvodjac) obj;
		
		return naziv.equals(i.naziv);
	}
	

	@Override
	public int compareTo(Izvodjac o) {
		return naziv.compareTo(o.naziv);
	}
	
	@Override
	public String toString() {
		return naziv;
	}
	
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	
}
