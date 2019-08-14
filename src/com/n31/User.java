package com.n31;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    public String name;
    public String surname;
    public String email;
    public List<String> roles = new ArrayList<String>();
    public List<String> phones = new ArrayList<String>();

    public void setName(String nameVal) {
        name = nameVal;
    }
    public void setSurname(String surnameVal) {
        surname = surnameVal;
    }
    public boolean setEmail(String emailVal) {
        Pattern p = Pattern.compile("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$");
        Matcher m = p.matcher(emailVal);
        if (m.matches()) {
            email = emailVal;
            return true;
        }
        else {
            return false;
        }
    }
    public void setRoles(String role) {
        roles.add(role);
    }
    public boolean setPhones(String phone) {
        Pattern p = Pattern.compile("^375[0-9]{9}");
        Matcher m = p.matcher(phone);
        if (m.matches()) {
            phones.add(phone);
            return true;
        }
        else {
            return false;
        }
    }

    public void setUserDataFromInput() {
        Scanner in = new Scanner(System.in);

        System.out.print("Имя: ");
        String name = in.nextLine();
        setName(name);

        System.out.print("Фамилия: ");
        String surname = in.nextLine();
        setSurname(surname);

        String email = "";
        do {
            System.out.print("Email: ");
            email = in.nextLine();
        } while (!setEmail(email));

        for (int i = 1; i <= 3; i++) {
            System.out.print("Роль " + i + ": ");
            String role = in.nextLine();
            setRoles(role);
            if (i != 3) {
                System.out.print("Хотите добавить еще одну роль? (y/n): ");
                String again = in.nextLine();
                if (!again.equals("y")) {
                    break;
                }
            }
        }

        for (int i = 1; i <= 3; i++) {
            String phone = "";
            do {
                System.out.print("Номер " + i + ": ");
                phone = in.nextLine();
            } while (!setPhones(phone));
            if (i != 3) {
                System.out.print("Хотите добавить еще один номер? (y/n): ");
                String again = in.nextLine();
                if (!again.equals("y")) {
                    break;
                }
            }
        }
    }
}
