package view;

import controller.FiltrirajKontroler;
import controller.IzaberiKontroler;
import controller.KolekcijaKontroler;
import controller.KupiKontroler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Artikal;
import model.Baza;
import model.Izvodjac;
import model.TipNosacaZvuka;

public class MainStage extends Stage {

	private static MainStage instance = null;
	
	private Scene scene;
	private VBox root = new VBox(20);
	
	//ELEMENT 1
	private HBox e1 = new HBox(25);
	private ComboBox<Izvodjac> cmbIzvodjac = new ComboBox<>();
	private RadioButton rbVinyl = new RadioButton("Vinyl");
	private RadioButton rbCd = new RadioButton("CD");
	private RadioButton rbOba = new RadioButton("Vinyl i CD");
	
	//ELEMENT 2
	private HBox e2 = new HBox(25);
	private ComboBox<String> cmbKriterijum = new ComboBox<String>();
	private TextField tfCena = new TextField();
	private Button btnPrikazi = new Button("Show All");
	private Button btnFiltriraj = new Button("Filter");
	
	//ELEMENT 3
	private TableView<Artikal> tvArtikli = new TableView<>();
	
	//ELEMENT 4
	private Button btnIzaber = new Button("Select");
	
	//ELEMENT 5
	private HBox e5 = new HBox(20);
	private ListView<Artikal> lvArtikli = new ListView<Artikal>();
	private TextField tfUkupno = new TextField();
	private Button btnKupi = new Button("Buy");
	private Button btnKolekcija = new Button("My Collection");

	
 	private MainStage() {
		
		root.setAlignment(Pos.CENTER);
		
		createView();
		
		actions();
		
		scene = new Scene(root, 800, 900);
		
		setScene(scene);
		setTitle("Store Application");
		show();
	}
	
 	
	private void actions() {
		btnPrikazi.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				tfCena.clear();
				tvArtikli.getItems().clear();
				tvArtikli.getItems().addAll(Baza.getInstace().getArtikli());
			}
		});
		
		btnFiltriraj.setOnAction(new FiltrirajKontroler(this));
		
		btnIzaber.setOnAction(new IzaberiKontroler(this));
		
		btnKupi.setOnAction(new KupiKontroler(this));
		
		btnKolekcija.setOnAction(new KolekcijaKontroler());
	}

	
	private void createView() {
		
		createElement1();
		root.getChildren().add(e1);
		
		createElement2();
		root.getChildren().add(e2);
		
		createElement3();
		root.getChildren().add(tvArtikli);
		
		root.getChildren().add(btnIzaber);
		
		createElement5();
		root.getChildren().add(e5);
		
	}


	private void createElement1() {
		
		e1.setAlignment(Pos.CENTER);
		
		cmbIzvodjac.getItems().addAll(Baza.getInstace().getIzvodjaci());
		if(Baza.getInstace().getIzvodjaci().size() != 0)
			cmbIzvodjac.setValue(cmbIzvodjac.getItems().get(0));
		
		e1.getChildren().add(new Label("Performer"));
		e1.getChildren().add(cmbIzvodjac);
		e1.getChildren().add(rbVinyl);
		e1.getChildren().add(rbCd);
		e1.getChildren().add(rbOba);
		
		ToggleGroup tg = new ToggleGroup();
		rbVinyl.setToggleGroup(tg);
		rbCd.setToggleGroup(tg);
		rbOba.setToggleGroup(tg);
		
	}
	
	private void createElement2() {
		e2.setAlignment(Pos.CENTER);
		
		cmbKriterijum.getItems().addAll(">", "<", "=");
		cmbKriterijum.setValue(cmbKriterijum.getItems().get(0));
		
		e2.getChildren().add(new Label("Price"));
		e2.getChildren().add(cmbKriterijum);
		e2.getChildren().add(tfCena);
		e2.getChildren().add(btnPrikazi);
		e2.getChildren().add(btnFiltriraj);
	}

	private void createElement3() {
		TableColumn<Artikal, Izvodjac> tcIzvodjac = new TableColumn<Artikal, Izvodjac>("Performer");
		tcIzvodjac.setCellValueFactory(new PropertyValueFactory<>("izvodjac"));
		tvArtikli.getColumns().add(tcIzvodjac);
		
		TableColumn<Artikal, String> tcNaziv = new TableColumn<Artikal, String>("Title");
		tcNaziv.setCellValueFactory(new PropertyValueFactory<>("naslov"));
		tvArtikli.getColumns().add(tcNaziv);
		
		TableColumn<Artikal, Integer> tcGodina = new TableColumn<Artikal, Integer>("Year");
		tcGodina.setCellValueFactory(new PropertyValueFactory<>("godinaIzdanja"));
		tvArtikli.getColumns().add(tcGodina);
		
		TableColumn<Artikal, String> tcZanr = new TableColumn<Artikal, String>("Genre");
		tcZanr.setCellValueFactory(new PropertyValueFactory<>("zanr"));
		tvArtikli.getColumns().add(tcZanr);
		
		TableColumn<Artikal, TipNosacaZvuka> tcTip = new TableColumn<Artikal, TipNosacaZvuka>("Type");
		tcTip.setCellValueFactory(new PropertyValueFactory<>("tip"));
		tvArtikli.getColumns().add(tcTip);
		
		TableColumn<Artikal, Integer> tcKomad = new TableColumn<Artikal, Integer>("Piece");
		tcKomad.setCellValueFactory(new PropertyValueFactory<>("brojKomada"));
		tvArtikli.getColumns().add(tcKomad);
		
		TableColumn<Artikal, Integer> tcCena = new TableColumn<Artikal, Integer>("Price");
		tcCena.setCellValueFactory(new PropertyValueFactory<>("cena"));
		tvArtikli.getColumns().add(tcCena);
		
		TableColumn<Artikal, String> tcKategorija = new TableColumn<Artikal, String>("Category");
		tcKategorija.setCellValueFactory(new PropertyValueFactory<>("kategorija"));
		tvArtikli.getColumns().add(tcKategorija);
		
		tvArtikli.getItems().addAll(Baza.getInstace().getArtikli());
		
		tvArtikli.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		
	}

	private void createElement5() {
		e5.setAlignment(Pos.CENTER);
		
		e5.getChildren().add(lvArtikli);
		
		
		GridPane gridPane = new GridPane();
	
		gridPane.setHgap(15);
		gridPane.setVgap(25);
		gridPane.setAlignment(Pos.CENTER);
		
		gridPane.add(new Label("Total bill : "), 0, 0);
		gridPane.add(tfUkupno, 1, 0);
		
		gridPane.add(btnKupi, 0, 1);
		gridPane.add(btnKolekcija, 1, 1);
		
		e5.getChildren().add(gridPane);
		
	}


	public static MainStage getInstance() {
		if(instance == null)
			instance = new MainStage();
		
		return instance;
	}

	public ComboBox<Izvodjac> getCmbIzvodjac() {
		return cmbIzvodjac;
	}

	public RadioButton getRbVinyl() {
		return rbVinyl;
	}

	public RadioButton getRbCd() {
		return rbCd;
	}

	public RadioButton getRbOba() {
		return rbOba;
	}

	public ComboBox<String> getCmbKriterijum() {
		return cmbKriterijum;
	}

	public TextField getTfCena() {
		return tfCena;
	}

	public TableView<Artikal> getTvArtikli() {
		return tvArtikli;
	}

	public ListView<Artikal> getLvArtikli() {
		return lvArtikli;
	}

	public TextField getTfUkupno() {
		return tfUkupno;
	}
	
	

}
