package com.telstra.codechallenge.github;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RepositoryData {
    String name;
    @JsonProperty("html_url")
    String htmlUrl;
    String watchers;
    String language;
    String description;

    public RepositoryData() {

    }
    public RepositoryData(String name, String htmlUrl, String watchers, String language, String description) {
        this.name = name;
        this.htmlUrl = htmlUrl;
        this.watchers = watchers;
        this.language = language;
        this.description = description;
    }
}
