package cn.it100.interview;


import com.apple.eio.FileManager;

import java.io.File;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;


/**
 * 编写一个程序，将 a.txt 文件中的单词与 b.txt 文件中的单词交替合并到 c.txt
 * 文件中，
 * a.txt 文件中的单词用回车符分隔，b.txt 文件中用回车或空格进行分隔。
 */

public class exercise111 {
    public static void main(String[] args) throws Exception {
        FileWriter ra = new FileWriter("a.txt");
        ra.write("adsf\nasgdg\ndasg\nqewr\ntrt\n");
        ra.close();
        FileWriter rb = new FileWriter("b.txt");
        rb.write("adsf asgdg dasg\nqewr trt\n");
        rb.close();

        FileManager2 a = new FileManager2("a.txt", new char[]{'\n'});
        FileManager2 b = new FileManager2("b.txt", new char[]{'\n', ' '});
        FileWriter c = new FileWriter("c.txt");
        String aWord = null;
        String bWord = null;
        //交替写入
        while ((aWord = a.nextWord()) != null) {
            c.write(aWord + "\n");
            bWord = b.nextWord();
            if (bWord != null) {
                c.write(bWord + "\n");
            }
        }
        while ((bWord = b.nextWord()) != null) {
            c.write(bWord + "\n");
        }
        c.close();
    }
}

class FileManager2 {
    String[] words = null;
    int pos = 0;//words的索引

    /**
     * 读入文件信息，根据分隔符，存入字符串数组words中
     *
     * @param filename
     * @param seperators
     * @throws Exception
     */

    public FileManager2(String filename, char[] seperators) throws Exception {
        File f = new File(filename);
        FileReader reader = new FileReader(filename);//文件字符流
        char[] buf = new char[(int) f.length()];//和文件字节流一样大
        int len = reader.read(buf);//读取的长度----小文件，一次读入
        String results = new String(buf, 0, len);
        String regex = null;//正则表达式
        if (seperators.length > 1) {
            regex = "" + seperators[0] + "|" + seperators[1];
        } else {
            regex = "" + seperators[0];
        }
        words = results.split(regex);
        reader.close();

    }

    /**
     * 返回当前pos位置words[pos] ，然后后移pos，即pos++
     *
     * @return
     */
    public String nextWord() {
        if (pos == words.length) {
            return null;
        }
        return words[pos++];
    }


}
