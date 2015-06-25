package br.com.trabalhouna.leagueoflegendshelper.to.staticresource;

import java.util.HashMap;

/**
 * Created by Rafael
 *
 * @since 23/06/2015
 */
public class StaticResource<T> {
    private String type;
    private String version;
    private HashMap<String, T> data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public HashMap<String, T> getData() {
        return data;
    }

    public void setData(HashMap<String, T> data) {
        this.data = data;
    }
}
