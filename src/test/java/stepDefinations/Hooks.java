package stepDefinations;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@Delete")
    public void beforeScenario() throws IOException {
StepDefination stepDefination = new StepDefination();
if (StepDefination.place_id == null){
stepDefination.add_place_pay_load_with("alex","French","asia");
stepDefination.user_calls_with_http_request("addPlaceApi","Post");
stepDefination.verify_place_id_created_maps_to_using("alex","getPlaceApi");
    }
    }
}
