package com.jwzhang;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GitHubOwnerTest {

    private Gson gson = new Gson();
    private GitHubOwner parsedOwner;

    @Before
    public void setup() {
        String owner = "{\n" +
                "          \"login\": \"test\",\n" +
                "          \"id\": 1234,\n" +
                "          \"avatar_url\": \"https://avatars.githubusercontent.com/u/9111?v=3\",\n" +
                "          \"gravatar_id\": \"\",\n" +
                "          \"url\": \"https://api.github.com/users/test\",\n" +
                "          \"html_url\": \"https://github.com/iambowen\",\n" +
                "          \"followers_url\": \"https://api.github.com/users/iambowen/followers\",\n" +
                "          \"following_url\": \"https://api.github.com/users/iambowen/following{/other_user}\",\n" +
                "          \"gists_url\": \"https://api.github.com/users/iambowen/gists{/gist_id}\",\n" +
                "          \"starred_url\": \"https://api.github.com/users/test/starred{/owner}{/repo}\",\n" +
                "          \"subscriptions_url\": \"https://api.github.com/users/test/subscriptions\",\n" +
                "          \"organizations_url\": \"https://api.github.com/users/test/orgs\",\n" +
                "          \"repos_url\": \"https://api.github.com/users/test/repos\",\n" +
                "          \"events_url\": \"https://api.github.com/users/test/events{/privacy}\",\n" +
                "          \"received_events_url\": \"https://api.github.com/users/test/received_events\",\n" +
                "          \"type\": \"User\",\n" +
                "          \"site_admin\": false \n" +
                "}";
        parsedOwner = gson.fromJson(owner, GitHubOwner.class);
    }

    @Test
    public void shouldParseGitHubOwnerCorrectly() {
        assertThat(parsedOwner.getLogin(), is("test"));
    }

    @Test
    public void shouldParseSiteAdminCorrectly() {
        assertThat(parsedOwner.getSiteAdmin(), is(false));
    }

    @Test
    public void shouldParseUrlCorrectly() {
        assertThat(parsedOwner.getUrl(), is("https://api.github.com/users/test"));
    }
}
