package app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigResolver implements Obfuscatable<ConfigResolver>{
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty
    private List<JobConfig> config = new ArrayList<>();

    private ConfigResolver(){};

    public ConfigResolver(List<JobConfig> config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return "app.ConfigResolver{" +
            "config=" + config +
            '}';
    }

    public List<JobConfig> getConfig() {
        return config;
    }

    @Override
    public ConfigResolver obfuscate(){
        return new ConfigResolver(
            config.stream().map(JobConfig::obfuscate).collect(Collectors.toList())
        );
    }

    @Override
    public ConfigResolver unobfuscate() {
        return new ConfigResolver(
            config.stream().map(JobConfig::unobfuscate).collect(Collectors.toList())
        );
    }
}
