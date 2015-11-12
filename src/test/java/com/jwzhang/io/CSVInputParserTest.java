package com.jwzhang.io;

import org.junit.Test;

import static org.junit.Assert.assertThat;

public class CSVInputParserTest {
    @Test
    public void shouldGetOneGitHubAccountAfterReadInput() {
        assertThat(parser.parse());
    }
}
