package com.jwzhang.httpclient;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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
    private String normalResults;

    @Before
    public void setup() {
        gitHubClient = new GitHubClient(httpClient);
        empty = "{\n" +
            "  \"total_count\": 0,\n" +
            "  \"incomplete_results\": false,\n" +
            "  \"items\": [\n" +
            "    \n" +
            "  ]\n" +
            "}";
        normalResults = "{\n" +
            "  \"total_count\": 3,\n" +
            "  \"incomplete_results\": false,\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"name\": \"contactmain.js\",\n" +
            "      \"path\": \"js/contactmain.js\",\n" +
            "      \"sha\": \"cd4b1aa5839729d4bc8709d6ef09e89838dc126d\",\n" +
            "      \"url\": \"https://api.github.com/repositories/14937448/contents/js/contactmain.js?ref=9cf4bb2cb2132bdc446fcdf54219f0a2f6cb0569\",\n" +
            "      \"git_url\": \"https://api.github.com/repositories/14937448/git/blobs/cd4b1aa5839729d4bc8709d6ef09e89838dc126d\",\n" +
            "      \"html_url\": \"https://github.com/abc/kieran-coursework-js/blob/9cf4bb2cb2132bdc446fcdf54219f0a2f6cb0569/js/contactmain.js\",\n" +
            "      \"repository\": {\n" +
            "        \"id\": 14937448,\n" +
            "        \"name\": \"kieran-coursework-js\",\n" +
            "        \"full_name\": \"abc/kieran-coursework-js\",\n" +
            "        \"owner\": {\n" +
            "          \"login\": \"abc\",\n" +
            "          \"id\": 3063240,\n" +
            "          \"avatar_url\": \"https://avatars.githubusercontent.com/u/3063240?v=3\",\n" +
            "          \"gravatar_id\": \"\",\n" +
            "          \"url\": \"https://api.github.com/users/abc\",\n" +
            "          \"html_url\": \"https://github.com/abc\",\n" +
            "          \"followers_url\": \"https://api.github.com/users/abc/followers\",\n" +
            "          \"following_url\": \"https://api.github.com/users/abc/following{/other_user}\",\n" +
            "          \"gists_url\": \"https://api.github.com/users/abc/gists{/gist_id}\",\n" +
            "          \"starred_url\": \"https://api.github.com/users/abc/starred{/owner}{/repo}\",\n" +
            "          \"subscriptions_url\": \"https://api.github.com/users/abc/subscriptions\",\n" +
            "          \"organizations_url\": \"https://api.github.com/users/abc/orgs\",\n" +
            "          \"repos_url\": \"https://api.github.com/users/abc/repos\",\n" +
            "          \"events_url\": \"https://api.github.com/users/abc/events{/privacy}\",\n" +
            "          \"received_events_url\": \"https://api.github.com/users/abc/received_events\",\n" +
            "          \"type\": \"User\",\n" +
            "          \"site_admin\": false\n" +
            "        },\n" +
            "        \"private\": false,\n" +
            "        \"html_url\": \"https://github.com/abc/kieran-coursework-js\",\n" +
            "        \"description\": \"Kieran and Grant's HTML5 coursework\",\n" +
            "        \"fork\": false,\n" +
            "        \"url\": \"https://api.github.com/repos/abc/kieran-coursework-js\",\n" +
            "        \"forks_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/forks\",\n" +
            "        \"keys_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/keys{/key_id}\",\n" +
            "        \"collaborators_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/collaborators{/collaborator}\",\n" +
            "        \"teams_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/teams\",\n" +
            "        \"hooks_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/hooks\",\n" +
            "        \"issue_events_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/issues/events{/number}\",\n" +
            "        \"events_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/events\",\n" +
            "        \"assignees_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/assignees{/user}\",\n" +
            "        \"branches_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/branches{/branch}\",\n" +
            "        \"tags_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/tags\",\n" +
            "        \"blobs_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/git/blobs{/sha}\",\n" +
            "        \"git_tags_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/git/tags{/sha}\",\n" +
            "        \"git_refs_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/git/refs{/sha}\",\n" +
            "        \"trees_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/git/trees{/sha}\",\n" +
            "        \"statuses_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/statuses/{sha}\",\n" +
            "        \"languages_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/languages\",\n" +
            "        \"stargazers_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/stargazers\",\n" +
            "        \"contributors_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/contributors\",\n" +
            "        \"subscribers_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/subscribers\",\n" +
            "        \"subscription_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/subscription\",\n" +
            "        \"commits_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/commits{/sha}\",\n" +
            "        \"git_commits_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/git/commits{/sha}\",\n" +
            "        \"comments_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/comments{/number}\",\n" +
            "        \"issue_comment_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/issues/comments{/number}\",\n" +
            "        \"contents_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/contents/{+path}\",\n" +
            "        \"compare_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/compare/{base}...{head}\",\n" +
            "        \"merges_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/merges\",\n" +
            "        \"archive_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/{archive_format}{/ref}\",\n" +
            "        \"downloads_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/downloads\",\n" +
            "        \"issues_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/issues{/number}\",\n" +
            "        \"pulls_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/pulls{/number}\",\n" +
            "        \"milestones_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/milestones{/number}\",\n" +
            "        \"notifications_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/notifications{?since,all,participating}\",\n" +
            "        \"labels_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/labels{/name}\",\n" +
            "        \"releases_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/releases{/id}\"\n" +
            "      },\n" +
            "      \"score\": 0.18969063\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"main.js\",\n" +
            "      \"path\": \"js/main.js\",\n" +
            "      \"sha\": \"eb6b02b80145c7f057c5d1d54a39fbda983d219b\",\n" +
            "      \"url\": \"https://api.github.com/repositories/14937448/contents/js/main.js?ref=9cf4bb2cb2132bdc446fcdf54219f0a2f6cb0569\",\n" +
            "      \"git_url\": \"https://api.github.com/repositories/14937448/git/blobs/eb6b02b80145c7f057c5d1d54a39fbda983d219b\",\n" +
            "      \"html_url\": \"https://github.com/abc/kieran-coursework-js/blob/9cf4bb2cb2132bdc446fcdf54219f0a2f6cb0569/js/main.js\",\n" +
            "      \"repository\": {\n" +
            "        \"id\": 14937448,\n" +
            "        \"name\": \"kieran-coursework-js\",\n" +
            "        \"full_name\": \"abc/kieran-coursework-js\",\n" +
            "        \"owner\": {\n" +
            "          \"login\": \"abc\",\n" +
            "          \"id\": 3063240,\n" +
            "          \"avatar_url\": \"https://avatars.githubusercontent.com/u/3063240?v=3\",\n" +
            "          \"gravatar_id\": \"\",\n" +
            "          \"url\": \"https://api.github.com/users/abc\",\n" +
            "          \"html_url\": \"https://github.com/abc\",\n" +
            "          \"followers_url\": \"https://api.github.com/users/abc/followers\",\n" +
            "          \"following_url\": \"https://api.github.com/users/abc/following{/other_user}\",\n" +
            "          \"gists_url\": \"https://api.github.com/users/abc/gists{/gist_id}\",\n" +
            "          \"starred_url\": \"https://api.github.com/users/abc/starred{/owner}{/repo}\",\n" +
            "          \"subscriptions_url\": \"https://api.github.com/users/abc/subscriptions\",\n" +
            "          \"organizations_url\": \"https://api.github.com/users/abc/orgs\",\n" +
            "          \"repos_url\": \"https://api.github.com/users/abc/repos\",\n" +
            "          \"events_url\": \"https://api.github.com/users/abc/events{/privacy}\",\n" +
            "          \"received_events_url\": \"https://api.github.com/users/abc/received_events\",\n" +
            "          \"type\": \"User\",\n" +
            "          \"site_admin\": false\n" +
            "        },\n" +
            "        \"private\": false,\n" +
            "        \"html_url\": \"https://github.com/abc/kieran-coursework-js\",\n" +
            "        \"description\": \"Kieran and Grant's HTML5 coursework\",\n" +
            "        \"fork\": false,\n" +
            "        \"url\": \"https://api.github.com/repos/abc/kieran-coursework-js\",\n" +
            "        \"forks_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/forks\",\n" +
            "        \"keys_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/keys{/key_id}\",\n" +
            "        \"collaborators_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/collaborators{/collaborator}\",\n" +
            "        \"teams_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/teams\",\n" +
            "        \"hooks_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/hooks\",\n" +
            "        \"issue_events_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/issues/events{/number}\",\n" +
            "        \"events_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/events\",\n" +
            "        \"assignees_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/assignees{/user}\",\n" +
            "        \"branches_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/branches{/branch}\",\n" +
            "        \"tags_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/tags\",\n" +
            "        \"blobs_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/git/blobs{/sha}\",\n" +
            "        \"git_tags_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/git/tags{/sha}\",\n" +
            "        \"git_refs_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/git/refs{/sha}\",\n" +
            "        \"trees_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/git/trees{/sha}\",\n" +
            "        \"statuses_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/statuses/{sha}\",\n" +
            "        \"languages_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/languages\",\n" +
            "        \"stargazers_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/stargazers\",\n" +
            "        \"contributors_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/contributors\",\n" +
            "        \"subscribers_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/subscribers\",\n" +
            "        \"subscription_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/subscription\",\n" +
            "        \"commits_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/commits{/sha}\",\n" +
            "        \"git_commits_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/git/commits{/sha}\",\n" +
            "        \"comments_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/comments{/number}\",\n" +
            "        \"issue_comment_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/issues/comments{/number}\",\n" +
            "        \"contents_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/contents/{+path}\",\n" +
            "        \"compare_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/compare/{base}...{head}\",\n" +
            "        \"merges_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/merges\",\n" +
            "        \"archive_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/{archive_format}{/ref}\",\n" +
            "        \"downloads_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/downloads\",\n" +
            "        \"issues_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/issues{/number}\",\n" +
            "        \"pulls_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/pulls{/number}\",\n" +
            "        \"milestones_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/milestones{/number}\",\n" +
            "        \"notifications_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/notifications{?since,all,participating}\",\n" +
            "        \"labels_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/labels{/name}\",\n" +
            "        \"releases_url\": \"https://api.github.com/repos/abc/kieran-coursework-js/releases{/id}\"\n" +
            "      },\n" +
            "      \"score\": 0.18969063\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"application.js\",\n" +
            "      \"path\": \"js/application.js\",\n" +
            "      \"sha\": \"c43d40a6ac889ebe27cfcf0ff53efe6267804b18\",\n" +
            "      \"url\": \"https://api.github.com/repositories/15045599/contents/js/application.js?ref=17c6125fdccb4530f9a10bd870b20cae9ef2fc63\",\n" +
            "      \"git_url\": \"https://api.github.com/repositories/15045599/git/blobs/c43d40a6ac889ebe27cfcf0ff53efe6267804b18\",\n" +
            "      \"html_url\": \"https://github.com/abc/kontakt/blob/17c6125fdccb4530f9a10bd870b20cae9ef2fc63/js/application.js\",\n" +
            "      \"repository\": {\n" +
            "        \"id\": 15045599,\n" +
            "        \"name\": \"kontakt\",\n" +
            "        \"full_name\": \"abc/kontakt\",\n" +
            "        \"owner\": {\n" +
            "          \"login\": \"abc\",\n" +
            "          \"id\": 3063240,\n" +
            "          \"avatar_url\": \"https://avatars.githubusercontent.com/u/3063240?v=3\",\n" +
            "          \"gravatar_id\": \"\",\n" +
            "          \"url\": \"https://api.github.com/users/abc\",\n" +
            "          \"html_url\": \"https://github.com/abc\",\n" +
            "          \"followers_url\": \"https://api.github.com/users/abc/followers\",\n" +
            "          \"following_url\": \"https://api.github.com/users/abc/following{/other_user}\",\n" +
            "          \"gists_url\": \"https://api.github.com/users/abc/gists{/gist_id}\",\n" +
            "          \"starred_url\": \"https://api.github.com/users/abc/starred{/owner}{/repo}\",\n" +
            "          \"subscriptions_url\": \"https://api.github.com/users/abc/subscriptions\",\n" +
            "          \"organizations_url\": \"https://api.github.com/users/abc/orgs\",\n" +
            "          \"repos_url\": \"https://api.github.com/users/abc/repos\",\n" +
            "          \"events_url\": \"https://api.github.com/users/abc/events{/privacy}\",\n" +
            "          \"received_events_url\": \"https://api.github.com/users/abc/received_events\",\n" +
            "          \"type\": \"User\",\n" +
            "          \"site_admin\": false\n" +
            "        },\n" +
            "        \"private\": false,\n" +
            "        \"html_url\": \"https://github.com/abc/kontakt\",\n" +
            "        \"description\": \"cheaa\",\n" +
            "        \"fork\": false,\n" +
            "        \"url\": \"https://api.github.com/repos/abc/kontakt\",\n" +
            "        \"forks_url\": \"https://api.github.com/repos/abc/kontakt/forks\",\n" +
            "        \"keys_url\": \"https://api.github.com/repos/abc/kontakt/keys{/key_id}\",\n" +
            "        \"collaborators_url\": \"https://api.github.com/repos/abc/kontakt/collaborators{/collaborator}\",\n" +
            "        \"teams_url\": \"https://api.github.com/repos/abc/kontakt/teams\",\n" +
            "        \"hooks_url\": \"https://api.github.com/repos/abc/kontakt/hooks\",\n" +
            "        \"issue_events_url\": \"https://api.github.com/repos/abc/kontakt/issues/events{/number}\",\n" +
            "        \"events_url\": \"https://api.github.com/repos/abc/kontakt/events\",\n" +
            "        \"assignees_url\": \"https://api.github.com/repos/abc/kontakt/assignees{/user}\",\n" +
            "        \"branches_url\": \"https://api.github.com/repos/abc/kontakt/branches{/branch}\",\n" +
            "        \"tags_url\": \"https://api.github.com/repos/abc/kontakt/tags\",\n" +
            "        \"blobs_url\": \"https://api.github.com/repos/abc/kontakt/git/blobs{/sha}\",\n" +
            "        \"git_tags_url\": \"https://api.github.com/repos/abc/kontakt/git/tags{/sha}\",\n" +
            "        \"git_refs_url\": \"https://api.github.com/repos/abc/kontakt/git/refs{/sha}\",\n" +
            "        \"trees_url\": \"https://api.github.com/repos/abc/kontakt/git/trees{/sha}\",\n" +
            "        \"statuses_url\": \"https://api.github.com/repos/abc/kontakt/statuses/{sha}\",\n" +
            "        \"languages_url\": \"https://api.github.com/repos/abc/kontakt/languages\",\n" +
            "        \"stargazers_url\": \"https://api.github.com/repos/abc/kontakt/stargazers\",\n" +
            "        \"contributors_url\": \"https://api.github.com/repos/abc/kontakt/contributors\",\n" +
            "        \"subscribers_url\": \"https://api.github.com/repos/abc/kontakt/subscribers\",\n" +
            "        \"subscription_url\": \"https://api.github.com/repos/abc/kontakt/subscription\",\n" +
            "        \"commits_url\": \"https://api.github.com/repos/abc/kontakt/commits{/sha}\",\n" +
            "        \"git_commits_url\": \"https://api.github.com/repos/abc/kontakt/git/commits{/sha}\",\n" +
            "        \"comments_url\": \"https://api.github.com/repos/abc/kontakt/comments{/number}\",\n" +
            "        \"issue_comment_url\": \"https://api.github.com/repos/abc/kontakt/issues/comments{/number}\",\n" +
            "        \"contents_url\": \"https://api.github.com/repos/abc/kontakt/contents/{+path}\",\n" +
            "        \"compare_url\": \"https://api.github.com/repos/abc/kontakt/compare/{base}...{head}\",\n" +
            "        \"merges_url\": \"https://api.github.com/repos/abc/kontakt/merges\",\n" +
            "        \"archive_url\": \"https://api.github.com/repos/abc/kontakt/{archive_format}{/ref}\",\n" +
            "        \"downloads_url\": \"https://api.github.com/repos/abc/kontakt/downloads\",\n" +
            "        \"issues_url\": \"https://api.github.com/repos/abc/kontakt/issues{/number}\",\n" +
            "        \"pulls_url\": \"https://api.github.com/repos/abc/kontakt/pulls{/number}\",\n" +
            "        \"milestones_url\": \"https://api.github.com/repos/abc/kontakt/milestones{/number}\",\n" +
            "        \"notifications_url\": \"https://api.github.com/repos/abc/kontakt/notifications{?since,all,participating}\",\n" +
            "        \"labels_url\": \"https://api.github.com/repos/abc/kontakt/labels{/name}\",\n" +
            "        \"releases_url\": \"https://api.github.com/repos/abc/kontakt/releases{/id}\"\n" +
            "      },\n" +
            "      \"score\": 0.13306919\n" +
            "    }\n" +
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

        String parameter = "user:zhang java";
        assertThat(gitHubClient.search(parameter).getItems().size(), is(0));
    }

    @Test
    public void shouldReturnNormalSearchResult() throws IOException, URISyntaxException {
        when(httpClient.execute(any(HttpGet.class))).thenReturn(response);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(200);
        when(response.getEntity()).thenReturn(entity);
        when(entity.getContent()).thenReturn(IOUtils.toInputStream(normalResults));

        String parameter = "user:bill java";
        assertThat(gitHubClient.search(parameter).getItems().size(), is(3));
    }

    @Test(expected = IOException.class)
    public void shouldThrowIOException() throws IOException, URISyntaxException {
        when(httpClient.execute(any(HttpGet.class))).thenThrow(IOException.class);

        gitHubClient.search("whatever");
    }
}
