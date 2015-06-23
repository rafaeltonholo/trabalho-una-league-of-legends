package br.com.trabalhouna.leagueoflegendshelper.to;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Created by Rafael
 *
 * @since 10/06/2015
 */
public class BaseTO implements Cloneable {
    private int _id;

    public BaseTO() {
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    private static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson;
    }

    public static <T extends BaseTO> T createByJson(String json, Class<T> c) {
        try {
            Gson gson = getGson();
            return gson.fromJson(json, c);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static Object createByJson(String json) {
        return getGson().fromJson(json, JsonObject.class);
    }

    @Override
    public String toString() {
        Gson gson = getGson();
        return gson.toJson(this, getClass());
    }
}
