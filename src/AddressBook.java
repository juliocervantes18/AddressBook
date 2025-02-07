import java.io.*;
import java.util.*;

public class AddressBook {
    private HashMap<String, String> contactos;
    private String archivo_de_contactos = "contactos.txt";

    public AddressBook() {
        contactos = new HashMap<>();
        load();
}

    public void load() {
        try (BufferedReader cargar = new BufferedReader(new FileReader(archivo_de_contactos))) {
            String numero_y_nombre;
            while ((numero_y_nombre = cargar.readLine()) != null) {
                String[] dividir = numero_y_nombre.split(",");
                if (dividir.length == 2) {
                    contactos.put(dividir[0], dividir[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("No se puede cargar la agenda. Se creara un archivo nuevo.");
        }
    }

    public void save() {
        try (BufferedWriter guardado = new BufferedWriter(new FileWriter(archivo_de_contactos))) {
            for (Map.Entry<String, String> guardar : contactos.entrySet()) {
                guardado.write(guardar.getKey() + "," + guardar.getValue());
                guardado.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los contactos.");
        }
    }

    public void list() {
        if(contactos.isEmpty()){
            System.out.println("No hay contactos en la agenda,cree uno por favor.");
        }else{
            System.out.println("Contactos:");
            for (Map.Entry<String, String> entrada : contactos.entrySet()) {
                System.out.println(entrada.getKey() + " : " + entrada.getValue());
            }
        }
    }

    public void create(String numero_de_contacto, String nombre_de_contacto) {
        if (contactos.containsKey(numero_de_contacto)) {
            System.out.println("El numero ya existe en la agenda.");
        } else {
            contactos.put(numero_de_contacto, nombre_de_contacto);
            save();
            System.out.println("Contacto agregado exitosamente.");
        }
    }

    public void delete(String numero_de_contacto) {
        if (contactos.remove(numero_de_contacto) != null) {
            save();
            System.out.println("Contacto eliminado exitosamente.");
        } else {
            System.out.println("El numero no se encontro en la agenda.");
        }
    }

    public static void main(String[] args) {
        AddressBook agenda = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu de la Agenda Telefonica:");
            System.out.println("1. Listar de contactos");
            System.out.println("2. Crear un nuevo contacto");
            System.out.println("3. Eliminar a un contacto");
            System.out.println("4. Salir del sistema");
            System.out.print("Seleccione una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    agenda.list();
                    break;
                case 2:
                    System.out.print("Ingrese el numero: ");
                    String numero_de_contacto = scanner.nextLine();
                    System.out.print("Ingrese el nombre: ");
                    String nombre_de_contacto = scanner.nextLine();
                    agenda.create(numero_de_contacto, nombre_de_contacto);
                    break;
                case 3:
                    System.out.print("Ingrese el numero que quiera eliminar: ");
                    String numero_a_eliminar = scanner.nextLine();
                    agenda.delete(numero_a_eliminar);
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("No selecciono una opcion, intente de nuevo y eliga una correcta.");
            }
        }
    }
}
