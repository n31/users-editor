package com.n31;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        mainMenu();
    }

    private static void mainMenu() {
        UsersList usersList = new UsersList();
        usersList.checkDbFile();
        Scanner in = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("Главное меню");
            System.out.println("1. Добавить пользователя");
            System.out.println("2. Список пользователей");
            System.out.println("3. Редактировать пользователя");
            System.out.println("4. Удалить пользователя");
            System.out.println("5. Информация о пользователе");
            System.out.println("6. Выход");
            System.out.print("Выберите опцию: ");
            option = in.nextInt();
            switch (option) {
                case 1:
                    createNewUser();
                case 2:
                    showUsersList();
                case 3:
                    editUser();
                case 4:
                    deleteUser();
                case 5:
                    showUserData();
                case 6:
                    System.exit(0);
            }
        } while (option > 6 || option < 1);
    }

    private static void editUser() {
        Scanner in = new Scanner(System.in);
        User u = new User();
        UsersList usersList = new UsersList();
        System.out.print("Введите номер пользователя: ");
        int id = in.nextInt();
        System.out.println("Редактирование пользователя: ");
        u.setUserDataFromInput();
        usersList.editUser(u, id);
        System.out.println("Редактирование успешно завершено");
        mainMenu();
    }

    private static void createNewUser() {
        User u = new User();
        System.out.println("Создание пользователя");
        u.setUserDataFromInput();
        UsersList userList = new UsersList();
        userList.addNewUser(u);
        System.out.println("Новый пользователь успешно добавлен");
        mainMenu();
    }

    private static void deleteUser() {
        Scanner in = new Scanner(System.in);
        UsersList usersList = new UsersList();
        System.out.println("Удаление пользователя");
        System.out.print("Введите номер пользователя: ");
        int id = in.nextInt();
        if (usersList.deleteUser(id)) {
            System.out.println("Пользователь успешно удален");
        }
        else {
            System.out.println("Пользователя с таким номером не существует");
        }
        mainMenu();
    }

    private static void showUserData() {
        Scanner in = new Scanner(System.in);
        UsersList usersList = new UsersList();
        System.out.println("Информация о пользователе");
        System.out.print("Введите номер пользователя: ");
        int id = in.nextInt();
        ArrayList<String> userData = usersList.getUserData(id);
        if (!userData.isEmpty()) {
            System.out.println("Имя: " + userData.get(0));
            System.out.println("Фимилия: " + userData.get(1));
            System.out.println("Email: " + userData.get(2));
            System.out.println("Роль 1: " + userData.get(3));
            System.out.println("Роль 2: " + userData.get(4));
            System.out.println("Роль 3: " + userData.get(5));
            System.out.println("Номер телефона 1: " + userData.get(6));
            System.out.println("Номер телефона 2: " + userData.get(7));
            System.out.println("Номер телефона 3: " + userData.get(8));
        }
        else {
            System.out.println("Пользователя с таким номером не существует");
        }
        mainMenu();
    }

    private static void showUsersList() {
        UsersList usersList = new UsersList();
        System.out.println("Список пользователей:");
        ArrayList<String[]> usersDataList = usersList.getUsersList();
        for (int i = 0; i < usersDataList.size(); i++) {
            System.out.println(i + " " + usersDataList.get(i)[0] + " " + usersDataList.get(i)[1]);
        }
        if (usersDataList.isEmpty()) {
            System.out.println("Список пользователей пуст");
        }
        mainMenu();
    }
}
