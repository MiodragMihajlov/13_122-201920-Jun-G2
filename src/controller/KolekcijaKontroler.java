package controller;

import java.text.DecimalFormat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Artikal;
import model.Baza;
import model.TipNosacaZvuka;
import view.KolekcijaStage;

public class KolekcijaKontroler implements EventHandler<ActionEvent>{

	private Baza baza = Baza.getInstace();
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
	@Override
	public void handle(ActionEvent event) {

		KolekcijaStage stage = new KolekcijaStage();
		
		baza.izvodjacAlbumi();
		stage.getTabela().getItems().addAll(baza.getKolekcijaPoIzvodjacima());
		
		
		int vinyl = 0;
		int cd = 0;
		int komada = 0;
		int polovni = 0;
		int nove = 0;
		int vrednost = 0;
		
		for(Artikal a : baza.getKolekcija()) {
			if(a.getTip() == TipNosacaZvuka.Vinyl)
				vinyl++;
			else 
				cd++;
			
			if(a.getKategorija().equals("nova"))
				nove++;
			else 
				polovni++;
			
			komada += a.getBrojKomada();
			
			vrednost += a.getCena();
		}
		
		double ukupno = baza.getKolekcija().size() * 1.0;
		double procenatPolovni = (polovni / ukupno) * 100;
		double procenatNovih = (nove / ukupno) * 100;
		
		stage.getTfVinyl().setText(String.valueOf(vinyl));
		stage.getTfCd().setText(String.valueOf(cd));
		stage.getTfUkupno().setText(String.valueOf(komada));
		stage.getTfPolovne().setText(String.format("%.2f", procenatPolovni) + " %");
		stage.getTfNovi().setText(df.format(procenatNovih) + " %");
		stage.getTfVrednost().setText(vrednost + " dinara");
		
		
		stage.show();
		
	}

}
