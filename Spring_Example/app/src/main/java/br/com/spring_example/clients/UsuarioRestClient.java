package br.com.spring_example.clients;


import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.spring_example.model.Usuario;

public class UsuarioRestClient {

    private String BASE_URL = "http://172.20.1.75:8080/usuario/";
    private RestTemplate restTemplate = new RestTemplate();

    public List<Usuario> findAll() {
        try {
            return restTemplate.exchange(
                    BASE_URL + "findall",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Usuario>>() {
                    }
            ).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public Usuario find(int id) {
        try {
            return restTemplate.exchange(
                    BASE_URL + "find/" + id,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Usuario>() {
                    }
            ).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean save(Usuario usuario) {
        try {
            Map<String, String> values = new HashMap<>();
            values.put("nome", usuario.getNome());
            values.put("email", usuario.getEmail());

            JSONObject jsonObject = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);


            jsonObject.put("nome", usuario.getNome());
            jsonObject.put("email", usuario.getEmail());

            HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);

            restTemplate.postForEntity(BASE_URL + "save", entity, null);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            restTemplate.delete(BASE_URL + "delete/" + id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(Usuario usuario) {
        try {
            Map<String, String> values = new HashMap<>();
            values.put("id", String.valueOf(usuario.getId()));
            values.put("name", usuario.getNome());
            values.put("email", usuario.getEmail());

            JSONObject jsonObject = new JSONObject();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<>(jsonObject.toString(), headers);

            restTemplate.put(BASE_URL + "update", entity);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
