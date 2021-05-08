package com.example.hulaki;

public class UsersModel {
    private String userId,userName,userProfileImage,userPhoneNumber,email,password;


    public UsersModel(){

    }
    public UsersModel(String uname,String mail,String password,String uid,String userProfileImage){
        this.userName=uname;
        this.email=mail;
        this.password=password;
        this.userId=uid;
        this.userProfileImage=userProfileImage;
    }
    public UsersModel(String userId, String userName, String userProfileImage, String userPhoneNumber) {
        this.userId = userId;
        this.userName = userName;
        this.userProfileImage = userProfileImage;
        this.userPhoneNumber = userPhoneNumber;
    }

   public String getEmail(){
        return email;
   }
public void setEmail(String email){
        this.email=email;
}
public String getPassword(){
        return  password;
}
public void setPassword(String password){
        this.password=password;
}
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserProfileImage() {
        return userProfileImage;
    }

    public void setUserProfileImage(String userProfileImage) {
        this.userProfileImage = userProfileImage;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }
}
