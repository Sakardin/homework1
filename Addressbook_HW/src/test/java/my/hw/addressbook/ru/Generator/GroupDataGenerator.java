package my.hw.addressbook.ru.Generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import my.hw.addressbook.ru.Model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 5/31/2016.
 */
public class GroupDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public  String file;

    @Parameter(names = "-d", description = "Data format")
    public  String format;




    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
           jCommander.parse(args);
        }catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
           }

    private void run() throws IOException {

        List<GroupData> groups = getGenerateGroups(count);
        if (format.equals("csv")) {
            saveAsCsv(groups, new File(file));
        }else if (format.equals("xml")) {
            getSaveAsXml(groups, new File(file));
        }else if (format.equals("json")) {
            getSaveAsJson(groups, new File(file));
        }else {
            System.out.println("Unrecognizes format " + format);
        }

    }

    private void getSaveAsJson(List<GroupData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void getSaveAsXml(List<GroupData> groups, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        xStream.alias("group", GroupData.class);
        String xml = xStream.toXML(groups);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();

    }

    private  void saveAsCsv(List<GroupData> groups, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (GroupData group : groups){
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));        }
        writer.close();

    }

    private  List<GroupData> getGenerateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i =1; i < count; i++){
            groups.add(new GroupData().withName(String.format("Test %s", i)).withHeader(String.format("Header %s", i))
            .withFooter(String.format("Footer %s", i)));
        }
        return groups;
    }
}
