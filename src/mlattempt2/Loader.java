/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlattempt2;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author voice
 */
public class Loader {
    //jpeg header for a 68*103 jpg
    public static byte[] header = new byte[]
    {
        -1,
        -40,
        -1,
        -32,
        0,
        16,
        74,
        70,
        73,
        70,
        0,
        1,
        2,
        0,
        0,
        1,
        0,
        1,
        0,
        0,
        -1,
        -37,
        0,
        67,
        0,
        8,
        6,
        6,
        7,
        6,
        5,
        8,
        7,
        7,
        7,
        9,
        9,
        8,
        10,
        12,
        20,
        13,
        12,
        11,
        11,
        12,
        25,
        18,
        19,
        15,
        20,
        29,
        26,
        31,
        30,
        29,
        26,
        28,
        28,
        32,
        36,
        46,
        39,
        32,
        34,
        44,
        35,
        28,
        28,
        40,
        55,
        41,
        44,
        48,
        49,
        52,
        52,
        52,
        31,
        39,
        57,
        61,
        56,
        50,
        60,
        46,
        51,
        52,
        50,
        -1,
        -37,
        0,
        67,
        1,
        9,
        9,
        9,
        12,
        11,
        12,
        24,
        13,
        13,
        24,
        50,
        33,
        28,
        33,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        50,
        -1,
        -64,
        0,
        17,
        8,
        0,
        103,
        0,
        68,
        3,
        1,
        34,
        0,
        2,
        17,
        1,
        3,
        17,
        1,
        -1,
        -60,
        0,
        31,
        0,
        0,
        1,
        5,
        1,
        1,
        1,
        1,
        1,
        1,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9,
        10,
        11,
        -1,
        -60,
        0,
        -75,
        16,
        0,
        2,
        1,
        3,
        3,
        2,
        4,
        3,
        5,
        5,
        4,
        4,
        0,
        0,
        1,
        125,
        1,
        2,
        3,
        0,
        4,
        17,
        5,
        18,
        33,
        49,
        65,
        6,
        19,
        81,
        97,
        7,
        34,
        113,
        20,
        50,
        -127,
        -111,
        -95,
        8,
        35,
        66,
        -79,
        -63,
        21,
        82,
        -47,
        -16,
        36,
        51,
        98,
        114,
        -126,
        9,
        10,
        22,
        23,
        24,
        25,
        26,
        37,
        38,
        39,
        40,
        41,
        42,
        52,
        53,
        54,
        55,
        56,
        57,
        58,
        67,
        68,
        69,
        70,
        71,
        72,
        73,
        74,
        83,
        84,
        85,
        86,
        87,
        88,
        89,
        90,
        99,
        100,
        101,
        102,
        103,
        104,
        105,
        106,
        115,
        116,
        117,
        118,
        119,
        120,
        121,
        122,
        -125,
        -124,
        -123,
        -122,
        -121,
        -120,
        -119,
        -118,
        -110,
        -109,
        -108,
        -107,
        -106,
        -105,
        -104,
        -103,
        -102,
        -94,
        -93,
        -92,
        -91,
        -90,
        -89,
        -88,
        -87,
        -86,
        -78,
        -77,
        -76,
        -75,
        -74,
        -73,
        -72,
        -71,
        -70,
        -62,
        -61,
        -60,
        -59,
        -58,
        -57,
        -56,
        -55,
        -54,
        -46,
        -45,
        -44,
        -43,
        -42,
        -41,
        -40,
        -39,
        -38,
        -31,
        -30,
        -29,
        -28,
        -27,
        -26,
        -25,
        -24,
        -23,
        -22,
        -15,
        -14,
        -13,
        -12,
        -11,
        -10,
        -9,
        -8,
        -7,
        -6,
        -1,
        -60,
        0,
        31,
        1,
        0,
        3,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        1,
        0,
        0,
        0,
        0,
        0,
        0,
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9,
        10,
        11,
        -1,
        -60,
        0,
        -75,
        17,
        0,
        2,
        1,
        2,
        4,
        4,
        3,
        4,
        7,
        5,
        4,
        4,
        0,
        1,
        2,
        119,
        0,
        1,
        2,
        3,
        17,
        4,
        5,
        33,
        49,
        6,
        18,
        65,
        81,
        7,
        97,
        113,
        19,
        34,
        50,
        -127,
        8,
        20,
        66,
        -111,
        -95,
        -79,
        -63,
        9,
        35,
        51,
        82,
        -16,
        21,
        98,
        114,
        -47,
        10,
        22,
        36,
        52,
        -31,
        37,
        -15,
        23,
        24,
        25,
        26,
        38,
        39,
        40,
        41,
        42,
        53,
        54,
        55,
        56,
        57,
        58,
        67,
        68,
        69,
        70,
        71,
        72,
        73,
        74,
        83,
        84,
        85,
        86,
        87,
        88,
        89,
        90,
        99,
        100,
        101,
        102,
        103,
        104,
        105,
        106,
        115,
        116,
        117,
        118,
        119,
        120,
        121,
        122,
        -126,
        -125,
        -124,
        -123,
        -122,
        -121,
        -120,
        -119,
        -118,
        -110,
        -109,
        -108,
        -107,
        -106,
        -105,
        -104,
        -103,
        -102,
        -94,
        -93,
        -92,
        -91,
        -90,
        -89,
        -88,
        -87,
        -86,
        -78,
        -77,
        -76,
        -75,
        -74,
        -73,
        -72,
        -71,
        -70,
        -62,
        -61,
        -60,
        -59,
        -58,
        -57,
        -56,
        -55,
        -54,
        -46,
        -45,
        -44,
        -43,
        -42,
        -41,
        -40,
        -39,
        -38,
        -30,
        -29,
        -28,
        -27,
        -26,
        -25,
        -24,
        -23,
        -22,
        -14,
        -13,
        -12,
        -11,
        -10,
        -9,
        -8,
        -7,
        -6,
        -1,
        -38,
        0,
        12,
        3,
        1,
        0,
        2,
        17,
        3,
        17,
        0,
        63,
        0
    };
    public static byte[] closer = new byte[]{
        -1,
        -39
    };
    public static double[] resize(double[] arr)
    {
        double[] retVal = new double[arr.length*2];
        int sent = 0;
        for (double d : arr)
            retVal[sent++] = d;
        return retVal;
    }
    /**
     * Assumes that trailing zeros are not desired
     * @param arr
     * @return arr without 0's
     */
    public static double[] desize(double[] arr)
    {
        int i = arr.length-1;
        for (; arr[i] == 0; i--)
            i = i;
        double[] retVal = new double[i];
        for (int b = 0; b < retVal.length; b++)
            retVal[b] = arr[b];
        return retVal;
    }
    public static double[][] loadDJIA()
    {
        URL path = GeneticProblemSolver.class.getResource("DIJA.txt");
        File f = new File(path.getFile());
        BufferedReader reader;
        try 
        {
            reader = new BufferedReader(new FileReader(f));
        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println("FILE NOT FOUND");
            ex.printStackTrace();
            return new double[][]{};
        }
        double[] inputs = new double[10];
        double[] outputs =new double[10];
        String[] split;
        String s = "";
        try 
        {
            int sent = 0;
            int dayCounter = 0;
            double lastDay = 0;
            double day;
            double val;
            while ((s = reader.readLine()) != null)
            {
                //System.out.println(s);
                split = s.split("	");

                if (split.length != 2) continue;
                
                
                try 
                {
                    day = Double.parseDouble(split[0].substring(split[0].length()-2));
                    val = Double.parseDouble(split[1]);
                }
                catch (Exception ex)
                {
                    continue;
                }
                
                if (inputs.length < sent*2)
                {
                    inputs = resize(inputs);
                    outputs = resize(outputs);
                }
                
                
                if (sent != 0)
                {
                    //New Month
                    if (lastDay - day > 0)
                        dayCounter += day;
                    //Day
                    else
                        dayCounter += day-lastDay;
                    lastDay = day;
                }
                else
                {
                    lastDay = day;
                    dayCounter += day;
                }
                inputs[sent] = dayCounter*1.0;
                outputs[sent++] = val;
                
            }
        } 
        catch (IOException ex) 
        {
            System.out.println("BROKE WHILE READING");
            ex.printStackTrace();
            return new double[][]{};
        }
        catch (Exception ex)
        {
            System.out.println("BROKE IN ANOTHER WAY");
            ex.printStackTrace();
            return new double[][]{};
        }
        inputs = desize(inputs);
        outputs = desize(outputs);
        return new double[][]{inputs, outputs};
    }
    public static int[] loadImageRGB(String imagePath)
    {
        URL path = GeneticProblemSolver.class.getResource(imagePath);
        File f = new File(path.getFile());
        BufferedImage image;
        try {
            image = ImageIO.read(f);
        } catch (IOException ex) {
            System.out.println("Error loading image");
            return new int[]{};
        }
        int[] retVal = new int[image.getWidth()*image.getHeight() + 2];
        
        retVal[retVal.length-2] = image.getWidth();
        retVal[retVal.length-1] = image.getHeight();
        
        int sent = 0;
        for (int i = 0; i < image.getHeight(); i++)
            for (int w = 0; w < image.getWidth(); w++)
                retVal[sent++] = image.getRGB(w, i);
        
        return retVal;
    }
    /*public static byte[][] loadMonaLisaBytes()
    {
        URL path = GeneticProblemSolver.class.getResource("Mona_Lisa.jpg");
        File f = new File(path.getFile());
        BufferedImage image;
        try {
            image = ImageIO.read(f);
        } catch (IOException ex) {
            System.out.println("Error loading image");
            return new byte[][]{};
        }
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", ostream);
        } catch (IOException ex) {
            System.out.println("Failed to write to bytearrayoutputstream");
            return new byte[][]{};
        }
        byte[] arr = ostream.toByteArray();
        
        return new byte[][]{arr};
    }
    public static void writeImage(byte[] arr, String filename)
    {
       
        File f = new File(filename + ".jpg");
        
        ByteArrayInputStream arrstream = new ByteArrayInputStream(arr);
        BufferedImage image;
        try {
            image = ImageIO.read(arrstream);
            
        } catch (IOException ex) {
            System.out.println("Error reading to image");
            return;
        }
        try {
            ImageIO.write(image, "jpg", f);
        } catch (IOException ex) {
            System.out.println("Failed to write to file");
            return;
        }
        System.out.println("Completed Writing Image");
    }*/

    static void createImage(int width, int height, int[] rgb, String name) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int sent = 0;
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                image.setRGB(x, y, rgb[sent++]);
        
        File output = new File(name+".bmp");
        try {
            ImageIO.write(image, "bmp", output);
        } catch (IOException ex) {
            System.out.println("Failed to write to file");
            return;
        }
    }
}
