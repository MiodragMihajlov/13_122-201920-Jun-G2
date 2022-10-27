package model;

import java.util.Set;
import java.util.TreeSet;

public class IzvodjacAlbumi {
	
	private Izvodjac izvodjac;
	private Set<String> albumi;
	
	public IzvodjacAlbumi(Izvodjac izvodjac) {
		this.izvodjac = izvodjac;
		this.albumi = new TreeSet<String>();
	}
	
	public IzvodjacAlbumi(Izvodjac izvodjac, String album) {
		this(izvodjac);
		this.albumi.add(album);
	}

	public Izvodjac getIzvodjac() {
		return izvodjac;
	}

	public Set<String> getAlbumi() {
		return albumi;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof IzvodjacAlbumi))
			return false;
		
		IzvodjacAlbumi ia = (IzvodjacAlbumi) obj;
		
		return izvodjac.equals(ia.getIzvodjac());
	}
	
	@Override
	public String toString() {
		return izvodjac + "\n\t" + albumi;
	}
	
	
	
	

}
