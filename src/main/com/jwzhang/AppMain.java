package com.jwzhang;

import com.jwzhang.analysis.SensitiveWordsWatchman;
import com.jwzhang.httpclient.GitHubClient;
import com.jwzhang.model.GitHubSearchResult;
import org.apache.http.impl.client.HttpClientBuilder;

public class AppMain {
    public static void main(String[] args) {
        GitHubClient githubClient = new GitHubClient(HttpClientBuilder.create().build());
        SensitiveWordsWatchman sensitiveWordsWatchman = new SensitiveWordsWatchman(githubClient);

        GitHubSearchResult results = sensitiveWordsWatchman.watch("user:iambowen rea");

        results.getItems().forEach(item -> System.out.println(item.getHtmlUrl()));
    }
}
