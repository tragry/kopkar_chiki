package tiara.anggreyani.chicken.Model.Login;

public class LoginResponse {

    private String accessToken;
    private String token_type;
    private int expires_int;

    public String getAccessToken(){
        return accessToken;
    }
    public void setAcessToken(String accessToken){
        this.accessToken = accessToken;
    }
    public String getToken_type(){
        return token_type;
    }
    public void setToken_type(String token_type){
        this.token_type = token_type;
    }
    public int getExpires_int(){
        return expires_int;
    }
    public void setExpires_int(int expires_int){
        this.expires_int = expires_int;
    }
}
