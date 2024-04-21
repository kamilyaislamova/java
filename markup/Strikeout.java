package markup;

import java.util.List;

public class Strikeout extends Marking {

    public Strikeout (List <Markdown> l) {
        super(l);
        sym = "~";
        symOpen = "[s]";
        symClose = "[/s]";
    }
}
