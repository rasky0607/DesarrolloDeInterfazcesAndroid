package es.adrianmmudarra.socialspinner.model;


import androidx.annotation.Nullable;

import java.util.Objects;

public class Social implements Comparable<Social>{
    private int icon;
    private String name;
    private int totalUser;
    private int dolares;

    public Social(int icon, String name) {
        this.icon = icon;
        this.name = name;

    }

    public Social(int icon, String name, int totalUser, int dolares) {
        this.icon = icon;
        this.name = name;
        this.totalUser = totalUser;
        this.dolares = dolares;
    }

    public int getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(int totalUser) {
        this.totalUser = totalUser;
    }

    public int getDolares() {
        return dolares;
    }

    public void setDolares(int dolares) {
        this.dolares = dolares;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Social o) {
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return name.equals(((Social)obj).name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
