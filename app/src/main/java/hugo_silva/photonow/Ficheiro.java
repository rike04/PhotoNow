package hugo_silva.photonow;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by rike4 on 27/05/2017.
 */

public class Ficheiro {

    private static Repositorio repo = null;

    public static Repositorio getRepositorio(){
        if (repo == null) repo = new Repositorio();
        return repo;
    }

    public static void serializar(String filename) {
        // Serializar um objeto para ficheiro
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(repo);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in " + filename);
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    public static void desserializar(String filename) {
        //repo = new Repositorio();
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            repo = (Repositorio) in.readObject();
            in.close();
            fileIn.close();
            System.out.printf("File " + filename + " read!");
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Repositorio class not found. " + ex.getMessage());
        }
    }


}
