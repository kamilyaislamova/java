package markup;

public class Text implements Markdown{
    String s;

    public Text (String s1) {
        this.s = s1;
    }

    public void toMarkdown(StringBuilder a) {
        a.append(s);
    }

    public void toBBCode(StringBuilder a) {
        a.append(s);
    }
}
