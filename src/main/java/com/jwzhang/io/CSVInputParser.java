package com.jwzhang.io;

import com.google.common.collect.Lists;
import com.jwzhang.model.user.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVInputParser {

    private CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader("Name","Github Account");
    private final String NAME = "Name";
    private final String ACCOUNT = "Github Account";

    public List<User> parse(String fileName) {
        List<User> result = Lists.newArrayList();
        try {
            FileReader fileReader = new FileReader(fileName);
            new CSVParser(fileReader, csvFileFormat).getRecords().forEach(record -> addToRecordSet(record, result));
            result.remove(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void addToRecordSet(CSVRecord record, List<User> list) {
        list.add(new User(record.get(NAME), record.get(ACCOUNT)));
    }
}
