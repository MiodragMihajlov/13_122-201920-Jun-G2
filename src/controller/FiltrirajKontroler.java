package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Artikal;
import model.Baza;
import model.Izvodjac;
import model.TipNosacaZvuka;
import view.MainStage;

public class FiltrirajKontroler implements EventHandler<ActionEvent> {

	private MainStage view;
	
	public FiltrirajKontroler(MainStage view) {
		this.view = view;
	}
	
	@Override
	public void handle(ActionEvent event) {

		Izvodjac izvodjac = view.getCmbIzvodjac().getValue();
		
		boolean vinyl = view.getRbVinyl().isSelected();
		boolean cd = view.getRbCd().isSelected();
		boolean oba = view.getRbOba().isSelected();
		
		String kriterujum = view.getCmbKriterijum().getValue();
		String stringCena = view.getTfCena().getText();
		
		// a && b = true AKKO su i a i b true		
		if(izvodjac == null || (!vinyl && !cd && !oba) || kriterujum == null || stringCena.isEmpty()) {
			System.err.println("Nisu uneti svi kriterijumi pretrage!");
			return;
		}
		
		int cena = 0;
		try {
			cena = Integer.parseInt(stringCena);
		} catch (Exception e) {
			System.err.println("U text field je dozvoljeno uneti samo brojeve!");
			view.getTfCena().clear();
			return;
		}
		
		
		
		
		List<Artikal> filtrirani = new ArrayList<Artikal>();
		
		for(Artikal a : Baza.getInstace().getArtikli()) {
			
			
			if(!a.getIzvodjac().equals(izvodjac))
				continue;
			
	
			if(cd && (a.getTip() == TipNosacaZvuka.Vinyl))
				continue;
			if(vinyl && (a.getTip() == TipNosacaZvuka.CD))
				continue;
			
		
			if(kriterujum.equals("=") && a.getCena() == cena)
				filtrirani.add(a);
			else if(kriterujum.equals(">") && a.getCena() > cena)
				filtrirani.add(a);
			else if(kriterujum.equals("<") && a.getCena() < cena)
				filtrirani.add(a);		
			
		}
		
		view.getTvArtikli().getItems().clear();
		view.getTvArtikli().getItems().addAll(filtrirani);
		
	}

}
