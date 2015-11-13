package com.jwzhang.analysis;

import com.jwzhang.model.github.GitHubSearchResult;

public interface SecurityGuard {
    GitHubSearchResult watch(String keywords);
}
