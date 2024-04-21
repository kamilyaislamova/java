package markup;

import java.util.List;

public abstract class Marking implements Markdown{
    String sym;
    List <Markdown> ls;
    String symOpen;
    String symClose;

    public Marking (List <Markdown> l) {
        this.ls = l;
    }

    public void toMarkdown(StringBuilder a) {
        a.append(sym);
        for (Markdown var: ls) {
            var.toMarkdown(a);
        }
        a.append(sym);
    }

    public void toBBCode(StringBuilder a) {
        a.append(symOpen);
        for (Markdown var: ls) {
            var.toBBCode(a);
        }
        a.append(symClose);
    }    
}
