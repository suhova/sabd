package app;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.cli.*;

import java.io.*;

public class ObfuscationApplication {

    public static void main(String[] args) throws IOException {
        Options options = new Options();

        Option input = new Option("i", "input", true, "input file path");
        input.setRequired(true);
        options.addOption(input);

        Option output = new Option("o", "output", true, "output file path");
        output.setRequired(true);
        options.addOption(output);

        Option flagOpt = new Option("f", "flag", true, "is obfuscate?");
        flagOpt.setRequired(true);
        options.addOption(flagOpt);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        try {
            CommandLine cmd = parser.parse(options, args);
            String pathInput = cmd.getOptionValue("input");
            String pathOutput = cmd.getOptionValue("output");
            boolean flag = Boolean.parseBoolean(cmd.getOptionValue("flag"));
            File fileInput = new File(pathInput);
            File fileOutput = new File(pathOutput);

            XmlMapper xmlMapper = new XmlMapper();
            String xml = inputStreamToString(new FileInputStream(fileInput));
            ConfigResolver inputConfig = xmlMapper.readValue(xml, ConfigResolver.class);
            ConfigResolver outputConfig;
            if (flag) {
                outputConfig = inputConfig.obfuscate();
            } else {
                outputConfig = inputConfig.unobfuscate();
            }
            xmlMapper.writerWithDefaultPrettyPrinter().writeValue(fileOutput, outputConfig);
            System.out.println("_SUCCESS");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
        }

    }

    public static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
