package UtilityClasses;

import java.io.*;

public class CSVUtils {

    public static void processCSV(String inputFilePath, String outputFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Convert , delimiter to ||| but preserve quoted commas
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                StringBuilder modifiedLine = new StringBuilder();

                for (int i = 0; i < fields.length; i++) {
                    String field = fields[i];

                    // Replace content ""(two) into "(one)
                    field = field.replace("\"\"", "\"");

                    // Append the modified field
                    modifiedLine.append(field);

                    // Append the delimiter ||| if not the last field
                    if (i < fields.length - 1) {
                        modifiedLine.append("|||");
                    }
                }
                String newLine = modifiedLine.toString();
                // Replace content "||| into |||
                newLine = newLine.replace("\"|||", "|||");
                // Replace content "||| into |||
                newLine = newLine.replace("|||\"", "|||");

                // Write the modified line to the output file
                writer.write(newLine);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
