package com.jwzhang.analysis;

import com.google.common.collect.Lists;
import com.jwzhang.httpclient.GitHubClient;
import com.jwzhang.model.GitHubItem;
import com.jwzhang.model.GitHubSearchResult;
import org.apache.http.impl.client.HttpClients;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SensitiveWordsWatchmanTest {

    private SecurityGuard watchman;
    @Mock
    private GitHubClient gitHubClient = mock(GitHubClient.class);

    @Before
    public void setup() {
        watchman = new SensitiveWordsWatchman(gitHubClient);
    }

    @Test
    public void shouldReturnEmptyResultWhenThereIsNoItem() throws IOException, URISyntaxException {
        when(gitHubClient.search("no result")).thenReturn(new GitHubSearchResult());
        assertThat(watchman.watch("no result").getTotalCounts(), is(0));
    }

    @Test
    public void shouldReturnExpectedResultsWhenThereAreSomeItems() throws IOException, URISyntaxException {
        GitHubSearchResult results = new GitHubSearchResult();
        GitHubItem item1 = new GitHubItem();
        GitHubItem item2 = new GitHubItem();
        List<GitHubItem> items = Lists.newArrayList(item1, item2);
        results.setIncompleteResults(false);
        results.setTotalCounts(2);
        results.setItems(items);
        when(gitHubClient.search("some results")).thenReturn(results);
        assertThat(watchman.watch("some results").getItems(), is(not(empty())));
        assertThat(watchman.watch("some results").getTotalCounts(), is(2));
    }

    @Test
    public void shouldReturnEmptyResultEvenNoResultBack() throws IOException, URISyntaxException {
        when(gitHubClient.search(anyString())).thenThrow(IOException.class);
        assertThat(watchman.watch("exceptions").getTotalCounts(), is(0));
        assertThat(watchman.watch("exceptions").getItems(), is(empty()));
    }

}