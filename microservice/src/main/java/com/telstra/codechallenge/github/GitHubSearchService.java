package com.telstra.codechallenge.github;

import com.telstra.codechallenge.catfacts.CatFact;
import com.telstra.codechallenge.catfacts.CatFactResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class GitHubSearchService {
    @Value("${github.search.api.url}")
    private String gitHubSearchUrl;

    private final RestTemplate restTemplate;

    public GitHubSearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    /**
     * Given a count and page No. this method uses the Github Search API
     * to fetch the list of repositories created in the last week and sort
     * based on most no.of stars
     * @param count - no. of repositories to fetch
     * @param pageNo - pagination number to scroll through list
     * @return
     */
    public GitHubSearchAPIResponse findLatestHotRepositories(int count, int pageNo) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        Date weekBeforeToday = cal.getTime();
        String weekBeforeDate = dateFormat.format(weekBeforeToday);
        System.out.println(weekBeforeDate);

        GitHubSearchAPIResponse apiResponse = new GitHubSearchAPIResponse();
        try {
           apiResponse =
                    restTemplate.getForObject(gitHubSearchUrl +
                            "/search/repositories?q=created:>" + weekBeforeDate + "&sort=stars&order=desc&per_page=" +
                            count + "&page=" + pageNo, GitHubSearchAPIResponse.class);
        } catch (Exception e) {
            //TODO: Handle exception more specific and use generic response than GitHubSearchAPIResponse
            if (e instanceof HttpClientErrorException) {
                //catch
                apiResponse = new GitHubSearchAPIResponse();
                apiResponse.setMessage(e.getMessage());
            }
        }


        List<RepositoryData> lists = new ArrayList<>();
        int total = 0;
        System.out.println(apiResponse);
        if (apiResponse != null) {
            lists = apiResponse.getItems();
            total = apiResponse.getTotalCount();
        }
        return apiResponse;
    }
}
