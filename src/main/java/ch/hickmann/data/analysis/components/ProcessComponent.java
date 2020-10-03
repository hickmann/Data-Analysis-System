package ch.hickmann.data.analysis.components;

public class ProcessComponent {

    public String[] getFields(String line, String delimiter) {
        return line.split(delimiter);
    }
}
