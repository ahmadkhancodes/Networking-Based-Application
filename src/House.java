
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
public class House implements Serializable {

    Buyer b = new Buyer();
    int id;
    double length;
    double width;
    String Address;
    double area;
    int rooms;
    int bathrooms;
    int noOfStories;
    double price;
    double rentPrice;
    boolean soldStatus = false;
    boolean rentStatus = false;

    public House() {
    }

    public House(double length, double width, String Address, int rooms, int bathrooms, int noOfStories, double price, double rentPrice) throws customException.InvalidPrice {
        this.id = House.idCounter() + 1;
        this.length = length;
        this.width = width;
        this.Address = Address;
        this.rooms = rooms;
        this.bathrooms = bathrooms;
        this.noOfStories = noOfStories;
        if (price > 0) {
            this.price = price;
        } else {
            throw new customException.InvalidPrice();
        }
        if (rentPrice >= 0) {
            this.rentPrice = rentPrice;
        }
        else {
            throw new customException.InvalidPrice();
        }
        this.rentPrice = rentPrice;
        this.area = length * width;
    }

    public String getAddress() {
        return Address;
    }

    public void setRentStatus(boolean rentStatus) {
        this.rentStatus = rentStatus;
    }

    public void setSoldStatus(boolean soldStatus) {
        this.soldStatus = soldStatus;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public static int idCounter() {
        ArrayList<House> hl = House.readAllData();
        if (!hl.isEmpty()) {
            return hl.get(hl.size() - 1).getId();
        } else {
            return 0;
        }
    }

    public static boolean validateId(int id) {
        boolean valid = false;
        int con = 0;
        for (House h : readAllData()) {
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

    public void setPrice(double price) {
        this.price = price;
    }

    public void setB(Buyer b) {
        this.b = b;
    }

    public boolean isSoldStatus() {
        return soldStatus;
    }

    public boolean isRentStatus() {
        return rentStatus;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public double getPrice() {
        return price;
    }

    public Buyer getB() {
        return b;
    }

    public double getArea() {
        return area;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public int getRooms() {
        return rooms;
    }

    public int getId() {
        return id;
    }

    public int getNoOfStories() {
        return noOfStories;
    }

    public void setNewPrices(int id, double price, double rentPrice) {
        this.price = price;
        this.rentPrice = rentPrice;
        ArrayList<House> list = readAllData();
        for (House w : list) {
            if (id == w.getId()) {
                w.setPrice(price);
                w.setRentPrice(rentPrice);
                break;
            }
        }
        ObjectOutputStream outputStream = null;

        try {
            // Read old objects

// Append new object into existing list
            outputStream = new ObjectOutputStream(new FileOutputStream("house.ser"));

// Write all objects (old and new one) into the file
            for (int i = 0; i < list.size(); i++) {
                outputStream.writeObject(list.get(i));
            }

        } catch (IOException exp) {
            System.out.println("IO Exception while opening file");
        } finally {
// cleanup code which closes output stream if its object was created
            try {
                if (outputStream != null) {
                    outputStream.close();
                    // flag of success

                }

            } catch (IOException exp) {
                System.out.println("IO Exception while closing file");
            }
        }
    }

    public void goingToSold(Buyer b, int id) {
        this.b = b;
        this.soldStatus = true;

        ArrayList<House> list = readAllData();
        for (House w : list) {
            if (id == w.getId()) {
                w.setB(b);
                w.setSoldStatus(true);
                break;
            }
        }
        ObjectOutputStream outputStream = null;

        try {
            // Read old objects

// Append new object into existing list
            outputStream = new ObjectOutputStream(new FileOutputStream("house.ser"));

// Write all objects (old and new one) into the file
            for (int i = 0; i < list.size(); i++) {
                outputStream.writeObject(list.get(i));
            }

        } catch (IOException exp) {
            System.out.println("IO Exception while opening file");
        } finally {
// cleanup code which closes output stream if its object was created
            try {
                if (outputStream != null) {
                    outputStream.close();
                    // flag of success

                }

            } catch (IOException exp) {
                System.out.println("IO Exception while closing file");
            }
        }
    }

    public void goingToRent(Buyer b, int id) {
        this.b = b;
        this.rentStatus = true;

        ArrayList<House> list = readAllData();
        for (House w : list) {
            if (id == w.getId()) {
                w.setB(b);
                w.setRentStatus(true);
                break;
            }
        }
        ObjectOutputStream outputStream = null;

        try {
            // Read old objects

// Append new object into existing list
            outputStream = new ObjectOutputStream(new FileOutputStream("house.ser"));

// Write all objects (old and new one) into the file
            for (int i = 0; i < list.size(); i++) {
                outputStream.writeObject(list.get(i));
            }

        } catch (IOException exp) {
            System.out.println("IO Exception while opening file");
        } finally {
// cleanup code which closes output stream if its object was created
            try {
                if (outputStream != null) {
                    outputStream.close();
                    // flag of success

                }

            } catch (IOException exp) {
                System.out.println("IO Exception while closing file");
            }
        }
    }

    public void backFromRent(int id) {
        this.b = new Buyer();
        this.rentStatus = false;

        ArrayList<House> list = readAllData();
        for (House w : list) {
            if (id == w.getId()) {
                w.setB(b);
                w.setRentStatus(false);
                break;
            }
        }
        ObjectOutputStream outputStream = null;

        try {
            // Read old objects

// Append new object into existing list
            outputStream = new ObjectOutputStream(new FileOutputStream("house.ser"));

// Write all objects (old and new one) into the file
            for (int i = 0; i < list.size(); i++) {
                outputStream.writeObject(list.get(i));
            }

        } catch (IOException exp) {
            System.out.println("IO Exception while opening file");
        } finally {
// cleanup code which closes output stream if its object was created
            try {
                if (outputStream != null) {
                    outputStream.close();
                    // flag of success

                }

            } catch (IOException exp) {
                System.out.println("IO Exception while closing file");
            }
        }
    }

    public void Display() {
        System.out.println("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println("Home ID : " + this.id);
        System.out.println("Home Area : " + this.area + "m^2");
        System.out.println("No of Rooms : " + this.rooms);
        System.out.println("No of Bathrooms : " + this.bathrooms);
        System.out.println("No of Stories : " + this.noOfStories);
        System.out.println("Home Location : " + this.Address);
        System.out.println("Price : " + this.price);
        if (rentPrice > 0) {
            System.out.println("Rent Price : " + this.rentPrice);
        } else {
            System.out.println("Not Available For Rent !");
        }
        if (this.soldStatus) {
            System.out.println("This House is Sold ! ");
        }
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
    }

    public static ArrayList<House> readAllData() {
        ArrayList<House> HouseList = new ArrayList<>(0);
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream("house.ser"));
            boolean EOF = false;
            while (!EOF) {
                try {
                    House myObj = (House) inputStream.readObject();
                    HouseList.add(myObj);
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
        return HouseList;
    }

    public static void writeToFile(House s) {
        ObjectOutputStream outputStream = null;
        try {
            ArrayList<House> HouseList = readAllData();
            HouseList.add(s);

            outputStream = new ObjectOutputStream(new FileOutputStream("house.ser"));
            for (int i = 0; i < HouseList.size(); i++) {
                outputStream.writeObject(HouseList.get(i));
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
        ArrayList<House> list = readAllData();
        boolean deleted = false;
        for (House w : list) {
            if (id == w.getId()) {
                list.remove(w);
                deleted = true;
                ObjectOutputStream outputStream = null;
                try {
                    outputStream = new ObjectOutputStream(new FileOutputStream("house.ser"));
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
