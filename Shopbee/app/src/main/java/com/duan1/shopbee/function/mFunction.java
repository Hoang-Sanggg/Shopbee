package com.duan1.shopbee.function;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class mFunction {

    public String BitmapToString(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return  temp;
    }

    public Bitmap StringBitMap(String encodeToString){
        try {
            byte[] encodeByte = Base64.decode(encodeToString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch (Exception e){
            return null;
        }
    }

    public static void data(String data, Context context){
        try{
            FileOutputStream fileOutputStream = context.openFileOutput("datata.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }catch (Exception e){

        }
    }

    public static void saveLastMess(String data, String chatId, Context context){
        try{
            FileOutputStream fileOutputStream = context.openFileOutput(chatId+".txt", Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        }catch (Exception e){

        }
    }

    public static String getLastMess(String chatId, Context context){
        String data = "0";

        try{
            FileInputStream fileInputStream = context.openFileInput(chatId+".txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine())!= null){
                stringBuilder.append(line);
            }
            data = stringBuilder.toString();
        }catch (Exception e){

        }
        return data;
    }

    public static String getData( Context context){
        String data = "0";

        try{
            FileInputStream fileInputStream = context.openFileInput("datata.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine())!= null){
                stringBuilder.append(line);
            }
            data = stringBuilder.toString();
        }catch (Exception e){

        }
        return data;
    }

    

}
