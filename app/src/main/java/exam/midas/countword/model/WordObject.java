package exam.midas.countword.model;

public class WordObject {
    private int number;
    private String name;
    private String line;

    public WordObject() {
        number = 1;
        name = "";
        line = "";
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        String result = getLine();
        if (result.length() > 0) {
            if (!result.contains(line))
                this.line = result + (" " + line);
        } else {
            this.line = line;
        }
    }
}
