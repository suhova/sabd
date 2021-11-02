package app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobConfig implements Obfuscatable<JobConfig>{
    @JsonProperty
    String name;
    @JsonProperty
    String pool;
    @JsonProperty
    String cronTime;
    @JsonProperty
    String jarVersion;

    private JobConfig(){};

    public JobConfig(String name, String pool, String cronTime, String jarVersion) {
        this.name = name;
        this.pool = pool;
        this.cronTime = cronTime;
        this.jarVersion = jarVersion;
    }

    @Override
    public String toString() {
        return "app.JobConfig{" +
            "name='" + name + '\'' +
            ", pool='" + pool + '\'' +
            ", jarVersion='" + jarVersion + '\'' +
            '}';
    }

    @Override
    public JobConfig obfuscate(){
        return new JobConfig(
            ObfuscationUtils.obfuscate(name),
            ObfuscationUtils.obfuscate(pool),
            ObfuscationUtils.obfuscate(cronTime),
            ObfuscationUtils.obfuscate(jarVersion)
        );
    }

    @Override
    public JobConfig unobfuscate() {
        return new JobConfig(
            ObfuscationUtils.unobfuscate(name),
            ObfuscationUtils.unobfuscate(pool),
            ObfuscationUtils.unobfuscate(cronTime),
            ObfuscationUtils.unobfuscate(jarVersion)
        );
    }
}
