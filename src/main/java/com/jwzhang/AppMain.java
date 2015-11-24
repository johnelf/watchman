package com.jwzhang;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jwzhang.analysis.SensitiveWordsWatchman;
import com.jwzhang.httpclient.GitHubClient;
import com.jwzhang.io.CSVInputParser;
import com.jwzhang.io.GitHubRegex;
import com.jwzhang.model.github.GitHubItem;
import com.jwzhang.model.github.GitHubResultItem;
import com.jwzhang.model.github.GitHubSearchResult;
import com.jwzhang.model.user.User;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
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

        String targetFile = Paths.get("").toAbsolutePath().toString() + "/" + keywords + ".html";

        BufferedWriter output = null;
        output = new BufferedWriter(new FileWriter(new File(targetFile)));

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

        HashMap<String, GitHubResultItem> userMap = Maps.newHashMap();
        for (GitHubSearchResult result : resultSet) {
            for (GitHubItem item : result.getItems()) {
                String loginName = item.getRepo().getOwner().getLogin();
                if (userMap.containsKey(loginName)) {
                    userMap.get(loginName).getUrls().add(item.getHtmlUrl());
                } else {
                    GitHubResultItem value = new GitHubResultItem();
                    value.setAvartar(item.getRepo().getOwner().getAvatarUrl());
                    value.setName(item.getRepo().getOwner().getLogin());
                    value.getUrls().add(item.getHtmlUrl());
                    value.setGitUrl(item.getRepo().getOwner().getUrl());
                    userMap.put(loginName, value);
                }
            }
        }

        DefaultMustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("report.mustache");

        HashMap<Object, Object> models = Maps.newHashMap();
        models.put("userMap", userMap.values());

        mustache.execute(output, models).flush();
        output.close();

        System.out.println(resultSet.size() + " Items matched.");
        System.out.println("Write report to " + keywords + ".html in current working directory");
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
