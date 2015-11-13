package com.jwzhang.io;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CSVInputParserTest {
    private CSVInputParser parser = new CSVInputParser();

    @Test
    public void shouldGetTwoGitHubAccountAfterReadInput() {
        String fileName = this.getClass().getClassLoader().getResource("test-1.csv").getFile();
        assertThat(parser.parse(fileName).size(), is(1));
    }

    @Test
    public void shouldGetEmptyGitHubAccountWhenNoRecordsLeft() {
        String fileName = this.getClass().getClassLoader().getResource("test-0.csv").getFile();
        assertThat(parser.parse(fileName).size(), is(0));
    }

    @Test
    public void shouldParseSixRecordsFromCSVFile() {
        String fileName = this.getClass().getClassLoader().getResource("test.csv").getFile();
        assertThat(parser.parse(fileName).size(), is(5));
    }
}
