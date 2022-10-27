package view;

import java.util.Set;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Izvodjac;
import model.IzvodjacAlbumi;

public class KolekcijaStage extends Stage {

	private Scene scene;
	
	private TableView<IzvodjacAlbumi> tabela = new TableView<IzvodjacAlbumi>();
	
	private GridPane grid = new GridPane();
	private TextField tfVinyl = new TextField();
	private TextField tfCd = new TextField();
	private TextField tfUkupno = new TextField();
	private TextField tfPolovne = new TextField();
	private TextField tfNovi = new TextField();
	private TextField tfVrednost = new TextField();
	
	public KolekcijaStage() {
		
		VBox vBox = new VBox(20);
		vBox.setAlignment(Pos.CENTER);
		vBox.setPadding(new Insets(20));
		
		kreiranjeTabele();
		kreiranjeGrida();
		
		vBox.getChildren().addAll(tabela, grid);
		
		scene = new Scene(vBox, 600, 600);
		setScene(scene);
		
		setTitle("My Collection");
	//	show();
		
	}


	private void kreiranjeGrida() {
		grid.setVgap(15);
		grid.setHgap(15);
		grid.setAlignment(Pos.CENTER);

		grid.add(new Label("Vinyl: "), 0, 0);
		grid.add(new Label("CD: "), 0, 1);
		grid.add(new Label("Total pices: "), 0, 2);
		grid.add(new Label("Used: "), 2, 0);
		grid.add(new Label("New: "), 2, 1);
		grid.add(new Label("Value of collection: "), 2, 2);

		grid.add(tfVinyl, 1, 0);
		grid.add(tfCd, 1, 1);
		grid.add(tfUkupno, 1, 2);
		grid.add(tfPolovne, 3, 0);
		grid.add(tfNovi, 3, 1);
		grid.add(tfVrednost, 3, 2);
		
	}

	private void kreiranjeTabele() {
		TableColumn<IzvodjacAlbumi, Izvodjac> tcIzvodjaci = new TableColumn<IzvodjacAlbumi, Izvodjac>("Naziv izvodjaca");
		TableColumn<IzvodjacAlbumi, Set<String>> tcAlbumi = new TableColumn<IzvodjacAlbumi, Set<String>>("Albumi");
		
		tabela.getColumns().addAll(tcIzvodjaci, tcAlbumi);
		
		tcIzvodjaci.setCellValueFactory(new PropertyValueFactory<>("izvodjac"));
		tcAlbumi.setCellValueFactory(new PropertyValueFactory<>("albumi"));
	}

	public TableView<IzvodjacAlbumi> getTabela() {
		return tabela;
	}
	
	public TextField getTfVinyl() {
		return tfVinyl;
	}

	public TextField getTfCd() {
		return tfCd;
	}

	public TextField getTfUkupno() {
		return tfUkupno;
	}

	public TextField getTfPolovne() {
		return tfPolovne;
	}

	public TextField getTfNovi() {
		return tfNovi;
	}

	public TextField getTfVrednost() {
		return tfVrednost;
	}
	
	
	
}
