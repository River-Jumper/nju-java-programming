package example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;

import example.classloader.SteganographyClassLoader;

public class Scene {

    public static void main(String[] args) throws Exception {

        Line line = new Line(7);
        line.put(Gourd.ONE, 6);
        line.put(Gourd.TWO, 3);
        line.put(Gourd.THREE, 1);
        line.put(Gourd.FOUR, 5);
        line.put(Gourd.FIVE, 2);
        line.put(Gourd.SIX, 4);
        line.put(Gourd.SEVEN, 0);

        Geezer theGeezer = Geezer.getTheGeezer();
        URL fuHuaURL = new URL("file:///C:\\Users\\Lenovo\\Desktop\\class\\advancedJava\\nju-java-programming\\jwork-03\\example.QuickSorter.png");
        URL bubbleSorterURL = new URL("http://java.nemoworks.info/images/example.BubbleSorter.png");
        SteganographyClassLoader loader = new SteganographyClassLoader(fuHuaURL);

        Class c = loader.loadClass("example.QuickSorter");


        Sorter sorter = (Sorter) c.newInstance();

        theGeezer.setSorter(sorter);

        String log = theGeezer.lineUp(line);

        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter("result.txt"));
        writer.write(log);
        writer.flush();
        writer.close();

    }

}
