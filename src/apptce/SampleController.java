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

public class SampleController {
	@FXML
	Button button;
	
	@FXML
	private ChoiceBox<String> choiceBox;
	
	public SampleController() {
		
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
			System.out.println(response.body());

			Json json = new Json();
			JsonValue jsonValue = json.fromJson(null, response.body()); // Parse JSON response
	        
	        // Assuming the JSON structure is like {"data": [...]}
	        JsonValue dataArray = jsonValue.get("data");

	        Municipio[] municipios = json.readValue(Municipio[].class, dataArray);
	        
	        for(Municipio m : municipios) {
				this.choiceBox.getItems().add(m.getNome_municipio());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
