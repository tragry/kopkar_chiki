package tiara.anggreyani.chicken;

import retrofit2.http.GET;
import retrofit2.http.Header;
import tiara.anggreyani.chicken.Model.Home.HomeResponse;
import tiara.anggreyani.chicken.Model.Login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import tiara.anggreyani.chicken.Model.Login.SendLogin;
import tiara.anggreyani.chicken.Model.Plafon.PlafonResponse;
import tiara.anggreyani.chicken.Model.Profile.ProfileResponse;
import tiara.anggreyani.chicken.Model.Simpanan.SimpananResponse;

public interface UserService {

    @POST("api/login")
    Call<LoginResponse> userLogin(@Body SendLogin loginRequest);

    //@Headers("Accept: application/json")
    @GET("api/home")
    Call<HomeResponse> dataHome(@Header("Authorization") String token);

    //@Headers("Accept: application/json")
    @GET("api/profile")
    Call<ProfileResponse> dataProfile(@Header("Authorization") String token);

    @GET("api/plafon")
    Call<PlafonResponse> dataPlafon(@Header("Authorization") String token);

    @GET("api/simpanan")
    Call<SimpananResponse> dataSimpanan(@Header("Authorization") String token);
}
