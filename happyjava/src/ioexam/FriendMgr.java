package ioexam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FriendMgr {
    public static void main(String[] args) {
        try(
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter pw = new PrintWriter("myFriendInfo.txt");
        ){
            for (int i = 0; i < 5; i++){
                System.out.println("이름을 입력하세요. ");
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
