// THINZAR HNIN YU
// 2201014
// DIT/FT/1B/03

package CA2;

import javax.swing.JOptionPane;

public class Administrator {

    private String adminID;
    private String name;
    private String password;

    public Administrator(String adminID, String name, String password) {
        this.adminID = adminID;
        this.name = name;
        this.password = password;
    }

    public String getAdminID() {
        return adminID;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
