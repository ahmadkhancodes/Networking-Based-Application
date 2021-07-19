/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author Muhammad Ahmad Khan
 */
public class propertyServer {

    DatagramSocket recieveSocket;

    public propertyServer() {
    }

    public void propertyManagmentSystem() throws SocketException, ClassNotFoundException {
        recieveSocket = new DatagramSocket(6737);
        DataPacket packetforsend = new DataPacket();
        while (true) {
            DataPacket recievingPacket = recieveDataFromClient();
            ArrayList<Integer> choiceSequence = recievingPacket.getChoiceSequence();
            int a = (int) choiceSequence.get(0);
            int b = (int) choiceSequence.get(1);
            int c = 0;
            if (choiceSequence.size() == 3) {
                c = (int) choiceSequence.get(2);
            }
            switch (a) {
                case 1:
                    switch (b) {
                        case 1:
                            switch (c) {
                                case 1:
                                    try {
                                        Plot p = new Plot(recievingPacket.getP().getLength(), recievingPacket.getP().getWidth(), recievingPacket.getP().getLocation(), recievingPacket.getP().getPrice());
                                        Plot.writeToFile(p);
                                        packetforsend = new DataPacket();
                                        packetforsend.setSuccessMessage("\nPlot Added !\n");
                                        sendDataToClient(packetforsend);
                                    } catch (Exception es) {
                                        System.out.println(es.getMessage());
                                    }

                                    break;
                                case 2:
                                    try {
                                        if (Plot.validateId(recievingPacket.getId())) {
                                            Plot.delete(recievingPacket.getId());
                                            packetforsend = new DataPacket();
                                            packetforsend.setSuccessMessage("\nSuuccessfully Deleted\n");
                                        } else {
                                            packetforsend = new DataPacket();
                                            packetforsend.setSuccessMessage("\nInvalid Id\n");
                                        }
                                        sendDataToClient(packetforsend);
                                    } catch (Exception exx) {
                                        System.out.println(exx.getMessage());
                                    }
                                    break;
                                case 3:
                                    try {
                                        ArrayList<Plot> pl = Plot.readAllData();
                                        if (Plot.validateId(recievingPacket.getId())) {
                                            for (Plot pro : pl) {
                                                if (pro.getId() == recievingPacket.getId()) {
                                                    pro.setNewPrices(recievingPacket.getId(), recievingPacket.getPrice());
                                                    break;
                                                }
                                            }
                                            packetforsend = new DataPacket();
                                            packetforsend.setSuccessMessage("\nSuccessfully Updated\n");
                                        } else {
                                            packetforsend = new DataPacket();
                                            packetforsend.setSuccessMessage("\nInvalid Id\n");
                                        }
                                        sendDataToClient(packetforsend);
                                    } catch (Exception exx) {
                                        System.out.println(exx.getMessage());
                                    }
                                    break;
                                case 4:
                                    try {
                                        if (!Plot.readAllData().isEmpty()) {
                                            packetforsend = new DataPacket(null, Plot.readAllData(), null);
                                        } else {
                                            packetforsend = new DataPacket();
                                            packetforsend.setSuccessMessage("Nothing To Show Here");
                                        }
                                        sendDataToClient(packetforsend);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 5:
                                    try {
                                        if (Plot.validateId(recievingPacket.getId())) {
                                            packetforsend = new DataPacket();
                                            packetforsend.setSuccessMessage("\nFound\n");
                                            sendDataToClient(packetforsend);
                                        } else {
                                            packetforsend = new DataPacket();
                                            packetforsend.setSuccessMessage("\nNot Found\n");
                                            sendDataToClient(packetforsend);
                                        }
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 0:
                                    break;

                            }
                            break;
                        case 2:
                            switch (c) {
                                case 1:
                                    try {
                                        House h = recievingPacket.getH();
                                        House.writeToFile(h);
                                        packetforsend = new DataPacket();
                                        packetforsend.setSuccessMessage("\nHouse Added !\n");
                                        sendDataToClient(packetforsend);
                                    } catch (Exception es) {
                                        System.out.println(es.getMessage());
                                    }

                                    break;
                                case 2:
                                    try {
                                        if (House.validateId(recievingPacket.getId())) {
                                            House.delete(recievingPacket.getId());
                                            packetforsend = new DataPacket();
                                            packetforsend.setSuccessMessage("\nSuuccessfully Deleted\n");
                                        } else {
                                            packetforsend = new DataPacket();
                                            packetforsend.setSuccessMessage("\nInvalid Id\n");
                                        }
                                        sendDataToClient(packetforsend);
                                    } catch (Exception exx) {
                                        System.out.println(exx.getMessage());
                                    }
                                    break;
                                case 3:
                                    try {
                                        ArrayList<House> pl = House.readAllData();
                                        if (Plot.validateId(recievingPacket.getId())) {
                                            for (House pro : pl) {
                                                if (pro.getId() == recievingPacket.getId()) {
                                                    pro.setNewPrices(recievingPacket.getId(), recievingPacket.getPrice(), recievingPacket.getRprice());
                                                    break;
                                                }
                                            }
                                            packetforsend = new DataPacket();
                                            packetforsend.setSuccessMessage("\nSuccessfully Updated\n");
                                        } else {
                                            packetforsend = new DataPacket();
                                            packetforsend.setSuccessMessage("\nInvalid Id\n");
                                        }
                                        sendDataToClient(packetforsend);
                                    } catch (Exception exx) {
                                        System.out.println(exx.getMessage());
                                    }
                                    break;
                                case 4:
                                    try {
                                        if (!House.readAllData().isEmpty()) {
                                            packetforsend = new DataPacket(House.readAllData(), null, null);
                                        } else {
                                            packetforsend = new DataPacket();
                                            packetforsend.setSuccessMessage("Nothing To Show Here");
                                        }
                                        sendDataToClient(packetforsend);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 5:
                                    try {
                                        if (House.validateId(recievingPacket.getId())) {
                                            packetforsend = new DataPacket();
                                            packetforsend.setSuccessMessage("\nFound\n");
                                            sendDataToClient(packetforsend);
                                        } else {
                                            packetforsend = new DataPacket();
                                            packetforsend.setSuccessMessage("\nNot Found\n");
                                            sendDataToClient(packetforsend);
                                        }
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 6:
                                    try {
                                        //House Operation
                                        if (House.validateId(recievingPacket.getId())) {
                                            House pp = new House();
                                            for (House plo : House.readAllData()) {
                                                if (plo.getId() == recievingPacket.getId()) {
                                                    pp = plo;
                                                    pp.backFromRent(recievingPacket.getId());
                                                    break;
                                                }
                                            }
                                            packetforsend = new DataPacket();
                                            packetforsend.setSuccessMessage("\nHouse is Now Available for Selling !\n");
                                        } else {
                                            packetforsend = new DataPacket();
                                            packetforsend.setSuccessMessage("\nInvalid House Id\n");
                                            sendDataToClient(packetforsend);
                                            break;
                                        }
                                        sendDataToClient(packetforsend);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                case 0:
                                    break;
                            }
                            break;
                        case 0:
                            break;

                    }
                    break;
                case 2:
                    switch (b) {
                        case 1:
                            try {
                                ArrayList<Plot> pl = new ArrayList<>();
                                for (Plot p : Plot.readAllData()) {
                                    if (!p.isSoldStatus()) {
                                        pl.add(p);
                                    }
                                }
                                ArrayList<House> hl = new ArrayList<>();
                                for (House p : House.readAllData()) {
                                    if (!p.isSoldStatus() && !p.isRentStatus()) {
                                        hl.add(p);
                                    }
                                }
                                packetforsend = new DataPacket(hl, pl, null);
                                sendDataToClient(packetforsend);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 2:
                            try {
                                if (Buyer.validateId(recievingPacket.getBid())) {
                                    for (Buyer pro : Buyer.readAllData()) {
                                        if (pro.getId() == recievingPacket.getBid()) {
                                            //Plot Operation
                                            if (Plot.validateId(recievingPacket.getId())) {
                                                Plot pp = new Plot();
                                                for (Plot plo : Plot.readAllData()) {
                                                    if (plo.getId() == recievingPacket.getId()) {
                                                        pp = plo;
                                                        pp.goingToSold(pro, recievingPacket.getId());
                                                        break;
                                                    }
                                                }
                                                packetforsend = new DataPacket();
                                                packetforsend.setRecipt(new Recipt(pro, pp));
                                            } else {
                                                packetforsend = new DataPacket();
                                                packetforsend.setSuccessMessage("\nInvalid Plot Id\n");
                                                sendDataToClient(packetforsend);
                                                break;
                                            }

                                            break;
                                        }
                                    }
                                } else {
                                    packetforsend = new DataPacket();
                                    packetforsend.setSuccessMessage("\nInvalid Buyer Id\n");
                                    sendDataToClient(packetforsend);
                                    break;
                                }
                                sendDataToClient(packetforsend);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 3:
                            try {
                                if (Buyer.validateId(recievingPacket.getBid())) {
                                    for (Buyer pro : Buyer.readAllData()) {
                                        if (pro.getId() == recievingPacket.getBid()) {
                                            //House Operation
                                            if (House.validateId(recievingPacket.getId())) {
                                                House pp = new House();
                                                for (House plo : House.readAllData()) {
                                                    if (plo.getId() == recievingPacket.getId()) {
                                                        pp = plo;
                                                        pp.goingToSold(pro, recievingPacket.getId());
                                                        break;
                                                    }
                                                }
                                                packetforsend = new DataPacket();
                                                packetforsend.setRecipt(new Recipt(pro, pp));
                                            } else {
                                                packetforsend = new DataPacket();
                                                packetforsend.setSuccessMessage("\nInvalid House Id\n");
                                                sendDataToClient(packetforsend);
                                                break;
                                            }

                                            break;
                                        }
                                    }
                                } else {
                                    packetforsend = new DataPacket();
                                    packetforsend.setSuccessMessage("\nInvalid Buyer Id\n");
                                    sendDataToClient(packetforsend);
                                    break;
                                }
                                sendDataToClient(packetforsend);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 4:
                            try {
                                if (Buyer.validateId(recievingPacket.getBid())) {
                                    for (Buyer pro : Buyer.readAllData()) {
                                        if (pro.getId() == recievingPacket.getBid()) {
                                            //House Operation
                                            if (House.validateId(recievingPacket.getId())) {
                                                House pp = new House();
                                                for (House plo : House.readAllData()) {
                                                    if (plo.getId() == recievingPacket.getId()) {
                                                        pp = plo;
                                                        pp.goingToRent(pro, recievingPacket.getId());
                                                        break;
                                                    }
                                                }
                                                packetforsend = new DataPacket();
                                                packetforsend.setRecipt(new Recipt(pro, pp));
                                            } else {
                                                packetforsend = new DataPacket();
                                                packetforsend.setSuccessMessage("\nInvalid House Id\n");
                                                sendDataToClient(packetforsend);
                                                break;
                                            }

                                            break;
                                        }
                                    }
                                } else {
                                    packetforsend = new DataPacket();
                                    packetforsend.setSuccessMessage("\nInvalid Buyer Id\n");
                                    sendDataToClient(packetforsend);
                                    break;
                                }
                                sendDataToClient(packetforsend);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 5:
                            try {
                                Buyer bu = recievingPacket.getB();
                                Buyer.writeToFile(bu);
                                packetforsend = new DataPacket();
                                packetforsend.setSuccessMessage("\nBuyer Added !\n");
                                sendDataToClient(packetforsend);
                            } catch (Exception es) {
                                System.out.println(es.getMessage());
                            }
                            break;
                        case 6:
                            try {
                                if (!Buyer.readAllData().isEmpty()) {
                                    packetforsend = new DataPacket(null, null, Buyer.readAllData());
                                } else {
                                    packetforsend = new DataPacket();
                                    packetforsend.setSuccessMessage("Nothing To Show Here");
                                }
                                sendDataToClient(packetforsend);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 0:
                            break;

                    }
                    break;
                case 0:
                    break;
            }

            try {

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public void sendDataToClient(DataPacket obj) {
        try {

            DatagramSocket sendSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("127.0.0.1");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(obj);
            byte[] data = outputStream.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, 5133);
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

    public DataPacket recieveDataFromClient() throws ClassNotFoundException {
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
        propertyServer p = new propertyServer();
        p.propertyManagmentSystem();
    }
}
