package lei.estg.utils;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import lei.estg.models.Player;

import java.io.FileReader;
import java.io.IOException;

public class JogoConfig {

    /**
     * Loads the configuration from the specified file.
     * This method reads a JSON file from the given file path and deserializes it into a JsonObject.
     * If an error occurs during reading or deserialization, the exception is caught and printed.
     *
     * @param caminhoFicheiro the path to the configuration file
     * @return the deserialized JsonObject from the file, or null if an error occurs
     */
    private JsonObject carregarConfig(String caminhoFicheiro) {
        try (FileReader reader = new FileReader(caminhoFicheiro)) {
            JsonObject jsonObject = (JsonObject) Jsoner.deserialize(reader);
            return jsonObject;
        } catch (JsonException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Loads the player configuration from the specified file.
     * This method reads a JSON file from the given file path, extracts the player configuration,
     * and creates a Player object with the extracted data.
     *
     * @param caminhoFicheiro the path to the configuration file
     * @return the Player object created from the configuration file, or null if the file path is null
     */
    public Player carregarPlayerConfig(String caminhoFicheiro) {
        if (caminhoFicheiro == null) return null;

        JsonObject playerObject = (JsonObject) carregarConfig(caminhoFicheiro).get("player");

        Player player = new Player(
                (String) playerObject.get("nome"),
                ((Number) playerObject.get("poder")).intValue(),
                ((Number) playerObject.get("vidaMaxima")).intValue(),
                ((Number) playerObject.get("mochila-limite")).intValue()
        );
        return player;

    }
}
