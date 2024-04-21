package markup;

import java.util.List;

public class UnorderedList extends ListBB{
    
    public UnorderedList(List<ListItem> l) {
        super(l);
        symOpen = "[list]";
    }
}
