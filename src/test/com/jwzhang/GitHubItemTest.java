package com.jwzhang;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GitHubItemTest {

    private Gson gson = new Gson();
    private GitHubItem gitHubItem;
    private final Double PRECISION = 0.000001;

    @Before
    public void setUp() {

        String item = "{\n" +
            "      \"name\": \"hello.cpp\",\n" +
            "      \"path\": \"poj/hello.cpp\",\n" +
            "      \"sha\": \"4c923320c04731ff521232313118223e9c\",\n" +
            "      \"url\": \"https://api.github.com/repositories/7223628/contents/poj/hello.cpp?ref=d33c0a66d248d3b53189f3bb8615f93a02d30e7c\",\n" +
            "      \"git_url\": \"https://api.github.com/repositories/7223628/git/blobs/4c92331ff52721e40c9bccde408223e9c\",\n" +
            "      \"html_url\": \"https://github.com/test/algorithm/blob/d33c0a66d248d3b531890e7c/poj/hello.cpp\",\n" +
            "      \"repository\": {\n" +
            "        \"id\": 12345,\n" +
            "        \"name\": \"algorithm\",\n" +
            "        \"full_name\": \"test/algorithm\",\n" +
            "        \"owner\": {\n" +
            "          \"login\": \"test\",\n" +
            "          \"id\": 12345,\n" +
            "          \"avatar_url\": \"https://avatars.githubusercontent.com/u/123456?v=3\",\n" +
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
            "        \"html_url\": \"https://github.com/test/algorithm\",\n" +
            "        \"description\": \"some practices with simple algorithm in c language\",\n" +
            "        \"fork\": false,\n" +
            "        \"url\": \"https://api.github.com/repos/test/algorithm\",\n" +
            "        \"forks_url\": \"https://api.github.com/repos/test/algorithm/forks\",\n" +
            "        \"keys_url\": \"https://api.github.com/repos/test/algorithm/keys{/key_id}\",\n" +
            "        \"collaborators_url\": \"https://api.github.com/repos/test/algorithm/collaborators{/collaborator}\",\n" +
            "        \"teams_url\": \"https://api.github.com/repos/test/algorithm/teams\",\n" +
            "        \"hooks_url\": \"https://api.github.com/repos/test/algorithm/hooks\",\n" +
            "        \"issue_events_url\": \"https://api.github.com/repos/test/algorithm/issues/events{/number}\",\n" +
            "        \"events_url\": \"https://api.github.com/repos/test/algorithm/events\",\n" +
            "        \"assignees_url\": \"https://api.github.com/repos/test/algorithm/assignees{/user}\",\n" +
            "        \"branches_url\": \"https://api.github.com/repos/test/algorithm/branches{/branch}\",\n" +
            "        \"tags_url\": \"https://api.github.com/repos/test/algorithm/tags\",\n" +
            "        \"blobs_url\": \"https://api.github.com/repos/test/algorithm/git/blobs{/sha}\",\n" +
            "        \"git_tags_url\": \"https://api.github.com/repos/test/algorithm/git/tags{/sha}\",\n" +
            "        \"git_refs_url\": \"https://api.github.com/repos/test/algorithm/git/refs{/sha}\",\n" +
            "        \"trees_url\": \"https://api.github.com/repos/test/algorithm/git/trees{/sha}\",\n" +
            "        \"statuses_url\": \"https://api.github.com/repos/test/algorithm/statuses/{sha}\",\n" +
            "        \"languages_url\": \"https://api.github.com/repos/test/algorithm/languages\",\n" +
            "        \"stargazers_url\": \"https://api.github.com/repos/test/algorithm/stargazers\",\n" +
            "        \"contributors_url\": \"https://api.github.com/repos/test/algorithm/contributors\",\n" +
            "        \"subscribers_url\": \"https://api.github.com/repos/test/algorithm/subscribers\",\n" +
            "        \"subscription_url\": \"https://api.github.com/repos/test/algorithm/subscription\",\n" +
            "        \"commits_url\": \"https://api.github.com/repos/test/algorithm/commits{/sha}\",\n" +
            "        \"git_commits_url\": \"https://api.github.com/repos/test/algorithm/git/commits{/sha}\",\n" +
            "        \"comments_url\": \"https://api.github.com/repos/test/algorithm/comments{/number}\",\n" +
            "        \"issue_comment_url\": \"https://api.github.com/repos/test/algorithm/issues/comments{/number}\",\n" +
            "        \"contents_url\": \"https://api.github.com/repos/test/algorithm/contents/{+path}\",\n" +
            "        \"compare_url\": \"https://api.github.com/repos/test/algorithm/compare/{base}...{head}\",\n" +
            "        \"merges_url\": \"https://api.github.com/repos/test/algorithm/merges\",\n" +
            "        \"archive_url\": \"https://api.github.com/repos/test/algorithm/{archive_format}{/ref}\",\n" +
            "        \"downloads_url\": \"https://api.github.com/repos/test/algorithm/downloads\",\n" +
            "        \"issues_url\": \"https://api.github.com/repos/test/algorithm/issues{/number}\",\n" +
            "        \"pulls_url\": \"https://api.github.com/repos/test/algorithm/pulls{/number}\",\n" +
            "        \"milestones_url\": \"https://api.github.com/repos/test/algorithm/milestones{/number}\",\n" +
            "        \"notifications_url\": \"https://api.github.com/repos/test/algorithm/notifications{?since,all,participating}\",\n" +
            "        \"labels_url\": \"https://api.github.com/repos/test/algorithm/labels{/name}\",\n" +
            "        \"releases_url\": \"https://api.github.com/repos/test/algorithm/releases{/id}\"\n" +
            "      },\n" +
            "      \"score\": 1.3298683\n" +
            "    }";

        gitHubItem = gson.fromJson(item, GitHubItem.class);
    }

    @Test
    public void shouldParseNameCorrectly() {
        assertThat(gitHubItem.getName(), is("hello.cpp"));
    }

    @Test
    public void shouldParsePathCorrectly() {
        assertThat(gitHubItem.getPath(), is("poj/hello.cpp"));
    }

    @Test
    public void shouldParseHtmlUrlCorrectly() {
        assertThat(gitHubItem.getHtmlUrl(), is("https://github.com/test/algorithm/blob/d33c0a66d248d3b531890e7c/poj/hello.cpp"));
    }

    @Test
    public void shouldGetFullNameFromRepositoryCorrectly() {
        assertThat(gitHubItem.getRepo().getFullName(), is("test/algorithm"));
    }

    @Test
    public void shouldGetLoginFromOwnerCorrectly() {
        assertThat(gitHubItem.getRepo().getOwner().getLogin(), is("test"));
    }

    @Test
    public void shouldParseScoreCorrectly() {
        assertThat(gitHubItem.getScore(), closeTo(1.3298683, PRECISION));
    }
}