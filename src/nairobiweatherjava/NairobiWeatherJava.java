/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nairobiweatherjava;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import java.io.*;
import java.net.*;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Date;

/**
 *
 * @author Anonymous
 */
public class NairobiWeatherJava extends TimerTask {

    private static final String EXCHANGE_NAME = "logs";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TimerTask timerTask = new NairobiWeatherJava();
        //running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 10 * 1000);
        System.out.println("CheckWeather started");
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        System.out.println("Check weather started at:" + new Date());
        CheckWeather();
        System.out.println("Check weather finished at:" + new Date());

    }

    public String CheckWeather() {
        try {
            //declare the Rabbit MQ connection Factory
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setPort(5672);
            //call the API with the access key
            URL weather = new URL("http://api.weatherstack.com/current?access_key=35dc6112635dbe90e71c8772ebf0b8ee&query=Nairobi");
            URLConnection yc = weather.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            //read the response
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                //parse the json response from the API
                JSONObject jObject = new JSONObject(inputLine);
                JSONObject current = (JSONObject) jObject.get("current");

                //The json array containing weather icons and weather description from the response
                JSONArray weather_icons = current.getJSONArray("weather_icons");
                JSONArray weather_descriptions = current.getJSONArray("weather_descriptions");

                //proceed to send message to the rabbit mq queue
                Connection conn = factory.newConnection();
                Channel channel = conn.createChannel();

                System.out.println("Address+++++++" + conn.getAddress());
                System.out.println("Port+++++++" + conn.getPort());
                channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
                String message = "TIME :" + current.getString("observation_time")
                        + "\nTEMPERATURE:" + current.getInt("temperature")
                        + "\nWEATHER_CODE:" + current.getInt("weather_code")
                        //                        + "\nWEATHER_ICONS:" + weather_icons
                        //                        + "\nWEATHER_DESCRIPTIONS:" + weather_descriptions
                        + "\nWIND_SPEED:" + current.getInt("wind_speed")
                        + "\nWIND_DEGREE:" + current.getInt("wind_degree")
                        + "\nPRESSURE:" + current.getInt("pressure")
                        + "\nPRECIPITATION:" + current.getInt("precip")
                        + "\nHUMIDITY:" + current.getInt("humidity")
                        + "\nCLOUD_COVER:" + current.getInt("cloudcover")
                        + "\nFEELS_LIKE:" + current.getInt("feelslike")
                        + "\nUV_INDEX:" + current.getInt("uv_index")
                        + "\nVISIBILITY:" + current.getInt("visibility")
                        + "\nIS_DAY:" + current.getString("is_day");
                //publish the message with the route key Nairobi Weather
                channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "'");
            }

            in.close();

            return "SENT";
        } catch (Exception ex) {
            Logger.getLogger(NairobiWeatherJava.class.getName()).log(Level.SEVERE, null, ex);
            return "NOT SENT";
        }
    }

}
