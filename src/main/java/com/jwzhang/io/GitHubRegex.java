package com.jwzhang.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GitHubRegex {
    public String extractUserName(String input) {
        Pattern compile = Pattern.compile("^https?://github.com/([^/]+)/?");
        Matcher matcher = compile.matcher(input);

        return matcher.matches() ? matcher.group(1) : "";
    }
}
