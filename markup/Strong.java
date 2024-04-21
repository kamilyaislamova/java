package markup;

import java.util.List;

public class Strong extends Marking {
    
    public Strong (List <Markdown> l) {
        super(l);
        sym = "__";
        symOpen = "[b]";
        symClose = "[/b]";
    }
}
