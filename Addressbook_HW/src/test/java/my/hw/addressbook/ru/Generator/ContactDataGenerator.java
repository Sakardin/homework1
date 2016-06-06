package my.hw.addressbook.ru.Generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import my.hw.addressbook.ru.Model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 5/31/2016.
 */
public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public  String file;

    @Parameter(names = "-d", description = "Data format")
    public  String format;




    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
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

        List<ContactData> contacts = getGeneratorContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        }else if (format.equals("xml")) {
            getSaveAsXml(contacts, new File(file));
        }else if (format.equals("json")) {
            getSaveAsJson(contacts, new File(file));
        }else {
            System.out.println("Unrecognizes format " + format);
        }

    }

    private void getSaveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

//с функцией try не надо закрывать writer
    private void getSaveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        xStream.alias("contact", ContactData.class);
        String xml = xStream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

//без try надо закрывать writer
    private  void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts){
            writer.write(String.format("%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getNickName()));        }
        writer.close();

    }

    private  List<ContactData> getGeneratorContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i =1; i < count; i++){
            contacts.add(new ContactData().witFirstName(String.format("TestFirstName %s", i)).withMiddleName(String.format("TestMiddleName %s", i)).withLastName(String.format("TestLastName %s", i))
                    .withNickName(String.format("TestNickName %s", i)).withTitle(String.format("TestTitle %s", i)).withCompany(String.format("TestCompany %s", i)).withAddress(String.format("TestAddress %s", i))
                    .withHomePhone(String.format("TestHomePhone %s", i)).withMobilePhone(String.format("TestMobilePhone %s", i))
                    .withWorkPhone(String.format("TestWorkPhone %s", i)).withFax(String.format("TestFax %s", i)));
        }
        return contacts;
    }
}
