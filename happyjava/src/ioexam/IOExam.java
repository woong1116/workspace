package ioexam;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class IOExam {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://docs.oracle.com/javase/8/docs/api/java/lang/System.html");

//        InputStream in = System.in;
//        InputStream in = new FileInputStream("src/ioexam/IOExam.java");
        InputStream in = url.openStream();
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);
    }
}
