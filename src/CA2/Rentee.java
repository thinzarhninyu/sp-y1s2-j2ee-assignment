// THINZAR HNIN YU
// 2201014
// DIT/FT/1B/03

package CA2;

import java.io.*;

public class Rentee implements Serializable{

    private String memberID;
    private String name;
    private String[] Comics;

    public Rentee(String memberID, String name, String[] Comics) {
        this.memberID = memberID;
        this.name = name;
        this.Comics = Comics;
    }

    public String getMemberID() {
        return memberID;
    }

    public String getName() {
        return name;
    }

    public String[] getComics() {
        return Comics;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComics(String[] Comics) {
        this.Comics = Comics;
    }
}
