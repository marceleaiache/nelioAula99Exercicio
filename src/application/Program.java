package application;

import entities.Employee;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Employee> list = new ArrayList<>();
        int numbOfEmployees;

        while (true) {
            System.out.println("How many employees will be registered?");
            if (sc.hasNextInt()) {
                numbOfEmployees = sc.nextInt();
                sc.nextLine();
                break;
            } else {
                System.out.println("The entered number needs to be an integer.");
                System.out.println("Please, how many employees will be registered? ");
                sc.nextLine();
            }
        }

        for (int i = 0; i < numbOfEmployees; i++) {
            System.out.printf("Employee #%d: ", i + 1);
            System.out.println("Id: ");
            int id = sc.nextInt();
            sc.nextLine();

            while (hasId(list, id)) {
                System.out.print("Id already taken. Try again: ");
                id = sc.nextInt();
                sc.nextLine();
            }

            System.out.println("Name: ");
            String name = sc.nextLine();
            System.out.println("Salary: ");
            double salary = sc.nextDouble();
            sc.nextLine();

            Employee employee = new Employee(id, name, salary);
            list.add(employee);
        }

        System.out.println("Enter the employee id that will have salary increase: ");
        int idEmployee = sc.nextInt();
        sc.nextLine();

        Integer pos = position(list, idEmployee);

        if (pos == null) {
            System.out.println("This id does not exist!");
        } else {
            System.out.println("Enter the percentage: ");
            double percentage = sc.nextDouble();
            sc.nextLine();
            list.get(pos).increaseSalary(percentage);
        }

        System.out.println("List of employees: ");
        System.out.println();
        for (Employee obj : list) {
            System.out.println(obj);
        }

        sc.close();
    }

    //MÉTODOS/CLASSES PARA VERIFICAR SE O ID INFORMADO EXISTE NA LISTA OU NÃO
    public static Integer position(List<Employee> list, int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return i;
            }
        }
        return null;
    }

    public static boolean hasId(List<Employee> list, int id) {
        Employee emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        return emp != null;
    }
}