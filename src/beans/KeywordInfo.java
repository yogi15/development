package beans;

public class KeywordInfo extends Object {
    public String   name;
    public int      start;
    public int      end;
       
    public KeywordInfo(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }
}