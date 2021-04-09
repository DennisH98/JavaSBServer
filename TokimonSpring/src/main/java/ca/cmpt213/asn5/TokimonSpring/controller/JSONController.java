package ca.cmpt213.asn5.TokimonSpring.controller;

import ca.cmpt213.asn5.TokimonSpring.model.Tokimon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class reads and writes JSON that is used with Tokimon Controller
 * @author Dennis Huynh
 * @author 301379204
 */

public class JSONController {

    public void writeToJsonFile(List<Tokimon> tokimonList){

        try {
            Gson gson = new GsonBuilder().create();
            FileWriter fw= new FileWriter("data/tokimon.json");
            gson.toJson(tokimonList,fw);
            fw.flush();
            fw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Tokimon> readJsonFile(){
        Gson gson = new Gson();
        try {
            File file= new File("data/tokimon.json");

            if(file.length()==0){
                return new ArrayList<>();
            }else{
                FileReader fr= new FileReader(file);
                //List<Tokimon> list = Arrays.asList(gson.fromJson(fr,Tokimon[].class));
                Tokimon[] tokiArray = gson.fromJson(fr,Tokimon[].class);
                List<Tokimon> tokimonList = new ArrayList<>();
                for(int i=0;i<tokiArray.length;i++){
                    tokimonList.add(tokiArray[i]);
                }

                return tokimonList;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }





}
