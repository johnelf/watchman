package com.jwzhang.io;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CSVInputParserTest {
    private CSVInputParser parser = new CSVInputParser();

    @Test
    public void shouldGetOneGitHubAccountAfterReadInput() {
        String fileName = this.getClass().getClassLoader().getResource("test-1.csv").getFile();
        assertThat(parser.parse(fileName).size(), is(2));
    }
}
