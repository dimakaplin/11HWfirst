package com.dimakaplin143.login;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.content.Context.MODE_PRIVATE;

public class StorageControl {

    private Context ctx;
    private FileOutputStream outputStream;

    public StorageControl(Context ctx) {
        this.ctx = ctx;
    }

    public void saveFile(String filename, String fileContent) {
        try
        {
            outputStream = ctx.openFileOutput(filename, MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bw = new BufferedWriter(outputStreamWriter);
            bw.write(fileContent);
            bw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public String readFile(String filename) {
        StringBuilder output = new StringBuilder();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(ctx.openFileInput(filename));

            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();

            while (line != null) {
                output = output.append(line);
                line = reader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public boolean isExist(String fileName) {
        return new File(ctx.getFilesDir(), fileName).exists();
    }


}
