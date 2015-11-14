package com.jwzhang.io;

import org.junit.Before;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GitHubRegexTest {

    private GitHubRegex gitHubRegex;

    @Before
    public void setup() {
        gitHubRegex = new GitHubRegex();
    }

    @Test
    public void shouldGetGitHubUser() {
        String input = "http://github.com/users";

        assertThat(gitHubRegex.extractUserName(input), is("users"));
    }

    @Test
    public void shouldGetGitHubUserWithSlash() {
        String input = "http://github.com/users/";

        assertThat(gitHubRegex.extractUserName(input), is("users"));
    }

    @Test
    public void shouldGetEmpty() {
        String input = "https://github.com/";

        assertThat(gitHubRegex.extractUserName(input), is(""));
    }

    @Test
    public void shouldGetEmptyWhenInvalid() {
        String input = "https://github.com//";

        assertThat(gitHubRegex.extractUserName(input), is(""));
    }
}
