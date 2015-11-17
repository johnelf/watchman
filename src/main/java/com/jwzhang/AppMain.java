package com.jwzhang;

import com.google.common.collect.Lists;
import com.jwzhang.analysis.SensitiveWordsWatchman;
import com.jwzhang.httpclient.GitHubClient;
import com.jwzhang.io.CSVInputParser;
import com.jwzhang.io.GitHubRegex;
import com.jwzhang.model.github.GitHubItem;
import com.jwzhang.model.github.GitHubSearchResult;
import com.jwzhang.model.user.User;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
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
        output.write("<head title=" + keywords + "\"><head>\n<body>");
        List<String> userNames = Lists.newArrayList();
        String names = "";
        users.removeIf(u -> u.getAccount().equals("") || u.getName().equals(""));

        Integer count = 1;
        Integer index = 0;
        for (User user: users) {
            String userName = gitHubRegex.extractUserName(user.getAccount());
            names += "user:" + userName + " ";
            if (count % 50 == 0) {
                userNames.add(index, names);
                index++;
                names = "";
            }
            count++;
        }
        userNames.add(index, names);

        List<GitHubSearchResult> resultSet = Lists.newArrayList();
        userNames.forEach(n -> resultSet.add(sensitiveWordsWatchman.watch(n + keywords)));

        Integer totalMatched = 0;
        try {
            for (GitHubSearchResult searchResult: resultSet) {
                totalMatched += searchResult.getTotalCounts();
                for (GitHubItem item : searchResult.getItems()) {
                    output.write("<a href=");
                    output.write(item.getHtmlUrl());
                    output.write(" target=\"_blank\">" + item.getRepo().getOwner().getLogin() + "</a><br>");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        output.write("</body>");
        output.close();
        System.out.println(totalMatched + " Items matched.");
        System.out.println("Write report to report.html in current working directory");
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
