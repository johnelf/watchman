package com.jwzhang.model.github;

import com.google.gson.Gson;
import com.jwzhang.model.github.GitHubSearchResult;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GitHubSearchResultTest {

    private Gson gson = new Gson();
    private GitHubSearchResult emptyGitHubSearchResult;
    private GitHubSearchResult normalGitHubSearchResult;

    @Before
    public void setup() {
        String emptySearchResults = "{\n" +
            "  \"total_count\": 0,\n" +
            "  \"incomplete_results\": false,\n" +
            "  \"items\": [\n" +
            "    \n" +
            "  ]\n" +
            "}";
        String normalSearchResults = "{\n" +
            "  \"total_count\": 2,\n" +
            "  \"incomplete_results\": false,\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"name\": \"TweetData.scala\",\n" +
            "      \"path\": \"3/src/main/scala/objsets/TweetData.scala\",\n" +
            "      \"sha\": \"8af9e68f4518e969d7413783e9\",\n" +
            "      \"url\": \"https://api.github.com/repositories/20009012/contents/3/src/main/scala/objsets/TweetData.scala?ref=83449ffa7b7b44991843b126661\",\n" +
            "      \"git_url\": \"https://api.github.com/repositories/20009012/git/blobs/8af9e68f451817bc63783e9\",\n" +
            "      \"html_url\": \"https://github.com/test/abc-functional-programming/blob/83449ffa7b7be4499fa3b126661/3/src/main/scala/objsets/TweetData.scala\",\n" +
            "      \"repository\": {\n" +
            "        \"id\": 654321,\n" +
            "        \"name\": \"functional-programming\",\n" +
            "        \"full_name\": \"test/abc-functional-programming\",\n" +
            "        \"owner\": {\n" +
            "          \"login\": \"test\",\n" +
            "          \"id\": 123231,\n" +
            "          \"avatar_url\": \"https://avatars.githubusercontent.com/u/123231?v=3\",\n" +
            "          \"gravatar_id\": \"\",\n" +
            "          \"url\": \"https://api.github.com/users/test\",\n" +
            "          \"html_url\": \"https://github.com/test\",\n" +
            "          \"followers_url\": \"https://api.github.com/users/test/followers\",\n" +
            "          \"following_url\": \"https://api.github.com/users/test/following{/other_user}\",\n" +
            "          \"gists_url\": \"https://api.github.com/users/test/gists{/gist_id}\",\n" +
            "          \"starred_url\": \"https://api.github.com/users/test/starred{/owner}{/repo}\",\n" +
            "          \"subscriptions_url\": \"https://api.github.com/users/test/subscriptions\",\n" +
            "          \"organizations_url\": \"https://api.github.com/users/test/orgs\",\n" +
            "          \"repos_url\": \"https://api.github.com/users/test/repos\",\n" +
            "          \"events_url\": \"https://api.github.com/users/test/events{/privacy}\",\n" +
            "          \"received_events_url\": \"https://api.github.com/users/test/received_events\",\n" +
            "          \"type\": \"User\",\n" +
            "          \"site_admin\": false\n" +
            "        },\n" +
            "        \"private\": false,\n" +
            "        \"html_url\": \"https://github.com/test/abc-functional-programming\",\n" +
            "        \"description\": \"\",\n" +
            "        \"fork\": false,\n" +
            "        \"url\": \"https://api.github.com/repos/test/abc-functional-programming\",\n" +
            "        \"forks_url\": \"https://api.github.com/repos/test/abc-functional-programming/forks\",\n" +
            "        \"keys_url\": \"https://api.github.com/repos/test/abc-functional-programming/keys{/key_id}\",\n" +
            "        \"collaborators_url\": \"https://api.github.com/repos/test/abc-functional-programming/collaborators{/collaborator}\",\n" +
            "        \"teams_url\": \"https://api.github.com/repos/test/abc-functional-programming/teams\",\n" +
            "        \"hooks_url\": \"https://api.github.com/repos/test/abc-functional-programming/hooks\",\n" +
            "        \"issue_events_url\": \"https://api.github.com/repos/test/abc-functional-programming/issues/events{/number}\",\n" +
            "        \"events_url\": \"https://api.github.com/repos/test/abc-functional-programming/events\",\n" +
            "        \"assignees_url\": \"https://api.github.com/repos/test/abc-functional-programming/assignees{/user}\",\n" +
            "        \"branches_url\": \"https://api.github.com/repos/test/abc-functional-programming/branches{/branch}\",\n" +
            "        \"tags_url\": \"https://api.github.com/repos/test/abc-functional-programming/tags\",\n" +
            "        \"blobs_url\": \"https://api.github.com/repos/test/abc-functional-programming/git/blobs{/sha}\",\n" +
            "        \"git_tags_url\": \"https://api.github.com/repos/test/abc-functional-programming/git/tags{/sha}\",\n" +
            "        \"git_refs_url\": \"https://api.github.com/repos/test/abc-functional-programming/git/refs{/sha}\",\n" +
            "        \"trees_url\": \"https://api.github.com/repos/test/abc-functional-programming/git/trees{/sha}\",\n" +
            "        \"statuses_url\": \"https://api.github.com/repos/test/abc-functional-programming/statuses/{sha}\",\n" +
            "        \"languages_url\": \"https://api.github.com/repos/test/abc-functional-programming/languages\",\n" +
            "        \"stargazers_url\": \"https://api.github.com/repos/test/abc-functional-programming/stargazers\",\n" +
            "        \"contributors_url\": \"https://api.github.com/repos/test/abc-functional-programming/contributors\",\n" +
            "        \"subscribers_url\": \"https://api.github.com/repos/test/abc-functional-programming/subscribers\",\n" +
            "        \"subscription_url\": \"https://api.github.com/repos/test/abc-functional-programming/subscription\",\n" +
            "        \"commits_url\": \"https://api.github.com/repos/test/abc-functional-programming/commits{/sha}\",\n" +
            "        \"git_commits_url\": \"https://api.github.com/repos/test/abc-functional-programming/git/commits{/sha}\",\n" +
            "        \"comments_url\": \"https://api.github.com/repos/test/abc-functional-programming/comments{/number}\",\n" +
            "        \"issue_comment_url\": \"https://api.github.com/repos/test/abc-functional-programming/issues/comments{/number}\",\n" +
            "        \"contents_url\": \"https://api.github.com/repos/test/abc-functional-programming/contents/{+path}\",\n" +
            "        \"compare_url\": \"https://api.github.com/repos/test/abc-functional-programming/compare/{base}...{head}\",\n" +
            "        \"merges_url\": \"https://api.github.com/repos/test/abc-functional-programming/merges\",\n" +
            "        \"archive_url\": \"https://api.github.com/repos/test/abc-functional-programming/{archive_format}{/ref}\",\n" +
            "        \"downloads_url\": \"https://api.github.com/repos/test/abc-functional-programming/downloads\",\n" +
            "        \"issues_url\": \"https://api.github.com/repos/test/abc-functional-programming/issues{/number}\",\n" +
            "        \"pulls_url\": \"https://api.github.com/repos/test/abc-functional-programming/pulls{/number}\",\n" +
            "        \"milestones_url\": \"https://api.github.com/repos/test/abc-functional-programming/milestones{/number}\",\n" +
            "        \"notifications_url\": \"https://api.github.com/repos/test/abc-functional-programming/notifications{?since,all,participating}\",\n" +
            "        \"labels_url\": \"https://api.github.com/repos/test/abc-functional-programming/labels{/name}\",\n" +
            "        \"releases_url\": \"https://api.github.com/repos/test/abc-functional-programming/releases{/id}\"\n" +
            "      },\n" +
            "      \"score\": 0.07005133\n" +
            "    },\n" +
            "    {\n" +
            "      \"name\": \"HelloWorldServlet.scala\",\n" +
            "      \"path\": \"src/main/scala/com/example/HelloWorldServlet.scala\",\n" +
            "      \"sha\": \"35d5bc170307d1cb0bdc0c118b5d87\",\n" +
            "      \"url\": \"https://api.github.com/repositories/18034473/contents/src/main/scala/com/example/HelloWorldServlet.scala?ref=faa2a088ec733d33905f\",\n" +
            "      \"git_url\": \"https://api.github.com/repositories/18034473/git/blobs/35d5bc170307ea86233b0bdc0c118b5d87\",\n" +
            "      \"html_url\": \"https://github.com/test/scalatra/blob/faa2a088ec733d33908152624cec7305f/src/main/scala/com/example/HelloWorldServlet.scala\",\n" +
            "      \"repository\": {\n" +
            "        \"id\": 4321,\n" +
            "        \"name\": \"scalatra\",\n" +
            "        \"full_name\": \"test/scalatra\",\n" +
            "        \"owner\": {\n" +
            "          \"login\": \"test\",\n" +
            "          \"id\": 123231,\n" +
            "          \"avatar_url\": \"https://avatars.githubusercontent.com/u/123231?v=3\",\n" +
            "          \"gravatar_id\": \"\",\n" +
            "          \"url\": \"https://api.github.com/users/test\",\n" +
            "          \"html_url\": \"https://github.com/test\",\n" +
            "          \"followers_url\": \"https://api.github.com/users/test/followers\",\n" +
            "          \"following_url\": \"https://api.github.com/users/test/following{/other_user}\",\n" +
            "          \"gists_url\": \"https://api.github.com/users/test/gists{/gist_id}\",\n" +
            "          \"starred_url\": \"https://api.github.com/users/test/starred{/owner}{/repo}\",\n" +
            "          \"subscriptions_url\": \"https://api.github.com/users/test/subscriptions\",\n" +
            "          \"organizations_url\": \"https://api.github.com/users/test/orgs\",\n" +
            "          \"repos_url\": \"https://api.github.com/users/test/repos\",\n" +
            "          \"events_url\": \"https://api.github.com/users/test/events{/privacy}\",\n" +
            "          \"received_events_url\": \"https://api.github.com/users/test/received_events\",\n" +
            "          \"type\": \"User\",\n" +
            "          \"site_admin\": false\n" +
            "        },\n" +
            "        \"private\": false,\n" +
            "        \"html_url\": \"https://github.com/test/scalatra\",\n" +
            "        \"description\": \"first project for scalatra\",\n" +
            "        \"fork\": false,\n" +
            "        \"url\": \"https://api.github.com/repos/test/scalatra\",\n" +
            "        \"forks_url\": \"https://api.github.com/repos/test/scalatra/forks\",\n" +
            "        \"keys_url\": \"https://api.github.com/repos/test/scalatra/keys{/key_id}\",\n" +
            "        \"collaborators_url\": \"https://api.github.com/repos/test/scalatra/collaborators{/collaborator}\",\n" +
            "        \"teams_url\": \"https://api.github.com/repos/test/scalatra/teams\",\n" +
            "        \"hooks_url\": \"https://api.github.com/repos/test/scalatra/hooks\",\n" +
            "        \"issue_events_url\": \"https://api.github.com/repos/test/scalatra/issues/events{/number}\",\n" +
            "        \"events_url\": \"https://api.github.com/repos/test/scalatra/events\",\n" +
            "        \"assignees_url\": \"https://api.github.com/repos/test/scalatra/assignees{/user}\",\n" +
            "        \"branches_url\": \"https://api.github.com/repos/test/scalatra/branches{/branch}\",\n" +
            "        \"tags_url\": \"https://api.github.com/repos/test/scalatra/tags\",\n" +
            "        \"blobs_url\": \"https://api.github.com/repos/test/scalatra/git/blobs{/sha}\",\n" +
            "        \"git_tags_url\": \"https://api.github.com/repos/test/scalatra/git/tags{/sha}\",\n" +
            "        \"git_refs_url\": \"https://api.github.com/repos/test/scalatra/git/refs{/sha}\",\n" +
            "        \"trees_url\": \"https://api.github.com/repos/test/scalatra/git/trees{/sha}\",\n" +
            "        \"statuses_url\": \"https://api.github.com/repos/test/scalatra/statuses/{sha}\",\n" +
            "        \"languages_url\": \"https://api.github.com/repos/test/scalatra/languages\",\n" +
            "        \"stargazers_url\": \"https://api.github.com/repos/test/scalatra/stargazers\",\n" +
            "        \"contributors_url\": \"https://api.github.com/repos/test/scalatra/contributors\",\n" +
            "        \"subscribers_url\": \"https://api.github.com/repos/test/scalatra/subscribers\",\n" +
            "        \"subscription_url\": \"https://api.github.com/repos/test/scalatra/subscription\",\n" +
            "        \"commits_url\": \"https://api.github.com/repos/test/scalatra/commits{/sha}\",\n" +
            "        \"git_commits_url\": \"https://api.github.com/repos/test/scalatra/git/commits{/sha}\",\n" +
            "        \"comments_url\": \"https://api.github.com/repos/test/scalatra/comments{/number}\",\n" +
            "        \"issue_comment_url\": \"https://api.github.com/repos/test/scalatra/issues/comments{/number}\",\n" +
            "        \"contents_url\": \"https://api.github.com/repos/test/scalatra/contents/{+path}\",\n" +
            "        \"compare_url\": \"https://api.github.com/repos/test/scalatra/compare/{base}...{head}\",\n" +
            "        \"merges_url\": \"https://api.github.com/repos/test/scalatra/merges\",\n" +
            "        \"archive_url\": \"https://api.github.com/repos/test/scalatra/{archive_format}{/ref}\",\n" +
            "        \"downloads_url\": \"https://api.github.com/repos/test/scalatra/downloads\",\n" +
            "        \"issues_url\": \"https://api.github.com/repos/test/scalatra/issues{/number}\",\n" +
            "        \"pulls_url\": \"https://api.github.com/repos/test/scalatra/pulls{/number}\",\n" +
            "        \"milestones_url\": \"https://api.github.com/repos/test/scalatra/milestones{/number}\",\n" +
            "        \"notifications_url\": \"https://api.github.com/repos/test/scalatra/notifications{?since,all,participating}\",\n" +
            "        \"labels_url\": \"https://api.github.com/repos/test/scalatra/labels{/name}\",\n" +
            "        \"releases_url\": \"https://api.github.com/repos/test/scalatra/releases{/id}\"\n" +
            "      },\n" +
            "      \"score\": 1.0664003\n" +
            "    }\n" +
            "  ]\n" +
            "}";

        normalGitHubSearchResult = gson.fromJson(normalSearchResults, GitHubSearchResult.class);
        emptyGitHubSearchResult = gson.fromJson(emptySearchResults, GitHubSearchResult.class);
    }

    @Test
    public void shouldParseTotalCountCorrectlyForNormalResults() {
        assertThat(normalGitHubSearchResult.getTotalCounts(), is(2));
    }

    @Test
    public void shouldParseTotalCountCorrectlyForEmptyResults() {
        assertThat(emptyGitHubSearchResult.getTotalCounts(), is(0));
    }

    @Test
    public void shouldParseIncompleteResultsCorrectly() {
        assertThat(emptyGitHubSearchResult.getIncompleteResults(), is(false));
    }

    @Test
    public void shouldParseItemSizeCorrectly() {
        assertThat(emptyGitHubSearchResult.getItems().size(), is(0));
    }

    @Test
    public void shouldReturnCorrespondingSizeForItemSize() {
        assertThat(normalGitHubSearchResult.getItems().size(), is(2));
    }

    @Test
    public void shouldGetRightNameForTheFirstItem() {
        assertThat(normalGitHubSearchResult.getItems().get(0).getName(), is("TweetData.scala"));
    }

}