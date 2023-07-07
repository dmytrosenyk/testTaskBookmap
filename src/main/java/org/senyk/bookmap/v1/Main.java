package org.senyk.bookmap.v1;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileRepRead read=new FileRepRead("input.txt");
        FileRepWrite write=new FileRepWrite("output.txt");
        List<String> input;
        try {
            input=read.readLines();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            write.writeFile(ComponentService.output(input));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
