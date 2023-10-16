package com.telstra.codechallenge.github;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GitHubSearchAPIResponse {
    private List<RepositoryData> items;
    @JsonProperty("total_count")
    private int totalCount;
    private String message;
}
