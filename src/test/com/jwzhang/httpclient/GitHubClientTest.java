package com.jwzhang.httpclient;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.protocol.HTTP;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GitHubClientTest {
    private GitHubClient gitHubClient;
    private String path = "/search/code";
    @Mock
    private HttpClient httpClient = mock(HttpClient.class);
    @Mock
    private HttpResponse response = mock(HttpResponse.class);
    @Mock
    private StatusLine statusLine = mock(StatusLine.class);
    @Mock
    private HttpEntity entity = mock(HttpEntity.class);
    private String empty;

    @Before
    public void setup() {
        gitHubClient = new GitHubClient(httpClient, path);
        empty = "{\n" +
            "  \"total_count\": 0,\n" +
            "  \"incomplete_results\": false,\n" +
            "  \"items\": [\n" +
            "    \n" +
            "  ]\n" +
            "}";
    }

    @Test
    public void shouldReturnEmptySearchResultWhenNoItem() throws IOException, URISyntaxException {
        when(httpClient.execute(any(HttpGet.class))).thenReturn(response);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(IOUtils.toInputStream(empty));

        String parameter = "user:abc java";
        assertThat(gitHubClient.search(parameter).getItems().size(), is(0));
    }
}
