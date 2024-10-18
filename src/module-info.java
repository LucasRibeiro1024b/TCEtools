module TCEtools {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.net.http;
	requires jsonbeans;
	
	opens apptce to javafx.graphics, javafx.fxml, jsonbeans;
}
