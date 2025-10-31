package org.example.projetoesgfiap.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;



public class CadastroPontoColetaService {

    private static final String BASE_URL = "http://localhost:8080/api/pontos-coleta";

    String schemasPath = "src/test/resources/schemas/";

    JSONObject jasonSchema;

    private final ObjectMapper mapper = new ObjectMapper();


    public Response cadastrarPontoColeta(Object pontoColetaRequestDto, String endpoint) {
        return io.restassured.RestAssured
                .given()
                .contentType(io.restassured.http.ContentType.JSON)
                .body(pontoColetaRequestDto)
                .when()
                .post(BASE_URL + endpoint)
                .then()
                .extract()
                .response();
    }

    public Response excluirPontoColeta(Long id) {
        return io.restassured.RestAssured
                .given()
                .contentType(io.restassured.http.ContentType.JSON)
                .when()
                .delete(BASE_URL + "/deletarPontoColeta/" + id)
                .then()
                .extract()
                .response();
    }

    private JSONObject loadJsonFromFile(String filePath) throws IOException, JSONException {
        // Lê todos os bytes do arquivo e transforma em String
        String jsonContent = Files.readString(Paths.get(filePath));

        // Cria o tokener a partir do conteúdo real do JSON
        JSONTokener tokener = new JSONTokener(jsonContent);

        // Retorna o objeto JSON carregado corretamente
        return new JSONObject(tokener);
    }

    public void setContract(String contract) throws IOException, JSONException {
        switch (contract) {
            case "Cadastro de ponto de coleta bem sucedido" -> jasonSchema = loadJsonFromFile(schemasPath + "cadastro-ponto-coleta-schema.json");
            default -> throw new IllegalStateException("Unexpected contract" + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema(Response response) throws IOException, JSONException {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jasonSchema.toString());
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);
        return schemaValidationErrors;
    }
}
