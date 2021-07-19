
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
public class DataPacket implements Serializable {

    Recipt recipt;
    int id;
    int bid;
    double price;
    double rprice;
    ArrayList<Integer> choiceSequence;
    House h;
    Plot p;
    Buyer b;
    ArrayList<House> hl;
    ArrayList<Plot> pl;
    ArrayList<Buyer> bl;
    String successMessage = "";
    String alertMessage = "";

    public DataPacket() {
    }

    public DataPacket(int id, double price, ArrayList<Integer> choiceSequence) {
        this.id = id;
        this.price = price;
        this.choiceSequence = choiceSequence;
    }

    public DataPacket(int id, ArrayList<Integer> choiceSequence) {
        this.id = id;
        this.choiceSequence = choiceSequence;
    }

    public DataPacket(ArrayList<House> hl, ArrayList<Plot> pl, ArrayList<Buyer> bl) {
        this.hl = hl;
        this.bl=bl;
        this.pl=pl;
    }

    public void setRprice(double rprice) {
        this.rprice = rprice;
    }

    public double getRprice() {
        return rprice;
    }

    public void setRecipt(Recipt recipt) {
        this.recipt = recipt;
    }

    public Recipt getRecipt() {
        return recipt;
    }


    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }
    

    public DataPacket(ArrayList<Integer> choiceSequence) {
        this.choiceSequence = choiceSequence;
    }

    public DataPacket(ArrayList<Integer> choiceSequence, House h) {
        this.choiceSequence = choiceSequence;
        this.h = h;
    }

    public DataPacket(ArrayList<Integer> choiceSequence, Plot p) {
        this.choiceSequence = choiceSequence;
        this.p = p;
    }

    public ArrayList<Buyer> getBl() {
        return bl;
    }

    public ArrayList<House> getHl() {
        return hl;
    }

    public ArrayList<Plot> getPl() {
        return pl;
    }
    
    

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public DataPacket(ArrayList<Integer> choiceSequence, Buyer b) {
        this.choiceSequence = choiceSequence;
        this.b = b;
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

    public String getAlertMessage() {
        return alertMessage;
    }

    public ArrayList<Integer> getChoiceSequence() {
        return choiceSequence;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public void setChoiceSequence(ArrayList<Integer> choiceSequence) {
        this.choiceSequence = choiceSequence;
    }

}
