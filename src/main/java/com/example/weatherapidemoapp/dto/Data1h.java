
package com.example.weatherapidemoapp.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "time",
    "precipitation",
    "snowfraction",
    "rainspot",
    "temperature",
    "felttemperature",
    "pictocode",
    "windspeed",
    "winddirection",
    "relativehumidity",
    "sealevelpressure",
    "precipitation_probability",
    "convective_precipitation",
    "isdaylight",
    "uvindex"
})
@Generated("jsonschema2pojo")
public class Data1h {

    @JsonProperty("time")
    private List<String> time = null;
    @JsonProperty("precipitation")
    private List<Double> precipitation = null;
    @JsonProperty("snowfraction")
    private List<Double> snowfraction = null;
    @JsonProperty("rainspot")
    private List<String> rainspot = null;
    @JsonProperty("temperature")
    private List<Double> temperature = null;
    @JsonProperty("felttemperature")
    private List<Double> felttemperature = null;
    @JsonProperty("pictocode")
    private List<Integer> pictocode = null;
    @JsonProperty("windspeed")
    private List<Double> windspeed = null;
    @JsonProperty("winddirection")
    private List<Integer> winddirection = null;
    @JsonProperty("relativehumidity")
    private List<Integer> relativehumidity = null;
    @JsonProperty("sealevelpressure")
    private List<Double> sealevelpressure = null;
    @JsonProperty("precipitation_probability")
    private List<Integer> precipitationProbability = null;
    @JsonProperty("convective_precipitation")
    private List<Double> convectivePrecipitation = null;
    @JsonProperty("isdaylight")
    private List<Integer> isdaylight = null;
    @JsonProperty("uvindex")
    private List<Integer> uvindex = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("time")
    public List<String> getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(List<String> time) {
        this.time = time;
    }

    @JsonProperty("precipitation")
    public List<Double> getPrecipitation() {
        return precipitation;
    }

    @JsonProperty("precipitation")
    public void setPrecipitation(List<Double> precipitation) {
        this.precipitation = precipitation;
    }

    @JsonProperty("snowfraction")
    public List<Double> getSnowfraction() {
        return snowfraction;
    }

    @JsonProperty("snowfraction")
    public void setSnowfraction(List<Double> snowfraction) {
        this.snowfraction = snowfraction;
    }

    @JsonProperty("rainspot")
    public List<String> getRainspot() {
        return rainspot;
    }

    @JsonProperty("rainspot")
    public void setRainspot(List<String> rainspot) {
        this.rainspot = rainspot;
    }

    @JsonProperty("temperature")
    public List<Double> getTemperature() {
        return temperature;
    }

    @JsonProperty("temperature")
    public void setTemperature(List<Double> temperature) {
        this.temperature = temperature;
    }

    @JsonProperty("felttemperature")
    public List<Double> getFelttemperature() {
        return felttemperature;
    }

    @JsonProperty("felttemperature")
    public void setFelttemperature(List<Double> felttemperature) {
        this.felttemperature = felttemperature;
    }

    @JsonProperty("pictocode")
    public List<Integer> getPictocode() {
        return pictocode;
    }

    @JsonProperty("pictocode")
    public void setPictocode(List<Integer> pictocode) {
        this.pictocode = pictocode;
    }

    @JsonProperty("windspeed")
    public List<Double> getWindspeed() {
        return windspeed;
    }

    @JsonProperty("windspeed")
    public void setWindspeed(List<Double> windspeed) {
        this.windspeed = windspeed;
    }

    @JsonProperty("winddirection")
    public List<Integer> getWinddirection() {
        return winddirection;
    }

    @JsonProperty("winddirection")
    public void setWinddirection(List<Integer> winddirection) {
        this.winddirection = winddirection;
    }

    @JsonProperty("relativehumidity")
    public List<Integer> getRelativehumidity() {
        return relativehumidity;
    }

    @JsonProperty("relativehumidity")
    public void setRelativehumidity(List<Integer> relativehumidity) {
        this.relativehumidity = relativehumidity;
    }

    @JsonProperty("sealevelpressure")
    public List<Double> getSealevelpressure() {
        return sealevelpressure;
    }

    @JsonProperty("sealevelpressure")
    public void setSealevelpressure(List<Double> sealevelpressure) {
        this.sealevelpressure = sealevelpressure;
    }

    @JsonProperty("precipitation_probability")
    public List<Integer> getPrecipitationProbability() {
        return precipitationProbability;
    }

    @JsonProperty("precipitation_probability")
    public void setPrecipitationProbability(List<Integer> precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    @JsonProperty("convective_precipitation")
    public List<Double> getConvectivePrecipitation() {
        return convectivePrecipitation;
    }

    @JsonProperty("convective_precipitation")
    public void setConvectivePrecipitation(List<Double> convectivePrecipitation) {
        this.convectivePrecipitation = convectivePrecipitation;
    }

    @JsonProperty("isdaylight")
    public List<Integer> getIsdaylight() {
        return isdaylight;
    }

    @JsonProperty("isdaylight")
    public void setIsdaylight(List<Integer> isdaylight) {
        this.isdaylight = isdaylight;
    }

    @JsonProperty("uvindex")
    public List<Integer> getUvindex() {
        return uvindex;
    }

    @JsonProperty("uvindex")
    public void setUvindex(List<Integer> uvindex) {
        this.uvindex = uvindex;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
