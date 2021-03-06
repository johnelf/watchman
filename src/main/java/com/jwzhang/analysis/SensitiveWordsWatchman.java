package com.jwzhang.analysis;

import com.jwzhang.httpclient.GitHubClient;
import com.jwzhang.model.github.GitHubSearchResult;

import java.io.IOException;
import java.net.URISyntaxException;

public class SensitiveWordsWatchman implements SecurityGuard {

    private final GitHubClient gitHubClient;

    public SensitiveWordsWatchman(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    @Override
    public GitHubSearchResult watch(String keywords) {
        GitHubSearchResult results = new GitHubSearchResult();
        try {
            System.out.println("Sending search query to github api.");
            results = gitHubClient.search(keywords);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return results;
    }
}
