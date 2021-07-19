/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muhammad Ahmad Khan
 */
public class customException {

    public static class InvalidPhoneNumber extends Exception {

        public InvalidPhoneNumber() {
            super("Invalid Phone Number !");
        }

    }

    public static class InvalidPrice extends Exception {

        public InvalidPrice() {
            super("Invalid Price !");
        }

    }
    
    public static class InvalidCNIC extends Exception {

        public InvalidCNIC() {
            super("Invalid CNIC !");
        }

    }

}
