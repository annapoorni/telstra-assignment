package com.telstra.codechallenge.github;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GitHubSearchController {

    @Autowired
    GitHubSearchService gitHubSearchService;

    @GetMapping(path = "/latestHotRepositories", produces = "application/vnd.github+json")
    public GitHubSearchAPIResponse findLatestHotRepositories(@RequestParam(value = "count", defaultValue = "5") int count,
                                                          @RequestParam(value = "page", defaultValue = "1") int pageNo) {
        return gitHubSearchService.findLatestHotRepositories(count, pageNo);
    }
}
