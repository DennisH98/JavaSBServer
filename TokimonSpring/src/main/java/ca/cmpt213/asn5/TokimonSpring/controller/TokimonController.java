package ca.cmpt213.asn5.TokimonSpring.controller;

import ca.cmpt213.asn5.TokimonSpring.model.Tokimon;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the rest controller that handles the GET, POST and DELETE requests on the server
 * @author Dennis Huynh
 * @author 3013279204
 */

@RestController
public class TokimonController {

    private List<Tokimon> tokiList = new ArrayList<>();
    JSONController jc= new JSONController();

    @GetMapping("/api/tokimon/all")
    public List<Tokimon> getTokiList(){

        return tokiList;
    }

    @GetMapping("/api/tokimon/{id}")
    public Tokimon getTokimon(@PathVariable int id){
        if(tokiList.isEmpty()){
        return new Tokimon(0,"-",0,0,"-",0,"-");
        }

        for(int i =0 ; i<tokiList.size();i++){
            if(tokiList.get(i).getId() == id){
                return tokiList.get(i);
            }
        }
        return new Tokimon(0,"-",0,0,"-",0,"-");
    }

    @PostMapping("/api/tokimon/add")
    public ResponseEntity<Tokimon> addTokimon(@RequestBody Tokimon toki){
        //add to list
        Tokimon tokimon = new Tokimon(toki.getId(),toki.getName(),toki.getWeight(),toki.getHeight(),
                toki.getAbility(),toki.getStrength(),toki.getColor());

        tokiList.add(tokimon);

        jc.writeToJsonFile(tokiList);
        return new ResponseEntity<Tokimon>(HttpStatus.OK);

    }

    @DeleteMapping("/api/tokimon/{id}")
    public ResponseEntity<Tokimon> deleteTokimon(@PathVariable int id){
        //delete from list
        for(int i =0 ; i<tokiList.size();i++){
            if(tokiList.get(i).getId() == id){
               tokiList.remove(i);
            }
        }
        jc.writeToJsonFile(tokiList);
        return new ResponseEntity<Tokimon>(HttpStatus.NO_CONTENT);
    }

    @PostConstruct
    public void init(){


        tokiList = jc.readJsonFile();

    }


}
