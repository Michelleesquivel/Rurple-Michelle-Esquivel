import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

public class Robot {
    public Map mapa;
    int x;
    int y;
    private int direction = 0;  //0 arriba, 1 derecha, 2 abajo, 3 Izaquieda

    public Robot (Map mapa) {
        this.mapa = mapa;
        direction = this.mapa.getDir();
        x = this.mapa.getxDir();
        y = this.mapa.getyDir();
    }

    public void readSol() {
        try{
            List<String> lines = Files.readAllLines(
                    Paths.get("C:\\Users\\NO\\Documents\\sOLUCION2.txt"),
                    StandardCharsets.UTF_8
            );

            mapa.writeMap();
            sleep(2000);
            for(int i =0;i < lines.size();i++){
                understandingMove(lines.get(i));
                mapa.writeMap();
                sleep(2000);
            }

            if(mapa.getCoins() == 0){
                System.out.println("Gano :D usted recogio todas las fichas");
            }else{
                System.out.println("Perdio :( no recogio todas las fichas");
            }
        } catch (IOException exception){
            System.out.println("Error!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void understandingMove(String line){
        switch(line.trim()){
            case "ROTATE":
                System.out.println("Rotando");
                rotate();
                break;

            case "MOVE":
                System.out.println("Avanzando");
                move();
                break;

            case "PICK":
                System.out.println("Consumiendo ficha");
                pick();
                break;

            default:
        }
    }
     //0 arriba, 1 derecha, 2 abajo, 3 Izaquieda
    public void rotate(){
        switch(direction){
            case 0:
                direction = 1;
                mapa.updatePos('>',y,x);
                break;

            case 1:
                direction = 2;
                mapa.updatePos('v',y,x);
                break;

            case 2:
                direction = 3;
                mapa.updatePos('<',y,x);
                break;

            case 3:
                direction = 0;
                mapa.updatePos('^',y,x);
                break;

            default:
        }
    }

    public void move(){
        switch(direction){
            case 0:
                if(mapa.type(y - 1,x) == 2){
                    if(mapa.type(y,x) != 1){
                        mapa.updatePos(' ',y,x);
                    }

                    mapa.updatePos('^',y - 1,x);
                    y--;
                }

                if(mapa.type(y - 1,x) == 1){
                    mapa.updatePos(' ',y,x);
                    y--;
                }
                break;

            case 1:
                if(mapa.type(y,x + 1) == 2){
                    if(mapa.type(y,x) != 1){
                        mapa.updatePos(' ',y,x);
                    }
                    mapa.updatePos('>',y ,x + 1);
                    x++;
                }

                if(mapa.type(y ,x + 1) == 1){
                    mapa.updatePos(' ',y,x);
                    x++;
                }

                break;

            case 2:
                if(mapa.type(y + 1,x) == 2){
                    if(mapa.type(y,x) != 1){
                        mapa.updatePos(' ',y,x);
                    }
                    mapa.updatePos('v',y + 1,x);
                    y++;
                }

                if(mapa.type(y + 1,x) == 1){
                    mapa.updatePos(' ',y,x);
                    y++;
                }
                break;

            case 3:
                if(mapa.type(y,x - 1) == 2){
                    if(mapa.type(y,x) != 1){
                        mapa.updatePos(' ',y,x);
                    }
                    mapa.updatePos('<',y,x - 1);
                    x--;
                }

                if(mapa.type(y ,x - 1) == 1){
                    mapa.updatePos(' ',y,x);
                    x--;
                }
                break;
        }
    }

    public void pick(){
        if(mapa.type(y,x) == 1){
            int coin = Character.getNumericValue(mapa.getPos(y,x));
            mapa.substractCoints();
            if(--coin == 0){
                switch(direction){
                    case 0:
                        mapa.updatePos('^',y,x);
                        break;

                    case 1:
                        mapa.updatePos('>',y,x);
                        break;

                    case 2:
                        mapa.updatePos('v',y,x);
                        break;

                    case 3:
                        mapa.updatePos('<',y,x);
                        break;
                }
            }else{
                mapa.updatePos((char)(Character.getNumericValue(mapa.getPos(y,x) - 1)+'0'),y,x);
            }
        }
    }

}
