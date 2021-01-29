package pl.coderslab.entity;

public class User {
    private int id;
    private String userName;
    private String email;
    private String password;
// zastanowic sie jak zmienic id zeby bylo wieksze od 0 (zaciagniete z bazy)
    public void setId(int id) {
        this.id = id;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    //poszukac walidacji emaila
    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId(){
        return id;
    }
    public String getUserName(){
        return userName;
    }
    public String getEmail(){
        return email;
    }
    //zastanowic sie czy geter do hasla zostaje
    public String getPassword(){
        return password;
    }

    @Override
    public String toString() {
        String result = "id = " + id + ", userName = " + this.getUserName() + ", email = " + this.getEmail() + ", password = " + this.getPassword();
        //if(this.getUserName() != null & this.getEmail() != null) {
            return result;
       // } else {
        //    return "Objekt jest null'em";
       // }
    }

    public User(String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
    public User(){}
}
