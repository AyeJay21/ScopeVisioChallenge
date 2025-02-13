package com.example.codingchallenge.postcodesData;

import com.opencsv.CSVReader;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

@Service
public class PostCodeService implements PostCodeInterface {
    public PostCodeRepository postCodeRepository;

    public PostCodeService(PostCodeRepository postCodeRepository) {
        this.postCodeRepository = postCodeRepository;
    }

    /**
     * Ruft postalCodeImportFromCSV auf, wenn Repository leer ist
     * @throws IOException
     */
    @PostConstruct
    public void init() throws IOException {
        if (postCodeRepository.count() == 0) {
            postalCodeImportFromCSV();
        }
    }

    /**
     * Überträgt korrigierten csv records in die Datenbank
     * @throws IOException
     */
    @Override
    public void postalCodeImportFromCSV() throws IOException {
        File file = new File("/app/resources/postcodes.csv");
        CSVReader reader = new CSVReader(new FileReader(file));

        String[] records;
        reader.readNext();

        // Ersetzt fehlenden Umlaute in der csv und speichert sie in die Datenbank
        while ((records = reader.readNext()) != null) {
            String[] values = records[0].split(",");

            //REGION1
            String federalState = records[2].replaceAll("\"", "").trim();
            federalState = federalState.replace("√§", "ä").replace("√", "ß").replace("√º", "ü").replace("√∂", "ö");
            //REGION3
            String land = records[4].replaceAll("\"", "").trim();
            land = land.replace("√§", "ä").replace("√", "ß").replace("√º", "ü").replace("√∂", "ö");
            //REGION4
            String city = records[5].replaceAll("\"", "").trim();
            city = city.replace("√§", "ä").replace("√", "ß").replace("√º", "ü").replace("√∂", "ö");
            //POSTLEITZAHL
            String postalCode = records[6].replaceAll("\"", "").trim();

            PostCode postCode = new PostCode();
            postCode.setId(UUID.randomUUID());
            postCode.setFederalState(federalState);
            postCode.setLand(land);
            postCode.setCity(city);
            postCode.setPostalCode(postalCode);

            postCodeRepository.save(postCode);
        }
        reader.close();
    }
}
