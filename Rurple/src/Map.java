import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Map {
    public Character[][] mapa;
    int coins;
    int xRobot;
    int yRobot;
    int iniDir;
    int xLength;
    int yLength;

    public Map(){
        coins =0;

    }

    public void readMap() {
        ArrayList rowcol =  new ArrayList<>();
        Scanner scanner;
        try{
            List<String> lines = Files.readAllLines(
                    Paths.get("C:\\Users\\NO\\Documents\\mapa2.txt"),
                    StandardCharsets.UTF_8
            );

            yLength = lines.size();
            xLength = lines.get(0).length();
            mapa = new Character[yLength][xLength];

            for(int i =0;i < lines.size();i++){
                for(int j =0;j < lines.get(i).length();j++){
                    mapa[i][j] = lines.get(i).charAt(j);
                    if(Character.isDigit(mapa[i][j])){
                        coins+= Character.getNumericValue(mapa[i][j]);
                    }

                    switch(mapa[i][j]){
                        case '^':
                            xRobot = j;
                            yRobot = i;
                            iniDir = 0;
                            break;

                        case '>':
                            xRobot = j;
                            yRobot = i;
                            iniDir = 1;
                            break;

                        case 'v':
                            xRobot = j;
                            yRobot = i;
                            iniDir = 2;
                            break;

                        case '<':
                            xRobot = j;
                            yRobot = i;
                            iniDir = 3;
                            break;

                        default:
                    }
                }
            }
        } catch (IOException exception){
            System.out.println("Error!");
        }
    }

    public Character getPos(int line, int pos){
        return mapa[line][pos];
    }

    public void updatePos(char upchar, int line, int pos){
        mapa[line][pos] = upchar;
    }

    public int type(int line, int pos){
        //Esto es coin
        if(Character.isDigit(mapa[line][pos])){
            return 1;
        }

        //Esto esta vacio
        if(mapa[line][pos] == ' '){
            return 2;
        }

        //Esto es pared
        if(mapa[line][pos] == '*'){
            return 3;
        }

        return 0;
    }

    public int getDir(){
        return iniDir;
    }

    public int getxDir(){
        return xRobot;
    }

    public int getyDir(){
        return yRobot;
    }

    public void writeMap(){
        String line;
        for(int i=0;i < yLength;i++){
            line = "";
            for(int j=0;j < xLength;j++){
                line += mapa[i][j];
            }
            System.out.println(line);
        }
    }
    public int getCoins(){
        return coins;
    }

    public void substractCoints(){
        coins--;
    }

}


