package com.jwzhang.model.github;

public class GitHubMatchedResult {
    private String name;
    private String url;

    public GitHubMatchedResult(String name, String htmlUrl) {
        this.name =  name;
        this.url = htmlUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
