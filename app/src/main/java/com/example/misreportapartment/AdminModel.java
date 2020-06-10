package com.example.misreportapartment;

public class AdminModel {
    private int id;
    private String adminUserName;
    private String password;
    private String cnf_password;

    public AdminModel(int id, String adminUserName, String password, String cnf_password) {
        this.id = id;
        this.adminUserName = adminUserName;
        this.password = password;
        this.cnf_password = cnf_password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCnf_password() {
        return cnf_password;
    }

    public void setCnf_password(String cnf_password) {
        this.cnf_password = cnf_password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AdminModel{");
        sb.append("id=").append(id);
        sb.append(", adminUserName='").append(adminUserName).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", cnf_password='").append(cnf_password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
