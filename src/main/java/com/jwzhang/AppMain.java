package com.jwzhang;

import com.jwzhang.analysis.SensitiveWordsWatchman;
import com.jwzhang.httpclient.GitHubClient;
import com.jwzhang.io.CSVInputParser;
import com.jwzhang.io.GitHubRegex;
import com.jwzhang.model.github.GitHubItem;
import com.jwzhang.model.github.GitHubSearchResult;
import com.jwzhang.model.user.User;
import com.sun.media.sound.InvalidFormatException;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class AppMain {
    private static String keywords = "realestate.com.au";

    public static void main(String[] args) throws IOException {

        if (args.length < 2) {
            System.out.println("Two Arguments are required.");
            return;
        }

        GitHubClient githubClient = new GitHubClient(HttpClientBuilder.create().build());
        SensitiveWordsWatchman sensitiveWordsWatchman = new SensitiveWordsWatchman(githubClient);
        CSVInputParser csvInputParser = new CSVInputParser();
        GitHubRegex gitHubRegex = new GitHubRegex();

        List<User> users = csvInputParser.parse(
            Optional.of(args[0]).map(AppMain::validateFileName).map(AppMain::getFileName).get());

        String targetFile = Paths.get("").toAbsolutePath().toString() + "/report.html";

        BufferedWriter output = null;
        File file = new File(targetFile);
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

    private static String validateFileName(String n) {
        if (n == null || n.equals("") || !n.endsWith(".csv")) {
            return "example.csv";
        }
        return  n;
    }

    private static String getFileName(String name) {
        String currentWorkingDir = Paths.get("").toAbsolutePath().toString();
        if (Paths.get(name).isAbsolute()) {
            return name;
        } else {
            return currentWorkingDir + "/" + name;
        }
    }
}
