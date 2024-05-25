package entity;

public class errorWord {//bean类
    private Integer line;
    private String word;

    public errorWord(int line, String word) {
        this.line = line;
        this.word = word;
    }

    public int getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return "line: "+line+", word: "+word+"end";
    }
}
