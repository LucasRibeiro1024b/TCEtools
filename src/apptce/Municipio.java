package apptce;

public class Municipio {
	private String codigo_municipio;
	private String nome_municipio;
	private String geoibgeId;
	private String geonamesId;
	
	public Municipio() {}

	public String getCodigo_municipio() {
		return codigo_municipio;
	}
	public void setCodigo_municipio(String codigo_municipio) {
		this.codigo_municipio = codigo_municipio;
	}
	public String getNome_municipio() {
		return nome_municipio;
	}
	public void setNome_municipio(String nome_municipio) {
		this.nome_municipio = nome_municipio;
	}
	public String getGeoibgeId() {
		return geoibgeId;
	}
	public void setGeoibgeId(String geoibgeId) {
		this.geoibgeId = geoibgeId;
	}
	public String getGeonamesId() {
		return geonamesId;
	}
	public void setGeonamesId(String geonamesId) {
		this.geonamesId = geonamesId;
	}
	
	@Override
	public String toString() {
		return "Municipio{codigo_municipio="+codigo_municipio+", nome_municipio="+nome_municipio+", geoibgeId="+geoibgeId+", geonamesId="+geonamesId+"}";
	}
}
