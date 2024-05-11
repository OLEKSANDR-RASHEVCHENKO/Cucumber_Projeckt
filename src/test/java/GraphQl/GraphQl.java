package GraphQl;

import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class GraphQl {
    public static void main(String[] args) {
        //Query
        String response=given().log().all().header("Content-Type", "application/json")
                .body("{\"query\":\"query{ \\n\\n  character(characterId:7823)\\n  {id,name,type,status,species,gender}\\n  location(locationId:9083)\\n  {name,type,dimension}\\n  episode(episodeId:6197)\\n  {name}\\n  characters(filters:{name:\\\"Walerii\\\"})\\n  {info{count}\\n  result{name,type}}\\n  \\n  \\n  \\n  \\n}\",\"variables\":null}")
                .when().post("https://rahulshettyacademy.com/gq/graphql")
                .then().extract().response().asString();
        System.out.println(response);
        JsonPath jsonPath = new JsonPath(response);
        String name=jsonPath.getString("data.character.name");
        Assert.assertEquals(name,"Walerii");
        System.out.println(name);

        //Mutation
        String mutationResponse=given().log().all().header("Content-Type", "application/json")
                .body("{\"query\":\"mutation\\n{\\n   createLocation(location:{name:\\\"Schwepitz\\\",type:\\\"Deutshcland\\\",dimension:\\\"243\\\"})\\n  {id}\\n  createCharacter(character:{name:\\\"Walerii\\\",type:\\\"Ticher\\\",status:\\\"live\\\",species:\\\"fantasy\\\",gender:\\\"male\\\",image:\\\"ping\\\",originId:9082,locationId:9082})\\n  {id}\\n  createEpisode(episode:{name:\\\"Raw and rust Agent\\\",air_date:\\\"1990 june\\\",episode:\\\"Netflix\\\"})\\n  {id}\\n}\\n  \\n\\n\",\"variables\":null}")
                .when().post("https://rahulshettyacademy.com/gq/graphql")
                .then().extract().response().asString();
        System.out.println(mutationResponse);


    }

}
