package com.jwzhang.model.github;

import com.google.common.collect.Lists;

import java.util.List;

public class GitHubResultItem {

    private String name;
    private String avartar;
    private String gitUrl;
    private List<String> urls;

    public GitHubResultItem() {
        this.urls = Lists.newArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvartar() {
        return avartar;
    }

    public void setAvartar(String avartar) {
        this.avartar = avartar;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

}
