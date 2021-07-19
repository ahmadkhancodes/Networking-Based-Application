
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muhammad Ahmad Khan
 */
public class Recipt implements Serializable{

    Buyer b;
    House h;
    Plot p;

    public Recipt(Buyer b, House h) {
        this.b = b;
        this.h = h;
    }

    public Recipt(Buyer b, Plot p) {
        this.b = b;
        this.p = p;
    }

    public Buyer getB() {
        return b;
    }

    public House getH() {
        return h;
    }

    public Plot getP() {
        return p;
    }

    public void setB(Buyer b) {
        this.b = b;
    }

    public void setH(House h) {
        this.h = h;
    }

    public void setP(Plot p) {
        this.p = p;
    }

    public void printRecipt() {
        System.out.println("*________________________ Recipt _____________________________*");
        System.out.println("Buyer Name : " + this.getB().getName());
        System.out.println("Buyer CNIC : " + this.getB().getCNIC());
        System.out.println("Buyer Phone : " + this.getB().getPhone());
        if (this.getP() != null) {
            System.out.println("Here Are your Plot Details");
            this.getP().Display();
            System.out.println("Total Amount to be Paid : " + this.getP().getPrice());
        }
        if (this.getH() != null) {
            System.out.println("Here Are Your House Details");
            System.out.println("House Area : " + this.getH().getArea());
            System.out.println("Rooms in House : " + this.getH().getRooms());
            System.out.println("Bathrooms in House : " + this.getH().getBathrooms());
            System.out.println("No of Stories in House : " + this.getH().getNoOfStories());
            System.out.println("House Address : " + this.getH().getAddress());
            if (this.getH().isRentStatus()) {
                System.out.println("First Rent Amount including Advance 50,000 : "+(this.getH().getRentPrice()+50000));
            } else {
                System.out.println("Total Amount to be Paid : " + this.getH().getPrice());

            }

        }
        System.out.println("*_____________________________________________________________*");
    }
}
