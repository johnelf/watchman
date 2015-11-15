package com.jwzhang;

import com.jwzhang.analysis.SensitiveWordsWatchman;
import com.jwzhang.httpclient.GitHubClient;
import com.jwzhang.io.CSVInputParser;
import com.jwzhang.io.GitHubRegex;
import com.jwzhang.model.github.GitHubItem;
import com.jwzhang.model.github.GitHubSearchResult;
import com.jwzhang.model.user.User;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AppMain {
    private static String keywords = "keywords";

    public static void main(String[] args) throws IOException {

        if (args.length < 2) {
            System.out.println("Two Arguments are required.");
            System.out.println("Usage: java -jar <watchman.jar> <input csv file> <key words>");
            return;
        }

        GitHubClient githubClient = new GitHubClient(HttpClientBuilder.create().build());
        SensitiveWordsWatchman sensitiveWordsWatchman = new SensitiveWordsWatchman(githubClient);
        CSVInputParser csvInputParser = new CSVInputParser();
        GitHubRegex gitHubRegex = new GitHubRegex();

        List<User> users = csvInputParser.parse(
            Optional.of(args[0]).map(AppMain::validateFileName).map(AppMain::getFileName).get());
        keywords = Optional.of(args[1]).map(AppMain::processKeyWords).get();

        String targetFile = Paths.get("").toAbsolutePath().toString() + "/report.html";

        BufferedWriter output = null;
        File file = new File(targetFile);
        output = new BufferedWriter(new FileWriter(file));
        output.write("<head title=" + AppMain.keywords + "\"><head>\n<body>");
        String userNames = "";
        users.removeIf(u -> u.getAccount().equals("") || u.getName().equals(""));
        for (User user: users) {
            String userName = gitHubRegex.extractUserName(user.getAccount());
            userNames += "user:" + userName + " ";
        }

        GitHubSearchResult searchResult = sensitiveWordsWatchman.watch(userNames + AppMain.keywords);
        try {
            for (GitHubItem item : searchResult.getItems()) {
                output.write("<a href=");
                output.write(item.getHtmlUrl());
                output.write(" >" + item.getRepo().getOwner().getLogin() + "</a><br>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        output.write("</body>");
        output.close();
    }

    private static String processKeyWords(String keywords){
        if (keywords == null || keywords.equals("")) {
            System.out.println("Keywords should not be empty or null");
            System.out.println("Use token as default value");
            return "token";
        }
        return keywords;
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
