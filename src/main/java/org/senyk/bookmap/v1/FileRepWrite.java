package org.senyk.bookmap.v1;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class FileRepWrite {
    private File file;

    public FileRepWrite(String file) {
        this.file = new File(file);
    }

    public void writeFile(List<String> output) throws IOException{
        FileWriter outputFile = new FileWriter(this.file);
        try {
            for (String o :output) {
                if (Objects.equals(o, ""))
                    continue;
                outputFile.write(o+"\n");
            }
            outputFile.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

}
