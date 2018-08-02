import java.util.Scanner;

public class Menu {

    Scanner scan;
    public void menu(){
        scan = new Scanner(System.in);
        boolean flag = true;
        Map map = new Map();

        do{
            print("BIENVENIDO AL MENU");
            print("1. Cargar mapa");
            print("2. Cargar movimiento");
            print("3. Salir");

            switch (scan.nextLine()){
                case "1":
                    map.readMap();
                    break;
                case "2":
                    Robot rob = new Robot(map);
                    rob.readSol();
                    break;
                case "3":
                    flag = false;
                    break;

                default:

            }

        }while(flag);
    }

    public void print(String line){
        System.out.println(line);
    }



}
