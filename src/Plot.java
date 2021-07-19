
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
public class Plot implements Serializable {

    int id;
    double length;
    double width;
    String location;
    double area;
    double price;
    boolean soldStatus = false;
    Buyer b = new Buyer();

    public Plot() {
    }

    public Plot(double length, double width, String location, double price) throws customException.InvalidPrice {
        this.id = Plot.idCounter() + 1;
        this.length = length;
        this.width = width;
        this.location = location;
        if (price > 0) {
            this.price = price;
        } else {
            throw new customException.InvalidPrice();
        }
        this.area = length * width;
    }

    public static int idCounter() {
        ArrayList<Plot> pl = Plot.readAllData();
        if (!pl.isEmpty()) {
            return pl.get(pl.size() - 1).getId();
        } else {
            return 0;
        }
    }

    public double getPrice() {
        return price;
    }

    public double getArea() {
        return area;
    }

    public int getId() {
        return id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public static boolean validateId(int id) {
        boolean valid = false;
        if (!readAllData().isEmpty()) {
            int con = 0;
            for (Plot h : readAllData()) {
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
        } else {
            valid = false;
        }
        return valid;
    }

    public void setSoldStatus(boolean soldStatus) {
        this.soldStatus = soldStatus;
    }

    public void setB(Buyer b) {
        this.b = b;
    }

    public boolean isSoldStatus() {
        return soldStatus;
    }

    public Buyer getB() {
        return b;
    }

    public String getLocation() {
        return location;
    }

    public void setNewPrices(int id, double price) {
        this.price = price;
        ArrayList<Plot> list = readAllData();
        for (Plot w : list) {
            if (id == w.getId()) {
                w.setPrice(price);
                break;
            }
        }
        ObjectOutputStream outputStream = null;

        try {
            // Read old objects

// Append new object into existing list
            outputStream = new ObjectOutputStream(new FileOutputStream("plot.ser"));

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

        ArrayList<Plot> list = readAllData();
        for (Plot w : list) {
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
            outputStream = new ObjectOutputStream(new FileOutputStream("plot.ser"));

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
        System.out.println("Plot ID : " + this.id);
        System.out.println("Plot Area : " + this.area + "m^2");
        System.out.println("Plot Location : " + this.location);
        System.out.println("Plot Price : " + this.price);
        if (this.soldStatus) {
            System.out.println("This Plot is Sold ! ");
        }
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
    }

    public static ArrayList<Plot> readAllData() {
        ArrayList<Plot> PlotList = new ArrayList<>();
        ObjectInputStream inputStream = null;
        try {
            inputStream = new ObjectInputStream(new FileInputStream("plot.ser"));
            boolean EOF = false;
            while (!EOF) {
                try {
                    Plot myObj = (Plot) inputStream.readObject();
                    PlotList.add(myObj);
                } catch (ClassNotFoundException e) {
                    System.out.println("Class not found");
                } catch (EOFException end) {
                    EOF = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("");
        } catch (IOException e) {
            System.out.println("");
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.out.println("IO Exception while closing file");
            }
        }
        return PlotList;
    }

    public static void writeToFile(Plot s) {
        ObjectOutputStream outputStream = null;
        try {
            ArrayList<Plot> PlotList = readAllData();
            PlotList.add(s);

            outputStream = new ObjectOutputStream(new FileOutputStream("plot.ser"));
            for (int i = 0; i < PlotList.size(); i++) {
                outputStream.writeObject(PlotList.get(i));
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
        ArrayList<Plot> list = readAllData();
        boolean deleted = false;
        for (Plot w : list) {
            if (id == w.getId()) {
                list.remove(w);
                deleted = true;
                ObjectOutputStream outputStream = null;
                try {
                    outputStream = new ObjectOutputStream(new FileOutputStream("plot.ser"));
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
