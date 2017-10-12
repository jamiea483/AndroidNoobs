package com.filip.teenagemutantmonsterrampage;

import com.filip.androidgames.framework.FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by jamie on 10/3/2017.
 */

public class Settings {
    public static boolean soundEnabled = true;
    public static boolean musicEnabled = true;
    public static int[] highscores = new int[] {100, 80, 50, 30, 10};

    public static void load(FileIO files)
    {
        BufferedReader in = null;
        try
        {
            in = new BufferedReader(new InputStreamReader(files.readFile(".mrnom")));
            soundEnabled = Boolean.parseBoolean(in.readLine());
            for(int i = 0; i< highscores.length; i++)
            {
                highscores[i] = Integer.parseInt(in.readLine());
            }
        }
        catch(IOException e)
        {

        }
        catch (NumberFormatException e)
        {

        }
        finally
        {
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch(IOException e)
            {

            }

        }
    }

    public static void save(FileIO files)
    {
        BufferedWriter out = null;
        try
        {
            out = new BufferedWriter(new OutputStreamWriter(files.writeFile(".mrnom")));
            out.write(Boolean.toString(soundEnabled));
            out.write("\n");
            for(int i = 0;i<highscores.length;i++)
            {
                out.write(Integer.toString(highscores[i]));
                out.write("\n");
            }
        }
        catch (IOException e)
        {

        }
        finally
        {
            try
            {
                if(out != null) {
                    out.close();
                }
            }
            catch (IOException e)
            {

            }
        }
    }

    public static void addScore(int Score)
    {
        for(int i = 0; i < highscores.length; i++)
        {
            if (highscores[i]< Score)
            {
                for(int j = highscores.length-1; j > i; j--)
                {
                    highscores[j] = highscores[j - 1];
                    highscores[i] = Score;
                    break;
                }
            }
        }
    }
}
