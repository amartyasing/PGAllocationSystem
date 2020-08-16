package com.example.pgallocationsystem.com.bean;

public class Candidate {
    String name,email,phoneno,address,gender,age,profession,collegename,companyname,alternatephoneno;

    @Override
    public String toString() {
        return "Name- "+name+"\n"+"Email- "+email+"\n"+"Phoneno- "+phoneno+"\n"+"Address- "+address+"\n"+"Gender- "+gender+"\n"+"Age- "+age+"\n"+"Profession- "+profession+"\n"+"Collegename- "+collegename+"\n"+"Companyname- "+companyname+"\n"+"AlternatePhoneno- "+alternatephoneno;
    }

    public Candidate(String name, String email, String phoneno, String address, String gender, String age, String profession, String collegename, String companyname, String alternatephoneno) {
        this.name = name;
        this.email = email;
        this.phoneno = phoneno;
        this.address = address;
        this.gender = gender;
        this.age = age;
        this.profession = profession;
        this.collegename = collegename;
        this.companyname = companyname;
        this.alternatephoneno = alternatephoneno;
    }

    public Candidate() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getAlternatephoneno() {
        return alternatephoneno;
    }

    public void setAlternatephoneno(String alternatephoneno) {
        this.alternatephoneno = alternatephoneno;
    }
}
