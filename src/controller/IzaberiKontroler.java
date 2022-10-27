package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Artikal;
import view.MainStage;

public class IzaberiKontroler implements EventHandler<ActionEvent>{

	private MainStage view;
	
	public IzaberiKontroler(MainStage view) {
		this.view = view;
	}
	
	@Override
	public void handle(ActionEvent event) {

		ObservableList<Artikal> selektovani = view.getTvArtikli().getSelectionModel().getSelectedItems();
		
		view.getLvArtikli().getItems().clear();
		view.getLvArtikli().getItems().addAll(selektovani);
		
	
		
		int ukupnaCena = 0;
		for(Artikal a : selektovani)
			ukupnaCena += a.getCena();
		
		
		view.getTfUkupno().setText(String.valueOf(ukupnaCena));
		
	}

}
