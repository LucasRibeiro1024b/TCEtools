module TCEtools {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.net.http;
	requires jsonbeans;
	
	opens model to jsonbeans, javafx.base;
	opens apptce to javafx.graphics, javafx.fxml, jsonbeans;
}
