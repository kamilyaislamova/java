package markup;

import java.util.List;

public abstract class ListBB implements BBCode {
    List <ListItem> ls;
    String symOpen;
    String symClose = "[/list]";

    public ListBB (List <ListItem> l) {
        this.ls = l;
    }

    public void toBBCode(StringBuilder s) {
        s.append(symOpen);
        for (ListItem var: ls) {
            var.toBBCode(s);
        }
        s.append(symClose);
    }
}
