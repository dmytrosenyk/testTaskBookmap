package org.senyk.bookmap.v1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRepRead {
    private String file;

    public FileRepRead(String file) {
        this.file = file;
    }
    public List<String> readLines() throws IOException {
        List<String> lines = new ArrayList<>();;
        FileReader fr = new FileReader(this.file);
        BufferedReader br = new BufferedReader(fr);
        try {
            String line;
            while (br.ready()){
                line= br.readLine();
                lines.add(line);
            }
        }
        finally {
            br.close();
            fr.close();
        }
        return lines;
    }
}
