package es.pablolopez.InventoryJetPack.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Sector implements Parcelable {

    public static final String TAG = "Sector";
    private String name;
    private String shortname;
    private String description;
    private Dependency dependencia;
    private String uriImage;

    public Sector() {
    }

    public Sector(String name, String shortname, String description, Dependency dependencia, String uriImage) {
        this.name = name;
        this.shortname = shortname;
        this.description = description;
        this.dependencia = dependencia;
        this.uriImage = uriImage;
    }

    protected Sector(Parcel in) {
        name = in.readString();
        shortname = in.readString();
        description = in.readString();
        dependencia = in.readParcelable(Dependency.class.getClassLoader());
        uriImage = in.readString();
    }

    public static final Creator<Sector> CREATOR = new Creator<Sector>() {
        @Override
        public Sector createFromParcel(Parcel in) {
            return new Sector(in);
        }

        @Override
        public Sector[] newArray(int size) {
            return new Sector[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dependency getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependency dependencia) {
        this.dependencia = dependencia;
    }

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(shortname);
        dest.writeString(description);
        dest.writeParcelable(dependencia, flags);
        dest.writeString(uriImage);
    }
}
