package controller;

import java.util.Collections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Baza;
import view.MainStage;


public class KupiKontroler implements EventHandler<ActionEvent> {

	private MainStage view;
	private Baza baza;
	
	public KupiKontroler(MainStage view) {
		this.view = view;
		this.baza = Baza.getInstace();
	}
	
	@Override
	public void handle(ActionEvent event) {
		
		baza.getKolekcija().addAll(view.getLvArtikli().getItems());
		
		Collections.sort(baza.getKolekcija());
		
		baza.ispisKolekcijeUFajl();
		
		
		
	}

}
