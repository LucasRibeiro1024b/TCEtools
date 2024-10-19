package apptce;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonValue;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Municipio;
import model.UnidadeOrc;

public class SampleController {
	@FXML
	private Button pesquisa;
	@FXML
	private TextField exercicio;
	@FXML
	private TextField quantidade;
	@FXML
	private TextField deslocamento;
	@FXML
	private ChoiceBox<Municipio> choiceBox;
	@FXML
	private TableView<UnidadeOrc> table;
	
	
	public SampleController() {
		
	}
	
	public void onButtonAction() {
		String cod = this.choiceBox.getValue().getCodigo_municipio();
		String exe = this.exercicio.getText();
		String qtd = this.quantidade.getText();
		String off = this.deslocamento.getText();
		
		try {
			HttpClient client = HttpClient.newHttpClient();
			//'https://api-dados-abertos.tce.ce.gov.br/unidades_orcamentarias?codigo_municipio=125&exercicio_orcamento=202400&quantidade=10&deslocamento=1' 
			String url = "https://api-dados-abertos.tce.ce.gov.br/unidades_orcamentarias?codigo_municipio="+cod+"&exercicio_orcamento="+exe+"&quantidade="+qtd+"&deslocamento="+off;
			System.out.println(url);
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(url))
					.GET()
					.build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("Response Code: " + response.statusCode());
			
			Json json = new Json();
			JsonValue jsonValue = json.fromJson(null, response.body());
	        JsonValue dataArray = jsonValue.get("data");
	        
	        UnidadeOrc[] unidorcs = json.readValue(UnidadeOrc[].class, dataArray);
	        
	        TableColumn<UnidadeOrc, String> colA = new TableColumn<>("codigo_municipio");
	        TableColumn<UnidadeOrc, String> colB = new TableColumn<>("exercicio_orcamento");
	        TableColumn<UnidadeOrc, String> colC = new TableColumn<>("codigo_orgao");
	        TableColumn<UnidadeOrc, String> colD = new TableColumn<>("codigo_unidade");
	        TableColumn<UnidadeOrc, String> colE = new TableColumn<>("codigo_tipo_unidade");
	        TableColumn<UnidadeOrc, String> colF = new TableColumn<>("nome_unidade");
	        TableColumn<UnidadeOrc, String> colG = new TableColumn<>("tipo_administracao_unidade");

	        this.table.getColumns().addAll(
	            colA, colB, colC, colD, colE, colF, colG
	        );
	        
	        for(UnidadeOrc u : unidorcs) {
				//this.choiceBox.getItems().add(m);
	        	System.out.println(u.nome_unidade); 
	        	this.table.getItems().add(u);
			}
	        
			/*
			 * codigo_municipio; exercicio_orcamento; codigo_orgao; codigo_unidade;
			 * codigo_tipo_unidade; nome_unidade; tipo_administracao_unidade;
			 */
	        colA.setCellValueFactory(new PropertyValueFactory<>("codigo_municipio"));
	        colB.setCellValueFactory(new PropertyValueFactory<>("exercicio_orcamento"));
	        colC.setCellValueFactory(new PropertyValueFactory<>("codigo_orgao"));
	        colD.setCellValueFactory(new PropertyValueFactory<>("codigo_unidade"));
	        colE.setCellValueFactory(new PropertyValueFactory<>("codigo_tipo_unidade"));
	        colF.setCellValueFactory(new PropertyValueFactory<>("nome_unidade"));
	        colG.setCellValueFactory(new PropertyValueFactory<>("tipo_administracao_unidade"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private void initialize() {
		try {
			HttpClient client = HttpClient.newHttpClient();

			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://api-dados-abertos.tce.ce.gov.br/municipios"))
					.GET()
					.build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println("Response Code: " + response.statusCode());

			Json json = new Json();
			JsonValue jsonValue = json.fromJson(null, response.body());
	        JsonValue dataArray = jsonValue.get("data");

	        Municipio[] municipios = json.readValue(Municipio[].class, dataArray);
	        
	        for(Municipio m : municipios) {
				this.choiceBox.getItems().add(m);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
