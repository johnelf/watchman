package com.jwzhang;

import com.google.common.collect.Lists;
import com.jwzhang.analysis.SensitiveWordsWatchman;
import com.jwzhang.httpclient.GitHubClient;
import com.jwzhang.io.CSVInputParser;
import com.jwzhang.io.GitHubRegex;
import com.jwzhang.model.github.GitHubItem;
import com.jwzhang.model.github.GitHubSearchResult;
import com.jwzhang.model.user.User;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class AppMain {
    private static String keywords = "realestate.com.au";

    public static void main(String[] args) throws IOException {
        String fileName = "/tmp/a.csv";

        GitHubClient githubClient = new GitHubClient(HttpClientBuilder.create().build());
        SensitiveWordsWatchman sensitiveWordsWatchman = new SensitiveWordsWatchman(githubClient);
        CSVInputParser csvInputParser = new CSVInputParser();
        GitHubRegex gitHubRegex = new GitHubRegex();

        List<User> users = csvInputParser.parse(fileName);

        BufferedWriter output = null;
        File file = new File("/tmp/a.html");
        output = new BufferedWriter(new FileWriter(file));
        output.write("<head title=" + keywords + "\"><head>\n<body>");
        String userNames = "";
        users.removeIf(u -> u.getAccount().equals("") || u.getName().equals(""));
        for (User user: users) {
            String userName = gitHubRegex.extractUserName(user.getAccount());
            userNames += "user:" + userName + " ";
        }

        GitHubSearchResult searchResult = sensitiveWordsWatchman.watch(userNames + keywords);
        try {
            for (GitHubItem item : searchResult.getItems()) {
                output.write("<a href=");
                output.write(item.getHtmlUrl());
                output.write(" >" + item.getGitUrl() + "</a><br>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        output.write("</body>");
        output.close();
    }
}
