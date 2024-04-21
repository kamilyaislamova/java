package markup;

import java.util.List;

public class ListItem {
    List<BBCode> ls;
    String symOpen = "[*]";

    public ListItem(List<BBCode> l) {
        this.ls = l;
    }

    public void toBBCode(StringBuilder s) {
        s.append(symOpen);
        for (BBCode var: ls) {
            var.toBBCode(s);
        }
    }
}
