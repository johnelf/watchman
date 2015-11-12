package com.jwzhang.analysis;

import com.jwzhang.model.GitHubSearchResult;

public interface SecurityGuard {
    GitHubSearchResult watch(String keywords);
}
