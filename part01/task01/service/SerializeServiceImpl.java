package main.java.part01.lesson07.task01.service;

import java.io.*;

public class SerializeServiceImpl implements SerializeService {


    /**
     * serialize object
     * @param object
     * @param file
     */
    @Override
    public void serialize(Object object, String file) {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(object);
            objectOut.close();
            fileOut.close();
            System.out.println("The Object was successfully written to a file");
        } catch (NotSerializableException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * deSerialize object from file
     * @param file
     * @return
     */
    @Override
    public Object deSerialize(String file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(file));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

            return object;

        } catch (NotSerializableException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
