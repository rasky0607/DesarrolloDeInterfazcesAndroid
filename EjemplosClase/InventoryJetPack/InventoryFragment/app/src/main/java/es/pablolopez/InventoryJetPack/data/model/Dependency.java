package es.pablolopez.InventoryJetPack.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

//Anotaciones de la libreria de Room @Entity @NomNull etc..
@Entity
public class Dependency implements Parcelable {
    @Ignore
    public static final String TAG = "dependency";

    @PrimaryKey @NonNull
    private String shortname;
    @ColumnInfo @NonNull
    private String name;
    private String description;
    private String inventory;
    private String uriImage;

    @Ignore
    public Dependency() {
    }

    //Este constructor es el unico que no ignoramos con la libreria room
    public Dependency(String name, String shortname, String description, String inventory, String uriImage) {
        this.name = name;
        this.shortname = shortname;
        this.description = description;
        this.inventory = inventory;
        this.uriImage = uriImage;
    }

    @Ignore
    protected Dependency(Parcel in) {
        name = in.readString();
        shortname = in.readString();
        description = in.readString();
        inventory = in.readString();
        uriImage = in.readString();
    }

    public static final Creator<Dependency> CREATOR = new Creator<Dependency>() {
        @Override
        public Dependency createFromParcel(Parcel in) {
            return new Dependency(in);
        }

        @Override
        public Dependency[] newArray(int size) {
            return new Dependency[size];
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

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return name;
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
        dest.writeString(inventory);
        dest.writeString(uriImage);
    }
}
