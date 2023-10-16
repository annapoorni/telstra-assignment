package com.telstra.codechallenge;

import com.telstra.codechallenge.github.GitHubSearchAPIResponse;
import com.telstra.codechallenge.github.RepositoryData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@JsonTest
public class GitHubSearchAPIJSonTests {

    @Autowired
    private JacksonTester<GitHubSearchAPIResponse> json;

    @Test
    public void testSerialize() throws Exception {
        List<RepositoryData> items = new ArrayList<>();
        items.add(new RepositoryData( "spring-2023-13500-lab-08-mattlabarca10",
                "https://github.com/Achapko200/spring-2023-13500-lab-08-mattlabarca10", "0", "C++",
                "spring-2023-13500-lab-08-mattlabarca10 created by GitHub Classroom"));
        GitHubSearchAPIResponse response = new GitHubSearchAPIResponse();
        response.setItems(items);
        response.setTotalCount(10);
        assertThat(this.json.write(response)).isEqualToJson("""
                {
                  "items": [
                    {
                      "name": "spring-2023-13500-lab-08-mattlabarca10",
                      "watchers": "0",
                      "language": "C++",
                      "description": "spring-2023-13500-lab-08-mattlabarca10 created by GitHub Classroom",
                      "html_url": "https://github.com/Achapko200/spring-2023-13500-lab-08-mattlabarca10"
                    }
                  ],
                  "total_count": 10
                }
                """);

    }

    @Test
    public void testDeserialize() throws Exception {
        String jsonContent = """
                {
                  "items": [
                    {
                      "name": "test",
                      "html_url": "blah",
                      "watchers": "0",
                      "language": "Java",
                      "description": "test repo"
                    }
                  ],
                  "total_count": 10
                }
                """;
        List<RepositoryData> items = new ArrayList<>();
        items.add(new RepositoryData( "test",
                "blah", "0", "Java",
                "test repo"));
        GitHubSearchAPIResponse response = new GitHubSearchAPIResponse();
        response.setItems(items);
        response.setTotalCount(10);
        assertThat(this.json.parse(jsonContent)).isEqualTo(response);
    }
}
