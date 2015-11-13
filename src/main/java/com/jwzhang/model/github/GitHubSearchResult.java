package com.jwzhang.model.github;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GitHubSearchResult {
    @SerializedName("total_count")
    private Integer totalCounts;
    @SerializedName("incomplete_results")
    private Boolean incompleteResults;
    private List<GitHubItem> items;

    public GitHubSearchResult() {
        this.totalCounts = 0;
        this.incompleteResults = false;
        this.items = Lists.newArrayList();
    }

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(Integer totalCounts) {
        this.totalCounts = totalCounts;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<GitHubItem> getItems() {
        return items;
    }

    public void setItems(List<GitHubItem> items) {
        this.items = items;
    }
}
