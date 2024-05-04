package resources;

public enum ApiResources {
    addPlaceApi("/maps/api/place/add/json"),
    getPlaceApi("/maps/api/place/get/json"),
    deletePlaceApi("/maps/api/place/delete/json");
    private String endpoint;

    ApiResources(String endpoint){

        this.endpoint = endpoint;
    }
    public String getEndpoint(){
        return endpoint;
    }
}
