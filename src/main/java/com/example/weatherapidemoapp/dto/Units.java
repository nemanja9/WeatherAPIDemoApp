
package com.example.weatherapidemoapp.dto;

import java.util.HashMap;
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
    "precipitation_probability",
    "pressure",
    "relativehumidity",
    "co",
    "precipitation",
    "temperature",
    "windspeed",
    "winddirection",
    "predictability"
})
@Generated("jsonschema2pojo")
public class Units {

    @JsonProperty("time")
    private String time;
    @JsonProperty("precipitation_probability")
    private String precipitationProbability;
    @JsonProperty("pressure")
    private String pressure;
    @JsonProperty("relativehumidity")
    private String relativehumidity;
    @JsonProperty("co")
    private String co;
    @JsonProperty("precipitation")
    private String precipitation;
    @JsonProperty("temperature")
    private String temperature;
    @JsonProperty("windspeed")
    private String windspeed;
    @JsonProperty("winddirection")
    private String winddirection;
    @JsonProperty("predictability")
    private String predictability;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("precipitation_probability")
    public String getPrecipitationProbability() {
        return precipitationProbability;
    }

    @JsonProperty("precipitation_probability")
    public void setPrecipitationProbability(String precipitationProbability) {
        this.precipitationProbability = precipitationProbability;
    }

    @JsonProperty("pressure")
    public String getPressure() {
        return pressure;
    }

    @JsonProperty("pressure")
    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    @JsonProperty("relativehumidity")
    public String getRelativehumidity() {
        return relativehumidity;
    }

    @JsonProperty("relativehumidity")
    public void setRelativehumidity(String relativehumidity) {
        this.relativehumidity = relativehumidity;
    }

    @JsonProperty("co")
    public String getCo() {
        return co;
    }

    @JsonProperty("co")
    public void setCo(String co) {
        this.co = co;
    }

    @JsonProperty("precipitation")
    public String getPrecipitation() {
        return precipitation;
    }

    @JsonProperty("precipitation")
    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    @JsonProperty("temperature")
    public String getTemperature() {
        return temperature;
    }

    @JsonProperty("temperature")
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    @JsonProperty("windspeed")
    public String getWindspeed() {
        return windspeed;
    }

    @JsonProperty("windspeed")
    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed;
    }

    @JsonProperty("winddirection")
    public String getWinddirection() {
        return winddirection;
    }

    @JsonProperty("winddirection")
    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }

    @JsonProperty("predictability")
    public String getPredictability() {
        return predictability;
    }

    @JsonProperty("predictability")
    public void setPredictability(String predictability) {
        this.predictability = predictability;
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
