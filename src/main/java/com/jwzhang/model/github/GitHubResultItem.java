package com.jwzhang.model.github;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class GitHubResultItem {

    private String name;
    private String avartar;
    private String gitUrl;
    private List<GitHubMatchedResult> urls;

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

    public List<GitHubMatchedResult> getUrls() {
        return urls;
    }

    public void setUrls(List<GitHubMatchedResult> urls) {
        this.urls = urls;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

}
