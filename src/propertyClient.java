
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Muhammad Ahmad Khan
 */
public class propertyClient {

    DatagramSocket recieveSocket;

    public propertyClient() {
    }

    public void propertyManagmentSystem() throws SocketException, ClassNotFoundException {
        recieveSocket = new DatagramSocket(5133);
        int j = 1;
        Scanner input = new Scanner(System.in);
        while (j != 0) {
            DataPacket packetforsend = new DataPacket();
            ArrayList<Integer> choiceSequence = new ArrayList<>();
            System.out.println("*-*-*-*-*-*-* Welcome to Property Managment System *-*-*-*-*-*-*-*");
            System.out.println("1.Manage Property");
            System.out.println("2.Sell Property");
            System.out.println("0.Exit");
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-**-*-*-*-*-*-*-*\n");
            System.out.println("Enter Your Choice : ");
            int a = input.nextInt();
            switch (a) {
                case 1:
                    choiceSequence.add(1);
                    System.out.println("*-*-*-*-*-*-*-*-*");
                    System.out.println("1.Manage Plots");
                    System.out.println("2.Manage Houses");
                    System.out.println("0.Back");
                    System.out.println("*-*-*-*-*-*-*-*-*\n");
                    System.out.println("Enter Your Choice : ");
                    int b = input.nextInt();
                    switch (b) {
                        case 1:
                            choiceSequence.add(1);
                            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                            System.out.println("1.Add Plot");
                            System.out.println("2.Delete Plot");
                            System.out.println("3.Update a Plot Price");
                            System.out.println("4.View All Plots");
                            System.out.println("5.Search a Plot");
                            System.out.println("0.Back");
                            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
                            System.out.println("Enter Your Choice : ");
                            int c = input.nextInt();
                            switch (c) {
                                case 1:
                                    choiceSequence.add(1);
                                    try {
                                        System.out.println("Length : ");
                                        int len = input.nextInt();
                                        System.out.println("Width : ");
                                        int wid = input.nextInt();
                                        System.out.println("Plot Address : ");
                                        String add = input.next();
                                        System.out.println("Price : ");
                                        int pri = input.nextInt();
                                        Plot p = new Plot(len, wid, add, pri);
                                        packetforsend = new DataPacket(choiceSequence, p);
                                        sendDataToServer(packetforsend);
                                        //recieving Response From Server
                                        DataPacket recievePacket = recieveDataFromServer();
                                        System.out.println(recievePacket.getSuccessMessage());

                                    } catch (Exception es) {
                                        System.out.println(es.getMessage());
                                    }

                                    break;
                                case 2:
                                    choiceSequence.add(2);
                                    try {
                                        System.out.println("Delete Plot");
                                        System.out.println("Enter Plot id You want to delete");
                                        int id = input.nextInt();
                                        packetforsend = new DataPacket(id, choiceSequence);
                                        sendDataToServer(packetforsend);
                                        //recieving Response From Server
                                        DataPacket recievePacket = recieveDataFromServer();
                                        System.out.println(recievePacket.getSuccessMessage());

                                    } catch (Exception exx) {
                                        System.out.println(exx.getMessage());
                                    }
                                    break;
                                case 3:
                                    choiceSequence.add(3);
                                    try {
                                        System.out.println("Update a Plot Price");
                                        System.out.println("Enter Plot ID of which you want to Update Price");
                                        int id = input.nextInt();
                                        System.out.println("Enter Plot New Price");
                                        int pri = input.nextInt();
                                        packetforsend = new DataPacket(id, pri, choiceSequence);
                                        sendDataToServer(packetforsend);
                                        //recieving Response From Server
                                        DataPacket recievePacket = recieveDataFromServer();
                                        System.out.println(recievePacket.getSuccessMessage());
                                    } catch (Exception exx) {
                                        System.out.println(exx.getMessage());
                                    }
                                    break;
                                case 4:
                                    choiceSequence.add(4);
                                    try {
                                        packetforsend = new DataPacket(choiceSequence);
                                        sendDataToServer(packetforsend);
                                        //recieving Response From Server
                                        DataPacket recievePacket = recieveDataFromServer();
                                        if (recievePacket.getSuccessMessage().equalsIgnoreCase("")) {
                                            for (Plot p : recievePacket.getPl()) {
                                                p.Display();
                                            }
                                        } else {
                                            System.out.println(recievePacket.getSuccessMessage());
                                        }
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 5:
                                    choiceSequence.add(5);
                                    try {
                                        System.out.println("Enter Plot id you want to search");
                                        int id = input.nextInt();
                                        packetforsend = new DataPacket(id, choiceSequence);
                                        sendDataToServer(packetforsend);
                                        //recieving Response From Server
                                        DataPacket recievePacket = recieveDataFromServer();
                                        System.out.println(recievePacket.getSuccessMessage());
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 0:
                                    break;

                                default:
                                    System.out.println("Invalid Choice");
                                    break;
                            }
                            break;
                        case 2:
                            choiceSequence.add(2);
                            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                            System.out.println("1.Add House");
                            System.out.println("2.Delete House");
                            System.out.println("3.Update a House Price");
                            System.out.println("4.View All Houses");
                            System.out.println("5.Search a House");
                            System.out.println("6.House Back from Rent");
                            System.out.println("0.Back");
                            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
                            System.out.println("Enter Your Choice : ");
                            int t = input.nextInt();
                            switch (t) {
                                case 1:
                                    choiceSequence.add(1);
                                    try {
                                        System.out.println("Length : ");
                                        int len = input.nextInt();
                                        System.out.println("Width : ");
                                        int wid = input.nextInt();
                                        System.out.println("House Address : ");
                                        String add = input.next();
                                        System.out.println("No of Rooms : ");
                                        int room = input.nextInt();
                                        System.out.println("No of Bathrooms : ");
                                        int broom = input.nextInt();
                                        System.out.println("No of Stories : ");
                                        int stor = input.nextInt();
                                        System.out.println("Price : ");
                                        int pri = input.nextInt();
                                        System.out.println("Rent Price (Simply Enter 0 incase of Not Available for Rent) : ");
                                        int rpri = input.nextInt();
                                        House h = new House(len, wid, add, room, broom, stor, pri, rpri);
                                        packetforsend = new DataPacket(choiceSequence, h);
                                        sendDataToServer(packetforsend);
                                        //recieving Response From Server
                                        DataPacket recievePacket = recieveDataFromServer();
                                        System.out.println(recievePacket.getSuccessMessage());
                                    } catch (Exception es) {
                                        System.out.println(es.getMessage());
                                    }

                                    break;
                                case 2:
                                    choiceSequence.add(2);
                                    try {
                                        System.out.println("Delete House");
                                        System.out.println("Enter House id You want to delete");
                                        int id = input.nextInt();
                                        packetforsend = new DataPacket(id, choiceSequence);
                                        sendDataToServer(packetforsend);
                                        //recieving Response From Server
                                        DataPacket recievePacket = recieveDataFromServer();
                                        System.out.println(recievePacket.getSuccessMessage());
                                    } catch (Exception exx) {
                                        System.out.println(exx.getMessage());
                                    }
                                    break;
                                case 3:
                                    choiceSequence.add(3);
                                    try {
                                        System.out.println("Update a House Price");
                                        System.out.println("Enter House ID of which you want to Update Price");
                                        int id = input.nextInt();
                                        System.out.println("Enter House New Price");
                                        int pri = input.nextInt();
                                        System.out.println("Enter House New Rent Price");
                                        int rpri = input.nextInt();
                                        packetforsend = new DataPacket(id, pri, choiceSequence);
                                        packetforsend.setRprice(rpri);
                                        sendDataToServer(packetforsend);
                                        //recieving Response From Server
                                        DataPacket recievePacket = recieveDataFromServer();
                                        System.out.println(recievePacket.getSuccessMessage());
                                    } catch (Exception exx) {
                                        System.out.println(exx.getMessage());
                                    }
                                    break;
                                case 4:
                                    choiceSequence.add(4);
                                    try {
                                        packetforsend = new DataPacket(choiceSequence);
                                        sendDataToServer(packetforsend);
                                        //recieving Response From Server
                                        DataPacket recievePacket = recieveDataFromServer();
                                        if (recievePacket.getSuccessMessage().equalsIgnoreCase("")) {
                                            for (House p : recievePacket.getHl()) {
                                                p.Display();
                                            }
                                        } else {
                                            System.out.println(recievePacket.getSuccessMessage());
                                        }
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 5:
                                    choiceSequence.add(5);
                                    try {
                                        System.out.println("Enter House id you want to search");
                                        int id = input.nextInt();
                                        packetforsend = new DataPacket(id, choiceSequence);
                                        sendDataToServer(packetforsend);
                                        //recieving Response From Server
                                        DataPacket recievePacket = recieveDataFromServer();
                                        System.out.println(recievePacket.getSuccessMessage());
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 6:
                                    choiceSequence.add(6);
                                    try {
                                        System.out.println("Enter House Id : ");
                                        int id = input.nextInt();
                                        packetforsend = new DataPacket(id, choiceSequence);
                                        sendDataToServer(packetforsend);
                                        //recieving Response From Server
                                        DataPacket recievePacket = recieveDataFromServer();
                                        System.out.println(recievePacket.getSuccessMessage());
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 0:
                                    break;

                                default:
                                    System.out.println("Invalid Choice");
                                    break;
                            }
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Invalid Choice !");

                    }
                    break;
                case 2:
                    choiceSequence.add(2);
                    System.out.println("*-*-*-*-*-*-*-*-*");
                    System.out.println("1.See All Available Plots & Houses");
                    System.out.println("2.Sell Plot");
                    System.out.println("3.Sell House");
                    System.out.println("4.Give House on Rent");
                    System.out.println("5.Add Buyer");
                    System.out.println("6.See All Registered Buyers");
                    System.out.println("0.Back");
                    System.out.println("*-*-*-*-*-*-*-*-*\n");
                    System.out.println("Enter Your Choice : ");
                    int r = input.nextInt();
                    switch (r) {
                        case 1:
                            try {
                                choiceSequence.add(1);
                                packetforsend = new DataPacket(choiceSequence);
                                sendDataToServer(packetforsend);
                                //recieving
                                DataPacket recievePacket = recieveDataFromServer();
                                System.out.println("*-*--------------- Plots ---------------*-*");
                                if (!recievePacket.getPl().isEmpty()) {
                                    for (Plot p : recievePacket.getPl()) {
                                        p.Display();
                                    }
                                } else {
                                    System.out.println("No Plots Available !");
                                }
                                System.out.println("*-*--------------- Houses ---------------*-*");
                                if (!recievePacket.getHl().isEmpty()) {
                                    for (House h : recievePacket.getHl()) {
                                        h.Display();
                                    }
                                } else {
                                    System.out.println("No Houses Available !");
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 2:
                            choiceSequence.add(2);
                            try {
                                System.out.println("Enter Plot Id you want to Sell : ");
                                int id = input.nextInt();
                                System.out.println("Enter Buyer Id to which you want to Sell : ");
                                int bid = input.nextInt();
                                packetforsend = new DataPacket(id, choiceSequence);
                                packetforsend.setBid(bid);
                                sendDataToServer(packetforsend);
                                //recieving Response From Server
                                DataPacket recievePacket = recieveDataFromServer();
                                if (recievePacket.getSuccessMessage().isEmpty()) {
                                    recievePacket.getRecipt().printRecipt();
                                } else {
                                    System.out.println(recievePacket.getSuccessMessage());
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 3:
                            choiceSequence.add(3);
                            try {
                                System.out.println("Enter House Id you want to Sell : ");
                                int id = input.nextInt();
                                System.out.println("Enter Buyer Id to which you want to Sell : ");
                                int bid = input.nextInt();
                                packetforsend = new DataPacket(id, choiceSequence);
                                packetforsend.setBid(bid);
                                sendDataToServer(packetforsend);
                                //recieving Response From Server
                                DataPacket recievePacket = recieveDataFromServer();
                                if (recievePacket.getSuccessMessage().isEmpty()) {
                                    recievePacket.getRecipt().printRecipt();
                                } else {
                                    System.out.println(recievePacket.getSuccessMessage());
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 4:
                            choiceSequence.add(4);
                            try {
                                System.out.println("Enter House Id you want to Give on Rent : ");
                                int id = input.nextInt();
                                System.out.println("Enter Buyer Id to which you want to Give : ");
                                int bid = input.nextInt();
                                packetforsend = new DataPacket(id, choiceSequence);
                                packetforsend.setBid(bid);
                                sendDataToServer(packetforsend);
                                //recieving Response From Server
                                DataPacket recievePacket = recieveDataFromServer();
                                if (recievePacket.getSuccessMessage().isEmpty()) {
                                    recievePacket.getRecipt().printRecipt();
                                } else {
                                    System.out.println(recievePacket.getSuccessMessage());
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 5:
                            choiceSequence.add(5);
                            try {
                                System.out.println("Name : ");
                                String name = input.next();
                                System.out.println("CNIC : ");
                                String cnic = input.next();
                                System.out.println("Phone : ");
                                String phone = input.next();
                                System.out.println("Address : ");
                                String add = input.next();
                                Buyer bu = new Buyer(name, cnic, add, phone);
                                packetforsend = new DataPacket(choiceSequence, bu);
                                sendDataToServer(packetforsend);
                                //recieving Response From Server
                                DataPacket recievePacket = recieveDataFromServer();
                                System.out.println(recievePacket.getSuccessMessage());
                            } catch (Exception es) {
                                System.out.println(es.getMessage());
                            }
                            break;
                        case 6:
                            choiceSequence.add(6);
                            try {
                                packetforsend = new DataPacket(choiceSequence);
                                sendDataToServer(packetforsend);
                                //recieving Response From Server
                                DataPacket recievePacket = recieveDataFromServer();
                                if (recievePacket.getSuccessMessage().equalsIgnoreCase("")) {
                                    for (Buyer p : recievePacket.getBl()) {
                                        p.Display();
                                    }
                                } else {
                                    System.out.println(recievePacket.getSuccessMessage());
                                }
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Invalid Choice !");
                            break;

                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }

    }

    public void sendDataToServer(DataPacket obj) {
        try {

            DatagramSocket sendSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(obj);
            byte[] data = outputStream.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, 6737);
            sendSocket.send(sendPacket);
            //

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public DataPacket recieveDataFromServer() throws ClassNotFoundException {
        DataPacket obj = new DataPacket();
        try {
            byte[] incomingData = new byte[1024];
            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            recieveSocket.receive(incomingPacket);
            byte[] ReceiveData = incomingPacket.getData();
            ByteArrayInputStream in = new ByteArrayInputStream(ReceiveData);
            ObjectInputStream is = new ObjectInputStream(in);
            obj = (DataPacket) is.readObject();
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public static void main(String[] args) throws SocketException, ClassNotFoundException {
        propertyClient p = new propertyClient();
        p.propertyManagmentSystem();
    }

}
