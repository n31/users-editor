package com.n31;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class UsersList {
    File dbFile = new File("src/resources/UsersList.xml");
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;

    public void checkDbFile() {
        if (!dbFile.exists()) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            try {
                builder = factory.newDocumentBuilder();
                Document doc = builder.newDocument();
                Element rootElement = doc.createElementNS("", "users_list");
                doc.appendChild(rootElement);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("src/resources/UsersList.xml"));
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(source, result);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }

    public void addNewUser(User u) {
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(dbFile);
            doc.getDocumentElement().normalize();

            Element userName = doc.createElement("name");
            userName.appendChild(doc.createTextNode(u.name));

            Element userSurname = doc.createElement("surname");
            userSurname.appendChild(doc.createTextNode(u.surname));

            Element userEmail = doc.createElement("email");
            userEmail.appendChild(doc.createTextNode(u.email));

            Element userRole1 = doc.createElement("role1");
            userRole1.appendChild(doc.createTextNode(u.roles.get(0)));

            Element userRole2 = doc.createElement("role2");
            String role = (u.roles.size() >= 2) ? u.roles.get(1) : " ";
            userRole2.appendChild(doc.createTextNode(role));

            Element userRole3 = doc.createElement("role3");
            role = (u.roles.size() == 3) ? u.roles.get(2) : " ";
            userRole3.appendChild(doc.createTextNode(role));

            Element userPhone1 = doc.createElement("phone1");
            userPhone1.appendChild(doc.createTextNode(u.phones.get(0)));

            Element userPhone2 = doc.createElement("phone2");
            String phone = (u.phones.size() >= 2) ? u.phones.get(1) : " ";
            userPhone2.appendChild(doc.createTextNode(phone));

            Element userPhone3 = doc.createElement("phone3");
            phone = (u.phones.size() == 3) ? u.phones.get(2) : " ";
            userPhone3.appendChild(doc.createTextNode(phone));

            NodeList users = doc.getElementsByTagName("users_list");
            Element userList = (Element) users.item(0);

            Element user = doc.createElement("user");
            user.appendChild(userName);
            user.appendChild(userSurname);
            user.appendChild(userEmail);
            user.appendChild(userRole1);
            user.appendChild(userRole2);
            user.appendChild(userRole3);
            user.appendChild(userPhone1);
            user.appendChild(userPhone2);
            user.appendChild(userPhone3);

            userList.appendChild(user);


            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/resources/UsersList.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void editUser(User u, int id) {
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(dbFile);
            doc.getDocumentElement().normalize();

            NodeList users = doc.getElementsByTagName("user");
            Element user = (Element) users.item(id);

            Node name = user.getElementsByTagName("name").item(0).getFirstChild();
            name.setNodeValue(u.name);

            Node surname = user.getElementsByTagName("surname").item(0).getFirstChild();
            surname.setNodeValue(u.surname);

            Node email = user.getElementsByTagName("email").item(0).getFirstChild();
            email.setNodeValue(u.email);

            Node role1 = user.getElementsByTagName("role1").item(0).getFirstChild();
            role1.setNodeValue(u.roles.get(0));

            Node role2 = user.getElementsByTagName("role2").item(0).getFirstChild();
            String role = (u.roles.size() >= 2) ? u.roles.get(1) : " ";
            role2.setNodeValue(role);

            Node role3 = user.getElementsByTagName("role3").item(0).getFirstChild();
            role = (u.roles.size() == 3) ? u.roles.get(2) : " ";
            role3.setNodeValue(role);

            Node phone1 = user.getElementsByTagName("phone1").item(0).getFirstChild();
            phone1.setNodeValue(u.phones.get(0));

            Node phone2 = user.getElementsByTagName("phone2").item(0).getFirstChild();
            String phone = (u.phones.size() >= 2) ? u.phones.get(1) : " ";
            phone2.setNodeValue(phone);

            Node phone3 = user.getElementsByTagName("phone3").item(0).getFirstChild();
            phone = (u.phones.size() == 3) ? u.phones.get(2) : " ";
            phone3.setNodeValue(phone);

            doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("src/resources/UsersList.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public boolean deleteUser(int id) {
        boolean isDeleted = false;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(dbFile);
            doc.getDocumentElement().normalize();

            NodeList users = doc.getElementsByTagName("user");
            if (id < users.getLength() && id >= 0) {
                Element user = (Element) users.item(id);
                user.getParentNode().removeChild(user);

                doc.getDocumentElement().normalize();
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("src/resources/UsersList.xml"));
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(source, result);
                isDeleted = true;
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return isDeleted;
    }

    public ArrayList<String> getUserData(int id) {
        ArrayList<String> userData = new ArrayList<String>();
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(dbFile);
            doc.getDocumentElement().normalize();

            NodeList users = doc.getElementsByTagName("user");
            if (id < users.getLength() && id >= 0) {
                Element user = (Element) users.item(id);

                Node name = user.getElementsByTagName("name").item(0).getFirstChild();
                Node surname = user.getElementsByTagName("surname").item(0).getFirstChild();
                Node email = user.getElementsByTagName("email").item(0).getFirstChild();
                Node role1 = user.getElementsByTagName("role1").item(0).getFirstChild();
                Node role2 = user.getElementsByTagName("role2").item(0).getFirstChild();
                Node role3 = user.getElementsByTagName("role3").item(0).getFirstChild();
                Node phone1 = user.getElementsByTagName("phone1").item(0).getFirstChild();
                Node phone2 = user.getElementsByTagName("phone2").item(0).getFirstChild();
                Node phone3 = user.getElementsByTagName("phone3").item(0).getFirstChild();

                userData.add(name.getNodeValue());
                userData.add(surname.getNodeValue());
                userData.add(email.getNodeValue());
                userData.add(role1.getNodeValue());
                userData.add(role2.getNodeValue());
                userData.add(role3.getNodeValue());
                userData.add(phone1.getNodeValue());
                userData.add(phone2.getNodeValue());
                userData.add(phone3.getNodeValue());
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return userData;
    }

    public ArrayList<String[]> getUsersList() {
        ArrayList<String[]> usersList = new ArrayList<String[]>();
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(dbFile);

            NodeList users = doc.getElementsByTagName("user");

            for (int i = 0; i < users.getLength(); i++) {
                Element user = (Element) users.item(i);
                Node name = user.getElementsByTagName("name").item(0).getFirstChild();
                Node surname = user.getElementsByTagName("surname").item(0).getFirstChild();
                String userName = name.getNodeValue();
                String userSurname = surname.getNodeValue();
                String[] userData = new String[] {
                        userName,
                        userSurname
                };
                usersList.add(userData);
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return usersList;
    }
}
