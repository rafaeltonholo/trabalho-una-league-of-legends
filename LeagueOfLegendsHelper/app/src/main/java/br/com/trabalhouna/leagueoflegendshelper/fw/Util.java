package br.com.trabalhouna.leagueoflegendshelper.fw;

/**
 * Created by Rafael
 *
 * @since 11/06/2015
 */

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String readString(InputStream i) throws IOException {
        byte[] bytes = readBytes(i);
        String texto = new String(bytes);
        return texto;
    }

    public static byte[] readBytes(InputStream i) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        try {

            byte[] buffer = new byte[1024];
            int len;
            while ((len = i.read(buffer)) > 0) {
                b.write(buffer, 0, len);
            }
            byte[] bytes = b.toByteArray();
            return bytes;

        } finally {
            b.close();
            i.close();
        }
    }

    public static String md5(String valor) {
        String sen = "";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger hash = new BigInteger(1, md.digest(valor.getBytes()));
        sen = hash.toString(16);
        return sen;
    }

    public static <T> T convertFromJson(String json, Class<?> clazz) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return (T) gson.fromJson(json, clazz);
    }

    public static <T> T convertFromJson(String json, Type type) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        return gson.fromJson(json, type);
    }

    public static String convertTime(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return format.format(date);
    }

    /**
     * Pega uma imagem da pasta assets do aplicativo e retorna um drawable para ser usado em um ImageView
     *
     * @param context  Contexto da aplicação
     * @param filePath Caminho completo do arquivo, incluindo sua extensão
     * @return Drawable para preencher um ImageView
     * @see android.widget.ImageView
     */
    public static Drawable getImageFromAssets(Context context, String filePath) {
        AssetManager manager = context.getAssets();
        InputStream in = null;
        Drawable drawable = null;

        try {
            in = manager.open(filePath);
            drawable = Drawable.createFromStream(in, filePath);
        } catch (IOException e) {
            Log.e(Util.class.getSimpleName(), e.getMessage(), e);
        } catch (Exception e) {
            Log.e(Util.class.getSimpleName(), "[ Unexpected Exception ] " + e.getMessage(), e);
        }

        return drawable;
    }

    public static boolean measureListView(ListView listView, int offset) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() * (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + offset + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }
    }
}
