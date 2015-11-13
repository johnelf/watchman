package com.jwzhang.model.github;

import com.google.gson.annotations.SerializedName;

public class GitHubItem {
    private String name;
    private String path;
    private String sha;
    private String url;
    @SerializedName("git_url")
    private String gitUrl;
    @SerializedName("html_url")
    private String htmlUrl;
    @SerializedName("repository")
    private GitHubRepo repo;
    private Double score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public GitHubRepo getRepo() {
        return repo;
    }

    public void setRepo(GitHubRepo repo) {
        this.repo = repo;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

}
