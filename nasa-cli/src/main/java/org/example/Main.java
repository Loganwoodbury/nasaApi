package org.example;

import org.example.model.Neo;
import org.example.model.NeoFeedResponse;
import org.example.service.NASAService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        System.out.println("Hello to my Near Earth Object App");
        System.out.print("Enter start date (YYYY-MM-DD): ");

        String sDate = userInput.nextLine();

        NASAService service = new NASAService();

        NeoFeedResponse response = service.getAllNeos(sDate, sDate);

        //for each loop -- loop through dates
        for (String key: response.getNearEarthObjects().keySet()){
            List<Neo> neoList = response.getNearEarthObjects().get(key);
            int count = neoList.size();
            System.out.println("for Date: " + key + " there are " + count + " near earth objects.");

            String code = "\u001B[0m";
            for (Neo n: neoList){
                if(n.isPotentiallyHazardousAsteroid()){
                    code = "\u001B[31m";;
                }
                System.out.println(code + "Id: " + n.getId());
                System.out.println("Name: " + n.getName());
                System.out.println("Potentially Hazardous? " + n.isPotentiallyHazardousAsteroid());
                System.out.println("Estimated Diameter: ");
                System.out.println("\tMax in Miles: " + n.getEstimatedDiameter().getMiles().getEstimatedDiameterMax());
                System.out.println("\tMin in Miles: " + n.getEstimatedDiameter().getMiles().getEstimatedDiameterMin());
                code = "\u001B[0m";
                System.out.println("\n*********************************");

            }
        }

    }
}