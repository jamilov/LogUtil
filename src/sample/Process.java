package sample;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Process {
    public static String readFile(String path){
        String result = "";
        try{
            FileInputStream fis = new FileInputStream(path);
            Scanner sc = new Scanner(fis);
            while(sc.hasNextLine()){
                result += sc.nextLine();
            }
            fis.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<String> parseMessageIDs(String xml)
    {
        ArrayList<String> res = new ArrayList<>();
        String[] tags = xml.split("[<>]");
        for(int i = 0; i < tags.length; i++){
            if(tags[i].equals("ns2:OriginalMessageId")){
                res.add(tags[i + 1]);
            }
            if(tags[i].equals("ns2:MessageID")){
                res.add(tags[i + 1]);
            }
        }
        return res;
    }

    public static boolean compareMessageIDs(String messageID, String path)
    {
        ArrayList<String> MessageIDs = parseMessageIDs(readFile(path));
        return MessageIDs.contains(messageID);
    }

    public static String returnNameOfFile(String path, String messageID) throws NullPointerException
    {
        File file = new File(path);
        String[] files = file.list();
        String newpath;
        try {
            for (String s : files) {
                if (s.contains("xml")) {
                    newpath = path + s;
                    if (compareMessageIDs(messageID, newpath)) {
                        return s;
                    }
                }
            }
        }catch (NullPointerException np)
        {
            System.out.println("Нет файлов в каталоге " + np.getMessage());
        }
        return "Not Found";
    }
}
