package com.jwzhang.httpclient;

import com.google.gson.Gson;
import com.jwzhang.model.github.GitHubSearchResult;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class GitHubClient {

    private final HttpClient httpClient;
    private final String path = "/search/code";
    private final String gitHubApiHost = "api.github.com";
    private final String scheme = "https";
    private final Gson gson;

    public GitHubClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.gson = new Gson();
    }

    public GitHubSearchResult search(String params) throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder();
        URI uri = builder.setScheme(scheme).setHost(gitHubApiHost)
            .setPath(path).setParameter("q", params)
            .build();
        HttpResponse response;
        GitHubSearchResult result = new GitHubSearchResult();
        System.out.println("Sending request to GitHub API");
        response = httpClient.execute(new HttpGet(uri));
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            BufferedReader bufferedReader = convertEntityContentToReader(response);
            result = gson.fromJson(bufferedReader, GitHubSearchResult.class);
        }

        System.out.println(response.getStatusLine().getStatusCode() + " Response from GitHub API.");
        EntityUtils.consume(response.getEntity());
        return result;
    }

    private BufferedReader convertEntityContentToReader(HttpResponse response) throws IOException {
        InputStream content = response.getEntity().getContent();
        InputStreamReader inputStreamReader = new InputStreamReader(content);
        return new BufferedReader(inputStreamReader);
    }
}
