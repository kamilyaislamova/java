package markup;

import java.util.List;

public class Emphasis extends Marking {
    
    public Emphasis (List <Markdown> l) {
        super(l);
        sym = "*";
        symOpen = "[i]";
        symClose = "[/i]";
    }

}
