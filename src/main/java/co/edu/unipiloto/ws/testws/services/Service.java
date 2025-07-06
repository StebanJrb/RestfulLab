/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unipiloto.ws.testws.services;

import co.edu.unipiloto.ws.testws.entity.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("service")
public class Service {
    private static Map <Integer, Person> mapPersonas = new HashMap<>();
    static {
        for (int i = 0; i < 10; i++) {
            int id = i+1;
            Person p = new Person(
                    new Random().nextInt(41)+18, 
                    "Personamaravillosa"+id,
                    id
            );
            
            /**Person p = new Person();
            int id = i+1;
            p.setId(id);
            p.setFullName("Perona Maravillosa "+id);
            p.setAge(new Random().nextInt());
            **/ 
            mapPersonas.put(id, p);
        }
    }
    @GET
    @Path("/getAllPersonsInXml")
    @Produces (MediaType.APPLICATION_XML)
    public List<Person>getAllPersonsInXml(){
       return new ArrayList<Person>(mapPersonas.values());
       
    }
    
    @GET
    @Path("/getAllPersonsInJSon")
    @Produces (MediaType.APPLICATION_JSON)
    public List<Person>getAllPersonsInJSon(){
       return new ArrayList<Person>(mapPersonas.values());
    }
    
    @GET
    @Path("/getAllPersonsByIdJson/{id}")
    @Produces (MediaType.APPLICATION_JSON)
    public Person getAllPersonsByIdJson(@PathParam("id")int id){
       return mapPersonas.get(id);
    }
    
    @POST
    @Path("/addPersonsByIdJson")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Person addPersonsInJson(Person p){
        mapPersonas.put(p.getId(), p);
        return p;
    }
    
    @GET
    @Path("/getAverageSalary")
    @Produces (MediaType.APPLICATION_XML)
    public String getAverageSalary(){
        double sum = 0;
        for(Map.Entry<Integer, Person> P : mapPersonas.entrySet()){
            sum += P.getValue().getSalary();
        }
        double averageSalary = sum / mapPersonas.size();
        System.out.println(mapPersonas.size());
        
        return "<averageSalary>" + averageSalary + "</averageSalary>";
    }
    
    @GET
    @Path("/getSumAllSalaries")
    @Produces (MediaType.APPLICATION_JSON)
    public double getSumAllSalaries(){
        double sumSalary = 0;
        for(Map.Entry<Integer, Person> P : mapPersonas.entrySet()){
            sumSalary += P.getValue().getSalary();
        }
        System.out.println(sumSalary);
       return sumSalary;
    }

}
