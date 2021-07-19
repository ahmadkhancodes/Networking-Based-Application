
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Muhammad Ahmad Khan
 */
public class Buyer implements Serializable {

    int id;
    String name;
    String CNIC;
    String address;
    String phone;

    public Buyer() {
    }

    public Buyer(String name, String CNIC, String address, String phone) throws customException.InvalidCNIC, customException.InvalidPhoneNumber {
        this.id = Buyer.idCounter() + 1;
        this.name = name;
        if (CNIC.length() == 13) {
            this.CNIC = CNIC;
        } else {
            throw new customException.InvalidCNIC();
        }
        this.address = address;
        if (phone.length() == 11) {
        this.phone = phone;
        } else {
            throw new customException.InvalidPhoneNumber();
        }
    }

    public String getAddress() {
        return address;
    }

    public String getCNIC() {
        return CNIC;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static int idCounter() {
        ArrayList<Buyer> pl = Buyer.readAllData();
        if (!pl.isEmpty()) {
            return pl.get(pl.size() - 1).getId();
        } else {
            return 0;
        }
    }

    public static boolean validateId(int id) {
        boolean valid = false;
        int con = 0;
        for (Buyer h : readAllData()) {
            con++;
            if (h.getId() == id) {
                valid = true;
                con = 0;
                break;
            }

        }
        if (con != 0) {
            valid = false;
        }
        return valid;
    }

    public void Display() {
        System.out.println("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("Buyer ID : " + this.id);
        System.out.println("Buyer Name : " + this.name);
        System.out.println("Buyer Phone : " + this.phone);
        System.out.println("Buyer CNIC : " + this.CNIC);
        System.out.println("Buyer Address : " + this.address);
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
    }

    public static ArrayList<Buyer> readAllData() {
        ArrayList<Buyer> BuyerList = new ArrayList<>(0);
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream("buyer.ser"));
            boolean EOF = false;
            while (!EOF) {
                try {
                    Buyer myObj = (Buyer) inputStream.readObject();
                    BuyerList.add(myObj);
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found");
                } catch (EOFException end) {
                    EOF = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("");
        } catch (IOException e) {
            System.out.println("IO Exception while opening stream");
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.out.println("IO Exception while closing file");
            }
        }
        return BuyerList;
    }

    public static void writeToFile(Buyer s) {
        ObjectOutputStream outputStream = null;
        try {
            ArrayList<Buyer> BuyerList = readAllData();
            BuyerList.add(s);

            outputStream = new ObjectOutputStream(new FileOutputStream("buyer.ser"));
            for (int i = 0; i < BuyerList.size(); i++) {
                outputStream.writeObject(BuyerList.get(i));
            }

        } catch (IOException exp) {
            System.out.println(exp.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static boolean delete(int id) {
        ArrayList<Buyer> list = readAllData();
        boolean deleted = false;
        for (Buyer w : list) {
            if (id == w.getId()) {
                list.remove(w);
                deleted = true;
                ObjectOutputStream outputStream = null;
                try {
                    outputStream = new ObjectOutputStream(new FileOutputStream("buyer.ser"));
                    for (int i = 0; i < list.size(); i++) {
                        outputStream.writeObject(list.get(i));
                    }
                } catch (IOException exp) {
                    System.out.println("IO Exception while opening file");
                } finally {
                    try {
                        if (outputStream != null) {
                            outputStream.close();
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
            }
        }
        return deleted;
    }

}
