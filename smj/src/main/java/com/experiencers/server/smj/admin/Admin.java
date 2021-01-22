package com.experiencers.server.smj.admin;


import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long admin_id;
    @Column(nullable = false, length = 50)
    private String ID;

    public Long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Long admin_id) {
        this.admin_id = admin_id;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", ID='" + ID + '\'' + '}';
    }
}
