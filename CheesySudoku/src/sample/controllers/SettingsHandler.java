package sample.controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

class SettingsHandler {
    private static final String SETTINGS_FILE = "settings.txt";

    static HashMap<String, Boolean> getAll() {
        HashMap<String, Boolean> result = new HashMap<>();

        try {
            FileReader reader = new FileReader(SETTINGS_FILE);
            BufferedReader breader = new BufferedReader(reader);

            String line;
            while((line = breader.readLine()) != null) {
                result.put(line.split("=")[0], line.split("=")[1].equals("true"));
            }

            breader.close();
        }
        catch (IOException ex) {
            Logger.getGlobal().log(Level.SEVERE, "Cannot read settings.");
        }

        return result;
    }

    static void set(String setting, boolean value) {
        try {
            ArrayList<String> lines = new ArrayList<>();
            int neededLine = -1;
            if(new File("settings.txt").exists()) {
                FileReader reader = new FileReader("settings.txt");
                BufferedReader breader = new BufferedReader(reader);
                int lineNum = 0;
                String line;

                while ((line = breader.readLine()) != null) {
                    lines.add(line);
                    if(line.split("=")[0].equals(setting)) {
                        neededLine = lineNum;
                    }
                    lineNum ++;
                }

                breader.close();
            }


            FileWriter writer = new FileWriter("settings.txt", false);
            for (int i = 0; i < lines.size(); i ++) {
                if(i != neededLine) {
                    writer.write(lines.get(i) + "\n");
                }
                else {
                    writer.write(lines.get(i).split("=")[0] + "=" + (value ? "true" : "false") + "\n");
                }
            }
            if(neededLine == -1) {
                writer.write(setting + "=" + (value ? "true" : "false"));
            }
            writer.close();
        }
        catch (IOException ex) {
            Logger.getGlobal().log(Level.SEVERE, "Cannot save settings.");
        }
    }

}
