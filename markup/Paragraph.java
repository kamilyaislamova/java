package markup;

import java.util.List;

public class Paragraph implements BBCode{
    List <Markdown> ls;

    public Paragraph (List <Markdown> l) {
        this.ls = l;
    }

    public void toMarkdown (StringBuilder s) {
        for (Markdown var: ls) {
            var.toMarkdown(s);
        }
    }

    public void toBBCode (StringBuilder s) {
        for (Markdown var: ls) {
            var.toBBCode(s);
        }
    }
}