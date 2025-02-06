import java.io.*;
import java.util.*;

public class AddressBook {
    private HashMap<String, String> contactos;
    private final String archivo_de_contactos = "contactos.txt";

    public AddressBook() {
        contactos = new HashMap<>();
        load();
}
